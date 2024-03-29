package org.provaHubFintech.server;

import java.sql.Connection;
import java.sql.Date;
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

public class ContaDeleteServerResource extends ServerResource {

	@Post
	public Response remove(Representation representation) {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Form form = new Form(getRequest().getEntity());
			int idconta = 0;
			String cpf = "",
					cnpj = "",
					nome =  "",
					tipoConta = "";
			Date data = null;
			for(Parameter p : form) {
				if(p.getName().equals("idconta")) idconta = Integer.parseInt(p.getValue());
				if(p.getName().equals("cpf")) cpf = p.getValue();
				if(p.getName().equals("cnpj")) cnpj = p.getValue();
				if(p.getName().equals("nome")) nome = p.getValue();
				if(p.getName().equals("tipoconta")) tipoConta = p.getValue();
				if(p.getName().equals("date")) data = Date.valueOf(p.getValue());
				else continue;
			}
			PreparedStatement ps;
			if(cpf.equals("")) {
				ps = c.prepareStatement("DELETE FROM CONTA "
						+ "WHERE idConta = ? AND cpf IS NULL AND cnpj = ? AND DataCriacao = ? "
						+ "AND nome = ? AND tipoConta = ?;");
				ps.setInt(1, idconta);
				ps.setString(2, cnpj);
				ps.setDate(3, data);
				ps.setString(4, nome);
				ps.setString(5, tipoConta);
			} else if (cnpj.equals("")) {
				ps = c.prepareStatement("DELETE FROM CONTA "
						+ "WHERE idConta = ? AND cnpj IS NULL and cpf = ? AND DataCriacao = ? "
						+ "AND nome = ? AND tipoConta = ?;");
				ps.setInt(1, idconta);
				ps.setString(2, cpf);
				ps.setDate(3, data);
				ps.setString(4, nome);
				ps.setString(5, tipoConta);
			} else {
			ps =
					c.prepareStatement("DELETE FROM CONTA "
							+ "WHERE idConta = ? AND cpf = ? AND cnpj = ? AND DataCriacao = ?"
							+ " AND nome = ? AND tipoConta = ?;");
			ps.setInt(1, idconta);
			ps.setString(2, cpf);
			ps.setString(3, cnpj);
			ps.setDate(4, data);
			ps.setString(5, nome);
			ps.setString(6, tipoConta);
			}
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
