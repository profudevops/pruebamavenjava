package com.mx.profuturo.bolsa.service.desk;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;
import com.mx.profuturo.bolsa.model.desk.dto.DeskDTO;
import com.mx.profuturo.bolsa.model.desk.vo.EscritorioVO;
import com.mx.profuturo.bolsa.model.graphics.adapters.AreaChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.BarChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.DonutChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.StackedChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.vo.AreaChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.BarChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.DonutChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.LineChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.SingleKPIWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.StackedChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.elements.PairedArrayValue;
import com.mx.profuturo.bolsa.model.graphics.vo.elements.StackedChartValue;
import com.mx.profuturo.bolsa.model.graphics.vo.elements.StackedDataset;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.role.CommercialRoles;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import com.mx.profuturo.bolsa.service.charts.ChartAdapterService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public abstract class DeskServiceBase implements DeskService {

	@Autowired
	DeskDataToChartService chartService;
	@Autowired
	private ChartAdapterService chartAdapterService;
	
	@Override
	public EscritorioVO getDeskData(DeskDTO dto) throws GenericStatusException {

		EscritorioVO vo = null;

		String role = dto.getRole();
		String idUsuario = dto.getUserId();

		if(CorporateRoles.ADMN.equals(role)) {

			vo = this.getAdministratorCorpDesk(idUsuario);

		}else if(CorporateRoles.RECRUITER.equals(role)) {

			vo = this.getRecruiterCorpDesk(idUsuario);

		}else if(CorporateRoles.COSTUMER_RECRUITER.equals(role)) {

			vo= this.getCustomerRecruiterDesk(idUsuario);

		}else if(CorporateRoles.OBSERVER.equals(role)) {

			vo= this.getObserverDesk(idUsuario);
		}else if(CommercialRoles.ADMN.equals(role)) {

			vo=this.getAdminCommercialDesk(idUsuario);

		}else if(CommercialRoles.CONSULTOR.equals(role)) {

			vo= this.getCommConsultor(idUsuario);

		}else if(CommercialRoles.RECRUITER.equals(role) || CommercialRoles.ANALYST.equals(role)) {

			vo= this.getCommAnalyst(idUsuario);

		}else if(CommercialRoles.OBSERVER.equals(role)) {
			vo= this.getCommObserver(idUsuario);
		}

		else{

			vo = new EscritorioVO();

		}
		
		

		return vo;

	}


	@Override
	public abstract RecruiterCorpDeskResponse __getRecruiterCorpData(RequestBean<String> request) throws GenericStatusException;
	
	
	private RequestBean<String> generateIdRequest(String idUsuario){
		RequestBean<String> rq = new RequestBean<String>();
		Map<String, String> uri = new HashMap<>();
		uri.put("idUsuario", idUsuario);
		rq.setUriVariables(uri);
		return rq ;
	}
	
	public EscritorioVO getRecruiterCorpDesk(String idUsuario) throws GenericStatusException {  

		RecruiterCorpDeskResponse data = this.__getRecruiterCorpData(this.generateIdRequest(idUsuario));
		BarChartAdapter adapter = chartService.translateBarChart(data.getCandidatosEtapa());
		BarChartWidgetVO bar = chartAdapterService.getBarChartWidget(adapter);
		
		EscritorioVO vo = new EscritorioVO();
		SingleKPIWidgetVO kpi1 = chartAdapterService.translateKPI(data.getEntrevistasHoy());
		SingleKPIWidgetVO kpi2 = chartAdapterService.translateKPI(data.getNuevosPostulados());
		SingleKPIWidgetVO kpi3 = chartAdapterService.translateKPI(data.getVacantesAbiertas());

		vo.getWidgets().add(kpi1);
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(bar);


		return vo;
	}

	@Override
	public abstract AdministratorCorpDeskResponse __getAdministratorCorpData(RequestBean<String> request) throws GenericStatusException ;
	private EscritorioVO getAdministratorCorpDesk(String idUsuario) throws GenericStatusException {


		AdministratorCorpDeskResponse data = this.__getAdministratorCorpData(this.generateIdRequest(idUsuario));
		EscritorioVO vo = new EscritorioVO();
		
 
		SingleKPIWidgetVO kpi1 = chartAdapterService.translateKPI(data.getCandidatosPostulados());
		SingleKPIWidgetVO kpi2 = chartAdapterService.translateKPI(data.getVacantesAbiertas());
		SingleKPIWidgetVO kpi3 = chartAdapterService.translateKPI(data.getCandidatosProceso());
		SingleKPIWidgetVO kpi4 = chartAdapterService.translateKPI(data.getPlazasAbiertas());
		
		DonutChartAdapter adapter = chartService.translateDonutChart(data.getCandidatosProcesoGrafica());
		DonutChartWidgetVO piebar = (DonutChartWidgetVO) chartAdapterService.getDonutChartWidget(adapter);
		
		StackedChartAdapter adapter2 = chartService.translateStackedChart(data.getEtapasReclutamiento());
		StackedChartWidgetVO  bar = (StackedChartWidgetVO) chartAdapterService.getStackedChartWidget(adapter2);
			
		vo.getWidgets().add(kpi1);
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(piebar);
		vo.getWidgets().add(bar);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(kpi4);

		return vo;



	}
	

	@Override
	public abstract CustomerRecruiterCorpDeskResponse __getCustomerRecruiterCorpData(RequestBean<String> request) throws GenericStatusException ;
	
	
	
	private EscritorioVO getCustomerRecruiterDesk(String idUsuario) throws GenericStatusException {
		EscritorioVO vo = new EscritorioVO();
		
		CustomerRecruiterCorpDeskResponse datos = this.__getCustomerRecruiterCorpData(this.generateIdRequest(idUsuario));
		
		/*
		 * SingleKPIWidgetVO kpi1 = new SingleKPIWidgetVO();
			kpi1.setStatus("complete");
			kpi1.setTitle("Entrevistas para hoy");
			kpi1.setValue(3);
	
			SingleKPIWidgetVO kpi2 = new SingleKPIWidgetVO();
	
			kpi2.setStatus("complete");
			kpi2.setTitle("Nuevos postulados");
			kpi2.setValue(75);
	
	
	
			SingleKPIWidgetVO kpi3 = new SingleKPIWidgetVO();
	
			kpi3.setStatus("complete");
			kpi3.setTitle("Plazas Abiertas");
			kpi3.setValue(14);
			
			
			BarChartWidgetVO bar = new BarChartWidgetVO(); 
			
			bar.setLabelX("Fase reclutamiento");
			bar.setLabelY("Candidatos");
			PairedArrayValue value = new PairedArrayValue();
			LinkedList<String> labels = new LinkedList<String>();
			LinkedList<String> datasets = new LinkedList<String>();
			labels.add("F1");
			labels.add("F2");
			labels.add("F3");
			labels.add("F4");
			value.setLabels(labels);
			datasets.add(30);
			datasets.add(20);
			datasets.add(10);
			datasets.add(15);
			value.setDatasets(datasets);
			bar.setValue(value);
	
			vo.getWidgets().add(kpi1);
			vo.getWidgets().add(kpi2);
			vo.getWidgets().add(kpi3);
			vo.getWidgets().add(bar);*/
	
			SingleKPIWidgetVO kpi1 = chartAdapterService.translateKPI(datos.getEntrevistasHoy());
			SingleKPIWidgetVO kpi2 = chartAdapterService.translateKPI(datos.getNuevosPostulados());
			SingleKPIWidgetVO kpi3 = chartAdapterService.translateKPI(datos.getPlazasAbiertas());
			BarChartAdapter adapter2 = chartService.translateBarChart(datos.getCandidatosEtapa());
			BarChartWidgetVO  bar2 = (BarChartWidgetVO) chartAdapterService.getBarChartWidget(adapter2);
			
			vo.getWidgets().add(kpi1);
			vo.getWidgets().add(kpi2);
			vo.getWidgets().add(kpi3);
			vo.getWidgets().add(bar2);
			return vo;
	}
	
	
	

	@Override
	public abstract ObserverCorpDeskResponse __getObserverCorpData(RequestBean<String> request) throws GenericStatusException ;
	
	private EscritorioVO getObserverDesk(String idUsuario) throws GenericStatusException {
		
		ObserverCorpDeskResponse data = this.__getObserverCorpData(this.generateIdRequest(idUsuario));
		
		EscritorioVO vo = new EscritorioVO();
		
		/*
		SingleKPIWidgetVO kpi1 = new SingleKPIWidgetVO(); 
		kpi1.setStatus("complete");
		kpi1.setTitle("Entrevista para hoy");
		kpi1.setValue(2);

		SingleKPIWidgetVO kpi2 = new SingleKPIWidgetVO(); 
		kpi2.setStatus("complete");
		kpi2.setTitle("Candidatos en primeras entrevistas");
		kpi2.setValue(2);
		
		SingleKPIWidgetVO kpi3 = new SingleKPIWidgetVO(); 
		kpi3.setStatus("complete");
		kpi3.setTitle("Vacantes abiertas");
		kpi3.setValue(2);
		*/

		/*DonutChartWidgetVO piebar = new DonutChartWidgetVO(); 
		piebar.setTitle("Candidatos en Proceso");

		PairedArrayValue valuep = new PairedArrayValue();

		valuep.getLabels().add("Aspirantes");
		valuep.getLabels().add("En proceso");
		valuep.getLabels().add("Concluido");
		valuep.getLabels().add("Rechazados");

		valuep.getDatasets().add(33);
		valuep.getDatasets().add(26);
		valuep.getDatasets().add(14);
		valuep.getDatasets().add(27);

		piebar.setValue(valuep);*/

		

		SingleKPIWidgetVO kpi1 = chartAdapterService.translateKPI(data.getEntrevistasHoy());
		SingleKPIWidgetVO kpi2 = chartAdapterService.translateKPI(data.getCandidatosPrimerasEntrevistas());
		SingleKPIWidgetVO kpi3 = chartAdapterService.translateKPI(data.getVacantesObservadas());
		DonutChartAdapter adapter = chartService.translateDonutChart(data.getCandidatosProceso());
		DonutChartWidgetVO piebar = (DonutChartWidgetVO) chartAdapterService.getDonutChartWidget(adapter);
		piebar.setSize("lg");
		
		vo.getWidgets().add(kpi1);
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(piebar);
	
		return vo;
	}
	
	@Override
	public abstract AdministratorCommDeskResponse __getAdministratorCommData(RequestBean<String> request) throws GenericStatusException ;
	private EscritorioVO getAdminCommercialDesk(String idUsuario) throws GenericStatusException {

		AdministratorCommDeskResponse data = this.__getAdministratorCommData(this.generateIdRequest(idUsuario));
		EscritorioVO vo = new EscritorioVO();
		
		LinkedList<String> dias = new LinkedList<String>();
		dias.add("DOMINGO"); 
		dias.add("LUNES");
		dias.add("MARTES");
		dias.add("MIÉRCOLES");
		dias.add("JUEVES");
		dias.add("VIERNES");
		dias.add("SÁBADO");
 
		SingleKPIWidgetVO kpi1 = chartAdapterService.translateKPI(data.getCandidatosPostulados());
		SingleKPIWidgetVO kpi2 = chartAdapterService.translateKPI(data.getCandidatosProceso());
		SingleKPIWidgetVO kpi3 = chartAdapterService.translateKPI(data.getVacantesAbiertas());
		SingleKPIWidgetVO kpi4 = chartAdapterService.translateKPI(data.getPlazasAbiertas());
		
		DonutChartAdapter adapter = chartService.translateDonutChart(data.getCandidatosProcesoGrafica());
		DonutChartWidgetVO piebar = (DonutChartWidgetVO) chartAdapterService.getDonutChartWidget(adapter);

		
		AreaChartAdapter adapter2 = chartService.translateAreaChart(data.getPostulacionesDia(), dias);
		AreaChartWidgetVO  bar =  (AreaChartWidgetVO) chartAdapterService.getAreaChartWidget(adapter2);
		bar.setTitle("Postulaciones por día");
		bar.setSize("lg");
		
	/*
		BarChartAdapter adapter2 = chartService.translateBarChart(data.getPostulacionesDia());
		BarChartWidgetVO  bar = (BarChartWidgetVO) chartAdapterService.getBarChartWidget(adapter2);
		*/
		vo.getWidgets().add(kpi1);
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(piebar);
		vo.getWidgets().add(bar);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(kpi4);

		return vo;
	}

	@Override
	public abstract ConsultorCommDeskResponse __getCommConsultorData(RequestBean<String> request) throws GenericStatusException;
	public EscritorioVO getCommConsultor(String idUsuario) throws GenericStatusException {
		EscritorioVO vo= new EscritorioVO();
		ConsultorCommDeskResponse data = __getCommConsultorData(this.generateIdRequest(idUsuario));
		StackedChartAdapter adapter2 = chartService.translateStackedChart(data.getEtapasReclutamiento());
		StackedChartWidgetVO  bar = (StackedChartWidgetVO) chartAdapterService.getStackedChartWidget(adapter2);

		SingleKPIWidgetVO w1 = chartAdapterService.translateKPI(data.getCandidatosPostulados());
		SingleKPIWidgetVO w2 = chartAdapterService.translateKPI(data.getPlazasAbiertas());
		SingleKPIWidgetVO w3 = chartAdapterService.translateKPI(data.getCandidatosProceso());
		vo.getWidgets().add(w1);
		vo.getWidgets().add(w2);
		vo.getWidgets().add(w3);
		vo.getWidgets().add(bar);

		return vo;
	}

	@Override
	public abstract AnalystCommDeskResponse __getCommAnalystData(RequestBean<String> request) throws GenericStatusException;
	private EscritorioVO getCommAnalyst(String idUsuario) throws GenericStatusException {


		EscritorioVO vo = new EscritorioVO();
		AnalystCommDeskResponse data = __getCommAnalystData(this.generateIdRequest(idUsuario));

		BarChartAdapter adapter2 = chartService.translateBarChart(data.getCandidatosEtapa());
		BarChartWidgetVO  bar = (BarChartWidgetVO) chartAdapterService.getBarChartWidget(adapter2);
		
		
		SingleKPIWidgetVO kpi1 = chartAdapterService.translateKPI(data.getEntrevistasHoy());
		SingleKPIWidgetVO kpi2 = chartAdapterService.translateKPI(data.getNuevosPostulados());
		SingleKPIWidgetVO kpi3 = chartAdapterService.translateKPI(data.getPlazasAbiertas());
		SingleKPIWidgetVO kpi4 = chartAdapterService.translateKPI(data.getMisEntrevistasHoy());
		SingleKPIWidgetVO kpi5 = chartAdapterService.translateKPI(data.getEntrevistasClientesHoy());
	

		vo.getWidgets().add(kpi1);
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(kpi4);
		vo.getWidgets().add(bar);
		vo.getWidgets().add(kpi5);
		
		
		
		return vo;
		
		
	}

	@Override
	public abstract ObserverCommDeskResponse __getCommObserver(RequestBean<String> request) throws GenericStatusException;
	private EscritorioVO getCommObserver(String idUsuario) throws GenericStatusException {
		//gerente comercial
		EscritorioVO vo = new EscritorioVO();
		ObserverCommDeskResponse datos = this.__getCommObserver(this.generateIdRequest(idUsuario));

		SingleKPIWidgetVO kpi2 = chartAdapterService.translateKPI(datos.getCandidatosProceso());
		SingleKPIWidgetVO kpi3 = chartAdapterService.translateKPI(datos.getEntrevistasHoy());
		BarChartAdapter adapter2 = chartService.translateBarChart(datos.getCandidatosEtapa());
		BarChartWidgetVO  bar2 = (BarChartWidgetVO) chartAdapterService.getBarChartWidget(adapter2);
		
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(bar2);

		return vo;
	}


}
