# Proyecto Java: Serialización y Manejo de Archivos

Este proyecto demuestra la implementación de la interfaz `Serializable` en Java y el manejo de archivos utilizando `BufferedReader` y `BufferedWriter`.

## Estructura del Proyecto

El proyecto consta de dos archivos principales:

- `Person.java`: Clase que implementa la interfaz Serializable
- `SerializationAndFileHandler.java`: Clase principal que demuestra las operaciones de serialización y manejo de archivos

## Componentes Principales

### 1. Clase Person
- Implementa la interfaz `Serializable`
- Contiene atributos básicos: nombre, edad y dirección
- Incluye un `serialVersionUID` para control de versiones
- Proporciona getters y setters para todos los atributos
- Implementa el método `toString()` para mostrar la información del objeto

### 2. Clase SerializationAndFileHandler
Implementa cuatro operaciones principales:

#### a) Serialización de Objetos
- Convierte objetos `Person` en un formato binario
- Guarda los objetos en un archivo `.ser`
- Utiliza `ObjectOutputStream` para la serialización

#### b) Deserialización de Objetos
- Lee objetos serializados desde el archivo
- Convierte los datos binarios de vuelta a objetos `Person`
- Utiliza `ObjectInputStream` para la deserialización

#### c) Escritura en Archivo de Texto
- Escribe la información de las personas en un archivo de texto
- Utiliza `BufferedWriter` para una escritura eficiente
- Cada persona se escribe en una línea separada

#### d) Lectura de Archivo de Texto
- Lee el contenido del archivo de texto
- Utiliza `BufferedReader` para una lectura eficiente
- Muestra el contenido línea por línea

## Archivos Generados

El programa genera dos archivos durante su ejecución:

1. `personas.ser`: Archivo binario que contiene los objetos serializados
2. `personas.txt`: Archivo de texto que contiene la información de las personas

## Compilación y Ejecución

Para compilar el proyecto:
```bash
javac Person.java SerializationAndFileHandler.java
```

Para ejecutar el programa:
```bash
java SerializationAndFileHandler
```

## Manejo de Errores

El programa incluye manejo de excepciones para:
- `IOException`: Errores de entrada/salida
- `ClassNotFoundException`: Errores durante la deserialización

## Notas Importantes

1. La interfaz `Serializable` no requiere implementación de métodos, solo la declaración de la interfaz.
2. El `serialVersionUID` es importante para mantener la compatibilidad entre versiones.
3. Se utilizan bloques try-with-resources para garantizar el cierre adecuado de los recursos.
4. El programa demuestra buenas prácticas de programación como:
   - Documentación con JavaDoc
   - Manejo de recursos
   - Separación de responsabilidades
   - Código limpio y mantenible 