package it.polito.udp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class testDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcURL= "jdbc:mysql://localhost/ufo_sightings?user=root&password=Leonardo00";
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
	
			String sql= "SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape <>'' "
					+ "order by shape ASC" ;
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			List<String> formeUfo= new ArrayList<>();
			while (res.next() ) {
				String forma =res.getString("shape");
				formeUfo.add(forma);
			}
			st.close();
			System.out.println(formeUfo);
			
			//Query con parametri
			String sql2="SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ? ";
			String shapeScelta= "circle";
			PreparedStatement st2= conn.prepareStatement(sql2);  //USIAMO SEMPRE QUESTO
			st2.setString(1, shapeScelta);
			ResultSet res2= st2.executeQuery();
			res2.first();
			int count= res2.getInt("cnt");
			st2.close();
			System.out.println("Ufo di forma "+shapeScelta+" sono: "+count);
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
