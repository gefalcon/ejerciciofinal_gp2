package ejerciciofinal_gp2;
import java.io.*;
import java.sql.*;

public class Cliente{
	
	 private String login;
	 private String passwd;
	 private String nombre;
	 private String apellido;
	 private String direccion;
	 private String telefono;

	private boolean existe (Connection con, String query) throws SQLException{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs.next();
	}
	public void añadircliente(Connection con, Statement stmt) throws IOException, SQLException{
		BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
		do{
		System.out.println("Introducir el login del usuario ");
		login=leer.readLine();
		
		}while(existe(con, "SELECT login from clientes where login = "+login+""));
		do{
		System.out.println("Introduzca la contraseña, al menos debe de tener 6 letras o numeros.");
		passwd=leer.readLine();
		}while(passwd.length()<6);
		do{
			System.out.println("Introduzca el nombre: ");
			nombre=leer.readLine();
		}while(nombre.length()<5);
		do{
			System.out.println("Introduzca el apellido: ");
			apellido=leer.readLine();
		}while(apellido.length()<5);
		do{
			System.out.println("Introduzca la direccion: ");
			direccion=leer.readLine();
		}while(direccion.length()<10);
		do{
			System.out.println("Introduzca el telefondo: ");
			telefono=leer.readLine();
		}while(!telefono.equals(9));
		stmt.executeUpdate("INSERT INTO clientes VALUES ('"+login+"','"+passwd+"','"+nombre+"','"+apellido+"','"+direccion+"','"+telefono+"')");
		}
	public void eliminarcliente(Statement stmt, Connection con) throws IOException, SQLException {
		BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
	
		do{
		System.out.println("Introduzca el login del cliente que desea elimnar: ");
		login=leer.readLine();
		}while(existe(con, "SELECT login from clientes where login = "+login+"")==false);
		stmt.executeUpdate("Delete from clientes where login= "+login+"");
		if(existe(con, "SELECT login from clientes where login = "+login+"")){
			System.out.println("Se a eliminado correctamente el archivo");
		}
	}
        
	

}

	
	

