import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Grafico;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class testing {
        private Grafico grafico;

    @BeforeEach
    public void setUp() {
        grafico = new Grafico();
    }

    @Test
    public void testAgregarConexion() {
        grafico.agregarConexion("CiudadA", "CiudadB", 10);
        assertEquals(10, grafico.obtenerDistancia("CiudadA", "CiudadB"));
    }

    @Test
    public void testObtenerDistancia() {
        grafico.agregarConexion("CiudadA", "CiudadB", 10);
        assertEquals(10, grafico.obtenerDistancia("CiudadA", "CiudadB"));
        assertEquals(Integer.MAX_VALUE, grafico.obtenerDistancia("CiudadA", "CiudadC"));
    }

    @Test
    public void testRutaMasCorta() {
        grafico.agregarConexion("CiudadA", "CiudadB", 10);
        grafico.agregarConexion("CiudadB", "CiudadC", 15);
        grafico.algoritmoFloyd();
        String expected = "Ruta más corta: [CiudadA, CiudadB, CiudadC], distancia: 25 KM.";
        assertEquals(expected, grafico.rutaMasCorta("CiudadA", "CiudadC"));
    }

    @Test
    public void testCentroGrafo() {
        grafico.agregarConexion("CiudadA", "CiudadB", 10);
        grafico.agregarConexion("CiudadB", "CiudadC", 15);
        grafico.agregarConexion("CiudadA", "CiudadC", 30);
        grafico.algoritmoFloyd();
        String expected = "El centro del grafo es: CiudadA";
        assertEquals(expected, grafico.centroGrafo());
    }

    @Test
    public void testModificarConexion() {
        grafico.agregarConexion("CiudadA", "CiudadB", 10);
        grafico.modificarConexion("CiudadA", "CiudadB", 20);
        assertEquals(20, grafico.obtenerDistancia("CiudadA", "CiudadB"));
    }

    @Test
    public void testModificarConexionInexistente() {
        grafico.modificarConexion("CiudadA", "CiudadB", 20);
        assertEquals(Integer.MAX_VALUE, grafico.obtenerDistancia("CiudadA", "CiudadB"));
    }

    @Test
    public void testObtenerCiudades() {
        grafico.agregarConexion("CiudadA", "CiudadB", 10);
        grafico.agregarConexion("CiudadB", "CiudadC", 15);
        Set<String> ciudades = grafico.obtenerCiudades();
        assertTrue(ciudades.contains("CiudadA"));
        assertTrue(ciudades.contains("CiudadB"));
        assertTrue(ciudades.contains("CiudadC"));
    }
}
