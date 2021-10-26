package com.br.bean;

import com.br.model.Agendamento;
import com.br.dao.AgendamentoDAO;
import com.br.database.ConexaoMySQL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lucas
 */
@ManagedBean
@SessionScoped
public class AgendamentoBean {

    //Criação do objeto do tipo AgendamentoDAO
    private final AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
    //Criação do objeto do tipo Agendamento
    private Agendamento agendamento = new Agendamento();
    //Criação do objeto lista do tipo Agendamento, para listar na datatable
    private List<Agendamento> listagendamento = new ArrayList();
    //Criação de um lista para filtrar os dados da busca
    private List<Agendamento> listafiltrada;

    public void mensagemAdd(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, mensagem);
    }

    /*
    Método que realiza a conexão com Banco de dados e repassa o Objeto, 
    na memoria, para o metodo inserir na classe AgendamentoDAO
     */
    public void inserir() {
        //Verifica se os dados no formulário estão vazios, se satisfazer a condição, 
        //retorna uma mensagem para o usuário, se não, executa o bloco else
        if ("".equals(agendamento.getDataAgendamento())
                || "".equals(agendamento.getHoraAgendamento())
                || "".equals(agendamento.getNomeCliente())
                || "".equals(agendamento.getEndereco())
                || "".equals(agendamento.getCidade())
                || "".equals(agendamento.getCep())
                || "".equals(agendamento.getEstado())
                || "".equals(agendamento.getServico())) {
            mensagemAdd("Erro !", "Algum campo não foi preenchido", FacesMessage.SEVERITY_ERROR);
        } else {

            try {

                //Realiza a conexão com o banco de dados
                agendamentoDAO.setConnection(ConexaoMySQL.getConexao());

                //Chama o metodo da Classe AgendamentoDAO e passa o objeto do tipo Agendamento a ser inserido
                agendamentoDAO.inserir(agendamento);

                //Zera o objeto para não ficar carregado na memória
                agendamento = new Agendamento();

                //Caso os procedimentos sejam bem sucessidos, rrtorna a mensagem de sucesso para o usuário
                mensagemAdd("Informação !", "Os dados foram salvos com sucesso.", FacesMessage.SEVERITY_INFO);
            } catch (Exception e) {
                //Caso ocorra algum problema, retorna a mensagem de falha para o usuário
                mensagemAdd("Erro !", "Não foi possivel salvar as informações. Verifique se os dados estão corretos ! ", FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    /*
    Método para zerar o objeto na memória, usado para limpar os campos do formulário
     */
    public void limparFormulario() {
        agendamento = new Agendamento();
    }

    /*
    Método para selecionar um objeto a ser atualizado ou deletado
     */
    public void editar(Agendamento e) {
        agendamento = e;
    }

    /*
    Método que realiza a conexão com Banco de dados e repassa o Objeto, 
    na memoria, para o metodo alterar na classe AgendamentoDAO
     */
    public void alterar() {
        if (agendamento.getIdAgendamento() != 0) {
            try {
                //conexão com o banco de dados
                System.out.println("conexão com o banco de dados");
                agendamentoDAO.setConnection(ConexaoMySQL.getConexao());

                //inserir os dados
                System.out.println("inserir os dados");
                agendamentoDAO.alterar(agendamento);

                //zera o objeto para não ficar carregado na memória
                agendamento = new Agendamento();

                //enviar uma mensagem de alterado com sucesso no banco de dados
                mensagemAdd("Informação !", "Os dados foram alterados com sucesso.", FacesMessage.SEVERITY_INFO);
            } catch (Exception e) {
                System.out.println("Erro ! Os dados não foram alterados: " + e);
            }
        } else {
            mensagemAdd("Erro !", "Os dados não foram alterados.", FacesMessage.SEVERITY_ERROR);
        }
    }

    /*
    Método que realiza a conexão com Banco de dados e repassa o Objeto, 
    na memoria, para o metodo remover na classe AgendamentoDAO
     */
    public void deletar() {
        if (agendamento.getIdAgendamento() != 0) {
            try {
                //conexão com o banco de dados
                agendamentoDAO.setConnection(ConexaoMySQL.getConexao());

                //inserir os dados
                agendamentoDAO.remover(agendamento);

                //zera o objeto para não ficar carregado na memória
                agendamento = new Agendamento();

                //enviar uma mensagem de deletados com sucesso no banco de dados
                mensagemAdd("Informação !", "Os dados foram deletados com sucesso.", FacesMessage.SEVERITY_INFO);
            } catch (Exception e) {
                System.out.println("Erro ! Os dados não foram deletados: " + e);
            }
        } else {
            mensagemAdd("Erro !", "Os dados não foram deletados.", FacesMessage.SEVERITY_ERROR);
        }
    }

    /*
    Método que realiza a conexão com Banco de dados e chama o método listar da classe AgendamentoDAO
    armazenando o retorno no objeto listagendamento do tipo List
     */
    public void listar() throws SQLException {
        //conexão com o banco de dados
        agendamentoDAO.setConnection(ConexaoMySQL.getConexao());

        //jogar na list do banco de dodos através da classe EsatdosDAO 
        listagendamento = agendamentoDAO.listar();

        if (listagendamento == null || listagendamento.isEmpty()) {
            //mensagemAdd("Informação !", "Nenhum dado foi encontrado.", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void adicionarCadastro() {
        listagendamento.add(agendamento);
        agendamento = new Agendamento();
    }

    /*
    Método para comparar o que foi digitado no campo busca com os atributos de cada objeto do tipo Agendamento
     */
    public boolean filtrarDados(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        Agendamento agend = (Agendamento) value;
        return agend.getNomeCliente().toLowerCase().contains(filterText)
                || agend.getEstado().toLowerCase().contains(filterText)
                || agend.getCidade().toLowerCase().contains(filterText)
                || agend.getServico().toLowerCase().contains(filterText);

    }

    //Get e Set de Agendaemnto
    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    //Get e Set de listagendamento
    public List<Agendamento> getListagendamento() {
        return listagendamento;
    }

    public void setListagendamento(List<Agendamento> listagendamentos) {
        this.listagendamento = listagendamentos;
    }

    //Get e Set de listafiltrada
    public List<Agendamento> getListafiltrada() {
        return listafiltrada;
    }

    public void setListafiltrada(List<Agendamento> listafiltrada) {
        this.listafiltrada = listafiltrada;
    }

}
