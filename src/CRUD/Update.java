package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

public class Update {
	public void Actualizar(int id,String apellido,String oficio,int direccion,double salario,double comision) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String usuario = "root";
			String password = "1234";
			String bd = "Ejercicio2";
			Date date = new Date();
			java.sql.Date fecha=new java.sql.Date(date.getTime());
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + bd + "?userTimezone=true&serverTimezone=UTC", usuario, password);
			Statement sentencia = conexion.createStatement();
			// hacemos previamente una consulta select para verificar que el id , existe
			String comprobar = "SELECT EMP_NO FROM EMPLEADOS WHERE EMP_NO=" + id;

			ResultSet resul = sentencia.executeQuery(comprobar);
		
			if (resul.next()) {
				String sql = "UPDATE EMPLEADOS SET APELLIDO = ? ," + " OFICIO= ? , " + "DIR = ?," + "FECHA_ALT= ?,"
						+ "SALARIO = ?," + "COMISION = ?" + " WHERE EMP_NO='" + id + "'";
				PreparedStatement st = conexion.prepareStatement(sql);
				st.setString(1, apellido);
				st.setString(2, oficio);
				st.setInt(3, direccion);
				st.setDate(4, fecha);
				st.setDouble(5, salario);
				st.setDouble(6, comision);
				
				st.executeUpdate();
				JOptionPane.showMessageDialog(null, "se ha guardado los datos con exito");
				st.close();
				conexion.close();
			} else {
				JOptionPane.showMessageDialog(null, "No existe ningun empleado con esta Id");
			}
			

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getCause());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}