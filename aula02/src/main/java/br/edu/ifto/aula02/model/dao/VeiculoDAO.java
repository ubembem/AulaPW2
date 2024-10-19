package br.edu.ifto.aula02.model.dao;

import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.Veiculo;
import br.edu.ifto.aula02.model.jdbc.MinhaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeiculoDAO {
    //criar um objeto Connection para receber a conexão
    Connection con;

    public VeiculoDAO(){
        con = MinhaConexao.conexao();
    }

    public Veiculo buscarVeiculo(Long id) {
        try {
            //comando sql
            String sql = "select * from veiculo where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Veiculo v = new Veiculo();
                v.setId(rs.getLong("id"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setPreco(rs.getDouble("preco"));
                v.setAnoFabricacao(rs.getInt("anoFabricacao"));
                return v;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Veiculo> buscarVeiculos() {
        try {
            //comando sql
            String sql = "select * from veiculo";
            PreparedStatement ps = con.prepareStatement(sql);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            //cria uma lista de veículos para retornar
            List<Veiculo> veiculos = new ArrayList();
            //laço para buscar todas os veículos do banco
            while (rs.next()) {
                Veiculo v = new Veiculo();
                v.setId(rs.getLong("id"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setPreco(rs.getDouble("preco"));
                v.setAnoFabricacao(rs.getInt("anoFabricacao"));
                //add veículo na lista
                veiculos.add(v);
            }
            //retorna a lista de veículos
            return veiculos;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean remove(Long id) {
        try {
            //comando sql
            String sql = "delete from veiculo where id = ?";
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

    public boolean save(Veiculo veiculo) {
        try {
            //comando sql
            String sql = "insert into veiculo (marca, modelo, preco, anoFabricacao) values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?

            ps.setString(1, veiculo.getMarca());
            ps.setString(2, veiculo.getModelo());
            ps.setDouble(3, veiculo.getPreco());
            ps.setInt(4, veiculo.getAnoFabricacao());




            if(ps.executeUpdate()==1)
                System.out.println("Veículo cadastrado com sucesso!");
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(Veiculo veiculo) {
        try{
            String sql = "update veiculo set marca = ?, modelo=?, preco = ?, anoFabricacao = ? where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //Referênciar o parâmetro do método para a?
            ps.setString(1, veiculo.getMarca());
            ps.setString(2, veiculo.getModelo());
            ps.setDouble(3, veiculo.getPreco());
            ps.setInt(4, veiculo.getAnoFabricacao());
            ps.setLong(5, veiculo.getId());

            if(ps.executeUpdate()==1)
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
