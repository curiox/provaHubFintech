package org.provaHubFintech.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.provaHubFintech.controller.ConnectionProvider;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class ContaDeleteServerResource extends ServerResource {

	@Post
	public Response remove() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Request req = getRequest();
			String cpf = (String) req.getAttributes().get("cpf"),
					cnpj = (String) req.getAttributes().get("cnpj"),
					nome =  (String) req.getAttributes().get("nome"),
					tipoConta = (String) req.getAttributes().get("tipoConta");
			Date dataNasc = (Date) req.getAttributes().get("data");
			PreparedStatement ps =
					c.prepareStatement("DELETE FROM CONTA"
							+ "WHERE cpf = ? AND cnpj = ? AND DataCriacao = ?"
							+ "AND nome = ? AND tipoConta = ?;");
			ps.setString(1, cpf);
			ps.setString(2, cnpj);
			ps.setDate(3, dataNasc);
			ps.setString(4, nome);
			ps.setString(5, tipoConta);
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
