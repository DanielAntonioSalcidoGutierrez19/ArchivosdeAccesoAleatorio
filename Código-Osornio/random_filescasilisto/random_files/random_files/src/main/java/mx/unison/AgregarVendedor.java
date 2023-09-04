package mx.unison;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class AgregarVendedor {

    public static void main(String[] args) {

        Vendor v = new Vendor();
        Scanner scanner = new Scanner(System.in);

        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\Usuario\\Downloads\\escuela\\DESARROLLO\\random_filescasilisto\\random_files\\random_files\\vendors.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            int cuantos = 0;
            System.out.print("¿Cuantos trabajadores quiere registrar? ");
            cuantos = scanner.nextInt();

            scanner.nextLine();

            for (int i = 0; i < cuantos; i++) {

                System.out.println("Registrando Empleado " + (i+1));

                System.out.print("Codigo de empleado: ");
                int codigo = scanner.nextInt();
                v.setCodigo(codigo);

                scanner.nextLine();

                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                v.setNombre(nombre);


                System.out.print("Fecha de nacimiento: ");
                String fecha = scanner.nextLine();
                v.setFecha(fecha);

                System.out.print("Zona: ");
                String zona = scanner.nextLine();
                v.setZona(zona);



                String nuevaLineaCSV = "\n" + codigo + "," + nombre + "," + fecha + "," + zona;
                bufferedWriter.write(nuevaLineaCSV);
            }

            scanner.close();
            bufferedWriter.close();
            System.out.println("Datos Actualizados.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






