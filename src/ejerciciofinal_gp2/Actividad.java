package ejerciciofinal_gp2;

import java.io.*;
import java.sql.*;

public class Actividad {
	static BufferedReader leer = new BufferedReader (new InputStreamReader (System.in));
	private int id;
	private String nombre;
	private String nombre_pabellon;
	private String descripcion;
	private String inicio;
	private Double precio;
	private int plazas_ocupadas;
	private int plazas_totales;
	
	public void añadirActividad(Statement stm) throws IOException, SQLException {
			
		ResultSet rs = stm.executeQuery("select max(id) from actividades");
		rs.next();
		id = rs.getInt(1) + 1;
		
		System.out.println("Introduzca el nombre de la actividad");
		nombre = leer.readLine();
		
		System.out.println("Introduzca el nombre del pabellón");
		nombre_pabellon = leer.readLine();
		
		System.out.println("Introduzca la descripcion");
		descripcion = leer.readLine();
		
		System.out.println("Introduzca la fecha de inicio");
		inicio = leer.readLine();
		
		System.out.println("Introduzca el precio");
		precio = Double.parseDouble(leer.readLine());
		
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
	
}
