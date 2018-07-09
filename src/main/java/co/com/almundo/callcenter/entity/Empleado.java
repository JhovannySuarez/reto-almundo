package co.com.almundo.callcenter.entity;

import java.util.logging.Logger;

public class Empleado {

	private String nombre;
	private String apellido;
	private String id;
	
	private TipoEmpleado tipoEmpleado;
	private EstadoEmpleado estadoEmpleado;
	private static final Logger LOGGER = Logger.getLogger(Empleado.class.getName());
	private static int llamadasAtendidas;
	
	public Empleado(String nombre, String apellio, String id, TipoEmpleado tipoEmpleado) {
		super();
		this.nombre = nombre;
		this.apellido = apellio;
		this.id = id;
		this.tipoEmpleado = tipoEmpleado;
		this.estadoEmpleado = EstadoEmpleado.DISPONIBLE;
		llamadasAtendidas = 0;
	}
	
	
	public synchronized void atenderLlamada(Llamada llamada) {
		try {
			String logMsg = String.format("Atendiendo llamada: '%s'. Empleado: '%s', Rol: '%s',",llamada.getDescripcion(), getNombreCompleto(),getTipoEmpleado()); 
			LOGGER.info(logMsg);
			Thread.sleep(llamada.getTiempoLLamada() * 1000L);
			logMsg = String.format("LLamada atendida '%s', Tiempo de llamada: '%s' Segundos", llamada.getDescripcion(), llamada.getTiempoLLamada());
			LOGGER.info(logMsg) ;
			llamadasAtendidas++;
		} catch (InterruptedException e) {
			LOGGER.warning("No se pudo atender la llamada "+llamada.getDescripcion());
			Thread.currentThread().interrupt();
		}
		setEstadoEmpleado(EstadoEmpleado.DISPONIBLE);
		llamada.setEstadoLlamada(EstadoLlamada.ATENDIDA);
	}
	
	public String getNombreCompleto(){
		return nombre + " " + apellido;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellio) {
		this.apellido = apellio;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TipoEmpleado getTipoEmpleado() {
		return tipoEmpleado;
	}
	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
	public EstadoEmpleado getEstadoEmpleado() {
		return estadoEmpleado;
	}
	public void setEstadoEmpleado(EstadoEmpleado estadoEmpleado) {
		this.estadoEmpleado = estadoEmpleado;
	}
	public static int getLlamadasAtendidas() {
		return llamadasAtendidas;
	}
	
}
