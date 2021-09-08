package com.mx.profuturo.bolsa.web.controller.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.profuturo.bolsa.model.graphics.adapters.AreaChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.BarChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.DonutChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.StackedChartAdapter;
import com.mx.profuturo.bolsa.model.reports.dto.FilterChainingDTO;
import com.mx.profuturo.bolsa.model.reports.dto.QueryDTO;
import com.mx.profuturo.bolsa.model.reports.response.FinalistHiredResponse;
import com.mx.profuturo.bolsa.model.reports.response.HiringsResponse;
import com.mx.profuturo.bolsa.model.reports.response.InterviewTypeResponse;
import com.mx.profuturo.bolsa.model.reports.response.RecruitmentSourcesResponse;
import com.mx.profuturo.bolsa.model.reports.response.RejectReasonsResponse;
import com.mx.profuturo.bolsa.model.reports.vo.FilterVO;
import com.mx.profuturo.bolsa.model.reports.vo.GraphicVO;
import com.mx.profuturo.bolsa.model.reports.vo.ReportVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesAnalistaVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesMesVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesNivelPuestoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.FinalistasContratadosVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.FuentesReclutamientoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.TiposEntrevistaVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.common.SCatalogoVO;
import com.mx.profuturo.bolsa.security.GrantingUserSettings;
import com.mx.profuturo.bolsa.service.charts.ChartAdapterService;
import com.mx.profuturo.bolsa.service.reports.ExcelService;
import com.mx.profuturo.bolsa.service.reports.FilterService;
import com.mx.profuturo.bolsa.service.reports.ReportDataService;
import com.mx.profuturo.bolsa.service.reports.ReportToChartService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

@Controller("reportsFakeControl")
@RequestMapping(value="metricas")
@CrossOrigin
@Scope("request")
public class ReportsBackFakeControllerImpl{
	
	@Autowired
	private ReportDataService reportDataService;
	
	@Autowired
	private FilterService filterService;
	
	
	private RequestBean<QueryDTO> generateRequest(QueryDTO dto){
		RequestBean<QueryDTO> rq = new RequestBean<QueryDTO>();
		rq.setEntityPayload(dto);
		return rq ;
	}
	
	
	@ResponseBody
    @RequestMapping(value="/motivos-rechazo", method = RequestMethod.POST)
	public ArrayList<RejectReasonsResponse> __getRejectReasonsServiceData(@RequestBody QueryDTO dto) throws GenericStatusException {
		// TODO Auto-generated method stub
		return reportDataService.__getRejectReasonsServiceData(this.generateRequest(dto));
	}

	@ResponseBody
    @RequestMapping(value="/motivos-abandono", method = RequestMethod.POST)
	public ArrayList<RejectReasonsResponse> __getAbandonmentReasonServiceData(@RequestBody QueryDTO dto) throws GenericStatusException {
		// TODO Auto-generated method stub
		return reportDataService.__getAbandonmentReasonServiceData(this.generateRequest(dto));
	}

	@ResponseBody
    @RequestMapping(value="/finalistas-contratados", method = RequestMethod.POST)
	public ArrayList<FinalistHiredResponse> __getFinalistVsHiredServiceData( @RequestBody QueryDTO dto) throws GenericStatusException{
		// TODO Auto-generated method stub
		return reportDataService.__getFinalistVsHiredServiceData(this.generateRequest(dto));
	}

	
	@ResponseBody
    @RequestMapping(value="/nivel-puesto", method = RequestMethod.POST)
	public ArrayList<HiringsResponse> __getPositionLevelServiceData(@RequestBody QueryDTO dto) throws GenericStatusException{
		// TODO Auto-generated method stub
		return reportDataService.__getPositionLevelServiceData(this.generateRequest(dto));
	}

	@ResponseBody
    @RequestMapping(value="/contrataciones-mes", method = RequestMethod.POST)
	public ArrayList<HiringsResponse> __getHiringsPerMonthServiceData(QueryDTO dto) throws GenericStatusException{
		// TODO Auto-generated method stub
		 return reportDataService.__getHiringsPerMonthServiceData(this.generateRequest(dto));
	}

	@ResponseBody
    @RequestMapping(value="/fuentes-reclutamiento", method = RequestMethod.POST)
	public ArrayList<RecruitmentSourcesResponse> __getRecruitmentSourceServiceData(@RequestBody QueryDTO dto) throws GenericStatusException{
		// TODO Auto-generated method stub
		return reportDataService.__getRecruitmentSourceServiceData(this.generateRequest(dto));
	}

	@ResponseBody
    @RequestMapping(value="/tipos-entrevista", method = RequestMethod.POST)
	public ArrayList<InterviewTypeResponse> __getInterviewsTypeServiceData( @RequestBody QueryDTO dto) throws GenericStatusException{
		// TODO Auto-generated method stub
		return reportDataService.__getInterviewsTypeServiceData(this.generateRequest(dto));
	}

	@ResponseBody
    @RequestMapping(value="/contrataciones", method = RequestMethod.POST)
	public ArrayList<HiringsResponse> __getHiringsPerAnalystServiceData(@RequestBody QueryDTO dto) throws GenericStatusException{
		// TODO Auto-generated method stub
		return reportDataService.__getHiringsPerAnalystServiceData(this.generateRequest(dto));
	}


	@ResponseBody
    @RequestMapping(value="/filtros/get-sub-area-interes", method = RequestMethod.GET)
	public ArrayList<CatalogoVO> getSubAreaInteresById(Integer idAreaInteres) {
		// TODO Auto-generated method stub
		//return filterService.getSubAreaInteresById(idAreaInteres);
		return null;
	}

	@ResponseBody
    @RequestMapping(value="/filtros/get-gerencias-by-regional", method = RequestMethod.GET)
	public ArrayList<CatalogoVO> getGerenciasById(Integer idRegional) {
		// TODO Auto-generated method stub
		//return filterService.getGerenciasById(idRegional);
		return null;
	}

	@ResponseBody
    @RequestMapping(value="/filtros/get-regionales-by-divisional", method = RequestMethod.GET)
	public ArrayList<CatalogoVO> getRegionalesById(Integer IdDivisional) {
		// TODO Auto-generated method stub
		//return filterService.getRegionalesById(IdDivisional);
		return null;
	}

	@ResponseBody
    @RequestMapping(value="/filtros/get-analistas-gerencia", method = RequestMethod.GET)
	public ArrayList<CatalogoVO> getAnalystByGerencia(Integer IdGerencia) throws GenericStatusException {
		// TODO Auto-generated method stub
		return hlpRsp(filterService.getAnalystByGerencia(IdGerencia));
	}
	
	
	@ResponseBody
    @RequestMapping(value="/filtros/get-divisionales", method = RequestMethod.GET)
	public ArrayList<CatalogoVO> getDivisionales() {
		// TODO Auto-generated method stub
		//return filterService.getDivisiones();
		return null;
	}
	
	@ResponseBody
    @RequestMapping(value="/filtros/get-niveles-puesto", method = RequestMethod.GET)
	public ArrayList<CatalogoVO> getNivelesPuesto() throws GenericStatusException {
		// TODO Auto-generated method stub
		return hlpRsp(filterService.getRangoVacante());
	}
	
	
	@ResponseBody
    @RequestMapping(value="/filtros/get-observadores", method = RequestMethod.GET)
	public ArrayList<SCatalogoVO> getObservadores() throws GenericStatusException {
		// TODO Auto-generated method stub
		return hlpSRsp(filterService.getObservadores());
	}
	
	
	@ResponseBody
    @RequestMapping(value="/filtros/get-areas-interesa", method = RequestMethod.GET)
	public ArrayList<CatalogoVO> getAreasInteres() {
		// TODO Auto-generated method stub
		//return filterService.getAreaInteres();
		return null;
	}
	
	@ResponseBody
    @RequestMapping(value="/filtros/get-analistas-corporativo", method = RequestMethod.GET)
	public ArrayList<CatalogoVO> getAnalistasCorp() throws GenericStatusException {
		// TODO Auto-generated method stub
		return hlpRsp(filterService.getAnalistasCorporativo());
	}
	
	
	@ResponseBody
    @RequestMapping(value="/filtros/get-centro-costos", method = RequestMethod.GET)
	public ArrayList<CatalogoVO> getCentroCostos() throws GenericStatusException {
		// TODO Auto-generated method stub
		return hlpRsp(filterService.getCentroCostos());
	}
	
	private ArrayList<CatalogoVO> hlpRsp(ArrayList<CatalogoVO> in){
		ArrayList<CatalogoVO> out = new ArrayList<CatalogoVO>();
		out.addAll(in);
		return out;
	}
	
	
	private ArrayList<SCatalogoVO> hlpSRsp(ArrayList<SCatalogoVO> in){
		ArrayList<SCatalogoVO> out = new ArrayList<SCatalogoVO>();
		out.addAll(in);
		return out;
	}
}