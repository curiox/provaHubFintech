package org.provaHubFintech.model;

import java.sql.Date;

public class Conta {
	int idConta;
	String nome;
	Date dataCriacao;
	String cnpj;
	String cpf;
	String tipoConta;
	int atividade;
	
	public String toString() {
		return idConta + "," + nome + "," + dataCriacao + "," + cnpj + "," + cpf + "," + tipoConta + "," + atividade;
	}
	public int getIdConta() {
		return idConta;
	}
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public int getAtividade() {
		return atividade;
	}
	public void setAtividade(int atividade) {
		this.atividade = atividade;
	}
}
