package org.provaHubFintech.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.provaHubFintech.controller.ConnectionProvider;
import org.provaHubFintech.model.Transferencia;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class TransferenciaServerResource extends ServerResource {

	private int contador = 0;
	
	@Get
	public void consulta() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PROVA.TRANSFERENCIA");
			Response res = getResponse();
			while(rs.next()) {
				Transferencia trans = new Transferencia();
				trans.setIdContaOrigem(rs.getInt("idContaOrigem"));
				trans.setIdContaDestino(rs.getInt("idContaDestino"));
				trans.setQuantia(rs.getFloat("quantia"));
				res.getAttributes().putIfAbsent("transferencia" + contador++, trans);
			}
			res.setStatus(Status.SUCCESS_ACCEPTED);
		} catch (SQLException e) {
			e.printStackTrace();
			Response res = getResponse();
			res.setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED);
			res.abort();
		}
	}
	
	@Post
	public void adiciona() {
		Connection c = null;
		Request req = getRequest();
		int cntOrigem = (int) req.getAttributes().get("origem"),
				cntDestino = (int) req.getAttributes().get("destino");
		float quantia = (float) req.getAttributes().get("quantia");
		try {
			c = ConnectionProvider.getConnection();
			PreparedStatement ps = c.prepareStatement("INSERT INTO TRANSFERENCIA VALUES (?, ?, ?);");
			ps.setInt(1, cntOrigem);
			ps.setInt(2, cntDestino);
			ps.setFloat(3, quantia);
			int rowsAffected = ps.executeUpdate();
			Response res = getResponse();
			res.getAttributes().putIfAbsent("rowsAffected", rowsAffected);
			res.setStatus(Status.SUCCESS_ACCEPTED);
		} catch (SQLException e) {
			e.printStackTrace();
			Response res = getResponse();
			res.setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED);
			res.abort();
		}
	}
	
	@Delete
	public void remove() {
		Connection c = null;
		Request req = getRequest();
		int origem = (int) req.getAttributes().get("origem"),
				destino = (int) req.getAttributes().get("destino");
		float quantia = (float) req.getAttributes().get("quantia");
		try {
			c = ConnectionProvider.getConnection();
			PreparedStatement ps =  c.prepareStatement("DELETE FROM TRANSFERENCIA WHERE idContaOrigem = ? AND idContaDestino = ? AND quantia = ?;");
			ps.setInt(1, origem);
			ps.setInt(2, destino);
			ps.setFloat(3, quantia);
			int rowsAffected = ps.executeUpdate();
			Response res = getResponse();
			res.getAttributes().putIfAbsent("rowsAffected", rowsAffected);
			res.setStatus(Status.SUCCESS_ACCEPTED);
		} catch (SQLException e) {
			e.printStackTrace();
			Response res = getResponse();
			res.setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED);
			res.abort();
		}
	}
	
	@Put
	public void atualiza() {
		Connection c = null;
		Request req = getRequest();
		int origem = (int) req.getAttributes().get("origem"),
				origemNova = (int) req.getAttributes().get("origemNova"),
				destino = (int) req.getAttributes().get("destino"),
				destinoNovo = (int) req.getAttributes().get("destinoNovo");
		float quantia = (float) req.getAttributes().get("quantia"),
				quantiaNova = (float) req.getAttributes().get("quantiaNova");
		try {
			c = ConnectionProvider.getConnection();
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
			res.getAttributes().putIfAbsent("rowsAffected", rowsAffected);
			res.setStatus(Status.SUCCESS_ACCEPTED);
		} catch (SQLException e) {
			e.printStackTrace();
			Response res = getResponse();
			res.setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED);
			res.abort();
		}
	}
	
}
