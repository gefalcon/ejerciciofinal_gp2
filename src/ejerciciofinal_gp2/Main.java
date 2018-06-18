package ejerciciofinal_gp2;

import java.io.*;
import java.sql.*;
import java.text.ParseException;

public class Main {
	private static String user;

	private static char menuPrincipal() throws IOException {
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		String n;
		do {
			System.out.println("Seleccione una opción: ");
			System.out.println("\nA.- Entrar como gestor.");
			System.out.println("B.- Entrar como cliente.");
			System.out.println("C.- Registrar un nuevo cliente.");
			System.out.println("D.- Salir de la aplicación.");
			n = leer.readLine().toUpperCase();
		} while (n.matches("[A-D]{1}") == false);
		return n.charAt(0);
	}

	private static String pedirDatos(String cad) throws IOException {
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String info;
		do {
			System.out.println(cad + ": ");
			info = lector.readLine();

			if (info.length() == 0) {
				System.out.println("Error: el campo " + cad + " es obligatorio");
			}

		} while (info.length() == 0);
		return info;
	}

	private static boolean loginCliente(Connection con) throws SQLException, IOException {

		String login, passwd;
		Statement stm = con.createStatement();

		login = pedirDatos("USUARIO");
		passwd = pedirDatos("CONTRASEÑA");

		ResultSet query = stm.executeQuery(
				"SELECT * FROM CLIENTES WHERE LOGIN LIKE '" + login + "' AND PASSWD LIKE '" + passwd + "'");

		if (query.next()) {
			user = login;
			return true;
		} else {
			System.out.println("Error de autenticación");
			user = "";
			return false;
		}
	}

	private static boolean loginGestor() throws IOException {
		Gestor gestor = new Gestor();

		String passwd = pedirDatos("CONTRASEÑA");

		if (passwd.equals(gestor.getPasswd())) {
			return true;
		} else {
			System.out.println("Error de autenticación");
			return false;
		}
	}

	private static int menuGestor() throws NumberFormatException, IOException {
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		int n;
		do {
			System.out.println("*** GESTOR ***");
			System.out.println("1.- Añadir un cliente");
			System.out.println("2.- Borrar un cliente");
			System.out.println("3.- Añadir una actividad");
			System.out.println("4.- Borrar una actividad");
			System.out.println("5.- Añadir un pabellon");
			System.out.println("6.- Borrar un pabellon");
			System.out.println("7.- Listar todos los clientes");
			System.out.println("8.- Listar todas las actividades");
			System.out.println("9.- Listar todos los pabellones");
			System.out.println("10.- Listar las actividades que tienen plazas libres");
			System.out.println("11.- Volver");
			n = Integer.parseInt(leer.readLine());
		} while (n < 1 || n > 11);
		return n;
	}

	private static int menuCliente() throws NumberFormatException, IOException {
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		int n;
		do {
			System.out.println("*** CLIENTE ***");
			System.out.println("1.- Listar todas las actividades");
			System.out.println("2.- Listar todos los pabellones");
			System.out.println("3.- Listar las actividades que tienen plazas libres");
			System.out.println(
					"4.- Listar las actividades con plazas libres que cuesten menos de una determinada cantidad");
			System.out
					.println("5.- Listar las actividades con plazas libres que se realizan en un determinado pabellon");
			System.out.println("6.- Mostrar información de una actividad especifica");
			System.out.println("7.- Listar las actividades en las que estoy suscrito");
			System.out.println("8.- Suscribirse a una actividad");
			System.out.println("9.- Desuscribirse de una actividad");
			System.out.println("10.- Volver");
			n = Integer.parseInt(leer.readLine());
		} while (n < 1 || n > 10);
		return n;
	}

	private static void listarTodosClientes(Connection con, boolean f) throws SQLException {
		if (existe(con, "select * from clientes")) {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select count(LOGIN) as count from clientes");
			rs.first();
			Cliente[] cli = new Cliente[rs.getInt("count")];
			rs = stm.executeQuery("select * from clientes");
			int i = 0;
			while (rs.next()) {
				cli[i] = new Cliente(rs.getString("LOGIN"), rs.getString("PASSWD"), rs.getString("NOMBRE"),
						rs.getString("APELLIDO"), rs.getString("DIRECCION"), rs.getString("TELEFONO"));
				i++;
			}
			for (i = 0; i < cli.length; i++)
				cli[i].listarClientes(f);
		} else
			System.out.println("No hay datos de clientes.");
	}

	private static void listarTodosActividades(Connection con, boolean f) throws SQLException {
		if (existe(con, "select * from actividades")) {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select count(ID) as count from actividades");
			rs.first();
			Actividad[] act = new Actividad[rs.getInt("count")];
			rs = stm.executeQuery("select * from actividades");
			int i = 0;
			while (rs.next()) {
				act[i] = new Actividad(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("NOMBRE_PABELLON"),
						rs.getString("DESCRIPCION"), rs.getString("INICIO"), rs.getDouble("PRECIO"),
						rs.getInt("PLAZAS_TOTALES"), rs.getInt("PLAZAS_OCUPADAS"));
				i++;
			}
			for (i = 0; i < act.length; i++)
				act[i].listarActividades(f);
		} else
			System.out.println("No hay datos de las actividades.");
	}

	private static boolean existe(Connection con, String query) throws SQLException {
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		return rs.next();
	}

	private static void listarlugarlibre(Connection con) throws SQLException, IOException {
		if (existe(con, "select * from Actividades")) {
			Statement stm = con.createStatement();
			String pab = pedirDatos("Introduzca un pabellón");
			ResultSet rs = stm.executeQuery(
					"select NOMBRE from actividades where plazas_totales > plazas_ocupadas and NOMBRE_PABELLON = '"
							+ pab + "'");
			boolean f = false;
			while (rs.next()) {
				f = true;
				System.out.println(rs.getString("NOMBRE"));
			}
			if (f == false)
				System.out.println("No hay actividades con plazas libres en el pabellón " + pab);
		} else
			System.out.println("No hay datos de actividades");
	}

	private static void listarTodosPabellones(Connection con) throws SQLException {
		if (existe(con, "select * from pabellones")) {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select count(PABELLON) as count from pabellones");
			rs.first();
			Pabellones[] pab = new Pabellones[rs.getInt("count")];
			rs = stm.executeQuery("select * from pabellones");
			int i = 0;
			while (rs.next()) {
				pab[i] = new Pabellones(rs.getString("PABELLON"), rs.getString("LOCALIDAD"));
				i++;
			}
			for (i = 0; i < pab.length; i++)
				pab[i].listarPabellones();
		} else
			System.out.println("No hay datos de pabellones.");
	}

	private static void listarActividades(Connection con) throws SQLException, IOException {
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		if (existe(con, "select * from actividades")) {
			double precio;
			Statement stm = con.createStatement();
			do {
				System.out.println("Introduzca la cantidad máxima a pagar: ");
				precio = Double.parseDouble(leer.readLine());
			} while (precio < 0);
			ResultSet rs = stm.executeQuery("select NOMBRE from actividades where precio <= " + precio
					+ " and plazas_totales > plazas_ocupadas");
			boolean f = false;
			while (rs.next()) {
				f = true;
				System.out.println((rs.getString("NOMBRE")));
			}
			if (f == false)
				System.out.println("No hay datos de actividades por debajo del precio introducido.");
		} else
			System.out.println("No hay datos de actividades.");
	}

	public static void listarActividadesLibres(Connection con, boolean f) throws SQLException {
		Statement stm = con.createStatement();
		boolean enc = false;
		ResultSet lista = stm.executeQuery("SELECT * FROM ACTIVIDADES WHERE PLAZAS_TOTALES-PLAZAS_OCUPADAS>0");
		while (lista.next()) {
			enc = true;
			if (f)
				System.out.println("Id:" + lista.getInt("ID"));
			System.out.println("Nombre: " + lista.getString("NOMBRE"));
			System.out.println("Pabellón: " + lista.getString("NOMBRE_PABELLON"));
			System.out.println("Descripción: " + lista.getString("DESCRIPCION"));
			System.out.println("Inicio: " + lista.getString("INICIO"));
			System.out.println("Precio: " + lista.getDouble("PRECIO"));
			System.out.println("Plazas totales: " + lista.getInt("PLAZAS_TOTALES"));
			System.out.println("Plazas ocupadas: " + lista.getInt("PLAZAS_OCUPADAS"));
			System.out.println("-----------------------------------");
		}
		if (enc == false)
			System.out.println("Lo siento, no hay actividades libres");
	}
	private static void mostrarInfoActConr(Statement stmt) throws IOException, SQLException {
		String act;
		int tuplas = 0;
		act = pedirDatos("¿Que actividad desea que se le muestre?");
		ResultSet rs = stmt.executeQuery("select * from actividades where NOMBRE like '" + act + "'");
		while (rs.next()) {
			System.out.println("NOMBRE: " + rs.getString("NOMBRE") + "\nPABELLON: "
					+ rs.getString("NOMBRE_PABELLON") + "\nDESCRIPCION: " + rs.getString("DESCRIPCION") + "\nINICIO: "
					+ rs.getString("INICIO") + "\nPRECIO: " + rs.getFloat("PRECIO") + "\nPLAZAS TOTALES: "
					+ rs.getInt("PLAZAS_TOTALES") + "\nPLAZAS OCUPADAS: " + rs.getInt("PLAZAS_OCUPADAS"));
			System.out.println();
			tuplas++;
		}
		if (tuplas == 0) {
			System.out.println("No se encuentra esa actividad en la base de datos");
		}
	}

	private static void misActividades(Statement stmt, String log) throws SQLException {
		int tuplas = 0;
		ResultSet rs = stmt.executeQuery(
				"select * from actividades where ID in (select ID_ACTIVIDAD from inscripciones where LOGIN_CLIENTE like '"
						+ log + "')");
		while (rs.next()) {
			System.out.println("NOMBRE: " + rs.getString("NOMBRE") + "\nPABELLON: "
					+ rs.getString("NOMBRE_PABELLON") + "\nDESCRIPCION: " + rs.getString("DESCRIPCION") + "\nINICIO: "
					+ rs.getString("INICIO") + "\nPRECIO: " + rs.getFloat("PRECIO") + "\nPLAZAS TOTALES: "
					+ rs.getInt("PLAZAS_TOTALES") + "\nPLAZAS OCUPADAS: " + rs.getInt("PLAZAS_OCUPADAS"));
			System.out.println();
			tuplas++;
		}
		if (tuplas == 0) {
			System.out.println("No esta inscrito aun en ninguna actividad");
		}
	}

	private static void suscribirseActividad(Connection con, String user)
			throws SQLException, NumberFormatException, IOException {
		if (existe(con, "select * from actividades where PLAZAS_TOTALES > PLAZAS_OCUPADAS")) {
			BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
			Statement stm = con.createStatement();
			ResultSet rs = stm
					.executeQuery("select count(ID) as count from actividades where PLAZAS_TOTALES > PLAZAS_OCUPADAS");
			rs.first();
			int n = rs.getInt("count");
			rs = stm.executeQuery("select * from actividades where PLAZAS_TOTALES > PLAZAS_OCUPADAS");
			Actividad[] act = new Actividad[n];
			int i = 0;
			while (rs.next()) {
				act[i] = new Actividad(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("NOMBRE_PABELLON"),
						rs.getString("DESCRIPCION"), rs.getString("INICIO"), rs.getDouble("PRECIO"),
						rs.getInt("PLAZAS_TOTALES"), rs.getInt("PLAZAS_OCUPADAS"));
				i++;
			}

			for (i = 0; i < act.length; i++)
				System.out.println((i + 1) + ".- " + act[i].getNombre() + " ("
						+ (act[i].getPlazas_totales() - act[i].getPlazas_ocupadas()) + " plazas libres)");
			int x;
			do {
				System.out.println("Seleccione una actividad: ");
				x = Integer.parseInt(leer.readLine());
			} while (x < 1 || x > act.length);
			stm.executeUpdate("insert into inscripciones values('" + user + "'," + act[(x - 1)].getId() + ")");
			stm.executeUpdate("update actividades set PLAZAS_OCUPADAS = (PLAZAS_OCUPADAS + 1) where ID = "
					+ act[(x - 1)].getId() + "");
			if (existe(con, "select * from inscripciones where LOGIN_CLIENTE = '" + user + "' and ID_ACTIVIDAD = "
					+ act[(x - 1)].getId() + ""))
				System.out.println("Datos introducidos correctamente");
			else
				System.out.println("Los datos no han sido introducidos correctamente");
		} else
			System.out.println("No hay actividades disponibles.");
	}

	private static void dessuscribirseActividad(Connection con, String user)
			throws SQLException, NumberFormatException, IOException {
		if (existe(con, "select * from inscripciones where LOGIN_CLIENTE = '" + user + "'")) {
			BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(
					"select count(ID) as count from actividades where ID IN(select ID_ACTIVIDAD from inscripciones where LOGIN_CLIENTE = '"
							+ user + "')");
			rs.first();
			Actividad[] act = new Actividad[rs.getInt("count")];
			rs = stm.executeQuery("select ID_ACTIVIDAD from inscripciones where LOGIN_CLIENTE = '" + user + "'");
			int i = 0;
			while (rs.next()) {
				Statement stm2 = con.createStatement();
				ResultSet rs2 = stm2
						.executeQuery("select * from actividades where ID = " + rs.getInt("ID_ACTIVIDAD") + "");
				while (rs2.next()) {
					act[i] = new Actividad(rs2.getInt("ID"), rs2.getString("NOMBRE"), rs2.getString("NOMBRE_PABELLON"),
							rs2.getString("DESCRIPCION"), rs2.getString("INICIO"), rs2.getDouble("PRECIO"),
							rs2.getInt("PLAZAS_TOTALES"), rs2.getInt("PLAZAS_OCUPADAS"));
					i++;
				}
			}
			for (i = 0; i < act.length; i++)
				System.out.println((i + 1) + ".- " + act[i].getNombre());
			int x;
			do {
				System.out.println("Seleccione una actividad: ");
				x = Integer.parseInt(leer.readLine());
			} while (x < 1 || x > act.length);
			stm.executeUpdate("delete from inscripciones where ID_ACTIVIDAD = " + act[(x - 1)].getId() + "");
			stm.executeUpdate("update actividades set PLAZAS_OCUPADAS = (PLAZAS_OCUPADAS - 1) where ID = "
					+ act[(x - 1)].getId() + "");
			if (existe(con, "select * from inscripciones where ID_ACTIVIDAD = " + act[(x - 1)].getId() + "") == false)
				System.out.println("Se ha desuscripto correctamente");
			else
				System.out.println("NO se ha desuscripto");
		} else
			System.out.println("No estás suscrito a ninguna actividad.");
	}

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, ParseException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/ejerciciofinal";
		Connection con = DriverManager.getConnection(url, "root", "123456");
		char opc1;
		int opc2;
		Cliente cli;
		Actividad act;
		Pabellones pab;
		do {
			opc1 = menuPrincipal();
			switch (opc1) {
			case 'A':
				if (loginGestor()) {
					do {
						opc2 = menuGestor();
						switch (opc2) {
						case 1:
							cli = new Cliente();
							cli.pedirdatos(con);
							break;
						case 2:
							cli = new Cliente();
							cli.eliminarCliente(con);
							break;
						case 3:
							act = new Actividad();
							act.añadirActividad(con.createStatement());
							break;
						case 4:
							act = new Actividad();
							act.eliminarActividad(con.createStatement());
							break;
						case 5:
							pab = new Pabellones();
							pab.añadirPab(con.createStatement());
							break;
						case 6:
							pab = new Pabellones();
							pab.borrarPab(con.createStatement());
							break;
						case 7:
							listarTodosClientes(con, true);
							break;
						case 8:
							listarTodosActividades(con, true);
							break;
						case 9:
							listarTodosPabellones(con);
							break;
						case 10:
							listarActividadesLibres(con, true);
							break;
						case 11:
							System.out.println("Volviendo...");
							break;
						}
					} while (opc2 != 11);
				}
				break;
			case 'B':
				if (loginCliente(con)) {
					do {
						opc2 = menuCliente();
						switch (opc2) {
						case 1:
							listarTodosActividades(con, false);
							break;
						case 2:
							listarTodosPabellones(con);
							break;
						case 3:
							listarActividadesLibres(con, false);
							break;
						case 4:
							listarActividades(con);
							break;
						case 5:
							listarlugarlibre(con);
							break;
						case 6:
							mostrarInfoActConr(con.createStatement());
							break;
						case 7:
							misActividades(con.createStatement(), user);
							break;
						case 8:
							suscribirseActividad(con, user);
							break;
						case 9:
							dessuscribirseActividad(con, user);
							break;
						case 10:
							System.out.println("Volviendo...");
							user = "";
							break;
						}
					} while (opc2 != 10);
				}
				break;
			case 'C':
				Cliente newuser = new Cliente();
				newuser.pedirdatos(con);
				System.out.println("Bienvenid@!");
				break;
			case 'D':
				System.out.println("Saliendo...");
				break;
			}
		} while (opc1 != 'D');
		con.close();
	}
}