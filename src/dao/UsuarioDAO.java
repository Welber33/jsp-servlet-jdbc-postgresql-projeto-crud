package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.print.PrintException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.Conexao;
import model.Usuario;

public class UsuarioDAO {
	private Connection conexao;
	private SqlScripts sql = new SqlScripts();

	public UsuarioDAO() {
		this.conexao = Conexao.getConnection();
	}
	
	
	/*
	 * método para inserir o usuario no banco de dados onde ele recebe como parâmetro o objeto usuario passado
	 * pelo servlet e starta uma conexão e insere as informações do usuário no banco.
	 */
	public void inserirUsuario(Usuario usuario) {
		try {
			PreparedStatement ps = conexao.prepareStatement(sql.inserirUsuario());
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getEndereco());
			ps.setDate(4, usuario.getDataNascimento());

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	

	public Usuario selecionarUsuario(int id) {
		Usuario usuario1 = null;

		try {
			PreparedStatement ps = conexao.prepareStatement(sql.selecionarUserbyId());
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String endereco = rs.getString("endereco");
				Date data = rs.getDate("data_nascimento");

				usuario1 = new Usuario(id, nome, email, endereco, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario1;
	}

	
	
	
	
	/*
	 * método de listagem do usuario onde pega todas informações do banco 
	 * e imprime na tabela da pagina lista-usuarios.jsp
	 */
	public List<Usuario> selecionarTodosUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			PreparedStatement ps = conexao.prepareStatement(sql.selecionarUsuarios());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String endereco = rs.getString("endereco");
				Date data = rs.getDate("data_nascimento");

				usuarios.add(new Usuario(id, nome, email, endereco, data));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	
	
	/*
	 * método booleano para deletar o usuario a checagem é feita assim como o metodo atualizarUsuario
	 */
	public boolean deletarUsuario(int id) throws SQLException {
		boolean rowDeleted = false;

		try {
			PreparedStatement ps = conexao.prepareStatement(sql.deletarusuario());
			ps.setInt(1, id);

			rowDeleted = ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowDeleted;
	}
	

	
	
	/*
	 * método booleano que identifica se o usuário existe antes de fazer a atualização 
	 * checando pelo ID do usuário no banco
	 */
	public boolean atualizarUsuario(Usuario usuario2) throws SQLException {
		boolean atualizarRows = false;

		try {
			PreparedStatement ps = conexao.prepareStatement(sql.atualizarUsuario());

			ps.setString(1, usuario2.getNome());
			ps.setString(2, usuario2.getEmail());
			ps.setString(3, usuario2.getEndereco());
			ps.setDate(4, usuario2.getDataNascimento());
			ps.setInt(5, usuario2.getId());

			atualizarRows = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return atualizarRows;
	}


}
