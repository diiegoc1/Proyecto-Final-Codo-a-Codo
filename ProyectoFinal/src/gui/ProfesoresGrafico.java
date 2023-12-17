
package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ProyectoFinal.Alumno;
import ProyectoFinal.ConexionDB;
import ProyectoFinal.Profesore;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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
import java.awt.Panel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Component;


public class ProfesoresGrafico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable TablaResult;


	public ProfesoresGrafico() {
		
		/*Creo la tabla y muestro los alumnos */
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
				String titulos[] = { "ID", "Nombre", "Apellido", "dni", "Email" };
				ModeloTabla.setColumnIdentifiers(titulos);

				///BD
				ArrayList<Profesore> listaProfes = new ArrayList<Profesore>();
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;
				try {
					rs = st.executeQuery("SELECT * FROM `profesores`");
					while (rs.next()) {
						Profesore profes = new Profesore();
						profes.setId(rs.getInt("id"));
						profes.setNombre(rs.getString("nombre"));
						profes.setApellido(rs.getString("apellido"));
						profes.setDni(rs.getInt("dni"));
						profes.setCorreo(rs.getString("email"));
						listaProfes.add(profes);
					}
					 
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (listaProfes!=null) {
					for (Profesore profe : listaProfes) {
						Object[] objeto =  {profe.getId(), profe.getNombre(), profe.getApellido(), 
								profe.getDni(), profe.getCorreo()};
						
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

		JLabel lblNewLabel = new JLabel("Menú de Profesores");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(159, 3, 365, 14);
		panel.add(lblNewLabel);
		
		
		textField  = new JTextField();
		JButton btnEliminarProfesor = new JButton("Eliminar Profesor");
		btnEliminarProfesor.setBackground(Color.WHITE);
		btnEliminarProfesor.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\eliminar16x16.png"));
		btnEliminarProfesor.setBorder(null);
		btnEliminarProfesor.setFocusPainted(false);
		btnEliminarProfesor.setBounds(42, 364, 138, 23);
		panel.add(btnEliminarProfesor);
		btnEliminarProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;
		
				Object [] mensaje = {"Ingrese el id del profesor a eliminar", textField};
				int option = JOptionPane.showConfirmDialog(null, mensaje, "Eliminar Alumno", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {		

				try {
					String idProfesorEliminar = textField.getText();		
					int elimok = st.executeUpdate("DELETE FROM profesores WHERE id=" +idProfesorEliminar);
					if (elimok == 1) {
						JOptionPane.showMessageDialog(null, "Profesor Eliminado correctamente");
						}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "No salio");
					e1.printStackTrace();
				}
			}				
		}
	});


		JLabel lblListProf = new JLabel("Listado de Profesores");
		lblListProf.setForeground(Color.WHITE);
		lblListProf.setBounds(42, 57, 161, 14);
		panel.add(lblListProf);	
		
		TablaResult = new JTable();
		TablaResult.setBounds(0, 0, 1, 1);
		panel.add(TablaResult);
		
		JScrollPane scrollPane = new JScrollPane(TablaResult);
		scrollPane.setBounds(41, 71, 602, 214);
		panel.add(scrollPane);
	
		textField  = new JTextField();
		JButton btnModificarProfesores = new JButton("Modificar Profesor");
		btnModificarProfesores.setBackground(Color.WHITE);
		btnModificarProfesores.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\editar16x16.png"));
		btnModificarProfesores.setBorder(null);
		btnModificarProfesores.setFocusPainted(false);
		btnModificarProfesores.setBounds(266, 364, 152, 23);
		panel.add(btnModificarProfesores);
		btnModificarProfesores.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
					ModificarProfesor pantalla = new ModificarProfesor();
					pantalla.setVisible(true);
					pantalla.setLocationRelativeTo(null);

					dispose();	
		}
	}
);
		
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
		
		JButton btnCrearProfesor = new JButton("Crear Profesor");
		btnCrearProfesor.setBounds(506, 364, 138, 23);
		panel.add(btnCrearProfesor);
		btnCrearProfesor.setBackground(Color.WHITE);
		btnCrearProfesor.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\icons8-más-16.png"));
		btnCrearProfesor.setBorder(null);
		btnCrearProfesor.setFocusPainted(false);
		
		JLabel lblNewLabel_2 = new JLabel("© 2023 Creado y diseñado por Diego ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(154, 154, 154));
		lblNewLabel_2.setBounds(184, 436, 316, 14);
		contentPane.add(lblNewLabel_2);
		btnCrearProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearProfesor pantalla = new CrearProfesor();
				pantalla.setVisible(true);
				pantalla.setLocationRelativeTo(null);
				dispose();		
			}
		});


	}
}
