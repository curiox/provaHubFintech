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

public class TransferenciaUpdateServerResource extends ServerResource {

	@Post
	public Response atualiza() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Request req = getRequest();
			int origem = (int) req.getAttributes().get("origem"),
					origemNova = (int) req.getAttributes().get("origemNova"),
					destino = (int) req.getAttributes().get("destino"),
					destinoNovo = (int) req.getAttributes().get("destinoNovo");
			float quantia = (float) req.getAttributes().get("quantia"),
					quantiaNova = (float) req.getAttributes().get("quantiaNova");
			PreparedStatement ps =
					c.prepareStatement("UPDATE TRANSFERENCIA"
							+ " SET idContaOrigem = ? AND idContaDestino = ? AND quantia = ?"
							+ " WHERE idContaOrigem = ? AND idContaDestino = ? AND quantia = ?");
			ps.setInt(1, origemNova);
			ps.setInt(2, destinoNovo);
			ps.setFloat(3, quantiaNova);
			ps.setInt(4, origem);
			ps.setInt(5, destino);
			ps.setFloat(6, quantia);
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
