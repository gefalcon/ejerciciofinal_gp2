package ejerciciofinal_gp2;

import java.io.IOException;
import java.sql.*;

public class Main {
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
		 Pabellones p=new Pabellones();
			Class.forName("com.mysql.jdbc.Driver"); 
			String url= "jdbc:mysql://localhost/ejerciciofinal";
			Connection con = DriverManager.getConnection(url, "root", "1234"); 
			Statement stmt = con.createStatement(); 
			p.añadirPab(stmt);
			p.borrarPab(stmt);
			con.close();
	}	
}