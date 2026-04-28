package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

import java.util.LinkedList;

public class ListaContactos {
	private LinkedList<NodoTemporal> listaContactos;

	public ListaContactos() {
		this.listaContactos = new LinkedList<>();
	}
	//private NodoTemporal lista;
	//private int size;
	
	/**
	 * Insertamos en la lista de nodos temporales, y a la vez inserto en la lista de nodos de coordenadas. 
	 * En la lista de coordenadas metemos el documento de la persona que está en esa coordenada 
	 * en un instante 
	 */

	public void insertarNodoTemporal (PosicionPersona p) {
		NodoTemporal nodoT = NodoTemporal.nodoFromPosition(p);
		if(listaContactos.isEmpty()){
			listaContactos.add(nodoT);
			return;
		}
		int i = 0;
		boolean encontrado = false;

		while (i < listaContactos.size() && !encontrado) {
			NodoTemporal actual = listaContactos.get(i);

			if (actual.getFecha().compareTo(nodoT.getFecha()) == 0) {
				actual.combineNodes(nodoT);
				encontrado = true;
			} else {
				i++;
			}
		}

		if (!encontrado) {
			insertInPosition(nodoT);
		}

	}

	
	public int tamanioLista () {
		return this.listaContactos.size();
	}

	public String getPrimerNodo() {
		return listaContactos.getFirst().toString();
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */

	public int recorrerEntreInstantes(FechaHora inicio, FechaHora fin, boolean personas) {
		if (listaContactos.isEmpty()) {
			return 0;
		}

		int cont = 0;

		for (NodoTemporal actual : listaContactos) {
			if (actual.getFecha().compareTo(inicio) >= 0 &&
					actual.getFecha().compareTo(fin) <= 0) {

				for (NodoPosicion nodo : actual.getListaCoordenadas()) {
					if (personas) {
						cont += nodo.getNumPersonas();
					} else {
						cont++;
					}
				}
			}
		}

		return cont;
	}

	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		return recorrerEntreInstantes(inicio, fin, true);
	}
	
	
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		return recorrerEntreInstantes(inicio, fin, false);
	}



	@Override
	public String toString() {
		String cadena = "";
		int cont = 0;

		for (NodoTemporal aux : listaContactos) {
			cadena += aux.getFecha().getFecha().toString();
			cadena += ";" + aux.getFecha().getHora().toString();

			cont++;
			if (cont < listaContactos.size()) {
				cadena += " ";
			}
		}

		return cadena;
	}

	private void insertInPosition(NodoTemporal nodoTemporal) {
		if (listaContactos.contains(nodoTemporal)) {
			throw new IllegalArgumentException("Date is already in the list");
		}

		int index = 0;

		for (NodoTemporal actual : listaContactos) {

				if (nodoTemporal.getFecha().compareTo(actual.getFecha()) < 0) {
					break;
				}
				index++;

		}

		if (index == listaContactos.size()) {
			listaContactos.addLast(nodoTemporal);
		} else {
			listaContactos.add(index, nodoTemporal);
		}
	}

	public LinkedList<NodoTemporal> getListaContactos() {
		return listaContactos;
	}

	
	
}
