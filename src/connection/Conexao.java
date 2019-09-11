package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private static Connection conexao = null;
	public static Connection getConnection() {
		try {
	        Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/users");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
}
