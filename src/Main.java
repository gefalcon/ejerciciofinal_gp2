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
	
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/ejerciciofinal";
		Connection con = DriverManager.getConnection(url, "root","123456");
		char opc1;
		do{
			opc1 = menuPrincipal();
			switch(opc1){
			case 'A':
				
				break;
			case 'B':
				
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
