package org.provaHubFintech.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.provaHubFintech.controller.ConnectionProvider;
import org.provaHubFintech.model.PessoaJuridica;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
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
			String result = "[<br>";
			for(PessoaJuridica p : lista) {
				result += p.toString();
				if(lista.size() > 1 && lista.indexOf(p) < lista.size()-1) {
					result += "<br>";
				}
			}
			result += "<br>]";
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
	public Response adiciona(Representation rep) {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Form form = new Form(getRequest().getEntity());
			String cnpj = "",
					razSoc = "",
					nomFan = "";
			for(Parameter p : form) {
				if(p.getName().equals("cnpj")) cnpj = p.getValue();
				if(p.getName().equals("razsoc")) razSoc = p.getValue();
				if(p.getName().equals("nomfan")) nomFan = p.getValue();
				else continue;
			}
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
