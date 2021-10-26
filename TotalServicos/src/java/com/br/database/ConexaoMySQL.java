package com.br.database;

/**
 *
 * @author lucas
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fschi
 */
public class ConexaoMySQL {
    //Objeto criado para pegar o Usuario e senha da Classe UsuarioMySQL
    private static final UsuarioMySQL userMySQL = new UsuarioMySQL();
    //atributos static para a conexao e serão final = uma constante
    
    //Recebe o nome de usuario do banco de dados armazenado na classe UsuarioMySQL
    private static final String USER = userMySQL.getUSER();
    //Recebe Senha do banco de dados armazenada na classe UsuarioMySQL
    private static final String PASS = userMySQL.getPASS();
    //Faz com que a classe seja carregada pela JVM
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //Caminho do banco de dados, porta nome do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/db_totalservicos?autoReconnect=true&useSSL=false";

    /*
    Incia a conexão com o Banco de Dados
    */
    public static Connection getConexao() {
        try {
            //Faz com que a classe seja carregada pela JVM
            Class.forName(DRIVER);
            System.out.println("Inciando conexão!");
            //Cria e retorna a conexão com o banco de dados
            return (Connection) DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro na conexão!: " + ex);
            throw new RuntimeException("Algo aconteceu de errado com a conexão com o banco, veja: " + ex);
        }
    }

    /*
    Fecha a conexão com o banco de dados recebendo a Conexão como parâmetro
    */
    public static void fecharConexao(Connection conn) {
        if (conn != null)//se estiver conectado
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Algo aconteceu de errado com o fechamento da conexão com o banco, veja: " + ex);
            }
        }
    }

    /*
    Fecha a conexão com o banco de dados recebendo a Conexão e PreparedSatement como parâmetros
    */
    public static void fecharConexao(Connection conn, PreparedStatement stmt) {
        if (stmt != null)//se estiver conectado 
        {
            try {
                stmt.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Algo aconteceu de errado com o fechamento da conexão com o banco, veja: " + ex);
            }
        }

        fecharConexao(conn);
    }
    /*
    Fecha a conexão com o banco de dados recebendo a Conexão, o PreparedSatement e o ResultSet como parâmetros
    */
    public static void fecharConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null)//se estiver conectado
        {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Algo aconteceu de errado com o fechamento da conexão com o banco, veja: " + ex);
            }
        }

        fecharConexao(conn, stmt);
    }

}
