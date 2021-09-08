package com.mx.profuturo.bolsa.service.colecciones;

import com.mx.profuturo.bolsa.model.service.vacancies.dto.InformacionGeneralVacanteMaestraVO;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.*;
import com.mx.profuturo.bolsa.model.vo.vacancy.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;

@Component
public class ColeccionVacantes {

    private LinkedList<ResumenPostulacionVO> coleccionResumenPublicacion;
    private LinkedList<PublicacionVO> coleccionPublicacion;
    private LinkedList<InformacionGeneralVacanteMaestraVO>coleccionVacantesMaestrasCards;


    public LinkedList<ResumenPostulacionVO> getColeccionResumenPublicacion() {
        return coleccionResumenPublicacion;
    }

    public void setColeccionResumenPublicacion(LinkedList<ResumenPostulacionVO> coleccionResumenPublicacion) {
        this.coleccionResumenPublicacion = coleccionResumenPublicacion;
    }

    public LinkedList<PublicacionVO> getColeccionPublicacion() {
        return coleccionPublicacion;
    }

    public void setColeccionPublicacion(LinkedList<PublicacionVO> coleccionPublicacion) {
        this.coleccionPublicacion = coleccionPublicacion;
    }

    public ResumenPublicacionesVO crearColeccionResumenPublicacionReclutadora(){
        LinkedList<ResumenPublicacionVO> coleccion =  new LinkedList<>();
        ResumenPublicacionesVO data = new ResumenPublicacionesVO();

        ResumenPublicacionVO res1 = new ResumenPublicacionVO();
        ResumenPublicacionVO res2 = new ResumenPublicacionVO();
        ResumenPublicacionVO res3 = new ResumenPublicacionVO();
        ResumenPublicacionVO res4 = new ResumenPublicacionVO();
        ResumenPublicacionVO res5 = new ResumenPublicacionVO();

        res1.setIdPublicacion(1);
        res1.setIdVacante(10);
        res1.setTituloVacante("Desarrollador Android");
        res1.setDiasActiva(20);
        res1.setCandidatos(2);
        res1.setEstatus("Cerrada");

        res2.setIdPublicacion(1);
        res2.setIdVacante(20);
        res2.setTituloVacante("Gerente de Sistemas");
        res2.setDiasActiva(68);
        res2.setCandidatos(2);
        res2.setTituloVacante("Reclutamiento");
        res2.setEstatus("Segundas entrevistas");

        coleccion.add(res1);
        coleccion.add(res2);


        data.setTotalPaginas(2);
        data.setPaginaActual(1);
        data.setResumenPublicaciones(coleccion);

        return data;
    }

    public LinkedList<InformacionGeneralVacanteMaestraVO> crearColleccionCardsVacantesMaestras(){


        LinkedList<InformacionGeneralVacanteMaestraVO> coleccion = new LinkedList<>();

        InformacionGeneralVacanteMaestraVO el1 = new InformacionGeneralVacanteMaestraVO();
        InformacionGeneralVacanteMaestraVO el2 = new InformacionGeneralVacanteMaestraVO();
        InformacionGeneralVacanteMaestraVO el3 = new InformacionGeneralVacanteMaestraVO();
        InformacionGeneralVacanteMaestraVO el4 = new InformacionGeneralVacanteMaestraVO();
        InformacionGeneralVacanteMaestraVO el5 = new InformacionGeneralVacanteMaestraVO();
        InformacionGeneralVacanteMaestraVO el6 = new InformacionGeneralVacanteMaestraVO();

        el1.setIdVacante(1);
        el1.setTituloVacante("Asesor Afore IMSS");
        el1.setTotalPlazasAbiertas(10);
        el1.setTotalProcesos(4);

        el2.setIdVacante(2);
        el2.setTituloVacante("Asesor Afore ISSTE");
        el2.setTotalPlazasAbiertas(10);
        el2.setTotalProcesos(4);

        el3.setIdVacante(3);
        el3.setTituloVacante("Asesor Afore Segmentos Altos");
        el3.setTotalPlazasAbiertas(10);
        el3.setTotalProcesos(4);


        el4.setIdVacante(1);
        el4.setTituloVacante("Asesor Préstamos");
        el4.setTotalPlazasAbiertas(10);
        el4.setTotalProcesos(4);


        el5.setIdVacante(2);
        el5.setTituloVacante("Asesor ");
        el5.setTotalPlazasAbiertas(10);
        el5.setTotalProcesos(4);

       coleccion.add(el1);
        coleccion.add(el2);
        coleccion.add(el3);
        coleccion.add(el4);
        coleccion.add(el5);

        return coleccion;
    }


    public LinkedList<ResumenReclutadorComercialVO> crearColeccionResumenPublicacionReclutadoraComercial(){
        LinkedList<ResumenReclutadorComercialVO> coleccion =  new LinkedList<>();

        ResumenReclutadorComercialVO res1 = new ResumenReclutadorComercialVO();
        ResumenReclutadorComercialVO res2 = new ResumenReclutadorComercialVO();
        ResumenReclutadorComercialVO res3 = new ResumenReclutadorComercialVO();
        ResumenReclutadorComercialVO res4 = new ResumenReclutadorComercialVO();
        ResumenReclutadorComercialVO res5 = new ResumenReclutadorComercialVO();

        res1.setIdPublicacion(1);
        res1.setIdVacante(10);
        res1.setTituloVacante("Gerente Comercial");
        res1.setDiasActiva(20);
        res1.setCandidatos(2);
        res1.setEstatus("Reclutamiento");
        res1.setSede("Joselillo");
        res1.setIdSede(1);

        res2.setIdPublicacion(2);
        res2.setIdVacante(20);
        res2.setTituloVacante("Gerente Comercial");
        res2.setDiasActiva(68);
        res2.setCandidatos(2);
        res2.setTituloVacante("Reclutamiento");
        res2.setEstatus("Segundas entrevistas");
        res2.setSede("Tampico");
        res2.setIdSede(2);

        coleccion.add(res1);
        coleccion.add(res2);

        return coleccion;
    }

    public LinkedList<ResumenVacanteMaestraVO> createResumenVacantesMaestras(){
        LinkedList<ResumenVacanteMaestraVO> resumenVacantesMaestras = new LinkedList<>();
        LinkedList<ResumenPlazaMaestraVO> resumenPlazas = new LinkedList<>();

        ResumenVacanteMaestraVO resumen1 = new ResumenVacanteMaestraVO();

        ResumenPlazaMaestraVO res1 = new ResumenPlazaMaestraVO();
        ResumenPlazaMaestraVO res2 = new ResumenPlazaMaestraVO();
        ResumenPlazaMaestraVO res3 = new ResumenPlazaMaestraVO();
        ResumenPlazaMaestraVO res4 = new ResumenPlazaMaestraVO();
        ResumenPlazaMaestraVO res5 = new ResumenPlazaMaestraVO();


        res1.setIdVacante(70);
        res1.setPlazasAbiertas(5);
        res1.setSede("Mixcoac");




        res2.setIdVacante(70);
        res2.setPlazasAbiertas(5);
        res2.setSede("Salinillas");




        res3.setIdVacante(70);
        res3.setPlazasAbiertas(5);
        res3.setSede("Joselillo");




        res4.setIdVacante(70);
        res4.setPlazasAbiertas(5);

        res4.setSede("San Ángel");




        res5.setIdVacante(70);
        res5.setPlazasAbiertas(5);

        res5.setSede("Corporativo");


        resumenPlazas.add(res1);
        resumenPlazas.add(res2);
        resumenPlazas.add(res3);
        resumenPlazas.add(res4);
        resumenPlazas.add(res5);

        resumen1.setIdVacante(70);
        resumen1.setTituloVacante("Asistente de atención a clientes");
        resumen1.setPublicaciones(resumenPlazas);

        resumenVacantesMaestras.add(resumen1);


        return resumenVacantesMaestras;
    }

    public LinkedList<ResumenVacanteMaestraAnalistaVO> createResumenVacantesAnalista(){
        LinkedList<ResumenPlazaMaestraAnalistaVO> resumen = new LinkedList<>();
        ResumenPlazaMaestraAnalistaVO res1 = new ResumenPlazaMaestraAnalistaVO();
        ResumenPlazaMaestraAnalistaVO res2 = new ResumenPlazaMaestraAnalistaVO();
        ResumenPlazaMaestraAnalistaVO res3 = new ResumenPlazaMaestraAnalistaVO();

        res1.setIdPublicacion(3);
        res1.setPlazasAbiertas(5);
        res1.setPlazasCubiertas(2);
        res1.setIdSede(1);

        res1.setSede("Mixcoac");



        res2.setIdPublicacion(3);
        res2.setPlazasCubiertas(4);
        res2.setPlazasAbiertas(5);
        res2.setIdSede(2);

        res2.setSede("Salinillas");



        res3.setIdPublicacion(3);
        res3.setPlazasAbiertas(5);
        res3.setPlazasCubiertas(1);
        res3.setIdSede(3);

        res3.setSede("Joselillo");

        resumen.add(res1);
        resumen.add(res2);
        resumen.add(res3);

        ResumenVacanteMaestraAnalistaVO resumenVacante = new ResumenVacanteMaestraAnalistaVO();
        resumenVacante.setIdVacante(3);
        resumenVacante.setResumen(resumen);
        resumenVacante.setTituloVacante("Asistente de atención a clientes");


        ResumenVacanteMaestraAnalistaVO resumenVacante2 = new ResumenVacanteMaestraAnalistaVO();
        resumenVacante2.setIdVacante(3);
        resumenVacante2.setResumen(new  LinkedList<ResumenPlazaMaestraAnalistaVO>());
        resumenVacante2.setTituloVacante("Asistente de atención a clientes 2");



        LinkedList<ResumenVacanteMaestraAnalistaVO> resumenesVacantesmaestras = new LinkedList<>();
        resumenesVacantesmaestras.add(resumenVacante);
        resumenesVacantesmaestras.add(resumenVacante2);



        return resumenesVacantesmaestras;

    }


    public LinkedList<ResumenVacanteCorp> createResumenVacantesCorp(){


        LinkedList<ResumenVacanteCorp> resumen = new LinkedList<>();

        ResumenVacanteCorp res1 = new ResumenVacanteCorp();
        ResumenVacanteCorp res2 = new ResumenVacanteCorp();
        ResumenVacanteCorp res3 = new ResumenVacanteCorp();
        ResumenVacanteCorp res4 = new ResumenVacanteCorp();
        ResumenVacanteCorp res5 = new ResumenVacanteCorp();

        res1.setIdPublicacion(1);
        res1.setIdVacante(10);
        res1.setTituloVacante("Especialista Marketing");
        res1.setDiasActiva(20);
        res1.setReclutadora("Erika Fuentes");
        res1.setEstatus("Reclutamiento");

        res2.setIdPublicacion(1);
        res2.setIdVacante(10);
        res2.setTituloVacante("Desarrollador Android");
        res2.setDiasActiva(20);
        res2.setReclutadora("Alba Gómez");
        res2.setEstatus("Reclutamiento");


        res3.setIdPublicacion(1);
        res3.setIdVacante(10);
        res3.setTituloVacante("Gerente de Sistemas");
        res3.setDiasActiva(20);
        res3.setReclutadora("Daniela Almaguer");
        res3.setEstatus("Reclutamiento");


        res4.setIdPublicacion(1);
        res4.setIdVacante(10);
        res4.setTituloVacante("Actuario");
        res4.setDiasActiva(20);
        res4.setReclutadora("Alejanda López");
        res4.setEstatus("Reclutamiento");


        res5.setIdPublicacion(1);
        res5.setIdVacante(10);
        res5.setTituloVacante("Administrador de Base de Datos");
        res5.setDiasActiva(20);
        res5.setReclutadora("Erika Fuentes");
        res5.setEstatus("Reclutamiento");

        resumen.add(res1);
        resumen.add(res2);
        resumen.add(res3);
        resumen.add(res4);
        resumen.add(res5);

        return resumen;


    }
}

