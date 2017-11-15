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
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Status;
import org.restlet.representation.Representation;

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
				if(lista.size() > 1 && lista.indexOf(conta) < lista.size()-1) {
					result += " | ";
				}
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
	public Response adiciona(Representation representation) {
		Connection c = null;
		try {
			c = ConnectionProvider.getConnection();
			Form form = new Form(getRequest().getEntity());
			String nome = "", cnpj = "", cpf = "", tipoConta = "";
			Date date = new Date(0);
			for (Parameter p : form) {
				if(p.getName().equals("nome")) nome = p.getValue();
				if(p.getName().equals("cnpj")) cnpj = p.getValue().length() > 0 ? p.getValue() : null;
				if(p.getName().equals("cpf")) cpf = p.getValue().length() > 0 ? p.getValue(): null;
				if(p.getName().equals("tipoconta")) tipoConta = p.getValue();
				if(p.getName().equals("date")) date = Date.valueOf(p.getValue());
				else continue;
			}
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
