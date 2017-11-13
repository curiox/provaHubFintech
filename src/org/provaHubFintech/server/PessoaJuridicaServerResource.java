package org.provaHubFintech.server;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.provaHubFintech.controller.ConnectionProvider;
import org.provaHubFintech.model.PessoaJuridica;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class PessoaJuridicaServerResource extends ServerResource {
	
	private int contador = 1;

	@Get
	public void consulta() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PROVA.PESSOA_JURIDICA");
			Response res = getResponse();
			while(rs.next()) {
				PessoaJuridica pf = new PessoaJuridica();
				pf.setCnpj(rs.getString("CNPJ"));
				pf.setRazaoSocial(rs.getString("RazaoSocial"));
				pf.setNomeFantasia(rs.getString("NomeFantasia"));
				res.getAttributes().putIfAbsent("clienteJ" + contador++, pf);
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
		String cnpj = (String) req.getAttributes().get("cnpj"),
				razSoc = (String) req.getAttributes().get("razSoc"),
				nomFan = (String) req.getAttributes().get("nomFan");
		try {
			c = ConnectionProvider.getConnection();
			PreparedStatement ps = c.prepareStatement("INSERT INTO PESSOA_JURIDICA VALUES (?, ?, ?);");
			ps.setString(1, cnpj);
			ps.setString(2, razSoc);
			ps.setString(3, nomFan);
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
		String cnpj = (String) req.getAttributes().get("cnpj"),
				razSoc = (String) req.getAttributes().get("razSoc"),
				nomFan = (String) req.getAttributes().get("nomFan");
		try {
			c = ConnectionProvider.getConnection();
			PreparedStatement ps =  c.prepareStatement("DELETE FROM PESSOA_JURIDICA WHERE cnpj = ? AND RazaoSocial = ? AND NomeFantasia = ?;");
			ps.setString(1, cnpj);
			ps.setString(2, razSoc);
			ps.setString(3, nomFan);
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
		String cnpj = (String) req.getAttributes().get("cnpj"),
				cpfNovo = (String) req.getAttributes().get("cnpjNovo"),
				nomeFantasia = (String) req.getAttributes().get("nomeFan"),
				nomeFantasiaNovo = (String) req.getAttributes().get("nomeFanNovo"),
				razSoc = (String) req.getAttributes().get("razSoc"),
				razSocNovo = (String) req.getAttributes().get("razSocNovo");
		try {
			c = ConnectionProvider.getConnection();
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
