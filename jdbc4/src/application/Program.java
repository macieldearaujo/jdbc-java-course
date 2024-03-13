package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(
					"UPDATE seller " +
					"SET BaseSalary = BaseSalary + ? " +
					"WHERE (DepartmentId = ?)"
					);
			
			ps.setDouble(1, 200.0);
			ps.setInt(2, 2);
			
			int arrowsAffected = ps.executeUpdate();
			
			System.out.println("Done! Arrows Affected: " + arrowsAffected);
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}

}
