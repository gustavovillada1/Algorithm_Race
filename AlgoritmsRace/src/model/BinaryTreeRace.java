package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Gustavo Adolfo Villada Mlina
 */

public class BinaryTreeRace extends Observable implements Runnable{

	//Atributos de la clase
	
	private BinaryTree binaryTree;
	private String algorithm;
	private String mode;
	private int n;
	private int progress;
	private boolean empty;
	
	/**
	 * Metodo Constructor de la clase BinaryTreeRace
	 * @param a Algoritmo por el cual se realizará la operación.
	 * @param m Modo por el cual se realizará el algoritmo.
	 * @param n Cantidad de elementos que estarán involucrados en la operación.
	 */
	public BinaryTreeRace(String a,String m, int n) {
		binaryTree=new BinaryTree();
		empty=binaryTree.isEmpty();
		this.algorithm=a;
		this.mode=m;
		this.n=n;
	}
	
	Thread thread;

	/**
	 * Este metodo se encarga de iniciar el hilo, el cual iniciará la operación de acuerdo a los parametros ingresados 
	 * en el método constructor. 
	 */
	public void startThread() {
		thread=new Thread(this);
		thread.start();
	}
	
	/**
	 * Metodo que verifica si la estructura de datos se encuentra vacia.
	 * @return
	 */
	public boolean getEmpty() {
		return empty;
	}
	
	/**
	 * Metodo encargado de calcular el porcentaje de datos realizados en la operación, para luego notificarlo a la clase que modifica el
	 * GUI para que este vaya modificando la barra de progreso.
	 */
	public void calculatePercentAndNotify() {
		
		int result=(this.progress*100)/this.n;
		this.setChanged();
		this.notifyObservers(result);
		this.clearChanged();
		
	}
	
	@Override
	public void run() {

		if(mode.equals("ITERATIVE")) {
			if(algorithm.equals("ADD")) {
				Long number=Long.MIN_VALUE;
				for(int i=0;i<this.n;i++) {
					number++;
					binaryTree.add(number);
					progress++;
					calculatePercentAndNotify();
				}			
			}else if(algorithm.equals("SEARCH")) {
				if(empty==false) {
				Long number=Long.MIN_VALUE;
				for(int s=0;s<n;s++) {
				
					binaryTree.search(number);
				number++;
				progress++;
				calculatePercentAndNotify();
				}
				}
			}else {
				if(empty==false) {
				Long number=Long.MIN_VALUE;
				for(int d=0;d<n;d++) {

					binaryTree.delete(number);
				}
				number++;
				progress++;
				calculatePercentAndNotify();
				}				
				
			}			
			
		}else {
			if(algorithm.equals("ADD")) {
				Long number=Long.MIN_VALUE;
				for(int i=0;i<this.n;i++) {
					number++;
					binaryTree.add(number);
					progress++;
					calculatePercentAndNotify();
				}				
			}else if(algorithm.equals("SEARCH")) {	
				if(empty==false) {

				Long number1=Long.MIN_VALUE;
				for(int i=0;i<n;i++) {
					progress++;
					binaryTree.search(number1);
					number1++;
				}		
				}
			}else {		
				if(empty==false) {
				Long number1=Long.MIN_VALUE;
				for(int i=0;i<n;i++) {
					progress++;
					binaryTree.delete(number1);
					number1++;
				}
			}
		  }
		}
	
		
	}
	
	
}
