import java.io.Serializable;

/**
 * Clase Person que implementa la interfaz Serializable
 * Esta clase demuestra cómo hacer que un objeto sea serializable en Java
 */
public class Person implements Serializable {
    // SerialVersionUID para control de versiones
    private static final long serialVersionUID = 1L;
    
    // Atributos de la clase
    private String name;
    private int age;
    private String address;
    
    /**
     * Constructor de la clase Person
     * @param name Nombre de la persona
     * @param age Edad de la persona
     * @param address Dirección de la persona
     */
    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    
    // Getters y Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Método toString para mostrar la información de la persona
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
} 