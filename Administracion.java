// La clase con mayor acceso al sistema

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Administracion {
    
    private ArrayList<Medico> medicos;
    private ArrayList<Cita> citas;
    private ArrayList<Historial> historialCambios;
    private int siguienteIDCita;
    private int siguienteIDMedico;
    
    // Constructor
    public Administracion() {
        medicos = new ArrayList<>();
        citas = new ArrayList<>();
        historialCambios = new ArrayList<>();
        siguienteIDCita = 1;
        siguienteIDMedico = 5; // Empezamos con 5 porque quemamos cinco médicos al iniciar
    }
    
    // ****************** GESTIÓN DE MÉDICOS ******************
    
    // Registrar un nuevo médico
    public void registrarMedico(Medico medico) {
        medicos.add(medico);
    }
    
    // Buscar médico por ID
    public Medico buscarMedicoPorID(int ID) {
        for (Medico medico : medicos) {
            if (medico.getID() == ID) {
                return medico;
            }
        }
        return null;
    }
    
    // Obtener lista de médicos
    public ArrayList<Medico> getMedicos() {
        return medicos;
    }
    
    // Buscar médicos por departamento
    public ArrayList<Medico> buscarPorDepartamento(String departamento) {
        ArrayList<Medico> resultado = new ArrayList<>();
        for (Medico medico : medicos) {
            if (medico.getDept().equalsIgnoreCase(departamento)) {
                resultado.add(medico);
            }
        }
        return resultado;
    }
    
    // Eliminar médico
    public boolean eliminarMedico(int ID) {
        Medico medico = buscarMedicoPorID(ID);
        if (medico != null) {
            medicos.remove(medico);
            return true;
        }
        return false;
    }

    public int getSiguienteIDMedico() {
        return siguienteIDMedico;
    }
    
    public void incrementarIDMedico() {
        siguienteIDMedico++;
    }
    
    // ****************** GESTIÓN DE CITAS ******************
    
    // Crear nueva cita (retorna null si hay error)
    public Cita crearCita(String paciente, int idMedico, LocalDate fecha, 
                          LocalTime hora, TipoCita tipo) {
        Medico medico = buscarMedicoPorID(idMedico);
        if (medico == null) {
            return null;
        }
        
        // Verificar conflictos de horario
        if (existeConflictoHorario(medico, fecha, hora)) {
            return null;
        }
        
        Cita nuevaCita = new Cita(siguienteIDCita, paciente, medico, fecha, hora, tipo);
        siguienteIDCita++; 
        citas.add(nuevaCita);
        return nuevaCita;
    }
    
    // Verificar si existe conflicto de horario
    public boolean existeConflictoHorario(Medico medico, LocalDate fecha, LocalTime hora) {
        for (Cita cita : citas) {
            if (cita.getMedico().getID() == medico.getID() &&
                cita.getFecha().equals(fecha) &&
                cita.getHora().equals(hora) &&
                (cita.getEstado() == EstadoCita.PROGRAMADA || 
                 cita.getEstado() == EstadoCita.CONFIRMADA)) {
                return true;
            }
        }
        return false;
    }
    
    // Buscar cita por ID
    public Cita buscarCitaPorID(int ID) {
        for (Cita cita : citas) {
            if (cita.getID() == ID) {
                return cita;
            }
        }
        return null;
    }
    
    // Obtener lista de citas
    public ArrayList<Cita> getCitas() {
        return citas;
    }
    
    // Obtener citas por estado
    public ArrayList<Cita> getCitasPorEstado(EstadoCita estado) {
        ArrayList<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getEstado() == estado) {
                resultado.add(cita);
            }
        }
        return resultado;
    }
    
    // Obtener citas por médico
    public ArrayList<Cita> getCitasPorMedico(int idMedico) {
        ArrayList<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getMedico().getID() == idMedico) {
                resultado.add(cita);
            }
        }
        return resultado;
    }
    
    // Obtener citas por paciente
    public ArrayList<Cita> getCitasPorPaciente(String nombrePaciente) {
        ArrayList<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getPaciente().equalsIgnoreCase(nombrePaciente)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }
    
    // Cancelar cita
    public boolean cancelarCita(int idCita) {
        Cita cita = buscarCitaPorID(idCita);
        if (cita != null) {
            cita.setEstado(EstadoCita.CANCELADA);
            return true;
        }
        return false;
    }
    
    // Cambiar estado de cita
    public boolean cambiarEstadoCita(int idCita, EstadoCita nuevoEstado) {
        Cita cita = buscarCitaPorID(idCita);
        if (cita != null) {
            cita.setEstado(nuevoEstado);
            return true;
        }
        return false;
    }
    
    // ****************** REAGENDAMIENTO ******************
    
    // Reagendar cita (retorna true si tuvo éxito)
    public boolean reagendarCita(int idCita, LocalDate nuevaFecha, 
                                 LocalTime nuevaHora, Integer nuevoIdMedico, 
                                 String motivo) {
        Cita cita = buscarCitaPorID(idCita);
        if (cita == null) {
            return false;
        }
        
        // Guardar datos anteriores
        String fechaHoraAnterior = cita.getFecha() + " " + cita.getHora();
        String medicoAnterior = cita.getMedico().getNombre();
        
        // Buscar nuevo médico
        Medico nuevoMedico = null;
        if (nuevoIdMedico != null) {
            nuevoMedico = buscarMedicoPorID(nuevoIdMedico);
            if (nuevoMedico == null) {
                return false;
            }
        } else {
            nuevoMedico = cita.getMedico();
        }
        
        // Verificar conflictos
        if (existeConflictoHorario(nuevoMedico, nuevaFecha, nuevaHora)) {
            return false;
        }
        
        // Reagendar
        cita.setFecha(nuevaFecha);
        cita.setHora(nuevaHora);
        cita.setMedico(nuevoMedico);
        cita.setEstado(EstadoCita.REAGENDADA);
        
        // Guardar en historial
        String fechaHoraNueva = nuevaFecha + " " + nuevaHora;
        String medicoNuevo = nuevoMedico.getNombre();
        String fechaCambio = LocalDate.now() + " " + LocalTime.now();
        
        Historial historial = new Historial(idCita, fechaHoraAnterior,
        fechaHoraNueva, medicoAnterior, medicoNuevo, fechaCambio, motivo);
        historialCambios.add(historial);
        
        return true;
    }
    
    // Obtener historial de una cita específica
    public ArrayList<Historial> getHistorialCita(int idCita) {
        ArrayList<Historial> resultado = new ArrayList<>();
        for (Historial historial : historialCambios) {
            if (historial.getID() == idCita) {
                resultado.add(historial);
            }
        }
        return resultado;
    }
    
    // Obtener todo el historial
    public ArrayList<Historial> getTodoHistorial() {
        return historialCambios;
    }
    
    // ****************** REPORTES FINANCIEROS ******************
    
    // Calcular nómina total del hospital
    public double calcularNominaTotal() {
        double total = 0;
        for (Medico medico : medicos) {
            total += medico.calcularSalario();
        }
        return total;
    }
    
    // Calcular nómina por departamento
    public double calcularNominaDepartamento(String departamento) {
        double total = 0;
        ArrayList<Medico> medicosDepto = buscarPorDepartamento(departamento);
        for (Medico medico : medicosDepto) {
            total += medico.calcularSalario();
        }
        return total;
    }
    
    // Obtener médicos ordenados por salario (mayor a menor)
    public ArrayList<Medico> getMedicosPorSalario() {
        ArrayList<Medico> copia = new ArrayList<>(medicos);
        
        // Organizar
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - i - 1; j++) {
                if (copia.get(j).calcularSalario() < copia.get(j + 1).calcularSalario()) {
                    Medico temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }
        return copia;
    }
    
    // ****************** ESTADÍSTICAS ******************
    
    // Contar citas por estado
    public int contarCitasPorEstado(EstadoCita estado) {
        int contador = 0;
        for (Cita cita : citas) {
            if (cita.getEstado() == estado) {
                contador++;
            }
        }
        return contador;
    }
    
    // Calcular porcentaje de completadas
    public double calcularPorcentajeCompletadas() {
        if (citas.isEmpty()) return 0;
        int completadas = contarCitasPorEstado(EstadoCita.COMPLETADA);
        return (completadas * 100.0) / citas.size();
    }
    
    // Calcular porcentaje de canceladas
    public double calcularPorcentajeCanceladas() {
        if (citas.isEmpty()) return 0;
        int canceladas = contarCitasPorEstado(EstadoCita.CANCELADA);
        return (canceladas * 100.0) / citas.size();
    }
    
    // Obtener siguiente ID de cita
    public int getSiguienteIDCita() {
        return siguienteIDCita;
    }
}
