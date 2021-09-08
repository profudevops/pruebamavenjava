package com.mx.profuturo.bolsa.service.desk;

import java.util.LinkedList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.desk.data.DatoCompuesto;
import com.mx.profuturo.bolsa.model.desk.data.DatosCompuestosGrafica;
import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;
import com.mx.profuturo.bolsa.model.graphics.adapters.AreaChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.BarChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.DonutChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.StackedChartAdapter;

@Service
@Scope("request")
public class DeskDataToChartServiceImpl implements DeskDataToChartService  {
	
	

	@Override
	public StackedChartAdapter translateStackedChart(DatosCompuestosGrafica datos) {
		StackedChartAdapter adapter = new StackedChartAdapter();
		if(datos != null) {
			adapter.setAxisX(datos.getEjeX());
			adapter.setAxisY(datos.getEjeY());
			LinkedList<DatoCompuesto> rows = datos.getDatos();
			if(datos.getDatos() != null) {
				Integer max = datos.getDatos().size();
				for(int i = 0; i < max; i++) {
					adapter.compute(rows.get(i).getPivote(),rows.get(i).getDato());
				}
				adapter.computeBars();
			}
			
		}
		
		return adapter;
	}
	
	

	@Override
	public BarChartAdapter translateBarChart(DatosGrafica  datos) {
		BarChartAdapter adapter = new BarChartAdapter();
		if(datos != null) {
			
			LinkedList<String> rows = datos.getDatos();
			adapter.setAxisX(datos.getEjeX());
			adapter.setAxisY(datos.getEjeY());
			if(datos.getDatos() != null) {
				Integer max = rows.size();
				adapter.setTotalHits(max);
				for(int i = 0; i < max; i++) {
					adapter.barChartCount(rows.get(i));
				}
				adapter.assignBarMembers(6, "Otros");
			}
		}
		return adapter ;
	}


	@Override
	public DonutChartAdapter translateDonutChart(DatosGrafica  datos) {
		DonutChartAdapter adapter = new DonutChartAdapter();
		if(datos != null) {
			adapter.setTitle(datos.getTitulo());
			LinkedList<String> rows = datos.getDatos();
			if(datos.getDatos() != null) {
				for(int i = 0; i < rows.size(); i++) {
					adapter.compute(rows.get(i));
				}
			}
		}
		return adapter;
	}



	@Override
	public AreaChartAdapter translateAreaChart(DatosGrafica datos, LinkedList<String> labels) {
		AreaChartAdapter adapter = new AreaChartAdapter();
		adapter.setAxisX(datos.getEjeX());
		adapter.setAxisY(datos.getEjeY());
		LinkedList<String> rows = datos.getDatos();
		if(datos.getDatos() != null) {
			
			adapter.setLabelsOrder(labels);
			for(int i = 0; i < rows.size(); i++) {
				adapter.compute(rows.get(i));
			}
		}
		return adapter;
	}
	

}
