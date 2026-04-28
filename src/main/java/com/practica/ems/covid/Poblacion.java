package com.practica.ems.covid;

import java.util.LinkedList;
import java.util.List;

import com.practica.excecption.EmsDuplicatePersonException;
import com.practica.excecption.EmsPersonNotFoundException;
import com.practica.genericas.Persona;

public class Poblacion {
	List<Persona> lista ;

	public Poblacion() {
		super();
		this.lista = new LinkedList<Persona>();
	}
	
	public List<Persona> getLista() {
        return this.lista;
	}

	public void addPersona (Persona persona) throws EmsDuplicatePersonException {
		if(lista.contains(persona))
			throw new EmsDuplicatePersonException();
		lista.add(persona);
	}
	
	public void delPersona(String documento) throws EmsPersonNotFoundException {
		Persona persona = new Persona(documento);
		if(!lista.remove(persona)){
			throw new EmsPersonNotFoundException();
		}
	}
	
	public int findPersona (String documento) throws EmsPersonNotFoundException {
		Persona persona = new Persona(documento);
		int pos = lista.indexOf(persona);

		if (pos == -1) {
			throw new EmsPersonNotFoundException();
		}

		return pos + 1;
	}
	
	public void printPoblacion() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for(Persona persona : lista) {
			cadena.append(persona.toString());
			cadena.append("\n");
		}
		//remove last \n
		cadena = new StringBuilder(cadena.substring(0, cadena.length() - 1));
		return cadena.toString();
	}

}
