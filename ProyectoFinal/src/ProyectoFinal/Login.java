package ProyectoFinal;

import java.util.Scanner;

public class Login {
	


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int intentos = 1;
		
		
		String DNI = ("11222333");
		String pass = ("admin");

		String user = null;
		String password = null;

		System.out.println("Por favor ingrese su DNI y Contraseña para acceder al sistema.");

		System.out.println("DNI (sin puntos y espacios)");
		user = in.next();
		System.out.println("Contraseña");
		password = in.next();

		if (user.equals(DNI) && (password.equals(pass))) {
			System.out.println("Bien!");
			System.out.println("===================");
			System.out.println("\n\n");
			Menu men = new Menu();
			men.main(null);
		}
		while (!user.equals(DNI) | (!password.equals(pass))) {
			System.err.println("DNI o Contraseña incorrectos, vuelva a intentarlo.");
			System.out.println("DNI (sin puntos y espacios)");
			user = in.next();
			System.out.println("Contraseña");
			password = in.next();
			intentos++;

			if (user.equals(DNI) && (password.equals(pass))) {
				System.out.println("Bien!");
				System.out.println("===================");
				System.out.println("\n\n\n\n");
				Menu men = new Menu();
				men.main(null);
			} else if (intentos > 4) {
				System.err.println("Parece que lo intentaste varias veces");
				break;
			}
		}
	}
}
