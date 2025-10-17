// Subclase de Médico, para la especialización "Cirujano"

public class Cirujano extends Medico {

    private String tiposOperaciones;  // (Cardíaca/Ortopédica/etc.)
    private int horasCirugiaDisponibles;
    private double tarifaPorHora;
    private double bonoPorRiesgo;
    private int horasCirugiaRealizadas;
    
    public Cirujano(int ID, String nombre, String departamento, int aniosExp, 
                   double salario, String tiposOperaciones, 
                   int horasCirugiaDisponibles, double tarifaPorHora, 
                   double bonoPorRiesgo) {
        super(ID, nombre, departamento, aniosExp, salario);
        this.tiposOperaciones = tiposOperaciones;
        this.horasCirugiaDisponibles = horasCirugiaDisponibles;
        this.tarifaPorHora = tarifaPorHora;
        this.bonoPorRiesgo = bonoPorRiesgo;
        this.horasCirugiaRealizadas = 0;
    }
    
    @Override
    public double calcularSalario() {
        return salario + (horasCirugiaRealizadas * tarifaPorHora) + bonoPorRiesgo;
    }
    
    // Getters
    public String getTiposOperaciones() { return tiposOperaciones; }
    public int getHorasCirugiaDisponibles() { return horasCirugiaDisponibles; }
    public double getTarifaPorHora() { return tarifaPorHora; }
    public double getBonoPorRiesgo() { return bonoPorRiesgo; }
    public int getHorasCirugiaRealizadas() { return horasCirugiaRealizadas; }
    
    // Setters
    public void setHorasCirugiaRealizadas(int horas) {
        this.horasCirugiaRealizadas = horas;
    }
    
    // Para registrar horas de cirugía
    public void registrarHorasCirugia(int horas) {
        horasCirugiaRealizadas += horas;
    }
}