package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Actor;

public class MySQLConnection {

	// GLOBAL
	private Connection connection;
	private Statement statement;

	// CONSTANTES
	public static final String TABLE_ACTORES = "actores";
	public static final String ACTORES_ID = "id";
	public static final String ACTORES_NOMBRE = "nombre";
	public static final String ACTORES_NACIONALIDAD = "nacionalidad";

	public MySQLConnection() {
		// JDBC
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blockbuster", "root", "");
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Actor getActorByID(int id) {
		try {
			String sql = "SELECT * FROM $TABLE WHERE $ID=$VID";
			sql = sql.replace("$TABLE", TABLE_ACTORES).replace("$ID", ACTORES_ID).replace("$VID", "" + id);
			ResultSet resultados = statement.executeQuery(sql);

			while (resultados.next()) {
				int actorid = resultados.getInt(1);
				String actornombre = resultados.getString(2);
				String actornacionalidad = resultados.getString(3);

				Actor actor = new Actor(actorid, actornombre, actornacionalidad);
				return actor;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Actor> getAllActors() {
		ArrayList<Actor> actores = new ArrayList<Actor>();
		try {
			String sql = "SELECT * FROM " + TABLE_ACTORES;

			ResultSet resultados = statement.executeQuery(sql);

			while (resultados.next()) {
				int actorid = resultados.getInt(1);
				String actornombre = resultados.getString(2);
				String actornacionalidad = resultados.getString(3);

				Actor actor = new Actor(actorid, actornombre, actornacionalidad);
				actores.add(actor);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actores;
	}

	public void insertActor(Actor actor) {
		try {
			String sql = "INSERT INTO $TABLE($NOMBRE, $NAC) VALUES ('$VNOMBRE','$VNAC')";
			sql = sql.replace("$TABLE", TABLE_ACTORES).replace("$NOMBRE", ACTORES_NOMBRE)
					.replace("$NAC", ACTORES_NACIONALIDAD).replace("$VNOMBRE", actor.getNombre())
					.replace("$VNAC", actor.getNacionalidad());

			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteActor(String id) {
		try {
			String sql = "DELETE FROM $TABLE WHERE $ID=$VID";

			sql = sql.replace("$TABLE", TABLE_ACTORES)
					.replace("$ID", ACTORES_ID)
					.replace("$VID", id);

			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
