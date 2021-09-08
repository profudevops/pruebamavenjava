package com.mx.profuturo.bolsa.model.reports.templates;

import com.mx.profuturo.bolsa.model.service.candidates.dto.CandidateComInfo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class CandidateComInformationTemplate extends CandidateInfoBaseTemplate implements XLSTemplate {


    public CandidateComInformationTemplate(String branch) {
        this.mode = branch;
    }


    //fecha	candidato	motivo	etapa	analista	observador/gerente	gerencia	regional	division

    @Override
    public String getFileName() {
        return "candidate-information.xlsx";
    }


    @Override
    protected Row getXLSCORPHeaders(Row row, Sheet s, Integer p) {
        return getXLSCOMHeaders(row, s, p);
    }

    @Override
    protected Row getXLSCORPRowMapping(Row row, Sheet s, Object o, Integer p) {
        return getXLSCOMRowMapping(row, s, o, p);
    }


    @Override
    protected Row getXLSCOMHeaders(Row row, Sheet s, Integer p) {
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Fecha Postulacion");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Postulado/Cartera");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Vacante donde aplicó");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Área de interes");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("SubÁrea de interes");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Nombre completo");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Fuente");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Residencia");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Municipio");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Celular");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Correo");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Curp");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Rfc");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Género");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Edad");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Escolaridad");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Especialidad");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Estado Civil");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Divisional");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Regional");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Gerencia Asignada");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Resultado Filtro Telefonico");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Motivo descartado");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Tipo de Entrevista Reclutamiento");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Fecha entrevista Reclutamiento");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Horario Entrevista Relcutamiento");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Resultado entrevista reclutamiento");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Motivo descartado");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Comentarios");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("D");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("I");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("S");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("C");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("AMITAI");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Nombre de Entrevistador");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Tipo de Entrevista Observador");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Fecha entrevista observador");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Horario Entrevista observador");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Resultado Entrevista Observador");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Motivo descartado");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Comentarios");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Analista responsable");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Acepta Oferta");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Motivo NO acepta");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Comentarios");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Solicitud de empleo");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Socioeconómico");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Contratado");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Motivo NO contratado");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Comentarios");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Repostulado");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Empresa");
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Puesto");
        // s.autoSizeColumn(p);



        return row;
    }

    @Override
    protected Row getXLSCOMRowMapping(Row row, Sheet s, Object o, Integer p) {
        CandidateComInfo vo = (CandidateComInfo)o;

        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getFechaPostulacion());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getPostuladoOcartera());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getVacanteAplicada());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getAreaInteres());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getSubAreaInteres());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getNombreCompleto());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getFuente());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getResidencia());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getMunicipio());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getCelular());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getCorreo());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getCurp());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getRfc());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getGenero());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getEdad());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getEscolaridad());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getEspecialidad());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getEstadoCivil());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getDivisional());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getRegional());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getGerencia());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getFiltroTelefonico());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getDescarteTelefonico());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getTipoPrimerEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getFechaPrimerEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getHoraPrimerEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getResultadoPrimerEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getMotivoDescartePrimerEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getComentariosPrimerEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getD());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getI());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getS());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getC());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getAmitai());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getNombreEntrevistador());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getTipoSegundaEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getFechaSegundaEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getHoraSegundaEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getResultadoSegundaEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getMotivoDescarteSegundaEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getComentariosSegundaEntrevista());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getResponsableVacante());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getAceptaOferta());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getMotivoNoAceptaOferta());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getComentariosOferta());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getSolicitudEmpleo());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getSocioEconomico());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getContratado());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getMotivoNoContratado());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getComentarios());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getRespostulado());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getEmpresaAnterior());
        // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getPuestoAnterior());
        // s.autoSizeColumn(p);


        return row;
    }
}
