package org.provaHubFintech.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.provaHubFintech.controller.ConnectionProvider;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class ContaUpdateServerResource extends ServerResource {

	@Post
	public Response atualiza(Representation representation) {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Form form = new Form(getRequest().getEntity());
			String nome = "",
					nomeNovo = "",
					cnpj = "",
					cnpjNovo = "",
					cpf = "",
					cpfNovo = "",
					tipoConta = "",
					tipoContaNovo = "";
			Date data = new Date(0), dataNova = new Date(0);
			PreparedStatement ps;
			for(Parameter p : form) {
				if(p.getName().equals("nome")) nome = p.getValue();
				if(p.getName().equals("nomenovo")) nomeNovo = p.getValue();
				if(p.getName().equals("cnpj")) cnpj = p.getValue();
				if(p.getName().equals("cnpjnovo")) cnpjNovo = p.getValue();
				if(p.getName().equals("cpf")) cpf = p.getValue();
				if(p.getName().equals("cpfnovo")) cpfNovo = p.getValue();
				if(p.getName().equals("tipoconta")) tipoConta = p.getValue();
				if(p.getName().equals("tipocontanovo")) tipoContaNovo = p.getValue();
				if(p.getName().equals("date")) data = Date.valueOf(p.getValue());
				if(p.getName().equals("datenova")) dataNova = Date.valueOf(p.getValue());
			}
			if(cnpj.equals("")) {
				ps = c.prepareStatement("UPDATE CONTA SET nome = ? AND DataCriacao = ? AND CPF = ? AND TipoConta = ? "
						+ "WHERE nome = ? AND DataCriacao = ? AND CPF = ? AND TipoConta = ? AND CNPJ IS NULL;");
				ps.setString(1, nomeNovo);
				ps.setDate(2, dataNova);
				ps.setString(3, cpfNovo);
				ps.setString(4, tipoContaNovo);
				ps.setString(5, nome);
				ps.setDate(6, data);
				ps.setString(7, cpf);
				ps.setString(8, tipoConta);
			}
			else if(cpf.equals("")) {
				ps = c.prepareStatement("UPDATE CONTA SET nome = ? AND DataCriacao = ? AND CNPJ = ? AND TipoConta = ? "
						+ "WHERE nome = ? AND DataCriacao = ? AND CNPJ = ? AND TipoConta = ? AND CPF IS NULL;");
				ps.setString(1, nomeNovo);
				ps.setDate(2, dataNova);
				ps.setString(3, cnpjNovo);
				ps.setString(4, tipoContaNovo);
				ps.setString(5, nome);
				ps.setDate(6, data);
				ps.setString(7, cnpj);
				ps.setString(8, tipoConta);
			} else {
				ps = c.prepareStatement("UPDATE CONTA"
						+ " SET nome = ? AND DataCriacao = ? AND CNPJ = ?"
						+ " AND CPF = ? AND TipoConta = ?"
						+ " WHERE nome = ? AND DataCriacao = ? AND CNPJ = ?"
						+ " AND CPF = ? AND TipoConta = ?");
				ps.setString(1, nomeNovo);
				ps.setDate(2, dataNova);
				ps.setString(3, cnpjNovo);
				ps.setString(4, cpfNovo);
				ps.setString(5, tipoContaNovo);
				ps.setString(6, nome);
				ps.setDate(7, data);
				ps.setString(8, cnpj);
				ps.setString(9, cpf);
				ps.setString(10, tipoConta);
			}
			int rowsAffected = ps.executeUpdate();
			Response res = getResponse();
			res.setEntity(rowsAffected + " linha(s) afetada(s)", MediaType.TEXT_PLAIN);
			res.setStatus(Status.SUCCESS_ACCEPTED);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			Response res = getResponse();
			res.setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED);
			return res;
		}
	}
	
}
