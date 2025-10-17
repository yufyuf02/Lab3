// Subclase de Médico, para la especialización "Enfermero/a"

public class Enfermero extends Medico {

    private String tipoTurno;  // (Diurno/Nocturno)
    private String nivelCertificacion;  // (Auxiliar/Técnico/etc.)
    private static final double BONO_NOCTURNO = 1500.0;
    
    public Enfermero(int ID, String nombre, String departamento, int aniosExp,
    double salario, String tipoTurno, String nivelCertificacion) {
        super(ID, nombre, departamento, aniosExp, salario);
        this.tipoTurno = tipoTurno;
        this.nivelCertificacion = nivelCertificacion;
    }
    
    @Override
    public double calcularSalario() {
        double salarioTotal = salario;
        if (tipoTurno.equalsIgnoreCase("Nocturno")) {
            salarioTotal += BONO_NOCTURNO;
        }
        return salarioTotal;
    }
    
    // Getters
    public String getTipoTurno() { return tipoTurno; }
    public String getNivelCertificacion() { return nivelCertificacion; }
    
    // Setters
    public void setTipoTurno(String tipoTurno) {
        this.tipoTurno = tipoTurno;
    }
}
