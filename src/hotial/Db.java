package hotial;
import java.sql.*;

public class Db 
{
	private Connection dbConn;
	private Statement stateMent;
	public Db() 
	{
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=hotail";
		String userName="sa";
		String userPwd="123456";
		try {
			Class.forName(driverName);
			dbConn=DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("Connection Successful!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public int executeUpdate(String sql) throws SQLException{
		stateMent=dbConn.createStatement();
		return stateMent.executeUpdate(sql);
	}
	public ResultSet executeQuery(String sql) throws SQLException{
		stateMent=dbConn.createStatement();
		return stateMent.executeQuery(sql);
	}
	public void closeConn() throws SQLException{
		stateMent.close();
		dbConn.close();
	}
	public static void main(String[] args) {
		new Db();
	}
	public PreparedStatement PreparedStatement(String sql)throws SQLException{
		return dbConn.prepareStatement(sql);
	}
}
