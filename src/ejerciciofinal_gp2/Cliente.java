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
	
	Cliente(String login, String passwd, String nombre, String apellido, String direccion, String telefono) {
		this.login = login;
		this.passwd = passwd;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	Cliente() {
		this.login = "";
		this.passwd = "";
		this.nombre = "";
		this.apellido = "";
		this.direccion = "";
		this.telefono = "";
	}
	private boolean existe (Connection con, String query) throws SQLException{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs.next();
	}
	public void pedirdatos(Connection con, java.sql.Statement stmt) throws IOException, SQLException{
		BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
		do{
		System.out.println("Introducir el login del usuario ");
		login=leer.readLine();
		
		}while(existe(con, "SELECT login from clientes where login = "+login+""));
	}
	public String getLogin() {
		return login;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getTelefono() {
		return telefono;
	}	
}
