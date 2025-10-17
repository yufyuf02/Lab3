// Autora: Yu-Fong Chen
// Fecha: 10/10/2025

// ***** Sistema Integral de Gestión Hospitalaria *****
// Para administrar el personal médico y manejar las citas.

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Administracion admin = new Administracion();

    public static void main(String[] args) {
        inicializarDatos();
        boolean salir = false;

        System.out.println("*** SISTEMA INTEGRAL DE GESTIÓN HOSPITALARIA ***");
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1:
                    menuGestionMedicos();
                    break;
                case 2:
                    menuGestionCitas();
                    break;
                case 3:
                    menuReagendamiento();
                    break;
                case 4:
                    menuReportesFinancieros();
                    break;
                case 5:
                    menuReportesAnalisis();
                    break;
                case 0:
                    System.out.println("\n¡Gracias por usar el sistema! :3");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente otra vez.");
            }
        }
        
        scanner.close();
    }
    
    
    // ****************** MÉTODOS ******************

    // Empezar con algunos datos quemados
    private static void inicializarDatos() {

        // Médicos
        Doctor doc1 = new Doctor(1, "Dr. Tulio Triviño", "Medicina General", 
                                 10, 8000.0, "Medicina Interna", 15, 200.0, 0);
        Cirujano cir1 = new Cirujano(2, "Dra. Perfectina Valentina", "Cirugía", 
                                     15, 12000.0, "Cardíaca, Ortopédica", 
                                     40, 500.0, 3000.0);
        Enfermero enf1 = new Enfermero(3, "Mario Godoy", "Enfermería", 
                                       5, 4000.0, "Nocturno", "Profesional");
        Radiologo rad1 = new Radiologo(4, "Dr. Consuelo Contecho", "Radiología", 
                                       8, 7000.0, "Rayos X, Tomografía, Resonancia", 150.0);
        
        admin.registrarMedico(doc1);
        admin.registrarMedico(cir1);
        admin.registrarMedico(enf1);
        admin.registrarMedico(rad1);
        
        // Citas
        admin.crearCita("Juan Pérez", 1, LocalDate.of(2025, 10, 20), 
                       LocalTime.of(10, 0), TipoCita.GENERAL);
        admin.crearCita("María Torres", 2, LocalDate.of(2025, 10, 21), 
                       LocalTime.of(14, 30), TipoCita.CIRUGIA);
        admin.crearCita("Carlos Gómez", 4, LocalDate.of(2025, 10, 22), 
                       LocalTime.of(9, 0), TipoCita.DIAGNOSTICO);
    }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("(1) Gestión de Personal Médico");
        System.out.println("(2) Gestión de Citas");
        System.out.println("(3) Reagendamiento");
        System.out.println("(4) Cálculos y Reportes Financieros");
        System.out.println("(5) Reportes y Análisis");
        System.out.println("(0) Salir del sistema");
    }
    
    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Por favor ingrese un número válido: ");
        }
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer
        return numero;
    }
    
    private static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Por favor ingrese un número válido: ");
        }
        double numero = scanner.nextDouble();
        scanner.nextLine(); // Limpia EL buffer
        return numero;
    }
    
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static void pausa() {
        System.out.print("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    // ****************** GESTIÓN DE MÉDICOS ******************
    
    private static void menuGestionMedicos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n*** Gestión de Personal Médico ***");
            System.out.println("(1) Registrar un nuevo médico");
            System.out.println("(2) Listar todos los médicos");
            System.out.println("(3) Buscar médico por ID");
            System.out.println("(4) Buscar médicos por departamento");
            System.out.println("(5) Eliminar médico");
            System.out.println("(0) Volver al menú principal");
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1:
                    registrarNuevoMedico();
                    break;
                case 2:
                    listarTodosMedicos();
                    break;
                case 3:
                    buscarMedicoPorID();
                    break;
                case 4:
                    buscarMedicosPorDepartamento();
                    break;
                case 5:
                    eliminarMedico();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private static void registrarNuevoMedico() {
        System.out.println("\n*** REGISTRAR UN NUEVO MÉDICO ***");
        
        int id = leerEntero("ID: ");
        
        // Verificar si el ID ya existe
        if (admin.buscarMedicoPorID(id) != null) {
            System.out.println("Ya existe un médico con ese ID.");
            return;
        }
        
        String nombre = leerTexto("Nombre completo: ");
        String departamento = leerTexto("Departamento: ");
        int aniosExp = leerEntero("Años de experiencia: ");
        double salarioBase = leerDouble("Salario base: Q");
        
        System.out.println("\nTipos de especialización:");
        System.out.println("(1) Doctor General");
        System.out.println("(2) Cirujano");
        System.out.println("(3) Enfermero/a");
        System.out.println("(4) Radiólogo/a");
        int tipo = leerEntero("Seleccione: ");
        
        Medico nuevoMedico = null;
        
        switch (tipo) {
            case 1:
                String especializacion = leerTexto("Especialización: ");
                int capacidad = leerEntero("Capacidad de pacientes por día: ");
                double tarifa = leerDouble("Tarifa por consulta: Q");
                nuevoMedico = new Doctor(id, nombre, departamento, aniosExp, salarioBase,
                                        especializacion, capacidad, tarifa, 0);
                break;
                
            case 2:
                String tiposOp = leerTexto("Tipos de operaciones (separadas por coma): ");
                int horasDisp = leerEntero("Horas de cirugía disponibles: ");
                double tarifaHora = leerDouble("Tarifa por hora: Q");
                double bono = leerDouble("Bono por riesgo: Q");
                nuevoMedico = new Cirujano(id, nombre, departamento, aniosExp, salarioBase,
                                          tiposOp, horasDisp, tarifaHora, bono);
                break;
                
            case 3:
                String turno = leerTexto("Tipo de turno (Diurno/Nocturno): ");
                String nivel = leerTexto("Nivel de certificación: ");
                nuevoMedico = new Enfermero(id, nombre, departamento, aniosExp, salarioBase,
                                           turno, nivel);
                break;
                
            case 4:
                String equipos = leerTexto("Equipos certificados (separados por coma): ");
                double tarifaEstudio = leerDouble("Tarifa por estudio: Q");
                nuevoMedico = new Radiologo(id, nombre, departamento, aniosExp, salarioBase,
                                           equipos, tarifaEstudio);
                break;
                
            default:
                System.out.println("✗ Tipo inválido.");
                return;
        }
        
        admin.registrarMedico(nuevoMedico);
        System.out.println("Médico registrado exitosamente. :)");
        pausa();
    }
    
    private static void listarTodosMedicos() {
        System.out.println("\n*** LISTA DE MÉDICOS ***");
        ArrayList<Medico> medicos = admin.getMedicos();
        
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
        } else {
            for (Medico medico : medicos) {
                System.out.println("\n" + medico.getInfo());
                System.out.println("Salario calculado: Q" + 
                    String.format("%.2f", medico.calcularSalario()));
                System.out.println("───────────────────");
            }
        }
        pausa();
    }
    
    private static void buscarMedicoPorID() {
        int id = leerEntero("\nIngrese ID del médico: ");
        Medico medico = admin.buscarMedicoPorID(id);
        
        if (medico != null) {
            System.out.println("\n✓ Médico encontrado:");
            System.out.println(medico.getInfo());
            System.out.println("Salario calculado: Q" + 
                String.format("%.2f", medico.calcularSalario()));
        } else {
            System.out.println("Médico no encontrado. :(");
        }
        pausa();
    }
    
    private static void buscarMedicosPorDepartamento() {
        String depto = leerTexto("\nIngrese el nombre del departamento: ");
        ArrayList<Medico> medicos = admin.buscarPorDepartamento(depto);
        
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos en ese departamento. :(");
        } else {
            System.out.println("\n*** MÉDICOS EN: " + depto + " ***");
            for (Medico medico : medicos) {
                System.out.println(medico.getInfo());
                System.out.println("───────────────────");
            }
        }
        pausa();
    }
    
    private static void eliminarMedico() {
        int id = leerEntero("\nIngrese ID del médico a eliminar: ");
        Medico medico = admin.buscarMedicoPorID(id);
        
        if (medico != null) {
            System.out.println("Médico a eliminar: " + medico.getNombre());
            String confirmacion = leerTexto("¿Está seguro? (si/no): ");
            
            if (confirmacion.equalsIgnoreCase("si")) {
                if (admin.eliminarMedico(id)) {
                    System.out.println("Médico eliminado exitosamente. :)");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("Médico no encontrado. :(");
        }
        pausa();
    }

    // ****************** GESTIÓN DE CITAS ******************
    
    private static void menuGestionCitas() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n*** GESTIÓN DE CITAS MÉDICAS ***");
            System.out.println("(1) Crear nueva cita");
            System.out.println("(2) Ver todas las citas");
            System.out.println("(3) Buscar citas por paciente");
            System.out.println("(4) Buscar citas por médico");
            System.out.println("(5) Buscar citas por estado");
            System.out.println("(6) Cambiar estado de cita");
            System.out.println("(7) Cancelar cita");
            System.out.println("(0) Volver al menú principal");            
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1:
                    crearNuevaCita();
                    break;
                case 2:
                    verTodasCitas();
                    break;
                case 3:
                    buscarCitasPorPaciente();
                    break;
                case 4:
                    buscarCitasPorMedico();
                    break;
                case 5:
                    buscarCitasPorEstado();
                    break;
                case 6:
                    cambiarEstadoCita();
                    break;
                case 7:
                    cancelarCita();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private static void crearNuevaCita() {
        System.out.println("\n*** CREAR NUEVA CITA ***");
        
        String paciente = leerTexto("Nombre del paciente: ");
        
        // Mostrar médicos disponibles
        System.out.println("\nMédicos disponibles:");
        ArrayList<Medico> medicos = admin.getMedicos();
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
            return;
        }
        for (Medico medico : medicos) {
            System.out.println("ID: " + medico.getID() + " - " + medico.getNombre() + 
                             " (" + medico.getDept() + ")");
        }
        
        int idMedico = leerEntero("\nID del médico: ");
        
        // Verificar que el médico existe
        if (admin.buscarMedicoPorID(idMedico) == null) {
            System.out.println("Médico no encontrado. :(");
            return;
        }
        
        // Leer fecha
        System.out.println("\nFecha de la cita:");
        int anio = leerEntero("Año (ej: 2025): ");
        int mes = leerEntero("Mes (1-12): ");
        int dia = leerEntero("Día (1-31): ");
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        
        // Leer hora
        System.out.println("\nHora de la cita:");
        int hora = leerEntero("Hora (0-23): ");
        int minuto = leerEntero("Minuto (0-59): ");
        LocalTime horaC = LocalTime.of(hora, minuto);
        
        // Tipo de cita
        System.out.println("\nTipo de cita:");
        System.out.println("(1) Consulta General");
        System.out.println("(2) Cirugía");
        System.out.println("(3) Terapia");
        System.out.println("(4) Diagnóstico");
        int tipoOpcion = leerEntero("Seleccione: ");
        
        TipoCita tipo = null;
        switch (tipoOpcion) {
            case 1: tipo = TipoCita.GENERAL; break;
            case 2: tipo = TipoCita.CIRUGIA; break;
            case 3: tipo = TipoCita.TERAPIA; break;
            case 4: tipo = TipoCita.DIAGNOSTICO; break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }
        
        // Crear cita
        Cita nuevaCita = admin.crearCita(paciente, idMedico, fecha, horaC, tipo);
        
        if (nuevaCita != null) {
            System.out.println("\n✓ Cita creada exitosamente.");
            System.out.println("ID de cita: #" + nuevaCita.getID());
        } else {
            System.out.println("\n✗ No se pudo crear la cita.");
            System.out.println("Posibles razones: Médico no existe o conflicto de horario.");
        }
        pausa();
    }
    
    private static void verTodasCitas() {
        System.out.println("\n*** TODAS LAS CITAS ***");
        ArrayList<Cita> citas = admin.getCitas();
        
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            for (Cita cita : citas) {
                System.out.println("\n" + cita.getInfo());
                System.out.println("───────────────────");
            }
        }
        pausa();
    }
    
    private static void buscarCitasPorPaciente() {
        String nombre = leerTexto("\nNombre del paciente: ");
        ArrayList<Cita> citas = admin.getCitasPorPaciente(nombre);
        
        if (citas.isEmpty()) {
            System.out.println("No se encontraron citas para ese paciente.");
        } else {
            System.out.println("\n*** CITAS DE: " + nombre + " ***");
            for (Cita cita : citas) {
                System.out.println("\n" + cita.getInfo());
                System.out.println("───────────────────");
            }
        }
        pausa();
    }
    
    private static void buscarCitasPorMedico() {
        int idMedico = leerEntero("\nID del médico: ");
        Medico medico = admin.buscarMedicoPorID(idMedico);
        
        if (medico == null) {
            System.out.println("Médico no encontrado.");
        } else {
            ArrayList<Cita> citas = admin.getCitasPorMedico(idMedico);
            
            if (citas.isEmpty()) {
                System.out.println("Este médico no tiene citas asignadas.");
            } else {
                System.out.println("\n*** CITAS DEL DR. " + medico.getNombre() + " ***");
                for (Cita cita : citas) {
                    System.out.println("\n" + cita.getInfo());
                    System.out.println("───────────────────");
                }
            }
        }
        pausa();
    }
    
    private static void buscarCitasPorEstado() {
        System.out.println("\nEstados disponibles:");
        System.out.println("(1) PROGRAMADA");
        System.out.println("(2) CONFIRMADA");
        System.out.println("(3) EN_PROGRESO");
        System.out.println("(4) COMPLETADA");
        System.out.println("(5) CANCELADA");
        System.out.println("(6) REAGENDADA");
        int opcion = leerEntero("Seleccione estado: ");
        
        EstadoCita estado = null;
        switch (opcion) {
            case 1: estado = EstadoCita.PROGRAMADA; break;
            case 2: estado = EstadoCita.CONFIRMADA; break;
            case 3: estado = EstadoCita.EN_PROGRESO; break;
            case 4: estado = EstadoCita.COMPLETADA; break;
            case 5: estado = EstadoCita.CANCELADA; break;
            case 6: estado = EstadoCita.REAGENDADA; break;
            default:
                System.out.println("Opción inválida.");
                return;
        }
        
        ArrayList<Cita> citas = admin.getCitasPorEstado(estado);
        
        if (citas.isEmpty()) {
            System.out.println("No hay citas con ese estado.");
        } else {
            System.out.println("\n*** CITAS CON ESTADO: " + estado + " ***");
            for (Cita cita : citas) {
                System.out.println("\n" + cita.getInfo());
                System.out.println("───────────────────");
            }
        }
        pausa();
    }
    
    private static void cambiarEstadoCita() {
        int idCita = leerEntero("\nID de la cita: ");
        Cita cita = admin.buscarCitaPorID(idCita);
        
        if (cita == null) {
            System.out.println("Cita no encontrada. :(");
            pausa();
            return;
        }
        
        System.out.println("\nCita encontrada:");
        System.out.println(cita.getInfo());
        
        System.out.println("\nNuevo estado:");
        System.out.println("(1) PROGRAMADA");
        System.out.println("(2) CONFIRMADA");
        System.out.println("(3) EN_PROGRESO");
        System.out.println("(4) COMPLETADA");
        System.out.println("(5) CANCELADA");
        System.out.println("(6) REAGENDADA");
        int opcion = leerEntero("Seleccione: ");
        
        EstadoCita nuevoEstado = null;
        switch (opcion) {
            case 1: nuevoEstado = EstadoCita.PROGRAMADA; break;
            case 2: nuevoEstado = EstadoCita.CONFIRMADA; break;
            case 3: nuevoEstado = EstadoCita.EN_PROGRESO; break;
            case 4: nuevoEstado = EstadoCita.COMPLETADA; break;
            case 5: nuevoEstado = EstadoCita.CANCELADA; break;
            case 6: nuevoEstado = EstadoCita.REAGENDADA; break;
            default:
                System.out.println("Opción inválida.");
                pausa();
                return;
        }
        
        if (admin.cambiarEstadoCita(idCita, nuevoEstado)) {
            System.out.println("Estado cambiado a: " + nuevoEstado + " :)");
        } else {
            System.out.println("Error al cambiar estado. :(");
        }
        pausa();
    }
    
    private static void cancelarCita() {
        int idCita = leerEntero("\nID de la cita a cancelar: ");
        Cita cita = admin.buscarCitaPorID(idCita);
        
        if (cita != null) {
            System.out.println("Cita a cancelar:");
            System.out.println(cita.getInfo());
            
            String confirmacion = leerTexto("\n¿Está seguro? (si/no): ");
            
            if (confirmacion.equalsIgnoreCase("si")) {
                if (admin.cancelarCita(idCita)) {
                    System.out.println("Cita cancelada exitosamente. :)");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("Cita no encontrada. :(");
        }
        pausa();
    }
}
