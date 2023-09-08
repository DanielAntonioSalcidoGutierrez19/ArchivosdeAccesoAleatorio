package unison;

public class Vendedor {
    private int codigo;
    private String nombre;
    private String fechaNacimiento;
    private String zona;
    private double ventasMensuales;

    // Constructor que inicializa los atributos del vendedor
    public Vendedor(int codigo, String nombre, String fechaNacimiento, String zona, double ventasMensuales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.zona = zona;
        this.ventasMensuales = ventasMensuales;
    }

    // Métodos getters y setters para acceder y modificar los atributos del vendedor

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public double getVentasMensuales() {
        return ventasMensuales;
    }

    public void setVentasMensuales(double ventasMensuales) {
        this.ventasMensuales = ventasMensuales;
    }

    // Método que convierte un objeto Vendedor en una cadena CSV
    public String toCSVString() {
        return codigo + "," + nombre + "," + fechaNacimiento + "," + zona + "," + ventasMensuales;
    }

    // Método que devuelve una representación de cadena del objeto Vendedor
    @Override
    public String toString() {
        return "Código: " + codigo + "\nNombre: " + nombre + "\nFecha de Nacimiento: " + fechaNacimiento + "\nZona: " + zona + "\nVentas Mensuales: " + ventasMensuales;
    }
}
