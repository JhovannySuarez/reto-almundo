package co.com.almundo.callcenter.entity;

import java.util.concurrent.ThreadLocalRandom;

public class Llamada {

	private static final int MIN_TIEMPO_LLAMADA = 5;
	private static final int MAX_TIEMPO_LLAMADA = 10;
	
	private int tiempoLlamada;
	private EstadoLlamada estadoLlamada;
	private String descripcion;
	private boolean llamadaEnEspera;

	public Llamada(String descripcion) {
		this.estadoLlamada = EstadoLlamada.PENDIENTE;
		calcularTiempoDeLlamada();
		this.descripcion = descripcion;
		this.llamadaEnEspera = false;
		
	}
	
	private void calcularTiempoDeLlamada(){
		tiempoLlamada = ThreadLocalRandom.current().nextInt(MIN_TIEMPO_LLAMADA, MAX_TIEMPO_LLAMADA + 1);
	}

	public int getTiempoLLamada() {
		return tiempoLlamada;
	}

	public EstadoLlamada getEstadoLlamada() {
		return estadoLlamada;
	}

	public void setEstadoLlamada(EstadoLlamada estadoLlamada) {
		this.estadoLlamada = estadoLlamada;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isLlamadaEnEspera() {
		return llamadaEnEspera;
	}

	public void setLlamadaEnEspera(boolean llamadaEnEspera) {
		this.llamadaEnEspera = llamadaEnEspera;
	}
	
}
