import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Tarea {
    private String idTarea;
    private String titulo;
    private String descripcion;
    private String asignadoA;
    private int prioridad;
    private int tiempoLlamada;
    private boolean completado;
    private Date fechaVencimiento;
    private Queue<String> registroActividad;

    public Tarea(String titulo, String descripcion, String asignadoA, int prioridad, Date fechaVencimiento) {
        this.idTarea = generarIdUnico();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.asignadoA = asignadoA;
        this.prioridad = prioridad;
        this.tiempoLlamada = 0;
        this.completado = false;
        this.fechaVencimiento = fechaVencimiento;
        this.registroActividad = new LinkedList<>();
        registrarActividad("Tarea creada");
    }

    private String generarIdUnico() {
        return String.format("%05d", ThreadLocalRandom.current().nextInt(10000, 100000));
    }

    public String obtenerIdTarea() {
        return idTarea;
    }

    public String obtenerTitulo() {
        return titulo;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    public String obtenerAsignadoA() {
        return asignadoA;
    }

    public int obtenerPrioridad() {
        return prioridad;
    }

    public int obtenerTiempoLlamada() {
        return tiempoLlamada;
    }

    public boolean estaCompletado() {
        return completado;
    }

    public Date obtenerFechaVencimiento() {
        return fechaVencimiento;
    }

    public Queue<String> obtenerRegistroActividad() {
        return registroActividad;
    }

    public void registrarActividad(String actividad) {
        registroActividad.offer(actividad);
    }

    public void marcarComoCompletado(int tiempoLlamada) {
        this.tiempoLlamada = tiempoLlamada;
        this.completado = true;
        registrarActividad("Tarea completada");
    }

    public void editarTarea(String titulo, String descripcion, String asignadoA, int prioridad, Date fechaVencimiento) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.asignadoA = asignadoA;
        this.prioridad = prioridad;
        this.fechaVencimiento = fechaVencimiento;
        registrarActividad("Tarea editada");
    }
}
