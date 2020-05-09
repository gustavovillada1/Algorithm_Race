package model;

import java.util.Observable;

public class Circlex extends Observable implements Runnable{

	
	public Circlex() {

	}
	
	private Thread hilo;
	private boolean agrandando;
	private int radio;
	public void startThread() {
		hilo=new Thread(this); 
		hilo.start();
	}
	
	public void setRadio(int t) {
		this.radio=t;
	}
	@Override
	public void run() {
		int uno=1;
		agrandando=false;
		
			try {

				while(uno==1) {
					Thread.sleep(50);

				if(radio==36) {
					radio--;
					agrandando=false;
				}else if(radio==1){
					radio++;
					agrandando=true;
				}else {
					if(agrandando==true) {
						radio++;
					}else {
						radio--;
					}	
				}
				
				this.setChanged();
				this.notifyObservers(radio);
				this.clearChanged();
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
	}

}
