package org.provaHubFintech.server;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.restlet.resource.Delete;
import org.restlet.resource.Patch;
import org.restlet.resource.ServerResource;
import org.provaHubFintech.controller.ConnectionProvider;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;

public class ContaServerResource extends ServerResource {
	
	private static int contador = 1;

	@Get
	public void consulta() {
		
	}
	
	@Post
	public void adiciona() {
		Connection c = null;
		Request req = getRequest();
		String nome = (String) req.getAttributes().get("nome"),
				cnpj = (String) req.getAttributes().get("cnpj"),
				cpf = (String) req.getAttributes().get("cpf"),
				tipoConta = (String) req.getAttributes().get("tipoconta");
		Date date = (Date) req.getAttributes().get("data");
		try {
			c = ConnectionProvider.getConnection();
			PreparedStatement ps = c.prepareStatement("INSERT INTO CONTA VALUES (?, ?, ?, ?, ?, ?);");
			ps.setInt(1, contador++);
			ps.setString(2, nome);
			ps.setDate(3, date);
			ps.setString(4, cnpj);
			ps.setString(5, cpf);
			ps.setString(6, tipoConta);
			int rowsAffected = ps.executeUpdate();
			Response resp = getResponse();
			resp.getAttributes().putIfAbsent("rowsAffected", rowsAffected);
			resp.setStatus(Status.SUCCESS_ACCEPTED);
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
