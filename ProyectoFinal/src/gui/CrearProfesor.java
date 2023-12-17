
package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ProyectoFinal.ConexionDB;
import java.awt.Color;


public class CrearProfesor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textdni;
	private JTextField textEmail;


	public CrearProfesor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(38, 38, 38));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCrearProfe = new JLabel("Crear Profesor");
		lblCrearProfe.setForeground(Color.WHITE);
		lblCrearProfe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCrearProfe.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearProfe.setBounds(214, 12, 255, 23);
		contentPane.add(lblCrearProfe);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\volverwhite.png"));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(38, 38, 38));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesoresGrafico volver = new ProfesoresGrafico();
				volver.setVisible(true);
				volver.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton_1.setBounds(13, 14, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblIngreseLosNuevos = new JLabel("Ingrese los nuevos datos del profesor");
		lblIngreseLosNuevos.setForeground(Color.WHITE);
		lblIngreseLosNuevos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIngreseLosNuevos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseLosNuevos.setBounds(214, 83, 255, 23);
		contentPane.add(lblIngreseLosNuevos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(Color.WHITE);
		lblIngreseLosNuevos.setForeground(Color.WHITE);
		lblIngreseLosNuevos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(299, 122, 86, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setForeground(Color.WHITE);
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setBounds(299, 172, 86, 14);
		contentPane.add(lblApellido);
		
		JLabel lblNombre_1_1 = new JLabel("DNI");
		lblNombre_1_1.setForeground(Color.WHITE);
		lblNombre_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1_1.setBounds(299, 224, 86, 14);
		contentPane.add(lblNombre_1_1);
		
		JLabel lblNombre_1_1_1 = new JLabel("Email");
		lblNombre_1_1_1.setForeground(Color.WHITE);
		lblNombre_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1_1_1.setBounds(299, 288, 86, 14);
		contentPane.add(lblNombre_1_1_1);
		
		textNombre = new JTextField();
		textNombre.setBorder(null);
		textNombre.setColumns(10);
		textNombre.setBounds(282, 141, 120, 20);
		contentPane.add(textNombre);
		
		textApellido = new JTextField();
		textApellido.setBorder(null);
		textApellido.setColumns(10);
		textApellido.setBounds(282, 189, 120, 20);
		contentPane.add(textApellido);
		
		textdni = new JTextField();
		textdni.setBorder(null);
		textdni.setColumns(10);
		textdni.setBounds(282, 245, 120, 20);
		contentPane.add(textdni);
		
		textEmail = new JTextField();
		textEmail.setBorder(null);
		textEmail.setColumns(10);
		textEmail.setBounds(282, 309, 120, 20);
		contentPane.add(textEmail);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBackground(SystemColor.textHighlight);
		btnAceptar.setBounds(297, 405, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB con = new ConexionDB();
				Statement st = con.conectar();
				ResultSet rs;					
					try {					
						String nombre = textNombre.getText();
						String apellido = textApellido.getText();
						String dni = textdni.getText();
						String email = textEmail.getText();
						int insok = st.executeUpdate("INSERT INTO profesores (nombre, apellido, dni, email) VALUES ('" + nombre + "','"
								+ apellido + "'," + dni + ",'" + email + "');");
						if (insok == 1) {
							JOptionPane.showMessageDialog(null, "Profesor agregado correctamente");
							textNombre.setText(null);
							textApellido.setText(null);
							textdni.setText(null);
							textEmail.setText(null);
						}					
					} catch (Exception a) {
						JOptionPane.showMessageDialog(null, "No se pudo crear el profesor");
						
					}	
			}
		});
	}

}
