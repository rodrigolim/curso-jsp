package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanUsuario;
import dao.DaoUsuario;

/**
 * Servlet implementation class TelefonesServlet
 */
@WebServlet("/salvarTelefones")
public class TelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TelefonesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String user = request.getParameter("user");
			BeanUsuario usuario = daoUsuario.consultar(Integer.parseInt(user));

			request.getSession().setAttribute("userEscolhido", usuario);
			
		
			

			RequestDispatcher view = request.getRequestDispatcher("cadastroTelefones.jsp");
			// request.setAttribute("telefones", daoTelefone.listar());
			request.setAttribute("msg", "Salvo com sucesso!");
			view.forward(request, response);

		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
	}

}
