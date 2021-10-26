package com.br.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class Estados {
    //ArrayList para inserir os Servicos
    private final List<String> arrayEstado;
    public Estados(){
        //inicia o ArrayList e insere os Estados para serem vizualizados no SelectOneMenu do PrimeFaces
        arrayEstado = new ArrayList<>();
        arrayEstado.add("RIO DE JANEIRO");
        arrayEstado.add("SÃO PAULO");
        arrayEstado.add("MINAS GERAIS");
        arrayEstado.add("DISTRITO FEDERAL");
        arrayEstado.add("CURITIBA");
        arrayEstado.add("ESPIRITO SANTO");
        arrayEstado.add("BELO HORIZONTE");
        
    }
    //Retorna o array contendo os Estados
    public List<String> getArrayEstado() {
        return arrayEstado;
    }
    
}
