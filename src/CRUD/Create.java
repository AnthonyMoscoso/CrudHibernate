package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

public class Create {
public void Create(String apellido,String oficio,int dir,double salario,double comision,int  dept_no) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String usuario="root";
		String password="1234";
		String bd="Ejercicio2";
		// Establecemos la conexion con la BD
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd+"?userTimezone=true&serverTimezone=UTC",usuario ,password );
		// Preparamos la consulta
		
	
	Date date = new Date();
	java.sql.Date fecha=new java.sql.Date(date.getTime());
	

	
// mediante un insert y un preparedstatement insertamos los datos en la tabla
	
	String query = "INSERT INTO EMPLEADOS " + "(APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) "
			+ "VALUES (?,?,?,?,?,?,?)";
	PreparedStatement st = conexion.prepareStatement(query);
	st.setString(1, apellido);
	st.setString(2, oficio);
	st.setInt(3, dir);
	st.setDate(4,  fecha);
	st.setDouble(5, salario);
	st.setDouble(6, comision);
	st.setInt(7, dept_no);
		// objeto DatabaseMetaData
	
	
	int res = st.executeUpdate();
	
	st.close();
	conexion.close();
	JOptionPane.showMessageDialog(null, "Se ha creado con exito el empleado :"+apellido);
} catch (SQLException | ClassNotFoundException ex) {
	JOptionPane.showMessageDialog(null,ex.getMessage());
}
}
}
