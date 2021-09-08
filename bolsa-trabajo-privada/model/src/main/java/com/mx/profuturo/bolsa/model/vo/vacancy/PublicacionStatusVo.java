package com.mx.profuturo.bolsa.model.vo.vacancy;

import com.mx.profuturo.bolsa.model.vo.Candidato.CandidatoResumenVO;

import java.util.List;

public class PublicacionStatusVo {
    private Integer idVacante;
    private Integer idPublicacion;
    private String nombreVacante;
    private Integer numeroPostulados;
    private Integer numeroReclutamientos;
    private List<ResumenReclutamientoVO> reclutamientos;
    private List<ResumenPostulacionVO> postulados;
    private List<CandidatoResumenVO> sugerencias;
}
