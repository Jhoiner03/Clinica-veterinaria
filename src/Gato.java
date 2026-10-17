public class Gato extends Paciente{

    private int nivelEstres;

    public Gato(String nombre, double peso, int edad){
        super(nombre, peso, edad, 5);

        nivelEstres(0);
    }

    public int getNivelEstres(){
        return nivelEstres;
    }

    public void setNivelEstres(int nivelEstres){
        if (nivelEstres < 0 || nivelEstres > 10){
            throw new IllegalArgumentException();
        }
        this.nivelEstres = nivelEstres;
    }
    @Override
    public String getTipo(){
        return Gato;
    }
}
