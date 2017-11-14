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

public class PessoaJuridicaUpdateServerResource extends ServerResource {

	@Post
	public Response atualiza() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Request req = getRequest();
			String cnpj = (String) req.getAttributes().get("cnpj"),
					cpfNovo = (String) req.getAttributes().get("cnpjNovo"),
					nomeFantasia = (String) req.getAttributes().get("nomeFan"),
					nomeFantasiaNovo = (String) req.getAttributes().get("nomeFanNovo"),
					razSoc = (String) req.getAttributes().get("razSoc"),
					razSocNovo = (String) req.getAttributes().get("razSocNovo");
			PreparedStatement ps =
					c.prepareStatement("UPDATE PESSOA_JURIDICA"
							+ " SET cnpj = ? AND NomeFantasia = ? AND RazaoSocial = ?"
							+ " WHERE cnpj = ? AND NomeFantasia = ? AND RazaoSocial = ?");
			ps.setString(1, cpfNovo);
			ps.setString(2, nomeFantasiaNovo);
			ps.setString(3, razSocNovo);
			ps.setString(4, cnpj);
			ps.setString(5, nomeFantasia);
			ps.setString(6, razSoc);
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
