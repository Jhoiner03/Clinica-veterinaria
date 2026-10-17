public class AveExotica extends Paciente{

    private double envergadura;

    public AveExotica(String nombre, double peso, int edad, double envergadura){
        super(nombre, peso, edad, 3);
    }

    public double setEnvergadura(){
        return envergadura;
    }

    public void setEnvergadura(double envergadura){
        if (!Double.isFinite(envergadura) || envergadura <= 0){
            throw new IllegalArgumentException();
        }
        this.envergadura = envergadura;
    }

    @Override
    public String getTipo(){
        return "Ave";
    }
}
