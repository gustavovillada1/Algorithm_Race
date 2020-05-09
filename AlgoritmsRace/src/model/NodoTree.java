package model;

public class NodoTree {
        public BinaryTree hijoDer;
        public BinaryTree hijoIZq;
        public long dato;
 
       public NodoTree(){
            hijoDer = null;
            hijoIZq = null;
            dato = 0;
        }

	public BinaryTree getHijoDer() {
		return hijoDer;
	}

	public void setHijoDer(BinaryTree hd) {
		this.hijoDer = hd;
	}

	public BinaryTree getHijoIzq() {
		return hijoIZq;
	}

	public void setHijoIzq(BinaryTree hi) {
		this.hijoIZq = hi;
	}

	public long getDato() {
		return dato;
	}

	public void setDato(long dato) {
		this.dato = dato;
	}
       
       
       
       
    }