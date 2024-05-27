package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    // Método para leer el grafo desde un archivo
    private static Grafico leerArchivo(String nombreArchivo) {
        Grafico grafo = new Grafico();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                String ciudad1 = partes[0].trim();
                String ciudad2 = partes[1].trim();
                int distancia = Integer.parseInt(partes[2].trim());
                grafo.agregarConexion(ciudad1, ciudad2, distancia);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return grafo;
    }
    
    // Método para calcular y mostrar la ruta más corta entre dos ciudades
    private static void calcularRutaMasCorta(Grafico grafo) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la ciudad de origen: ");
        String origen = scanner.nextLine();
        System.out.print("Ingrese la ciudad de destino: ");
        String destino = scanner.nextLine();
        System.out.println(grafo.rutaMasCorta(origen, destino));
    }

    private static void mostrarMenu() {
        System.out.println("Opciones:");
        System.out.println("1. Calcular ruta más corta entre dos ciudades.");
        System.out.println("2. Calcular la ciudad que queda en el centro del grafo.");
        System.out.println("3. Modificar el grafo.");
        System.out.println("4. Finalizar programa.");
    }

    public static void main(String[] args) {
        // Para leer el archivo txt y construir el grafo
        Grafico grafo = leerArchivo("demo\\src\\main\\java\\com\\example\\guategrafo.txt");
        
        // Para calcular la distancia más corta entre todos los pares de ciudades
        grafo.algoritmoFloyd();
        
        // Menú
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            Scanner input = new Scanner(System.in);
            String op = input.nextLine();
            int opcion = Integer.parseInt(op);
            switch (opcion) {
                case 1:
                    calcularRutaMasCorta(grafo);
                    break;
                case 2:
                    //calcularCentroGrafo(grafo);
                    break;
                case 3:
                    //modificarGrafo(grafo);
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
