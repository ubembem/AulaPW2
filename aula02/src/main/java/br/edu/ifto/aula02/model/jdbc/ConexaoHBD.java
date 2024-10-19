package br.edu.ifto.aula02.model.jdbc;

import br.edu.ifto.aula02.model.dao.PessoaDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoHBD implements ConexaoJDBC{


    @Override
    public Connection criarConexao() {
        try {
            //carregar o driver de conexão
            Class.forName("org.h2.Driver");
            //parâmetros
            String url = "jdbc:h2:mem:pweb";
            String usuario = "sa";
            String senha = "";
            //retorna a conexão com o banco de dados
            return DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexaoHBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
