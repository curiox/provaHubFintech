package org.provaHubFintech.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.provaHubFintech.controller.ConnectionProvider;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class PessoaJuridicaDeleteServerResource extends ServerResource {

	@Post
	public Response remove() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Request req = getRequest();
			String cnpj = (String) req.getAttributes().get("cnpj"),
					razSoc = (String) req.getAttributes().get("razSoc"),
					nomFan = (String) req.getAttributes().get("nomFan");
			PreparedStatement ps =  c.prepareStatement("DELETE FROM PESSOA_JURIDICA WHERE cnpj = ? AND RazaoSocial = ? AND NomeFantasia = ?;");
			ps.setString(1, cnpj);
			ps.setString(2, razSoc);
			ps.setString(3, nomFan);
			int rowsAffected = ps.executeUpdate();
			Response res = getResponse();
			res.setEntity(rowsAffected+" linha(s) afetada(s)", MediaType.TEXT_PLAIN);
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
