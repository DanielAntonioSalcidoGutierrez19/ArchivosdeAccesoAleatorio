package mx.unison;

import java.util.Date;
import java.util.Hashtable;
import java.util.Scanner;

public class SequencialBinario {
    public static void main(String[] args) {
        //AgregarVendedor();

        final String dataPath = "C:\\Users\\vitor\\Downloads\\random_files\\random_files\\vendors-data.dat";

        RandomVendorFile randomFile = new RandomVendorFile(dataPath);
        Vendor vendor;

        long t1 = System.currentTimeMillis();
        for(int i=1; i<= 100; i++) {
            int pos = (i * Vendor.RECORD_LEN) - Vendor.RECORD_LEN;
            vendor = randomFile.read(pos);
            System.out.println( vendor.getNombre() + ", " + vendor.getZona() );
        }
        long t2 = System.currentTimeMillis();

        long rt1 = t2 - t1;

        Vendor vendorArray[] = new Vendor[100];

        t1 = System.currentTimeMillis();

        randomFile.read(vendorArray);
        Hashtable<String,Integer> contadores = new Hashtable<>();
        for (Vendor v: vendorArray) {
            System.out.println(v);
        }
        t2 = System.currentTimeMillis();

        long rt2 = t2 - t1;
        System.out.printf("%nTiempos de ejecución:%nT1: %d T2: %d%n",rt1,rt2);
    }


    //LO QUE YO HE HECHO ALV AAJAJAJAJAJAJAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    /*
    public static void AgregarVendedor(){
        Vendor vendor = null;

        final String fileName = "C:\\Users\\vitor\\Downloads\\random_files\\random_files\\vendors.csv";
        //final String fileName = "D:\\data\\vendors-data.csv";
        VendorCSVFile csvFile = new VendorCSVFile(fileName);

        final String dataPath = "C:\\Users\\vitor\\Downloads\\random_files\\random_files\\vendors-data.dat";
        RandomVendorFile randomFile = new RandomVendorFile(dataPath);

        Scanner input = new Scanner(System.in);

        System.out.print("cuantos vendedores quieres registrar: ");
        int n = input.nextInt();
        String nombre = null;
        Date fechaDeNacimiento;
        String Zona;
        try{
        if (n >= 1){
            System.out.print("Nombre del vendedor: ");
            nombre = input.nextLine();
            System.out.print("Lugar de residencia: ");
            Zona = input.nextLine();
        }
        }catch (Exception e){
            System.out.println("ponga un numero válido");
        }

  }
     */




}
