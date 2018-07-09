package co.com.almundo.callcenter.entity;

import org.junit.Assert;
import org.junit.Test;

import co.com.almundo.callcenter.Dispatcher;

public class DispatcherTest {

	
	@Test
	public void pruebaDispatchCon10Llamadas() throws InterruptedException {
		int procesos = 1;
		Dispatcher.init();
		while(procesos <= Dispatcher.MAX_CALLS_NUMBER){
			Llamada llamada = new Llamada("Solicitud soporte técnico "+procesos);
			Dispatcher dispatcher = new Dispatcher();
			dispatcher.dispatchCall(llamada);
			procesos++;
		}
		Thread.sleep(20000l);
		
		Dispatcher.terminarDispatcher();
		Assert.assertTrue(Dispatcher.getColallamadasenespera().size() == 0);
	}

}
