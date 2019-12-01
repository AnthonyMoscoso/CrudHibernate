package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;
/*  esta clase fue creada para ser usada como conexion unica para todos los demas crud
 * 	debido a que previamente he trabajado con SQLITE pero mysql funciona de manera distinta 
 * y me estaba dando problemas pero la he dejado al final , para comprobar que siempre tenga
 * la base de datos conectada antes de ponerme a trabajar con ella
 */
public class Lanzador {
	public Connection lanzar() {
		Connection conexion=null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			//introducimos las variable usuario,contraseña y el nombre de nuesta bd
			String usuario="root";
			String password="1234";
			String bd="Ejercicio2";
			// Establecemos la conexion con la BD
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd+"?userTimezone=true&serverTimezone=UTC",usuario ,password );
			// Preparamos la consulta
			
			
			JOptionPane.showMessageDialog(null, "Conexion establecida");
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage());
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conexion;
	}
}
