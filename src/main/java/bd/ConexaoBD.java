package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConexaoBD {
	private Connection conn = null;
	private Statement stm = null;
	private ResultSet rs = null;

	public Connection conectar() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("");//insira sua conexao com o banco de dados
			System.out.println("Conectou no banco de dados.");
		} catch (SQLException ex) {
			System.out.println("Erro: Não conseguiu conectar no BD.");
		} catch (ClassNotFoundException ex) {
			System.out.println("Erro: Não encontrou o driver do BD.");
		}
		return conn;
	}

	public void inserir(String rawResult) {
		ConexaoBD conexao = new ConexaoBD();
		Connection conn = conexao.conectar();
		if (rawResult == null || rawResult.isEmpty()) {
			System.out.printf("Sem dados%n");
			return;
		}
		try {

			JSONObject json = new JSONObject(rawResult);
			JSONArray dias = json.getJSONArray("days");

			for (int i = 0; i < dias.length(); i++) {

				JSONObject diasvalores = dias.getJSONObject(i);
				String adicionar = "INSERT INTO public.dados VALUES ('" + json.getString("resolvedAddress") + "', '"
						+ diasvalores.getString("datetime") + "'," + diasvalores.getDouble("tempmax") + ","
						+ diasvalores.getDouble("tempmin") + "," + diasvalores.getDouble("temp") + ","
						+ diasvalores.getDouble("feelslikemax") + "," + diasvalores.getDouble("feelslikemin") + ","
						+ diasvalores.getDouble("feelslike") + "," + diasvalores.getDouble("dew") + ","
						+ diasvalores.getDouble("humidity") + "," + diasvalores.getDouble("precip") + ","
						+ diasvalores.getDouble("precipprob") + "," + diasvalores.getDouble("precipcover") + ","
						+ diasvalores.getDouble("snow") + "," + diasvalores.getDouble("snowdepth") + ","
						+ diasvalores.getDouble("windgust") + "," + diasvalores.getDouble("windspeed") + ","
						+ diasvalores.getDouble("winddir") + "," + diasvalores.getDouble("pressure") + ","
						+ diasvalores.getDouble("cloudcover") + "," + diasvalores.getDouble("visibility") + ","
						+ diasvalores.getDouble("solarradiation") + "," + diasvalores.getDouble("solarenergy") + ","
						+ diasvalores.getInt("uvindex") + "," + diasvalores.getInt("severerisk") + ", '"
						+ diasvalores.getString("sunrise") + "', '" + diasvalores.getString("sunset") + "',"
						+ diasvalores.getDouble("moonphase") + ", '" + diasvalores.getString("conditions") + "', '"
						+ diasvalores.getString("description") + "', '" + diasvalores.getString("icon") + "')";

				Statement stm = conn.createStatement();
				System.out.println(adicionar);
				stm.execute(adicionar);
				System.out.println("Adicionou ao BD.");
			}
		} catch (SQLException ex) {
			System.out.println("Não conseguiu adicionar ao BD.");
		} finally {
			conexao.desconectar();
		}

	}

	public ResultSet executarConsulta(String consulta) {
		conn = conectar();
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(consulta);
		} catch (SQLException ex) {
			System.out.println("Não conseguiu executar a consulta\n" + consulta);
			// Caso ocorra algum erro desconecta do banco de dados.
			desconectar();
		}

		return rs;
	}

	public void desconectar() {
		fecharResultSet(this.rs);
		fecharStatement(this.stm);
		fecharConnection(this.conn);
	}

	public void fecharConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Desconectou do banco de dados.");
			}
		} catch (SQLException ex) {
			System.out.println("Nao conseguiu desconectar do BD.");
		}
	}

	public void fecharStatement(Statement stm) {
		try {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao fechar o procedimento de consulta.");
		}
	}

	public void fecharResultSet(ResultSet resultado) {
		try {
			if (resultado != null && !resultado.isClosed()) {
				resultado.close();
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao fechar o resultado da consulta.");
		}
	}
}
