package model;

import java.sql.Date;

public class Usuario {
private int id;
private String nome;
private String email;
private String endereco;
private Date dataNascimento;

/*
 * Construtor usado para testes caso os outros dois falhassem
 */
public Usuario() {
	
	
}


public Usuario(String nome, String email, String endereco, Date dataNascimento) {
	super();
	this.nome = nome;
	this.email = email;
	this.endereco = endereco;
	this.dataNascimento = dataNascimento;
}


public Usuario(int id, String nome, String email, String endereco, Date dataNascimento) {
	super();
	this.id = id;
	this.nome = nome;
	this.email = email;
	this.endereco = endereco;
	this.dataNascimento = dataNascimento;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getEndereco() {
	return endereco;
}

public void setEndereco(String endereco) {
	this.endereco = endereco;
}

public Date getDataNascimento() {
	return dataNascimento;
}

public void setDataNascimento(Date dataNascimento) {
	this.dataNascimento = dataNascimento;
}

@Override
public String toString() {
	return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco + ", dataNascimento="
			+ dataNascimento + "]";
}

}
