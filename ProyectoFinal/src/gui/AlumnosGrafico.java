
package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Panel;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.List;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JList;
import javax.swing.JEditorPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ProyectoFinal.Alumno;
import ProyectoFinal.ConexionDB;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JSlider;
import java.awt.Dimension;
import javax.swing.DropMode;
import java.awt.ComponentOrientation;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

public class AlumnosGrafico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable TablaResult;

	public AlumnosGrafico() {
		
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
				ArrayList<Alumno> listaAlumos = new ArrayList<Alumno>();
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;
				try {
					rs = st.executeQuery("SELECT * FROM `alumnos`");
					while (rs.next()) {
						Alumno alumn = new Alumno();
						alumn.setId(rs.getInt("id"));
						alumn.setNombre(rs.getString("nombre"));
						alumn.setApellido(rs.getString("apellido"));
						alumn.setDni(rs.getInt("dni"));
						alumn.setCorreo(rs.getString("email"));
						listaAlumos.add(alumn);
					}
					 
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (listaAlumos!=null) {
					for (Alumno alumno : listaAlumos) {
						Object[] objeto =  {alumno.getId(), alumno.getNombre(), alumno.getApellido(), 
								alumno.getDni(), alumno.getCorreo()};
						
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

		JLabel lblNewLabel = new JLabel("Menú de Alumnos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(159, 11, 365, 14);
		panel.add(lblNewLabel);
		
		
		textField  = new JTextField();
		JButton btnEliminarAlumnos = new JButton(" Eliminar Alumnos");
		btnEliminarAlumnos.setBackground(Color.WHITE);
		btnEliminarAlumnos.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\eliminar16x16.png"));
		btnEliminarAlumnos.setBorder(null);
		btnEliminarAlumnos.setFocusPainted(false);
		btnEliminarAlumnos.setBounds(42, 364, 138, 23);
		panel.add(btnEliminarAlumnos);
		btnEliminarAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;
		
				Object [] mensaje = {"Ingrese el id del alumno a eliminar", textField};
				int option = JOptionPane.showConfirmDialog(null, mensaje, "Eliminar Alumno", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {		

				try {
					String idAlumnoEliminar = textField.getText();		
					int elimok = st.executeUpdate("DELETE FROM alumnos WHERE id=" +idAlumnoEliminar);
					if (elimok == 1) {
						JOptionPane.showMessageDialog(null, "Alumno Eliminado correctamente");
						}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "No salio");
					e1.printStackTrace();
				}
				}
				
			}
		});


		JLabel lblNewLabel_1 = new JLabel("Listado de Alumnos");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(42, 57, 138, 14);
		panel.add(lblNewLabel_1);	
		
		TablaResult = new JTable();
		TablaResult.setBounds(0, 0, 1, 1);
		panel.add(TablaResult);
		
		JScrollPane scrollPane = new JScrollPane(TablaResult);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(41, 71, 602, 214);
		panel.add(scrollPane);
	
		textField  = new JTextField();
		JButton btnModificarAlumnos = new JButton(" Modificar Alumnos");
		btnModificarAlumnos.setBackground(Color.WHITE);
		btnModificarAlumnos.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\editar16x16.png"));
		btnModificarAlumnos.setBorder(null);
		btnModificarAlumnos.setFocusPainted(false);
		btnModificarAlumnos.setBounds(266, 364, 152, 23);
		panel.add(btnModificarAlumnos);
		btnModificarAlumnos.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
					ModificarAlumno pantalla = new ModificarAlumno();
					pantalla.setVisible(true);
					pantalla.setLocationRelativeTo(null);

					dispose();	
		}
	}
);
		

		
		JButton btnCrearAlumnos = new JButton(" Crear Alumnos");
		btnCrearAlumnos.setBounds(506, 364, 138, 23);
		panel.add(btnCrearAlumnos);
		btnCrearAlumnos.setBackground(Color.WHITE);
		btnCrearAlumnos.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\icons8-más-16.png"));
		btnCrearAlumnos.setBorder(null);
		btnCrearAlumnos.setFocusPainted(false);
		btnCrearAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearAlumnos pantalla = new CrearAlumnos();
				pantalla.setVisible(true);
				pantalla.setLocationRelativeTo(null);
				dispose();		
			}
		});
	
		JButton btnhome = new JButton(" Menú principal");
		btnhome.setForeground(Color.WHITE);
		btnhome.setFocusPainted(false);
		btnhome.setBorderPainted(false);
		btnhome.setIconTextGap(0);
		btnhome.setHorizontalAlignment(SwingConstants.LEFT);
		btnhome.setBackground(new Color(38, 38, 38));
		btnhome.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\homablanc.png"));
		btnhome.setBorder(null);
		btnhome.setBounds(10, 8, 121, 23);
		panel.add(btnhome);
		
		JLabel lblNewLabel_2 = new JLabel("© 2023 Creado y diseñado por Diego ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(154, 154, 154));
		lblNewLabel_2.setBounds(184, 436, 316, 14);
		contentPane.add(lblNewLabel_2);
		btnhome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGrafico mostrar = new MenuGrafico();
				mostrar.setVisible(true);
				mostrar.setLocationRelativeTo(null);
				dispose();
			}
		});
	}
}



