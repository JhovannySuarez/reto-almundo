package co.com.almundo.callcenter.entity;


import org.junit.Assert;
import org.junit.Test;

public class LlamadaTest {

	@Test
	public void pruebaRangoCalcularTimpoLlamada(){
		Llamada llamada = new Llamada("Duda con factura");
		int tiempoLlamada = llamada.getTiempoLLamada();
		Assert.assertTrue(tiempoLlamada >= 5 && tiempoLlamada <= 10);
	}

}
