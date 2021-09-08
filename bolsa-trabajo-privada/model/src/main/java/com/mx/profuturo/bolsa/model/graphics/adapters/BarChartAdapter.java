package com.mx.profuturo.bolsa.model.graphics.adapters;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class BarChartAdapter {
	
	private String axisX;
	private String axisY;
	private String title;
	private HashMap<String, Integer> bars = new LinkedHashMap<>();
	private HashMap<String, Integer> barChartCounter = new LinkedHashMap<>();
	private HashMap<String, Integer> sortedBarChartCounter = new LinkedHashMap<>();
	private Integer totalHits;
	
	public String getAxisX() {
		return axisX;
	}
	public void setAxisX(String axisX) {
		this.axisX = axisX;
	}
	public String getAxisY() {
		return axisY;
	}
	public void setAxisY(String axisY) {
		this.axisY = axisY;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HashMap<String, Integer> getBars() {
		return bars;
	}
	public void setBars(HashMap<String, Integer> bars) {
		this.bars = bars;
	}
	
	
	
	public HashMap<String, Integer> getBarChartCounter() {
		return barChartCounter;
	}
	public void setBarChartCounter(HashMap<String, Integer> barChartCounter) {
		this.barChartCounter = barChartCounter;
	}
	public Integer getTotalHits() {
		return totalHits;
	}
	public void setTotalHits(Integer totalHits) {
		this.totalHits = totalHits;
	}
	public void  barChartCount(String hit) {
		
		if(null == barChartCounter.get(hit)) {
			barChartCounter.put(hit, 1);
		}else {
			Integer count = barChartCounter.get(hit);
			count++;
			barChartCounter.put(hit, count);
		}
	}
	
	public void assignBarMembers(int limit, String otros) {
		this.sortedBarChartCounter = this.sortByValue(this.barChartCounter);
		int i = 0;
		int count = 0;
		for (Entry<String, Integer> entry : this.sortedBarChartCounter.entrySet()) {  
			if(i<limit) {
				this.bars.put(entry.getKey(), entry.getValue());
				count+=entry.getValue();
			}else
				break;
		}
		if(count<this.totalHits) {
			this.bars.put(otros, (this.totalHits - count));
		}
	}
	
	public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        LinkedList<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
	

}
