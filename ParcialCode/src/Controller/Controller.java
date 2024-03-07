/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Conexion.conexionmySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author juan
 */
public class Controller {
       // Creamos un objeto de la clase ConexionMySQL
    conexionmySql conexion;

    public Controller() {
        this.conexion = new  conexionmySql();
    }
    
    public void insert(String id,String nombre,String actingEpecialty ,String empathy,String creativity,String Charisma, int age) {
        // Establecemos la conexión con la base de datos
        try (Connection conn = conexion.conectarMySQL()) {
            // Verificamos si la conexión fue exitosa
            if (conn != null) {
                // Preparamos la consulta SQL para insertar datos
                String insertSQL = "INSERT INTO Actor (identification,name,actingSpecialty ,empathy,creativity, charismaLevel,age) VALUES (?,?,?,?,?,?,?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, id);
                    pstmt.setString(2,nombre );
                    pstmt.setString(3, actingEpecialty);
                    pstmt.setString(4, empathy);
                    pstmt.setString(5, creativity);
                    pstmt.setString(6, Charisma);
                     pstmt.setInt(7, age); 
                    // Ejecutamos la consulta
                    int rowsAffected = pstmt.executeUpdate();

                    // Verificamos si la inserción fue exitosa
                    if (rowsAffected > 0) {
                        System.out.println("Inserción exitosa");
                    } else {
                        System.out.println("No se pudo insertar los datos");
                    }
                }
            } else {
                System.out.println("No se pudo establecer la conexión con la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al realizar la inserción en la base de datos");
            e.printStackTrace();
        }
    }
    
      public void select() {
        // Establecemos la conexión con la base de datos
        try (Connection conn = conexion.conectarMySQL()) {
            // Verificamos si la conexión fue exitosa
            if (conn != null) {
                // Preparamos la consulta SQL para seleccionar datos
                String selectSQL = "SELECT * FROM Actor";
                try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                    // Ejecutamos la consulta
                    ResultSet rs = pstmt.executeQuery();

                    // Iteramos sobre los resultados
                    while (rs.next()) {
                        System.out.println("id: " + rs.getString("identification") + ", nombre: " + rs.getString("name") + ", Especialidad Actoral: " + rs.getString("actingSpecialty")+", Nivel de Empatia :"+rs.getNString("empathy")+",Nivel de Creatividad "
                                + ":"+rs.getString("creativity")+" Nivel de Carisma:  "+ rs.getNString("charismaLevel")+"Edad "+ rs.getInt("age"));
                    }
                }
            } else {
                System.out.println("No se pudo establecer la conexión con la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al realizar la selección en la base de datos");
            e.printStackTrace();
        }
    }
     
}
