package mx.unison;

import java.io.IOException;
import java.io.RandomAccessFile;

public class EditDat {
    public static void main(String[] args) {
        try {
            // Abre el archivo en modo lectura y escritura
            RandomAccessFile file = new RandomAccessFile("C:\\Users\\Usuario\\Downloads\\escuela\\DESARROLLO\\random_filescasilisto\\random_files\\random_files\\vendors-data.dat", "rw");

            file.seek(0);
            file.writeUTF("eaaa");

            System.out.println(file.read());
file.seek(100);
            System.out.println(file.read());
            /*
            // Ubicación en el archivo donde deseas editar (por ejemplo, el byte 5)
            long posicion = 10;

            // Mueve el puntero al inicio de la ubicación de edición
            file.seek(posicion);

            // Lee el dato existente
            byte datoExistente = file.readByte();
            System.out.println("Dato existente en la posición " + posicion + ": " + datoExistente);

            // Realiza la edición (por ejemplo, incrementa el valor en 1)
            byte datoEditado = (byte) (datoExistente + 1);

            // Mueve el puntero de nuevo a la posición de edición
            file.seek(posicion);

            // Escribe el dato editado
            file.write(datoEditado);

            // Cierra el archivo
            file.close();

            System.out.println("Edición completada.");

             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}