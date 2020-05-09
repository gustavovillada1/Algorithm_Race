package model;



import java.util.Observable;

import javafx.scene.control.Label;

/**
 * @author Gustavo Adolfo Villada Molina - Universidad ICESI
 */

public class Cronometro2 extends Observable implements Runnable {
	
    private boolean cronometroActivo;
    private String time;
    private Thread hilo;

    public Cronometro2() {
	    cronometroActivo=false;
    }   

	public void detenerCronometro() {
    	cronometroActivo=false;
    }
	
    public void iniciarCronometro() {
        cronometroActivo = true;
        hilo = new Thread( this );
        hilo.start();
    }
  

	@Override
	public void run() {
		time="";
	       Integer minutos = 0 , segundos = 0, milesimas = 0;
	        String min="", seg="", mil="";
	        try
	        {
	            while( cronometroActivo )
	            {
	                Thread.sleep( 4 );
	                milesimas += 10;
	                if( milesimas == 1000 )
	                {
	                    milesimas = 0;
	                    segundos += 1;
	                    if( segundos == 60 ){
	                        segundos = 0;
	                        minutos++;
	                    }
	                }
	 
	                if( minutos < 10 ) {
	                	min = "0" + minutos;
	                }else {
	                	min = minutos.toString();
	                }
	                if( segundos < 10 ) {
	                	seg = "0" + segundos;
	                }else {
	                	seg = segundos.toString();
	                }
	                 
	                if( milesimas < 10 ) mil = "00" + milesimas;
	                else if( milesimas < 100 ) mil = "0" + milesimas;
	                else mil = milesimas.toString();
	                
	                this.setChanged();
	                this.notifyObservers(time);
	                this.clearChanged();	                 
	                time= min + ":" + seg + ":" + mil ;   
	                
	                
	            }
	        }catch(Exception e){}
	        time= "00:00:000";
	}
	

	

}
