package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ProyectoFinal.Menu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JTree;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class LoginGrafico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnEntrar;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textCampoDni;
	private JTextField textCampoContra;

	public LoginGrafico() {

		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(38, 38, 38));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Diieg\\eclipse-workspace\\ProyectoFinal\\resources\\user32x32.png"));
		lblNewLabel.setBounds(312, 64, 38, 52);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ingreso al sistema");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblNewLabel_1.setToolTipText("asda");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 13, 164, 31);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_1);
		
				JLabel lblDNI = new JLabel("DNI (sin puntos y espacios)");
				lblDNI.setVerticalAlignment(SwingConstants.BOTTOM);
				lblDNI.setHorizontalAlignment(SwingConstants.LEFT);
				lblDNI.setForeground(Color.WHITE);
				lblDNI.setBounds(231, 158, 226, 32);
				lblDNI.setFont(new Font("Consolas", Font.PLAIN, 11));
				contentPane.add(lblDNI);

		JLabel lblContra = new JLabel("Contraseña");
		lblContra.setVerticalAlignment(SwingConstants.BOTTOM);
		lblContra.setForeground(Color.WHITE);
		lblContra.setBounds(231, 243, 246, 28);
		lblContra.setHorizontalAlignment(SwingConstants.LEFT);
		lblContra.setFont(new Font("Consolas", Font.PLAIN, 11));
		contentPane.add(lblContra);

		textCampoDni = new JTextField();
		textCampoDni.setBorder(null);
		textCampoDni.setBounds(231, 201, 216, 20);
		contentPane.add(textCampoDni);
		textCampoDni.setColumns(10);
		
		textCampoContra = new JTextField();
		textCampoContra.setBorder(null);
		textCampoContra.setBounds(232, 279, 215, 20);
		textCampoContra.setColumns(10);
		contentPane.add(textCampoContra);

		JButton btnENTRAR = new JButton("Entrar                                            >");
		btnENTRAR.setHorizontalAlignment(SwingConstants.LEFT);
		btnENTRAR.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnENTRAR.setIcon(null);
		btnENTRAR.setSelectedIcon(null);
		btnENTRAR.setBounds(230, 357, 216, 23);
		contentPane.add(btnENTRAR);
		btnENTRAR.setForeground(Color.WHITE);
		btnENTRAR.setBackground(SystemColor.textHighlight);

		btnENTRAR.addActionListener(new ActionListener() {
			int intentos = 1;
			public void actionPerformed(ActionEvent e) {
				intentos++;
				String username = textCampoDni.getText();
				String password = textCampoContra.getText();
				if (username.contentEquals("11222333") && (password.contentEquals("admin"))) {
					MenuGrafico mostrar = new MenuGrafico();
					mostrar.setVisible(true);
					mostrar.setLocationRelativeTo(null);
					dispose();
						
				} else {
					JOptionPane.showMessageDialog(null, "DNI o Contraseña incorrectos, vuelva a intentarlo.");
					textCampoDni.setText(null);
					textCampoContra.setText(null);
				}
				
				if (intentos>4) {
					JOptionPane.showMessageDialog(null, "Parece que lo intentaste varias veces.");
					dispose();
				}
				
				
			}
			

		});
		
		
		JLabel lblNewLabel_1_1 = new JLabel("BIENVENIDO");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(199, 114, 265, 32);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("© 2023 Creado y diseñado por Diego ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(154, 154, 154));
		lblNewLabel_2.setBounds(184, 436, 316, 14);
		contentPane.add(lblNewLabel_2);



	}
}
