package ejerciciofinal_gp2;

import java.io.*;
import java.sql.*;

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
			System.out.println("1.-");
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
			System.out.println("1.-");
			System.out.println("10.- Volver");
			n = Integer.parseInt(leer.readLine());
		}while(n < 1 || n > 10);
		return n;
	}
	private static void listarTodosClientes(Connection con, boolean f) throws SQLException{
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
	private static void listarTodosActividades(Connection con, boolean f) throws SQLException{
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
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/ejerciciofinal";
		Connection con = DriverManager.getConnection(url, "root","123456");
		char opc1;
		int opc2;
		do{
			opc1 = menuPrincipal();
			switch(opc1){
			case 'A':
				do{
					opc2 = menuGestor();
					switch(opc2){
					case 1:
						
						break;
					case 2:
						
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
						listarTodosClientes(con, true);
						break;
					case 8:
						listarTodosActividades(con, true);
						break;
					case 9:
						
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
	}
}