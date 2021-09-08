package com.mx.profuturo.bolsa.web.controller.home;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.dto.ContenidoHomeDTO;
import com.mx.profuturo.bolsa.model.service.dto.ImagenGaleriaDTO;
import com.mx.profuturo.bolsa.model.vo.home.ContenidoVO;
import com.mx.profuturo.bolsa.service.home.HomeService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

@RequestMapping(value="contenido-home")
@Controller("homeContent")
@Scope("request")
@CrossOrigin
public class HomeContentController extends BaseController {

    @Autowired
    HomeService homeService;


    @CrossOrigin
    @RequestMapping(value = "/obtener-contenido-home", method = RequestMethod.GET)
    @ApiOperation(value = "home")
    public @ResponseBody
    EntityResponseBean<ContenidoVO> getHomeContent() throws GenericStatusException{
        ContenidoVO respuesta = homeService.getHomeContent();

        return this.buildEntitySuccessResponse(respuesta);
    }

    @CrossOrigin
    @RequestMapping(value = "/guardar-contenido-home", method = RequestMethod.POST)
    @ApiOperation(value = "home")
    public @ResponseBody
    BaseResponseBean saveHomeContent(@RequestBody ContenidoHomeDTO contenido) throws GenericStatusException{


        homeService.saveHomeContent(contenido);

        return this.buildSuccessResponse();
    }


    @CrossOrigin
    @RequestMapping(value = "/subir-imagen-galeria", method = RequestMethod.POST)
    @ApiOperation(value = "home")
    public @ResponseBody
    BaseResponseBean saveGalleryImage(@RequestBody ImagenGaleriaDTO imagen) throws GenericStatusException, IOException {

       homeService.uploadGalleryImage(imagen);
           return this.buildSuccessResponse();
    }




}
