package com.mx.profuturo.bolsa.service.desk;

import com.mx.profuturo.bolsa.model.role.CommercialRoles;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.desk.data.DatoCompuesto;
import com.mx.profuturo.bolsa.model.desk.data.DatosCompuestosGrafica;
import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;
import com.mx.profuturo.bolsa.model.desk.dto.DeskDTO;
import com.mx.profuturo.bolsa.model.desk.vo.EscritorioVO;
import com.mx.profuturo.bolsa.model.graphics.vo.BarChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.DonutChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.LineChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.SingleKPIWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.StackedChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.elements.PairedArrayValue;
import com.mx.profuturo.bolsa.model.graphics.vo.elements.StackedChartValue;
import com.mx.profuturo.bolsa.model.graphics.vo.elements.StackedDataset;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;



@Service
@Scope("request")
@Profile("mock")
public class DeskServiceMockImpl extends DeskServiceBase {
	


	public EscritorioVO getRecruiterCorpDesk(String idUsuario) {  

		
		EscritorioVO vo = new EscritorioVO();
		SingleKPIWidgetVO kpi1 = new SingleKPIWidgetVO();
		kpi1.setStatus("complete");
		kpi1.setTitle("Entrevistas para hoy");
		kpi1.setValue(6);
		
		SingleKPIWidgetVO kpi2 = new SingleKPIWidgetVO(); 
		kpi2.setStatus("complete");
		kpi2.setTitle("Nuevos postulados");
		kpi2.setValue(2);

		SingleKPIWidgetVO kpi3 = new SingleKPIWidgetVO();
		kpi3.setStatus("complete");
		kpi3.setTitle("Vacantes Abiertas");
		kpi3.setValue(6);


		BarChartWidgetVO bar = new BarChartWidgetVO(); 
	
		bar.setLabelX("Fase reclutamiento");
		bar.setLabelY("Candidatos");
		PairedArrayValue value = new PairedArrayValue();
		LinkedList<String> labels = new LinkedList<String>();
		LinkedList<Integer> datasets = new LinkedList<Integer>();
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
		vo.getWidgets().add(bar);


		return vo;
	}
	private EscritorioVO getAdministratorCorpDesk(String idUsuario) {



		EscritorioVO vo = new EscritorioVO();
		
		//KPI

		SingleKPIWidgetVO kpi1 = new SingleKPIWidgetVO();

		kpi1.setStatus("complete");

		kpi1.setTitle("Candidatos Postulados");

		kpi1.setValue(6);

		SingleKPIWidgetVO kpi2 = new SingleKPIWidgetVO();

		kpi2.setStatus("complete");

		kpi2.setTitle("Candidatos en Proceso");

		kpi2.setValue(2);



		SingleKPIWidgetVO kpi3 = new SingleKPIWidgetVO();

		kpi3.setStatus("complete");

		kpi3.setTitle("Vacantes Abiertas");

		kpi3.setValue(6);


		SingleKPIWidgetVO kpi4 = new SingleKPIWidgetVO();

		kpi4.setStatus("complete");

		kpi4.setTitle("Plazas Abiertas");

		kpi4.setValue(2);



		//DONUT


		DonutChartWidgetVO piebar = new DonutChartWidgetVO();



		piebar.setTitle("Candidatos en Proceso");

		PairedArrayValue valuep = new PairedArrayValue();

		valuep.getLabels().add("Aspirantes");

		valuep.getLabels().add("En proceso");

		valuep.getLabels().add("Concluido");

		valuep.getLabels().add("Rechazados");



		valuep.getDatasets().add(60);

		valuep.getDatasets().add(52);

		valuep.getDatasets().add(28);

		valuep.getDatasets().add(54);



		piebar.setValue(valuep);

		
		/// STACKED 
		
		StackedChartWidgetVO bar = new StackedChartWidgetVO();
		bar.setLabelX("Reclutadores");
		bar.setLabelY("Candidatos");
		StackedChartValue value = new StackedChartValue();
		
		StackedDataset ds1 = new StackedDataset();

		ds1.setLabel("1eras entrevistas");
		ds1.getData().add(1000);
		ds1.getData().add(1500);
		ds1.getData().add(700);
		ds1.getData().add(300);
		ds1.getData().add(1500);
		ds1.getData().add(200);
		ds1.getData().add(1000);
		

		
		StackedDataset ds2 = new StackedDataset();
		ds2.setLabel("Exámenes");
		ds2.getData().add(200);
		ds2.getData().add(100);
		ds2.getData().add(400);
		ds2.getData().add(200);
		ds2.getData().add(900);
		ds2.getData().add(100);
		ds2.getData().add(50);



		StackedDataset ds3 = new StackedDataset();

		ds3.setLabel("Segundas Entrevistas");
		ds3.getData().add(300);
		ds3.getData().add(400);
		ds3.getData().add(200);
		ds3.getData().add(100);
		ds3.getData().add(400);
		ds3.getData().add(500);
		ds3.getData().add(90);

		
		value.getLabels().add("R1");
		value.getLabels().add("R2");
		value.getLabels().add("R3");
		value.getLabels().add("R4");
		value.getLabels().add("R5");
		value.getLabels().add("R6");
		
		value.getDatasets().add(ds1);
		value.getDatasets().add(ds2);
		value.getDatasets().add(ds3);
		
		bar.setValue(value);

	
		vo.getWidgets().add(kpi1);
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(piebar);
		vo.getWidgets().add(bar);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(kpi4);

		return vo;



	}
	
	
	private EscritorioVO getCustomerRecruiterDesk(String idUsuario) {
	EscritorioVO vo = new EscritorioVO();
		
	SingleKPIWidgetVO kpi1 = new SingleKPIWidgetVO();

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
		LinkedList<Integer> datasets = new LinkedList<Integer>();
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
		vo.getWidgets().add(bar);

		return vo;
	}

	private EscritorioVO getObserverDesk(String idUsuario) {
		EscritorioVO vo = new EscritorioVO();
		
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
		

		DonutChartWidgetVO piebar = new DonutChartWidgetVO(); 
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

		piebar.setValue(valuep);

		
		
		vo.getWidgets().add(kpi1);
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(piebar);
	
		return vo;
	}

	private EscritorioVO getAdminCommercialDesk(String idUsuario) {

		EscritorioVO vo = new EscritorioVO();

		SingleKPIWidgetVO kpi1 = new SingleKPIWidgetVO();
		kpi1.setStatus("complete");
		kpi1.setTitle("Candidatos Postulados");
		kpi1.setValue(6);

		SingleKPIWidgetVO kpi2 = new SingleKPIWidgetVO();
		kpi2.setStatus("complete");
		kpi2.setTitle("Candidatos en Proceso");
		kpi2.setValue(2);

		SingleKPIWidgetVO kpi3 = new SingleKPIWidgetVO();
		kpi3.setStatus("complete");
		kpi3.setTitle("Vacantes Abiertas");
		kpi3.setValue(6);


		BarChartWidgetVO piebar = new BarChartWidgetVO();
		piebar.setTitle("Candidatos en Proceso");
		piebar.setVariant("donut-chart");

		PairedArrayValue valuep = new PairedArrayValue();
		valuep.getLabels().add("Aspirantes");
		valuep.getLabels().add("En proceso");
		valuep.getLabels().add("Concluido");
		valuep.getLabels().add("Rechazados");

		valuep.getDatasets().add(60);
		valuep.getDatasets().add(52);
		valuep.getDatasets().add(28);
		valuep.getDatasets().add(54);
		
		piebar.setSize("");

		piebar.setValue(valuep);
//


		LineChartWidgetVO bar = new LineChartWidgetVO(); 
		
		bar.setLabelX("Día");
		bar.setLabelY("Candidatos");
		PairedArrayValue value = new PairedArrayValue();
		LinkedList<String> labels = new LinkedList<String>();
		LinkedList<Integer> datasets = new LinkedList<Integer>();
		labels.add("Lunes");
		labels.add("Martes");
		labels.add("Miércoles");
		labels.add("Jueves");
		labels.add("Viernes");
		labels.add("Sábado");
		labels.add("Domingo");
		value.setLabels(labels);
		datasets.add(30);
		datasets.add(20);
		datasets.add(10);
		datasets.add(15);
		datasets.add(2);
		datasets.add(2);
		datasets.add(1);
		value.setDatasets(datasets);
		bar.setValue(value);

		vo.getWidgets().add(kpi1);
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(bar);
		vo.getWidgets().add(piebar);

		return vo;
	}

	public EscritorioVO getCommConsultor(String idUsuario) {
		EscritorioVO vo= new EscritorioVO();

		SingleKPIWidgetVO w1 = new SingleKPIWidgetVO();
		w1.setStatus("complete");
		w1.setTitle("Candidatos Postulados");
		w1.setValue(6);

		SingleKPIWidgetVO w2 = new SingleKPIWidgetVO();
		w2.setStatus("complete");
		w2.setTitle("Candidatos en Proceso");
		w2.setValue(2);

		SingleKPIWidgetVO w3 = new SingleKPIWidgetVO();
		w3.setStatus("complete");
		w3.setTitle("Vacantes abiertas");
		w3.setValue(1);


		
		
		
		/// STACKED 
		
				StackedChartWidgetVO bar = new StackedChartWidgetVO();
				bar.setLabelX("Reclutadores");
				bar.setLabelY("Candidatos");
				StackedChartValue value = new StackedChartValue();
				
				StackedDataset ds1 = new StackedDataset();

				ds1.setLabel("1eras entrevistas");
				ds1.getData().add(1000);
				ds1.getData().add(1500);
				ds1.getData().add(700);
				ds1.getData().add(300);
				ds1.getData().add(1500);
				ds1.getData().add(200);
				ds1.getData().add(1000);
				

				
				StackedDataset ds2 = new StackedDataset();
				ds2.setLabel("Exámenes");
				ds2.getData().add(200);
				ds2.getData().add(100);
				ds2.getData().add(400);
				ds2.getData().add(200);
				ds2.getData().add(900);
				ds2.getData().add(100);
				ds2.getData().add(50);



				StackedDataset ds3 = new StackedDataset();

				ds3.setLabel("Segundas Entrevistas");
				ds3.getData().add(300);
				ds3.getData().add(400);
				ds3.getData().add(200);
				ds3.getData().add(100);
				ds3.getData().add(400);
				ds3.getData().add(500);
				ds3.getData().add(90);

				
				value.getLabels().add("R1");
				value.getLabels().add("R2");
				value.getLabels().add("R3");
				value.getLabels().add("R4");
				value.getLabels().add("R5");
				value.getLabels().add("R6");
				
				value.getDatasets().add(ds1);
				value.getDatasets().add(ds2);
				value.getDatasets().add(ds3);
				
				bar.setValue(value);

		vo.getWidgets().add(w1);
		vo.getWidgets().add(w2);
		vo.getWidgets().add(w3);
		vo.getWidgets().add(bar);

		return vo;
	}

	private EscritorioVO getCommAnalyst(String idUsuario) {


		EscritorioVO vo = new EscritorioVO();

		SingleKPIWidgetVO kpi1 = new SingleKPIWidgetVO();
		kpi1.setStatus("complete");
		kpi1.setTitle("Entrevistas para hoy");
		kpi1.setValue(3);

		SingleKPIWidgetVO kpi2 = new SingleKPIWidgetVO();
		kpi2.setStatus("complete");
		kpi2.setTitle("Nuevos Postulados");
		kpi2.setValue(75);

		SingleKPIWidgetVO kpi3 = new SingleKPIWidgetVO();
		kpi3.setStatus("complete");
		kpi3.setTitle("Plazas abiertas");
		kpi3.setValue(14);
		
		
		
		SingleKPIWidgetVO kpi4 = new SingleKPIWidgetVO();
		kpi4.setStatus("complete");
		kpi4.setTitle("Mis entrevistas para hoy");
		kpi4.setValue(2);

		SingleKPIWidgetVO kpi5 = new SingleKPIWidgetVO();
		kpi5.setStatus("complete");
		kpi5.setTitle("Entrevistas de mis clientes hoy");
		kpi5.setValue(1);
		
		
		
		
		BarChartWidgetVO bar = new BarChartWidgetVO(); 
	
		bar.setLabelX("Fase reclutamiento");
		bar.setLabelY("Candidatos");
		PairedArrayValue value = new PairedArrayValue();
		LinkedList<String> labels = new LinkedList<String>();
		LinkedList<Integer> datasets = new LinkedList<Integer>();
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
		vo.getWidgets().add(kpi4);
		vo.getWidgets().add(bar);
		vo.getWidgets().add(kpi5);
		return vo;
	}

	private EscritorioVO getCommObserver(String idUsuario) {
		//gerente comercial
		EscritorioVO vo = new EscritorioVO();

		SingleKPIWidgetVO kpi1 = new SingleKPIWidgetVO();
		kpi1.setStatus("complete");
		kpi1.setTitle("Cadidatos postulados");
		kpi1.setValue(6);

		SingleKPIWidgetVO kpi2 = new SingleKPIWidgetVO();
		kpi2.setStatus("complete");
		kpi2.setTitle("Candidatos en proceso");
		kpi2.setValue(2);
		
		SingleKPIWidgetVO kpi3 = new SingleKPIWidgetVO();
		kpi3.setStatus("complete");
		kpi3.setTitle("Entrevistas Para hoy");
		kpi3.setValue(1);

		BarChartWidgetVO bar2 = new BarChartWidgetVO(); 
		
		bar2.setLabelX("Fase reclutamiento");
		bar2.setLabelY("Candidatos");
		PairedArrayValue value = new PairedArrayValue();
		LinkedList<String> labels = new LinkedList<String>();
		LinkedList<Integer> datasets = new LinkedList<Integer>();
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
		bar2.setValue(value);
		
		/*
		/// STACKED 
		
				StackedChartWidgetVO bar = new StackedChartWidgetVO();
				bar.setLabelX("Reclutadores");
				bar.setLabelY("Candidatos");
				StackedChartValue value = new StackedChartValue();
				
				StackedDataset ds1 = new StackedDataset();

				ds1.setLabel("1eras entrevistas");
				ds1.getData().add(1000);
				ds1.getData().add(1500);
				ds1.getData().add(700);
				ds1.getData().add(300);
				ds1.getData().add(1500);
				ds1.getData().add(200);
				ds1.getData().add(1000);
				

				
				StackedDataset ds2 = new StackedDataset();
				ds2.setLabel("Exámenes");
				ds2.getData().add(200);
				ds2.getData().add(100);
				ds2.getData().add(400);
				ds2.getData().add(200);
				ds2.getData().add(900);
				ds2.getData().add(100);
				ds2.getData().add(50);



				StackedDataset ds3 = new StackedDataset();

				ds3.setLabel("Segundas Entrevistas");
				ds3.getData().add(300);
				ds3.getData().add(400);
				ds3.getData().add(200);
				ds3.getData().add(100);
				ds3.getData().add(400);
				ds3.getData().add(500);
				ds3.getData().add(90);

				
				value.getLabels().add("R1");
				value.getLabels().add("R2");
				value.getLabels().add("R3");
				value.getLabels().add("R4");
				value.getLabels().add("R5");
				value.getLabels().add("R6");
				
				value.getDatasets().add(ds1);
				value.getDatasets().add(ds2);
				value.getDatasets().add(ds3);
				
				bar.setValue(value);
				*/
		

		vo.getWidgets().add(kpi1);
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(bar2);

		return vo;
	}
	

	public RecruiterCorpDeskResponse __getRecruiterCorpData(String idUsuario) {
		RecruiterCorpDeskResponse response = new RecruiterCorpDeskResponse();
		KPIEscritorio entrevistasHoy = new KPIEscritorio();
		entrevistasHoy.setTitulo("Entrevistas para hoy");
		entrevistasHoy.setValor(6);
		response.setEntrevistasHoy(entrevistasHoy);
		KPIEscritorio nuevosPostulados = new KPIEscritorio();
		nuevosPostulados.setTitulo("Nuevos postulados");
		nuevosPostulados.setValor(6);
		response.setNuevosPostulados(nuevosPostulados);
		KPIEscritorio vacantesAbiertas = new KPIEscritorio();
		vacantesAbiertas.setTitulo("Vacantes Abiertas");
		vacantesAbiertas.setValor(6);
		response.setVacantesAbiertas(vacantesAbiertas);
		
		DatosGrafica datosGrafica = new DatosGrafica();
		datosGrafica.setEjeY("Candidatos");
		datosGrafica.setEjeX("Fase de reclutamiento");
		
		LinkedList<String> datos = new LinkedList<String>();
		String e1 = "PRIMERAS";
		datos.add(e1);
		String  e2 = "PRIMERAS";
		datos.add(e2);
		String e3 = "SEGUNDAS";
		datos.add(e3);
		String e4 = "FILTRO";
		datos.add(e4);
		String e5 = "CONTRATACIÓN";
		datos.add(e5);
		String e6 = "CONTRATACIÓN";
		datos.add(e6);
		
		
		datosGrafica.setDatos(datos );
		
		
		response.setCandidatosEtapa(datosGrafica);
		
		return response ;
	}
	

	public AdministratorCorpDeskResponse __getAdministratorCorpData(String idUsuario) {
		EscritorioVO vo = new EscritorioVO();
		
		//KPI

		SingleKPIWidgetVO kpi1 = new SingleKPIWidgetVO();

		kpi1.setStatus("complete");

		kpi1.setTitle("Candidatos Postulados");

		kpi1.setValue(6);

		SingleKPIWidgetVO kpi2 = new SingleKPIWidgetVO();

		kpi2.setStatus("complete");

		kpi2.setTitle("Candidatos en Proceso");

		kpi2.setValue(2);



		SingleKPIWidgetVO kpi3 = new SingleKPIWidgetVO();

		kpi3.setStatus("complete");

		kpi3.setTitle("Vacantes Abiertas");

		kpi3.setValue(6);


		SingleKPIWidgetVO kpi4 = new SingleKPIWidgetVO();

		kpi4.setStatus("complete");

		kpi4.setTitle("Plazas Abiertas");

		kpi4.setValue(2);



		//DONUT


		DonutChartWidgetVO piebar = new DonutChartWidgetVO();



		piebar.setTitle("Candidatos en Proceso");

		PairedArrayValue valuep = new PairedArrayValue();

		valuep.getLabels().add("Aspirantes");

		valuep.getLabels().add("En proceso");

		valuep.getLabels().add("Concluido");

		valuep.getLabels().add("Rechazados");



		valuep.getDatasets().add(60);

		valuep.getDatasets().add(52);

		valuep.getDatasets().add(28);

		valuep.getDatasets().add(54);



		piebar.setValue(valuep);

		
		/// STACKED 
		
		StackedChartWidgetVO bar = new StackedChartWidgetVO();
		bar.setLabelX("Reclutadores");
		bar.setLabelY("Candidatos");
		StackedChartValue value = new StackedChartValue();
		
		StackedDataset ds1 = new StackedDataset();

		ds1.setLabel("1eras entrevistas");
		ds1.getData().add(1000);
		ds1.getData().add(1500);
		ds1.getData().add(700);
		ds1.getData().add(300);
		ds1.getData().add(1500);
		ds1.getData().add(200);
		ds1.getData().add(1000);
		

		
		StackedDataset ds2 = new StackedDataset();
		ds2.setLabel("Exámenes");
		ds2.getData().add(200);
		ds2.getData().add(100);
		ds2.getData().add(400);
		ds2.getData().add(200);
		ds2.getData().add(900);
		ds2.getData().add(100);
		ds2.getData().add(50);



		StackedDataset ds3 = new StackedDataset();

		ds3.setLabel("Segundas Entrevistas");
		ds3.getData().add(300);
		ds3.getData().add(400);
		ds3.getData().add(200);
		ds3.getData().add(100);
		ds3.getData().add(400);
		ds3.getData().add(500);
		ds3.getData().add(90);

		
		value.getLabels().add("R1");
		value.getLabels().add("R2");
		value.getLabels().add("R3");
		value.getLabels().add("R4");
		value.getLabels().add("R5");
		value.getLabels().add("R6");
		
		value.getDatasets().add(ds1);
		value.getDatasets().add(ds2);
		value.getDatasets().add(ds3);
		
		bar.setValue(value);

	
		vo.getWidgets().add(kpi1);
		vo.getWidgets().add(kpi2);
		vo.getWidgets().add(piebar);
		vo.getWidgets().add(bar);
		vo.getWidgets().add(kpi3);
		vo.getWidgets().add(kpi4);

		

		AdministratorCorpDeskResponse response = new AdministratorCorpDeskResponse();
		
		KPIEscritorio candidatosPostulados = new KPIEscritorio();
		candidatosPostulados.setTitulo("Candidatos Postulados");
		candidatosPostulados.setValor(2);
		response.setCandidatosPostulados(candidatosPostulados);
		
		KPIEscritorio candidatosProceso = new KPIEscritorio();
		candidatosProceso.setTitulo("Candidatos en proceso");
		candidatosProceso.setValor(6);
		response.setCandidatosProceso(candidatosProceso);
		
		DatosGrafica candidatosProcesoGrafica = new DatosGrafica();
		candidatosProcesoGrafica.setTitulo("Candidatos en proceso");
		LinkedList<String> datos = new LinkedList<String>();
		String e1 = "En proceso";
		datos.add(e1);
		String  e2 = "En proceso";
		datos.add(e2);
		String e3 = "Completado";
		datos.add(e3);
		String e4 = "Completados";
		datos.add(e4);
		String e5 = "En proceso";
		datos.add(e5);
		String e6 = "Rechazados";
		datos.add(e6);
		
		candidatosProcesoGrafica.setDatos(datos);
		response.setCandidatosProcesoGrafica(candidatosProcesoGrafica);
		
		DatosCompuestosGrafica etapasReclutamiento = new DatosCompuestosGrafica();
		etapasReclutamiento.setEjeX("Fase de reclutamiento");
		etapasReclutamiento.setEjeY("Candidatos");
		LinkedList<DatoCompuesto> datos2 = new LinkedList<DatoCompuesto>();
		DatoCompuesto x1 = new DatoCompuesto();
		x1.setPivote("R1");
		x1.setDato("Primeras entrevistas");
		datos2.add(x1);
		DatoCompuesto x2= new DatoCompuesto();
		x2.setPivote("R1");
		x2.setDato("Primeras entrevistas");
		datos2.add(x2);
		DatoCompuesto x3 = new DatoCompuesto();
		x3.setPivote("R1");
		x3.setDato("Primeras entrevistas");
		datos2.add(x3);
		DatoCompuesto x4= new DatoCompuesto();
		x4.setPivote("R2");
		x4.setDato("Primeras entrevistas");
		datos2.add(x4);
		DatoCompuesto x5= new DatoCompuesto();
		x5.setPivote("R2");
		x5.setDato("Primeras entrevistas");
		datos2.add(x5);
		DatoCompuesto x6= new DatoCompuesto();
		x6.setPivote("R1");
		x6.setDato("Segundas entrevistas");
		datos2.add(x6);
		DatoCompuesto x7= new DatoCompuesto();
		x7.setPivote("R2");
		x7.setDato("Segundas entrevistas");
		datos2.add(x7);
		DatoCompuesto x8= new DatoCompuesto();
		x8.setPivote("R1");
		x8.setDato("Examenes");
		datos2.add(x8);
		DatoCompuesto x9= new DatoCompuesto();
		x9.setPivote("R2");
		x9.setDato("Examenes");
		datos2.add(x9);
		DatoCompuesto x10= new DatoCompuesto();
		x10.setPivote("R2");
		x10.setDato("Examenes");
		datos2.add(x10);
		DatoCompuesto x11= new DatoCompuesto();
		x11.setPivote("R1");
		x11.setDato("Primeras entrevistas");
		datos2.add(x11);
		DatoCompuesto x12= new DatoCompuesto();
		x12.setPivote("R3");
		x12.setDato("Primeras entrevistas");
		datos2.add(x12);
		etapasReclutamiento.setDatos(datos2);
		response.setEtapasReclutamiento(etapasReclutamiento);
		
		KPIEscritorio plazasAbiertas = new KPIEscritorio();
		plazasAbiertas.setTitulo("Plazas abiertas");
		plazasAbiertas.setValor(2);
		response.setPlazasAbiertas(plazasAbiertas);
		
		KPIEscritorio vacantesAbiertas = new KPIEscritorio();
		vacantesAbiertas.setTitulo("Vacantes abiertas");
		vacantesAbiertas.setValor(3);
		response.setVacantesAbiertas(vacantesAbiertas);
		
		return response ;
	}

	public ConsultorCommDeskResponse __getCommConsultorData(String idUsuario) {
		ConsultorCommDeskResponse r = new ConsultorCommDeskResponse();
		
		KPIEscritorio plazasAbiertas = new KPIEscritorio();
		plazasAbiertas.setTitulo("Plazas abiertas");
		plazasAbiertas.setValor(2);
	
		KPIEscritorio candidatosPostulados = new KPIEscritorio();
		candidatosPostulados.setTitulo("Candidatos Postulados");
		candidatosPostulados.setValor(2);
		
		KPIEscritorio candidatosProceso = new KPIEscritorio();
		candidatosProceso.setTitulo("Candidatos en proceso");
		candidatosProceso.setValor(7);
		
		DatosCompuestosGrafica etapasReclutamiento = new DatosCompuestosGrafica();
		etapasReclutamiento.setEjeX("Fase de reclutamiento");
		etapasReclutamiento.setEjeY("Candidatos");
		LinkedList<DatoCompuesto> datos2 = new LinkedList<DatoCompuesto>();
		DatoCompuesto x1 = new DatoCompuesto();
		x1.setPivote("R1");
		x1.setDato("Primeras entrevistas");
		datos2.add(x1);
		DatoCompuesto x2= new DatoCompuesto();
		x2.setPivote("R1");
		x2.setDato("Primeras entrevistas");
		datos2.add(x2);
		DatoCompuesto x3 = new DatoCompuesto();
		x3.setPivote("R1");
		x3.setDato("Primeras entrevistas");
		datos2.add(x3);
		DatoCompuesto x4= new DatoCompuesto();
		x4.setPivote("R2");
		x4.setDato("Primeras entrevistas");
		datos2.add(x4);
		DatoCompuesto x5= new DatoCompuesto();
		x5.setPivote("R2");
		x5.setDato("Primeras entrevistas");
		datos2.add(x5);
		DatoCompuesto x6= new DatoCompuesto();
		x6.setPivote("R1");
		x6.setDato("Segundas entrevistas");
		datos2.add(x6);
		DatoCompuesto x7= new DatoCompuesto();
		x7.setPivote("R2");
		x7.setDato("Segundas entrevistas");
		datos2.add(x7);
		DatoCompuesto x8= new DatoCompuesto();
		x8.setPivote("R1");
		x8.setDato("Examenes");
		datos2.add(x8);
		DatoCompuesto x9= new DatoCompuesto();
		x9.setPivote("R2");
		x9.setDato("Examenes");
		datos2.add(x9);
		DatoCompuesto x10= new DatoCompuesto();
		x10.setPivote("R2");
		x10.setDato("Examenes");
		datos2.add(x10);
		DatoCompuesto x11= new DatoCompuesto();
		x11.setPivote("R1");
		x11.setDato("Primeras entrevistas");
		datos2.add(x11);
		DatoCompuesto x12= new DatoCompuesto();
		x12.setPivote("R3");
		x12.setDato("Primeras entrevistas");
		datos2.add(x12);
		etapasReclutamiento.setDatos(datos2);
		
		
		r.setCandidatosPostulados(candidatosPostulados);
		r.setCandidatosProceso(candidatosProceso);
		r.setPlazasAbiertas(plazasAbiertas);
		r.setEtapasReclutamiento(etapasReclutamiento);
		return r;
	}

	public AnalystCommDeskResponse __getCommAnalystData(String idUsuario) {
		AnalystCommDeskResponse r = new AnalystCommDeskResponse();
		// TODO Auto-generated method stub
		
		KPIEscritorio plazasAbiertas = new KPIEscritorio();
		plazasAbiertas.setTitulo("Plazas abiertas");
		plazasAbiertas.setValor(2);
		
		KPIEscritorio nuevasPostulados = new KPIEscritorio();
		nuevasPostulados.setTitulo("Nuevos Postulados");
		nuevasPostulados.setValor(19);
		
		KPIEscritorio entrevistasClientes  = new KPIEscritorio();
		entrevistasClientes.setTitulo("Entrevistas Clientes para hoy");
		entrevistasClientes.setValor(8);
		
		KPIEscritorio misentrevistasHoy = new KPIEscritorio();
		misentrevistasHoy.setTitulo("Mis entrevistas para hoy");
		misentrevistasHoy.setValor(8);
		
		KPIEscritorio entrevistasHoy = new KPIEscritorio();
		entrevistasHoy.setTitulo("Entrevistas para hoy");
		entrevistasHoy.setValor(16);
		
		DatosGrafica datosGrafica = new DatosGrafica();
		datosGrafica.setEjeY("Candidatos");
		datosGrafica.setEjeX("Fase de reclutamiento");
	
		
		LinkedList<String> datos = new LinkedList<String>();
		String e1 = "PRIMERAS";
		datos.add(e1);
		String  e2 = "PRIMERAS";
		datos.add(e2);
		String e3 = "SEGUNDAS";
		datos.add(e3);
		String e4 = "FILTRO";
		datos.add(e4);
		String e5 = "CONTRATACIÓN";
		datos.add(e5);
		String e6 = "CONTRATACIÓN";
		datos.add(e6);
		
		
		r.setEntrevistasClientesHoy(entrevistasClientes);
		
		r.setEntrevistasHoy(misentrevistasHoy);
	
		r.setNuevosPostulados(nuevasPostulados);
		r.setPlazasAbiertas(plazasAbiertas);
		
		r.setCandidatosEtapa(datosGrafica);
		return r ;
	}

	public ObserverCommDeskResponse __getCommObserver(String idUsuario) {
		ObserverCommDeskResponse r = new ObserverCommDeskResponse();
		
	

		KPIEscritorio candidatosProceso = new KPIEscritorio();
		candidatosProceso.setTitulo("Candidatos en proceso");
		candidatosProceso.setValor(7);
		
		KPIEscritorio entrevistasHoy = new KPIEscritorio();
		entrevistasHoy.setTitulo("Entrevistas para hoy");
		entrevistasHoy.setValor(6);
		
		r.setCandidatosProceso(candidatosProceso);
		r.setEntrevistasHoy(entrevistasHoy);
		
		DatosGrafica datosGrafica = new DatosGrafica();
		datosGrafica.setEjeY("Candidatos");
		datosGrafica.setEjeX("Fase de reclutamiento");
	
		
		LinkedList<String> datos = new LinkedList<String>();
		String e1 = "PRIMERAS";
		datos.add(e1);
		String  e2 = "PRIMERAS";
		datos.add(e2);
		String e3 = "SEGUNDAS";
		datos.add(e3);
		String e4 = "FILTRO";
		datos.add(e4);
		String e5 = "CONTRATACIÓN";
		datos.add(e5);
		String e6 = "CONTRATACIÓN";
		datos.add(e6);
		r.setCandidatosEtapa(datosGrafica);
		return r ;
	}

	public AdministratorCommDeskResponse __getAdministratorCommData(String idUsuario) {
		AdministratorCommDeskResponse r = new AdministratorCommDeskResponse();
		// TODO Auto-generated method stub
		KPIEscritorio plazasAbiertas = new KPIEscritorio();
		plazasAbiertas.setTitulo("Plazas abiertas");
		plazasAbiertas.setValor(2);
		
		KPIEscritorio candidatosProceso = new KPIEscritorio();
		candidatosProceso.setTitulo("Candidatos en proceso");
		candidatosProceso.setValor(2);
		
		KPIEscritorio candidatosPostulados = new KPIEscritorio();
		candidatosPostulados.setTitulo("Candidatos Postulados");
		candidatosPostulados.setValor(2);
		
		r.setPlazasAbiertas(plazasAbiertas);  
		r.setCandidatosPostulados(candidatosPostulados);

		DatosGrafica postulacionesDia = new DatosGrafica();
		postulacionesDia.setTitulo("Candidatos en proceso");
		LinkedList<String> datos2 = new LinkedList<String>();
		String f1 = "Domingo";
		datos2.add(f1);
		String  f2 = "Lunes";
		datos2.add(f2);
		String f3 = "Martes";
		datos2.add(f3);
		String f4 = "Miércoles";
		datos2.add(f4);
		String f5 = "Jueves";
		datos2.add(f5);
		String f6 = "Viernes";
		datos2.add(f6);
		String f7 = "Lunes";
		datos2.add(f7);
		String f8 = "Lunes";
		datos2.add(f8);
		String f9 = "Martes";
		datos2.add(f9);
		postulacionesDia.setDatos(datos2);
		r.setPostulacionesDia(postulacionesDia);
		
		r.setCandidatosProceso(candidatosProceso);
		DatosGrafica candidatosProcesoGrafica = new DatosGrafica();
		candidatosProcesoGrafica.setTitulo("Candidatos en proceso");
		LinkedList<String> datos = new LinkedList<String>();
		String e1 = "En proceso";
		datos.add(e1);
		String  e2 = "En proceso";
		datos.add(e2);
		String e3 = "Completado";
		datos.add(e3);
		String e4 = "Completados";
		datos.add(e4);
		String e5 = "En proceso";
		datos.add(e5);
		String e6 = "Rechazados";
		datos.add(e6);
		
		candidatosProcesoGrafica.setDatos(datos);
		r.setCandidatosProcesoGrafica(candidatosProcesoGrafica);
		return r;
	}

	public ObserverCorpDeskResponse __getObserverCorpData(String idUsuario) {
		ObserverCorpDeskResponse response = new ObserverCorpDeskResponse();
		
		KPIEscritorio candidatosPrimerasEntrevistas = new KPIEscritorio();
		candidatosPrimerasEntrevistas.setTitulo("Candidadatos en Primeras Entrevistas");
		candidatosPrimerasEntrevistas.setValor(12);
		response.setCandidatosPrimerasEntrevistas(candidatosPrimerasEntrevistas);
		
		KPIEscritorio vacantesObservadas = new KPIEscritorio();
		candidatosPrimerasEntrevistas.setTitulo("Vacantes Observadas");
		candidatosPrimerasEntrevistas.setValor(1);
		
		DatosGrafica candidatosProceso = new DatosGrafica();
		candidatosProceso.setTitulo("Candidatos en proceso");
		LinkedList<String> datos = new LinkedList<String>();
		String e1 = "En proceso";
		datos.add(e1);
		String  e2 = "En proceso";
		datos.add(e2);
		String e3 = "Completado";
		datos.add(e3);
		String e4 = "Completados";
		datos.add(e4);
		String e5 = "En proceso";
		datos.add(e5);
		String e6 = "Rechazados";
		datos.add(e6);
		candidatosProceso.setDatos(datos);
		response.setCandidatosProceso(candidatosProceso);
		
		
		response.setVacantesObservadas(vacantesObservadas);
		response.setCandidatosProceso(candidatosProceso);
		// TODO Auto-generated method stub
		return response ;
	}

	public CustomerRecruiterCorpDeskResponse __getCustomerRecruiterCorpData(String idUsuario) {
		CustomerRecruiterCorpDeskResponse r = new CustomerRecruiterCorpDeskResponse();
		
		KPIEscritorio candidatosProceso = new KPIEscritorio();
		candidatosProceso.setTitulo("Candidatos en proceso");
		candidatosProceso.setValor(7);
		
		KPIEscritorio entrevistasHoy = new KPIEscritorio();
		entrevistasHoy.setTitulo("Entrevistas para hoy");
		entrevistasHoy.setValor(6);
		
	
		r.setNuevosPostulados(candidatosProceso);
		r.setEntrevistasHoy(entrevistasHoy);
		
		DatosGrafica datosGrafica = new DatosGrafica();
		datosGrafica.setEjeY("Candidatos");
		datosGrafica.setEjeX("Fase de reclutamiento");
	
		
		LinkedList<String> datos = new LinkedList<String>();
		String e1 = "PRIMERAS";
		datos.add(e1);
		String  e2 = "PRIMERAS";
		datos.add(e2);
		String e3 = "SEGUNDAS";
		datos.add(e3);
		String e4 = "FILTRO";
		datos.add(e4);
		String e5 = "CONTRATACIÓN";
		datos.add(e5);
		String e6 = "CONTRATACIÓN";
		datos.add(e6);
		datosGrafica.setDatos(datos);
		r.setCandidatosEtapa(datosGrafica);
		return r ;
	}
	
	@Override
	public RecruiterCorpDeskResponse __getRecruiterCorpData(RequestBean<String> request) {
		// TODO Auto-generated method stub
		String idUsuario = request.getEntityPayload();
		return this.__getRecruiterCorpData(idUsuario);
	}
	@Override
	public AdministratorCorpDeskResponse __getAdministratorCorpData(RequestBean<String> request) {
		// TODO Auto-generated method stub
		String idUsuario = request.getEntityPayload();
		return this.__getAdministratorCorpData(idUsuario);
	}
	@Override
	public CustomerRecruiterCorpDeskResponse __getCustomerRecruiterCorpData(RequestBean<String> request) {
		// TODO Auto-generated method stub
		String idUsuario = request.getEntityPayload();
		return this.__getCustomerRecruiterCorpData(idUsuario);
	}
	@Override
	public ObserverCorpDeskResponse __getObserverCorpData(RequestBean<String> request) {
		// TODO Auto-generated method stub
		String idUsuario = request.getEntityPayload();
		return this.__getObserverCorpData(idUsuario);
	}
	@Override
	public AdministratorCommDeskResponse __getAdministratorCommData(RequestBean<String> request) {
		// TODO Auto-generated method stub
		String idUsuario = request.getEntityPayload();
		return this.__getAdministratorCommData(idUsuario);
	}
	@Override
	public ConsultorCommDeskResponse __getCommConsultorData(RequestBean<String> request) {
		// TODO Auto-generated method stub
		String idUsuario = request.getEntityPayload();
		return this.__getCommConsultorData(idUsuario);
	}
	@Override
	public AnalystCommDeskResponse __getCommAnalystData(RequestBean<String> request) {
		// TODO Auto-generated method stub
		String idUsuario = request.getEntityPayload();
		return this.__getCommAnalystData(idUsuario);
	}
	@Override
	public ObserverCommDeskResponse __getCommObserver(RequestBean<String> request) {
		// TODO Auto-generated method stub
		String idUsuario = request.getEntityPayload();
		return this.__getCommObserver(idUsuario);
	}





}
