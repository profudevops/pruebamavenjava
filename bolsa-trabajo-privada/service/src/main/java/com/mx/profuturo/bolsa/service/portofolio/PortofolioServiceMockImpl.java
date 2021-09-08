package com.mx.profuturo.bolsa.service.portofolio;

import com.mx.profuturo.bolsa.model.wallet.dto.WalletSearchDTO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletResultVO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletReultListVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Scope("request")
@Profile("mock")
public class PortofolioServiceMockImpl extends PortofolioServiceBase {

    public LinkedList<WalletResultVO> candidatos;

    @Override
    public WalletReultListVO __callFindCandidates(WalletSearchDTO filtro) throws GenericStatusException{
        WalletReultListVO resultados = new WalletReultListVO();
        if(filtro.getModo().equals("CORP")){
            resultados = this.filterCorp(filtro);
        }else if(filtro.getModo().equals("COM")){
            resultados = this.filterCom(filtro);
        }

    return resultados;

    }


    public void getCandidates(){

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
        vo.add(r3);
        vo.add(r4);
        vo.add(r5);
        vo.add(r6);


        setCandidatos(vo);
    }


    public void getComCandidates(){

        LinkedList<WalletResultVO> vo = new LinkedList<WalletResultVO>();
        WalletResultVO r1 = new WalletResultVO();
        WalletResultVO r2 = new WalletResultVO();
        WalletResultVO r3 = new WalletResultVO();
        WalletResultVO r4 = new WalletResultVO();



        r1.setNombre("Ricardo Llamas Moctezuma");
        r1.setCalificacion("Talento");
        r1.setIdCalificacion(1);
        r1.setIdCandidato(1);
        r1.setEdad(33);
        r1.setEscolaridad("Bachillerato");
        r1.setIdEscolaridad(1);
        r1.setUbicacion("Estado de México");
        r1.setIdUbicacion(2);


        r2.setNombre("Julio Arredondo");
        r2.setCalificacion("Talento");
        r2.setIdCalificacion(1);
        r2.setIdCandidato(1);
        r2.setEdad(33);
        r2.setEscolaridad("Licenciatura");
        r2.setIdEscolaridad(2);
        r2.setUbicacion("Ciudad de México");
        r2.setIdUbicacion(1);

        r3.setNombre("Erik Almaguer");
        r3.setCalificacion("Lista Negra");
        r3.setIdCalificacion(2);
        r3.setIdCandidato(1);
        r3.setEdad(33);
        r3.setEscolaridad("Licenciatura");
        r3.setIdEscolaridad(2);
        r3.setUbicacion("Ciudad de México");
        r3.setIdUbicacion(1);

        r4.setNombre("Juan Arnulfo Reyes");
        r4.setCalificacion("Talento");
        r4.setIdCalificacion(1);
        r4.setIdCandidato(1);
        r4.setEdad(27);
        r4.setEscolaridad("Licenciatura");
        r4.setIdEscolaridad(2);
        r4.setUbicacion("Estado de Ḿéxico");
        r4.setIdUbicacion(2);

        vo.add(r1);
        vo.add(r2);
        vo.add(r3);
        vo.add(r4);

        setCandidatos(vo);

    }

    public WalletReultListVO  filterCorp(WalletSearchDTO filtro){

        this.getCandidates();
        WalletReultListVO resultados = new WalletReultListVO();
        LinkedList<WalletResultVO> candidatosResult = this.candidatos;
        ArrayList<Integer> aBorrar = new ArrayList<>();
        Set<Integer> set = new LinkedHashSet<>();

        resultados.setPaginaActual(1);
        resultados.setTotalPaginas(1);


        if(filtro.getAreaInteres()==null &&
                filtro.getSubAreaInteres()==null &&
                filtro.getCalificacion()==null&&
                filtro.getTiempoEnCartera()==null){
            resultados.setResultados(this.getCandidatos());
        }

        if(filtro.getAreaInteres()!=null){
            for(int opc: filtro.getAreaInteres()){
                int i = 0;
                for(WalletResultVO candidato :candidatos){
                    if(opc != candidato.getIdAreaInteres()){
                        aBorrar.add(i);
                    }
                    i++;
                }

            }
        }

        if(filtro.getSubAreaInteres()!=null){
            for(int opc: filtro.getSubAreaInteres()){
                int i = 0;
                for(WalletResultVO candidato :candidatos){
                    if(opc != candidato.getIdSubAreaInteres()){
                        aBorrar.add(i);
                    }
                    i++;
                }

            }
        }

        if(filtro.getCalificacion()!=null){
            for(int opc: filtro.getCalificacion()){
                int i = 0;
                for(WalletResultVO candidato :candidatos){
                    if(opc != candidato.getIdCalificacion()){
                        aBorrar.add(i);
                    }
                    i++;
                }

            }
        }

        if(filtro.getTiempoEnCartera()!=null){
            for(int opc: filtro.getTiempoEnCartera()){
                int i = 0;
                for(WalletResultVO candidato :candidatos){
                    if(opc != candidato.getIdTiempoCartera()){
                        aBorrar.add(i);
                    }
                    i++;
                }

            }
        }




        set.addAll(aBorrar);
        aBorrar.clear();
        aBorrar.addAll(set);
        Collections.sort(aBorrar,Collections.reverseOrder());
        System.out.println(aBorrar);

        if(aBorrar.size()>0){
            resultados.setResultados(null);
            for(int elemento: aBorrar) {
                candidatosResult.remove(elemento);
            }
            resultados.setResultados(candidatosResult);
        }

        return resultados;

    }



    public WalletReultListVO  filterCom(WalletSearchDTO filtro){

        this.getComCandidates();
        WalletReultListVO resultados = new WalletReultListVO();
        LinkedList<WalletResultVO> candidatosResult = this.candidatos;
        ArrayList<Integer> aBorrar = new ArrayList<>();
        Set<Integer> set = new LinkedHashSet<>();

        resultados.setPaginaActual(1);
        resultados.setTotalPaginas(1);


        if(filtro.getEdad()==null &&
                filtro.getUbicacion()==null &&
                filtro.getCalificacion()==null&&
                filtro.getEscolaridad()==null){
            resultados.setResultados(this.getCandidatos());
        }

        if(filtro.getEdad()!=null){
            for(int opc: filtro.getEdad()){
                int i = 0;
                for(WalletResultVO candidato :candidatos){
                    if(opc != candidato.getEdad()){
                        aBorrar.add(i);
                    }
                    i++;
                }

            }
        }

        if(filtro.getEscolaridad()!=null){
            for(int opc: filtro.getEscolaridad()){
                int i = 0;
                for(WalletResultVO candidato :candidatos){
                    if(opc != candidato.getIdEscolaridad()){
                        aBorrar.add(i);
                    }
                    i++;
                }

            }
        }

        if(filtro.getCalificacion()!=null){
            for(int opc: filtro.getCalificacion()){
                int i = 0;
                for(WalletResultVO candidato :candidatos){
                    if(opc != candidato.getIdCalificacion()){
                        aBorrar.add(i);
                    }
                    i++;
                }

            }
        }

        if(filtro.getUbicacion()!=null){
            for(int opc: filtro.getUbicacion()){
                int i = 0;
                for(WalletResultVO candidato :candidatos){
                    if(opc != candidato.getIdUbicacion()){
                        aBorrar.add(i);
                    }
                    i++;
                }

            }
        }




        set.addAll(aBorrar);
        aBorrar.clear();
        aBorrar.addAll(set);
        Collections.sort(aBorrar,Collections.reverseOrder());
        System.out.println(aBorrar);

        if(aBorrar.size()>0){
            resultados.setResultados(null);
            for(int elemento: aBorrar) {
                candidatosResult.remove(elemento);
            }
            resultados.setResultados(candidatosResult);
        }

        return resultados;

    }



    public LinkedList<WalletResultVO> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(LinkedList<WalletResultVO> candidatos) {
        this.candidatos = candidatos;
    }
}
