package web;

import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import helper.HelperClass;
import model.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO dao;
	private HelperClass help;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		dao = new UsuarioDAO();
		help = new HelperClass();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	public HelperClass getHelp() {
		return help;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {

			switch (action) {
			case "/new":
				mostrarNovoFormulario(request, response);
				break;
			case "/insert":
				inserirUsuario(request, response);
				break;
			case "/delete":
				deletarUsuario(request, response);
				break;
			case "/edit":
				mostrarFormularioEdicao(request, response);
				break;
			case "/update":
				alterarUsuario(request, response);
				break;
			default:
				listarUsuarios(request, response);
				break;
			}

		} catch (Exception e) {

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	

	public void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Usuario> listarUsuarios = dao.selecionarTodosUsuarios();
		request.setAttribute("listarUsuarios", listarUsuarios);
		System.out.println(listarUsuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-usuarios.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void mostrarNovoFormulario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("formulario-usuario.jsp");
		dispatcher.forward(request, response);
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		Usuario usuarioExistente = dao.selecionarUsuario(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("formulario-usuario.jsp");
		request.setAttribute("usuario", usuarioExistente);
		dispatcher.forward(request, response);

	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 * @throws ServletException
	 */
	public void inserirUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException, ServletException {
		help.tempInicialRequisicao();
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String data = request.getParameter("dataNascimento");

		if (!help.validarUsuarios(nome, email, endereco, data)) {
			System.out.println("ta entrando aqui");
			request.setAttribute("mensagem", "Erro ao cadastrar Usuario. Tente novamente!!");
			response.sendRedirect("new");
			return; //para não sair do if e executar o proximo bloco mesmo que entre nessa condição e não consiga validar
		}

		System.out.println("saiu do if e caiu aqui ");
		Usuario novoUsuario = new Usuario(nome, email, endereco, help.dataConverter(data));
		dao.inserirUsuario(novoUsuario);
		help.tempFinalRequisicao();
		
		System.out.println("tempo final da execução foi: " + (" "));
		response.sendRedirect("lista");
		
		

	}
	
	
	
/**
 * 
 * @param request
 * @param response
 * @throws SQLException
 * @throws IOException
 * @throws ParseException
 * @throws ServletException
 */
	public void alterarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException, ServletException {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String data = request.getParameter("dataNascimento");

		if (!help.validarUsuarios(nome, email, endereco, data)) {
			System.out.println("ta entrando aqui");
			mostrarFormularioEdicao(request, response);
			request.setAttribute("mensagem", "Erro ao cadastrar Usuario. Tente novamente!!");
			return;
		}
		System.out.println("saiu do if e entrou aqui");
		Usuario usuarioAtualizado = new Usuario(id, nome, email, endereco, help.dataConverter(data));
		dao.atualizarUsuario(usuarioAtualizado);
		response.sendRedirect("lista");

	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	public void deletarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.deletarUsuario(id);
		response.sendRedirect("lista");

	}

}
