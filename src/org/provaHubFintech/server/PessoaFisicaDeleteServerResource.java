package org.provaHubFintech.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.provaHubFintech.controller.ConnectionProvider;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class PessoaFisicaDeleteServerResource extends ServerResource {
	
	@Post
	public Response remove(Representation representation) {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Request req = getRequest();
			Form form = new Form(req.getEntity());
			String cpf = "",
					nomeComp = "";
			Date dataNasc = new Date(0);
			for (Parameter p : form) {
				if(p.getName().equals("cpf")) {
					cpf = p.getValue();
				}
				if(p.getName().equals("nomeComp")) {
					nomeComp = p.getValue();
				}
				if(p.getName().equals("dataNasc")) {
					dataNasc = Date.valueOf(p.getValue());
				}
				else continue;
			}
			PreparedStatement ps =  c.prepareStatement("DELETE FROM PESSOA_FISICA WHERE cpf = ? AND nomeCompleto = ? AND dataNasc = ?;");
			ps.setString(1, cpf);
			ps.setString(2, nomeComp);
			ps.setDate(3, dataNasc);
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
