package ejerciciofinal_gp2;

import java.io.*;
import java.sql.*;
import java.text.ParseException;

public class Main {
	private static char menuPrincipal() throws IOException{
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		String n;
		do{
			System.out.println("Seleccione una opción: ");
			System.out.println("\nA.- Entrar como gestor.");
			System.out.println("B.- Entrar como cliente.");
			System.out.println("C.- Registrar un nuevo cliente.");
			System.out.println("D.- Salir de la aplicación.");
			n = leer.readLine().toUpperCase();
		}while(n.matches("[A-D]{1}") == false);
		return n.charAt(0);
	}
	private static int menuGestor() throws NumberFormatException, IOException{
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		int n;
		do{
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
		}while(n < 1 || n > 11);
		return n;
	}
	private static int menuCliente() throws NumberFormatException, IOException{
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		int n;
		do{
			System.out.println("*** CLIENTE ***");
			System.out.println("1.- Listar todas las actividades");
			System.out.println("2.- Listar todos los pabellones");
			System.out.println("3.- Listar las actividades que tienen plazas libres");
			System.out.println("4.- Listar las actividades con plazas libres que cuesten menos de una determinada cantidad");
			System.out.println("5.- Listar las actividades con plazas libres que se realizan en un determinado pabellon");
			System.out.println("6.- Mostrar información de una actividad especifica");
			System.out.println("7.- Listar las actividades en las que estoy suscrito");
			System.out.println("8.- Suscribirse a una actividad");
			System.out.println("9.- Desuscribirse de una actividad");
			System.out.println("10.- Volver");
			n = Integer.parseInt(leer.readLine());
		}while(n < 1 || n > 10);
		return n;
	}
	private static void listarTodosClientes(Connection con, boolean f) throws SQLException{
		if(existe(con, "select * from clientes")){
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select count(LOGIN) as count from clientes");
			rs.first();
			Cliente[] cli = new Cliente[rs.getInt("count")];
			rs = stm.executeQuery("select * from clientes");
			int i = 0;
			while(rs.next()){
				cli[i] = new Cliente(rs.getString("LOGIN"), rs.getString("PASSWD"), rs.getString("PASSWD"), rs.getString("APELLIDO"), rs.getString("APELLIDO"), rs.getString("APELLIDO"));
				i++;
			}
			for(i = 0; i < cli.length; i++)
				cli[i].listarClientes(f);
		}
		else System.out.println("No hay datos de clientes.");
	}
	private static void listarTodosActividades(Connection con, boolean f) throws SQLException{
		if(existe(con, "select * from actividades")){
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select count(ID) as count from actividades");
			rs.first();
			Actividad[] act = new Actividad[rs.getInt("count")];
			rs = stm.executeQuery("select * from actividades");
			int i = 0;
			while(rs.next()){
				act[i] = new Actividad(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("NOMBRE_PABELLON"), rs.getString("DESCRIPCION"), rs.getString("INICIO"), rs.getDouble("PRECIO"), rs.getInt("PLAZAS_TOTALES"), rs.getInt("PLAZAS_OCUPADAS"));
				i++;
			}
			for(i = 0; i < act.length; i++)
				act[i].listarActividades(f);
		}
		else System.out.println("No hay datos de las actividades.");
	}
	private static boolean existe(Connection con, String query) throws SQLException{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		return rs.next();
	}
	private static void listarTodosPabellones(Connection con) throws SQLException{
		if(existe(con, "select * from pabellones")){
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select count(PABELLON) as count from pabellones");
			rs.first();
			Pabellones[] pab = new Pabellones[rs.getInt("count")];
			rs = stm.executeQuery("select * from pabellones");
			int i = 0;
			while(rs.next()){
				pab[i] = new Pabellones(rs.getString("PABELLON"), rs.getString("LOCALIDAD"));
				i++;
			}
			for(i = 0; i < pab.length; i++)
				pab[i].listarPabellones();
		}
		else System.out.println("No hay datos de pabellones.");
	}
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, ParseException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/ejerciciofinal";
		Connection con = DriverManager.getConnection(url, "root","123456");
		char opc1;
		int opc2;
		Cliente cli;
		Actividad act;
		Pabellones pab;
		do{
			opc1 = menuPrincipal();
			switch(opc1){
			case 'A':
				do{
					opc2 = menuGestor();
					switch(opc2){
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
						
						break;
					case 11:
						System.out.println("Volviendo...");
						break;
					}
				}while(opc2 != 11);
				break;
			case 'B':
				do{
					opc2 = menuCliente();
					switch(opc2){
					case 1:
						listarTodosActividades(con, false);
						break;
					case 2:
						listarTodosPabellones(con);
						break;
					case 3:
						
						break;
					case 4:
						
						break;
					case 5:
						
						break;
					case 6:
						
						break;
					case 7:
						
						break;
					case 8:
						
						break;
					case 9:
						
						break;
					case 10:
						System.out.println("Volviendo...");
						break;
					}
				}while(opc2 != 10);
				break;
			case 'C':
				
				break;
			case 'D':
				System.out.println("Saliendo...");
				break;
			}
		}while(opc1 != 'D');
		con.close();
	}
}