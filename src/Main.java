import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine().trim();

            if (linea.equals("salir")) {
                break;
            }

            System.out.println(ejecutar(hospital, linea));
        }

        scanner.close();
    }

    private static String ejecutar(Hospital hospital, String linea) {
        String[] partes = linea.split("\\s+");

        try {
            switch (partes[0]) {
                case "ingresar":
                    return ingresar(hospital, partes);

                case "vacunar":
                    if (partes.length != 2) {
                        return "ERROR: datos invalidos";
                    }
                    return hospital.vacunar(partes[1]);

                case "asignar":
                    if (partes.length != 2) {
                        return "ERROR: datos invalidos";
                    }
                    return hospital.asignar(partes[1]);

                case "medicar":
                    if (partes.length != 3) {
                        return "ERROR: datos invalidos";
                    }

                    double mg;

                    try {
                        mg = Double.parseDouble(partes[2]);
                    } catch (NumberFormatException e) {
                        mg = Double.NaN;
                    }

                    return hospital.medicar(partes[1], mg);

                case "alta":
                    if (partes.length != 2) {
                        return "ERROR: datos invalidos";
                    }
                    return hospital.alta(partes[1]);

                case "reporte":
                    if (partes.length != 1) {
                        return "ERROR: datos invalidos";
                    }
                    return hospital.reporte();

                default:
                    return "ERROR: comando desconocido";
            }
        } catch (IllegalArgumentException e) {
            return "ERROR: datos invalidos";
        }
    }

    private static String ingresar(Hospital hospital, String[] partes) {
        if (partes.length < 2) {
            return "ERROR: datos invalidos";
        }

        switch (partes[1]) {
            case "perro":
                if (partes.length != 7) {
                    return "ERROR: datos invalidos";
                }

                if (!partes[6].equalsIgnoreCase("si")
                        && !partes[6].equalsIgnoreCase("no")) {
                    return "ERROR: datos invalidos";
                }

                Perro perro = new Perro(
                        partes[2],
                        Double.parseDouble(partes[3]),
                        Integer.parseInt(partes[4]),
                        partes[5],
                        partes[6].equalsIgnoreCase("si")
                );

                return hospital.ingresar(perro);

            case "gato":
                if (partes.length != 5) {
                    return "ERROR: datos invalidos";
                }

                Gato gato = new Gato(
                        partes[2],
                        Double.parseDouble(partes[3]),
                        Integer.parseInt(partes[4])
                );

                return hospital.ingresar(gato);

            case "ave":
                if (partes.length != 6) {
                    return "ERROR: datos invalidos";
                }

                AveExotica ave = new AveExotica(
                        partes[2],
                        Double.parseDouble(partes[3]),
                        Integer.parseInt(partes[4]),
                        Double.parseDouble(partes[5])
                );

                return hospital.ingresar(ave);

            default:
                return "ERROR: datos invalidos";
        }
    }
}