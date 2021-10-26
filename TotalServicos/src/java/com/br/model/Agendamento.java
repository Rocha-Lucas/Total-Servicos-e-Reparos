package com.br.model;

import com.sun.xml.rpc.processor.util.StringUtils;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author lucas
 */
public class Agendamento {

    //Criação de todos atributos da classe
    private int idAgendamento;
    private String dataAgendamento;
    private String horaAgendamento;
    private String nomeCliente;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private String observacao;
    private String servico;
    private String enderecoCompleto;
    private Time timeFormat;
    //atributo usado para refatorar o a hora vinda do Banco de Dados
    private DateFormat timeToString = new SimpleDateFormat("HH:mm");
    //atributo usado para refatorar a data vinda do Banco de Dados
    private DateFormat dataAgendamentoFormatada = new SimpleDateFormat("dd/MM/yyyy");

 
    //Get e Set idAgendamento
    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    //Get e Set dataAgendamento
    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    //Get e Set dataAgendamentoFormatada
    public DateFormat getDataAgendamentoFormatada() {
        return dataAgendamentoFormatada;
    }

    public void setDataAgendamentoFormatada(DateFormat dataAgendamentoFormatada) {
        this.dataAgendamentoFormatada = dataAgendamentoFormatada;
    }

    //Get e Set horaAgendamento
    public String getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(String horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    //Get e Set nomeCliente
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomecliente) {
        this.nomeCliente = nomecliente;
    }

    //Get e Set telefone
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    //Get e Set endereco
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereço) {
        this.endereco = endereço;
    }

    //Get e Set cidade
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    //Get e Set estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //Get e Set cep
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    //Get e Set observacao
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    //Get e Set servico
    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    //Get enderecoCompleto, retorna o a junção de endereço cidade e cep para a Datatable
    public String getEnderecoCompleto() {
        enderecoCompleto = endereco + " - " + cidade + " - " + cep;
        return enderecoCompleto;
    }

    public DateFormat getTimeToString() {
        return timeToString;
    }

    public void setTimeToString(DateFormat timeToString) {
        this.timeToString = timeToString;
    }

    
    public String CapString(String minuscula){
        return StringUtils.capitalize(minuscula);
    }
    
    public Time timeFormat(String horario){
        return timeFormat.valueOf(horario);
    }
}
