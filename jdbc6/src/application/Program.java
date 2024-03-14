package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String args[]) {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			st = conn.createStatement();
			int arrows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2900 WHERE departmentId = 1");
			
			/*int x = 1;
			
			if(x < 2) {
				throw new SQLException("Fake error!");
			}*/
			
			int arrows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3900 WHERE departmentId = 2");
			
			conn.commit();
			System.out.println("Arrows 1: " + arrows1);
			System.out.println("Arrows 2: " + arrows2);
		}
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		}
	}
}
