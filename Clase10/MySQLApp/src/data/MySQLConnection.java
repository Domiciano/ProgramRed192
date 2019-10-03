package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLConnection {

	private static MySQLConnection instance;
	
	//GLOBAL
	private Connection connection;
	private Statement statement;
	
	
	//CONSTANTES
	public static final String TABLE_ACTORES = "actores";
	public static final String ACTORES_ID = "id";
	public static final String ACTORES_NOMBRE = "nombre";
	public static final String ACTORES_NACIONALIDAD = "nacionalidad";
	
	
	private MySQLConnection() {
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
	
	public static synchronized MySQLConnection getInstance() {
		if(instance == null) {
			instance = new MySQLConnection();
		}
		return instance;
	}
	
	
	//CRUD
	public void createActor(Actor actor) {
		String sql = "INSERT INTO $TABLE($NOMBRE,$NACIONALIDAD) VALUES ('$VNOMBRE','$VNACIONALIDAD')";
		sql = sql
				.replace("$TABLE", TABLE_ACTORES)
				.replace("$NOMBRE", ACTORES_NOMBRE)
				.replace("$NACIONALIDAD", ACTORES_NACIONALIDAD)
				.replace("$VNOMBRE", actor.getNombre())
				.replace("$VNACIONALIDAD", actor.getNacionalidad());
		System.out.println(sql);
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		statement.execute("INSERT INTO actores(nombre,nacionalidad) "
				+ "VALUES('"+actor.getNombre()+"','"+actor.getNacionalidad()+"')");
		 */
	}
	
	public ArrayList<Actor> getAllActors(){
		ArrayList<Actor> output = new ArrayList<Actor>();
		
		String sql = "SELECT * FROM $TABLE";
		sql = sql.replace("$TABLE", TABLE_ACTORES);
		System.out.println(sql);
		try {
			ResultSet resultados = statement.executeQuery(sql);
			
			while(resultados.next()) {
				int id = resultados.getInt(1);
				String nombre = resultados.getString(2);
				String nacionalidad = resultados.getString(3);
				Actor actor = new Actor(id, nombre, nacionalidad);
				output.add(actor);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	
	
}
