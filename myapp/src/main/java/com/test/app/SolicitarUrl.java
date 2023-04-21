package com.test.app;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SolicitarUrl {
    
     //Parametros iniciais para solicitção
     private static String url_principal="https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
     private String localizacao="Catalão,BR";
     private String data_inicio=null; 
     private String data_fim=null; 
     private String unidade_metrica="metric"; //us,metric,uk 
     private String Chave_api="76TNBX56HWG6LMD274ED3RXF7";



    void previsao_prox_dias() throws Exception {
       
        Usarjson parsejson = new Usarjson();

        //Build the URL pieces
        StringBuilder requestBuilder=new StringBuilder(url_principal);
        requestBuilder.append(localizacao);
                
       /*  if (startDate!=null && !startDate.isEmpty()) {
            requestBuilder.append("/").append(startDate);
            if (endDate!=null && !endDate.isEmpty()) {
                requestBuilder.append("/").append(endDate);
            }
        }*/
                
        //Build the parameters to send via GET or POST
        URIBuilder builder = new URIBuilder(requestBuilder.toString());
        builder.setParameter("unitGroup", unidade_metrica)
            .setParameter("key", Chave_api);

        HttpGet get = new HttpGet(builder.build());
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
                    
        CloseableHttpResponse response = httpclient.execute(get);
                    
        String rawResult=null;
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.printf("Bad response status code:%d%n",		
                    response.getStatusLine().getStatusCode());
                return;
                }			
                org.apache.http.HttpEntity entity = response.getEntity();
                if (entity != null) {
                    rawResult= EntityUtils.toString(entity);
                }
                        
            } finally {
                response.close();
            }
                    
            parsejson.parseTimelineJson(rawResult);
    }



    public String getUrl_principal() {
        return url_principal;
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
