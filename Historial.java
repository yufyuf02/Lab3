// Para llevar control de los cambios y reagendamientos

public class Historial {
    
    private int ID;
    private String fechaHoraAnterior;
    private String fechaHoraNueva;
    private String medicoAnterior;
    private String medicoNuevo;
    private String fechaCambio;  // Cuándo se hizo el cambio
    private String motivo;
    
    public Historial(int ID, String fechaHoraAnterior, String fechaHoraNueva,
    String medicoAnterior, String medicoNuevo, String fechaCambio, String motivo) {
        this.ID = ID;
        this.fechaHoraAnterior = fechaHoraAnterior;
        this.fechaHoraNueva = fechaHoraNueva;
        this.medicoAnterior = medicoAnterior;
        this.medicoNuevo = medicoNuevo;
        this.fechaCambio = fechaCambio;
        this.motivo = motivo;
    }
    
    // Getters
    public int getID() { return ID; }
    public String getFechaHoraAnterior() { return fechaHoraAnterior; }
    public String getFechaHoraNueva() { return fechaHoraNueva; }
    public String getMedicoAnterior() { return medicoAnterior; }
    public String getMedicoNuevo() { return medicoNuevo; }
    public String getFechaCambio() { return fechaCambio; }
    public String getMotivo() { return motivo; }
    
    public String getInfo() {
        return "Cambio en Cita #" + ID + 
               "Fecha del cambio: " + fechaCambio +
               "\n  Anteriormente: " + fechaHoraAnterior + " con " + medicoAnterior +
               "\n  Nuevo: " + fechaHoraNueva + " con " + medicoNuevo +
               "\n  Motivo: " + motivo;
    }
}
