package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.MessageHandler.Whole;

import beans.BeanCursoJsp;
import connection.SingleConnection;
import jdk.nashorn.internal.ir.WhileNode;

public class DaoUsuario {
	
private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void inserir(BeanCursoJsp usuario) {
	  try {
		String sql = "insert into usuario (login, senha, nome, telefone, email) values (?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getLogin());
		statement.setString(2, usuario.getSenha());
		statement.setString(3, usuario.getNome());
		statement.setString(4, usuario.getTelefone());
		statement.setString(5, usuario.getEmail());
		statement.execute();
		
		//Testando o commit do git integrado ao Eclipse
		connection.commit();
		
	  }catch (Exception e) {
		  e.printStackTrace();
		try {
			connection.rollback();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	  }			
	}
	
    public List<BeanCursoJsp> listar() throws SQLException{
    	List<BeanCursoJsp> lista = new ArrayList<BeanCursoJsp>();
    	
    	String sql = "select * from usuario";
    	
    	PreparedStatement statement = connection.prepareStatement(sql);
    	ResultSet resultSet = statement.executeQuery();
    	
    	while (resultSet.next()) {
    		
    		BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
    		beanCursoJsp.setId(resultSet.getInt("id"));
    		beanCursoJsp.setLogin(resultSet.getString("login"));
    		beanCursoJsp.setSenha(resultSet.getString("senha"));    		
    		beanCursoJsp.setNome(resultSet.getString("nome"));
    		beanCursoJsp.setTelefone(resultSet.getString("telefone"));
    		beanCursoJsp.setEmail(resultSet.getString("email"));
    		
    		lista.add(beanCursoJsp);    		
    	}
    	
    	return lista;    	
    }
    
    public void delete(Integer id){
      try {
    	String sql = "delete from usuario where id = '"+id+"'";
    	PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        
        connection.commit();
      }catch (Exception e) {
		e.printStackTrace();
		try {
		  connection.rollback();	
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	  }
    }
    
    public void editar(BeanCursoJsp usuario) {
  	  try {
  		String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ?, email = ? where id = "+usuario.getId();
  		PreparedStatement statement = connection.prepareStatement(sql);
  		statement.setString(1, usuario.getLogin());
  		statement.setString(2, usuario.getSenha());
  		statement.setString(3, usuario.getNome());
  		statement.setString(4, usuario.getTelefone());
  		statement.setString(5, usuario.getEmail());
  		statement.executeUpdate();
  		
  		connection.commit();
  		
  	  }catch (Exception e) {
  		  e.printStackTrace();
  		try {
  			connection.rollback();
  		} catch (Exception e2) {
  			e2.printStackTrace();
  		}
  	  }			
  	}

	public BeanCursoJsp consultar(Integer id) throws SQLException {
		String sql = "select * from usuario where id = "+id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getInt("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));			
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			beanCursoJsp.setEmail(resultSet.getString("email"));
			
			return beanCursoJsp;
		}
		
		return null;
	}
	
	public boolean validarLogin(String login) throws SQLException {
		String sql = "select count(1) as qtd from usuario where login = '"+login+"' ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
				
			return resultSet.getInt("qtd") <= 0;
		}
		
		return false;
	}

}
