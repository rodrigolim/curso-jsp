package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefone {
	
private Connection connection;
	
	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}
	
	public void inserir(BeanTelefone telefone) {
	  try {
		String sql = "insert into telefones (numero, tipo, usuario) values (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, telefone.getNumero());
		statement.setString(2, telefone.getTipo());
		statement.setInt(3, telefone.getUsuario());
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
	
    public List<BeanTelefone> listar() throws SQLException{
    	List<BeanTelefone> lista = new ArrayList<BeanTelefone>();
    	
    	String sql = "select * from telefones";
    	
    	PreparedStatement statement = connection.prepareStatement(sql);
    	ResultSet resultSet = statement.executeQuery();
    	
    	while (resultSet.next()) {
    		
    		BeanTelefone bean = new BeanTelefone();
    		bean.setId(resultSet.getInt("id"));
    		bean.setNumero(resultSet.getString("numero"));
    		bean.setTipo(resultSet.getString("tipo"));    		
    		bean.setUsuario(resultSet.getInt("usuario"));
    		
    		lista.add(bean);    		
    	}
    	
    	return lista;    	
    }
    
    public void delete(Integer id){
      try {
    	String sql = "delete from telefones where id = '"+id+"'";
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
    
    public void editar(BeanTelefone telefone) {
  	  try {
  		String sql = "update telefones set numero = ?, tipo = ?, usuario = ?  where id = "+telefone.getId();
  		PreparedStatement statement = connection.prepareStatement(sql);
  		statement.setString(1, telefone.getNumero());
  		statement.setString(2, telefone.getTipo());
  		statement.setInt(3, telefone.getUsuario());
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

	public BeanTelefone consultar(Integer id) throws SQLException {
		String sql = "select * from telefones where id = "+id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanTelefone bean = new BeanTelefone();
			bean.setId(resultSet.getInt("id"));
			bean.setNumero(resultSet.getString("numero"));
			bean.setTipo(resultSet.getString("tipo"));			
			bean.setUsuario(resultSet.getInt("usuario"));
			
			return bean;
		}
		
		return null;
	}
	
}
