public abstract class Paciente {
    private String nombre;
    private double peso;
    private int edad;
    private String estado;
    private double factorDosis;
    private double dosisAcumulada;
    private Jaula jaula;

    public Paciente(String nombre, double peso, int edad,
                    double factorDosis) {
        setNombre(nombre);
        setPeso(peso);
        setEdad(edad);
        this.factorDosis = factorDosis;
        this.estado = "INGRESADO";
        this.dosisAcumulada = 0;
        this.jaula = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.nombre = nombre.trim();
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        double maximo = this instanceof Perro ? 80 : 15;

        if (!Double.isFinite(peso) || peso <= 0 || peso > maximo) {
            throw new IllegalArgumentException();
        }

        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0 || edad > 30) {
            throw new IllegalArgumentException();
        }
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (!"INGRESADO".equals(estado)
                && !"CUARENTENA".equals(estado)
                && !"EN_TRATAMIENTO".equals(estado)) {
            throw new IllegalArgumentException();
        }

        this.estado = estado;
    }

    public double getFactorDosis() {
        return factorDosis;
    }

    public double getDosisMaxima() {
        return peso * factorDosis;
    }

    public double getDosisAcumulada() {
        return dosisAcumulada;
    }

    public void setDosisAcumulada(double dosisAcumulada) {
        if (!Double.isFinite(dosisAcumulada)
                || dosisAcumulada < 0
                || dosisAcumulada > 3 * getDosisMaxima()) {
            throw new IllegalArgumentException();
        }

        this.dosisAcumulada = dosisAcumulada;
    }

    public Jaula getJaula() {
        return jaula;
    }

    public void setJaula(Jaula jaula) {
        this.jaula = jaula;
    }

    public abstract String getTipo();
}