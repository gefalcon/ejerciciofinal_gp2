package ejerciciofinal_gp2;

import java.io.*;
import java.sql.*;

public class Actividad {
	private int id;
	private String nombre;
	private String nombre_pabellon;
	private String descripcion;
	private String inicio;
	private Double precio;
	private int plazas_totales;
	private int plazas_ocupadas;
	
	
	
	Actividad(int id, String nombre, String nombre_pabellon, String descripcion, String inicio, Double precio,int plazas_totales, int plazas_ocupadas) {
		this.id = id;
		this.nombre = nombre;
		this.nombre_pabellon = nombre_pabellon;
		this.descripcion = descripcion;
		this.inicio = inicio;
		this.precio = precio;
		this.plazas_ocupadas = plazas_ocupadas;
		this.plazas_totales = plazas_totales;
	}
	Actividad() {
		this.id = 0;
		this.nombre = "";
		this.nombre_pabellon = "";
		this.descripcion = "";
		this.inicio = "";
		this.precio = (double) 0;
		this.plazas_ocupadas = 0;
		this.plazas_totales = 0;
	}
	public void añadirActividad(Statement stm) throws IOException, SQLException {
		BufferedReader leer = new BufferedReader (new InputStreamReader (System.in));
		ResultSet rs = stm.executeQuery("select max(id) from actividades");
		rs.next();
		id = rs.getInt(1) + 1;
		do{
			System.out.println("Introduzca el nombre de la actividad");
			nombre = leer.readLine();
		}while(nombre.length() == 0);
		do{
			System.out.println("Introduzca el nombre del pabellón");
			nombre_pabellon = leer.readLine();
		}while(nombre_pabellon.length() == 0);
		do{
			System.out.println("Introduzca la descripcion");
			descripcion = leer.readLine();
		}while(descripcion.length() == 0);
		do{
			System.out.println("Introduzca la fecha de inicio");
			inicio = leer.readLine();
		}while(inicio.length() == 0);
		do{
			System.out.println("Introduzca el precio");
			precio = Double.parseDouble(leer.readLine());
		}while(precio < 0);
		do{
			System.out.println("Introduzca el numero de plazas totales");
			plazas_totales = Integer.parseInt(leer.readLine());
		} while (plazas_totales < 0);	
		do{
			System.out.println("Introduzca el numero de plazas ocupadas");
			plazas_ocupadas = Integer.parseInt(leer.readLine());
		} while (plazas_totales < plazas_ocupadas);
		
		stm.executeUpdate("INSERT INTO actividades VALUES ("+id +", "+nombre +", '"+nombre_pabellon +"', '"+descripcion +"',"+inicio +","
				+ " "+precio +"',"+plazas_totales +"', '"+plazas_ocupadas +")");
		
	}
	
	public void eliminarActividad(Statement stm) throws SQLException, NumberFormatException, IOException {
		BufferedReader leer = new BufferedReader (new InputStreamReader (System.in));
		boolean validar = false;
		do{
			System.out.println("Introduzca el nombre del id de la actividad a eliminar");
			int valor = Integer.parseInt(leer.readLine());
			ResultSet rs = stm.executeQuery("select id from actividades where id = "+valor +"");
			if (!rs.next()) {
				stm.executeUpdate("delete from actividades where id = "+valor +"");
				validar = true;
			}
		} while (validar == false);
		
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getNombre_pabellon() {
		return nombre_pabellon;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getInicio() {
		return inicio;
	}
	public Double getPrecio() {
		return precio;
	}
	public int getPlazas_ocupadas() {
		return plazas_ocupadas;
	}
	public int getPlazas_totales() {
		return plazas_totales;
	}
	public void listarActividades(boolean f){ //si se pasa false no se mostrá el id 
		if(f)
			System.out.println("ID: " + id);
		System.out.println("NOMBRE DE ACTIVIDAD: "+ nombre);
		System.out.println("NOMBRE PABELLON: " + nombre_pabellon);
		System.out.println("DESCRIPCION: " + descripcion);
		System.out.println("FECHA INICIO: " + inicio);
		System.out.println("PRECIO: " + precio);
		System.out.println("PLAZAS TOTALES: " + plazas_totales);
		System.out.println("PLAZAS OCUPADAS: " + plazas_ocupadas);
		System.out.println();
	}
}
