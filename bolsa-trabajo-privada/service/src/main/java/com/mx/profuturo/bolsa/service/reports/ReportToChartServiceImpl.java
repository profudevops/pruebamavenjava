package com.mx.profuturo.bolsa.service.reports;

import java.util.HashMap;
import java.util.LinkedList;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import com.mx.profuturo.bolsa.model.graphics.adapters.AreaChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.BarChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.DonutChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.StackedChartAdapter;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesAnalistaVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesMesVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesNivelPuestoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.FinalistasContratadosVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.FuentesReclutamientoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.TiposEntrevistaVO;

@Service
@Scope("request")
public class ReportToChartServiceImpl implements ReportToChartService{
	
	HashMap<String, Integer> barChartCounter;
	
	private MonthAux monthAux = new MonthAux();

	public BarChartAdapter translateRejectReasonChart(LinkedList<MotivosRechazoVO> rows) {
		BarChartAdapter adapter = new BarChartAdapter();
		Integer max = rows.size();
		adapter.setAxisX("Motivos");
		adapter.setAxisY("Cantidad");
		adapter.setTitle("Motivos de rechazo");
		adapter.setTotalHits(max);
		for(int i = 0; i < max; i++) {
			adapter.barChartCount(rows.get(i).getMotivo());
		}
		adapter.assignBarMembers(6, "Otros");
		return adapter ;
	}
	
	@Override
	public BarChartAdapter translateRejectStageChart(LinkedList<MotivosRechazoVO> rows) {
		BarChartAdapter adapter = new BarChartAdapter();
		Integer max = rows.size();
		adapter.setAxisX("Etapas");
		adapter.setAxisY("Cantidad");
		adapter.setTitle("Etapas de rechazo");
		adapter.setTotalHits(max);
		for(int i = 0; i < max; i++) {
			adapter.barChartCount(rows.get(i).getEtapa());
		}
		adapter.assignBarMembers(6, "Otros");
		return adapter ;
	}
	
	@Override
	public  StackedChartAdapter translateRejectReasonByStepChart(LinkedList<MotivosRechazoVO> rows) {
		StackedChartAdapter adapter = new StackedChartAdapter();
		Integer max = rows.size();
		adapter.setAxisX("Motivos");
		adapter.setAxisY("Cantidad");
		adapter.setTitle("Motivos de Rechazo por Etapa");
		for(int i = 0; i < max; i++) {
			adapter.compute(rows.get(i).getMotivo(),rows.get(i).getEtapa());
		}
		adapter.computeBars();
		return adapter;
	}

	
	@Override
	public  StackedChartAdapter translateAbandonmentReasonChart(LinkedList<MotivosRechazoVO> rows) {
		StackedChartAdapter adapter = new StackedChartAdapter();
		Integer max = rows.size();
		adapter.setAxisX("Etapa");
		adapter.setAxisY("Cantidad");
		adapter.setTitle("Etapa de Deserción");
		for(int i = 0; i < max; i++) {
			adapter.compute(rows.get(i).getMotivo(),rows.get(i).getEtapa());
		}
		adapter.computeBars();
		return adapter;
	}

	@Override
	public DonutChartAdapter translateFinalistVsHiredChart(LinkedList<FinalistasContratadosVO> rows) {
		DonutChartAdapter adapter = new DonutChartAdapter();
		for(int i = 0; i < rows.size(); i++) {
			adapter.compute(rows.get(i).getStatus());
		}
		return adapter;
	}

	@Override
	public BarChartAdapter translatePositionLevelHiringsChart(LinkedList<ContratacionesNivelPuestoVO> rows) {
		BarChartAdapter adapter = new BarChartAdapter();
		Integer max = rows.size();
		adapter.setAxisX("Nivel de Puesto");
		adapter.setAxisY("Cantidad");
		adapter.setTitle("Contrataciones por nivel de puesto");
		adapter.setTotalHits(max);
		for(int i = 0; i < max; i++) {
			adapter.barChartCount(rows.get(i).getNivelPuesto());
		}
		adapter.assignBarMembers(6, "Otros");
		return adapter ;
	}

	@Override
	public AreaChartAdapter translateHiringsPerMonthChart(LinkedList<ContratacionesMesVO> rows) {
		AreaChartAdapter adapter = new AreaChartAdapter();
		adapter.setAxisX("Mes");
		adapter.setAxisY("Contrataciones");
		adapter.setLabelsOrder(monthAux.getMonths());
		for(int i = 0; i < rows.size(); i++) {
			adapter.compute(rows.get(i).getMes());
		}
		return adapter;
	}

	@Override
	public BarChartAdapter translateHiringsPerAnalystChart(LinkedList<ContratacionesAnalistaVO>  rows) {
		BarChartAdapter adapter = new BarChartAdapter();
		Integer max = rows.size();
		adapter.setAxisX("Contrataciones");
		adapter.setAxisY("Cantidad");
		adapter.setTitle("Contrataciones por Responsable");
		adapter.setTotalHits(max);
		for(int i = 0; i < max; i++) {
			adapter.barChartCount(rows.get(i).getAnalista());
		}
		adapter.assignBarMembers(6, "Otros");
		return adapter ;
	}

	@Override
	public StackedChartAdapter translateRecruitmentSourcesChart(LinkedList<FuentesReclutamientoVO> rows) {
		StackedChartAdapter adapter = new StackedChartAdapter();
		Integer max = rows.size();
		adapter.setAxisX("Fuentes");
		adapter.setAxisY("Fuentes de reclutamiento");
		adapter.setTitle("titulo");
		for(int i = 0; i < max; i++) {
			adapter.compute(rows.get(i).getFuente(),rows.get(i).getStatus());
		}
		adapter.computeBars();
		return adapter;
	}

	@Override
	public StackedChartAdapter translateInterviewTypesChart(LinkedList<TiposEntrevistaVO> rows) {
		StackedChartAdapter adapter = new StackedChartAdapter();
		Integer max = rows.size();
		adapter.setAxisX("Tipos de entrevista");
		adapter.setAxisY("Cantidad");
		adapter.setTitle("Tipos de entrevista");
		for(int i = 0; i < max; i++) {
			adapter.compute(rows.get(i).getTipo(),rows.get(i).getStatus());
		}
		adapter.computeBars();
		return adapter;
	}
	


	
}
