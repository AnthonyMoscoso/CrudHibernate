package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CRUD.Create;
import CRUD.Delete;
import CRUD.Lanzador;
import CRUD.Read;
import CRUD.Update;
import Hibernate.Departamentos;
import Jasper.Jasper;
import Singleton.HibernateUtil;

import javax.swing.JTabbedPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class Ventana {

	private JFrame frame;
	private Lanzador  lanzador=null;
	private Create create= new Create();
	private Read read=new Read();
	private Delete delete = new Delete();
	private Update update= new Update();
	
	private ArrayList<String[]>arrayDatos=new ArrayList<String[]>();
	private DefaultTableModel  model;
	String[] nombreColumna= {
			"Nº Departamento",
			"Nombre departamento",
			"Localidad",
			"Nº Empleado",
			"Apellido",
			"Oficio",
			"Direccion",
			"Fecha",
			"Salario",
			"Comision",
			"Departamento",
			};
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
		lanzador=new Lanzador();
		lanzador.lanzar();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1124, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnEmpleados = new JButton("Empleados");
		panel_1.add(btnEmpleados);
		
		JButton btnApartamento = new JButton("Departamento");
		panel_1.add(btnApartamento);
		
		JButton btnJasper = new JButton("Jasper");
		btnJasper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jasper jasper = new Jasper();
				String [] args = {""};
				jasper.main(args);
			}
		});
		panel_1.add(btnJasper);
		btnApartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirDepartamento();
				Cargardatos();
			}
		});
		btnEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				abrirEmpleados();
				Cargardatos();
					
				
			}
		});
	
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		String data[][]= {};
	
		
		model =new DefaultTableModel(data,nombreColumna);
		table = new JTable(data,nombreColumna);
		panel_2.add(table);
		Cargardatos();
	

		
		
	}
	private void abrirEmpleados() {
		DialogEmpleados nuevo = new DialogEmpleados(frame,true);
		frame.dispose();
		nuevo.setVisible(true);
	}
	private void abrirDepartamento() {
		DialogDepartamentos nuevo = new DialogDepartamentos(frame,true);
		frame.dispose();
		nuevo.setVisible(true);
		 
		}
	
	private void Cargardatos() {
		arrayDatos.clear();
		model.setRowCount(0);
		table.setModel(model);
	
		arrayDatos.add(nombreColumna);
		read.SelectAll(arrayDatos);
				
		for(String[] data :arrayDatos) {
			model.addRow(data);
		}
		
		table.setModel(model);
		
	}
	
}
