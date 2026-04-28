package com.practica.genericas;


import com.practica.excecption.EmsInvalidNumberOfDataException;

public class PosicionPersona {
	private Coordenada coordenada;
	private String documento;
	private FechaHora fechaPosicion;

	public PosicionPersona(String documento, FechaHora fechaPosicion, Coordenada coordenada) {
		this.documento = documento;
		this.fechaPosicion = fechaPosicion;
		this.coordenada = coordenada;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public FechaHora getFechaPosicion() {
		return fechaPosicion;
	}
	public void setFechaPosicion(FechaHora fechaPosicion) {
		this.fechaPosicion = fechaPosicion;
	}

	public static PosicionPersona crearPosicionPersona(String[] datos) throws EmsInvalidNumberOfDataException {
		if (datos.length != Constantes.MAX_DATOS_LOCALIZACION) {
			throw new EmsInvalidNumberOfDataException("El número de datos para LOCALIZACION es menor de 6");
		}
		String documento = datos[1];
		String fecha = datos[2];
		String hora = datos[3];
		FechaHora fechaPosicion = FechaHora.parseFecha(fecha,hora);
		Coordenada coordenada = new Coordenada(Float.parseFloat(datos[4]), Float.parseFloat(datos[5]));
		return new PosicionPersona(documento, fechaPosicion, coordenada);
	}

	@Override
	public String toString() {
		String cadena = "";
        cadena += String.format("%s;", getDocumento());
        FechaHora fecha = getFechaPosicion();        
        cadena+=String.format("%02d/%02d/%04d;%02d:%02d;", 
	        		fecha.getFecha().getDia(), 
	        		fecha.getFecha().getMes(), 
	        		fecha.getFecha().getAnio(),
	        		fecha.getHora().getHora(),
	        		fecha.getHora().getMinuto());
        cadena+=String.format("%.4f;%.4f\n", getCoordenada().getLatitud(), 
	        		getCoordenada().getLongitud());
	
		return cadena;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof PosicionPersona)) {
			return false;
		}

		PosicionPersona posicionPersona = (PosicionPersona) obj;
		return posicionPersona.documento.equals(this.documento) &&
				posicionPersona.fechaPosicion.equals(this.fechaPosicion);
	}

	@Override
	public int hashCode() {
		final int prime = 23;
		int result = 1;
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((fechaPosicion == null) ? 0 : fechaPosicion.hashCode());
		return result;
	}
}
