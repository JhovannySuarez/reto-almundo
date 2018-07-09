package co.com.almundo.callcenter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import co.com.almundo.callcenter.controller.EmpleadoController;
import co.com.almundo.callcenter.entity.Empleado;
import co.com.almundo.callcenter.entity.Llamada;

public class Dispatcher {

	public static final int MAX_CALLS_NUMBER = 10;
	private static final Queue<Llamada> colaLlamadasEnEspera  = new LinkedList<>();
	private static EmpleadoController empleadoController;
	private static ExecutorService exService;
	private static final Logger LOGGER = Logger.getLogger(Dispatcher.class.getName());

	public static void init() {
		empleadoController = new EmpleadoController();
		exService = Executors.newFixedThreadPool(MAX_CALLS_NUMBER);
	}

	/**
	 * Si hay empleados disponiles le da prioridad a las llamadas en cola, en
	 * caso contrario encola la llamada. Si no hay llamadas en cola, la llamada
	 * es atendida inmediatamente. 
	 * 
	 * Punto extra: Dar alguna solución sobre qué
	 * pasa con una llamada cuando no hay ningún empleado libre. 
	 * Solución: se encola y la procesa el primer empleado en quedar disponible.
	 * 
	 * 
	 * @param llamada
	 * @throws InterruptedException
	 */
	public void dispatchCall(Llamada llamada) {
		Runnable runnable = () -> {
			Empleado empleadoDisponible = empleadoController.getEmpleadoDisponible();
			if (empleadoDisponible != null) {
				if (colaLlamadasEnEspera.isEmpty()) {
					empleadoDisponible.atenderLlamada(llamada);
				} else {
					desEncolarLlamada(empleadoDisponible);
				}
			} else {
				if (!llamada.isLlamadaEnEspera())
					encolarLlamada(llamada);

				// Espera 5 segundos por el próximo empleado disponible e
				// intenta nuevamente.
				try {
					Thread.sleep(5000l);
				} catch (InterruptedException e) {
					LOGGER.warning("No se pudo despachar la llamada " + llamada.getDescripcion());
					Thread.currentThread().interrupt();
				}
				dispatchCall(llamada);
			}
		};
		exService.execute(runnable);
	}
	
	public static void terminarDispatcher(){
		exService.shutdown();
		LOGGER.info("Total llamadas atendidas: "+Empleado.getLlamadasAtendidas());
	}

	private synchronized void encolarLlamada(Llamada llamada) {
		colaLlamadasEnEspera.offer(llamada);
		String logMsg = String.format("Encolando llamada: %s, Encoladas: %s",llamada.getDescripcion(),colaLlamadasEnEspera.size());
		LOGGER.info(logMsg);
		llamada.setLlamadaEnEspera(true);
	}
	
	private void desEncolarLlamada(Empleado empleadoDisponible){
		Llamada llamadaEnCola = colaLlamadasEnEspera.poll();
		String logMsg = String.format("Desencolando llamada. %s, Llamadas en espera: %s",
				llamadaEnCola.getDescripcion(), colaLlamadasEnEspera.size());
		LOGGER.info(logMsg);
		empleadoDisponible.atenderLlamada(llamadaEnCola);
	}

	public static Queue<Llamada> getColallamadasenespera() {
		return colaLlamadasEnEspera;
	}

}
