package CRUD;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Read {
	
public void SelectAll(ArrayList<String[]> arrayDatos) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String usuario="root";
		String password="1234";
		String bd="Ejercicio2";
		// Establecemos la conexion con la BD
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd+"?userTimezone=true&serverTimezone=UTC",usuario ,password );
		Statement sentencia = conexion.createStatement();
		String sql = "SELECT * FROM departamentos INNER JOIN empleados on departamentos.dept_no = empleados.dept_no";
		//String sql = "SELECT * FROM empleados";
		ResultSet resul = sentencia.executeQuery(sql);
		
		while (resul.next()) {
			// este metodo sirve para introducir mis datos en una tabla
			// mediante este metodo while lo que hacemos realmente es leer los datos de nuestra base de datos
			String[] data= {resul.getInt(1)+"",resul.getString(2),resul.getString(3),resul.getInt(4)+"",resul.getString(5),
					resul.getString(6),""+resul.getInt(7),""+resul.getDate(8),""+ resul.getDouble(9),""+resul.getDouble(10),""+resul.getInt(11)};
			arrayDatos.add(data);
		}
		
		
		resul.close(); // Cerrar ResultSet
		sentencia.close(); // Cerrar Statement
		conexion.close();
	}
	catch(SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
public void selectFromDepartamento(ArrayList<String[]> arrayDatos) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String usuario="root";
		String password="1234";
		String bd="Ejercicio2";
		// Establecemos la conexion con la BD
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd+"?userTimezone=true&serverTimezone=UTC",usuario ,password );
		Statement sentencia = conexion.createStatement();
		String sql = "SELECT * FROM departamentos";
		//String sql = "SELECT * FROM empleados";
		ResultSet resul = sentencia.executeQuery(sql);
		
		while (resul.next()) {
			// este metodo sirve para introducir mis datos en una tabla
			// mediante este metodo while lo que hacemos realmente es leer los datos de nuestra base de datos
			String[] data= {resul.getInt(1)+"",resul.getString(2),resul.getString(3)};
			arrayDatos.add(data);
		}
		
		
		resul.close(); // Cerrar ResultSet
		sentencia.close(); // Cerrar Statement
		conexion.close();
	}
	catch(SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void SelectById(int id,ArrayList<String> data) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String usuario="root";
		String password="1234";
		String bd="Ejercicio2";
		// Establecemos la conexion con la BD
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd+"?userTimezone=true&serverTimezone=UTC",usuario ,password );
		Statement sentencia = conexion.createStatement();
		String sql = "SELECT * FROM EMPLEADOS where emp_no="+id;
		//String sql = "SELECT * FROM empleados";
		ResultSet resul = sentencia.executeQuery(sql);
		
		while (resul.next()) {
			data.add(resul.getString(2));
			data.add(resul.getString(3));
			data.add(resul.getInt(4)+"");
			data.add(resul.getDouble(6)+"");
			data.add(resul.getDouble(7)+"");
			data.add(resul.getInt(8)+"");
		 
			
		}
		
	
		resul.close(); // Cerrar ResultSet
		sentencia.close(); // Cerrar Statement
	}
	catch(SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
}


