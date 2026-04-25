package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EditoraDAO;
import br.ufscar.dc.dsw.domain.Editora;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/editoras/*")
public class EditoraController extends HttpServlet {

    private EditoraDAO dao;

    @Override
    public void init() {
        dao = new EditoraDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) action = "";

        try {
            switch (action) {
                case "/cadastro": apresentaFormCadastro(request, response); break;
                case "/insercao": insere(request, response); break;
                case "/remocao": remove(request, response); break;
                case "/edicao": apresentaFormEdicao(request, response); break;
                case "/atualizacao": atualize(request, response); break;
                default: lista(request, response); break;
            }
        } catch (RuntimeException e) { throw new ServletException(e); }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listaEditoras", dao.getAll());
        request.getRequestDispatcher("/editora/lista.jsp").forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/editora/formulario.jsp").forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        request.setAttribute("editora", dao.get(id));
        request.getRequestDispatcher("/editora/formulario.jsp").forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        dao.insert(new Editora(request.getParameter("cnpj"), request.getParameter("nome")));
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        dao.update(new Editora(id, request.getParameter("cnpj"), request.getParameter("nome")));
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        dao.delete(new Editora(id));
        response.sendRedirect("lista");
    }
}