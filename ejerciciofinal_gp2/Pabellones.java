package ejerciciofinal_gp2;

import java.io.*;
import java.sql.*;

public class Pabellones {

	private String nompab,locpab;
	
	Pabellones(String nompab, String locpab) {
		this.nompab = nompab;
		this.locpab = locpab;
	}
	Pabellones() {
		this.nompab = "";
		this.locpab = "";
	}
	
	public String pedirNom() throws IOException{
		BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
		String pab;
		do{
			System.out.println("Introduzca el nombre del pabellon: ");
			pab=leer.readLine();
		}while(pab.length()==0);
		return pab;
	}
	
	public String pedirLoc() throws IOException{
		BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
		String loc;
		do{
			System.out.println("Introduzca la localidad del pabellon: ");
			loc=leer.readLine();
		}while(loc.length()==0);
		return loc;
	}
	
	public void siYaExiste(Statement stmt) throws NumberFormatException, IOException, SQLException{
		BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
		int opc;
		do{
			System.out.println("�Qu� desea hacer? \n 1.Volver al menu inicial \2.Introducir otro pabellon ");
			opc=Integer.parseInt(leer.readLine());
			switch (opc) {
			case 1:
				
				break;
	
			case 2:
				a�adirPab(stmt);
				break;
				
			default:
				System.out.println("opcion no disponible en este menu");
				break;
			}
		}while(opc<1||opc>2);
	}
	
	public void a�adirPab(Statement stmt) throws IOException, SQLException{
		int tuplas=0;
			nompab=pedirNom();
			ResultSet rs=stmt.executeQuery("SELECT * FROM pabellones WHERE PABELLON like '"+nompab+"'");
			while(rs.next()){
				tuplas++;
			}
			if(tuplas!=0){
				System.out.println("Le informamos de que el pabellon "+nompab+" ya existe en nuestra base de datos.");
			}
		locpab=pedirLoc();
		stmt.executeUpdate("INSERT INTO pabellones VALUES ('"+nompab+"','"+locpab+"')");
		siYaExiste(stmt);
	}

	
	public void siNoExiste(Statement stmt) throws NumberFormatException, IOException, SQLException{
		BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
		int opc;
		do{
			System.out.println("�Qu� desea hacer? \n 1.Volver al menu inicial \2.Borrar otro pabellon ");
			opc=Integer.parseInt(leer.readLine());
			switch (opc) {
			case 1:
				
				break;
	
			case 2:
				borrarPab(stmt);
				break;
				
			default:
				System.out.println("opcion no disponible en este menu");
				break;
			}
		}while(opc<1||opc>2);
	}
	public void borrarPab(Statement stmt) throws SQLException, IOException{
		int tuplas=0;
		nompab=pedirNom();
		ResultSet rs=stmt.executeQuery("SELECT * FROM pabellones WHERE PABELLON like '"+nompab+"'");
		while(rs.next()){
			tuplas++;
		}
		if(tuplas!=0){
			stmt.executeUpdate("DELETE FROM pabellones WHERE PABELLON like '"+nompab+"'");
		}
		else{
			System.out.println("Le informamos de que el pabellon "+nompab+" no se encuentra en nuestra base de datos.");
		}
		siNoExiste(stmt);
	}
	public String getNompab() {
		return nompab;
	}
	public String getLocpab() {
		return locpab;
	}
	public void listarPabellones(){
		System.out.println("NOMBRE PABELLON: " + nompab);
		System.out.println("LOCALIDAD: " + locpab);
		System.out.println();
	}
}
