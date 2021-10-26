package com.br.bean;

import com.br.model.Estados;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lucas
 */
@ManagedBean
@SessionScoped
public class EstadosBean {
    //Criação do objeto do tipo Estados
    Estados estado = new Estados();

    public Estados getEstado() {
        return estado;
    }
    
}
