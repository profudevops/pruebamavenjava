package com.mx.profuturo.bolsa.model.vo.common;

import com.mx.profuturo.bolsa.model.vo.vacancy.PublicacionVO;
import com.mx.profuturo.bolsa.model.vo.home.ContenidoVO;
import com.mx.profuturo.bolsa.model.vo.output.Respuesta;
import com.mx.profuturo.bolsa.model.vo.search.PaginaResultadosVacanteVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@PropertySource({"classpath:application.properties"})
public class Utils {
    public static String statusTextOk;
    public static String messageOk;

    @Value("${httpStatus.ok.statusText}")
    public void setStatusTextOk(String statusText) {
        statusTextOk = statusText;
    }

    public final Respuesta RESPONSE_JOB_RESULTS(String status, PaginaResultadosVacanteVO jobResults) {
        Respuesta respuesta = new Respuesta();

        respuesta.setStatus(status);
        respuesta.setStatusText(statusTextOk);
        respuesta.setVacantes(jobResults);


        return respuesta;

    }

    public final Respuesta RESPONSE_INTEREST_AREAS(String status, ColeccionAreasInteresVO areasInteres) {
        Respuesta respuesta = new Respuesta();

        respuesta.setStatus(status);
        respuesta.setStatusText(statusTextOk);
        respuesta.setAreasInteres(areasInteres.getAreasInteres());


        return respuesta;
    }

    public final Respuesta RESPONSE_HOME_CONTENT(String status, ContenidoVO homeContent) {
        Respuesta respuesta = new Respuesta();

        respuesta.setStatus(status);
        respuesta.setStatusText(statusTextOk);
        respuesta.setHome(homeContent);


        return respuesta;
    }

    public final Respuesta RESPONSE_APPLICATION(String status, boolean isApplicationComplete) {
        Respuesta respuesta = new Respuesta();

        respuesta.setStatus(status);
        respuesta.setStatusText(statusTextOk);
        respuesta.setEstadoAplicacion(isApplicationComplete);


        return respuesta;
    }

    public final Respuesta RESPONSE_MEXICAN_STATES(String status, ArrayList<CatalogoVO> mexicanStates) {
        Respuesta respuesta = new Respuesta();

        respuesta.setStatus(status);
        respuesta.setStatusText(statusTextOk);
        respuesta.setEstadosMexicanos(mexicanStates);


        return respuesta;
    }

    public final Respuesta RESPONSE_FORM_OPTIONS(String status, OpcionesFormularioVO opcionesFormularioVO) {
        Respuesta respuesta = new Respuesta();

        respuesta.setStatus(status);
        respuesta.setStatusText(statusTextOk);
        respuesta.setOpcionesFormularioVO(opcionesFormularioVO);


        return respuesta;
    }



    public final Respuesta RESPONSE_REGIONS(String status, ArrayList<RegionVO> regions) {
        Respuesta respuesta = new Respuesta();

        respuesta.setStatus(status);
        respuesta.setStatusText(statusTextOk);
        respuesta.setRegiones(regions);


        return respuesta;
    }

    public final Respuesta RESPONSE_VACANCY_DETAIL(String status, PublicacionVO publicacionVO) {
        Respuesta respuesta = new Respuesta();

        respuesta.setStatus(status);
        respuesta.setStatusText(statusTextOk);
        respuesta.setPublicacionVO(publicacionVO);


        return respuesta;
    }
}

