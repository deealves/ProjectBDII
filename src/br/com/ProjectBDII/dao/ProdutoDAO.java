/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjectBDII.dao;

import br.com.ProjectBDII.Model.Produto;
import br.com.ProjectBDII.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {
    private Connection con;
    private String sql;
    private PreparedStatement stmt;
 
   public boolean insert(Produto p) throws SQLException {
        sql = "INSERT INTO produto (nome, descricao, valor, quantidade, unidade) VALUES (?,?,?,?,?)";

            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1,p.getNome());
            stmt.setString(2,p.getDescricao());
            stmt.setFloat(3,p.getValor());
            stmt.setInt(4, p.getQuant());
            stmt.setInt(5,p.getUnidade());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } 
   
    public boolean update(Produto p) throws SQLException{
        sql = "UPDATE produto SET  nome = ?, descricao = ?, valor = ?, quantidade = ?, unidade = ? WHERE id=?";
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(sql);
        stmt.setString(1,p.getNome());
        stmt.setString(2,p.getDescricao());
        stmt.setFloat(3,p.getValor());
        stmt.setInt(4, p.getQuant());
        stmt.setInt(5,p.getUnidade());
        stmt.setInt(6,p.getId());
        stmt.executeUpdate();
        stmt.close();
        con.close();
        return true;
    
    }
     
    public boolean delete(Produto p) throws SQLException{
        sql = "DELETE FROM produto WHERE id=?";
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1,p.getId());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
     
    }
     
     public List<Produto> listar() throws SQLException{
         sql = "select p.id, p.id, p.nome, p.descricao, p.valor, p.quantidade, p.unidade from produto p ";
         con = ConnectionFactory.getConnection();
         stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         
         List<Produto> produtos = new ArrayList<>();
         while (rs.next()){
             
             Produto p = new Produto();
             
             p.setId(rs.getInt("id"));
             p.setNome(rs.getString("nome"));
             p.setDescricao(rs.getString("descricao"));
             p.setValor(rs.getFloat("valor"));
             p.setQuant(rs.getInt("quantidade"));
             p.setUnidade(rs.getInt("unidade"));
             
             produtos.add(p);
         }
         
         con.close();
         return produtos;
        
     }
     
     public List<Produto> buscar(String query) throws SQLException{
        List<Produto> lista = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        
        sql = "select p.* from produto p  where p.nome ilike ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, query + '%');
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt(1);
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");
            float valor = rs.getFloat("valor");
            int quantidade = rs.getInt("quantidade");
            
            
            Produto p = new Produto();
            
            p.setId(id);
            p.setNome(nome);
            p.setDescricao(descricao);
            p.setValor(valor);
            p.setQuant(quantidade);
            p.setUnidade(quantidade);
            
            
            lista.add(p);
        }
        con.close();
        return lista;
    }
}