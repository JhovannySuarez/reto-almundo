package co.com.almundo.callcenter.entity;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import co.com.almundo.callcenter.controller.EmpleadoController;

public class EmpleadoTest {

	@Test
	public void pruebaAtenderLlamada() {
		Llamada llamada = new Llamada("Duda fecha de corte");
		Empleado emp1 = new Empleado("Juan", "Perez", "7878", TipoEmpleado.OPERADOR);
		emp1.atenderLlamada(llamada);
		Assert.assertEquals(EstadoLlamada.ATENDIDA, llamada.getEstadoLlamada());
	}

	@Test
	public void pruebaEmpleadoDisponible() {
		EmpleadoController empleadoController = new EmpleadoController();
		List<Empleado> listaEmpleados = empleadoController.getListaEmpleados();

		int i = 1;
		long contOperarios = listaEmpleados.stream().filter(emp -> emp.getTipoEmpleado().equals(TipoEmpleado.OPERADOR))
				.count();
		long contSupervisores = listaEmpleados.stream()
				.filter(emp -> emp.getTipoEmpleado().equals(TipoEmpleado.SUPERVISOR)).count();

		while (i <= empleadoController.getListaEmpleados().size()) {
			Empleado empleadoDisponible = empleadoController.getEmpleadoDisponible();

			if (i <= contOperarios) {
				Assert.assertTrue(empleadoDisponible.getTipoEmpleado().equals(TipoEmpleado.OPERADOR));
			} else if (i <= (contOperarios + contSupervisores)) {
				Assert.assertTrue(empleadoDisponible.getTipoEmpleado().equals(TipoEmpleado.SUPERVISOR));
			} else {
				Assert.assertTrue(empleadoDisponible.getTipoEmpleado().equals(TipoEmpleado.DIRECTOR));
			}
			i++;
		}
	}

}
