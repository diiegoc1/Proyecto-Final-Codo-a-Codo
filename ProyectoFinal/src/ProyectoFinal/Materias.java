package ProyectoFinal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Materias {

	public void MostrarMenuMat() {

		Scanner in = new Scanner(System.in);

		int op = 0;

		List<String> menuMat = new ArrayList<String>();
		menuMat.add("\nMenú de materías");
		menuMat.add("[1] Listar Materias");
		menuMat.add("[2] Modificar Materias");
		menuMat.add("[3] Eliminar Materias");
		menuMat.add("[4] Crear Materias");
		menuMat.add("[0] Volver al menú principal");

		do {
			for (String opcion : menuMat) {
				System.out.println(opcion);
			}
		///TODO agregar try
			op = in.nextInt();

			
			switch (op) {
			case 1:
				Materias listar = new Materias();
				listar.listarMaterias(in);
				break;
			case 2:
				Materias modificar = new Materias();
				modificar.modificarMaterias(in);
				break;
			case 3:
				Materias eliminar = new Materias();
				eliminar.eliminarMaterias(in);
				break;
			case 4:
				Materias crear = new Materias();
				crear.crearMaterias(in);
				break;
			case 5:
				Materias alumnomat = new Materias();
				alumnomat.alumnosMaterias(in);
				break;
			case 6:
				Materias profemat = new Materias();
				profemat.profesoresMaterias(in);
				break;

			default:
				break;
			}

		} while (op != 0);


	
	}

	public void listarMaterias(Scanner in) {
		List<Materia> listmat = new ArrayList<Materia>();

		ConexionDB con = new ConexionDB();
		Statement st = con.conectar();
		ResultSet rs;
		try {
			rs = st.executeQuery("SELECT * FROM `materias`");
			while (rs.next()) {
				Materia materias = new Materia();
				materias.setId(rs.getInt("id"));
				materias.setNombre(rs.getString("nombre"));
				listmat.add(materias);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nListado de materias             ");
		System.out.println("   ID   |   NOMBRE           ");

		for (Materia mates : listmat) {
			System.out.printf("   %-9d%-15s%n", mates.getId(), mates.getNombre());
		}

		System.out.println("\npresione una tecla para continuar");
		String cont = in.next();
	}

	public void modificarMaterias(Scanner in) {
		List<Materia> listmat = new ArrayList<Materia>();
		ConexionDB con = new ConexionDB();
		Statement st = con.conectar();
		ResultSet rs;
		

		System.out.println("Buscar materias por nombre ");
		String bus = in.next();
		
		try {
			rs = st.executeQuery("SELECT * FROM `materias` WHERE nombre LIKE '" + bus + "%'");
			while (rs.next()) {
				Materia materias = new Materia();
				materias.setId(rs.getInt("id"));
				materias.setNombre(rs.getString("nombre"));
				listmat.add(materias);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nListado de materias             ");
		System.out.println("   ID   |   NOMBRE           ");

		for (Materia mates : listmat) {
			System.out.printf("   %-9d%-15s%n", mates.getId(), mates.getNombre());
		}

		System.out.println("\nIngrese el ID de la materia a modificar");
		String contid = in.next();
		
			System.out.println("Ingrese el nuevo Nombre");
			in.nextLine(); 
			String modid1 = in.nextLine(); 
			
			try {
				int updateok = st.executeUpdate("UPDATE `materias` SET `nombre` = '"+modid1+"' WHERE `materias`.`id` = "+contid+"");
				if (updateok == 1) {
					System.out.println("Materia modificada correctamente");
				} else {
					System.err.println("No se pudo modificar la materia");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

	}

	public void eliminarMaterias(Scanner in) {
		System.out.println("     Eliminar Materia     ");
		System.out.println("Buscar materias por nombre ");
		String op = in.next();
	
		List<Materia> listMat1 = new ArrayList<Materia>();
		ConexionDB con = new ConexionDB();
		Statement st = con.conectar();
		ResultSet rs;
		
		try {
			rs = st.executeQuery("SELECT * FROM `materias` WHERE nombre LIKE '" + op + "%'");
			while (rs.next()) {
				Materia materias = new Materia();
				materias.setId(rs.getInt("id"));
				materias.setNombre(rs.getString("nombre"));
				listMat1.add(materias);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nListado de materias             ");
		System.out.println("   ID   |   NOMBRE           ");

		for (Materia mates : listMat1) {
			System.out.printf("   %-9d%-15s%n", mates.getId(), mates.getNombre());
		}




			try {
				System.out.println("ingrese el ID de la materia a borrar");
				int num = in.nextInt();
				int deleteok = st.executeUpdate("DELETE FROM `materias` WHERE id=" + num);
				if (deleteok == 1) {
					System.out.println("Materia elimminada correctamente");
				} else {
					
				}

			} catch (Exception e) {
				System.err.println("No se puedo eliminar la materia");
				
			}

	}

	public void crearMaterias(Scanner in) {

		System.out.println("\n         crear materia         ");
		System.out.println("Ingrese los datos de la materia");
		System.out.println("Nombre:");
		in.nextLine(); 
		String crearmaterias = in.nextLine(); 
		
		
		ConexionDB con = new ConexionDB();
		Statement st = con.conectar();
		
		try {
			int insertok= st.executeUpdate("INSERT INTO materias (nombre) VALUES ('"+crearmaterias+"');");
			if (insertok==1) {
				System.out.println("Materia creada correctamente");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public void alumnosMaterias(Scanner in) {
		// TODO Auto-generated method stub

	}

	public void profesoresMaterias(Scanner in) {
		// TODO Auto-generated method stub

	}

}
