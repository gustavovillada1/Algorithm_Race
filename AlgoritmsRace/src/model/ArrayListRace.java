package model;

import java.util.ArrayList;
import java.util.Observable;
/**
 * @author Gustavo Adolfo Villada Molina - Universidad ICESI
 */

public class ArrayListRace extends Observable implements Runnable{

	private ArrayList<Long> arraylist;
	private String algorithm;
	private String mode;
	private int n;
	private int progress;
	private boolean empty;
	
	/**
	 * Metodo constructor de la clase LinkedListRace
	 * @param a El algoritmo de la operación.
	 * @param m El modo en que se realizará la cantidad de veces deseadas el algoritmo elegido 
	 * @param n	La cantidad de datos que estarán involucrados en el algoritmo con su respectivo modo
	 */
	public ArrayListRace(String a,String m, int n) {
		arraylist=new ArrayList<Long>();
		empty=arraylist.isEmpty();
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
		if(arraylist.isEmpty()==true) {
			return true;
		}else {
		return false;
		}
		
	}
	
	public ArrayList<Long> getArraylist() {
		return arraylist;
	}

	public void setArraylist(ArrayList<Long> arraylist) {
		this.arraylist = arraylist;
	}
	
	public String getMode() {
		return mode;
	}
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * Metodo encargado de buscar de forma recursiva en el ArrayList.
	 * @param searched numero que se desea buscar en el ArrayList.
	 * @param index la posición en el ArrayList donde estará el numero a encontrar .
	 */	public int searchRecursive(long searched, int index) {
		calculatePercentAndNotify();

		if(index>arraylist.size()-1) {
		return -1;	
		}else if(arraylist.get(index)==searched) {
			return index;
		}else {
			return searchRecursive(searched, index+1);
		}
		
	}
	
	/**
	 * Metodo encargado de borrar de forma recursiva en el ArrayList.
	 * @param n cantidad de numeros a eliminar.
	 * @param number1 el numero que se desea eliminar en el ArrayList.
	 */
	public void deleteRecursive(int n, long number1) {
		Long number=number1;
		calculatePercentAndNotify();

		if(n==0) {
			return;
		}else if(arraylist.get(n)==number){
			arraylist.remove(n);
		}else {
			deleteRecursive(n-1,number);		
		}
		
	}

	
	/**
	 * Metodo encargado de calcular el porcentaje de datos realizados en la operación, para luego notificarlo a la clase que modifica el
	 * GUI para que este vaya modificando la barra de progreso.
	 */
	public void calculatePercentAndNotify() {
		
		int result=(this.progress*100)/this.n;
//		System.out.println(result+" %"+progress);
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
					arraylist.add(number);
					progress++;
					calculatePercentAndNotify();
				}			
			}else if(algorithm.equals("SEARCH")) {
				Long number=Long.MIN_VALUE;
				for(int s=0;s<n;s++) {
				
				for(int i=0;i<arraylist.size();i++) {
					if(arraylist.get(i)==number) {						
					}
				}						
				number++;
				progress++;
				calculatePercentAndNotify();
				
				}
			}else {
				Long number=Long.MIN_VALUE;
				for(int d=0;d<n;d++) {

				for(int i=0;i<arraylist.size();i++) {
					if(arraylist.get(i)==number) {
					arraylist.remove(i);
					}
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
					arraylist.add(number);
					progress++;
					calculatePercentAndNotify();
				}				
			}else if(algorithm.equals("SEARCH")) {	

				Long number1=Long.MIN_VALUE;
				for(int i=0;i<n;i++) {
					progress++;
					searchRecursive(number1, 0);
					number1++;
						
				}
			}else {		
				Long number1=Long.MIN_VALUE;
				for(int i=0;i<n;i++) {
					progress++;
					deleteRecursive(0,number1);
					number1++;
				
			}
		  }
		}
	
		
	}
	
	
}
