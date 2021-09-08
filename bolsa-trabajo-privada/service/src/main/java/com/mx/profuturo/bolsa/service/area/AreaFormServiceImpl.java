package com.mx.profuturo.bolsa.service.area;

import com.mx.profuturo.bolsa.model.common.GenericParamRequestBean;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.*;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.basic.BasicIconFactory;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class AreaFormServiceImpl extends AreaFormServiceBase {

    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Value("${ws.areas.obtenerAreaInteres}")
    private String getInterestAreaEndPoint;

    @Value("${ws.areas.actualizarSubAreaInteres}")
    private String updateSubInterestareaEndPoint;

    @Value("${ws.areas.obtenerAreasInteres}")
    private String getInterestAreasEndPoint;

    @Value("${ws.areas.actualizarAreaInteres}")
    private String actualizarAreaInteres;

    @Value("${ws.areas.bajaSubareaDeInteres}")
    private String bajaSubareaDeInteres;

    @Value("${ws.areas.crearAreaInteres}")
    private String crearAreaInteres;

    @Value("${ws.areas.bajaAreaDeInteres}")
    private String bajaAreaDeInteres;

    @Value("${ws.areas.crearSubAreaInteres}")
    private String crearSubAreaInteres;

    @Value("${ws.bolsatrabajo.port}")
    private String port;

    @Autowired
    private RestClient restClient;

    @Override
    protected BasicDTO __callCreateInterestArea(RequestBean<AreaInteresDTO> genericRequestBean) throws GenericStatusException {
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,crearAreaInteres));
        BasicDTO response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, BasicDTO.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    public Boolean updateSubArea(SubAreaInteresDTO subDto) throws GenericStatusException {
        RequestBean<SubAreaInteresDTO> genericRequestBean = new RequestBean<>();
        genericRequestBean.setPayload(subDto);
        UpdateInterestSubareaResponseBean respuesta = this.callUpdateInterestSubarea(genericRequestBean);
        String responseText = respuesta.getRespuesta();
        if("Exito".equals(responseText)){
            return true;
        }
        throw new GenericStatusException();
    }


    @Override
    protected AreaInteresDTO __callgetInterestArea(RequestBean<GetAreaInteres> genericRequestBean) throws GenericStatusException {
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,getInterestAreaEndPoint));
        AreaInteresDTO response = null;
        try {
            response = restClient.exetuteGET(genericRequestBean, AreaInteresDTO.class);

        } catch (Exception e) {
             throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected List<AreaInteresDTO> __callgetInterestAreas(RequestBean<GetAreaInteres> genericRequestBean) throws GenericStatusException {

        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,getInterestAreasEndPoint));
        GetInterestAreasResponseBean serviceresponse = null;
        List<AreaInteresDTO> response=null;
        try {
            serviceresponse = restClient.exetuteGET(genericRequestBean, GetInterestAreasResponseBean.class);
            response = serviceresponse.getAreas();


        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected BasicDTO __callCreateSubtArea(RequestBean<SubAreaInteresDTO> genericRequestBean) throws GenericStatusException {

        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,crearSubAreaInteres));
        BasicDTO response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, BasicDTO.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected Boolean __callupdateSubArea(SubAreaInteresDTO subDto) throws GenericStatusException {
        return false;
    }

    @Override
    protected Boolean __calldeleteSubArea(int idsubDto) throws GenericStatusException {
        return false;
    }

    @Override
    protected GenericTextResponseBean __callUpdateInterestArea(RequestBean<AreaInteresDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,actualizarAreaInteres));
        GenericTextResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected UpdateInterestSubareaResponseBean callUpdateInterestSubarea(RequestBean<SubAreaInteresDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,updateSubInterestareaEndPoint));

        UpdateInterestSubareaResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, UpdateInterestSubareaResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected GenericTextResponseBean callDropInterestaArea(RequestBean<DropInterestAreaRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,bajaAreaDeInteres));
        GenericTextResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected GenericTextResponseBean callDropInterestSubarea(RequestBean<DropInterestSubareaRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,bajaSubareaDeInteres));
        GenericTextResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }



}
