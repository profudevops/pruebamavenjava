package com.mx.profuturo.bolsa.service.cartera;

import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.AreaInteresDTO;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.SubAreaInteresDTO;
import com.mx.profuturo.bolsa.model.service.cartera.*;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.wallet.dto.WalletSearchDTO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletFiltersVO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletResultVO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletReultListVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Scope("request")
@Profile({"mock"})
public class CarteraServiceMockImpl extends CarteraServiceBase{

    @Override
    protected WalletFiltersVO callLoadFilters(RequestBean<LoadFiltersRequest> genericRequestBean){

        WalletFiltersVO vo = new WalletFiltersVO();
        Map<String, ?> map = genericRequestBean.getUriVariables();
        String modo = (String) map.get("modo");
        if(modo.equals("CORP")){
            ArrayList<CatalogoVO> areasInteres = new ArrayList<CatalogoVO>();
            CatalogoVO ai1 = new CatalogoVO(1, "Sistemas");
            ai1.setDescripcion("Sistemas");
            CatalogoVO ai2 = new CatalogoVO(2, "Recursos Humanos");
            areasInteres.add(ai1);
            areasInteres.add(ai2);
            vo.setAreasInteres(areasInteres);

            ArrayList<CatalogoVO> calificaciones = new ArrayList<CatalogoVO>();
            CatalogoVO cl1 = new CatalogoVO(1, "Talento");
            CatalogoVO cl2 = new CatalogoVO(2, "Lista Negra");
            calificaciones.add(cl1);
            calificaciones.add(cl2);
            vo.setCalificaciones(calificaciones);

            LinkedList<CatalogoVO> subAreasInteres = new LinkedList<CatalogoVO>();
            CatalogoVO sai1 = new CatalogoVO(1, "Programación");
            CatalogoVO sai2 = new CatalogoVO(2, "Gobierno TI");
            subAreasInteres.add(sai1);
            subAreasInteres.add(sai2);

            ArrayList<CatalogoVO> tiempoEnCartera = new ArrayList<CatalogoVO>();
            CatalogoVO r1 = new CatalogoVO(1,"menos de 3 meses");
            CatalogoVO r2 = new CatalogoVO(2,"3 a 6 meses");
            CatalogoVO r3 = new CatalogoVO(3,"6 meses a un año");
            CatalogoVO r4 = new CatalogoVO(4,"más de un año");
            tiempoEnCartera.add(r1);
            tiempoEnCartera.add(r2);
            tiempoEnCartera.add(r3);
            tiempoEnCartera.add(r4);


            vo.setTiempoEnCartera(tiempoEnCartera);

            vo.setModo("CORP");}else

        if(modo.equals("COM")){


            ArrayList<CatalogoVO> calificaciones = new ArrayList<CatalogoVO>();
            CatalogoVO cl1 = new CatalogoVO(1, "Talento");
            CatalogoVO cl2 = new CatalogoVO(2, "Lista Negra");
            calificaciones.add(cl1);
            calificaciones.add(cl2);
            vo.setCalificaciones(calificaciones);

            ArrayList<CatalogoVO> edad = new ArrayList<>();
            CatalogoVO edad1 = new CatalogoVO();
            CatalogoVO edad2 = new CatalogoVO();
            edad1.setDescripcion("20 a 25 años");
            edad1.setCodigo("1");
            edad2.setDescripcion("26 a 30 años");
            edad2.setCodigo("2");
            edad.add(edad1);
            edad.add(edad2);
            vo.setEdad(edad);

            ArrayList<CatalogoVO> escolaridad = new ArrayList<CatalogoVO>();
            CatalogoVO esc1 = new CatalogoVO(1, "Bachillerato");
            CatalogoVO esc2 = new CatalogoVO(2, "Licenciatura");
            CatalogoVO esc3 = new CatalogoVO(3, "Posgrado");
            escolaridad.add(esc1);
            escolaridad.add(esc2);
            escolaridad.add(esc3);
            vo.setEscolaridad(escolaridad);

            ArrayList<CatalogoVO> ubicacion = new ArrayList<>();
            CatalogoVO ub1 = new CatalogoVO(1,"Ciudad de México");
            CatalogoVO ub2 = new CatalogoVO(2, "Estado de México");
            ubicacion.add(ub1);
            ubicacion.add(ub2);
            vo.setUbicacion(ubicacion);

            vo.setModo("COM");

        }
        if(modo.equals("ATN")){
            ArrayList<CatalogoVO> calificaciones = new ArrayList<CatalogoVO>();
            CatalogoVO cl1 = new CatalogoVO(1, "Talento");
            CatalogoVO cl2 = new CatalogoVO(2, "Lista Negra");
            calificaciones.add(cl1);
            calificaciones.add(cl2);
            vo.setCalificaciones(calificaciones);

            ArrayList<CatalogoVO> edad = new ArrayList<>();
            CatalogoVO edad1 = new CatalogoVO();
            CatalogoVO edad2 = new CatalogoVO();
            edad1.setDescripcion("20 a 25 años");
            edad1.setCodigo("1");
            edad2.setDescripcion("26 a 30 años");
            edad2.setCodigo("2");
            edad.add(edad1);
            edad.add(edad2);
            vo.setEdad(edad);

            ArrayList<CatalogoVO> escolaridad = new ArrayList<CatalogoVO>();
            CatalogoVO esc1 = new CatalogoVO(1, "Bachillerato");
            CatalogoVO esc2 = new CatalogoVO(2, "Licenciatura");
            CatalogoVO esc3 = new CatalogoVO(3, "Posgrado");
            escolaridad.add(esc1);
            escolaridad.add(esc2);
            escolaridad.add(esc3);
            vo.setEscolaridad(escolaridad);

            ArrayList<CatalogoVO> ubicacion = new ArrayList<>();
            CatalogoVO ub1 = new CatalogoVO(1,"Ciudad de México");
            CatalogoVO ub2 = new CatalogoVO(2, "Estado de México");
            ubicacion.add(ub1);
            ubicacion.add(ub2);
            vo.setUbicacion(ubicacion);

            vo.setModo("COM");

        }

        return vo;

    }

    @Override
    protected ArrayList<AreaInteresDTO> callGetSubInterestArea(RequestBean<GetSubInterestAreaRequest> genericRequestBean)throws GenericStatusException{
        ArrayList<AreaInteresDTO> areasList = new ArrayList<>();
        ArrayList<Integer> areaFilter = genericRequestBean.getPayload().getIds();

        ArrayList<SubAreaInteresDTO> listsub = new ArrayList<SubAreaInteresDTO>();
        SubAreaInteresDTO sai = new SubAreaInteresDTO();
        SubAreaInteresDTO sai1 = new SubAreaInteresDTO();
        SubAreaInteresDTO sai2 = new SubAreaInteresDTO();

        sai.setIdArea(1);
        sai.setDescripcion("descripcion");
        sai.setIdSubArea(1);
        sai.setNombre("Desarrollo");
        listsub.add(sai);

        sai1.setIdArea(1);
        sai1.setDescripcion("descripcion");
        sai1.setIdSubArea(2);
        sai1.setNombre("Arquitectura");
        listsub.add(sai1);

        sai2.setIdArea(1);
        sai2.setDescripcion("descripcion");
        sai2.setIdSubArea(3);
        sai2.setNombre("Seguridad");
        listsub.add(sai2);

        AreaInteresDTO ai = new AreaInteresDTO();
        ai.setDescripcion("Área de sistemas");
        ai.setIdArea(1);
        ai.setNombre("Sistemas");
        ai.setSubareas(listsub);



        ArrayList<SubAreaInteresDTO> listsub2 = new ArrayList<SubAreaInteresDTO>();
        SubAreaInteresDTO sa2i = new SubAreaInteresDTO();
        SubAreaInteresDTO sa2i1 = new SubAreaInteresDTO();
        SubAreaInteresDTO sa2i2 = new SubAreaInteresDTO();

        sa2i.setIdArea(2);
        sa2i.setDescripcion("descripcion");
        sa2i.setIdSubArea(21);
        sa2i.setNombre("Reclutamiento");
        listsub2.add(sa2i);

        sa2i1.setIdArea(2);
        sa2i1.setDescripcion("descripcion");
        sa2i1.setIdSubArea(22);
        sa2i1.setNombre("Relaciones laborales");
        listsub2.add(sa2i1);


        AreaInteresDTO ai2 = new AreaInteresDTO();
        ai2.setDescripcion("Área de Recursos Humanos");
        ai2.setIdArea(2);
        ai2.setNombre("Recursos Humanos");
        ai2.setSubareas(listsub2);

        for(int idx:areaFilter){
            if(idx==1){
                areasList.add(ai);
            }
            if(idx==2){
                areasList.add(ai2);
            }

        }

        return areasList;
    }

	@Override
	protected WalletReultListVO callFindCandidates(RequestBean<WalletSearchDTO> filtros) throws GenericStatusException {
		 WalletReultListVO result = new WalletReultListVO();
	        LinkedList<WalletResultVO> vo = new LinkedList<WalletResultVO>();
	        WalletResultVO r1 = new WalletResultVO();
	        WalletResultVO r2 = new WalletResultVO();
	        WalletResultVO r3 = new WalletResultVO();
	        WalletResultVO r4 = new WalletResultVO();
	        WalletResultVO r5 = new WalletResultVO();
	        WalletResultVO r6 = new WalletResultVO();

	        r1.setAreaInteres("Sistemas");
	        r1.setIdAreaInteres(1);
	        r1.setSubAreaInteres("Desarrollo");
	        r1.setIdSubAreaInteres(1);
	        r1.setCalificacion("Talento");
	        r1.setIdCalificacion(1);
	        r1.setIdCandidato(1);
	        r1.setNombre("Julio Llamas Moctezuma");
	        r1.setTiempoEnCartera("1 mes");
	        r1.setIdTiempoCartera(1);


	        r2.setAreaInteres("Sistemas");
	        r2.setSubAreaInteres("Desarrollo");
	        r2.setCalificacion("Lista negra");
	        r2.setIdCandidato(1);
	        r2.setNombre("Laura Roldán Gómez");
	        r2.setTiempoEnCartera("1 \n" +
	                "        r2.setIdSubAreaInteres(1);mes");
	        r2.setIdAreaInteres(1);
	        r2.setIdCalificacion(2);
	        r2.setIdTiempoCartera(1);

	        r3.setAreaInteres("Recursos Humanos");
	        r3.setSubAreaInteres("Recluamiento");
	        r3.setCalificacion("Talento");
	        r3.setIdCandidato(1);
	        r3.setNombre("Alicia Lara Rivera");
	        r3.setTiempoEnCartera("1 mes");
	        r3.setIdAreaInteres(2);
	        r3.setIdSubAreaInteres(21);
	        r3.setIdCalificacion(1);
	        r3.setIdTiempoCartera(1);

	        r4.setAreaInteres("Recursos Humanos");
	        r4.setSubAreaInteres("Relaciones laborales");
	        r4.setCalificacion("Lista negra");
	        r4.setIdCandidato(1);
	        r4.setNombre("Eden Reyes Villaseñor");
	        r4.setTiempoEnCartera("1 mes");
	        r4.setIdAreaInteres(2);
	        r4.setIdSubAreaInteres(22);
	        r4.setIdCalificacion(2);
	        r4.setIdTiempoCartera(1);

	        r5.setAreaInteres("Sistemas");
	        r5.setSubAreaInteres("Arquitectura");
	        r5.setCalificacion("Lista negra");
	        r5.setIdCandidato(1);
	        r5.setNombre("Daniela Fuentes Perez");
	        r5.setTiempoEnCartera("1 mes");
	        r5.setIdAreaInteres(1);
	        r5.setIdSubAreaInteres(2);
	        r5.setIdCalificacion(2);
	        r5.setIdTiempoCartera(1);

	        r6.setAreaInteres("Recursos Humanos");
	        r6.setSubAreaInteres("Relaciones laborales");
	        r6.setCalificacion("Talento");
	        r6.setIdCandidato(1);
	        r6.setNombre("Edgar Nuñez Méndez");
	        r6.setTiempoEnCartera("1 mes");
	        r6.setIdAreaInteres(2);
	        r6.setIdSubAreaInteres(22);
	        r6.setIdCalificacion(1);
	        r6.setIdTiempoCartera(1);

	        /*r3.setNombre("Erik Almaguer Fuentes");
	        r3.setCalificacion("Talento");
	        r3.setIdCandidato(3);
	        r3.setEdad(33);
	        r3.setEscolaridad("Licenciatura");
	        r3.setUbicacion("Ciudad de México");

	        r4.setNombre("Juan Arnulfo Reyes Loredo");
	        r4.setCalificacion("Talento");
	        r4.setIdCandidato(4);
	        r4.setEdad(27);
	        r4.setEscolaridad("Licenciatura");
	        r4.setUbicacion("Guadalajara");*/

	        vo.add(r1);
	        vo.add(r2);
	        /*vo.add(r3);
	        vo.add(r4);
	        vo.add(r5);
	        vo.add(r6);*/
	        result.setResultados(vo);

	        return result;
	}

}
