package com.mx.profuturo.bolsa.service.home;

import com.mx.profuturo.bolsa.model.service.dto.ContenidoHomeDTO;
import com.mx.profuturo.bolsa.model.service.dto.ImagenGaleriaDTO;
import com.mx.profuturo.bolsa.model.vo.home.ContenidoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface HomeService {

    ContenidoVO getHomeContent() throws GenericStatusException;
    boolean saveHomeContent(ContenidoHomeDTO contenidoHomeInput) throws GenericStatusException;
    boolean uploadGalleryImage(ImagenGaleriaDTO imagen) throws IOException, FileNotFoundException;


}
