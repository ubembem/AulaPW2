package br.edu.ifto.aula02.model.dao;

import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.jdbc.MinhaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAO {

    //criar um objeto Connection para receber a conexão
    Connection con;

    public PessoaDAO(){
        con = MinhaConexao.conexao();
    }

    public Pessoa buscarPessoa(Long id) {
        try {
            //comando sql
            String sql = "select * from pessoa where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Pessoa> buscarPessoas() {
        try {
            //comando sql
            String sql = "select * from pessoa";
            PreparedStatement ps = con.prepareStatement(sql);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            //cria uma lista de pessoas para retornar
            List<Pessoa> pessoas = new ArrayList();
            //laço para buscar todas as pessoas do banco
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                //add pessoa na lista
                pessoas.add(p);
            }
            //retorna a lista de pessoas
            return pessoas;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean remove(Long id) {
        try {
            //comando sql
            String sql = "delete from pessoa where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean save(Pessoa pessoa) {
        try {
            //comando sql
            String sql = "insert into pessoa (nome) values (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setString(1, pessoa.getNome());

            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(Pessoa pessoa) {
       try{
           String sql = "update pessoa set nome = ? where id = ?";
           PreparedStatement ps = con.prepareStatement(sql);
           //Referênciar o parâmetro do método para a?
           ps.setString(1, pessoa.getNome());

           if(ps.executeUpdate()==1)
               return true;
           return false;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }
}
