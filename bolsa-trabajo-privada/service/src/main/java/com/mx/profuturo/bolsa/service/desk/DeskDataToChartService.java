package com.mx.profuturo.bolsa.service.desk;

import java.util.LinkedList;

import com.mx.profuturo.bolsa.model.desk.data.DatosCompuestosGrafica;
import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;
import com.mx.profuturo.bolsa.model.graphics.adapters.AreaChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.BarChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.DonutChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.StackedChartAdapter;

public interface DeskDataToChartService {
	
	StackedChartAdapter translateStackedChart(DatosCompuestosGrafica datos);

	DonutChartAdapter translateDonutChart(DatosGrafica row);

	BarChartAdapter translateBarChart(DatosGrafica datos);
	
	AreaChartAdapter translateAreaChart(DatosGrafica datos, LinkedList<String> labels);
	
	

}
