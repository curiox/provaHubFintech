package org.provaHubFintech.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.provaHubFintech.controller.ConnectionProvider;
import org.provaHubFintech.model.PessoaJuridica;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class PessoaJuridicaServerResource extends ServerResource {

	@Get
	public Response consulta() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PROVA.PESSOA_JURIDICA");
			Response res = getResponse();
			ArrayList<PessoaJuridica> lista = new ArrayList<>();
			while(rs.next()) {
				PessoaJuridica pj = new PessoaJuridica();
				pj.setCnpj(rs.getString("CNPJ"));
				pj.setRazaoSocial(rs.getString("RazaoSocial"));
				pj.setNomeFantasia(rs.getString("NomeFantasia"));
				lista.add(pj);
			}
			String result = "[";
			for(PessoaJuridica p : lista) {
				result += p.toString();
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
			String cnpj = (String) req.getAttributes().get("cnpj"),
					razSoc = (String) req.getAttributes().get("razSoc"),
					nomFan = (String) req.getAttributes().get("nomFan");
			PreparedStatement ps = c.prepareStatement("INSERT INTO PESSOA_JURIDICA VALUES (?, ?, ?);");
			ps.setString(1, cnpj);
			ps.setString(2, razSoc);
			ps.setString(3, nomFan);
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
