import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que demuestra el uso de Serializable y operaciones con archivos
 */
public class Main {
    public static void main(String[] args) {
        // Crear una lista de personas
        List<Person> people = new ArrayList<>();
        people.add(new Person("Juan Pérez", 25, "Calle 123"));
        people.add(new Person("María García", 30, "Avenida 456"));
        
        // Nombre del archivo para serialización
        String serializedFile = "personas.ser";
        // Nombre del archivo para operaciones con BufferedReader
        String textFile = "personas.txt";
        
        try {
            // 1. Demostración de serialización
            System.out.println("Serializando objetos...");
            serializeObjects(people, serializedFile);
            
            // 2. Demostración de deserialización
            System.out.println("\nDeserializando objetos...");
            List<Person> deserializedPeople = deserializeObjects(serializedFile);
            System.out.println("Objetos deserializados:");
            for (Person person : deserializedPeople) {
                System.out.println(person);
            }
            
            // 3. Demostración de operaciones con BufferedReader
            System.out.println("\nEscribiendo en archivo de texto...");
            writeToFile(people, textFile);
            
            System.out.println("\nLeyendo archivo de texto con BufferedReader...");
            readFromFile(textFile);
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Serializa una lista de objetos Person a un archivo
     * @param people Lista de personas a serializar
     * @param filename Nombre del archivo donde se guardará
     */
    private static void serializeObjects(List<Person> people, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(people);
            System.out.println("Objetos serializados exitosamente en " + filename);
        }
    }
    
    /**
     * Deserializa una lista de objetos Person desde un archivo
     * @param filename Nombre del archivo a leer
     * @return Lista de personas deserializadas
     */
    @SuppressWarnings("unchecked")
    private static List<Person> deserializeObjects(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Person>) ois.readObject();
        }
    }
    
    /**
     * Escribe la información de las personas en un archivo de texto
     * @param people Lista de personas a escribir
     * @param filename Nombre del archivo donde se escribirá
     */
    private static void writeToFile(List<Person> people, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Person person : people) {
                writer.write(person.toString());
                writer.newLine();
            }
            System.out.println("Archivo de texto creado exitosamente: " + filename);
        }
    }
    
    /**
     * Lee el contenido de un archivo de texto usando BufferedReader
     * @param filename Nombre del archivo a leer
     */
    private static void readFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Contenido del archivo:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
} 