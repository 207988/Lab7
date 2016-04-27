package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ParolaDAO {
	private String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";	
	
	public List<String> trovaParole(int n){
		
		List<String>elencoParole=new ArrayList<String>();		
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			String sql= "SELECT nome FROM parola WHERE LENGTH(nome)=?;";			
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1,n);
			
			ResultSet res=st.executeQuery();
			
			while(res.next()){
				elencoParole.add(res.getString("nome"));
			}
			
			res.close();
			conn.close();
			return elencoParole;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
		
	}
	
public List<String> trovaParoleSimili(List<String>temp){
		
		List<String>elencoParole=new ArrayList<String>();		
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			String sql= "SELECT nome FROM parola WHERE nome LIKE ?;";
			
			for(String s:temp){
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1,s);
				
				
				ResultSet res=st.executeQuery();
				
				while(res.next()){
					String word=res.getString("nome");
					if(!elencoParole.contains(word))
						elencoParole.add(word);
				}
				res.close();
			}
			
			
			conn.close();
			return elencoParole;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
		
	}
}