package model;

public class LinkedListt  {
	    	
    private Nodo raiz;
    private Nodo tail;

    private int size;

    public LinkedListt () {
	    raiz=null;
	    size=0;
    }
      
    public void add(Long value) {
        Nodo node = new Nodo(value, null, null);
        if (tail == null && raiz == null) {
            tail = node;
            raiz = node;
        } else {
            node.setNext(raiz);
            raiz.setPrev(node);
            raiz = node;
        }
    }
    
  
    public void search(long valor){
        if (raiz != null){
                   Nodo aux = raiz;
                 
                   int cont = 0;
                   while (aux != null){
                               if (aux.getDato() == valor ){
                                           cont++;
                                           aux = aux.getNext();
                                         
                               }                                 
                   }                     
             }
        }

    
    public void remove(int index){ 	
    	
        if (raiz != null){
             Nodo aux = raiz;
             Nodo ant = null;
             while (aux != null){
                    if (aux.getDato() == index ){
                       if (ant == null){
                           raiz = raiz.getNext();
                           aux.setNext(null);
                           aux= raiz;
                                          
                           }else {
                                 ant.setNext(aux.getNext());
                                 aux.setNext(null);
                                 aux = ant.getNext();
                            }                                             
                            }else{
                                 ant = aux;
                                 aux = aux.getNext();
                            }
                   }
         }
}
    
    public Long get(int index) {
    	Long number = null;
    	Nodo start=raiz;
    	if(index<size) {
    		int cont=0;
    		while(cont<=index) {
    			cont++;
    			start=start.getNext();
    			if(cont==index) {
    				number=start.getDato();
    			}
    		}
    	}
    	
    	return number;
    }
    
    

    public boolean isEmpty() {
        if (raiz == null) {
            return true;
        }else {
            return false;
        }
    }
   
    public int size() {
    	return size;
    }


    

	}
	
