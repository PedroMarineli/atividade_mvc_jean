/**
 *
 * @author PEDRO_TI
 */

package controller;

import dao.CarroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.CarroVO;

public class CarroController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            int operacao = Integer.parseInt(request.getParameter("acao"));
            CarroDAO pDAO = new CarroDAO();
            
            switch(operacao){
                case 1 -> {
                    CarroVO p = new CarroVO();
                    p.setMarca(request.getParameter("marca")); 
                    p.setModelo(request.getParameter("modelo")); 
                    p.setAno(request.getParameter("ano")); 
                    if(pDAO.gravar(p)){
                        response.sendRedirect("exibe_resultado.jsp?result=1");
                    }else{
                        response.sendRedirect("exibe_resultado.jsp?result=2");
                    }
                }
                case 2 -> {
                    request.setAttribute("lista", pDAO.buscarPessoas());
                    RequestDispatcher rd = request.getRequestDispatcher("/exibe_veiculos.jsp");
                    rd.forward(request, response);                    
                }
                case 3 ->{
                    int id = Integer.parseInt(request.getParameter("id"));
                    if(pDAO.excluir(id)){
                        response.sendRedirect("exibe_resultado.jsp?result=1");
                    }else{
                        response.sendRedirect("exibe_resultado.jsp?result=2");
                    }
                }
                        
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

