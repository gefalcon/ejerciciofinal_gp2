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
	public void pedirdatos(Connection con) throws IOException, SQLException{
		BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
		do{
		System.out.println("Introducir el login del usuario ");
		login=leer.readLine();
		
		}while(existe(con, "SELECT login from clientes where login = "+login+""));
		do{
			System.out.println("Introducir la contraseña: ");
			passwd = leer.readLine();
		}while(passwd.length() == 0);
		do{
			System.out.println("Introducir el nombre del cliente: ");
			nombre = leer.readLine();
		}while(nombre.length() == 0);
		do{
			System.out.println("Introduzca el apellido del cliente: ");
			apellido = leer.readLine();
		}while(apellido.length() == 0);
		do{
			System.out.println("Introduzca la direccion del cliente: ");
			direccion = leer.readLine();
		}while(direccion.length() == 0);
		do{
			System.out.println("Introduzca el telefono del cliente: ");
			telefono = leer.readLine();
		}while(telefono.length() == 0);
		Statement stm = con.createStatement();
		stm.executeUpdate("insert into clientes values('"+login+"','"+passwd+"','"+nombre+"','"+apellido+"','"+direccion+"','"+telefono+"')");
		if(existe(con, "select * from clientes where login = "+login+""))
			System.out.println("Datos introducidos corrctamente.");
	}
	public void eliminarCliente(Connection con) throws IOException, SQLException{
		BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
		String cad;
		do{
			System.out.println("Introduzca el cliente que desea eliminar: ");
			cad = leer.readLine();
		}while(existe(con, "select * from cliente where login = "+cad+"") == false);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("delete from cliente where login = "+cad+"");
		if(existe(con, "select * from cliente where login = "+cad+"") == false)
			System.out.println("Cliente eliminado correctamente");
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
	public void listarClientes(boolean f){ //si se pasa false no se muestra la contraseña
		System.out.println("USUARIO: " + login);
		if(f)
			System.out.println("CONTRASEÑA: " + passwd);
		System.out.println("NOMBRE: " + nombre);
		System.out.println("APELLIDOS: " + apellido);
		System.out.println("DIRECCION: " + direccion);
		System.out.println("TELEFONO: " + telefono);
		System.out.println();
	}
}
