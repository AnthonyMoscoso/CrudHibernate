package CRUD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class Delete {
public void DeleteFrom(int id) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String usuario="root";
		String password="1234";
		String bd="Ejercicio2";
		// Establecemos la conexion con la BD
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd+"?userTimezone=true&serverTimezone=UTC",usuario ,password );
		// Preparamos la consulta
		Statement sentencia = conexion.createStatement();
		String comprobar = "SELECT EMP_NO FROM EMPLEADOS WHERE EMP_NO=" + id;

		ResultSet resul = sentencia.executeQuery(comprobar);
		if (resul.next()) {String sql = "Delete from EMPLEADOS WHERE emp_no ='" + id + "'";
		PreparedStatement st = conexion.prepareStatement(sql);
		int rs = st.executeUpdate();
		st.close();
		conexion.close();
		}
		else {
			JOptionPane.showMessageDialog(null, "No existe ningun empleado con esta Id");
		}
		
	} catch (SQLException ex) {
		JOptionPane.showMessageDialog(null, "Error al eliminar" + ex.getMessage());
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}