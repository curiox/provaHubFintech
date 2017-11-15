package org.provaHubFintech.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.provaHubFintech.controller.ConnectionProvider;
import org.provaHubFintech.model.PessoaFisica;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class PessoaFisicaServerResource extends ServerResource {
	
	@Get
	public Response consulta() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PROVA.PESSOA_FISICA");
			Response res = getResponse();
			ArrayList<PessoaFisica> lista = new ArrayList<>();
			while(rs.next()) {
				PessoaFisica pf = new PessoaFisica();
				pf.setCpf(rs.getString("cpf"));
				pf.setDataNasc(rs.getDate("dataNasc"));
				pf.setNomeCompleto(rs.getString("nomeCompleto"));
				lista.add(pf);
			}
			String result = "[";
			for(PessoaFisica p : lista) {
				result += p.toString();
				if(lista.size() > 1 && lista.indexOf(p) < lista.size()-1) {
					result += " | ";
				}
			}
			result += "]";
			res.setEntity(result, MediaType.TEXT_PLAIN);
			res.setStatus(Status.SUCCESS_ACCEPTED);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			Response res = getResponse();
			res.setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED);
			return res;
		}
	}
	
	@Post
	public Response adiciona(Representation representation) throws IOException {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Form form = new Form(getRequest().getEntity());
			String cpf = "", nomeComp = "";
			Date dataNasc = new Date(0);
			for (Parameter p : form) {
				if(p.getName().equals("cpf")) cpf = p.getValue();
				if(p.getName().equals("nomeComp")) nomeComp = p.getValue();
				if(p.getName().equals("dataNasc")) dataNasc = Date.valueOf(p.getValue());
				else continue;
			}
			PreparedStatement ps = c.prepareStatement("INSERT INTO PESSOA_FISICA VALUES (?, ?, ?);");
			ps.setString(1, cpf);
			ps.setString(2, nomeComp);
			ps.setDate(3, dataNasc);
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
