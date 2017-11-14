package org.provaHubFintech.server;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.provaHubFintech.controller.ConnectionProvider;
import org.provaHubFintech.model.Conta;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;

public class ContaServerResource extends ServerResource {
	
	private static int contador = 1;

	@Get
	public Response consulta() {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PROVA.CONTA");
			Response res = getResponse();
			ArrayList<Conta> lista = new ArrayList<>();
			while(rs.next()) {
				Conta co = new Conta();
				co.setIdConta(rs.getInt("idConta"));
				co.setNome(rs.getString("Nome"));
				co.setCnpj(rs.getString("CNPJ"));
				co.setCpf(rs.getString("CPF"));
				co.setDataCriacao(rs.getDate("DataCriacao"));
				co.setTipoConta(rs.getString("tipoConta"));
				co.setAtividade(rs.getInt("atividade"));
				lista.add(co);
			}
			String result = "[";
			for(Conta conta : lista) {
				result += conta.toString();
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
			String nome = (String) req.getAttributes().get("nome"),
					cnpj = (String) req.getAttributes().get("cnpj"),
					cpf = (String) req.getAttributes().get("cpf"),
					tipoConta = (String) req.getAttributes().get("tipoconta");
			Date date = (Date) req.getAttributes().get("data");
			PreparedStatement ps = c.prepareStatement("INSERT INTO CONTA VALUES (?, ?, ?, ?, ?, ?, 1);");
			ps.setInt(1, contador++);
			ps.setString(2, nome);
			ps.setDate(3, date);
			ps.setString(4, cnpj);
			ps.setString(5, cpf);
			ps.setString(6, tipoConta);
			int rowsAffected = ps.executeUpdate();
			Response resp = getResponse();
			resp.setEntity(rowsAffected + " linha(s) afetada(s)",MediaType.TEXT_PLAIN);
			resp.setStatus(Status.SUCCESS_ACCEPTED);
			return resp;
		} catch (SQLException e) {
			e.printStackTrace();
			Response res = getResponse();
			res.setStatus(Status.CLIENT_ERROR_EXPECTATION_FAILED);
			return res;
		}
	}
	
}
