package com.example.importacion;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class importacion {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/Mapamundi";
        String user = "postgres";
        String password = "1234";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            // Cargar archivo JSON
            InputStream is = new FileInputStream("paises.json");
            JSONTokener tokener = new JSONTokener(is);
            JSONObject jsonData = new JSONObject(tokener);

            // Insertar países
            JSONArray countries = jsonData.getJSONArray("countries");
            for (int i = 0; i < countries.length(); i++) {
                JSONObject country = countries.getJSONObject(i);
                String idCountry = country.getString("id_country");
                System.out.println("Insertando país con ID " + idCountry);

                insertarPais(conn, idCountry, country.getString("ISO3"), country.getString("Country_name"));

                // Insertar departamentos dentro del país con el nuevo formato de id_department
                if (country.has("departments")) {
                    JSONArray departments = country.getJSONArray("departments");

                    // Mapa para almacenar la secuencia de los departamentos por país
                    Map<String, Integer> departmentSequence = new HashMap<>();

                    for (int j = 0; j < departments.length(); j++) {
                        JSONObject dept = departments.getJSONObject(j);
                        String nameDepartment = dept.getString("name_department");

                        // Generar el nuevo id_department
                        int sequence = departmentSequence.getOrDefault(idCountry, 0) + 1;
                        departmentSequence.put(idCountry, sequence);

                        System.out.println("Insertando departamento con ID compuesto: " + idCountry + String.format("%02d", sequence));

                        // Llamada para insertar el departamento con el nuevo formato
                        insertarDepartamento(conn, idCountry, sequence, nameDepartment);
                    }
                }
            }

            conn.commit();
            System.out.println("Todos los datos han sido importados correctamente.");
        } catch (Exception e) {
            System.err.println("Error en la operación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertarPais(Connection conn, String idCountry, String iso3, String countryName) throws SQLException {
        String sql = "INSERT INTO country (id_country, ISO3, Country_name) VALUES (?, ?, ?) ON CONFLICT (id_country) DO NOTHING";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idCountry);
            pstmt.setString(2, iso3);
            pstmt.setString(3, countryName);
            pstmt.executeUpdate();
        }
    }

    private static void insertarDepartamento(Connection conn, String idCountry, int sequence, String nameDepartment) throws SQLException {
        // Crear el ID compuesto concatenando los tres primeros dígitos (id_country) con la secuencia
        String idDepartment = idCountry + String.format("%03d", sequence);

        String sql = "INSERT INTO department (id_department, id_country, department_name) VALUES (?, ?, ?) " +
                "ON CONFLICT (id_department) DO NOTHING";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idDepartment); // ID compuesto: "00401", "00402", etc.
            pstmt.setString(2, idCountry);   // Código del país
            pstmt.setString(3, nameDepartment); // Nombre del departamento
            pstmt.executeUpdate();
        }
    }

}
