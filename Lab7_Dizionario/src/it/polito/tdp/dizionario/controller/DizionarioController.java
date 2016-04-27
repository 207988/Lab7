package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.DizionarioModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	
	DizionarioModel diz=new DizionarioModel();
	
	public void setModel(DizionarioModel diz){
		this.diz=diz;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGenera;

    @FXML
    private Button btnTrova;

    @FXML
    private Button btnConnessi;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Button btnReset;

    @FXML
    void doConnessi(ActionEvent event) {

    }

    @FXML
    void doGenera(ActionEvent event) { 
    	txtOutput.clear();
    	int i=-1;
    	try{
    		i=Integer.parseInt(txtNumero.getText());  
    	}
    	catch(NumberFormatException nfe){
    		txtOutput.setText("Inserisci un numero di lettere valido");
    		return;
    	}
    	if(i!=-1)
    		diz.loadWords(i);

    }

    @FXML
    void doReset(ActionEvent event) {
    	diz.clear();
    }

    @FXML
    void doTrova(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtNumero != null : "fx:id=\"txtNumero\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnGenera != null : "fx:id=\"btnGenera\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnTrova != null : "fx:id=\"btnTrova\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnConnessi != null : "fx:id=\"btnConnessi\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Dizionario.fxml'.";

    }
}


