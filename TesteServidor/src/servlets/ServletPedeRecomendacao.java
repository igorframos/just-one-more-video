package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.UsuarioControle;

/**
 * Servlet implementation class ServletPedeRecomendacao
 */
@WebServlet("/ServletPedeRecomendacao")
public class ServletPedeRecomendacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPedeRecomendacao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioControle controle = UsuarioControle.getInstance();
		
		System.out.println("Estou pedindo recomendações!");
		
		String idTemp = request.getParameter("id");
		int id;
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/xml");
		
		if (idTemp != null) {
			id = Integer.parseInt( idTemp );
			
			TreeSet<String> videos = controle.getVideosRecomendadosFromId(id);
			
			PrintWriter out = response.getWriter();
			
			out.println("<resposta>");
			out.println("<mensagem>sucesso</mensagem>");
			
			for (String link : videos) {
				out.println("<video>" + link + "</video>");
			}
			
			out.println("</resposta>");
		} else {
			response.getWriter().write("<mensagem>falha</mensagem>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
