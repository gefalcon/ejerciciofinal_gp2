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
						
						break;
					case 8:
						
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
