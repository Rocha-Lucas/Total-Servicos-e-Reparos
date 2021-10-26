package com.br.dao;

import com.br.model.Agendamento;
import com.br.database.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class AgendamentoDAO {
   
    private Connection connection;

    //get e set da conexao
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    
    /*
    Método para inserir um dado novo na tabela, recebendo um objeto do tipo Agendamento
    */
    public void inserir(Agendamento agendamento) {
        //Query para inserir um dado novo na tabela
        String sql = "INSERT INTO tb_agendamento (dt_agendamento, hr_agendamento, nm_cliente, telefone, observacao, endereco, cidade, estado, cep, servico) VALUES (STR_TO_DATE(?,'%d/%m/%Y'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            //Cria um PreparedStatement, para executar uma query
            stmt = connection.prepareStatement(sql);
            //Adiciona os valores que são esperados pela query
            stmt.setString(1, agendamento.getDataAgendamento());
            stmt.setTime(2, agendamento.timeFormat(agendamento.getHoraAgendamento()+":00"));
            stmt.setString(3, agendamento.getNomeCliente().toLowerCase());
            stmt.setString(4, agendamento.getTelefone());
            stmt.setString(5, agendamento.getObservacao());
            stmt.setString(6, agendamento.getEndereco().toLowerCase());
            stmt.setString(7, agendamento.getCidade());
            stmt.setString(8, agendamento.getEstado().toUpperCase());
            stmt.setString(9, agendamento.getCep());
            stmt.setString(10, agendamento.getServico().toLowerCase());
            //Executa a query
            stmt.execute();
        } catch (SQLException ex){
            //Caso o ocorra algum erro no procedimento retorna no console e ativa a excessão do MessageDialog do bloco catch no inserir AgendamentoBean 
            System.out.println("Não foi possível inserir os dados! Erro : " + ex);
            JOptionPane.showInternalMessageDialog(null, "");

        }finally{
            ConexaoMySQL.fecharConexao(connection, stmt);
        }
    }

    /*
    Método para alterar um dado armazenado na tabela, recebendo um objeto do tipo Agendamento
    */
    public void alterar(Agendamento agendamento) {
        //Query para atualizar o dado armazenado na tabela referente ao id selecionado
        String sql = "UPDATE tb_agendamento SET dt_agendamento = STR_TO_DATE(?,'%d/%m/%Y'), hr_agendamento = ?, nm_cliente = ?, telefone = ?, observacao  = ?, endereco = ?, cidade = ?, estado = ?, cep = ?, servico = ? WHERE id_agendamento = ?";
        PreparedStatement stmt = null;
        try {
            //Cria um PreparedStatement, para executar uma query
            stmt = connection.prepareStatement(sql);
            //Adiciona os valores para atualizar
            stmt.setString(1, agendamento.getDataAgendamento());
            stmt.setTime(2, agendamento.timeFormat(agendamento.getHoraAgendamento()+":00"));
            stmt.setString(3, agendamento.getNomeCliente().toLowerCase());
            stmt.setString(4, agendamento.getTelefone());
            stmt.setString(5, agendamento.getObservacao());
            stmt.setString(6, agendamento.getEndereco().toLowerCase());
            stmt.setString(7, agendamento.getCidade());
            stmt.setString(8, agendamento.getEstado().toUpperCase());
            stmt.setString(9, agendamento.getCep());
            stmt.setString(10, agendamento.getServico().toLowerCase());
            stmt.setInt(11, agendamento.getIdAgendamento());
            //Executa a query
            stmt.execute();
        } catch (SQLException ex) {
            //retorna false caso o ocorra algum erro no procedimento e o retorna no console
            System.out.println("Não foi possível alterar os dados! Erro: " + ex);
        }finally{
            //Encerra a conexão
            ConexaoMySQL.fecharConexao(connection, stmt);
        }
    }

    /*
    Método para remover um dado armazenado na tabela, recebendo um objeto do tipo Agendamento
    */
    public void remover(Agendamento agendamento) {
        //Query para deletar o dado armazenado na tabela referente ao id selecionado
        String sql = "DELETE FROM tb_agendamento WHERE id_agendamento = ?";
        PreparedStatement stmt = null;
        try {
            //Cria um PreparedStatement, para executar uma query
            stmt = connection.prepareStatement(sql);
            //Adiciona os valores que são esperados pela query
            stmt.setInt(1, agendamento.getIdAgendamento());
            //Executa a query
            stmt.execute();
        } catch (SQLException ex) {
            //retorna false caso o ocorra algum erro no procedimento e o retorna no console
            System.out.println("Não foi possível remover os dados! Erro : " + ex);
        }
        finally{
            //Encerra a conexão
            ConexaoMySQL.fecharConexao(connection, stmt);
        }
    }

     /*
    Método para listar os dados armazenados na tabela, retornando uma lista do tipo Agendamento
    */
    public List<Agendamento> listar() throws SQLException {
        //Query para selecionar todos os dados armazenados na tabela
        String sql = "SELECT * FROM tb_agendamento";

        List<Agendamento> retorno = new ArrayList<>();
        PreparedStatement stmt = null;
        //Classe que vai recuperar os dados do banco. ***SELECT***
        ResultSet rs = null;
        try {
            //Cria um PreparedStatement, para executar uma query
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Agendamento agendamento = new Agendamento();
//                agendamento.refactorData(rs.getDate("dt_agendamento"));
                agendamento.setIdAgendamento(rs.getInt("id_agendamento"));
                agendamento.setDataAgendamento(agendamento.getDataAgendamentoFormatada().format(rs.getDate("dt_agendamento")));
                agendamento.setHoraAgendamento(agendamento.getTimeToString().format(rs.getTime("hr_agendamento")));
                agendamento.setNomeCliente(agendamento.CapString(rs.getString("nm_cliente")));
                agendamento.setTelefone(rs.getString("telefone"));
                agendamento.setObservacao(rs.getString("observacao"));
                agendamento.setEndereco(agendamento.CapString(rs.getString("endereco")));
                agendamento.setCidade(agendamento.CapString(rs.getString("cidade")));
                agendamento.setEstado(rs.getString("estado"));
                agendamento.setCep(rs.getString("cep"));
                agendamento.setServico(agendamento.CapString(rs.getString("servico")));
                //vamos adicionando a liss retorno
                retorno.add(agendamento);
            }

        } catch (SQLException ex) {
            System.out.println("Não foi possível listar os dados. Erro: " + ex);
        }finally{
            //Encerra a conexão
            ConexaoMySQL.fecharConexao(connection, stmt, rs);
        }
        return retorno;
    }
}
