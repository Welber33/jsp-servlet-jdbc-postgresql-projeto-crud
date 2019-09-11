package dao;

public class SqlScripts {

	public String inserirUsuario() {
		return "insert into usuario(nome,email,endereco,data_nascimento) values(?,?,?,?);";
	}
	
	public String selecionarUserbyId() {
		return "select id,nome,email,endereco,data_nascimento from usuario where id =?;";
	} 
	
	public String selecionarUsuarios() {
		return "select * from usuario;";
	}
	
	public String deletarusuario() {
		return "delete from usuario where id=?;";
	}
	
	public String atualizarUsuario(){
		return "update usuario set nome=?, email=?, endereco=?, data_nascimento=? where id=?;";
	}
}
