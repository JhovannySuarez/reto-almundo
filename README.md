# reto Call Center Almundo
Este proyecto solucióna el reto "Call center" para ingreso a Almundo

# Solución Extras/Plus 
* Dar alguna solución sobre qué pasa con una llamada cuando no hay ningún empleado libre:
Se crea la cola "colaLlamadasEnEspera" en la clase Dispatcher.java en la cuál se almacenan las llamadas cuando no hay empleados disponibles,
para ser atendida una vez haya un empleado libre.

* Dar alguna solución sobre qué pasa con una llamada cuando entran más de 10 llamadas concurrentes.
Se crea un pool con un máximo de 10 hilos.

* Agregar los tests unitarios que se crean convenientes.
Se crean los test para las clases Empleado.java, Llamada.java y Dispatcher.java

* Agregar documentación de código:
Se agrega el archivo diagramas/class_diagram.png
