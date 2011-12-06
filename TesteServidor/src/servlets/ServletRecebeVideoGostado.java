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
		
		UsuarioControle controle = new UsuarioControle();
		
		System.out.println("Entrei no Servlet!");
		
		String acao = request.getParameter("acao");
		String video = request.getParameter("cod");
		
		String ip = request.getRemoteHost();		
		
		System.out.println(acao + " - " + video + " - " + ip);
		
		if ( (acao != null) && (video != null) ) {
			
			if ( acao.equalsIgnoreCase("gostar") ) {				
				controle.insereVideoGostado(ip, video);
				
				response.setContentType("text/xml");
				response.getWriter().write("<mensagem>Adicionado!</mensagem>");
			} else {
				
			}			
		} else {
			response.setContentType("text");
			response.getWriter().write("<mensagem>Faltou algo!</mensagem>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
