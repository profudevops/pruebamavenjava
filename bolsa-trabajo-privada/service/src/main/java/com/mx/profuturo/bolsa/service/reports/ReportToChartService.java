package com.mx.profuturo.bolsa.service.reports;

import java.util.LinkedList;


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

public interface ReportToChartService {

	BarChartAdapter translateRejectReasonChart(LinkedList<MotivosRechazoVO> rows);

	StackedChartAdapter translateRejectReasonByStepChart(LinkedList<MotivosRechazoVO> rows);

	StackedChartAdapter translateAbandonmentReasonChart(LinkedList<MotivosRechazoVO> rows);

	DonutChartAdapter translateFinalistVsHiredChart(LinkedList<FinalistasContratadosVO> rows);

	BarChartAdapter translatePositionLevelHiringsChart(LinkedList<ContratacionesNivelPuestoVO> rows);

	AreaChartAdapter translateHiringsPerMonthChart(LinkedList<ContratacionesMesVO> rows);

	BarChartAdapter translateHiringsPerAnalystChart(LinkedList<ContratacionesAnalistaVO> rows);

	StackedChartAdapter translateRecruitmentSourcesChart(LinkedList<FuentesReclutamientoVO> rows);

	StackedChartAdapter translateInterviewTypesChart(LinkedList<TiposEntrevistaVO> rows);

	BarChartAdapter translateRejectStageChart(LinkedList<MotivosRechazoVO> rows);

	

}
