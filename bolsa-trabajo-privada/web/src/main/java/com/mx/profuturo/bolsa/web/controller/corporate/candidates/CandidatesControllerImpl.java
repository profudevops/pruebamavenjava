package com.mx.profuturo.bolsa.web.controller.corporate.candidates;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.mx.profuturo.bolsa.configuration.TokenService;
import com.mx.profuturo.bolsa.model.reports.vo.ReportVO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.*;
import com.mx.profuturo.bolsa.model.vo.Candidato.*;
import com.mx.profuturo.bolsa.model.vo.vacancy.VacanteListaVacanteVO;
import com.mx.profuturo.bolsa.service.reports.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.service.candidates.CandidateService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;

import javax.servlet.http.HttpServletResponse;


@RequestMapping(value = "candidatos")
@Controller("controllerCandidates")
@Scope("request")
@CrossOrigin
public class CandidatesControllerImpl extends BaseController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ExcelService excelService;


    public @ResponseBody  //todo:parametros de paginacion
    @RequestMapping(value = "obtener-candidatos-en-reclutamiento", method = RequestMethod.GET)
    EntityResponseBean<ResumenCandidatosListVO> getInitCandidates(@RequestHeader(value = "Authorization") String token,
                                                                  @RequestParam(value = "paginaActual") int paginaActual,
                                                                  @RequestParam(value = "search", required = false) String search) throws GenericStatusException {


        UserCredentialsVO user = tokenService.getUserFromToken(this.getCleanToken(token));
        Integer idRama = tokenService.getBranchId(this.getCleanToken(token));
        ResumenCandidatosListVO responseService = candidateService.getInitCandidates(user, paginaActual, search, idRama);
        //if(responseService.getTotalPaginas() == 0) {

        //}
        return this.buildEntitySuccessResponse(responseService);

    }


    public @ResponseBody
    @RequestMapping(value = "detalle-candidato", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    EntityResponseBean<CandidatoDetallesVO> getDetailsCandidate(@RequestParam(value = "idCandidato") int idCandidato) throws GenericStatusException {
        CandidatoDetallesVO detailsCandidate = new CandidatoDetallesVO();

        detailsCandidate = candidateService.getCandidateDetails(idCandidato);

        return this.buildEntitySuccessResponse(detailsCandidate);
    }


    public @ResponseBody
    @RequestMapping(value = "calificar-candidato", method = RequestMethod.POST)
    EntityResponseBean<ScoreVO> setScoreCandidate(@RequestBody SetScoreCandidateInDTO calificacion) throws GenericStatusException {

        calificacion.setIdCandidato(calificacion.getId());
        calificacion.setIdCalificacion(calificacion.getCalificacion());

        ScoreVO response = candidateService.setScoreCandidate(calificacion);

        return this.buildEntitySuccessResponse(response);
    }


    public @ResponseBody
    @RequestMapping(value = "asignar-vacante", method = RequestMethod.POST)
    BaseResponseBean setVacantCandidate(
            @RequestHeader("Authorization") String token,
            @RequestBody GenericIdCandidateInDTO datos) throws GenericStatusException {

        datos.setIdUsuario(tokenService.getUserIdFromToken(this.getCleanToken(token)));
        boolean responseService = candidateService.setVacantCandidate(datos);


        return this.buildSuccessResponse();
    }

    public @ResponseBody
    @RequestMapping(value = "recomendar-vacante", method = RequestMethod.POST)
    BaseResponseBean recommendCandidate(
            @RequestHeader(value = "Authorization") String token
            , @RequestBody GenericIdCandidateInDTO datos) throws GenericStatusException {

        datos.setIdUsuario(tokenService.getUserIdFromToken(this.getCleanToken(token)));
        candidateService.recommendCandidate(datos);

        return this.buildSuccessResponse();

    }


    public @ResponseBody
    @RequestMapping(value = "obtener-calificaciones-tags", method = RequestMethod.GET)
    EntityResponseBean<LinkedList<ScoreVO>> getScoreTags() throws GenericStatusException {

        LinkedList<ScoreVO> vo = new LinkedList<ScoreVO>();

        vo = candidateService.getScoreTags();

        return this.buildEntitySuccessResponse(vo);

    }


    public @ResponseBody
    @RequestMapping(value = "obtener-historial-candidato", method = RequestMethod.GET)
    EntityResponseBean<RecruitmentHistoryVO> getHistyory(@RequestParam(value = "idCandidato") int idCandidato) throws GenericStatusException {


        RecruitmentHistoryVO candidateHistory = new RecruitmentHistoryVO();

        candidateHistory = candidateService.getCandidateHistory(idCandidato);

        return this.buildEntitySuccessResponse(candidateHistory);
    }


    public @ResponseBody
    @RequestMapping(value = "editar-correo", method = RequestMethod.POST)
    BaseResponseBean editEmail(@RequestBody CandidateEditEmailRequestBean request) throws GenericStatusException {
		candidateService.editEmail(request);
		return this.buildSuccessResponse();
    }


    @ResponseBody
    @RequestMapping(value = "descarga-informacion", method = RequestMethod.POST)
    public BaseResponseBean downloadCandidateInformation(@RequestBody CandidateInfoRequestBean request) throws GenericStatusException {
        boolean responseService = candidateService.getCandidateInformation(request);
        if (responseService) {
			return this.buildSuccessResponse();
		}
        throw new GenericStatusException();
//        LinkedList<CandidateCorpInfo> listCorp = new LinkedList<>();
//        LinkedList<CandidateComInfo> listCom = new LinkedList<>();
//        ReportVO report = new ReportVO();

//        if(data!= null) {
//            if (request.getModo().equals("CORP")) {
//                for(CandidateCorpInfo e: data.getResultadoscorp()) {
//                    listCorp.add(e);
//                }
//                report.setReportData(listCorp);
//            }else {
//                for(CandidateComInfo e: data.getResultadoscom()) {
//                    listCom.add(e);
//                }
//                report.setReportData(listCom);
//


//        report.setTitle("Informacion Candidatos");
//        report.setCode("CANINFO");
//        report.setBranch(request.getModo());
//        report.setTipoVacante(0);

//        this.excelService.downloadXLSXFile(response, report);

    }


    //TODO: Extraer datos token
    public @ResponseBody
    @RequestMapping(value = "obtener-lista-simple-mis-vacantes", method = RequestMethod.GET)
    EntityResponseBean<List<VacanteListaVacanteVO>> getMyVacanciesSimpleList(
            @RequestHeader(value = "Authorization") String token) throws GenericStatusException {

        List<VacanteListaVacanteVO> vo;
        String idUsuario = tokenService.getUserIdFromToken(this.getCleanToken(token));
        vo = candidateService.getSimpleListMyVacancy(idUsuario);

        return this.buildEntitySuccessResponse(vo);

    }


    public @ResponseBody
    @RequestMapping(value = "obtener-lista-simple-otras-vacantes", method = RequestMethod.GET)
    EntityResponseBean<List<VacanteListaVacanteVO>> getOtherVacanciesSimpleList(
            @RequestHeader("Authorization") String token) throws GenericStatusException {

        List<VacanteListaVacanteVO> vo;
        String idUsuario = tokenService.getUserIdFromToken(this.getCleanToken(token));
        vo = candidateService.simpleListOtherVacancy(idUsuario);

        return this.buildEntitySuccessResponse(vo);

    }


    //enviar-a-cartera
    public @ResponseBody
    @RequestMapping(value = "enviar-cartera", method = RequestMethod.POST)
    BaseResponseBean sendToPortofolio(@RequestBody SendWalletRequestBean idCandidato) throws GenericStatusException {

        candidateService.sendToPortofolio(idCandidato);
        return this.buildSuccessResponse();
    }

}
