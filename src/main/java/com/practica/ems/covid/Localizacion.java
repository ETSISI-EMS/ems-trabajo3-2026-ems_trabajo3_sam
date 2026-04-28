package com.practica.ems.covid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsLocalizationNotFoundException;
import com.practica.excecption.EmsPersonNotFoundException;
import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class Localizacion {
	List<PosicionPersona> lista;

	public Localizacion() {
		super();
		this.lista = new LinkedList<PosicionPersona>();
	}

	public void addLocalizacion (PosicionPersona p) throws EmsDuplicateLocationException {
		if(lista.contains(p)) {
			throw new EmsDuplicateLocationException();
		}
		lista.add(p);
	}
	
	public int findLocalizacion (String documento, FechaHora fechaHora) throws EmsLocalizationNotFoundException {
		PosicionPersona pp = new PosicionPersona(documento, fechaHora, null);
		int pos = lista.indexOf(pp);

		if (pos == -1) {
			throw new EmsLocalizationNotFoundException();
		}

		return pos + 1;
	}

	public List<PosicionPersona> localizacionPersona(String documento) throws EmsPersonNotFoundException{
		List<PosicionPersona> result =  new ArrayList<PosicionPersona>();

		for (PosicionPersona pp : this.lista) {
            if (pp.getDocumento().equals(documento)) {
                result.add(pp);
            }
        }
		if (result.isEmpty()) {
			throw new EmsPersonNotFoundException();
		}
		return result;
	}

	public void delLocalizacion(String documento, FechaHora fechaHora) throws EmsLocalizationNotFoundException {
		PosicionPersona pp = new PosicionPersona(documento, fechaHora, null);

		if (!lista.remove(pp)) {
			throw new EmsLocalizationNotFoundException();
		}
	}
	
	void printLocalizacion() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for(PosicionPersona pp : lista) {
	        cadena.append(pp.toString());
			cadena.append("\n");
	    }
		//remove last \n
		cadena = new StringBuilder(cadena.substring(0, cadena.length() - 1));
		return cadena.toString();
	}

}
