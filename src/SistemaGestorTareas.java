import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class SistemaGestorTareas {
    public static void main(String[] args) {
        GestorTareas gestorTareas = new GestorTareas();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSistema de Gestión de Tareas");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Iniciar Tarea");
            System.out.println("3. Completar Tarea");
            System.out.println("4. Mostrar Pendientes");
            System.out.println("5. Mostrar En Progreso");
            System.out.println("6. Mostrar Completadas");
            System.out.println("7. Ver Detalles de Tarea");
            System.out.println("8. Mover Tarea");
            System.out.println("9. Mostrar Tablero de Tareas");
            System.out.println("10. Salir");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarTarea(scanner, gestorTareas);
                    break;
                case 2:
                    iniciarTarea(scanner, gestorTareas);
                    break;
                case 3:
                    completarTarea(scanner, gestorTareas);
                    break;
                case 4:
                    mostrarPendientes(gestorTareas);
                    break;
                case 5:
                    mostrarEnProgreso(gestorTareas);
                    break;
                case 6:
                    mostrarCompletadas(gestorTareas);
                    break;
                case 7:
                    verDetallesTarea(scanner, gestorTareas);
                    break;
                case 8:
                    moverTarea(scanner, gestorTareas);
                    break;
                case 9:
                    mostrarTablero(gestorTareas);
                    break;
                case 10:
                    System.out.println("Saliendo del Sistema de Gestión de Tareas...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, inténtelo de nuevo.");
            }
        }
    }

    private static void agregarTarea(Scanner scanner, GestorTareas gestorTareas) {
        System.out.print("Ingrese el título de la tarea: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el asignado de la tarea: ");
        String asignado = scanner.nextLine();
        System.out.print("Ingrese la prioridad de la tarea (1-5): ");
        int prioridad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la fecha de vencimiento de la tarea (yyyy-MM-dd): ");
        String fechaString = scanner.nextLine();
        Date fechaVencimiento = null;
        try {
            fechaVencimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Tarea nuevaTarea = new Tarea(titulo, descripcion, asignado, prioridad, fechaVencimiento);
        gestorTareas.agregarTarea(nuevaTarea);
        System.out.println("Tarea agregada a Pendientes.");
    }

    private static void iniciarTarea(Scanner scanner, GestorTareas gestorTareas) {
        System.out.print("Ingrese el ID de la tarea para iniciar: ");
        String idInicio = scanner.nextLine();
        boolean encontradaInicio = false;
        for (Tarea tarea : gestorTareas.getPendientes()) {
            if (tarea.obtenerIdTarea().equals(idInicio)) {
                gestorTareas.iniciarTarea(tarea);
                System.out.println("Tarea iniciada: " + tarea.obtenerTitulo());
                encontradaInicio = true;
                break;
            }
        }
        if (!encontradaInicio) {
            System.out.println("Tarea no encontrada en Pendientes.");
        }
    }

    private static void completarTarea(Scanner scanner, GestorTareas gestorTareas) {
        System.out.print("Ingrese el ID de la tarea para completar: ");
        String idCompleto = scanner.nextLine();
        boolean encontradaCompleto = false;
        for (Tarea tarea : gestorTareas.getEnProgreso()) {
            if (tarea.obtenerIdTarea().equals(idCompleto)) {
                gestorTareas.completarTarea(tarea);
                System.out.println("Tarea completada: " + tarea.obtenerTitulo());
                encontradaCompleto = true;
                break;
            }
        }
        if (!encontradaCompleto) {
            System.out.println("Tarea no encontrada en En Progreso.");
        }
    }

    private static void mostrarPendientes(GestorTareas gestorTareas) {
        System.out.println("\nPendientes:");
        for (Tarea tarea : gestorTareas.getPendientes()) {
            System.out.println("- " + tarea.obtenerTitulo() + " (ID: " + tarea.obtenerIdTarea() + ")");
        }
    }

    private static void mostrarEnProgreso(GestorTareas gestorTareas) {
        System.out.println("\nEn Progreso:");
        for (Tarea tarea : gestorTareas.getEnProgreso()) {
            System.out.println("- " + tarea.obtenerTitulo() + " (ID: " + tarea.obtenerIdTarea() + ")");
        }
    }

    private static void mostrarCompletadas(GestorTareas gestorTareas) {
        System.out.println("\nCompletadas:");
        for (Tarea tarea : gestorTareas.getCompletadas()) {
            System.out.println("- " + tarea.obtenerTitulo() + " (ID: " + tarea.obtenerIdTarea() + ")");
        }
    }

    private static void verDetallesTarea(Scanner scanner, GestorTareas gestorTareas) {
        System.out.print("Ingrese el ID de la tarea para ver detalles: ");
        String idDetalles = scanner.nextLine();
        boolean encontradaDetalles = false;
        for (Tarea tarea : gestorTareas.getTodasTareas()) {
            if (tarea.obtenerIdTarea().equals(idDetalles)) {
                System.out.println("Título: " + tarea.obtenerTitulo());
                System.out.println("Descripción: " + tarea.obtenerDescripcion());
                System.out.println("Asignado a: " + tarea.obtenerAsignadoA());
                System.out.println("Prioridad: " + tarea.obtenerPrioridad());
                System.out.println("Fecha de Vencimiento: " + new SimpleDateFormat("yyyy-MM-dd").format(tarea.obtenerFechaVencimiento()));
                System.out.println("Registro de Actividad:");
                for (String actividad : tarea.obtenerRegistroActividad()) {
                    System.out.println("- " + actividad);
                }
                encontradaDetalles = true;
                break;
            }
        }
        if (!encontradaDetalles) {
            System.out.println("Tarea no encontrada.");
        }
    }

    private static void moverTarea(Scanner scanner, GestorTareas gestorTareas) {
        System.out.print("Ingrese el ID de la tarea para mover: ");
        String idMover = scanner.nextLine();
        boolean encontradaMover = false;
        Tarea tareaMover = null;
        for (Tarea tarea : gestorTareas.getTodasTareas()) {
            if (tarea.obtenerIdTarea().equals(idMover)) {
                tareaMover = tarea;
                encontradaMover = true;
                break;
            }
        }
        if (!encontradaMover) {
            System.out.println("Tarea no encontrada.");
            return;
        }
        System.out.println("Seleccione el destino:");
        System.out.println("1. Pendientes");
        System.out.println("2. En Progreso");
        System.out.println("3. Completadas");
        System.out.print("Ingrese el número de destino: ");
        int destino = scanner.nextInt();
        scanner.nextLine();
        Stack<Tarea> colaDestino = null;
        switch (destino) {
            case 1:
                colaDestino = gestorTareas.getPendientes();
                break;
            case 2:
                colaDestino = gestorTareas.getEnProgreso();
                break;
            case 3:
                colaDestino = gestorTareas.getCompletadas();
                break;
            default:
                System.out.println("Destino no válido.");
                break;
        }
        if (colaDestino != null) {
            gestorTareas.moverTarea(tareaMover, colaDestino);
            System.out.println("Tarea movida exitosamente.");
        }
    }

    private static void mostrarTablero(GestorTareas gestorTareas) {
        System.out.println("\nTablero de Tareas:");
        System.out.println("+-------------------+------------------+-----------+");
        System.out.println("|     Pendientes    |    En Progreso   | Completadas|");
        System.out.println("+-------------------+------------------+-----------+");
        int maxTareas = Math.max(Math.max(gestorTareas.getPendientes().size(), gestorTareas.getEnProgreso().size()), gestorTareas.getCompletadas().size());
        for (int i = 0; i < maxTareas; i++) {
            String tareaPendiente = (i < gestorTareas.getPendientes().size()) ? gestorTareas.getPendientes().get(i).obtenerTitulo() : "";
            String tareaEnProgreso = (i < gestorTareas.getEnProgreso().size()) ? gestorTareas.getEnProgreso().get(i).obtenerTitulo() : "";
            String tareaCompletada = (i < gestorTareas.getCompletadas().size()) ? gestorTareas.getCompletadas().get(i).obtenerTitulo() : "";
            System.out.printf("| %-17s| %-16s| %-9s|\n", tareaPendiente, tareaEnProgreso, tareaCompletada);
        }
        System.out.println("+-------------------+------------------+-----------+");
    }
}
