package unison;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class read {
    private static final String nombreArchivoEntrada = "C:/Users/marco/IdeaProjects/Random_File/src/vendors.csv";
    private static final String nombreArchivoSalida = "C:/Users/marco/IdeaProjects/Random_File/src/vendors-data.dat";
    private static final List<Vendedor> vendedores = new ArrayList<>();
    private static final Map<String, List<Vendedor>> vendedoresPorZona = new HashMap<>();

    public static void main(String[] args) {
        cargarDatos();

        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Agregar Vendedor");
            System.out.println("2. Borrar Vendedor");
            System.out.println("3. Modificar Vendedor");
            System.out.println("4. Consultar Vendedores por Zona");
            System.out.println("5. Ver Todos los Vendedores");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    agregarVendedor(scanner);
                    break;
                case 2:
                    borrarVendedor(scanner);
                    break;
                case 3:
                    modificarVendedor(scanner);
                    break;
                case 4:
                    consultarPorZona(scanner);
                    break;
                case 5:
                    verTodosLosVendedores();
                    break;
                case 6:
                    guardarDatos();
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 6);
    }

    // Carga datos desde el archivo CSV a la lista de vendedores y el mapa de vendedoresPorZona
    private static void cargarDatos() {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivoSalida))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 5) {
                    int codigo = Integer.parseInt(campos[0].trim());
                    String nombre = campos[1].trim();
                    String fechaNacimiento = campos[2].trim();
                    String veCodZona = campos[3].trim();
                    double ventasMensuales = Double.parseDouble(campos[4].trim());

                    Vendedor vendedor = new Vendedor(codigo, nombre, fechaNacimiento, veCodZona, ventasMensuales);
                    vendedores.add(vendedor);
                    vendedoresPorZona.computeIfAbsent(veCodZona, k -> new ArrayList<>()).add(vendedor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guarda datos de la lista de vendedores de nuevo en el archivo CSV
    private static void guardarDatos() {
        try (FileWriter fw = new FileWriter(nombreArchivoSalida)) {
            for (Vendedor vendedor : vendedores) {
                fw.write(vendedor.toCSVString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Agrega un nuevo vendedor a la lista y al mapa de vendedoresPorZona
    private static void agregarVendedor(Scanner scanner) {
        System.out.print("Ingrese el código del vendedor: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        if (vendedores.stream().anyMatch(v -> v.getCodigo() == codigo)) {
            System.out.println("El código de vendedor ya existe. Por favor, elija otro código.");
            return;
        }

        System.out.print("Ingrese el nombre del vendedor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la fecha de nacimiento del vendedor (dd/mm/yy): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Ingrese la zona del vendedor: ");
        String zona = scanner.nextLine();
        System.out.print("Ingrese el total de ventas mensuales del vendedor: ");
        double ventasMensuales = scanner.nextDouble();

        Vendedor nuevoVendedor = new Vendedor(codigo, nombre, fechaNacimiento, zona, ventasMensuales);
        vendedores.add(nuevoVendedor);
        vendedoresPorZona.computeIfAbsent(zona, k -> new ArrayList<>()).add(nuevoVendedor);
        System.out.println("Vendedor agregado exitosamente.");
    }

    // Borra un vendedor de la lista y del mapa de vendedoresPorZona
    private static void borrarVendedor(Scanner scanner) {
        System.out.print("Ingrese el código del vendedor a borrar: ");
        int codigo = scanner.nextInt();

        Optional<Vendedor> vendedorEncontrado = vendedores.stream()
                .filter(v -> v.getCodigo() == codigo)
                .findFirst();

        if (vendedorEncontrado.isPresent()) {
            System.out.print("¿Está seguro de que desea borrar a este vendedor? (S/N): ");
            scanner.nextLine(); // Consumir la nueva línea antes de leer la respuesta
            String confirmacion = scanner.nextLine();
            if (confirmacion.equalsIgnoreCase("S")) {
                Vendedor vendedor = vendedorEncontrado.get();
                vendedores.remove(vendedor);
                vendedoresPorZona.get(vendedor.getZona()).remove(vendedor);
                System.out.println("Vendedor borrado exitosamente.");
            } else {
                System.out.println("Borrado cancelado.");
            }
        } else {
            System.out.println("Vendedor no encontrado.");
        }
    }

    // Modifica los datos de un vendedor
    private static void modificarVendedor(Scanner scanner) {
        System.out.print("Ingrese el código del vendedor a modificar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Optional<Vendedor> vendedorEncontrado = vendedores.stream()
                .filter(v -> v.getCodigo() == codigo)
                .findFirst();

        if (vendedorEncontrado.isPresent()) {
            Vendedor vendedor = vendedorEncontrado.get();
            System.out.print("Ingrese el nuevo nombre del vendedor: ");
            String nombre = scanner.nextLine();
            vendedor.setNombre(nombre);
            System.out.print("Ingrese la nueva fecha de nacimiento del vendedor (dd/mm/yy): ");
            String fechaNacimiento = scanner.nextLine();
            vendedor.setFechaNacimiento(fechaNacimiento);
            System.out.print("Ingrese la nueva zona del vendedor: ");
            String zona = scanner.nextLine();
            vendedor.setZona(zona);
            System.out.print("Ingrese el nuevo total de ventas mensuales del vendedor: ");
            double ventasMensuales = scanner.nextDouble();
            vendedor.setVentasMensuales(ventasMensuales);

            System.out.println("Vendedor modificado exitosamente.");
        } else {
            System.out.println("Vendedor no encontrado.");
        }
    }

    // Consulta y muestra vendedores por zona
    private static void consultarPorZona(Scanner scanner) {
        System.out.print("Ingrese la zona a consultar: ");
        String zonaConsulta = scanner.nextLine();

        List<Vendedor> vendedoresEnZona = vendedoresPorZona.getOrDefault(zonaConsulta, new ArrayList<>());

        if (!vendedoresEnZona.isEmpty()) {
            System.out.println("Vendedores en la zona '" + zonaConsulta + "':");
            vendedoresEnZona.forEach(v -> System.out.println(v.toString()));
        } else {
            System.out.println("No hay vendedores en la zona especificada.");
        }
    }

    // Muestra todos los vendedores en la lista
    private static void verTodosLosVendedores() {
        if (!vendedores.isEmpty()) {
            System.out.println("Todos los vendedores en el archivo .dat:");
            vendedores.forEach(v -> System.out.println(v.toString()));
        } else {
            System.out.println("No hay vendedores registrados.");
        }
    }
}
