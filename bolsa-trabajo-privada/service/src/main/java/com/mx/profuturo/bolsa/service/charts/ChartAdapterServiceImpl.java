package com.mx.profuturo.bolsa.service.charts;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.graphics.adapters.AreaChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.BarChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.DonutChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.StackedChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.vo.AreaChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.AreaDataSet;
import com.mx.profuturo.bolsa.model.graphics.vo.BarChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.DonutChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.LineChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.SingleKPIWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.StackedChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.elements.PairedArrayValue;
import com.mx.profuturo.bolsa.model.graphics.vo.elements.StackedChartValue;
import com.mx.profuturo.bolsa.model.graphics.vo.elements.StackedDataset;
import com.mx.profuturo.bolsa.service.desk.KPIEscritorio;

@Service
@Scope("request")
public class ChartAdapterServiceImpl implements ChartAdapterService{
	
	@Override
	public BarChartWidgetVO getBarChartWidget(BarChartAdapter adapter) {
		BarChartWidgetVO bar = new BarChartWidgetVO();
		
		
		bar.setLabelX(adapter.getAxisX());
		bar.setLabelY(adapter.getAxisY());
		PairedArrayValue value = new PairedArrayValue();
		LinkedList<String> labels = new LinkedList<String>();
		LinkedList<Integer> datasets = new LinkedList<Integer>();
		
		for (Entry<String, Integer> entry : adapter.getBars().entrySet()) {
		    labels.add(entry.getKey());
		    datasets.add(entry.getValue());    
		}
		
		value.setLabels(labels);
		value.setDatasets(datasets);
		bar.setValue(value);
		
		return bar;
	}
	
	
	@Override
	public StackedChartWidgetVO getStackedChartWidget(StackedChartAdapter adapter) {

		StackedChartWidgetVO bar = new StackedChartWidgetVO();
		bar.setLabelX(adapter.getAxisX());
		bar.setLabelY(adapter.getAxisY());
		StackedChartValue value = new StackedChartValue();
		value.setLabels(adapter.getMs());
		
		for(Entry<String, LinkedList<Integer>> finalEntry:adapter.getBars().entrySet()) {
			
			StackedDataset ds = new StackedDataset();
			ds.setLabel(finalEntry.getKey());
			ds.setData(finalEntry.getValue());
			value.getDatasets().add(ds);
		}
		
		bar.setValue(value);
		return bar;
	}


	public StackedChartWidgetVO getStackedChartWidget_(StackedChartAdapter adapter) {

		StackedChartWidgetVO bar = new StackedChartWidgetVO();
		bar.setLabelX(adapter.getAxisX());
		bar.setLabelY(adapter.getAxisY());
		StackedChartValue value = new StackedChartValue();
			
		HashMap<String, LinkedList <Integer>> auxDataSet = new HashMap<>();
		/*for(Entry<String, HashMap<String, Integer>> entry: adapter.getBars().entrySet()) {
			value.getLabels().add(entry.getKey());
			for(Entry<String, Integer> subentry:entry.getValue().entrySet()) {
				if(null == auxDataSet.get(subentry.getKey())) {
					auxDataSet.put(subentry.getKey(), new LinkedList<Integer>());
				}
				auxDataSet.get(subentry.getKey()).add(subentry.getValue());
			}
		}*/
		
		for(Entry<String, LinkedList<Integer>> finalEntry:auxDataSet.entrySet()) {
			
			StackedDataset ds = new StackedDataset();
			ds.setLabel(finalEntry.getKey());
			ds.setData(finalEntry.getValue());
			value.getDatasets().add(ds);
		}
		
		bar.setValue(value);
		return bar;
	}


	@Override
	public DonutChartWidgetVO getDonutChartWidget(DonutChartAdapter adapter) {
		DonutChartWidgetVO vo = new DonutChartWidgetVO();
		vo.setTitle(adapter.getTitle());
		PairedArrayValue valuep = new PairedArrayValue();
		for(Entry<String, Integer> entry:adapter.getParts().entrySet()) {
			valuep.getLabels().add(entry.getKey());
			valuep.getDatasets().add(entry.getValue());
		}
		vo.setValue(valuep);
		return vo;		
	}


	@Override
	public AreaChartWidgetVO getAreaChartWidget(AreaChartAdapter adapter) {
		AreaChartWidgetVO vo = new AreaChartWidgetVO();	
		

		//dataSet.setFill(true);
		//dataSet.setLabel("LABEL");
		Integer i = 0;
		PairedArrayValue valuep = new PairedArrayValue();
		for(String entry:adapter.getLabelsOrder()) {
			valuep.getLabels().add(entry);
			i = (null== adapter.getParts().get(entry))? 0: adapter.getParts().get(entry);
			valuep.getDatasets().add(i);
		}
		vo.setValue(valuep);
		return vo;
	}


	@Override
	public Object getLineChartWidget(AreaChartAdapter adapter) {
		LineChartWidgetVO bar = new LineChartWidgetVO();
		bar.setLabelX(adapter.getAxisX());
		bar.setLabelY(adapter.getAxisY());
		PairedArrayValue value = new PairedArrayValue();
		LinkedList<String> labels = new LinkedList<String>();
		LinkedList<Integer> datasets = new LinkedList<Integer>();
		
		Integer i = 0;
		for(String entry:adapter.getLabelsOrder()) {
			
			i = (null== adapter.getParts().get(entry))? 0: adapter.getParts().get(entry);
		    labels.add(entry);
		    datasets.add(i);    
		}
		
		value.setLabels(labels);
		value.setDatasets(datasets);
		bar.setValue(value);
		
		return bar;
	}


	@Override
	public SingleKPIWidgetVO translateKPI(KPIEscritorio kpi) {
		SingleKPIWidgetVO vo = new SingleKPIWidgetVO();
		vo.setStatus("complete");
		if(kpi!= null) {
			vo.setTitle(kpi.getTitulo());
			vo.setValue(kpi.getValor());
		}
		return vo;
	}
	
	

}
