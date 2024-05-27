
package com.example;

import java.util.*;

public class Grafico {
    // Mapa que contiene las conexiones entre ciudades y sus distancias
    private Map<String, Map<String, Integer>> juntados;

    public Grafico() {
        juntados = new HashMap<>();
    }
    
    // Método para obtener el conjunto de todas las ciudades en el grafo
    public Set<String> obtenerCiudades() {
        return juntados.keySet();
    }
    
    // Implementación del algoritmo de Floyd-Warshall para calcular las rutas más cortas entre todas las parejas de ciudades
    public void algoritmoFloyd() {
        for (String intermedia : obtenerCiudades()) {
            for (String origen : obtenerCiudades()) {
                for (String destino : obtenerCiudades()) {
                    int distanciaOrigenIntermedia = obtenerDistancia(origen, intermedia);
                    int distanciaIntermediaDestino = obtenerDistancia(intermedia, destino);
                    int distanciaOrigenDestino = obtenerDistancia(origen, destino);
                    int nuevaDistancia = distanciaOrigenIntermedia != Integer.MAX_VALUE &&
                    distanciaIntermediaDestino != Integer.MAX_VALUE ?
                    distanciaOrigenIntermedia + distanciaIntermediaDestino : Integer.MAX_VALUE;
                    if (distanciaOrigenDestino > nuevaDistancia) {
                        agregarConexion(origen, destino, nuevaDistancia);
                    }
                }
            }
        }
    }
    
    // Método para calcular la ruta más corta entre dos ciudades específicas
    public String rutaMasCorta(String origen, String destino) {
        // Verificar si las ciudades están en el grafo
        if (!juntados.containsKey(origen) || !juntados.containsKey(destino)) {
            return "No hay ruta disponible.";
        }
        List<String> ciudadesIntermedias = new ArrayList<>();
        int distancia = obtenerDistancia(origen, destino);
        if (distancia == Integer.MAX_VALUE) {
            return "No hay ruta disponible.";
        }
        String ciudadActual = origen;
        Set<String> visitadas = new HashSet<>(); // Para evitar ciclos infinitos
        while (!ciudadActual.equals(destino) && ciudadesIntermedias.size() < juntados.size()) {
            ciudadesIntermedias.add(ciudadActual);
            visitadas.add(ciudadActual);
            boolean encontrado = false;
            for (String ciudad : juntados.get(ciudadActual).keySet()) {
                if (!visitadas.contains(ciudad)) {
                    int nuevaDistancia = obtenerDistancia(ciudadActual, ciudad) + obtenerDistancia(ciudad, destino);
                    if (nuevaDistancia == distancia) {
                        ciudadActual = ciudad;
                        encontrado = true;
                        break;
                    }
                }
            }
            if (!encontrado) {
                // Si no se encontró ninguna ciudad para avanzar, salir del bucle
                break;
            }
        }
        ciudadesIntermedias.add(destino);
        return "Ruta más corta: " + ciudadesIntermedias + ", distancia: " + distancia + " KM.";
    }
    
    // Método para obtener la distancia entre dos ciudades
    public int obtenerDistancia(String ciudad1, String ciudad2) {
        return juntados.containsKey(ciudad1) && juntados.get(ciudad1).containsKey(ciudad2)
        ? juntados.get(ciudad1).get(ciudad2)
        : Integer.MAX_VALUE;
    }

    // Método para agregar una conexión entre dos ciudades con una distancia dada
    public void agregarConexion(String ciudad1, String ciudad2, int distancia) {
        // Para asegurarse de que ambas ciudades están en el mapa
        juntados.putIfAbsent(ciudad1, new HashMap<String, Integer>());
        juntados.putIfAbsent(ciudad2, new HashMap<String, Integer>());
        juntados.get(ciudad1).put(ciudad2, distancia);
    }

    // Método para calcular la ciudad que queda en el centro del grafo
    public String centroGrafo() {
        String centro = null;
        int menorDistancia = Integer.MAX_VALUE;
        for (String ciudad : obtenerCiudades()) {
            int mayorDistancia = 0;
            for (String otraCiudad : obtenerCiudades()) {
                if (!ciudad.equals(otraCiudad)) {
                    mayorDistancia = Math.max(mayorDistancia, obtenerDistancia(ciudad, otraCiudad));
                }
            }
            if (menorDistancia > mayorDistancia) {
                menorDistancia = mayorDistancia;
                centro = ciudad;
            }
        }
        return "El centro del grafo es: " + centro;
    }
}