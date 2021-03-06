package br.cefetmg.inf.hosten.controller.exibirtela;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExibirTelaInserirUsuario {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterCargo manterCargo = new ManterCargoProxy();
            List<Cargo> listaCargos = manterCargo.listarTodos();
            
            request.setAttribute("listaCargos", listaCargos);
            
            jsp = "/view/funcionarios-inserir.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
