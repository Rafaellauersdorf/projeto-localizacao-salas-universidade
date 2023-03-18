import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class LocalizacaoSalas {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/university";
    private static final String USER = "root";
    private static final String PASS = "senha123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Obter a localização atual do usuário
        double latitudeAtual = obterLatitudeAtual(scanner);
        double longitudeAtual = obterLongitudeAtual(scanner);

        // Obter a localização da sala de aula desejada
        String salaDesejada = obterSalaDesejada(scanner);
        LatLng coordenadasSala = obterCoordenadasSala(salaDesejada);

        // Calcular a distância entre a localização atual e a sala de aula desejada
        double distancia = calcularDistancia(latitudeAtual, longitudeAtual, coordenadasSala);

        // Exibir a distância para o usuário
        System.out.println("A sala de aula " + salaDesejada + " está a " + distancia + " metros de distância.");

        scanner.close();
    }

    private static double obterLatitudeAtual(Scanner scanner) {
        System.out.print("Digite a latitude atual: ");
        return scanner.nextDouble();
    }

    private static double obterLongitudeAtual(Scanner scanner) {
        System.out.print("Digite a longitude atual: ");
        return scanner.nextDouble();
    }

    private static String obterSalaDesejada(Scanner scanner) {
        System.out.print("Digite o nome da sala de aula desejada: ");
        return scanner.nextLine();
    }

    private static LatLng obterCoordenadasSala(String salaDesejada) {
        try {
            // Estabelecer conexão com o banco de dados
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Consultar o banco de dados para obter a localização da sala de aula desejada
            String sql = "SELECT latitude, longitude FROM salas WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, salaDesejada);
            ResultSet rs = stmt.executeQuery();

            // Obter as coordenadas da sala de aula desejada
            LatLng coordenadas = null;
            if (rs.next()) {
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                coordenadas = new LatLng(latitude, longitude);
            }

            // Fechar as conexões com o banco de dados
            rs.close();
            stmt.close();
            conn.close();

            return coordenadas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static double calcularDistancia(double latitude1, double longitude1, LatLng coordenadas2) {
        // Calcular a distância entre duas coordenadas geográficas usando a fórmula de Haversine
        double EARTH_RADIUS = 6371.01; // em quilômetros
