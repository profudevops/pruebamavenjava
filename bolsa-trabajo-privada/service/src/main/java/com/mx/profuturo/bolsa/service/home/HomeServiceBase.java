package com.mx.profuturo.bolsa.service.home;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.service.dto.*;
import com.mx.profuturo.bolsa.model.vo.home.ContenidoVO;
import com.mx.profuturo.bolsa.model.vo.home.Galeria;
import com.mx.profuturo.bolsa.model.vo.home.SeccionVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.codec.Base64;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class HomeServiceBase implements HomeService{

    @Value("${bolsaTrabajo.images.web.path}")
    private String IMAGE_PATH;

    @Value("${bolsaTrabajo.images.fileSystem}")
    private String IMAGE_FILESYSTEM;

    @Override
    public ContenidoVO getHomeContent() throws GenericStatusException{
        RequestBean<GenericRequestBean> genericRequestBean = new RequestBean<>();
        ContenidoVO contenidoHome = this.__callGetHomeContent(genericRequestBean);
        contenidoHome = setEmptyHomeFields(contenidoHome);
        if(contenidoHome.getGaleria()!=null && contenidoHome.getGaleria().getImagenesOut().size()>0){
            Map<Integer,String> imageMap = new HashMap<>();
            for(Map.Entry<Integer,String> entry : contenidoHome.getGaleria().getImagenesOut().entrySet()){
                imageMap.put(entry.getKey(),IMAGE_PATH+entry.getValue());
            }
            contenidoHome.getGaleria().setImagenesOut(imageMap);
        }
        if(null != contenidoHome.getCultura()){
            contenidoHome.getCultura().setMediaUrl(contenidoHome.getCultura().getTexto());
        }
        return contenidoHome;

    }

    @Override
    public boolean saveHomeContent(ContenidoHomeDTO contenidoHomeInput) throws GenericStatusException{


    RequestBean<ContenidoHomeDTO> genericRequestBean = new RequestBean<>();
    genericRequestBean.setPayload(setNewHomeData(contenidoHomeInput));
        GenericTextResponseBean response = this.__callUpdateHomeContent(genericRequestBean);
        if("Exito".equals(response.getRespuesta())){
            return true;
        }
        throw new GenericStatusException();
    }



    @Override
    public boolean uploadGalleryImage(ImagenGaleriaDTO imagen) throws IOException, FileNotFoundException{

        ImageDataDTO imageData = this.getImageData(imagen);

        return __callUploadImage(imageData);


    }


    private ImageDataDTO getImageData(ImagenGaleriaDTO imagen){
        ImageDataDTO imageData = new ImageDataDTO();

        String[] imageParts = imagen.getImagen().split(",");
        String imageExtension= imageParts[0].replace("data:image/","");
        imageExtension = "."+imageExtension.replace(";base64","");
        byte[] imageByte = Base64.decode(imageParts[1].getBytes());
        Integer toBeNamed = imagen.getOrden();

        imageData.setImageExtension(imageExtension);
        imageData.setImageString(imageByte);
        imageData.setImageName(toBeNamed.toString());


        return imageData;

    }


    protected abstract GenericTextResponseBean __callUpdateHomeContent(RequestBean<ContenidoHomeDTO> genericRequestBean)throws GenericStatusException;
    protected abstract boolean __callUploadImage(ImageDataDTO imageDataDTO)throws IOException,FileNotFoundException;
    protected abstract ContenidoVO __callGetHomeContent(RequestBean<GenericRequestBean> genericRequestBean) throws GenericStatusException;


    private Map<Integer,String> updateImages(Map<Integer,String> imageMap,ArrayList<NuevaImagenDTO> cambioImagenes){
        Map<Integer,String> newMap = imageMap;

        for(NuevaImagenDTO nuevaImagenDTO:cambioImagenes){
            for (Map.Entry<Integer,String> entry : imageMap.entrySet()){
                if(nuevaImagenDTO.getOrden()==entry.getKey()){
                    String[] imageParts = entry.getValue().split("\\.");
                    String sb = imageParts[0]+nuevaImagenDTO.getExtension();
                    newMap.put(nuevaImagenDTO.getOrden(),sb);
                }
            }
        }

        return newMap;

    }


    private ContenidoHomeDTO setNewHomeData(ContenidoHomeDTO contenidoHomeInput){

        ContenidoHomeDTO newContent  = new ContenidoHomeDTO();
        if(contenidoHomeInput.getGaleria()!=null) {
            Map<Integer, String> newMap = this.updateImages(contenidoHomeInput.getGaleria().getImagenesOut(), contenidoHomeInput.getGaleria().getImagenes());
            Galeria newGaleria = contenidoHomeInput.getGaleria();
            newGaleria.setImagenesOut(newMap);
            newContent.setGaleria(newGaleria);
        }
        if(contenidoHomeInput.getCabecera()!= null)
        {
            newContent.setCabecera(contenidoHomeInput.getCabecera());
        }
        if(contenidoHomeInput.getBuscador()!= null)
        {
            newContent.setBuscador(contenidoHomeInput.getBuscador());
        }
        if(contenidoHomeInput.getBuscadorCodigo()!= null)
        {
            newContent.setBuscadorCodigo(contenidoHomeInput.getBuscadorCodigo());
        }
        if(contenidoHomeInput.getCultura()!= null)
        {
            newContent.setCultura(contenidoHomeInput.getCultura());
        }
        if(contenidoHomeInput.getQuienesSomos()!=null){
            newContent.setQuienesSomos(contenidoHomeInput.getQuienesSomos());
        }

        return newContent;
    }

    private ContenidoVO setEmptyHomeFields(ContenidoVO contenidoHome){

        if(contenidoHome.getGaleria()==null){
            Map<Integer,String> imagenes = new HashMap<>();
            Galeria galeria = new Galeria();
            galeria.setTitulo("");
            galeria.setTexto("");
            galeria.setImagenesOut(imagenes);
            contenidoHome.setGaleria(galeria);
        }
        if(contenidoHome.getQuienesSomos()==null){
            contenidoHome.setQuienesSomos(new SeccionVO("","","",""));
        }
        if(contenidoHome.getBuscadorCodigo() == null){
            contenidoHome.setBuscadorCodigo(new SeccionVO("","","",""));
        }
        if(contenidoHome.getCultura() == null){
            contenidoHome.setCultura(new SeccionVO("","","",""));
        }
        if(contenidoHome.getCabecera() == null){
            contenidoHome.setCabecera(new SeccionVO("","","",""));
        }


        return contenidoHome;

    }

    //protected abstract boolean __callSaveHomeContent(RequestBean<GenericRequestBean> genericRequestBean) throws GenericStatusException;
}
