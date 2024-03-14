package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(
				"DELETE FROM department " +
				"WHERE Id = ?");
			
			ps.setInt(1, 8);

			int arrowsAffected = ps.executeUpdate();
			System.out.println("Done! Arrows Affected: " + arrowsAffected);
			
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}
}
