import java.util.*;

public class GestorTareas {
    private Stack<Tarea> pendientes;
    private Stack<Tarea> enProgreso;
    private Stack<Tarea> completadas;

    public GestorTareas() {
        this.pendientes = new Stack<>();
        this.enProgreso = new Stack<>();
        this.completadas = new Stack<>();
    }

    public void agregarTarea(Tarea tarea) {
        pendientes.push(tarea);
    }

    public void iniciarTarea(Tarea tarea) {
        pendientes.remove(tarea);
        enProgreso.push(tarea);
        tarea.registrarActividad("Tarea iniciada");
    }

    public void completarTarea(Tarea tarea) {
        enProgreso.remove(tarea);
        tarea.marcarComoCompletado((int) (Math.random() * 10 + 1)); // Simular tiempo de procesamiento
        completadas.push(tarea);
    }

    public Stack<Tarea> getPendientes() {
        return pendientes;
    }

    public Stack<Tarea> getEnProgreso() {
        return enProgreso;
    }

    public Stack<Tarea> getCompletadas() {
        return completadas;
    }

    public void moverTarea(Tarea tarea, Stack<Tarea> destino) {
        if (destino == pendientes || destino == enProgreso || destino == completadas) {
            tarea.registrarActividad("Tarea movida a " + destino.getClass().getSimpleName());
            destino.push(tarea);
        } else {
            System.out.println("Destino no válido.");
        }
    }

    public List<Tarea> getTodasTareas() {
        List<Tarea> todasTareas = new ArrayList<>();
        todasTareas.addAll(pendientes);
        todasTareas.addAll(enProgreso);
        todasTareas.addAll(completadas);
        return todasTareas;
    }
}
