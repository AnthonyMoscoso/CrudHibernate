package Ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CRUD.Read;
import CRUD.Update;
import Hibernate.Departamentos;
import Hibernate.Empleados;
import Singleton.HibernateUtil;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window.Type;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

import javax.swing.border.EtchedBorder;

public class DialogEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtApellido;
	private JTextField txtOficio;
	private JTextField txtDireccion;
	private JTextField txtSalario;
	private JTextField txtComision;
	private int dir,dept_no;
	private float salario, comision;
	private String apellido, oficio;
	Read read = new Read();
	private JTable table;
	private JTextField txtNdepartamento;
	private DefaultTableModel  model;
	private ArrayList<String[]>arrayDatos=new ArrayList<String[]>();

	JLabel lbAlerta ;
	String[] nombreColumna= {
			"Nº Empleado",
			"APELLIDO",
			"OFICIO",
			"DIRECCION",
			"SALARIO",
			"COMISION",
			"DEPARTAMENTO",
			"FECHA"
			};

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DialogEmpleados(JFrame parent, boolean mode) {
		super(parent, mode);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		setBounds(100, 100, 1386, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(5, 1, 5, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 11, 249, 338);
		contentPanel.add(panel);
		panel.setLayout(null);

		contentPanel.add(panel);

		txtComision = new JTextField();
		txtComision.setBounds(103, 178, 117, 14);
		panel.add(txtComision);
		txtComision.setColumns(10);

		JLabel lbComision = new JLabel("COMISION");
		lbComision.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		lbComision.setBounds(26, 178, 67, 14);
		panel.add(lbComision);
		lbComision.setEnabled(false);

		txtSalario = new JTextField();
		txtSalario.setBounds(103, 153, 117, 14);
		panel.add(txtSalario);
		txtSalario.setColumns(10);

		JLabel lbSalario = new JLabel("SALARIO");
		lbSalario.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		lbSalario.setBounds(26, 153, 67, 14);
		panel.add(lbSalario);
		lbSalario.setEnabled(false);

		JLabel lblDireccion = new JLabel("DIRECCION");
		lblDireccion.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		lblDireccion.setBounds(26, 128, 69, 14);
		panel.add(lblDireccion);
		lblDireccion.setEnabled(false);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(103, 128, 117, 14);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);

		JLabel lbOficio = new JLabel("OFICIO");
		lbOficio.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		lbOficio.setBounds(26, 103, 67, 14);
		panel.add(lbOficio);
		lbOficio.setEnabled(false);

		txtOficio = new JTextField();
		txtOficio.setBounds(103, 103, 117, 14);
		panel.add(txtOficio);
		txtOficio.setColumns(10);

		JLabel lbApellido = new JLabel("APELLIDO");
		lbApellido.setEnabled(false);
		lbApellido.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		lbApellido.setBounds(26, 72, 67, 20);
		panel.add(lbApellido);
		lbApellido.setHorizontalAlignment(SwingConstants.LEFT);

		txtApellido = new JTextField();
		txtApellido.setBounds(103, 78, 117, 14);
		panel.add(txtApellido);
		txtApellido.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obtenerDatos();
				mostrarDatos();
			}
		});
		btnNewButton.setBounds(119, 232, 101, 23);
		panel.add(btnNewButton);

		JLabel lblEmpleados = new JLabel("EMPLEADOS");
		lblEmpleados.setFont(new Font("Cambria", Font.BOLD, 15));
		lblEmpleados.setBounds(26, 30, 111, 20);
		panel.add(lblEmpleados);
		
		JLabel labelNdepartamento = new JLabel("N\u00BA DEPARTAMENTO");
		labelNdepartamento.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		labelNdepartamento.setEnabled(false);
		labelNdepartamento.setBounds(26, 203, 151, 14);
		panel.add(labelNdepartamento);
		
		txtNdepartamento = new JTextField();
		txtNdepartamento.setColumns(10);
		txtNdepartamento.setBounds(167, 203, 53, 14);
		panel.add(txtNdepartamento);
		
		 lbAlerta = new JLabel("");
		lbAlerta.setBounds(26, 273, 194, 52);
		panel.add(lbAlerta);

	

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(4, 1, 4, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(1223, 11, 133, 338);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setBounds(10, 29, 108, 23);
		panel_1.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actualizar();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnActualizar.setActionCommand("OK");
		getRootPane().setDefaultButton(btnActualizar);

		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarEmpleado();
			}
		});
		btnBorrar.setBounds(10, 63, 108, 23);
		panel_1.add(btnBorrar);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getParent().setVisible(true);
				dispose();
			}
		});
		btnVolver.setActionCommand("Cancel");
		buttonPane.add(btnVolver);
		
		

		String data[][]= {};
		
		
	
		model =new DefaultTableModel(data,nombreColumna);
		table = new JTable(data,nombreColumna);
		table.setBorder(new MatteBorder(3, 1, 3, 1, (Color) new Color(0, 0, 0)));
		table.setBounds(271, 11, 939, 338);
		contentPanel.add(table);
		mostrarDatos();

	}
	private void actualizar() throws ParseException {
		if(table.getSelectedRow()>0) {
			//Se obtiene la sesión creada por el Singleton. Se debe usar esta línea a lo largo
			//de todas las clases en las que deseemos realizar operaciones con la bbdd.
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			// Creo mi departamento 
			Empleados emp = new Empleados();
		
			
		
				int emp_no=Integer.parseInt( (String) model.getValueAt(table.getSelectedRow(), 0));
				String apellido="";
				String oficio="";
				int dir=0;
				float salario=0;
				float comision=0;
				int dep=0;
				
				if(txtApellido.getText().length()>0) {
					apellido=txtApellido.getText();
				}
				else {
					apellido=(String) model.getValueAt(table.getSelectedRow(), 1);
				}
				
				if(txtOficio.getText().length()>0) {
					oficio=txtOficio.getText();
				}
				else {
					oficio=(String) model.getValueAt(table.getSelectedRow(), 2);
				}
				// direccion
				try {
					if(txtDireccion.getText().length()>0) {
						dir=Integer.parseInt(txtDireccion.getText());
					}
					else {
						dir=Integer.parseInt( (String) model.getValueAt(table.getSelectedRow(), 3));
					}
				}
				catch(NumberFormatException ex) {
					lbAlerta.setText("caracter erroneo en la direccion");
				}
				//salario
				try {
					if(txtSalario.getText().length()>0) {
						salario=Float.parseFloat(txtSalario.getText());
					}
					else {
						salario=Float.parseFloat((String) model.getValueAt(table.getSelectedRow(), 4));
					}
				}
				catch(NumberFormatException ex) {
					lbAlerta.setText("caracter erroneo en el salario");
				}
				//comision
				try {
					if(txtComision.getText().length()>0) {
						comision=Float.parseFloat(txtComision.getText());
					}
					else {
						comision=Float.parseFloat( (String) model.getValueAt(table.getSelectedRow(), 5));
					}
				}
				catch(NumberFormatException ex) {
					lbAlerta.setText("caracter erroneo en la comision");
				}
				//dept_no
				try {
					if(txtNdepartamento.getText().length()>0) {
						dep=Integer.parseInt(txtNdepartamento.getText());
					}
					else {
						dep=Integer.parseInt( (String) model.getValueAt(table.getSelectedRow(), 6));
					}
				}
				catch(NumberFormatException ex) {
					lbAlerta.setText("caracter erroneo en el Numero de departamento");
				}
				emp.setApellido(apellido);
				emp.setDir((short) dir); // el director es el numero de empleado 7499
				emp.setOficio(oficio);
				emp.setSalario(salario);
				emp.setComision(comision);
				java.util.Date hoy = new java.util.Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				
				Date date=sdf.parse((String) model.getValueAt(table.getSelectedRow(), 7));
				
				emp.setFechaAlt(date);
				Departamentos d = new Departamentos(); // creo un objeto Departamentos
				d.setDeptNo((byte) dep); // el número de dep es 10
				emp.setDepartamentos(d);
				
				emp.setEmpNo((short) emp_no); 
			
				
	
				
				session.update(emp);
				try {
					tx.commit();
					} catch (ConstraintViolationException e) {
					System.out.printf("MENSAJE: %s%n",e.getMessage());
					System.out.printf("COD ERROR: %d%n",e.getErrorCode());
					System.out.printf("ERROR SQL: %s%n" ,
					 e.getSQLException().getMessage());
					}
					mostrarDatos();
					session.close();
				
				
		}
		else {
			JOptionPane.showMessageDialog(null, "debe seleccionar un elemento primero");
		}
		
			
		}
	private void borrarEmpleado() {
		if(table.getSelectedRow()>0) {
			//Se obtiene la sesión creada por el Singleton. Se debe usar esta línea a lo largo
			//de todas las clases en las que deseemos realizar operaciones con la bbdd.
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			// Creo mi departamento 
			Empleados emp = new Empleados();
		
			// creo las variables las cuales ingresare en el departamento
		
				int emp_no=Integer.parseInt( (String) model.getValueAt(table.getSelectedRow(), 0));
				
				emp.setEmpNo((short) emp_no); // el numero de empleado es 4455
			
				
	
				
				session.delete(emp);
				try {
					tx.commit();
					} catch (ConstraintViolationException e) {
					System.out.printf("MENSAJE: %s%n",e.getMessage());
					System.out.printf("COD ERROR: %d%n",e.getErrorCode());
					System.out.printf("ERROR SQL: %s%n" ,
					 e.getSQLException().getMessage());
					}
					mostrarDatos();
					session.close();
				
				
		}
	}
	
	private void obtenerDatos() {
		int contador = 0;
		if (txtApellido.getText().replace(" ", "").length() > 0) {
			apellido = txtApellido.getText();
			contador++;
		}
		if (txtOficio.getText().replace(" ", "").length() > 0) {
			oficio = txtOficio.getText();
			contador++;
		}
		if (txtDireccion.getText().replace(" ", "").length() > 0) {
			try {
				dir = Integer.parseInt(txtDireccion.getText());
				contador++;
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null,
						"Ha introducido valores erroeneo en la direccion introduzca solo numero entero ");
			}

		}
		if (txtSalario.getText().replace(" ", "").length() > 0) {
			try {
				salario = Float.parseFloat(txtDireccion.getText());
				contador++;
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ha introducido valores erroeneo en el salario ");
			}

		}
		if (txtComision.getText().replace(" ", "").length() > 0) {
			try {
				comision = Float.parseFloat(txtDireccion.getText());
				contador++;
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ha introducido valores erroneos en la comision ");
			}

		}
		if (txtNdepartamento.getText().replace(" ", "").length() > 0) {
			try {
				dept_no = Integer.parseInt(txtNdepartamento.getText());
				contador++;
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null,
						"Ha introducido valores erroeneo en  numero de departamento introduzca solo numero entero ");
			}

		}
		if (contador == 6) {
			insertarEmpleado(apellido, oficio, dir, salario, comision, dept_no);
			//create.Create(apellido, oficio, dir, salario, comision, dept_no);
			limpiar();
		} else {
			JOptionPane.showMessageDialog(null, "No puede dejar datos vacios");
		}
	}
	
	private void limpiar() {
		txtNdepartamento.setText("");
		txtComision.setText("");
		txtSalario.setText("");
		txtDireccion.setText("");
		txtApellido.setText("");
		txtOficio.setText("");
	}
	
	private void insertarEmpleado(String apellido,String oficio,int dir,Float salario,Float comision,int  dept_no) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
	
		Empleados em = new Empleados(); // creo un objeto empleados
		//em.setEmpNo((short) 4456); // el numero de empleado es 4455
		em.setApellido(apellido);
		em.setDir((short) dir); // el director es el numero de empleado 7499
		em.setOficio(oficio);
		em.setSalario(salario);
		em.setComision(comision);
		Departamentos d = new Departamentos(); // creo un objeto Departamentos
		d.setDeptNo((byte) dept_no); // el número de dep es 10
		em.setDepartamentos(d);
		// fecha de alta
		java.util.Date hoy = new java.util.Date();
		java.sql.Date fecha = new java.sql.Date(hoy.getTime());
		em.setFechaAlt(fecha);
		try {
		session.save(em);
		try {
		tx.commit(); //necesario para que los datos se almacenen en la BBDD
		} catch (ConstraintViolationException e) {
		JOptionPane.showMessageDialog(null,"EMPLEADO DUPLICADO");
		System.out.printf("MENSAJE: %s%n", e.getMessage());
		System.out.printf("COD ERROR: %d%n", e.getErrorCode());
		System.out.printf("ERROR SQL: %s%n",
		e.getSQLException().getMessage());
		}
		} catch (TransientPropertyValueException e) {
			JOptionPane.showMessageDialog(null,"EL DEPARTAMENTO NO EXISTE");
			JOptionPane.showMessageDialog(null,"MENSAJE: %s%n", e.getMessage(), dept_no);
			JOptionPane.showMessageDialog(null,"Propiedad: %s%n", e.getPropertyName(), dept_no);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ERROR NO CONTROLADO....");
		e.printStackTrace();
		}
		session.close();
		
		//System.exit(0);
		}
	
public void mostrarDatos() {
	
	arrayDatos.clear();
	
	model.setRowCount(0);
	table.setModel(model);

	arrayDatos.add(nombreColumna);
	//read.selectFromDepartamento(arrayDatos);
	leerEmpleados();
			
	for(String[] data :arrayDatos) {
		model.addRow(data);
	}
	
	table.setModel(model);
	
}
public void leerEmpleados() {
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	Transaction tx = session.beginTransaction();
	Query query = session.createQuery("SELECT e FROM Empleados e");
	ArrayList<Empleados> empleados = (ArrayList<Empleados>) query.list();
	for (Empleados empleado : empleados) {
		String [] data= {empleado.getEmpNo()+"",empleado.getApellido(),empleado.getOficio(),empleado.getDir()+"",empleado.getSalario()+"",empleado.getComision()+"",empleado.getDepartamentos().getDeptNo()+"",empleado.getFechaAlt()+""};
		arrayDatos.add(data);
	}
}
}
