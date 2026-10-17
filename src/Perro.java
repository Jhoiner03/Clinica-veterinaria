public class Perro extends Paciente{

    private String raza;
    private boolean vacunado;

    public Perro(String nombre, double peso, int edad, String raza, boolean vacunado){
        super(nombre, peso, edad, 10);

        setRaza(raza);
        setVacunado(vacunado);

        if (!vacunado){
            setEstado("Cuarentena");
        }
    }

    public String getRaza(){
        return raza;
    }

    public void setRaza(String raza){
        if (raza == null || raza.trim().isEmpty()){
            throw new IllegalArgumentException();
        }
        this.raza = raza.trim();
    }

    public boolean isVacunado(){
        return vacunado;
    }

    public void setVacunado(boolean vacunado){
        this.vacunado = vacunado;
    }
    @Override
    public String getTipo(){
        return "Perro";
    }
}


