package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.MessageHandler.Whole;

import beans.BeanProduto;
import connection.SingleConnection;
import jdk.nashorn.internal.ir.WhileNode;

public class DaoProduto {
	
private Connection connection;
	
	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void inserir(BeanProduto produto) {
		  try {
			String sql = "insert into produto (descricao, quantidade, valor) values (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getDescricao());
			statement.setDouble(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
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
		
    public List<BeanProduto> listar() throws SQLException{
	    	List<BeanProduto> lista = new ArrayList<BeanProduto>();
	    	
	    	String sql = "select * from produto";
	    	
	    	PreparedStatement statement = connection.prepareStatement(sql);
	    	ResultSet resultSet = statement.executeQuery();
	    	
	    	while (resultSet.next()) {
	    		
	    		BeanProduto bean = new BeanProduto();
	    		bean.setId(resultSet.getInt("id"));
	    		bean.setDescricao(resultSet.getString("descricao"));
	    		bean.setQuantidade(resultSet.getDouble("quantidade"));    		
	    		bean.setValor(resultSet.getDouble("valor"));
    		
	    		lista.add(bean);    		
	    	}
	    	
	    	return lista;    	
	    }
	    
    public void delete(Integer id){
	      try {
	    	String sql = "delete from produto where id = '"+id+"'";
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
	    
    public void editar(BeanProduto produto) {
	  	  try {
	  		String sql = "update produto set descricao = ?, quantidade = ?, valor = ? where id = "+produto.getId();
	  		PreparedStatement statement = connection.prepareStatement(sql);
	  		statement.setString(1, produto.getDescricao());
	  		statement.setDouble(2, produto.getQuantidade());
	  		statement.setDouble(3, produto.getValor());
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

	public BeanProduto consultar(Integer id) throws SQLException {
			String sql = "select * from produto where id = "+id;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				BeanProduto bean = new BeanProduto();
				bean.setId(resultSet.getInt("id"));
				bean.setDescricao(resultSet.getString("descricao"));
				bean.setQuantidade(resultSet.getDouble("quantidade"));			
				bean.setValor(resultSet.getDouble("valor"));
				
				return bean;
			}
			
			return null;
		}
		
	public boolean validarProduto(String descricao) throws SQLException {
			String sql = "select count(1) as qtd from produto where descricao = '"+descricao+"' ";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
					
				return resultSet.getInt("qtd") <= 0;
			}
			
			return false;
		}
	
}
