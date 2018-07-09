package co.com.almundo.callcenter.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import co.com.almundo.callcenter.entity.Empleado;
import co.com.almundo.callcenter.entity.EstadoEmpleado;
import co.com.almundo.callcenter.entity.TipoEmpleado;

public class EmpleadoController {

	private List<Empleado> listaEmpleados;

	public EmpleadoController() {
		listaEmpleados = new ArrayList<>();
		iniciarListaEmpleados();
	}

	public synchronized Empleado getEmpleadoDisponible() {
		Empleado empleado = listaEmpleados.stream().filter(emp -> emp.getEstadoEmpleado().equals(EstadoEmpleado.DISPONIBLE))
				.findFirst().orElse(null);
		if(empleado != null){
			empleado.setEstadoEmpleado(EstadoEmpleado.OCUPADO);
		}
		return empleado;
	}

	/**
	 * Crea una lista de empleados y los ordena por tipo de empleado.
	 */
	private void iniciarListaEmpleados() {
		Empleado empleado1 = new Empleado("Carolina", "Rivera", "1128", TipoEmpleado.DIRECTOR);
		listaEmpleados.add(empleado1);
//		Empleado empleado2 = new Empleado("Pedro", "Suarez", "6785", TipoEmpleado.OPERADOR);
//		listaEmpleados.add(empleado2);
		Empleado empleado3 = new Empleado("Juan", "Arenas", "1537", TipoEmpleado.OPERADOR);
		listaEmpleados.add(empleado3);
		Empleado empleado4 = new Empleado("Juan", "Perez", "9898", TipoEmpleado.SUPERVISOR);
		listaEmpleados.add(empleado4);
//		Empleado empleado5 = new Empleado("Rodrigo", "Pereyra", "2314", TipoEmpleado.DIRECTOR);
//		listaEmpleados.add(empleado5);
		Empleado empleado6 = new Empleado("Ana", "Zapata", "4501", TipoEmpleado.OPERADOR);
		listaEmpleados.add(empleado6);
		Empleado empleado7 = new Empleado("Denis", "Lujan", "5678", TipoEmpleado.SUPERVISOR);
		listaEmpleados.add(empleado7);
//		Empleado empleado8 = new Empleado("Carlos", "Martinez", "1020", TipoEmpleado.SUPERVISOR);
//		listaEmpleados.add(empleado8);
		Empleado empleado9 = new Empleado("Esteban", "Betts", "9812", TipoEmpleado.OPERADOR);
		listaEmpleados.add(empleado9);
		Empleado empleado10 = new Empleado("Vinicio", "Rivera", "2030", TipoEmpleado.OPERADOR);
		listaEmpleados.add(empleado10);
//		Empleado empleado11 = new Empleado("Sandra", "Gomez", "4050", TipoEmpleado.OPERADOR);
//		listaEmpleados.add(empleado11);
//		Empleado empleado12 = new Empleado("Mateo", "Suarez", "5050", TipoEmpleado.SUPERVISOR);
//		listaEmpleados.add(empleado12);
//		Empleado empleado13 = new Empleado("Valery", "Pareja", "9284", TipoEmpleado.DIRECTOR);
//		listaEmpleados.add(empleado13);
//		Empleado empleado14 = new Empleado("Angela", "Ramirez", "8347", TipoEmpleado.OPERADOR);
//		listaEmpleados.add(empleado14);
//		Empleado empleado15 = new Empleado("Yeison", "Osorio", "3856", TipoEmpleado.OPERADOR);
//		listaEmpleados.add(empleado15);
//		Empleado empleado16 = new Empleado("Daniela", "Rueda", "4749", TipoEmpleado.OPERADOR);
//		listaEmpleados.add(empleado16);
//		Empleado empleado17 = new Empleado("Daniel", "Garcia", "9091", TipoEmpleado.OPERADOR);
//		listaEmpleados.add(empleado17);

		Comparator<Empleado> comparator = (emp1, emp2) -> emp1.getTipoEmpleado().getPrioridad()
				- emp2.getTipoEmpleado().getPrioridad();

		Collections.sort(listaEmpleados, comparator);
	}

	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

}
