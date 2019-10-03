package main;

import java.util.ArrayList;

import data.Actor;
import data.MySQLConnection;

public class Main {

	public static void main(String[] args) {
		//SET GLOBAL time_zone = '-5:00';
		MySQLConnection connection = MySQLConnection.getInstance();
		
		
		//Actor adam = new Actor(0,"Hyun Bin","USA");
		//connection.createActor(adam);
		ArrayList<Actor> actores = connection.getAllActors();
		System.out.println(actores.size());
		for(int i=0 ; i<actores.size() ; i++) {
			System.out.println(actores.get(i).getId() + "->" + actores.get(i).getNombre());
		}
		
	}

}
