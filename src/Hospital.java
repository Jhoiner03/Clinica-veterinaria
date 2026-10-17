import java.util.Locale;

public class Hospital {
    private Paciente[] pacientes;
    private int contador;
    private Jaula[] jaulas;

    public Hospital() {
        pacientes = new Paciente[10];
        jaulas = new Jaula[6];
        contador = 0;
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        jaulas[0] = new Jaula(1, "S", "A");
        jaulas[1] = new Jaula(2, "M", "A");
        jaulas[2] = new Jaula(3, "M", "A");
        jaulas[3] = new Jaula(4, "L", "A");
        jaulas[4] = new Jaula(5, "S", "B");
        jaulas[5] = new Jaula(6, "M", "B");

        pacientes[contador++] =
                new Perro("Rocky", 30, 4, "labrador", true);

        pacientes[contador++] = new Gato("Misifu", 4, 3);

        pacientes[contador++] = new AveExotica("Kiwi", 0.8, 2, 70);
    }

    private int buscar(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public String ingresar(Paciente paciente) {
        // El constructor ya validó los datos.
        if (paciente == null) {
            return "ERROR: datos invalidos";
        }

        if (buscar(paciente.getNombre()) != -1) {
            return "ERROR: nombre duplicado";
        }

        if (contador == pacientes.length) {
            return "ERROR: hospital lleno";
        }

        pacientes[contador] = paciente;
        contador++;

        if (paciente.getEstado().equals("CUARENTENA")) {
            return "OK: " + paciente.getNombre()
                    + " ingresado en CUARENTENA";
        }

        return "OK: " + paciente.getNombre() + " ingresado";
    }

    public String vacunar(String nombre) {
        int indice = buscar(nombre);

        if (indice == -1) {
            return "ERROR: paciente no encontrado";
        }

        Paciente paciente = pacientes[indice];

        if (!(paciente instanceof Perro)) {
            return "ERROR: solo aplica a perros";
        }

        Perro perro = (Perro) paciente;

        if (perro.isVacunado()) {
            return "ERROR: ya vacunado";
        }

        perro.setVacunado(true);
        perro.setEstado("INGRESADO");

        return "OK: " + perro.getNombre() + " vacunado";
    }

    public String asignar(String nombre) {
        int indice = buscar(nombre);

        if (indice == -1) {
            return "ERROR: paciente no encontrado";
        }

        Paciente paciente = pacientes[indice];

        if (paciente.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }

        if (paciente.getJaula() != null) {
            return "ERROR: paciente ya tiene jaula";
        }

        String tamano;

        if (paciente.getPeso() < 5) {
            tamano = "S";
        } else if (paciente.getPeso() <= 25) {
            tamano = "M";
        } else {
            tamano = "L";
        }

        String zona = "A";

        if (paciente instanceof AveExotica) {
            zona = "B";
            AveExotica ave = (AveExotica) paciente;

            if (ave.getEnvergadura() > 60 && tamano.equals("S")) {
                tamano = "M";
            }
        }

        for (int i = 0; i < jaulas.length; i++) {
            Jaula jaula = jaulas[i];

            if (jaula.estaLibre()
                    && jaula.getZona().equals(zona)
                    && jaula.getTamano().equals(tamano)) {

                jaula.setOcupante(paciente);
                paciente.setJaula(jaula);

                return "OK: " + paciente.getNombre()
                        + " asignado a jaula " + jaula.getNumero();
            }
        }

        return "ERROR: no hay jaula compatible";
    }

    public String medicar(String nombre, double mg) {
        int indice = buscar(nombre);

        if (indice == -1) {
            return "ERROR: paciente no encontrado";
        }

        if (!Double.isFinite(mg) || mg <= 0) {
            return "ERROR: datos invalidos";
        }

        Paciente paciente = pacientes[indice];

        if (paciente.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }

        if (paciente.getJaula() == null) {
            return "ERROR: paciente sin jaula";
        }

        if (paciente instanceof Gato) {
            Gato gato = (Gato) paciente;

            if (gato.getNivelEstres() >= 8) {
                return "ERROR: gato con estres alto";
            }
        }

        if (mg > paciente.getDosisMaxima()) {
            return "ERROR: dosis excede el maximo";
        }

        double nuevoAcumulado = paciente.getDosisAcumulada() + mg;

        if (nuevoAcumulado > 3 * paciente.getDosisMaxima()) {
            return "ERROR: tope acumulado superado";
        }

        paciente.setDosisAcumulada(nuevoAcumulado);
        paciente.setEstado("EN_TRATAMIENTO");

        if (paciente instanceof Gato) {
            Gato gato = (Gato) paciente;
            int nuevoEstres = Math.min(10, gato.getNivelEstres() + 2);
            gato.setNivelEstres(nuevoEstres);
        }

        return "OK: " + paciente.getNombre()
                + " medicado con " + mg + " mg";
    }

    public String alta(String nombre) {
        int indice = buscar(nombre);

        if (indice == -1) {
            return "ERROR: paciente no encontrado";
        }

        Paciente paciente = pacientes[indice];

        if (!paciente.getEstado().equals("EN_TRATAMIENTO")) {
            return "ERROR: paciente no apto para alta";
        }

        if (paciente.getJaula() != null) {
            paciente.getJaula().setOcupante(null);
            paciente.setJaula(null);
        }

        // Desplazar los pacientes para no dejar huecos.
        for (int i = indice; i < contador - 1; i++) {
            pacientes[i] = pacientes[i + 1];
        }

        contador--;
        pacientes[contador] = null;

        return "OK: " + paciente.getNombre() + " dado de alta";
    }

    public String reporte() {
        String texto = "--- REPORTE ---";
        texto += "\nPacientes: " + contador + "/10";

        for (int i = 0; i < contador; i++) {
            Paciente paciente = pacientes[i];
            String numeroJaula = "-";

            if (paciente.getJaula() != null) {
                numeroJaula = String.valueOf(
                        paciente.getJaula().getNumero()
                );
            }

            String peso = String.format(
                    Locale.US, "%.1f", paciente.getPeso()
            );

            texto += "\n" + paciente.getNombre()
                    + " | " + paciente.getTipo()
                    + " | " + peso + " kg"
                    + " | " + paciente.getEstado()
                    + " | jaula: " + numeroJaula;
        }

        int libres = 0;

        for (int i = 0; i < jaulas.length; i++) {
            if (jaulas[i].estaLibre()) {
                libres++;
            }
        }

        texto += "\nJaulas libres: " + libres;
        return texto;
    }
}