<%--<jsp:include page="/WEB-INF/controleAcesso.jsp" flush="false">
    <jsp:param name="nomePagina" value="Tela de Itens de Conforto"/>
</jsp:include>--%>
<%@page import="br.cefetmg.inf.hosten.model.domain.ItemConforto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <!-- Mostra para o browser que o site é otimizado para mobile -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Itens de Conforto</title>

        <!-- CSS -->
        <!-- Google Icon Font -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Materialize CSS -->
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/materialize/materialize.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/padrao-tipo-1.css"/>

        <!--  Script -->
        <!-- Import jQuery before Materialize JS  -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/materialize/materialize.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/itens-conforto.js"></script>
    </head>
    
    <body>
        <header>
            <%@include file="menu.jsp"%>
        </header>

        <main>            
            <h4 class="title">Visualização de Itens de Conforto</h4>
            
            <div id="container" class="row">
                <div class="col s9 search-box">
                    <div class="input-field">
                        <i class="material-icons prefix">search</i>
                        <label for="search">Buscar item de conforto por... (selecione um campo ao lado)</label>
                        <input id="search" type="search">
                    </div>
                </div>

                <div class="col s3 select-box">
                    <div class="input-field">
                        <select>
                            <option value="1">Código</option>
                            <option value="2">Descrição</option>
                        </select>
                        <label>Filtro</label>
                    </div>
                </div>
            </div>

            <div>
                <jsp:include page="itens-conforto-tabela.jsp"></jsp:include>
            </div>
                
            <div class="card-action right-align button-box">
                <a href="/hosten/view/itens-conforto-inserir.jsp"><button id="add-button" class="btn waves-effect waves-light"><i class="material-icons left">add_circle_outline</i>Novo Item de Conforto</button></a>
            </div>
        </main>

        <footer>
            <div class="footer-copyright">
                <div class="container">
                    Feito com  <i class="tiny material-icons">favorite_border</i> por estudantes do CEFET-MG
                </div>
            </div>
        </footer>
    </body>
</html>
