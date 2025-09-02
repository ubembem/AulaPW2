package aulasPW2.Aula1.conexao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConexaoJDBC {
    public Connection criarConexao() throws SQLException;
}
