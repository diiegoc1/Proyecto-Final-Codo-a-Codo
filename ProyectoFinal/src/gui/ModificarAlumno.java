
package gui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ProyectoFinal.Alumno;
import ProyectoFinal.ConexionDB;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Panel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class ModificarAlumno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textID;
	private JTextField textField1;
	private JTextField textField;
	

	public ModificarAlumno() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				CargarTabla();
			}

			public void CargarTabla() {

				DefaultTableModel tabla = new DefaultTableModel();

				String titulos[] = { "ID", "Nombre", "Apellido", "dni", "Email" };
				tabla.setColumnIdentifiers(titulos);

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

				if (listaAlumos != null) {
					for (Alumno alumno : listaAlumos) {
						Object[] objeto = { alumno.getId(), alumno.getNombre(), alumno.getApellido(), alumno.getDni(),
								alumno.getCorreo() };

						tabla.addRow(objeto);
					}
				}
				table.setModel(tabla);
				
				

				
			}
			
			
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(38, 38, 38));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Modificar Alumno");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(215, 11, 255, 23);
		contentPane.add(lblNewLabel);

		JLabel lblListaAlum = new JLabel("Listado de Alumnos");
		lblListaAlum.setForeground(Color.WHITE);
		lblListaAlum.setHorizontalAlignment(SwingConstants.LEFT);
		lblListaAlum.setBounds(16, 67, 121, 14);
		contentPane.add(lblListaAlum);

		table = new JTable();
		table.setBounds(20, 81, 654, 120);
		contentPane.add(table);

		textID = new JTextField();	
		textID.setBorder(null);
		
		textID.setBounds(296, 237, 86, 20);
		contentPane.add(textID);
		textID.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Ingrese el ID del alumno a modificar");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(236, 212, 222, 14);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Que datos desea modificar");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(134, 304, 401, 23);
		contentPane.add(lblNewLabel_7);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(16, 81, 658, 120);
		contentPane.add(scrollPane);

		JButton btnNewButton_1 = new JButton("  Volver");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(38, 38, 38));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\volverwhite.png"));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(16, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlumnosGrafico volver = new AlumnosGrafico();
				volver.setVisible(true);
				volver.setLocationRelativeTo(null);
				dispose();
			}
		});
		

		
		textField  = new JTextField();
		JButton btnNombre = new JButton("Nombre");
		btnNombre.setBackground(Color.WHITE);
		btnNombre.setBorder(null);
		btnNombre.setBounds(93, 348, 89, 23);
		contentPane.add(btnNombre);
		btnNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;
				
				Object [] mensaje = {"Ingrese el nuevo nombre del alumno", textField};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Modificar Nombre", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION) {	
					try {
						String dato = textID.getText();
						String textoN = textField.getText();
						int updateok = st.executeUpdate("UPDATE `alumnos` SET  `nombre` = '" + textoN +"' WHERE `alumnos`.`id` = '" + dato + "' ");
						if (updateok == 1) {
							JOptionPane.showMessageDialog(null, "Nombre modificado correctamente");
						}					
					} catch (Exception a) {
						JOptionPane.showMessageDialog(null, "No salio");
						a.printStackTrace();
					}
				}
	
		
			}
		});

		
		
		textField  = new JTextField();
		JButton btnApellido = new JButton("Apellido");
		btnApellido.setBackground(Color.WHITE);
		btnApellido.setBorder(null);
		btnApellido.setBounds(238, 348, 89, 23);
		contentPane.add(btnApellido);
		btnApellido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;
				
				Object [] mensaje = {"Ingrese el nuevo Apellido del alumno", textField};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Modificar Apellido", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION) {	
					try {
						String dato = textID.getText();
						String textoA = textField.getText();
						int updateok = st.executeUpdate("UPDATE `alumnos` SET  `apellido` = '" + textoA +"' WHERE `alumnos`.`id` = '" + dato + "' ");
						if (updateok == 1) {
							JOptionPane.showMessageDialog(null, "Apellido modificado correctamente");
						}					
					} catch (Exception g) {
						JOptionPane.showMessageDialog(null, "No salio");
						g.printStackTrace();
					}
				}
			
			}
		});

		
		
		
		textField  = new JTextField();
		JButton btndni = new JButton("dni");
		btndni.setBackground(Color.WHITE);
		btndni.setBorder(null);
		btndni.setBounds(369, 348, 89, 23);
		contentPane.add(btndni);
		btndni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;
				
				Object [] mensaje = {"Ingrese el nuevo DNI del alumno", textField};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Modificar DNI", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION) {	
					try {
						String dato = textID.getText();
						String textoD = textField.getText();
						int updateok = st.executeUpdate("UPDATE `alumnos` SET  `dni` = '" + textoD +"' WHERE `alumnos`.`id` = '" + dato + "' ");
						if (updateok == 1) {
							JOptionPane.showMessageDialog(null, "DNI modificado correctamente");
						}					
					} catch (Exception g) {
						JOptionPane.showMessageDialog(null, "No salio");
						g.printStackTrace();
					}
				}
			}
		});

		
		

		
		
		
		
		textField  = new JTextField();
		JButton btnEmail = new JButton("Email");
		btnEmail.setBackground(Color.WHITE);
		btnEmail.setBorder(null);
		btnEmail.setBounds(502, 348, 89, 23);
		contentPane.add(btnEmail);
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;
				
				Object [] mensaje = {"Ingrese el nuevo Email del alumno", textField};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Modificar Email", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION) {	
					try {
						String dato = textID.getText();
						String textoE = textField.getText();
						int updateok = st.executeUpdate("UPDATE `alumnos` SET  `email` = '" + textoE +"' WHERE `alumnos`.`id` = '" + dato + "' ");
						if (updateok == 1) {
							JOptionPane.showMessageDialog(null, "Email modificado correctamente");
						}					
					} catch (Exception g) {
						JOptionPane.showMessageDialog(null, "No salio");
						g.printStackTrace();
					}
				}
			
			
			}
		});


	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
