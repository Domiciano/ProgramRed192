package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLConnection {

	//GLOBAL
	private Connection connection;
	private Statement statement;
	
	
	//CONSTANTES
	public static final String TABLE_ACTORES = "actores";
	public static final String ACTORES_ID = "id";
	public static final String ACTORES_NOMBRE = "nombre";
	public static final String ACTORES_NACIONALIDAD = "nacionalidad";
	
	
	public MySQLConnection() {
		//JDBC
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blockbuster", "root","");
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
