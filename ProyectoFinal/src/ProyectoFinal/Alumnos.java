package ProyectoFinal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Alumnos {

	public void MostrarMenuAlum() {

		Scanner in = new Scanner(System.in);

		int op = 0;

		List<String> menuAlum = new ArrayList<String>();

		menuAlum.add("\n\nMenú de Alumnos");
		menuAlum.add("[1] Listar Alumnos");
		menuAlum.add("[2] Modificar Alumnos");
		menuAlum.add("[3] Eliminar Alumnos");
		menuAlum.add("[4] Crear Alumnos");
		menuAlum.add("[0] Volver al menú principal");

		do {
			for (String opcion : menuAlum) {
				System.out.println(opcion);
			}
			op = in.nextInt();

			switch (op) {
			case 1:
				Alumnos listar = new Alumnos();
				listar.ListarAlumnos(in);
				break;
			case 2:
				Alumnos modificar = new Alumnos();
				modificar.ModificarAlumnos(in);
				break;
			case 3:
				Alumnos eliminar = new Alumnos();
				eliminar.EliminarAlumnos(in);
				break;
			case 4:
				Alumnos crear = new Alumnos();
				crear.CrearAlumnos(in);
				break;
			case 5:
				Alumnos materias = new Alumnos();
				materias.MatPorAlumnos();
				break;
			default:
				break;
			}

		} while (op != 0);

	}

	public void ListarAlumnos(Scanner in) {

		List<Alumno> listAlum = new ArrayList<Alumno>();

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
				listAlum.add(alumn);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n                  Lista de alumnos             ");
		System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");

		for (Alumno alumno : listAlum) {
			System.out.printf("   %-6d%-13s%-15s%-10d%-15s%n", alumno.getId(), alumno.getNombre(), alumno.getApellido(),
					alumno.getDni(), alumno.getCorreo());
		}
		System.out.println("¿Desea buscar alumno? si/no");
		String busc = in.next();
		if (busc.equals("si")) {
			listAlum = BuscarAlumnos(in, st);
		}

	}

	public void ModificarAlumnos(Scanner in) {

		System.out.println("Busqueda de alumno por nombre, apellido o dni");
		String mod = in.next();

		List<Alumno> listAlum = new ArrayList<Alumno>();

		ConexionDB con = new ConexionDB();
		Statement st = con.conectar();
		ResultSet rs;
		try {
			rs = st.executeQuery("SELECT * FROM alumnos WHERE nombre LIKE '" + mod + "%' OR dni LIKE '" + mod
					+ "%' OR apellido LIKE '" + mod + "%'");
			while (rs.next()) {
				Alumno alumn = new Alumno();
				alumn.setId(rs.getInt("id"));
				alumn.setNombre(rs.getString("nombre"));
				alumn.setApellido(rs.getString("apellido"));
				alumn.setDni(rs.getInt("dni"));
				alumn.setCorreo(rs.getString("email"));
				listAlum.add(alumn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n\n  Estos son los alumnos que coinciden con su busqueda");
		System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");

		for (Alumno alumno : listAlum) {
			System.out.printf("   %-6d%-12s%-15s%-10d%-15s%n", alumno.getId(), alumno.getNombre(), alumno.getApellido(),
					alumno.getDni(), alumno.getCorreo());
		}

		System.out.println("Ingrese el ID del alumno a modificar");
		int id = in.nextInt();

		System.out.println("Que datos desea modificar: 1- Nombre 2- Apellido 3- Dni 4- Email");
		int algo = in.nextInt();

		switch (algo) {
		case 1:
			System.out.println("Ingrese el nuevo NOMBRE ");
			String modnom = in.next();

			try {
				int modmok = st.executeUpdate(
						"UPDATE `alumnos` SET  `nombre` = '" + modnom + "' WHERE `alumnos`.`id` = '" + id + "' ");
				if (modmok == 1) {
					System.out.println("Alumno modificado correctamente");
				} else {
					System.err.println("No se pudo modificar el alumno");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case 2:
			System.out.println("Ingrese el nuevo Apellido");
			String modape = in.next();

			try {
				int modmok = st.executeUpdate(
						"UPDATE `alumnos` SET  `apellido` = '" + modape + "' WHERE `alumnos`.`id` = '" + id + "' ");
				if (modmok == 1) {
					System.out.println("Alumno modificado correctamente");
				} else {
					System.err.println("No se pudo modificar el alumno");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case 3:
			System.out.println("Ingrese el nuevo Dni");
			int moddni = in.nextInt();

			try {
				int modmok = st.executeUpdate(
						"UPDATE `alumnos` SET  `dni` = '" + moddni + "' WHERE `alumnos`.`id` = '" + id + "' ");
				if (modmok == 1) {
					System.out.println("Alumno modificado correctamente");
				} else {
					System.err.println("No se pudo modificar el alumno");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case 4:
			System.out.println("Ingrese el nuevo Email");
			String modemail = in.next();

			try {
				int modmok = st.executeUpdate(
						"UPDATE `alumnos` SET  `email` = '" + modemail + "' WHERE `alumnos`.`id` = '" + id + "' ");
				if (modmok == 1) {
					System.out.println("Alumno modificado correctamente");
				} else {
					System.err.println("No se pudo modificar el alumno");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;

		default:
			break;
		}

	}

	public void EliminarAlumnos(Scanner in) {
		ConexionDB con = new ConexionDB();
		Statement st = con.conectar();
		ResultSet rs;
		System.out.println("\n        Eliminar Alumno       ");
		List<Alumno> listAlum = BuscarAlumnos(in, st);
		System.out.println("Ingrese el ID del alumno a eliminar");
		int elim = in.nextInt();

		try {
			int elimok = st.executeUpdate("DELETE FROM alumnos WHERE id=" + elim);
			if (elimok == 1) {
				System.out.println("Alumno eliminado");
			} else {
				System.err.println("No se pudo eliminar el alumno");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void CrearAlumnos(Scanner in) {
		System.out.println("\n         Alta de alumnos         ");
		System.out.println("Ingrese los datos del alumno");
		System.out.println("Nombre:");
		in.nextLine();
		String nombre = in.nextLine();
		in.nextLine();
		System.out.println("Apellido");
		String apellido = in.nextLine();
		System.out.println("DNI");
		int dni = in.nextInt();
		System.out.println("Email");
		String email = in.next();

		ConexionDB con = new ConexionDB();
		Statement st = con.conectar();

		try {
			int insok = st.executeUpdate("INSERT INTO alumnos (nombre, apellido, dni, email) VALUES ('" + nombre + "','"
					+ apellido + "'," + dni + ",'" + email + "');");
			if (insok == 1) {
				System.out.println("El alumno se agrego correctamente");
			} else {
				System.err.println("No se pudo agregar el alumno");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void MatPorAlumnos() {
		System.out.println("Materias de alumnos");
	}

	public List<Alumno> BuscarAlumnos(Scanner in, Statement st) {

		ResultSet rs;
		List<Alumno> ListAlum = new ArrayList<Alumno>();

		System.out.println("\n¿Por que campo desea buscar el alumno?");
		System.out.println("1- Nombre 2- Apellido 3- dni 4- Email");
		int op = in.nextInt();

		switch (op) {
		case 1:
			System.out.println("Ingrese el nombre a buscar");
			
			String nom = in.next();

			try {
				rs = st.executeQuery("SELECT * FROM alumnos WHERE nombre like '" + nom + "%'");
				while (rs.next()) {
					Alumno Alumn = new Alumno();
					Alumn.setId(rs.getInt("id"));
					Alumn.setNombre(rs.getString("nombre"));
					Alumn.setApellido(rs.getString("apellido"));
					Alumn.setDni(rs.getInt("dni"));
					Alumn.setCorreo(rs.getString("email"));
					ListAlum.add(Alumn);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");
			for (Alumno alumno : ListAlum) {
				System.out.printf("   %-6d%-12s%-15s%-10d%-15s%n", alumno.getId(), alumno.getNombre(),
						alumno.getApellido(), alumno.getDni(), alumno.getCorreo());
			}
			break;

		case 2:
			System.out.println("Ingrese el apellido a buscar");
			String ape = in.next();

			try {
				rs = st.executeQuery("SELECT * FROM alumnos WHERE apellido like '" + ape + "%'");
				while (rs.next()) {
					Alumno Alumn = new Alumno();
					Alumn.setId(rs.getInt("id"));
					Alumn.setNombre(rs.getString("nombre"));
					Alumn.setApellido(rs.getString("apellido"));
					Alumn.setDni(rs.getInt("dni"));
					Alumn.setCorreo(rs.getString("email"));
					ListAlum.add(Alumn);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");
			for (Alumno alumno : ListAlum) {
				System.out.printf("   %-6d%-12s%-15s%-10d%-15s%n", alumno.getId(), alumno.getNombre(),
						alumno.getApellido(), alumno.getDni(), alumno.getCorreo());
			}
			break;
		case 3:
			System.out.println("Ingrese el dni a buscar");
			String dni = in.next();

			try {
				rs = st.executeQuery("SELECT * FROM alumnos WHERE dni like '" + dni + "%'");
				while (rs.next()) {
					Alumno Alumn = new Alumno();
					Alumn.setId(rs.getInt("id"));
					Alumn.setNombre(rs.getString("nombre"));
					Alumn.setApellido(rs.getString("apellido"));
					Alumn.setDni(rs.getInt("dni"));
					Alumn.setCorreo(rs.getString("email"));
					ListAlum.add(Alumn);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");
			for (Alumno alumno : ListAlum) {
				System.out.printf("   %-6d%-12s%-15s%-10d%-15s%n", alumno.getId(), alumno.getNombre(),
						alumno.getApellido(), alumno.getDni(), alumno.getCorreo());
			}
			break;
		case 4:
			System.out.println("Ingrese el email a buscar");
			String email = in.next();

			try {
				rs = st.executeQuery("SELECT * FROM alumnos WHERE email like '" + email + "%'");
				while (rs.next()) {
					Alumno Alumn = new Alumno();
					Alumn.setId(rs.getInt("id"));
					Alumn.setNombre(rs.getString("nombre"));
					Alumn.setApellido(rs.getString("apellido"));
					Alumn.setDni(rs.getInt("dni"));
					Alumn.setCorreo(rs.getString("email"));
					ListAlum.add(Alumn);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");
			for (Alumno alumno : ListAlum) {
				System.out.printf("   %-6d%-12s%-15s%-10d%-15s%n", alumno.getId(), alumno.getNombre(),
						alumno.getApellido(), alumno.getDni(), alumno.getCorreo());
			}
			break;

		default:
			break;
		}

		return ListAlum;

	}

}
