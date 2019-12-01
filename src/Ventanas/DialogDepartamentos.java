package Ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.mapping.List;

import CRUD.Read;
import Hibernate.Departamentos;
import Singleton.HibernateUtil;

import java.awt.TextField;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;

public class DialogDepartamentos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TextField txtDeptNo,txtNombre,txtLoc;
	private JTable tableDepartamentos;
	private ArrayList<String[]>arrayDatos=new ArrayList<String[]>();
	
	private Read read = new Read();
	private DefaultTableModel  model;
	private JLabel lbAlert;
	String[] nombreColumna= {
			"Nº Departamento",
			"Nombre departamento",
			"Localidad",
			};

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DialogDepartamentos(JFrame parent, boolean mode) {
		super(parent, mode);
		setType(Type.UTILITY);
		getContentPane().setFont(new Font("Kobold Hunter Beta", Font.PLAIN, 11));
		setFont(new Font("Kobold Hunter Beta", Font.PLAIN, 15));
		setForeground(new Color(169, 169, 169));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBackground(new Color(128, 128, 128));
		getContentPane().setBackground(new Color(135, 206, 235));
		setBounds(100, 100, 743, 440);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.textInactiveText);
		getContentPane().add(panel_3, BorderLayout.NORTH);
		contentPanel.setBackground(new Color(169, 169, 169));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
				contentPanel.setLayout(null);
			
				JPanel panelDatos = new JPanel();
				panelDatos.setBorder(new MatteBorder(4, 1, 4, 1, (Color) new Color(0, 0, 0)));
				panelDatos.setFont(new Font("Javanese Text", Font.BOLD, 13));
				panelDatos.setBackground(new Color(169, 169, 169));
				panelDatos.setBounds(10, 11, 191, 326);
				contentPanel.add(panelDatos);
				panelDatos.setLayout(null);
				
					txtNombre = new TextField();
					txtNombre.setBounds(25, 132, 133, 24);
					panelDatos.add(txtNombre);
					
					
					txtDeptNo = new TextField();
					txtDeptNo.setBounds(25, 82, 114, 24);
					panelDatos.add(txtDeptNo);
					
						txtLoc = new TextField();
						txtLoc.setBounds(25, 182, 133, 24);
						panelDatos.add(txtLoc);
						
						
							JButton BtnCrear = new JButton("Crear");
							BtnCrear.setForeground(Color.GRAY);
							BtnCrear.setBackground(Color.BLACK);
							BtnCrear.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									crearDepartamento();
								}
							});
							BtnCrear.setBounds(82, 236, 76, 24);
							panelDatos.add(BtnCrear);
							BtnCrear.setActionCommand("OK");
							getRootPane().setDefaultButton(BtnCrear);
							
							lbAlert = new JLabel("");
							lbAlert.setForeground(Color.RED);
							lbAlert.setBounds(12, 273, 167, 40);
							panelDatos.add(lbAlert);
							
							JLabel lblNewLabel = new JLabel("DeptNo");
							lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 13));
							lblNewLabel.setBounds(25, 52, 66, 24);
							panelDatos.add(lblNewLabel);
							
							JLabel lblNewLabel_1 = new JLabel("Nombre");
							lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 13));
							lblNewLabel_1.setBounds(25, 112, 66, 14);
							panelDatos.add(lblNewLabel_1);
							
							JLabel lblNewLabel_2 = new JLabel("Localidad");
							lblNewLabel_2.setFont(new Font("Georgia", Font.BOLD, 13));
							lblNewLabel_2.setBounds(25, 162, 76, 14);
							panelDatos.add(lblNewLabel_2);
			
			JPanel panel = new JPanel();
			panel.setBorder(new MatteBorder(4, 1, 4, 1, (Color) new Color(0, 0, 0)));
			panel.setBackground(new Color(128, 128, 128));
			panel.setBounds(207, 11, 390, 326);
			contentPanel.add(panel);
		
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(128, 128, 128));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton BtnVolver = new JButton("VOLVER");
				BtnVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getParent().setVisible(true);
						dispose();
					}
				});
				BtnVolver.setActionCommand("Cancel");
				buttonPane.add(BtnVolver);

				String data[][]= {};
				
				
				
				model =new DefaultTableModel(data,nombreColumna);
				panel.setLayout(new GridLayout(0, 1, 0, 0));
				tableDepartamentos = new JTable(data,nombreColumna);
				tableDepartamentos.setBackground(new Color(169, 169, 169));
				panel.add(tableDepartamentos);
				
				JPanel panel_2 = new JPanel();
				panel_2.setBounds(602, 11, 118, 326);
				contentPanel.add(panel_2);
				panel_2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
				panel_2.setBackground(new Color(128, 128, 128));
				panel_2.setLayout(null);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(6, 12, 106, 60);
				panel_1.setBorder(UIManager.getBorder("ComboBox.border"));
				panel_2.add(panel_1);
				
				JButton btnDelete = new JButton("Borrar");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						borrarDepartamento();
					}
				});
				
				JButton btnActualizar = new JButton("Actualizar");
				btnActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						actualizarDepartamento();
					}
				});
				panel_1.setLayout(new GridLayout(0, 1, 0, 0));
				panel_1.add(btnActualizar);
				panel_1.add(btnDelete);
				Cargardatos();
			
		
	}
	private void borrarDepartamento() {
		if(tableDepartamentos.getSelectedRow()>0) {
			//Se obtiene la sesión creada por el Singleton. Se debe usar esta línea a lo largo
			//de todas las clases en las que deseemos realizar operaciones con la bbdd.
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			// Creo mi departamento 
			Departamentos dep = new Departamentos();
		
			// creo las variables las cuales ingresare en el departamento
		
				int numDept=Integer.parseInt( (String) model.getValueAt(tableDepartamentos.getSelectedRow(), 0));
				
			//	String nombre_departamento=(String) model.getValueAt(tableDepartamentos.getSelectedRow(), 1);

			//	String localizacion_departamento=(String) model.getValueAt(tableDepartamentos.getSelectedRow(), 2);
				
				dep.setDeptNo((byte)numDept);
			//	dep.setDnombre(nombre_departamento);
			//	dep.setLoc(localizacion_departamento);
				session.delete(dep);
				try {
					tx.commit();
					} catch (ConstraintViolationException e) {
					JOptionPane.showMessageDialog(null,e.getMessage()+"\n"+e.getErrorCode()+"\n"+
					 e.getSQLException().getMessage());
					}
					Cargardatos();
					session.close();
				
				
		}
		
	}
	private void actualizarDepartamento() {
		if(tableDepartamentos.getSelectedRow()>0) {
			//Se obtiene la sesión creada por el Singleton. Se debe usar esta línea a lo largo
			//de todas las clases en las que deseemos realizar operaciones con la bbdd.
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			// Creo mi departamento 
			Departamentos dep = new Departamentos();
		
			// creo las variables las cuales ingresare en el departamento
		
				int numDept=Integer.parseInt( (String) model.getValueAt(tableDepartamentos.getSelectedRow(), 0));
				String nombre_departamento="", localizacion_departamento="";
				if(txtNombre.getText().isEmpty()) {
					nombre_departamento=(String) model.getValueAt(tableDepartamentos.getSelectedRow(), 1);

					
				}
				else {
					nombre_departamento=txtNombre.getText();
				}
				if(txtLoc.getText().isEmpty()) {
					localizacion_departamento=(String) model.getValueAt(tableDepartamentos.getSelectedRow(), 2);
				}
				else {
					localizacion_departamento=txtLoc.getText();
				}
				
				
				
				dep.setDeptNo((byte)numDept);
				dep.setDnombre(nombre_departamento);
				dep.setLoc(localizacion_departamento);
				session.update(dep);
				try {
					tx.commit();
					} catch (ConstraintViolationException e) {
					System.out.printf("MENSAJE: %s%n",e.getMessage());
					System.out.printf("COD ERROR: %d%n",e.getErrorCode());
					System.out.printf("ERROR SQL: %s%n" ,
					 e.getSQLException().getMessage());
					}
					Cargardatos();
					session.close();
				
				
		}
		else {
			JOptionPane.showMessageDialog(null,"debe seleccionar primero un elemento de la tabla");
		}
	}
	private void crearDepartamento() {
		//Se obtiene la sesión creada por el Singleton. Se debe usar esta línea a lo largo
		//de todas las clases en las que deseemos realizar operaciones con la bbdd.
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		// Creo mi departamento 
		Departamentos dep = new Departamentos();
	
		// creo las variables las cuales ingresare en el departamento
		try {
			int numDept=Integer.parseInt(txtDeptNo.getText());
			
			String nombre_departamento=txtNombre.getText();

			String localizacion_departamento=txtLoc.getText();
			
			dep.setDeptNo((byte)numDept);
			dep.setDnombre(nombre_departamento);
			dep.setLoc(localizacion_departamento);
			session.save(dep);
			try {
			tx.commit();
			} catch (ConstraintViolationException e) {
			System.out.println("DEPARTAMENTO DUPLICADO");
			System.out.printf("MENSAJE: %s%n",e.getMessage());
			System.out.printf("COD ERROR: %d%n",e.getErrorCode());
			System.out.printf("ERROR SQL: %s%n" ,
			 e.getSQLException().getMessage());
			}
			Cargardatos();
			session.close();
		}
		catch(NumberFormatException ex) {
			lbAlert.setText("ha introducido un caracter erroneo comprube su id");
		}
		
		
	}
	private void Cargardatos() {
		arrayDatos.clear();
		
		model.setRowCount(0);
		tableDepartamentos.setModel(model);
	
		arrayDatos.add(nombreColumna);
		//read.selectFromDepartamento(arrayDatos);
		leerDepartamentos();
				
		for(String[] data :arrayDatos) {
			model.addRow(data);
		}
		
		tableDepartamentos.setModel(model);
		//leerDepartamentos();
		
	}
	private void leerDepartamentos() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("SELECT d FROM Departamentos d");
		ArrayList<Departamentos> deparamentos = (ArrayList<Departamentos>) query.list();
		for (Departamentos depart : deparamentos) {
			String [] data= {depart.getDeptNo()+"",depart.getDnombre(),depart.getLoc()};
			arrayDatos.add(data);
		}
	}
}
