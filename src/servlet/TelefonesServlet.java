package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanTelefone;
import beans.BeanUsuario;
import dao.DaoTelefone;
import dao.DaoUsuario;

/**
 * Servlet implementation class TelefonesServlet
 */
@WebServlet("/salvarTelefones")
public class TelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefone daoTelefone = new DaoTelefone();

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
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");
			String id = request.getParameter("id");
			
			BeanUsuario usuario = daoUsuario.consultar(Integer.parseInt(user));

			request.getSession().setAttribute("userEscolhido", usuario);

			if (!(acao == null)) {
				if (acao.equalsIgnoreCase("delete")) {
					daoTelefone.delete(Integer.parseInt(id));

					RequestDispatcher view = request.getRequestDispatcher("cadastroTelefones.jsp");
					request.setAttribute("telefones", daoTelefone.listar());
					view.forward(request, response);
				} else if (acao.equalsIgnoreCase("editar")) {
					BeanTelefone beanCursoJsp = daoTelefone.consultar(Integer.parseInt(id));

					RequestDispatcher view = request.getRequestDispatcher("cadastroTelefones.jsp");
					request.setAttribute("fone", beanCursoJsp);
					view.forward(request, response);
				} 
			}else  {
				RequestDispatcher view = request.getRequestDispatcher("cadastroTelefones.jsp");
			    request.setAttribute("telefones", daoTelefone.listar());
			    view.forward(request, response);
			}

		} catch (Exception e) {
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
		
		BeanUsuario usuario = (BeanUsuario) request.getSession().getAttribute("userEscolhido");
		
	    String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {

				RequestDispatcher view = request.getRequestDispatcher("cadastroTelefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			BeanTelefone telefone = new BeanTelefone();
			telefone.setId(!id.isEmpty() ? Integer.parseInt(id) : null);
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setUsuario(usuario.getId());


			try {
				String msg = null;
				boolean podeInserir = true;

				if (numero == null || numero.isEmpty()) {
					msg = "Número deve ser informado!";
					podeInserir = false;
				} else if (tipo == null || tipo.isEmpty()) {
					msg = "Tipo deve ser informado!";
					podeInserir = false;
				} 

				if (msg != null) {
					request.setAttribute("msg", msg);
				}

				if (id == null || id.isEmpty() && podeInserir) {
					daoTelefone.inserir(telefone);
				} else if (id != null || !id.isEmpty()) {
					daoTelefone.editar(telefone);
				}

				if (!podeInserir) {
					request.setAttribute("fone", telefone);
				}

				RequestDispatcher view = request.getRequestDispatcher("cadastroTelefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
