package org.provaHubFintech.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.provaHubFintech.controller.ConnectionProvider;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class PessoaJuridicaUpdateServerResource extends ServerResource {

	@Post
	public Response atualiza(Representation rep) {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Form form = new Form(getRequest().getEntity());
			String cnpj = "",
					cnpjNovo = "",
					nomFan = "",
					nomFanNovo = "",
					razSoc = "",
					razSocNovo = "";
			for(Parameter p : form) {
				if(p.getName().equals("cnpj")) cnpj = p.getValue();
				if(p.getName().equals("cnpjnovo")) cnpjNovo = p.getValue();
				if(p.getName().equals("razsoc")) razSoc = p.getValue();
				if(p.getName().equals("razsocnovo")) razSocNovo = p.getValue();
				if(p.getName().equals("nomfan")) nomFan = p.getValue();
				if(p.getName().equals("nomfannovo")) nomFanNovo = p.getValue();
				else continue;
			}
			PreparedStatement ps =
					c.prepareStatement("UPDATE PESSOA_JURIDICA"
							+ " SET cnpj = ? AND NomeFantasia = ? AND RazaoSocial = ?"
							+ " WHERE cnpj = ? AND NomeFantasia = ? AND RazaoSocial = ?;");
			ps.setString(1, cnpjNovo);
			ps.setString(2, nomFanNovo);
			ps.setString(3, razSocNovo);
			ps.setString(4, cnpj);
			ps.setString(5, nomFan);
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
