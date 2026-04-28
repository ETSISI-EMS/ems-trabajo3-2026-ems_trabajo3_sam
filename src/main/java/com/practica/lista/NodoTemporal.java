package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

import java.util.LinkedList;


/**
 * Nodo para guardar un instante de tiempo. Además guardamos una lista con las coordeandas
 * y las personas (solo número) que en ese instante están en una coordeanda en concreto  
 *
 */
public class NodoTemporal {
	private LinkedList<NodoPosicion> listaCoordenadas;
	private FechaHora fecha;
	
	
	public NodoTemporal() {
		super();
		//siguiente = null;
		listaCoordenadas = new LinkedList<>();
	}

	public static NodoTemporal nodoFromPosition(PosicionPersona p){
		NodoTemporal nodoTemporal = new NodoTemporal();
		nodoTemporal.setFecha(p.getFechaPosicion());
		NodoPosicion nodoPosicion = new NodoPosicion(p.getCoordenada(), 1); //insertas una persona
		nodoTemporal.listaCoordenadas.add(nodoPosicion);
		return nodoTemporal;
	}

	public void combineNodes(NodoTemporal other) {
		if (!this.fecha.equals(other.fecha)) {
			return;
		}

		for (NodoPosicion it: other.listaCoordenadas) {
			int pos = listaCoordenadas.indexOf(it);

			if (pos == -1) {
				listaCoordenadas.add(new NodoPosicion(it));
			} else {
				listaCoordenadas.get(pos).combine(it);
			}
		}
	}

	public FechaHora getFecha() {
		return fecha;
	}
	public void setFecha(FechaHora fecha) {
		this.fecha = fecha;
	}

	public LinkedList<NodoPosicion> getListaCoordenadas() {
		return listaCoordenadas;
	}

	@Override
	public String toString() {
		return fecha.getFecha().toString() + ";" + fecha.getHora().toString();
	}

}
