package it.polito.udp.ufo.db;

import java.util.ArrayList;
import java.util.List;

public class testDB {

	public static void main(String[] args) {
		
		sightingDAO dao= new sightingDAO();
		List<String> formeUfo= dao.readShapes();
		for (String f: formeUfo) {
			int count=dao.countByShape(f);
			System.out.println("Ufo di forma "+f+" sono: "+count);
		}
	}

}
