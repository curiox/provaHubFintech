package org.provaHubFintech.server;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.provaHubFintech.controller.ConnectionProvider;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Patch;
import org.restlet.resource.ServerResource;

public class PessoaFisicaServerResource extends ServerResource {
	
	@Get
	public void consulta() {
		
	}
	
	@Post
	public void adiciona() {
		Connection c = null;
		Request req = getRequest();
		String cpf = (String) req.getAttributes().get("cpf"),
				nomeComp = (String) req.getAttributes().get("nomeComp");
		Date dataNasc = (Date) req.getAttributes().get("dataNasc");
		try {
			c = ConnectionProvider.getConnection();
			PreparedStatement ps = c.prepareStatement("INSERT INTO PESSOA_FISICA VALUES (?, ?, ?);");
			ps.setString(1, cpf);
			ps.setString(2, nomeComp);
			ps.setDate(3, dataNasc);
			int rowsAffected = ps.executeUpdate();
			Response res = getResponse();
			res.getAttributes().putIfAbsent("rowsAffected", rowsAffected);
			res.setStatus(Status.SUCCESS_ACCEPTED);
		} catch (SQLException e) {
			e.printStackTrace();
			Response res = getResponse();
			res.setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED);
			res.abort();
		}
	}
	
	@Delete
	public void remove() {
		
	}
	
	@Patch
	public void atualiza() {
		
	}

}
