package com.practica.lista;

import java.util.ArrayList;

import com.practica.genericas.Coordenada;

/**
 * Nodo para la lista de coordenadas. En el guardamos cuántas personas  están
 * en una coordenada  en un momento temporal. 
 * También guardaremos la lista de personas que están en esa coordenada en un 
 * momento en concreto
 */
public class NodoPosicion {
	private Coordenada coordenada;	
	private int numPersonas;
	//private NodoPosicion siguiente;
	
	
	public NodoPosicion() {
		super();
	}


	public NodoPosicion(Coordenada coordenada,  int numPersonas) {
		super();
		this.coordenada = coordenada;		
		this.numPersonas = numPersonas;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public void combine(NodoPosicion other) {
		if (this.coordenada.equals(other.coordenada))
			this.numPersonas += other.numPersonas;
	}

	public NodoPosicion(NodoPosicion np) {
		this.coordenada = new Coordenada(np.coordenada);
		this.numPersonas = np.numPersonas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		NodoPosicion other = (NodoPosicion) obj;
		return this.coordenada.equals(other.coordenada);
	}

	
}
