package com.mx.profuturo.bolsa.model.graphics.adapters;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class StackedChartAdapter {
	
	private String axisX;
	private String axisY;
	private String title;
	private LinkedList<String> ms = new LinkedList<>();
	private HashMap<String, HashMap<String, Integer>> ns = new LinkedHashMap<>();
	private HashMap<String, LinkedList<Integer>> bars = new LinkedHashMap<>();
	private HashMap<String, Integer> counter = new LinkedHashMap<>();

	private String m;
	private String n;
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
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public void compute(String m, String n) {
		
		Integer i = 0, c = 0;
	
		if(null == this.ns.get(n)) {
			this.ns.put(n, new LinkedHashMap<String, Integer>());
		}
		
		if(null == this.counter.get(m)) {
			this.counter.put(m, 0);
		}
		
		
		if(null == this.ns.get(n).get(m)) {
			this.ns.get(n).put(m, 0);
		}

		c = this.counter.get(m);
		this.counter.put(m, ++c);
		i = this.ns.get(n).get(m);
		this.ns.get(n).put(m, ++i);

		
	}
	
	public void sortCounter() {
		this.counter = this.sortByValue(this.counter);
	}

	public void computeBars() {
		this.sortCounter();
		String m = null;
		String n = null;
		Integer i;
		for(Entry<String, Integer> entry : this.counter.entrySet()) {
			m = entry.getKey();
			this.ms.add(m);
			for(Entry<String, HashMap<String, Integer>> subentry : this.ns.entrySet()) {
				n = subentry.getKey();
				if(null == subentry.getValue().get(m)) {
					i = 0;
				}else {
					i = subentry.getValue().get(m);
				}
				
				if(null == this.bars.get(n)) {
					this.bars.put(n, new LinkedList<Integer>());
				}
				
				this.bars.get(n).add(i);
			}
		}
	}
	
	
	/*
	public void computeBars_(Integer maxM, String others) {
		LinkedList<String> selected = new LinkedList<>();
		Integer j = 0;
		for (Entry<String, HashMap<String, Integer>> entry : this.tmp.entrySet()) {
			this.bars.put(entry.getKey(), entry.getValue());
			selected.add(entry.getKey());
			if(++j >= maxM) {
				break;
			}
		}
		
		for(int i = 0; i < selected.size(); i++) {
			this.tmp.remove(i);
		}
		
		if(0 < this.tmp.size()) {
			this.generateOthersBar(others);
		}
	}
	
	private void generateOthersBar(String others) {
		
		
		for(Entry<String, HashMap<String, Integer>> entry : this.tmp.entrySet()) {
			if(null != entry.getValue()) {
				for(Entry<String, Integer> subentry : entry.getValue().entrySet() ) {
					this.computeBarForOthers(subentry.getKey(), subentry.getValue());
				}
			}
		}
		
		this.bars.put(others, this.othersBar);
		
	}
	
	
	private void computeBarForOthers(String m, Integer n) {
		Integer i = 0, c = 0;
		
		if(null == this.othersBar.get(m)) {
			this.othersBar.put(m,n);
		}else {
			i = this.othersBar.get(m);
			this.othersBar.put(m, (n + i));
		}
	}
	
	*/
	
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
	public HashMap<String, HashMap<String, Integer>> getNs() {
		return ns;
	}
	public void setNs(HashMap<String, HashMap<String, Integer>> ns) {
		this.ns = ns;
	}
	public HashMap<String, Integer> getCounter() {
		return counter;
	}
	public void setCounter(HashMap<String, Integer> counter) {
		this.counter = counter;
	}
	public HashMap<String, LinkedList<Integer>> getBars() {
		return bars;
	}
	public void setBars(HashMap<String, LinkedList<Integer>> bars) {
		this.bars = bars;
	}
	public LinkedList<String> getMs() {
		return ms;
	}
	public void setMs(LinkedList<String> ms) {
		this.ms = ms;
	} 
	
	

}
