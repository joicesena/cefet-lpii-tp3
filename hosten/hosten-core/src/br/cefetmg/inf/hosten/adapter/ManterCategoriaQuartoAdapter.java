package br.cefetmg.inf.hosten.adapter;

import java.util.ArrayList;

public class ManterCategoriaQuartoAdapter implements AdapterInterface {
    private ArrayList lista;
    private String tipoRetorno;

    public ManterCategoriaQuartoAdapter(ArrayList lista) {
        this.lista = lista;
        
        operacao();
    }

    private void operacao() {
        
    }

    @Override
    public Object getReturnObject() {
        return lista;
    }

    @Override
    public String getReturnObjectType() {
        return tipoRetorno;
    }
    
}