package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.UsuarioControle;

/**
 * Servlet implementation class ServletRecebeVideoGostado
 */
@WebServlet("/ServletRecebeVideoGostado")
public class ServletRecebeVideoGostado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRecebeVideoGostado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioControle controle = UsuarioControle.getInstance();
		
		System.out.println("Recebi um v�deo!");
		
		String acao = request.getParameter("acao");
		String video = request.getParameter("cod");
		
		if ( (acao != null) && (video != null) ) {
			
			if ( acao.equalsIgnoreCase("gostar") ) {				
				controle.insereVideoGostado(0, video);
				
				response.setContentType("text/html");
				response.getWriter().write("Adicionado!");
			} else {
				
			}			
		} else {
			response.setContentType("text/html");
			response.getWriter().write("Faltou algo!");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
