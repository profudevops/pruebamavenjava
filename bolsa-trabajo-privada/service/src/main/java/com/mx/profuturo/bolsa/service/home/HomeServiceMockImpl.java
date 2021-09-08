package com.mx.profuturo.bolsa.service.home;


import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.service.dto.ContenidoHomeDTO;
import com.mx.profuturo.bolsa.model.service.dto.ImageDataDTO;
import com.mx.profuturo.bolsa.model.vo.home.Galeria;
import com.mx.profuturo.bolsa.model.vo.home.SeccionVO;
import com.mx.profuturo.bolsa.model.vo.home.ContenidoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope("request")
@Profile("mock")
public class HomeServiceMockImpl extends HomeServiceBase {
    @Value("${bolsaTrabajo.images.path}")
    private String imagePath;

    @Override
    public ContenidoVO __callGetHomeContent(RequestBean<GenericRequestBean> genericRequestBean)throws GenericStatusException {
        ContenidoVO homeContent = new ContenidoVO();

        Map<Integer,String> imagenes = new HashMap<>();

        Galeria galeria = new Galeria();
        galeria.setTitulo("Conoce más de nuestra cultura");
        galeria.setTexto("Te invitamos a formar parte de nuestro equipo");

        imagenes.put(1,"http://www.profuturo.mx/blog/wp-content/uploads/2019/08/imagen_blog190_1300.png");
        imagenes.put(2,"http://www.profuturo.mx/blog/wp-content/uploads/2019/08/imagen_blog184_1300.png");
        imagenes.put(3,"http://www.profuturo.mx/blog/wp-content/uploads/2019/11/CDC_NOV_11_1840.png");
        imagenes.put(4,"http://www.profuturo.mx/blog/wp-content/uploads/2019/11/CDC_NOV_11_1840.png");
        imagenes.put(5,"http://www.profuturo.mx/blog/wp-content/uploads/2019/08/imagen_blog190_1300.png");

        galeria.setImagenesOut(imagenes);

        homeContent.setCabecera(new SeccionVO("Profuturo","Bolsa de Trabajo","Bienvenido","http://www.profuturo.mx"));
        homeContent.setBuscadorCodigo(new SeccionVO("Código de vacante","Introduce el código de la vacante","El código es de 6 caracteres",""));
        homeContent.setCultura(new SeccionVO("Nuestra cultura","Queremos que conozcas la cultura Profuturo","Pulsa el botón para reporoducir el vídeo","https://www.youtube.com/watch?v=9cIBeWbu4rM"));
        homeContent.setQuienesSomos(new SeccionVO("Quieres conocer más","En Profuturo tenemos la oportunidad que estás buscando","Desarrolla tu talento al máximo",""));
        homeContent.setGaleria(galeria);


        return homeContent;

    }


    @Override
    protected boolean __callUploadImage(ImageDataDTO imageDataDTO) throws IOException, FileNotFoundException {
        String filePath = this.imagePath+imageDataDTO.getImageName()+imageDataDTO.getImageExtension();


        try
        {
            File folder = new File(this.imagePath);
            folder.setWritable(true);
            File imageFile =  new File(filePath);
            String[] fileList = folder.list();
            for(String image : fileList){
                if(image.startsWith(imageDataDTO.getImageName())){
                    File fileToDelete = new File(this.imagePath+image);
                    fileToDelete.delete();
                }
            }

            imageFile.createNewFile();
            new FileOutputStream(filePath,false).write(imageDataDTO.getImageString());
            folder.setWritable(false);
            folder.setReadable(true);
            return true;
        }
        catch(IOException e)
        {
            throw e;
        }
    }

    @Override
    protected GenericTextResponseBean __callUpdateHomeContent(RequestBean<ContenidoHomeDTO> genericRequestBean)throws GenericStatusException{

        GenericTextResponseBean response = new GenericTextResponseBean();
         response.setRespuesta("Exito");
        return response;
    }
}
