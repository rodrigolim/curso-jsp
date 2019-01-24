package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoProduto daoProduto = new DaoProduto();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProdutoServlet() {
		super();
		// TODO Auto-generated constructor stub
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
			String id = request.getParameter("id");

			if (!(acao == null)) {

				if (acao.equalsIgnoreCase("delete")) {
					daoProduto.delete(Integer.parseInt(id));

					RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
					request.setAttribute("produtos", daoProduto.listar());
					view.forward(request, response);
				} else if (acao.equalsIgnoreCase("editar")) {
					BeanProduto beanProduto = daoProduto.consultar(Integer.parseInt(id));

					RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
					request.setAttribute("product", beanProduto);
					view.forward(request, response);
				} else if (acao.equalsIgnoreCase("listartodos")) {
					RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
					request.setAttribute("produtos", daoProduto.listar());
					view.forward(request, response);
				}

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

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {

				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String descricao = request.getParameter("descricao");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			try {
				String msg = null;
				boolean podeInserir = true;

				if (descricao == null || descricao.isEmpty()) {
					msg = "Descrição deve ser informada!";
					podeInserir = false;
				} else if (quantidade == null || quantidade.isEmpty()) {
					msg = "Quantidade deve ser informada!";
					podeInserir = false;
				} else if (valor == null || valor.isEmpty()) {
					msg = "Valor deve ser informado!";
					podeInserir = false;
				} else if (id == null || id.isEmpty() && !daoProduto.validarProduto(descricao)) {
					msg = "Produto já existente para esta descrição!";
					podeInserir = false;
				}

				BeanProduto produto = new BeanProduto();
				produto.setId(!id.isEmpty() ? Integer.parseInt(id) : null);
				produto.setDescricao(descricao);

				if (quantidade != null && !quantidade.isEmpty()) {
					produto.setQuantidade(Double.parseDouble(quantidade));
				}

				if (valor != null && !valor.isEmpty())
					produto.setValor(Double.parseDouble(valor));

				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty() && podeInserir) {
					daoProduto.inserir(produto);
				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoProduto.editar(produto);
				}

				if (!podeInserir) {
					request.setAttribute("product", produto);
				}

				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
