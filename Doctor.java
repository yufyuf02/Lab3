// Subclase de Médico, para la especialización "Doctor General"

public class Doctor extends Medico {

    private String especializacion;
    private int capPacientesDia;
    private double tarifaConsulta;
    private int numConsultas;  // Para calcular su salario

    public Doctor (int ID, String nombre, String departamento, int aniosExp, double salario,
    String especializacion, int capPacientesDia, double tarifaConsulta, int numConsultas) {
        super(ID, nombre, departamento, aniosExp, salario);         // El constructor permite que exista la subclase, el super llama a la clase base.
        this.especializacion = especializacion;
        this.capPacientesDia = capPacientesDia;
        this.tarifaConsulta = tarifaConsulta;
        this.numConsultas = 0;  // Inicia en 0
    }

    @Override
    public double calcularSalario() {
        return salario + (numConsultas * tarifaConsulta);
    }
    
    // Getters
    public String getEspecializacion() { return especializacion; }
    public int getCapPacientesDia() { return capPacientesDia; }
    public double getTarifaConsulta() { return tarifaConsulta; }
    public int getNumConsultas() { return numConsultas; }

    // Setters
    public void setNumConsultas(int numConsultas) {
        this.numConsultas = numConsultas;
    }
    
    // Para registrar consultas
    public void registrarConsulta() {
        numConsultas++;
    }
}