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
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class PessoaFisicaUpdateServerResource extends ServerResource {
	
	@Post
	public Response atualiza() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Request req = getRequest();
			Form form = new Form(req.getEntity());
			String cpf = "",
					cpfNovo = "",
					nomeComp = "",
					nomeCompNovo = "";
			Date dataNasc = new Date(0),
					dataNascNovo = new Date(0);
			for(Parameter p : form) {
				if(p.getName().equals("cpf")) cpf = p.getValue();
				if(p.getName().equals("cpfNovo")) cpfNovo = p.getValue();
				if(p.getName().equals("nomeComp")) nomeComp = p.getValue();
				if(p.getName().equals("nomeCompNovo")) nomeCompNovo = p.getValue();
				if(p.getName().equals("dataNasc")) dataNasc = Date.valueOf(p.getValue());
				if(p.getName().equals("dataNascNovo")) dataNascNovo = Date.valueOf(p.getValue());
			}
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
