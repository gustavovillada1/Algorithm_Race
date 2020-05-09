package model;

public class Nodo {

		public long dato;
		Nodo next, prev;
		
		public Nodo(Long dat, Nodo nex, Nodo pre) {
			this.dato=dat;
			this.next=nex;
			this.prev=pre;
			
		}

		public long getDato() {
			return dato;
		}

		public void setDato(int dato) {
			this.dato = dato;
		}

		public Nodo getNext() {
			return next;
		}

		public void setNext(Nodo next) {
			this.next = next;
		}

		public Nodo getPrev() {
			return prev;
		}

		public void setPrev(Nodo prev) {
			this.prev = prev;
		}
		
		
		
}
