import java.io.*;
import java.sql.*;

public class Cliente{
	transient BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
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
	public void pedirdatos(Connection con, java.sql.Statement stmt) throws IOException, SQLException{
		do{
		System.out.println("Introducir el login del usuario ");
		login=leer.readLine();
		
		}while(existe(con, "SELECT login from clientes where login = "+login+""));
		
	}
	
	
}
