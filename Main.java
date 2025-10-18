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
        System.out.println(
            "\n" +
            "(1) Gestión de Personal Médico \n" +
            "(2) Gestión de Citas \n" +
            "(3) Reagendamiento \n" +
            "(4) Cálculos y Reportes Financieros \n" +
            "(5) Reportes y Análisis \n" +
            "(0) Salir del sistema");
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
        System.out.print("\nPresione ENTER para continuar... ");
        scanner.nextLine();
    }

    // ****************** GESTIÓN DE MÉDICOS ******************
    
    private static void menuGestionMedicos() {
        boolean volver = false;
        while (!volver) {
            System.out.println(
                "\n*** Gestión de Personal Médico ***\n" +
                "(1) Registrar un nuevo médico\n" +
                "(2) Listar todos los médicos\n" +
                "(3) Buscar médico por ID\n" +
                "(4) Buscar médicos por departamento\n" +
                "(5) Eliminar médico\n" +
                "(0) Volver al menú principal");
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
        
        // Asigna el ID automáticamente
        int id = admin.getSiguienteIDMedico();
        System.out.println("ID asignado: " + id);
        
        String nombre = leerTexto("Nombre completo: ");
        
        // Seleccionar departamento
        System.out.println("\nDepartamentos disponibles:");
        System.out.println(
            "(1) Medicina General\n" +
            "(2) Cirugía\n" +
            "(3) Enfermería\n" +
            "(4) Radiología\n" +
            "(5) Cardiología\n" +
            "(6) Pediatría\n" +
            "(7) Emergencias\n" +
            "(8) Neurología");
        int opcionDepto = leerEntero("Seleccione departamento: ");
        
        String departamento = "";
        switch (opcionDepto) {
            case 1: departamento = "Medicina General"; break;
            case 2: departamento = "Cirugía"; break;
            case 3: departamento = "Enfermería"; break;
            case 4: departamento = "Radiología"; break;
            case 5: departamento = "Cardiología"; break;
            case 6: departamento = "Pediatría"; break;
            case 7: departamento = "Emergencias"; break;
            case 8: departamento = "Neurología"; break;
            default:
                System.out.println("Departamento inválido.");
                return;
        }

        int aniosExp = leerEntero("Años de experiencia: ");
        double salarioBase = leerDouble("Salario base: Q");
        
        System.out.println(
            "\nTipos de especialización:\n" +
            "(1) Doctor General\n" +
            "(2) Cirujano\n" +
            "(3) Enfermero/a\n" +
            "(4) Radiólogo/a\n");
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
        admin.incrementarIDMedico();            // Incrementa el conteo de IDs de médicos
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
            System.out.println(
                "\n*** GESTIÓN DE CITAS MÉDICAS ***\n" +
                "(1) Crear nueva cita\n" +
                "(2) Ver todas las citas\n" +
                "(3) Buscar citas por paciente\n" +
                "(4) Buscar citas por médico\n" +
                "(5) Buscar citas por estado\n" +
                "(6) Cambiar estado de cita\n" +
                "(7) Cancelar cita\n" +
                "(0) Volver al menú principal");    
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
            System.out.println("\nCita creada exitosamente. :)");
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
        System.out.println(
            "\nEstados disponibles:\n" +
            "(1) PROGRAMADA\n" +
            "(2) CONFIRMADA\n" +
            "(3) EN_PROGRESO\n" +
            "(4) COMPLETADA\n" +
            "(5) CANCELADA\n" +
            "(6) REAGENDADA");
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
        
        System.out.println(
            "\nNuevo estado:\n" +
            "(1) PROGRAMADA\n" +
            "(2) CONFIRMADA\n" +
            "(3) EN_PROGRESO\n" +
            "(4) COMPLETADA\n" +
            "(5) CANCELADA\n" +
            "(6) REAGENDADA");
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
            
            String confirmacion = leerTexto("\n¿Está seguro? (Sí/No): ");
            
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

    // ****************** REAGENDAMIENTO ******************

    private static void menuReagendamiento() {
        boolean volver = false;
        while (!volver) {
            System.out.println(
                "\n*** SISTEMA DE REAGENDAMIENTO ***\n" +
                "(1) Reagendar una cita\n" +
                "(2) Ver historial de una cita específica\n" +
                "(3) Ver el historial de reagendamientos\n" +
                "(0) Volver al menú principal");
            
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1:
                    reagendarCita();
                    break;
                case 2:
                    verHistorialCitaEspecifica();
                    break;
                case 3:
                    verTodoHistorial();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private static void reagendarCita() {
        System.out.println("\n*** REAGENDAR CITA ***");
        
        int idCita = leerEntero("ID de la cita a reagendar: ");
        Cita cita = admin.buscarCitaPorID(idCita);
        
        if (cita == null) {
            System.out.println("Cita no encontrada.");
            pausa();
            return;
        }
        
        System.out.println("\nCita actual:");
        System.out.println(cita.getInfo());
        
        // Nueva fecha
        System.out.println("\nIngresar la fecha de la nueva cita...");
        int anio = leerEntero("Año (ej: 2025): ");
        int mes = leerEntero("Mes (1-12): ");
        int dia = leerEntero("Día (1-31): ");
        LocalDate nuevaFecha = LocalDate.of(anio, mes, dia);
        
        // Nueva hora
        System.out.println("\nIngresar la hora de la nueva cita...");
        int hora = leerEntero("Hora (0-23): ");
        int minuto = leerEntero("Minuto (0-59): ");
        LocalTime nuevaHora = LocalTime.of(hora, minuto);
        
        // ¿Cambiar médico?
        String cambiarMedico = leerTexto("\n¿Desea cambiar de médico? (Sí/No): ");
        Integer nuevoIdMedico = null;
        
        if (cambiarMedico.equalsIgnoreCase("si")) {
            System.out.println("\nMédicos disponibles:");
            ArrayList<Medico> medicos = admin.getMedicos();
            for (Medico medico : medicos) {
                System.out.println("ID: " + medico.getID() + " - " + medico.getNombre() + 
                                 " (" + medico.getDept() + ")");
            }
            nuevoIdMedico = leerEntero("\nID del nuevo médico: ");
        }
        
        // Ingresar el motivo del reagendamiento
        String motivo = leerTexto("Motivo del reagendamiento: ");
        
        // Intentar reagendar
        boolean exito = admin.reagendarCita(idCita, nuevaFecha, nuevaHora, nuevoIdMedico, motivo);
        
        if (exito) {
            System.out.println("\nCita reagendada exitosamente. :)");
        } else {
            System.out.println("\nNo se pudo reagendar la cita.");
            System.out.println("Posiblemente por conflicto de horario o médico no encontrado.");
        }
        pausa();
    }
    
    private static void verHistorialCitaEspecifica() {
        int idCita = leerEntero("\nID de la cita: ");
        ArrayList<Historial> historial = admin.getHistorialCita(idCita);
        
        if (historial.isEmpty()) {
            System.out.println("No hay cambios registrados para esta cita.");
        } else {
            System.out.println("\n*** HISTORIAL DE CITA #" + idCita + " ***");
            for (Historial h : historial) {
                System.out.println("\n" + h.getInfo());
            }
        }
        pausa();
    }
    
    private static void verTodoHistorial() {
        ArrayList<Historial> historial = admin.getTodoHistorial();
        
        if (historial.isEmpty()) {
            System.out.println("\nNo hay reagendamientos registrados.");
        } else {
            System.out.println("\n*** HISTORIAL COMPLETO DE REAGENDAMIENTOS ***");
            for (Historial h : historial) {
                System.out.println("\n" + h.getInfo());
            }
        }
        pausa();
    }

    // ****************** REPORTES FINANCIEROS ******************
    
    private static void menuReportesFinancieros() {
        boolean volver = false;
        while (!volver) {
            System.out.println(
                "\n*** REPORTES FINANCIEROS ***\n" +
                "(1) Calcular salario de un médico específico\n" +
                "(2) Reporte de nómina por departamento\n" +
                "(3) Nómina total del hospital\n" +
                "(4) Desgloce de médicos mejor pagados\n" +
                "(0) Volver al menú principal");
            
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1:
                    calcularSalarioMedico();
                    break;
                case 2:
                    reporteNominaDepartamento();
                    break;
                case 3:
                    nominaTotalHospital();
                    break;
                case 4:
                    topMedicosMejorPagados();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private static void calcularSalarioMedico() {
        int id = leerEntero("\nID del médico: ");
        Medico medico = admin.buscarMedicoPorID(id);
        
        if (medico == null) {
            System.out.println("Médico no encontrado.");
        } else {
            System.out.println("\n*** INFORMACIÓN SALARIAL ***");
            System.out.println("Médico: " + medico.getNombre());
            System.out.println("Departamento: " + medico.getDept());
            System.out.println("Salario base: Q" + String.format("%.2f", medico.getSalario()));
            System.out.println("\n>>> SALARIO TOTAL: Q" + 
                String.format("%.2f", medico.calcularSalario()) + " <<<");
        }
        pausa();
    }
    
    private static void reporteNominaDepartamento() {
        String depto = leerTexto("\nNombre del departamento: ");
        ArrayList<Medico> medicos = admin.buscarPorDepartamento(depto);
        
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos en ese departamento.");
        } else {
            System.out.println("\n*** NÓMINA DEL DEPARTAMENTO: " + depto + " ***");
            double totalDepto = 0;
            
            for (Medico medico : medicos) {
                double salario = medico.calcularSalario();
                System.out.println(medico.getNombre() + ": Q" + 
                    String.format("%.2f", salario));
                totalDepto += salario;
            }
            
            System.out.println("───────────────────────────────");
            System.out.println("TOTAL DEPARTAMENTO: Q" + 
                String.format("%.2f", totalDepto));
        }
        pausa();
    }
    
    private static void nominaTotalHospital() {
        double total = admin.calcularNominaTotal();
        ArrayList<Medico> medicos = admin.getMedicos();
        
        System.out.println("\n*** NÓMINA TOTAL DEL HOSPITAL ***");
        System.out.println("Total de médicos: " + medicos.size());
        
        if (!medicos.isEmpty()) {
            System.out.println("\nDesglose por médico:");
            for (Medico medico : medicos) {
                System.out.println(medico.getNombre() + " (" + medico.getDept() + "): Q" +
                    String.format("%.2f", medico.calcularSalario()));
            }
        }
        
        System.out.println("\n═══════════════════════════════════");
        System.out.println(">>> NÓMINA TOTAL: Q" + String.format("%.2f", total) + " <<<");
        System.out.println("═══════════════════════════════════");
        pausa();
    }
    
    private static void topMedicosMejorPagados() {
        int cantidad = leerEntero("\n¿Cuántos médicos desea ver? ");
        ArrayList<Medico> medicosOrdenados = admin.getMedicosPorSalario();
        
        if (medicosOrdenados.isEmpty()) {
            System.out.println("No hay médicos registrados.");
        } else {
            System.out.println("\n*** " + cantidad + " MÉDICOS MEJOR PAGADOS ***");
            int limite = Math.min(cantidad, medicosOrdenados.size());
            
            for (int i = 0; i < limite; i++) {
                Medico medico = medicosOrdenados.get(i);
                System.out.println((i + 1) + ". " + medico.getNombre() + 
                    " (" + medico.getDept() + ")");
                System.out.println("   Salario: Q" + 
                    String.format("%.2f", medico.calcularSalario()));
            }
        }
        pausa();
    }

    // ****************** REPORTES Y ANÁLISIS ******************
    
    private static void menuReportesAnalisis() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n*** REPORTES Y ANÁLISIS ***");
            System.out.println("(1) Reporte completo de personal");
            System.out.println("(2) Estadísticas por departamento");
            System.out.println("(3) Reporte de citas por estado");
            System.out.println("(4) Análisis de eficiencia del sistema");
            System.out.println("(0) Volver al menú principal");
            
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1:
                    reporteCompletoPersonal();
                    break;
                case 2:
                    estadisticasPorDepartamento();
                    break;
                case 3:
                    reporteCitasPorEstado();
                    break;
                case 4:
                    analisisEficiencia();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private static void reporteCompletoPersonal() {
        ArrayList<Medico> medicos = admin.getMedicos();
        
        if (medicos.isEmpty()) {
            System.out.println("\nNo hay médicos registrados.");
        } else {
            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("║        REPORTE COMPLETO DE PERSONAL            ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println("Total de médicos: " + medicos.size());
            System.out.println();
            
            for (Medico medico : medicos) {
                System.out.println("───────────────────────────────────────");
                System.out.println(medico.getInfo());
                System.out.println("Salario calculado: Q" + 
                    String.format("%.2f", medico.calcularSalario()));
                
                // Información específica según tipo
                if (medico instanceof Doctor) {
                    Doctor doc = (Doctor) medico;
                    System.out.println("Especialización: " + doc.getEspecializacion());
                    System.out.println("Consultas realizadas: " + doc.getNumConsultas());
                } else if (medico instanceof Cirujano) {
                    Cirujano cir = (Cirujano) medico;
                    System.out.println("Tipos de operaciones: " + cir.getTiposOperaciones());
                    System.out.println("Horas de cirugía realizadas: " + cir.getHorasCirugiaRealizadas());
                } else if (medico instanceof Enfermero) {
                    Enfermero enf = (Enfermero) medico;
                    System.out.println("Turno: " + enf.getTipoTurno());
                    System.out.println("Nivel: " + enf.getNivelCertificacion());
                } else if (medico instanceof Radiologo) {
                    Radiologo rad = (Radiologo) medico;
                    System.out.println("Equipos certificados: " + rad.getCertificados());
                    System.out.println("Estudios realizados: " + rad.getNumEstudiosRealizados());
                }
            }
            System.out.println("───────────────────────────────────────");
        }
        pausa();
    }
    
    private static void estadisticasPorDepartamento() {
        String depto = leerTexto("\nNombre del departamento: ");
        ArrayList<Medico> medicos = admin.buscarPorDepartamento(depto);
        
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos en ese departamento.");
        } else {
            System.out.println("\n*** ESTADÍSTICAS: " + depto + " ***");
            System.out.println("Total de médicos: " + medicos.size());
            
            double totalSalarios = 0;
            int totalExperiencia = 0;
            
            for (Medico medico : medicos) {
                totalSalarios += medico.calcularSalario();
                totalExperiencia += medico.getAniosExp();
            }
            
            double promedioSalario = totalSalarios / medicos.size();
            double promedioExperiencia = (double) totalExperiencia / medicos.size();
            
            System.out.println("Nómina total del departamento: Q" + 
                String.format("%.2f", totalSalarios));
            System.out.println("Salario promedio: Q" + 
                String.format("%.2f", promedioSalario));
            System.out.println("Experiencia promedio: " + 
                String.format("%.1f", promedioExperiencia) + " años");
        }
        pausa();
    }
    
    private static void reporteCitasPorEstado() {
        System.out.println("\n*** REPORTE DE CITAS POR ESTADO ***");
        
        int programadas = admin.contarCitasPorEstado(EstadoCita.PROGRAMADA);
        int confirmadas = admin.contarCitasPorEstado(EstadoCita.CONFIRMADA);
        int enProgreso = admin.contarCitasPorEstado(EstadoCita.EN_PROGRESO);
        int completadas = admin.contarCitasPorEstado(EstadoCita.COMPLETADA);
        int canceladas = admin.contarCitasPorEstado(EstadoCita.CANCELADA);
        int reagendadas = admin.contarCitasPorEstado(EstadoCita.REAGENDADA);
        
        int total = admin.getCitas().size();
        
        System.out.println("\nTotal de citas: " + total);
        System.out.println("───────────────────────────────");
        System.out.println("PROGRAMADA:   " + programadas);
        System.out.println("CONFIRMADA:   " + confirmadas);
        System.out.println("EN_PROGRESO:  " + enProgreso);
        System.out.println("COMPLETADA:   " + completadas);
        System.out.println("CANCELADA:    " + canceladas);
        System.out.println("REAGENDADA:   " + reagendadas);
        pausa();
    }
    
    private static void analisisEficiencia() {
        ArrayList<Cita> citas = admin.getCitas();
        
        if (citas.isEmpty()) {
            System.out.println("\nNo hay citas para analizar.");
            pausa();
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║      ANÁLISIS DE EFICIENCIA DEL SISTEMA        ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        int total = citas.size();
        int programadas = admin.contarCitasPorEstado(EstadoCita.PROGRAMADA);
        int confirmadas = admin.contarCitasPorEstado(EstadoCita.CONFIRMADA);
        int enProgreso = admin.contarCitasPorEstado(EstadoCita.EN_PROGRESO);
        int completadas = admin.contarCitasPorEstado(EstadoCita.COMPLETADA);
        int canceladas = admin.contarCitasPorEstado(EstadoCita.CANCELADA);
        int reagendadas = admin.contarCitasPorEstado(EstadoCita.REAGENDADA);
        
        double porcCompletadas = admin.calcularPorcentajeCompletadas();
        double porcCanceladas = admin.calcularPorcentajeCanceladas();
        
        System.out.println("\nTOTAL DE CITAS: " + total);
        System.out.println("\nDistribución por estado:");
        System.out.println("  Programadas:  " + programadas + " (" + 
            String.format("%.1f", (programadas * 100.0 / total)) + "%)");
        System.out.println("  Confirmadas:  " + confirmadas + " (" + 
            String.format("%.1f", (confirmadas * 100.0 / total)) + "%)");
        System.out.println("  En progreso:  " + enProgreso + " (" + 
            String.format("%.1f", (enProgreso * 100.0 / total)) + "%)");
        System.out.println("  Completadas:  " + completadas + " (" + 
            String.format("%.1f", porcCompletadas) + "%)");
        System.out.println("  Canceladas:   " + canceladas + " (" + 
            String.format("%.1f", porcCanceladas) + "%)");
        System.out.println("  Reagendadas:  " + reagendadas + " (" + 
            String.format("%.1f", (reagendadas * 100.0 / total)) + "%)");
        
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("INDICADORES CLAVE:");
        System.out.println("  ✓ Tasa de completación: " + String.format("%.2f", porcCompletadas) + "%");
        System.out.println("  ✗ Tasa de cancelación: " + String.format("%.2f", porcCanceladas) + "%");
        
        if (porcCompletadas >= 70) {
            System.out.println("\n>>> Sistema funcionando EFICIENTEMENTE <<<");
        } else if (porcCompletadas >= 50) {
            System.out.println("\n>>> Sistema funcionando ACEPTABLEMENTE <<<");
        } else {
            System.out.println("\n>>> Sistema necesita MEJORAS <<<");
        }
        System.out.println("══════════════════════════════════════════════");
        
        pausa();
    }
}

// FIN DE PROGRAMA