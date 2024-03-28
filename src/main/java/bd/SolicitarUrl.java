package bd;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SolicitarUrl {

	// Parametros iniciais para solicitção
	private static String url_principal = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
	private String localizacao;
	private String data_inicio;
	private String data_fim;
	private String unidade_metrica="metric"; // us,metric,uk
	private String Chave_api;

	void previsao_prox_dias() throws Exception {

		Usarjson parsejson = new Usarjson();

		// Build the URL pieces
		StringBuilder requestBuilder = new StringBuilder(url_principal);
		requestBuilder.append(localizacao);

		if (data_inicio != null && !data_inicio.isEmpty()) {
			requestBuilder.append("/").append(data_inicio);
			if (data_fim != null && !data_fim.isEmpty()) {
				requestBuilder.append("/").append(data_fim);
			}
		}

		// Build the parameters to send via GET or POST
		URIBuilder builder = new URIBuilder(requestBuilder.toString());
		builder.setParameter("unitGroup", unidade_metrica).setParameter("key", Chave_api);

		HttpGet get = new HttpGet(builder.build());

		CloseableHttpClient httpclient = HttpClients.createDefault();

		CloseableHttpResponse response = httpclient.execute(get);

		String rawResult = null;
		try {
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.out.printf("Bad response status code:%d%n", response.getStatusLine().getStatusCode());
				return;
			}
			org.apache.http.HttpEntity entity = response.getEntity();
			if (entity != null) {
				rawResult = EntityUtils.toString(entity);
			}

		} finally {
			response.close();
		}
		parsejson.parseTimelineJson(rawResult);
	}

	public void previsao_completa() throws Exception {
		Usarjson parsejson = new Usarjson();
		ConexaoBD conexao = new ConexaoBD();
		conexao.conectar();

		String method = "GET"; // GET OR POST

		// Build the URL pieces
		StringBuilder requestBuilder = new StringBuilder(url_principal);
		requestBuilder.append(localizacao);

		if (data_inicio != null && !data_inicio.isEmpty()) {
			requestBuilder.append("/").append(data_inicio);
			if (data_fim != null && !data_fim.isEmpty()) {
				requestBuilder.append("/").append(data_fim);
			}
		}

		// Build the parameters to send via GET or POST
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append("&").append("unitGroup=").append(unidade_metrica);
		paramBuilder.append("&").append("key=").append(Chave_api);

		// for GET requests, add the parameters to the request
		if ("GET".equals(method)) {
			requestBuilder.append("?").append(paramBuilder);
		}

		// set up the connection
		URL url = new URL(requestBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		// If post method, send post request
		if ("POST".equals(method)) {
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString(paramBuilder.length()));
			conn.setUseCaches(false);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(paramBuilder.toString());
			wr.flush();
			wr.close();
		}

		// check the response code and set up the reader for the appropriate stream
		int responseCode = conn.getResponseCode();
		boolean isSuccess = responseCode == 200;
		StringBuffer response = new StringBuffer();
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(isSuccess ? conn.getInputStream() : conn.getErrorStream()))) {

			// read the response
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}
		if (!isSuccess) {
			System.out.printf("Bad response status code:%d, %s%n", responseCode, response.toString());

			return;
		}

		// pass the string response to be parsed and used
		parsejson.parseTimelineJson(response.toString());
		conexao.inserir(response.toString());
		conexao.desconectar();
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}

	public String getData_fim() {
		return data_fim;
	}

	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}

	public String getUnidade_metrica() {
		return unidade_metrica;
	}

	public void setUnidade_metrica(String unidade_metrica) {
		this.unidade_metrica = unidade_metrica;
	}

	public String getChave_api() {
		return Chave_api;
	}

	public void setChave_api(String chave_api) {
		Chave_api = chave_api;
	}

}
