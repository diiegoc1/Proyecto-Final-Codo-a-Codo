package ProyectoFinal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Profesores {

	public void MostrarMenuProf() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		int op = 0;

		List<String> menuProf = new ArrayList<String>();
		menuProf.add("\n\nMenú de Profesores");
		menuProf.add("[1] Listar Profesores");
		menuProf.add("[2] Modificar Profesores");
		menuProf.add("[3] Eliminar Profesores");
		menuProf.add("[4] Crear Profesores");
		menuProf.add("[0] Volver al menú principal");
		do {
			for (String opcion : menuProf) {
				System.out.println(opcion);
			}
			op = in.nextInt();

			switch (op) {
			case 1:
				Profesores listarp = new Profesores();
				listarp.ListadoDeProfesores(in);
				break;
			case 2:
				Profesores modificarp = new Profesores();
				modificarp.ModificacionDeProfesores(in);
				break;
			case 3:
				Profesores eliminarp = new Profesores();
				eliminarp.EliminacionDeProfesores(in);
				break;
			case 4:
				Profesores crearp = new Profesores();
				crearp.CreacionDeProfesores(in);
				break;
			case 5:
				Profesores materiasp = new Profesores();
				materiasp.materiasPorProfesores(in);
				break;
			default:
				break;
			}
			

		} while (op != 0);

	}

	public void ListadoDeProfesores(Scanner in) {
		List<Profesore> listProf = new ArrayList<Profesore>();

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
				listProf.add(profes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n                  Lista de profesores             ");
		System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");

		for (Profesore profesores : listProf) {
			System.out.printf("   %-6d%-12s%-15s%-10d%-15s%n", profesores.getId(), profesores.getNombre(),
					profesores.getApellido(), profesores.getDni(), profesores.getCorreo());
		}
		System.out.println("¿Desea buscar profesor? si/no");
		String busc = in.next();
			if (busc.equals("si")) {
				listProf= buscarprofe(in, st);
			}
	}

	public void ModificacionDeProfesores(Scanner in) {
		ConexionDB con = new ConexionDB();
		Statement st = con.conectar();

		System.out.println("            Modificar Profesor");
		List<Profesore> listProfe = buscarprofe(in, st);
		System.out.println("ingrese el ID del profesor a modificar");
		int id = in.nextInt();
		try {
			for (Profesore profe : listProfe) {
				if (profe.getId() == id) {
					System.out.println("Ingrese los datos del profesor a modificar");
					System.out.println("Nombre: " + profe.getNombre());
					String nombreprof = in.next();
					System.out.println("Apellido: " + profe.getApellido());
					String apellidoprof = in.next();
					System.out.println("DNI: " + profe.getDni());
					int dniprof = in.nextInt();
					System.out.println("Email: " + profe.getCorreo());
					String emailprof = in.next();

					int updatok = st.executeUpdate("UPDATE `profesores` SET `nombre` = '" + nombreprof
							+ "', `apellido` = '" + apellidoprof + "', `dni` = '" + dniprof + "', `email` = '"
							+ emailprof + "' WHERE `profesores`.`id` = '" + id + "' ");
					if (updatok == 1) {
						System.out.println("Profesor modificado correctamente");
					} else {
						System.err.println("No se pudo modificar el profesor");
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void EliminacionDeProfesores(Scanner in) {
		ConexionDB con = new ConexionDB();
		Statement st = con.conectar();
		ResultSet rs;
		System.out.println("\n        Eliminar Profesor       ");
		List<Profesore> listProfe = buscarprofe(in, st);
		System.out.println("Ingrese el ID del profesor a eliminar");
		int elim = in.nextInt();

		try {
			int elimok = st.executeUpdate("DELETE FROM profesores WHERE id=" + elim);
			if (elimok == 1) {
				System.out.println("Profesor eliminado");
			} else {
				System.err.println("No se pudo eliminar el profesor");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void CreacionDeProfesores(Scanner in) {
		System.out.println("\n         Alta de profesores        ");
		System.out.println("Ingrese los datos del profesor");
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
			int insok = st.executeUpdate("INSERT INTO profesores (nombre, apellido, dni, email) VALUES ('" + nombre
					+ "','" + apellido + "'," + dni + ",'" + email + "');");
			if (insok == 1) {
				System.out.println("El profesor se agrego correctamente");
			} else {
				System.err.println("No se pudo agregar el profesor");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Profesore> buscarprofe(Scanner in, Statement st) {
		ResultSet rs;
		List<Profesore> ListProfe = new ArrayList<Profesore>();

		System.out.println("\n¿Por que campo desea buscar el profesor?");
		System.out.println("1- Nombre 2- Apellido 3- dni 4- Email");
		int op = in.nextInt();

		switch (op) {
		case 1:
			System.out.println("Ingrese el nombre a buscar");
			String nom = in.next();

			try {
				rs = st.executeQuery("SELECT * FROM profesores WHERE nombre like '" + nom + "%'");
				while (rs.next()) {
					Profesore profe = new Profesore();
					profe.setId(rs.getInt("id"));
					profe.setNombre(rs.getString("nombre"));
					profe.setApellido(rs.getString("apellido"));
					profe.setDni(rs.getInt("dni"));
					profe.setCorreo(rs.getString("email"));
					ListProfe.add(profe);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");
			for (Profesore profesores : ListProfe) {
				System.out.printf("   %-6d%-12s%-15s%-10d%-15s%n", profesores.getId(), profesores.getNombre(),
						profesores.getApellido(), profesores.getDni(), profesores.getCorreo());
			}
			break;

		case 2:
			System.out.println("Ingrese el apellido a buscar");
			String ape = in.next();

			try {
				rs = st.executeQuery("SELECT * FROM profesores WHERE apellido like '" + ape + "%'");
				while (rs.next()) {
					Profesore profe = new Profesore();
					profe.setId(rs.getInt("id"));
					profe.setNombre(rs.getString("nombre"));
					profe.setApellido(rs.getString("apellido"));
					profe.setDni(rs.getInt("dni"));
					profe.setCorreo(rs.getString("email"));
					ListProfe.add(profe);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");
			for (Profesore profesores : ListProfe) {
				System.out.printf("   %-6d%-12s%-15s%-10d%-15s%n", profesores.getId(), profesores.getNombre(),
						profesores.getApellido(), profesores.getDni(), profesores.getCorreo());
			}
			
			break;
		case 3:
			System.out.println("Ingrese el dni a buscar");
			String dni = in.next();

			try {
				rs = st.executeQuery("SELECT * FROM profesores WHERE dni like '" + dni + "%'");
				while (rs.next()) {
					Profesore profe = new Profesore();
					profe.setId(rs.getInt("id"));
					profe.setNombre(rs.getString("nombre"));
					profe.setApellido(rs.getString("apellido"));
					profe.setDni(rs.getInt("dni"));
					profe.setCorreo(rs.getString("email"));
					ListProfe.add(profe);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");
			for (Profesore profesores : ListProfe) {
				System.out.printf("   %-6d%-12s%-15s%-10d%-15s%n", profesores.getId(), profesores.getNombre(),
						profesores.getApellido(), profesores.getDni(), profesores.getCorreo());
			}
			break;
		case 4:
			System.out.println("Ingrese el email a buscar");
			String email = in.next();

			try {
				rs = st.executeQuery("SELECT * FROM profesores WHERE email like '" + email + "%'");
				while (rs.next()) {
					Profesore profe = new Profesore();
					profe.setId(rs.getInt("id"));
					profe.setNombre(rs.getString("nombre"));
					profe.setApellido(rs.getString("apellido"));
					profe.setDni(rs.getInt("dni"));
					profe.setCorreo(rs.getString("email"));
					ListProfe.add(profe);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("   ID   |   NOMBRE   |   APELLIDO   |   DNI   |   EMAIL             ");
			for (Profesore profesores : ListProfe) {
				System.out.printf("   %-6d%-12s%-15s%-10d%-15s%n", profesores.getId(), profesores.getNombre(),
						profesores.getApellido(), profesores.getDni(), profesores.getCorreo());
			}

			break;

		default:
			break;
		}

		return ListProfe;

	}

	public void materiasPorProfesores(Scanner in) {
		List<Profesore> listprof = new ArrayList<Profesore>();
		ConexionDB con = new ConexionDB();
		Statement st= con.conectar();
		ResultSet rs;
		buscarprofe(in, st);
		
		
		System.out.println(" Ingrese el id del profesor para buscar las materias dictadas");
		int id = in.nextInt();
		
		try {
			rs = st.executeQuery("SELECT * FROM `materias` WHERE idprofesor='"+id+"'");
			while (rs.next()) {
				Profesore prof = new Profesore();
				prof.getApellido();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
