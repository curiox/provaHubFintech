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

public class TransferenciaDeleteServerResource extends ServerResource {
	
	@Post
	public Response remove(Representation rep) {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Form form = new Form(getRequest().getEntity());
			int origem = 0,
					destino = 0;
			float quantia = 0;
			String aporte = null;
			for(Parameter p : form) {
				if(p.getName().equals("cntOrigem")) origem = Integer.parseInt(p.getValue());
				if(p.getName().equals("cntDestino")) destino = Integer.parseInt(p.getValue());
				if(p.getName().equals("quantia")) quantia = Float.parseFloat(p.getValue());
				if(p.getName().equals("aporte")) aporte = p.getValue().equals("") ? "" : p.getValue();
				else continue;
			}
			PreparedStatement ps;
			if(aporte.equals("")) {
				ps = c.prepareStatement("DELETE FROM TRANSFERENCIA WHERE idContaOrigem = ? AND idContaDestino = ? AND quantia = ? AND codAporte IS NULL;");
				ps.setInt(1, origem);
				ps.setInt(2, destino);
				ps.setFloat(3, quantia);
			}
			else {
				ps =  c.prepareStatement("DELETE FROM TRANSFERENCIA WHERE idContaOrigem = ? AND idContaDestino = ? AND quantia = ? AND codAporte = ?;");
				ps.setInt(1, origem);
				ps.setInt(2, destino);
				ps.setFloat(3, quantia);
				ps.setString(4, aporte);
			}
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
