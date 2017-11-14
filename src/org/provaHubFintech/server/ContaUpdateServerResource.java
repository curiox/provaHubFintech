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

public class ContaUpdateServerResource extends ServerResource {

	@Post
	public Response atualiza() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Request req = getRequest();
			String nome = (String) req.getAttributes().get("nome"),
					nomeNovo = (String) req.getAttributes().get("nomeNovo"),
					cnpj = (String) req.getAttributes().get("cnpj"),
					cnpjNovo = (String) req.getAttributes().get("cnpjNovo"),
					cpf = (String) req.getAttributes().get("cpf"),
					cpfNovo = (String) req.getAttributes().get("cpfNovo"),
					tipoConta = (String) req.getAttributes().get("tipoConta"),
					tipoContaNovo = (String) req.getAttributes().get("tipoContaNovo");
			Date dataNova = (Date) req.getAttributes().get("data");
			PreparedStatement ps =
					c.prepareStatement("UPDATE CONTA"
							+ " SET nome = ? AND DataCriacao = ? AND CNPJ = ?"
							+ " AND CPF = ? AND TipoConta = ?"
							+ " WHERE nome = ? AND CNPJ = ?"
							+ " AND CPF = ? AND TipoConta = ?");
			ps.setString(1, nomeNovo);
			ps.setDate(2, dataNova);
			ps.setString(3, cnpjNovo);
			ps.setString(4, cpfNovo);
			ps.setString(5, tipoContaNovo);
			ps.setString(6, nome);
			ps.setString(7, cnpj);
			ps.setString(8, cpf);
			ps.setString(9, tipoConta);
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
