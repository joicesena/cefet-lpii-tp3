package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.ManterItemConforto;
import br.cefetmg.inf.hosten.model.service.impl.ManterItemConfortoImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarItensConforto {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            ManterItemConforto manterItem = new ManterItemConfortoImpl();
            List<ItemConforto> listaItens = manterItem.listarTodos();
            
            request.setAttribute("listaItens", listaItens);
            jsp = "/view/itens-conforto.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}