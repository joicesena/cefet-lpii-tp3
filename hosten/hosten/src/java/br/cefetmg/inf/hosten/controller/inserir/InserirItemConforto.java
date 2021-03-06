package br.cefetmg.inf.hosten.controller.inserir;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.proxy.ManterItemConfortoProxy;

public class InserirItemConforto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        
        try {
            String codItem = request.getParameter("codItem");
            String desItem = request.getParameter("desItem");
            
            ItemConforto itemConforto = new ItemConforto(codItem, desItem);

            IManterItemConforto manterItem = new ManterItemConfortoProxy();
            boolean testeRegistro = manterItem.inserir(itemConforto);
            
            if (testeRegistro)
                request.setAttribute("mensagem", "Item inserido com sucesso");
            else
                request.setAttribute("mensagem", "Não foi possível inserir o item!");
            
            jsp = "/servletweb?acao=ListarItensConforto&tipoAcao=Padrao";
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
