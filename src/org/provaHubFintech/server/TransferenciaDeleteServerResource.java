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

public class TransferenciaDeleteServerResource extends ServerResource {
	
	@Post
	public Response remove() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Request req = getRequest();
			int origem = (int) req.getAttributes().get("origem"),
					destino = (int) req.getAttributes().get("destino");
			float quantia = (float) req.getAttributes().get("quantia");
			String aporte = (String) req.getAttributes().get("aporte");
			PreparedStatement ps =  c.prepareStatement("DELETE FROM TRANSFERENCIA WHERE idContaOrigem = ? AND idContaDestino = ? AND quantia = ?;");
			ps.setInt(1, origem);
			ps.setInt(2, destino);
			ps.setFloat(3, quantia);
			ps.setString(4, aporte);
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
