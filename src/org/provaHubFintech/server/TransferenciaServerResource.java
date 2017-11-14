package org.provaHubFintech.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.provaHubFintech.controller.ConnectionProvider;
import org.provaHubFintech.model.Transferencia;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class TransferenciaServerResource extends ServerResource {
	
	@Get
	public Response consulta() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PROVA.TRANSFERENCIA");
			Response res = getResponse();
			ArrayList<Transferencia> lista = new ArrayList<>();
			while(rs.next()) {
				Transferencia trans = new Transferencia();
				trans.setIdContaOrigem(rs.getInt("idContaOrigem"));
				trans.setIdContaDestino(rs.getInt("idContaDestino"));
				trans.setQuantia(rs.getFloat("quantia"));
				trans.setCodAporte(rs.getString("codAporte"));
				lista.add(trans);
			}
			String result = "[";
			for (Transferencia t : lista) {
				result += t.toString();
			}
			result += "]";
			res.setEntity(result, MediaType.TEXT_PLAIN);
			res.setStatus(Status.SUCCESS_ACCEPTED);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			Response res = getResponse();
			res.setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED);
			return res;
		}
	}
	
	@Post
	public Response adiciona() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Request req = getRequest();
			int cntOrigem = (int) req.getAttributes().get("origem"),
					cntDestino = (int) req.getAttributes().get("destino");
			float quantia = (float) req.getAttributes().get("quantia");
			String aporte = (String) req.getAttributes().get("aporte");
			PreparedStatement ps = c.prepareStatement("INSERT INTO TRANSFERENCIA VALUES (?, ?, ?, ?);");
			ps.setInt(1, cntOrigem);
			ps.setInt(2, cntDestino);
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
