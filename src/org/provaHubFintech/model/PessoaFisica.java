package org.provaHubFintech.model;

import java.sql.Date;

public class PessoaFisica {
	String cpf;
	String nomeCompleto;
	Date dataNasc;
	
	public String toString() {
		return cpf + "," + nomeCompleto + "," + dataNasc;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	
}
