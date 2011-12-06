package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.UsuarioControle;

/**
 * Servlet implementation class ServletPedeId
 */
@WebServlet("/ServletPedeId")
public class ServletPedeId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPedeId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioControle controle = UsuarioControle.getInstance();

		System.out.println("Pedindo um id");
		
		int id = controle.geraId();
		
		System.out.println("Mandei o ID " + id + " !");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/xml");
		response.getWriter().write("<id>" + id + "</id>");
		response.getWriter().flush();
		response.getWriter().close();
		
		System.out.println(response.isCommitted());
	}

}
