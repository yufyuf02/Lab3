// Clase base para todos los médicos

public abstract class Medico {
    protected int ID;
    protected String nombre;
    protected String departamento;
    protected int aniosExp;
    protected double salario;

    // Constructor
    public Medico (int ID, String nombre, String departamento, int aniosExp, double salario) {
        this.ID = ID;
        this.nombre = nombre;
        this.departamento = departamento;
        this.aniosExp = aniosExp;
        this.salario = salario;
    }

    // Getters
    public int getID() { return ID; }
    public String getNombre() { return nombre; }
    public String getDept() { return departamento; }
    public int getAniosExp() { return aniosExp; }
    public double getSalario() { return salario; }

    // Setters
    public void setDept(String departamento) {
        this.departamento = departamento;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }

    // ~~~~~~~~~~~~~~~~~~ Métodos ~~~~~~~~~~~~~~~~~~

    // Calcular el salario, diferente para cada especialización
    public abstract double calcularSalario();

    // Conseguir la información básica del médico
    public String getInfo() {
        return
            "ID: " + ID + 
            "Nombre: " + nombre + 
            "Departamento: " + departamento + 
            "Experiencia: " + aniosExp + " años" +
            "Salario: Q" + String.format("%.2f", salario); // Dos decimales
    }
}
