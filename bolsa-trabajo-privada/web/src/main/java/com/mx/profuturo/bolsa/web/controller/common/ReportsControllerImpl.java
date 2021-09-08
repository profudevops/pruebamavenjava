package com.mx.profuturo.bolsa.web.controller.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;


import com.mx.profuturo.bolsa.model.service.vacancies.vo.PersonaProfuturoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.profuturo.bolsa.configuration.TokenService;
import com.mx.profuturo.bolsa.model.graphics.adapters.AreaChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.BarChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.DonutChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.StackedChartAdapter;
import com.mx.profuturo.bolsa.model.reports.dto.FilterChainingDTO;
import com.mx.profuturo.bolsa.model.reports.dto.QueryDTO;
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
import com.mx.profuturo.bolsa.model.role.Branches;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.service.charts.ChartAdapterService;
import com.mx.profuturo.bolsa.service.reports.ExcelService;
import com.mx.profuturo.bolsa.service.reports.FilterService;
import com.mx.profuturo.bolsa.service.reports.ReportDataService;
import com.mx.profuturo.bolsa.service.reports.ReportToChartService;
import com.mx.profuturo.bolsa.service.vacantes.VacancyService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.formatter.EscapeUtil;

@Controller("reportsControl")
@RequestMapping(value="metricas")
@Scope("request") // probablemente session
@CrossOrigin
public class ReportsControllerImpl extends BaseController {

	/*@Autowired
	private GrantingUserSettings userSettings;*/

	@Autowired
	private FilterService filterService;

	@Autowired
	private ReportDataService reportDataService;

	@Autowired
	private ReportToChartService reportToChartService;

	@Autowired
	private ChartAdapterService chartAdapterService;

	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private EscapeUtil escapeUtil;

	private ReportVO reportVo;

	@Autowired
	private TokenService tokenService;

	@Autowired
	VacancyService vacancyService;


	@ResponseBody
    @RequestMapping(value="encadenar-filtros", method = RequestMethod.GET)
	@CrossOrigin
	public EntityResponseBean<LinkedList<CatalogoVO>> getFilterChainingById(@RequestHeader("Authorization") String token,@RequestParam Integer idElementoClave,@RequestParam String filtro) throws GenericStatusException {
		Integer idRama = tokenService.getBranchId(this.getCleanToken(token));
		String numeroEmpleado = tokenService.getUserIdFromToken(this.getCleanToken(token));
		LinkedList<?> vo = null;
		if("SUBAREAINTERES".equals(filtro)) {
			//vo = this.filterService.getSubAreaInteresById(dto.getIdElementoClave());
		}else if("REGIONALES".equals(filtro)) {
			//vo = this.filterService.getRegionalesById(dto.getIdElementoClave());
			vo = this.vacancyService.getRegionsByBranch(idElementoClave);
		}else if("GERENCIAS".equals(filtro)) {
			vo =  this.vacancyService.getDepartmentsByRegional(idElementoClave);
		}else if("ANALISTAS".equals(filtro)) {


 			LinkedList<PersonaProfuturoVO> pre =
 					vacancyService.searchCollaborator(numeroEmpleado, "",idRama);
 					//this.filterService.getAnalystByGerencia(idElementoClave);
 			vo = pre;
			
		}     
		

		return this.buildEntitySuccessResponse(vo);
	}

	
	@ResponseBody
    @RequestMapping(value="cargar-filtros-iniciales", method = RequestMethod.GET)
	public EntityResponseBean<FilterVO> getInitialFilterDataByReport(@RequestHeader("Authorization")  String token, @RequestParam String reportCode , @RequestParam String branch) throws GenericStatusException {
		
		String pBranch = EscapeUtil.escapeHtml(branch);
		UserCredentialsVO user = tokenService.getUserFromToken(this.getCleanToken(token));
	    String idUser = user.getId();
		Integer idBranch = user.getIdBranch();
		FilterVO vo = this.filterService.getFiltersData(idUser, idBranch, reportCode, pBranch);
		return this.buildEntitySuccessResponse(vo);
	}
	
	@ResponseBody
    @RequestMapping(value="generar-grafica", method = RequestMethod.POST)
	public EntityResponseBean<GraphicVO> getGraphicObjectsByReport(@RequestHeader("Authorization")  String token, @RequestBody QueryDTO query) throws GenericStatusException{ 
		String reportCode = query.getReportCode();
		UserCredentialsVO user = tokenService.getUserFromToken(this.getCleanToken(token));
		Integer idBranch = user.getIdBranch();
		query.setIdRama(this.handleBranch(query.getTipoVacante(),idBranch));
		
		ReportVO vo = null;
		GraphicVO gvo = null;
		if("MOR".equals(reportCode)) {
			vo = this.getRejectReasonData(query);
			gvo = this.getRejectReasonChart(vo.getReportData());
			vo.setGraphicVO(gvo);
		}else if("MORPE".equals(reportCode)) {
			//vo = this.getRejectReasonData(query);
			vo = this.getRejectReasonPerStageData(query);
			gvo = this.getRejectReasonByStepChart(vo.getReportData());
			vo.setGraphicVO(gvo);
		}else if("EAB".equals(reportCode)) {
			vo = this.getAbandonmentReasonData(query);
			gvo = this.getAbandonmentReasonChart(vo.getReportData());
			vo.setGraphicVO(gvo);
		}else if("CFC".equals(reportCode)) {
			vo = this.getFinalistVsHiredData(query);
			gvo = this.getFinalistVsHiredChart(vo.getReportData());
			vo.setGraphicVO(gvo);
		}else if("CNP".equals(reportCode)) {
			vo = this.getPositionLevelHiringsData(query);
			gvo = this.getPositionLevelHiringsChart(vo.getReportData());
			vo.setGraphicVO(gvo);
		}else if("CPM".equals(reportCode)) {
			vo = this.getHiringsPerMonthData(query);
			gvo = this.getHiringsPerMontChart(vo.getReportData());
			vo.setGraphicVO(gvo);
		}else if("CAN".equals(reportCode)) {
			vo = this.getHiringsPerAnalystData(query);
			gvo = this.getHiringsPerAnalystChart(vo.getReportData());
			vo.setGraphicVO(gvo);
		}else if("FRE".equals(reportCode)) {
			vo = this.getRecruitmentSourcesData(query);
			gvo = this.getRecruitmentSourcesChart(vo.getReportData());
			vo.setGraphicVO(gvo);
		}else if("TIE".equals(reportCode)) {
			vo = this.getInterviewTypesData(query);
			gvo = this.getInterviewTypesChart(vo.getReportData());
			vo.setGraphicVO(gvo);
		}
		
		
		
		this.reportVo = vo;
		return this.buildEntitySuccessResponse(vo.getGraphicVO());
	}
	

	
	private Integer handleBranch(String tipoVacante, Integer idBranch) {
		
		Integer branch = idBranch;
		if(branch.equals(Branches.CORPORATEID)) {
			if(!"V_CORP".equals(tipoVacante) && null != tipoVacante) 
				branch = Branches.CLIENTSID;
		}
		
		return branch;
	}


	private GraphicVO getInterviewTypesChart(LinkedList reportData) {
		GraphicVO gVo = new GraphicVO();
		StackedChartAdapter adapter = this.reportToChartService.translateInterviewTypesChart(reportData);
		Object chart = this.chartAdapterService.getStackedChartWidget(adapter);
		gVo.setGrafica(chart);
		gVo.setNombre("Tipos de entrevista");
		return gVo;
	}


	private ReportVO getInterviewTypesData(QueryDTO query) throws GenericStatusException{
		ReportVO  vo = new ReportVO();
		LinkedList<TiposEntrevistaVO> reportData = this.reportDataService.getInterviewTypesData(query);
		vo.setTitle("Tipos de entrevista");
		vo.setCode(query.getReportCode());
		vo.setReportData(reportData);
		return vo;
	}

	
	
	

	private GraphicVO getRecruitmentSourcesChart(LinkedList reportData) {
		GraphicVO gVo = new GraphicVO();
		StackedChartAdapter adapter = this.reportToChartService.translateRecruitmentSourcesChart(reportData);
		Object chart = this.chartAdapterService.getStackedChartWidget(adapter);
		gVo.setGrafica(chart);
		gVo.setNombre("Fuentes de reclutamiento");
		return gVo;
	}


	private ReportVO getRecruitmentSourcesData(QueryDTO query) throws GenericStatusException{
		ReportVO  vo = new ReportVO();
		LinkedList<FuentesReclutamientoVO> reportData = this.reportDataService.getRecruitmentSourcesData(query);
		vo.setTitle("Fuentes de reclutamiento");
		vo.setCode(query.getReportCode());
		vo.setReportData(reportData);
		return vo;
	}

	
	
	

	private GraphicVO getHiringsPerAnalystChart(LinkedList reportData) {
		GraphicVO gVo = new GraphicVO();
		BarChartAdapter adapter = this.reportToChartService.translateHiringsPerAnalystChart(reportData);
		Object chart = this.chartAdapterService.getBarChartWidget(adapter);
		gVo.setGrafica(chart);
		gVo.setNombre("Contrataciones por Responsable");
		return gVo;
	}


	private ReportVO getHiringsPerAnalystData(QueryDTO query) throws GenericStatusException{
	
		ReportVO  vo = new ReportVO();
		LinkedList<ContratacionesAnalistaVO> reportData = this.reportDataService.getHiringsPerAnalystData(query);
		vo.setTitle("Contrataciones por Responsable");
		vo.setCode(query.getReportCode());
		vo.setReportData(reportData);
		return vo;
	}
	
	
	private GraphicVO getHiringsPerMontChart(LinkedList reportData) {
		GraphicVO gVo = new GraphicVO();
		AreaChartAdapter adapter = this.reportToChartService.translateHiringsPerMonthChart(reportData);
		Object chart = this.chartAdapterService.getLineChartWidget(adapter);
		gVo.setGrafica(chart);
		gVo.setNombre("Contrataciones por mes");
		return gVo;
	}


	private ReportVO getHiringsPerMonthData(QueryDTO query) throws GenericStatusException{
		ReportVO  vo = new ReportVO();
		LinkedList<ContratacionesMesVO> reportData = this.reportDataService.getHiringsPerMonthData(query);
		vo.setTitle("Contrataciones por mes");
		vo.setCode(query.getReportCode());
		vo.setReportData(reportData);
		return vo;
	}



	private ReportVO getPositionLevelHiringsData(QueryDTO query) throws GenericStatusException{
		ReportVO  vo = new ReportVO();
		LinkedList<ContratacionesNivelPuestoVO> reportData = this.reportDataService.getPositionLevelHiringsdData(query);
		vo.setTitle("Contrataciones por nivel de puesto");
		vo.setCode(query.getReportCode());
		vo.setReportData(reportData);
		return vo;
	}


	private GraphicVO getPositionLevelHiringsChart(LinkedList reportData) {
		GraphicVO gVo = new GraphicVO();
		BarChartAdapter adapter = this.reportToChartService.translatePositionLevelHiringsChart(reportData);
		Object chart = this.chartAdapterService.getBarChartWidget(adapter);
		gVo.setGrafica(chart);
		gVo.setNombre("Contrataciones por nivel de puesto");
		return gVo;
	}

	
	
	
	
	

	private GraphicVO getFinalistVsHiredChart(LinkedList reportData) {
		GraphicVO gVo = new GraphicVO();
		DonutChartAdapter adapter = this.reportToChartService.translateFinalistVsHiredChart(reportData);
		Object chart = this.chartAdapterService.getDonutChartWidget(adapter);
		gVo.setGrafica(chart);
		gVo.setNombre("Finalistas Contra Contratados");
		return gVo;
	}


	private ReportVO getFinalistVsHiredData(QueryDTO query) throws GenericStatusException{
		ReportVO  vo = new ReportVO();
		LinkedList<FinalistasContratadosVO> reportData = this.reportDataService.getFinalistVsHiredData(query);
		vo.setTitle("Finalistas contra Contratados");
		vo.setCode(query.getReportCode());
		vo.setReportData(reportData);
		return vo;
	}


	private GraphicVO getAbandonmentReasonChart(LinkedList reportData) {
		GraphicVO gVo = new GraphicVO();
		BarChartAdapter adapter = this.reportToChartService.translateRejectStageChart(reportData);
		Object chart = this.chartAdapterService.getBarChartWidget(adapter);
		gVo.setGrafica(chart);
		gVo.setNombre("Etapas de Deserción");
		return gVo;
	}


	private ReportVO getAbandonmentReasonData(QueryDTO query) throws GenericStatusException{
		ReportVO  vo = new ReportVO();
		LinkedList<MotivosRechazoVO> reportData = this.reportDataService.getAbandonmentReasonData(query);
		vo.setTitle("Etapas de Deserción");
		vo.setCode(query.getReportCode());
		vo.setReportData(reportData);
		return vo;
	}


	
	
	
	private ReportVO getRejectReasonData(QueryDTO query) throws GenericStatusException{
		ReportVO  vo = new ReportVO();
		LinkedList<MotivosRechazoVO> reportData = this.reportDataService.getRejectReasonsData(query);
		vo.setTitle("Motivos de rechazo");
		vo.setCode(query.getReportCode());
		vo.setReportData(reportData);
		return vo;
	}
	
	
	
	private ReportVO getRejectReasonPerStageData(QueryDTO query) throws GenericStatusException{
		ReportVO  vo = new ReportVO();
		LinkedList<MotivosRechazoVO> reportData = this.reportDataService.getRejectReasonsPerStageData(query);
		vo.setTitle("Motivos de rechazo por etapa");
		vo.setCode(query.getReportCode());
		vo.setReportData(reportData);
		return vo;
	}
	
	
	private GraphicVO getRejectReasonChart(LinkedList<MotivosRechazoVO> reportData) {
	
		GraphicVO gVo = new GraphicVO();
		BarChartAdapter adapter = this.reportToChartService.translateRejectReasonChart(reportData);
		Object chart = this.chartAdapterService.getBarChartWidget(adapter);
		gVo.setGrafica(chart);
		gVo.setNombre("Motivos de rechazo");
		return gVo;
	}
	
	
	
	
	
	
	private ReportVO getRejectReasonByStepData(QueryDTO query) throws GenericStatusException{
		ReportVO  vo = new ReportVO();
		vo.setTitle("Motivos de rechazo por etapa");
		vo.setCode(query.getReportCode());
		LinkedList<MotivosRechazoVO> reportData = this.reportDataService.getRejectReasonsData(query);
		vo.setReportData(reportData);
		return vo;
	}
	
	
	private GraphicVO getRejectReasonByStepChart(LinkedList<MotivosRechazoVO> reportData) {
		
		GraphicVO gVo = new GraphicVO();
		StackedChartAdapter adapter = this.reportToChartService.translateRejectReasonByStepChart(reportData);
		Object chart = this.chartAdapterService.getStackedChartWidget(adapter);
		gVo.setGrafica(chart);
		gVo.setNombre("Motivos de rechazo por etapa");
		return gVo;
	}
	
	
	@ResponseBody
    @RequestMapping(value="descargar-reporte", method = RequestMethod.POST)
	public void downloadXLSX(@RequestHeader("Authorization")  String token,@RequestBody QueryDTO query, HttpServletResponse response) throws IOException,Exception {
		String reportCode = query.getReportCode();
		UserCredentialsVO user = tokenService.getUserFromToken(this.getCleanToken(token));
		Integer idBranch = user.getIdBranch();
		query.setIdRama(this.handleBranch(query.getTipoVacante(), idBranch));
		ReportVO vo = null;

			if("MOR".equals(reportCode)) {
				vo  = this.getRejectReasonData(query);
				
			}else if("MORPE".equals(reportCode)) {
				vo = this.getRejectReasonByStepData(query);
		
			}else if("EAB".equals(reportCode)) {
				vo = this.getAbandonmentReasonData(query);
		
			}else if("CFC".equals(reportCode)) {
				vo = this.getFinalistVsHiredData(query);
			
			}else if("CNP".equals(reportCode)) {
				vo = this.getPositionLevelHiringsData(query);
			
			}else if("CPM".equals(reportCode)) {
				vo = this.getHiringsPerMonthData(query);
				
			}else if("CAN".equals(reportCode)) {
				vo = this.getHiringsPerAnalystData(query);
		
			}else if("FRE".equals(reportCode)) {
				vo = this.getRecruitmentSourcesData(query);
		
			}else if("TIE".equals(reportCode)) {
				vo = this.getInterviewTypesData(query);
				
			}else {
				throw new Exception("No report to download");
			}

		vo.setBranch(query.getRama());
		this.reportVo = vo; 
		Integer tipo = "V_CTS".equals(query.getTipoVacante())?0:1;
		this.reportVo.setTipoVacante(tipo);
		this.excelService.downloadXLSXFile(response, this.reportVo);
	}
	
	
	@ResponseBody
    @RequestMapping(value="obtener-lista-reportes", method = RequestMethod.GET)
	public EntityResponseBean<LinkedList<CatalogoVO>> getReportList(HttpServletResponse response) throws IOException {
		LinkedList<CatalogoVO> vo = new LinkedList<>();
		
		CatalogoVO r1 = new CatalogoVO();
		r1.setCodigo("MOR");
		r1.setDescripcion("Motivos de rechazo");
		
		CatalogoVO r5 = new CatalogoVO();
		r5.setCodigo("MORPE");
		r5.setDescripcion("Motivos de rechazo por Etapa");
		
		CatalogoVO r4 = new CatalogoVO();
		r4.setCodigo("EAB");
		r4.setDescripcion("Etapas de deserción");
		
		CatalogoVO r2 = new CatalogoVO();
		r2.setCodigo("FRE");
		r2.setDescripcion("Fuentes de reclutamiento");
		
		CatalogoVO r3 = new CatalogoVO();
		r3.setCodigo("CAN");
		r3.setDescripcion("Contrataciones por responsable");
		
		CatalogoVO r6 = new CatalogoVO();
		r6.setCodigo("CFC");
		r6.setDescripcion("Candidatos finalistas y contratados");
		
		
		CatalogoVO r7 = new CatalogoVO();
		r7.setCodigo("CNP");
		r7.setDescripcion("Contrataciones por nivel de puesto");
		
		CatalogoVO r8 = new CatalogoVO();
		r8.setCodigo("CPM");
		r8.setDescripcion("Contrataciones por mes");
		
		
		CatalogoVO r9 = new CatalogoVO();
		r9.setCodigo("TIE");
		r9.setDescripcion("Tipo de entrevista");
		
		vo.add(r1);
		vo.add(r2);
		vo.add(r3);
		vo.add(r4);
		vo.add(r5);
		vo.add(r6);
		vo.add(r7);
		vo.add(r8);
		vo.add(r9);
		
		return this.buildEntitySuccessResponse(vo);
	}
		
	
}