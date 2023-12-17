package ProyectoFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int op = 0;
		List<String> menuin = new ArrayList<String>();
		menuin.add("\nBienvenido al sistema");
		menuin.add("Eliga una opcion");
		menuin.add("[1] Alumnos");
		menuin.add("[2] Profesores");
		menuin.add("[3] Materias");
		menuin.add("[9] Salir");

		do {
			for (String opcion : menuin) {
				System.out.println(opcion);
			}
			op = in.nextInt();

			switch (op) {
			case 1:
				Alumnos alum = new Alumnos();
				alum.MostrarMenuAlum();
				break;

			case 2:
				Profesores prof=new Profesores();
				prof.MostrarMenuProf();
				
				break;

			case 3:
				Materias mat=new Materias();
				mat.MostrarMenuMat();
				
				break;

			case 9:
				System.out.println("Saludos.");
				break;

			default:
				System.err.println("Opcion no valida vuelva a intentarlo.");
				break;
			}

		} while (op != 9);

	}

}
