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

public class PessoaFisicaUpdateServerResource extends ServerResource {
	
	@Post
	public Response atualiza() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Request req = getRequest();
			String cpf = (String) req.getAttributes().get("cpf"),
					cpfNovo = (String) req.getAttributes().get("cpfNovo"),
					nomeComp = (String) req.getAttributes().get("nomeComp"),
					nomeCompNovo = (String) req.getAttributes().get("nomeCompNovo");
			Date dataNasc = (Date) req.getAttributes().get("dataNasc"),
					dataNascNovo = (Date) req.getAttributes().get("dataNascNovo");
			PreparedStatement ps =
					c.prepareStatement("UPDATE PESSOA_FISICA"
							+ " SET cpf = ? AND nomeCompleto = ? AND dataNasc = ?"
							+ " WHERE cpf = ? AND nomeCompleto = ? AND dataNasc = ?");
			ps.setString(1, cpfNovo);
			ps.setString(2, nomeCompNovo);
			ps.setDate(3, dataNascNovo);
			ps.setString(4, cpf);
			ps.setString(5, nomeComp);
			ps.setDate(6, dataNasc);
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
