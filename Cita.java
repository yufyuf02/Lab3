// Clase para la gestión de citas médicas

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private int ID;
    private String paciente;
    private Medico medico;
    private LocalDate fecha;
    private LocalTime hora;
    private TipoCita tipo;
    private EstadoCita estado;

    // Constructor
    public Cita (int ID, String paciente, Medico medico,
    LocalDate fecha, LocalTime hora, TipoCita tipo) {
        this.ID = ID;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.estado = EstadoCita.PROGRAMADA; // Inicia como "PROGRAMADA"
    }

    // Getters
    public int getID() { return ID; }
    public String getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public TipoCita getTipo() { return tipo; }
    public EstadoCita getEstado() { return estado; }

    // Setters
    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    // ~~~~~~~~~~~~~~~~~~ Métodos ~~~~~~~~~~~~~~~~~~
    
    // Información de la cita
    public String getInfo() {
        return "Cita #" + ID + " | " +
               "Paciente: " + paciente + " | " +
               "Médico: " + medico.getNombre() + " | " +
               "Fecha: " + fecha +  " | " +
               "Hora: " + hora + " | " +
               "Tipo: " + tipo +  " | " +
               "Estado: " + estado;
    }

    // Cambiar el estado
    public void cambiarEstado(EstadoCita nuevoEstado) {
        this.estado = nuevoEstado;
    }
    
    // Reagendar
    public void reagendar(LocalDate nuevaFecha, LocalTime nuevaHora, Medico nuevoMedico) {
        this.fecha = nuevaFecha;
        this.hora = nuevaHora;
        if (nuevoMedico != null) {
            this.medico = nuevoMedico;
        }
        this.estado = EstadoCita.REAGENDADA;
    }
}
