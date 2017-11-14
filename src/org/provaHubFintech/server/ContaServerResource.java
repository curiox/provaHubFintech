package org.provaHubFintech.server;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.Delete;
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
		Request req = getRequest();
		String nome = (String) req.getAttributes().get("nome"),
				cnpj = (String) req.getAttributes().get("cnpj"),
				cpf = (String) req.getAttributes().get("cpf"),
				tipoConta = (String) req.getAttributes().get("tipoconta");
		Date date = (Date) req.getAttributes().get("data");
		try {
			c = ConnectionProvider.getConnection();
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
	
	@Delete
	public Response remove() {
		Connection c = null;
		Request req = getRequest();
		String cpf = (String) req.getAttributes().get("cpf"),
				cnpj = (String) req.getAttributes().get("cnpj"),
				nome =  (String) req.getAttributes().get("nome"),
				tipoConta = (String) req.getAttributes().get("tipoConta");
		Date dataNasc = (Date) req.getAttributes().get("data");
		try {
			c = ConnectionProvider.getConnection();
			PreparedStatement ps =
					c.prepareStatement("DELETE FROM CONTA"
							+ "WHERE cpf = ? AND cnpj = ? AND DataCriacao = ?"
							+ "AND nome = ? AND tipoConta = ?;");
			ps.setString(1, cpf);
			ps.setString(2, cnpj);
			ps.setDate(3, dataNasc);
			ps.setString(4, nome);
			ps.setString(5, tipoConta);
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
	
	@Put
	public Response atualiza() {
		Connection c = null;
		Request req = getRequest();
		String nome = (String) req.getAttributes().get("nome"),
				nomeNovo = (String) req.getAttributes().get("nomeNovo"),
				cnpj = (String) req.getAttributes().get("cnpj"),
				cnpjNovo = (String) req.getAttributes().get("cnpjNovo"),
				cpf = (String) req.getAttributes().get("cpf"),
				cpfNovo = (String) req.getAttributes().get("cpfNovo"),
				tipoConta = (String) req.getAttributes().get("tipoConta"),
				tipoContaNovo = (String) req.getAttributes().get("tipoContaNovo");
		Date dataNova = (Date) req.getAttributes().get("data");
		try {
			c = ConnectionProvider.getConnection();
			PreparedStatement ps =
					c.prepareStatement("UPDATE CONTA"
							+ " SET nome = ? AND DataCriacao = ? AND CNPJ = ?"
							+ " AND CPF = ? AND TipoConta = ?"
							+ " WHERE nome = ? AND CNPJ = ?"
							+ " AND CPF = ? AND TipoConta = ?");
			ps.setString(1, nomeNovo);
			ps.setDate(2, dataNova);
			ps.setString(3, cnpjNovo);
			ps.setString(4, cpfNovo);
			ps.setString(5, tipoContaNovo);
			ps.setString(6, nome);
			ps.setString(7, cnpj);
			ps.setString(8, cpf);
			ps.setString(9, tipoConta);
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
