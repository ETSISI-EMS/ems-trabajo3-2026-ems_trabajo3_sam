package com.practica.ems.covid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsDuplicatePersonException;
import com.practica.excecption.EmsInvalidNumberOfDataException;
import com.practica.excecption.EmsInvalidTypeException;
import com.practica.excecption.EmsLocalizationNotFoundException;
import com.practica.excecption.EmsPersonNotFoundException;
import com.practica.genericas.FechaHora;
import com.practica.genericas.Persona;
import com.practica.genericas.PosicionPersona;
import com.practica.lista.ListaContactos;

public class ContactosCovid {
	private Poblacion poblacion;
	private Localizacion localizacion;
	private ListaContactos listaContactos;

	public ContactosCovid() {
		this.poblacion = new Poblacion();
		this.localizacion = new Localizacion();
		this.listaContactos = new ListaContactos();
	}

	public Poblacion getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	public ListaContactos getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(ListaContactos listaContactos) {
		this.listaContactos = listaContactos;
	}

	public void loadData(String data, boolean reset) throws EmsInvalidTypeException, EmsInvalidNumberOfDataException,
			EmsDuplicatePersonException, EmsDuplicateLocationException {
		if(reset){
			resetData();
		}
		String[] datas = dividirEntrada(data);
		readData(datas);
	}

	public void loadDataFile(String fichero, boolean reset) {
		BufferedReader br = null;
		String[] datas;
		String data;
		
		try {
			br = new BufferedReader(new FileReader(fichero));
			if(reset){
				resetData();
			}
			while ((data = br.readLine()) != null) {
				datas = dividirEntrada(data.trim());
				readData(datas);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void resetData() {
		this.poblacion = new Poblacion();
		this.localizacion = new Localizacion();
		this.listaContactos = new ListaContactos();
	}

	private void readData(String[] datas) throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException {
		for (String linea : datas) {
			String[] datos = this.dividirLineaData(linea);

			if (datos[0].equals("PERSONA")) {
				this.poblacion.addPersona(Persona.crearPersona(datos));
			}else if (datos[0].equals("LOCALIZACION")) {
				PosicionPersona pp = PosicionPersona.crearPosicionPersona(datos);
				this.localizacion.addLocalizacion(pp);
				this.listaContactos.insertarNodoTemporal(pp);
			} else {
				throw new EmsInvalidTypeException();
			}
		}
	}

	public int findPersona(String documento) throws EmsPersonNotFoundException {
		return this.poblacion.findPersona(documento);
	}

	public int findLocalizacion(String documento, String fecha, String hora) throws EmsLocalizationNotFoundException {
		FechaHora fechaHora = new FechaHora(FechaHora.Fecha.parseFecha(fecha), FechaHora.Hora.parseHora(hora));
		return this.localizacion.findLocalizacion(documento, fechaHora);
	}

	public List<PosicionPersona> localizacionPersona(String documento) throws EmsPersonNotFoundException {
		return this.localizacion.localizacionPersona(documento);
	}

	public boolean delPersona(String documento) throws EmsPersonNotFoundException {
		this.poblacion.delPersona(documento);
		return false;
	}

	private String[] dividirEntrada(String input) {
        return input.split("\\n");
	}

	private String[] dividirLineaData(String data) {
        return data.split("\\;");
	}
}
