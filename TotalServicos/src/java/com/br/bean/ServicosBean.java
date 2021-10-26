package com.br.bean;

import com.br.model.Servicos;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lucas
 */
@ManagedBean
@SessionScoped
public class ServicosBean {
    //Criação do objeto do tipo Servicos
    Servicos servico = new Servicos();

    public Servicos getServico() {
        return servico;
    }

}
