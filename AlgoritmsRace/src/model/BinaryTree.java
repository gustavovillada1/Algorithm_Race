package model;
/**
 * @author Gustavo Adolfo Villada Molina - Universidad ICESI
 */

public class BinaryTree{
 
    public NodoTree raiz;
 
    public BinaryTree(){
        NodoTree raiz = new NodoTree();
    }
 
    public boolean isEmpty(){
        return (raiz == null);
    }
 
    public void add(long a){
        if (isEmpty()) {
            NodoTree newnodo = new NodoTree();
            newnodo.dato=a;
            newnodo.hijoDer=new BinaryTree();
            newnodo.hijoIZq=new BinaryTree();
            raiz = newnodo;
        }
        else {
            if (a > raiz.dato) {
                (raiz.hijoDer).add(a);
            }
            if (a < raiz.dato){
                (raiz.hijoIZq).add(a);
            }
        }
    }
    
    /*
     private void add( NodoTree nodo, NodoTree raiz ) {
        if ( raiz == null ) {

            this.setRaiz(nodo);
        }
        else {
            if ( nodo.getDato() <= raiz.getDato() ) {

                if (raiz.getHijoIzq() == null) {
                    raiz.setHijoIzq(nodo);
                }
                else {
                    addNodo( nodo , raiz.getHojaIzquierda() );
                }
            }
            else {

                if (raiz.getHojaDerecha() == null) {
                    raiz.setHojaDerecha(nodo);
                }
                else {
                    addNodo( nodo, raiz.getHojaDerecha() );
                }
            }
        }
    }

*/
/*
    public void addNodo( Nodo nodo ) {
        this.add( nodo , this.raiz );
    }
   */ 
    
    
 
    public BinaryTree search(long a){
        BinaryTree arbolito = null;
        if (!isEmpty()) {
            if (a == raiz.getDato()) {
            return this;
            }
            else {
                if (a < raiz.getDato()) {
                    arbolito = raiz.getHijoIzq().search(a);
                }
                else {
                    arbolito = raiz.getHijoDer().search(a);
                }
            }
        }
        return arbolito;
    }
 
    public boolean existe(int a){
    if (!isEmpty()) {
            if (a == raiz.getDato()) {
            return true;
            }
            else {
                if (a < raiz.getDato()) {
                    raiz.getHijoIzq().existe(a);
                }
                else {
                    raiz.getHijoDer().existe(a);
                }
            }
        }
        return false;
    }
 
    public int cantidad(){
        if (isEmpty()) {
            return 0;
        }
        else {
            return (1 + raiz.getHijoDer().cantidad() + raiz.getHijoIzq().cantidad());
        }
    }

    public long buscarMin() {
        BinaryTree arbolActual = this;
        while( !arbolActual.raiz.getHijoIzq().isEmpty() ) {
            arbolActual = arbolActual.raiz.getHijoIzq();
        }
        long devuelvo= arbolActual.raiz.getDato();
        arbolActual.raiz=null;
        return devuelvo;
    }

    public boolean esHoja() {
        boolean hoja = false;
        if( (raiz.getHijoIzq()).isEmpty() && (raiz.getHijoDer()).isEmpty() ) {
            hoja = true;
        }
        return hoja;
    }
 
    public void delete(long a) {
        BinaryTree deletethis = search(a);
        if (!deletethis.isEmpty()) {
        	
            if (deletethis.esHoja()) {
                deletethis.raiz = null;
            }else {
            	
                if (!deletethis.raiz.getHijoIzq().isEmpty() && !deletethis.raiz.getHijoDer().isEmpty()) {
                    deletethis.raiz.setDato( deletethis.raiz.getHijoDer().buscarMin());
                }else {
                	
                    if (deletethis.raiz.getHijoIzq().isEmpty()) {
                        deletethis.raiz = deletethis.raiz.getHijoDer().raiz;
                    }else{
                        deletethis.raiz = deletethis.raiz.getHijoIzq().raiz;
                    }
                }
            }
        }
    }

	public NodoTree getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoTree raiz) {
		this.raiz = raiz;
	}
    
    
    
}