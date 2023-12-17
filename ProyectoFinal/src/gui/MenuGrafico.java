package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class MenuGrafico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MenuGrafico() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(38, 38, 38));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Bienvenido al sistema administrativo");
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(125, 16, 434, 15);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNewLabel_1);

		JLabel lblEligaUnaOpcion = new JLabel("Eliga una opcion");
		lblEligaUnaOpcion.setForeground(Color.WHITE);
		lblEligaUnaOpcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblEligaUnaOpcion.setFont(new Font("Consolas", Font.BOLD, 12));
		lblEligaUnaOpcion.setBounds(213, 130, 246, 32);
		contentPane.add(lblEligaUnaOpcion);

		JButton btnOpcion = new JButton("       Alumnos");
		btnOpcion.setHorizontalAlignment(SwingConstants.LEFT);
		btnOpcion.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\usuario32x32.png"));
		btnOpcion.setForeground(Color.BLACK);
		btnOpcion.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnOpcion.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnOpcion.setBackground(Color.WHITE);
		btnOpcion.setBorder(UIManager.getBorder("Button.border"));
		btnOpcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlumnosGrafico mostrar = new AlumnosGrafico();
				mostrar.setVisible(true);
				mostrar.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnOpcion.setBounds(42, 202, 183, 50);
		contentPane.add(btnOpcion);

		JButton btnProfesores = new JButton("     Profesores");
		btnProfesores.setHorizontalAlignment(SwingConstants.LEFT);
		btnProfesores.setForeground(Color.BLACK);
		btnProfesores.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnProfesores.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\prof32x32.png"));
		btnProfesores.setBackground(Color.WHITE);
		btnProfesores.setBorder(UIManager.getBorder("Button.border"));
		btnProfesores.setFocusPainted(false);
		btnProfesores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesoresGrafico mostrar = new ProfesoresGrafico();
				mostrar.setVisible(true);
				mostrar.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnProfesores.setBounds(459, 202, 183, 50);
		contentPane.add(btnProfesores);

		JButton btnMaterias = new JButton("      Materias");
		btnMaterias.setHorizontalAlignment(SwingConstants.LEFT);
		btnMaterias.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\materias.png"));
		btnMaterias.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMaterias.setBackground(Color.WHITE);
		btnMaterias.setBorder(UIManager.getBorder("Button.border"));
		btnMaterias.setFocusPainted(false);
		btnMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MateriasGrafico mostrar = new MateriasGrafico();
				mostrar.setVisible(true);
				mostrar.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnMaterias.setBounds(252, 202, 183, 50);
		contentPane.add(btnMaterias);

		JButton btnSalir = new JButton("  Salir");
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setIconTextGap(0);
		btnSalir.setBorder(null);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(38, 38, 38));
		btnSalir.setIcon(new ImageIcon("C:\\\\Users\\\\Diieg\\\\eclipse-workspace\\\\ProyectoFinal\\\\resources\\\\exit16x16.png"));
		btnSalir.setBounds(612, 13, 72, 23);
		contentPane.add(btnSalir);
		
		JLabel lblNewLabel_2 = new JLabel("© 2023 Creado y diseñado por Diego ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(154, 154, 154));
		lblNewLabel_2.setBounds(184, 436, 316, 14);
		contentPane.add(lblNewLabel_2);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);		
			}
		});
	}
}
