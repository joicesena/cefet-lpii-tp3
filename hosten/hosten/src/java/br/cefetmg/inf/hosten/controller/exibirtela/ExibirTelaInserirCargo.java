package br.cefetmg.inf.hosten.controller.exibirtela;

import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExibirTelaInserirCargo {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterCargo manterCargo = new ManterCargoProxy();
            List<Programa> listaProgramas = manterCargo.listarTodosProgramas();

            request.setAttribute("listaProgramas", listaProgramas);
            
            jsp = "/view/cargos-inserir.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
