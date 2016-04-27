package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.ParolaDAO;

public class DizionarioModel {
	
	
	private List<String>elencoParole=new ArrayList<String>();
	ParolaDAO parDao=new ParolaDAO();
	protected SimpleGraph<String, DefaultEdge> wordGraph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	
	
	public void loadWords(int n){
		elencoParole.addAll(parDao.trovaParole(n));	
		//AGGIUNGI VERTICI A GRAFO
		for(String s:elencoParole)
			wordGraph.addVertex(s);	
		
		
	
			
		
		
		for(String s:elencoParole){	
			
			for(String word:parDao.trovaParoleSimili(jolly(s))){
				wordGraph.addEdge(s, word);
			}
			System.out.println(s);
		}
		
		System.out.println(wordGraph.edgeSet());
		
	}
	
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
	

}
