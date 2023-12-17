package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ProyectoFinal.ConexionDB;
import ProyectoFinal.Materia;
import ProyectoFinal.Profesore;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MateriasGrafico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable TablaResult;
	private JTextField textField2;
	private JTextField textID;
	private JTextField textField3;

	public MateriasGrafico() {

		/* Creo la tabla y muestro los alumnos */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				CargarTabla();

			}

			private void CargarTabla() {
				DefaultTableModel ModeloTabla = new DefaultTableModel() {

					/// filas y columnas no son editables
					public boolean isCellEditable(int row, int colum) {
						return false;
					}

				};
				/// Establecemos los nombres de las columnas
				String titulos[] = { "ID", "Nombre" };
				ModeloTabla.setColumnIdentifiers(titulos);

				/// BD
				ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;
				try {
					rs = st.executeQuery("SELECT * FROM `materias`");
					while (rs.next()) {
						Materia materias = new Materia();
						materias.setId(rs.getInt("id"));
						materias.setNombre(rs.getString("nombre"));
						listaMaterias.add(materias);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				if (listaMaterias != null) {
					for (Materia mate : listaMaterias) {
						Object[] objeto = { mate.getId(), mate.getNombre() };

						ModeloTabla.addRow(objeto);
					}
				}
				TablaResult.setModel(ModeloTabla);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(38, 38, 38));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBounds(0, 10, 684, 398);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblMenu = new JLabel("Menú de Materias");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMenu.setBounds(159, 3, 365, 14);
		panel.add(lblMenu);

		textField = new JTextField();
		JButton btnEliminarMate = new JButton("Eliminar Materia");
		btnEliminarMate.setBackground(Color.WHITE);
		btnEliminarMate.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\eliminar16x16.png"));
		btnEliminarMate.setBorder(null);
		btnEliminarMate.setFocusPainted(false);
		btnEliminarMate.setBounds(42, 364, 138, 23);
		panel.add(btnEliminarMate);
		btnEliminarMate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;

				Object[] mensaje = { "Ingrese el id de la materia a eliminar", textField };
				int option = JOptionPane.showConfirmDialog(null, mensaje, "Eliminar Materia",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {

					try {
						String idMateriaEliminar = textField.getText();
						int elimok = st.executeUpdate("DELETE FROM materias WHERE id=" + idMateriaEliminar);
						if (elimok == 1) {
							JOptionPane.showMessageDialog(null, "Materia Eliminada correctamente");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No salio");
						e1.printStackTrace();
					}
				}
			}
		});

		JLabel lblListMat = new JLabel("Listado de Materias");
		lblListMat.setForeground(Color.WHITE);
		lblListMat.setBounds(42, 57, 113, 14);
		panel.add(lblListMat);

		TablaResult = new JTable();
		TablaResult.setBounds(0, 0, 1, 1);
		panel.add(TablaResult);

		JScrollPane scrollPane = new JScrollPane(TablaResult);
		scrollPane.setBounds(41, 71, 602, 137);
		panel.add(scrollPane);

		textID = new JTextField();
		textField2 = new JTextField();
		JButton btnModificarMaterias = new JButton("Modificar Materias");
		btnModificarMaterias.setBackground(Color.WHITE);
		btnModificarMaterias.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\editar16x16.png"));
		btnModificarMaterias.setBorder(null);
		btnModificarMaterias.setFocusPainted(false);
		btnModificarMaterias.setBounds(266, 364, 152, 23);
		panel.add(btnModificarMaterias);
		btnModificarMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;

				Object[] mensaje = { "Ingrese el id de la materia a modificar", textID };
				int option = JOptionPane.showConfirmDialog(null, mensaje, "Modificar Materia",JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					try {
						String id = textID.getText();
						
						Object[] campo = { "Ingrese el nuevo nombre de la materia", textField2 };
						int opcion = JOptionPane.showConfirmDialog(null, campo, "Modificar Materia",JOptionPane.OK_CANCEL_OPTION);
						
						if (opcion == JOptionPane.OK_OPTION) {
							String nombreID = textField2.getText();
							int update = st.executeUpdate("UPDATE `materias` SET `nombre` = '"+nombreID+"' WHERE `materias`.`id` = "+id+"");
							if (update==1) {
								JOptionPane.showMessageDialog(null, "Materia Modificada correctamente");
							}
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No salio");
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnMenuPrinc = new JButton(" Menú principal");
		btnMenuPrinc.setForeground(Color.WHITE);
		btnMenuPrinc.setFocusPainted(false);
		btnMenuPrinc.setBorderPainted(false);
		btnMenuPrinc.setIconTextGap(0);
		btnMenuPrinc.setHorizontalAlignment(SwingConstants.LEFT);
		btnMenuPrinc.setBackground(new Color(38, 38, 38));
		btnMenuPrinc.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\homablanc.png"));
		btnMenuPrinc.setBorder(null);
		btnMenuPrinc.setBounds(10, 8, 121, 23);
		panel.add(btnMenuPrinc);
		btnMenuPrinc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGrafico mostrar = new MenuGrafico();
				mostrar.setVisible(true);
				mostrar.setLocationRelativeTo(null);
				dispose();
			}
		});
		JLabel lblNewLabel_2 = new JLabel("© 2023 Creado y diseñado por Diego ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(154, 154, 154));
		lblNewLabel_2.setBounds(184, 436, 316, 14);
		contentPane.add(lblNewLabel_2);

		
		textField3 = new JTextField();
		JButton btnCrearMateria = new JButton("Crear Materia");
		btnCrearMateria.setBackground(Color.WHITE);
		btnCrearMateria.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\icons8-más-16.png"));
		btnCrearMateria.setBorder(null);
		btnCrearMateria.setFocusPainted(false);
		btnCrearMateria.setBounds(505, 364, 138, 23);
		panel.add(btnCrearMateria);
		btnCrearMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;

				Object[] mensaje = { "Ingrese el nombre de la materia a crear", textField3 };
				int option = JOptionPane.showConfirmDialog(null, mensaje, "Crear Materia",JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					try {
							String nombreNew = textField3.getText();
							int update= st.executeUpdate("INSERT INTO materias (nombre) VALUES ('"+nombreNew+"');");
							if (update==1) {
								JOptionPane.showMessageDialog(null, "Materia creada correctamente");
							}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No salio");
						e1.printStackTrace();
					}
				}
				
				
				
				
			}
		});

	}
}
