import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Zonas{
    public static void main(String[] args) {

    }
    private String Linea;
    private BufferedReader lector;
    private String Partes[]=null;


    public void leerArchivos(String NombreArchivo){
        try {
            lector=new BufferedReader(new FileReader(NombreArchivo));
            while ((Linea=lector.readLine())!=null){
            Partes=Linea.split(",");
            imprimirlinea();
            System.out.println();
            }
            lector.close();
            Linea=null;
            Partes=null;
            //try
            lector=new BufferedReader(new FileReader(NombreArchivo));
            // Crear un mapa para almacenar vendedores por código de zona
            Map<String, StringBuilder> vendorsByZone = new HashMap<>();

            // Crear un analizador CSV usando Apache Commons CSV
            CSVParser csvParser = new CSVParser(new FileReader(NombreArchivo), CSVFormat.DEFAULT.withHeader());

            // Iterar a través de las filas del CSV
            for (CSVRecord record : csvParser) {
                String veCodZona = record.get("Ve_CodZona");
                String veNomven = record.get("Ve_Nomven");

                // Verificar si ya existe un vendedor en el código de zona actual
                if (vendorsByZone.containsKey(veCodZona)) {
                    // Si existe, agregar el nombre del vendedor a la lista existente
                    vendorsByZone.get(veCodZona).append(", ").append(veNomven);
                } else {
                    // Si no existe, crear una nueva lista de vendedores
                    StringBuilder newVendorList = new StringBuilder(veNomven);
                    vendorsByZone.put(veCodZona, newVendorList);
                }
            }

            // Imprimir los vendedores por código de zona
            for (Map.Entry<String, StringBuilder> entry : vendorsByZone.entrySet()) {
                String veCodZona = entry.getKey();
                String vendedores = entry.getValue().toString();
                System.out.println("Código de Zona: " + veCodZona);
                System.out.println("Vendedores: " + vendedores);
                System.out.println();
            }

            // Cerrar  CSV
            csvParser.close();



        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            e.printStackTrace();
        }
    }
    public void imprimirlinea(){
        for (int i = 0; i < Partes.length; i++) {
            System.out.print(Partes[i]+"  |   ");
        }
    }}



