package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.ParolaDAO;

public class DizionarioModel {
	
	
	int i=0;
	private List<String>elencoParole=new ArrayList<String>();
	ParolaDAO parDao=new ParolaDAO();
	protected SimpleGraph<String, DefaultEdge> wordGraph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	
	
	public void loadWords(int n){
		elencoParole.addAll(parDao.trovaParole(n));	
		//AGGIUNGI VERTICI A GRAFO
		for(String s:elencoParole)
			wordGraph.addVertex(s);	
		
		System.out.println(wordGraph.vertexSet().size());
	
			
		long time=System.nanoTime();
		
		this.graphRAM();
		
		time=System.nanoTime()-time;
		System.out.format("Tempo esecuzione: %fs\n", time/(1e9));
		System.out.println("Numero archi: "+wordGraph.edgeSet().size());
		
		
	}
	
	//DB VERSION
	public void graphDB(){
		for(String s:elencoParole){			
			for(String word:parDao.trovaParoleSimili(jolly(s))){
				i++;
				if((!wordGraph.containsEdge(s, word))&&(word.compareTo(s)!=0))
					wordGraph.addEdge(s, word);				
			}
			System.out.println(s);
		}
	}
	//DB VERSION
	public List<String> jolly(String s){
		
		List<String>temp=new ArrayList<String>();
		
		for(int i=0;i<s.toCharArray().length;i++){
			StringBuilder sb=new StringBuilder(s);			
			sb.setCharAt(i, '_');
			//System.out.println(sb.toString());
			temp.add(sb.toString());
		}
		return temp;
	}
	
	public void clear(){
		elencoParole.clear();
		
		wordGraph=new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	}
	//RAM VERSION
	public void graphRAM(){
		for(String s1:elencoParole){			
			for(String s2:elencoParole){
				if((!wordGraph.containsEdge(s1, s2))&&(this.checkWord(s1, s2)!=-1))
					wordGraph.addEdge(s1, s2);				
			}			
		}
		
	}
	
	//RAM VERSION
	public int checkWord(String s1, String s2){
		//0 con iniziale
		//1 parole diverse per 1 sola lettera
		//-1 parole diverse per più di 1 lettera
		int res =-1;
		if(s1.compareTo(s2)==0)
			return -1;
		for(int i=0;i<s1.length();i++){
			if(s1.charAt(i)!=s2.charAt(i)){
				if(res==1)
					return -1;
				res = 1;
			}
		}
		return res;
	}
	

}
