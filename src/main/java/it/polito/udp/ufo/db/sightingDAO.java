package it.polito.udp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class sightingDAO {
	
	String jdbcURL= "jdbc:mysql://localhost/ufo_sightings?user=root&password=Leonardo00";

	public List<String> readShapes() {
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
			conn.close();
			return formeUfo;
		} catch (SQLException e) {
			throw new RuntimeException("Database error in readShapes",e);
		}
	}
	
	public int countByShape(String shape) {
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			String sql2="SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ? ";
			PreparedStatement st2= conn.prepareStatement(sql2);  //USIAMO SEMPRE QUESTO
			st2.setString(1, shape);
			ResultSet res2= st2.executeQuery();
			res2.first();
			int count= res2.getInt("cnt");
			st2.close();
			conn.close();
			return count;
		} catch (SQLException e) {
			throw new RuntimeException("Database error in countByShape",e);
		}
	}
}
