package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
  * Resposns�vel por fazer a conex�o com o banco de dados
  * @author Rodrigo
  *
  */
public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	 
	 static {
		 connectar();
	 }
	 
	 public SingleConnection() {
		 connectar();
	 }

	 private static void connectar() {
			try {
				
				if(connection == null) {
					Class.forName("org.postgresql.Driver");
					connection = DriverManager.getConnection(url, user, password);
					connection.setAutoCommit(false);
					
					System.out.println("Conectou com sucesso!");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Erro ao acessar o banco de dados");
			}
		}
		
		public static Connection getConnection() {
			return connection;
		}
}
