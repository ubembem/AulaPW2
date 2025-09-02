package aulasPW2.Aula1.dao;

import aulasPW2.Aula1.model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAO {
    //Criar um objeto conection para receber a conexão
    Connection conexao;
    public PessoaDAO() {
        conexao = MinhaConexao.conexao();
    }
    public boolean save(Pessoa pessoa) {
        try{
            String sql = "INSERT INTO pessoa(nome, cpf, telefone, email) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setString(3, pessoa.getTelefone());
            stmt.setString(4, pessoa.getEmail());
            if(stmt.execute()){
                System.out.println("Inserido com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean update(Pessoa pessoa) {
        try {
            //comando sql
            String sql = "update tb_pessoa set nome=? where id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setString(1, pessoa.getNome());
            ps.setLong(2, pessoa.getId());

            if (ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Pessoa buscarPessoa(Long id) {
        try {
            //comando sql
            String sql = "select * from tb_pessoa where id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
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
            String sql = "select * from tb_pessoa";
            PreparedStatement ps = conexao.prepareStatement(sql);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            //cria uma lista de pessoas para retornar
            List<Pessoa> pessoas = new ArrayList();
            //laço para buscar todas as pessoas do banco
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
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
            String sql = "delete from tb_pessoa where id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
