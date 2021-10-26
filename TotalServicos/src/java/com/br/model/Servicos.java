package com.br.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class Servicos {
    //ArrayList para inserir os Serviços
    private final List<String> arrayServico;
    
    public Servicos(){
        //Inica o ArrayList e insere os Serviços para serem vizualizados no SelectOneMenu do PrimeFaces
        arrayServico = new ArrayList<>();
        arrayServico.add("Elétrica");
        arrayServico.add("Hidráulica");
        arrayServico.add("Segurança eletrônica");
        arrayServico.add("Manutenção/instalação de ar condicionado");
        arrayServico.add("Manutenção/instalação de bombas d'água");
        arrayServico.add("Manutenção/instalação de boia de nivel elétrica");
        arrayServico.add("Manutenção/instalação de interfone");
        arrayServico.add("Limpeza de Caixas d'água");
        arrayServico.add("Poda de árvore");
        arrayServico.add("Montagem/desmontagem de móvel");
        arrayServico.add("Manutenção de telhado");
    
    }
    //Get ArrayServico que retorna a lista de Serviços
    public List<String> getArrayServico() {
        return arrayServico;
    }
}
