package com.mx.profuturo.bolsa.service.home;

import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.service.dto.ContenidoHomeDTO;
import com.mx.profuturo.bolsa.model.service.dto.ImageDataDTO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.home.ContenidoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class HomeServiceImpl extends HomeServiceBase {

    @Value("${bolsaTrabajo.images.path}")
    private String imagePath;

    @Value("${bolsaTrabajo.images.fileSystem}")
    private String IMAGE_FILESYSTEM;

    @Value("${ws.bolsatrabajo.port}")
    private String port;

    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Value("${ws.contenido.ObtenerContenidoHome}")
    private String obtenerContenidoHome;

    @Value("${ws.contenido.guardarContenidoHome}")
    private String guardarContenidoHome;

    @Autowired
    RestClient restClient;

    @Override
    protected boolean __callUploadImage(ImageDataDTO imageDataDTO) throws IOException, FileNotFoundException {
        String filePath = this.IMAGE_FILESYSTEM+imageDataDTO.getImageName()+imageDataDTO.getImageExtension();


        try
        {
            File folder = new File(this.IMAGE_FILESYSTEM);
            File imageFile =  new File(filePath);
            String[] fileList = folder.list();
            if(fileList.length>0) {
                for (String image : fileList) {
                    if (image.startsWith(imageDataDTO.getImageName())) {
                        File fileToDelete = new File(this.IMAGE_FILESYSTEM + image);
                        fileToDelete.delete();
                    }
                }
            }

            imageFile.createNewFile();
            new FileOutputStream(filePath,false).write(imageDataDTO.getImageString());
            return true;
        }
        catch(IOException e)
        {
            throw e;
        }
    }

    @Override
    protected ContenidoVO __callGetHomeContent(RequestBean<GenericRequestBean> genericRequestBean) throws GenericStatusException {
        ContenidoVO response = new ContenidoVO();
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerContenidoHome));
        try {

           response = restClient.exetuteGET(genericRequestBean, ContenidoVO.class);

        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected GenericTextResponseBean __callUpdateHomeContent(RequestBean<ContenidoHomeDTO> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,guardarContenidoHome));
        GenericTextResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

}
