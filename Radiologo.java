// Subclase de Médico, para la especialización "Radiólogo/a"

public class Radiologo extends Medico {

    private String certificados;  // (RayosX, Tomografía, etc.)
    private double tarifaPorEstudio;
    private int numEstudiosRealizados;  // Para calcular salario
    
    public Radiologo(int ID, String nombre, String departamento, int aniosExp, 
                    double salario, String certificados, 
                    double tarifaPorEstudio) {
        super(ID, nombre, departamento, aniosExp, salario);
        this.certificados = certificados;
        this.tarifaPorEstudio = tarifaPorEstudio;
        this.numEstudiosRealizados = 0;
    }
    
    @Override
    public double calcularSalario() {
        return salario + (numEstudiosRealizados * tarifaPorEstudio);
    }
    
    // Getters
    public String getCertificados() { return certificados; }
    public double getTarifaPorEstudio() { return tarifaPorEstudio; }
    public int getNumEstudiosRealizados() { return numEstudiosRealizados; }
    
    // Setters
    public void setNumEstudiosRealizados(int numEstudios) {
        this.numEstudiosRealizados = numEstudios;
    }

    // Para registrar estudios
    public void registrarEstudio() {
        numEstudiosRealizados++;
    }
}
