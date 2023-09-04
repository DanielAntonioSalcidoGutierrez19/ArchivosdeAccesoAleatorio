package mx.unison;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RandomVendorFile {
    private String fileName;

    public RandomVendorFile(String fileName) {
        this.fileName = fileName;
    }

    public long write(int pos, Vendor v) throws IOException {
        RandomAccessFile out = null;
        long position = 0;
        byte buffer[] = null;

        try {
            out = new RandomAccessFile(fileName, "rw");

            out.seek(pos);

            out.writeInt(v.getCodigo());

            byte[] nombreBytes = v.getNombre().getBytes("UTF-8");
            out.writeInt(nombreBytes.length);
            out.write(nombreBytes);

            long dob = v.getFecha().getTime();
            out.writeLong(dob);

            byte[] zonaBytes = v.getZona().getBytes("UTF-8");
            out.writeInt(zonaBytes.length);
            out.write(zonaBytes);

        } catch (IOException ex) {
            Logger.getLogger(RandomVendorFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }

        return position;
    }


    public Vendor read(long position) {
        RandomAccessFile out = null;
        long bytes = 0;
        byte buffer[] = null;
        Vendor vendor = null;
        try {
            out = new RandomAccessFile(fileName, "rws");
            out.seek(position);

            int codigo = out.readInt();

            byte[] nameBytes = new byte[Vendor.MAX_NAME];
            out.read(nameBytes);

            long dateBytes = out.readLong();

            byte[] zonaBytes = new byte[Vendor.MAX_ZONE];
            out.read(zonaBytes);

            vendor = new Vendor(codigo, new String(nameBytes), new Date(dateBytes),
                    new String(zonaBytes));
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(RandomVendorFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendor;
    }

    public void read(Vendor vendors[]) {
        RandomAccessFile out = null;
        long bytes = 0;
        byte buffer[] = null;
        Vendor vendor = null;
        try {
            out = new RandomAccessFile(fileName, "rws");
            for (int i = 0; i < vendors.length; i++) {

                int codigo = out.readInt();

                byte[] nameBytes = new byte[Vendor.MAX_NAME];
                out.read(nameBytes);

                long dateBytes = out.readLong();

                byte[] zonaBytes = new byte[Vendor.MAX_ZONE];
                out.read(zonaBytes);

                vendors[i] = new Vendor(codigo, new String(nameBytes), new Date(dateBytes),
                        new String(zonaBytes));
            }
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(RandomVendorFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public static void main(String[] args) throws IOException {

        final String dataPath = "C:\\Users\\Usuario\\Downloads\\escuela\\DESARROLLO\\random_filescasilisto\\random_files\\random_files\\vendors-data.dat";

        RandomVendorFile randomFile = new RandomVendorFile(dataPath);

        Scanner input = new Scanner(System.in);

        System.out.print("Numero de registro:");

        int n = input.nextInt();

        input.nextLine();

        int pos = (n * Vendor.RECORD_LEN) - Vendor.RECORD_LEN;
        Vendor p = randomFile.read(pos);

        //long t1 = System.currentTimeMillis();
        //long t2 = System.currentTimeMillis();

        System.out.println(p);
        //System.out.println(t2 - t1);

        // Mostrar los campos disponibles al usuario
        System.out.println("Campos disponibles para modificar:");
        System.out.println("1. Nombre");
        System.out.println("2. Fecha de Nacimiento");
        System.out.println("3. Zona");
        System.out.print("Seleccione el campo que desea modificar (1/2/3): ");

        int campoSeleccionado = input.nextInt();

        input.nextLine();

        // Realizar la modificación basada en la selección del usuario
        switch (campoSeleccionado) {
            case 1:
                System.out.print("Ingrese el nuevo nombre: ");
                String nuevoNombre = input.nextLine();
                p.setNombre(nuevoNombre);
                break;
            case 2:
                System.out.print("Ingrese la nueva fecha de nacimiento: ");
                String nuevaFechaNacimiento = input.nextLine();
                p.setFecha(nuevaFechaNacimiento);
                break;
            case 3:
                System.out.print("Ingrese la nueva zona: ");
                String nuevaZona = input.nextLine();
                p.setZona(nuevaZona);
                break;
            default:
                System.out.println("Selección no válida.");
                break;
        }

        // Guardar el objeto Vendor modificado en el archivo

        randomFile.write(pos,p);

        // Imprimir el objeto Vendor modificado
        System.out.println("Registro modificado:");
        System.out.println(p);
    }

    }

