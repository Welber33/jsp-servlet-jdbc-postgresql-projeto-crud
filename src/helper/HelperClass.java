package helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;
import web.UsuarioServlet;

public class HelperClass {

	UsuarioServlet userController = new UsuarioServlet();
	UsuarioDAO dao = new UsuarioDAO();
	
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 * 
	 * public void validarUsuariosNovo(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ParseException {
		if ((request.getParameter("nome") == null || request.getParameter("nome").isEmpty())
				|| (request.getParameter("email") == null || request.getParameter("email").isEmpty())
				|| (request.getParameter("endereco") == null || request.getParameter("endereco").isEmpty())) {
			System.out.println("campos vazios");
			response.sendRedirect("new");
		} else if (validarEmail(request.getParameter("email"))) {
			// userController.inserirUsuario(request, response);
		} else {
			response.sendRedirect("new");
		}

	}
	 */
	

	/**
	 * 
	 * @param nome
	 * @param email
	 * @param endereco
	 * @param data
	 * @return retorna true caso nenhuma das opções seja falsa.
	 */
	public boolean validarUsuarios(String nome, String email, String endereco, String data) {
		if ((nome == null || nome.isEmpty()) || (email == null || email.isEmpty())
				|| (endereco == null || endereco.isEmpty()) || (data == null || data.isEmpty())) {
			System.out.println("campos vazios");
			return false;
		} else if (!validarEmail(email)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public boolean validarEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws ParseException
	 */
	public Date dataConverter(String data) throws ParseException {
		java.sql.Date dataRecebida = null;

		if (data == null || data.isEmpty()) {
			System.out.println("data nula");
		} else {
			DateFormat dataFormatar = new SimpleDateFormat("dd-mm-yyyy");

			java.util.Date data1 = dataFormatar.parse(data);
			dataRecebida = new java.sql.Date(data1.getTime());
		}

		return dataRecebida;

	}

	/* ----------------------------------------------------------------------------------- */
	/**
	 * *****Métodos para pegar o tempo de execução(em teste)*****
	 */
	
	
	/**
	 * 
	 * @return Long tempo inicial de execução
	 */
	public Long tempInicialRequisicao() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 
	 * @return Long tempo final de execução
	 */
	public Long tempFinalRequisicao() {
		return System.currentTimeMillis();
	}

}
