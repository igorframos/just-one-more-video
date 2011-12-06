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
		
		System.out.println("Recebi um vídeo!");
		
		String acao = request.getParameter("acao");
		String video = request.getParameter("cod");
		int id = Integer.parseInt( request.getParameter("id") );
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/xml");
		
		if ( (acao != null) && (video != null) && (id > 0) ) {
			
			if ( controle.existeCliente(id) ) {
				// TODO um filtro que só permite vídeos do iutubil
				
				if ( acao.equalsIgnoreCase("gostar") ) {				
					controle.insereVideoGostado(0, video);
					
					response.getWriter().write("<mensagem>Adicionado!</mensagem>");
				} else {
					// TODO implementar o que fazer com "não gostar"
				}
			}						
		} else {
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
