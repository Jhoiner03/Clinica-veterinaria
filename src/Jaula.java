public class Jaula{
    private int numero;
    private String tamano;
    private String zona;
    private Paciente ocupante;

    public Jaula(int numero, String tamano, String zona){
        setNumero(numero);
        setTamano(tamano);
        setZona(zona);
        this.ocupante = null;
    }

    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero){
        if (numero <= 0){
            throw new IllegalArgumentException();
        }
        this.numero = numero;
    }

    public String getTamano(){
        return tamano;
    }

    public void setTamano(String tamano){
        if (!"S".equals(tamano) && !"M".equals(tamano) && !"L".equals(tamano)){
            throw new IllegalArgumentException();
        }
        this.tamano = tamano;
    }

    public String getZona(){
        return zona;
    }

    public void setZona(String zona){
        if (!"A".equals(zona) && !"B".equals(zona)){
            throw new IllegalArgumentException();
        }
        this.zona = zona;
    }

    public Paciente getOcupante(){
        return ocupante;
    }

    public void setOcupante(Paciente ocupante){
        this.ocupante = ocupante;
    }

    public boolean estaLibre(){
        return ocupante == null;
    }

}
