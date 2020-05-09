package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.ArrayListRace;
import model.BinaryTree;
import model.BinaryTreeRace;
import model.Circlex;
import model.Cronometro2;
import model.LinkedListRace;
import model.LinkedListt;

/**
 * @author Gustavo Adolfo Villada Molina - Universidad ICESI
 */

public class WindowController implements Initializable, Observer{

    @FXML
    private ProgressBar progressArrayList;

    @FXML
    private ProgressBar progressLinkedList;

    @FXML
    private ProgressBar progressABB;

    @FXML
    private ComboBox<String> cbAlgorithm;

    @FXML
    private ComboBox<String> cbMode;

    @FXML
    private Button run;

    @FXML
    private Label timekeeper;

    @FXML
    private Label finalResultArrayList;

    @FXML
    private Label finalResultLinkedList;

    @FXML
    private Label finalResultABB;

    @FXML
    private TextField textF;
    
    @FXML
    private Circle circle2;

    @FXML
    private Circle circle1;

    
    //Clases que modificaran el circulo del GUI.
    private Circlex circlex;
    private Circlex circlex1;

    //Clases que serán recurrentes mientras la carrera esté activa.
    private Cronometro2 cronom;
    private ArrayListRace arraylistrace;
    private LinkedListRace linkedlistrace;
    private LinkedListRace binarytreerace;
    
    //Variables globales para reducir la cantidad de modificaciones del GUI y evitar que se paralice.
     int n;
     int cantidad;
     int rep;
     byte terminatedRace;

    @FXML
    void startCompetition(ActionEvent event) {
    	System.out.println("---------");
    	finalResultABB.setText("00:00:00");
    	finalResultArrayList.setText("00:00:00");
    	finalResultLinkedList.setText("00:00:00");
    	terminatedRace=0;
       	try {
	
    	int n=Integer.parseInt(textF.getText());
    	String algorithm=cbAlgorithm.getSelectionModel().getSelectedItem();
    	String mode=cbMode.getSelectionModel().getSelectedItem();
    	
		run.setDisable(false);
		cronom=new Cronometro2();
		cronom.addObserver(this);

		ArrayListRace arraylist=new ArrayListRace(algorithm, mode, n);
		arraylist.addObserver(this);
		arraylistrace=arraylist;

		BinaryTreeRace binarytreerace=new BinaryTreeRace(algorithm, mode, n);
		binarytreerace.addObserver(this);
		arraylistrace=arraylist;
		
		LinkedListRace linkedlist=new LinkedListRace(algorithm, mode, n);
		linkedlist.addObserver(this);
		linkedlistrace=linkedlist;
		
		
		
		this.n=n;
		this.cantidad=cuantasVeces();
		
		if(cbAlgorithm.getSelectionModel().getSelectedItem()==null||cbMode.getSelectionModel().getSelectedItem()==null) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Ha ocurrido un error .");
			alert.setTitle("Opps.");
			alert.showAndWait();
		}else {

		cronom.iniciarCronometro();
		arraylistrace.startThread();
		linkedlistrace.startThread();
//		binarytreerace.startThread();  // Me alcanzó el time y tengo que ganar una materia ahí :'c
		run.setDisable(true);   

		}
		
    	}catch(Exception e) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Ha ocurrido un error");
			alert.setTitle("Opps.");
			alert.showAndWait();
			
    	}

    
    }
    
   


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Circulo que iniciará aumentando su tamaño.
		circlex=new Circlex();
		circlex.setRadio(1);
		circlex.addObserver(this);
		circlex.startThread();		
		//Circulo que iniciará reduciendo su tamaño.
		circlex1=new Circlex();
		circlex1.setRadio(36);
		circlex1.addObserver(this);
		circlex1.startThread();
		
		//Se rellena el combobox del tipo de algoritmo. 
		List<String> typeAlgorit=new ArrayList<String>();
		ObservableList<String> ta;
		typeAlgorit.add("ADD");
		typeAlgorit.add("SEARCH");
		typeAlgorit.add("DELETE");	
		ta=FXCollections.observableArrayList(typeAlgorit);
		cbAlgorithm.setItems(ta);
		
		//Se rellena el combobox del modo de la carrera.
		List<String> typeMode=new ArrayList<String>();
		ObservableList<String> tm;
		typeMode.add("ITERATIVE");
		typeMode.add("RECURSIVE");	
		tm=FXCollections.observableArrayList(typeMode);
		cbMode.setItems(tm);
		
	}

	/**
	 * Este metodo está encargado de determinar la cantidad de veces que se acumularán las notificaciones de la clase observable
	 * según la cantidad de datos a generar para que el hilo del GUI no se sature y se paralice al momento de modificar algun 
	 * elemento del GUI.
	 * @return cantidad de notificaciones recibidas en el metodo update() antes de realizar una modificación en el GUI.
	 */
	public int cuantasVeces() {
		int re=1;
		if(n<1000) {
			return 1;
		}else {
			re=n/1000;
		}	
		return re;
	}

	

	@Override
	public void update(Observable cron, Object arg1) { 
		rep++;		

		int n = 100;
	if(cron.equals(linkedlistrace)||cron.equals(arraylistrace)) {	
		n=(int) arg1;
	}
		
		if(rep>=cantidad||n==100) {

		Platform.runLater(()->{	
			///AQUI MIRA SI LA NOTIFICACIÓN ES DEL CRONOMETRO
			if(cron==cronom) {
				timekeeper.setText((String)arg1);
				
				
				///AQUI MIRA SI LA NOTIFICACIÓN VIENE DEL ARRAYLISTRACE Y VA AUMENTANDO LA BARRA DE PROGRESO.
		}else if(cron==arraylistrace) {
			
			int p=(int)arg1;
			progressArrayList.setProgress((double)p/100);
			
			if((int)arg1==100) {
		    	finalResultArrayList.setText(timekeeper.getText());
		    	terminatedRace++;
			}
			
			///AQUI MIRA SI LA NOTIFICACIÓN VIENE DEL LINKEDLISTRACE Y VA AUMENTANDO LA BARRA DE PROGRESO.
		}else if(cron==linkedlistrace){
			int p=(int)arg1;
			progressLinkedList.setProgress((double)p/100);
			
			if((int)arg1==100) {
		    	finalResultLinkedList.setText(timekeeper.getText());
		    	terminatedRace++;
			}
			
			///AQUI MIRA SI LA NOTIFICACIÓN VIENE DEL BINARYTREERACE Y VA AUMENTANDO LA BARRA DE PROGRESO.
		}else if(cron==binarytreerace){
			int p=(int)arg1;
			progressABB.setProgress((double)p/100);
			
			if((int)arg1==100) {
		    	finalResultABB.setText(timekeeper.getText());
		    	terminatedRace++;
			}
		}else if(cron==circlex1){
			int d=(int)arg1;
			circle1.setRadius((double) d);
			
			}else {
			int d=(int)arg1;
			circle2.setRadius((double) d);
			
			}
		});
		
		rep=0;
		if(terminatedRace==2) {
	    	cronom.detenerCronometro();
	    	run.setDisable(false);	
	    	terminatedRace=0;
		}
	
		}		
	}




}
