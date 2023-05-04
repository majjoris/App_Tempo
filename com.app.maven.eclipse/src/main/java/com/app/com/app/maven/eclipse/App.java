package com.app.com.app.maven.eclipse;

/**
 *  @author euller
 *
 */
public class App
{
    public static void main( String[] args ) throws Exception
    {
    	 SolicitarUrl urll = new SolicitarUrl();

         urll.setLocalizacao("Catalão, GO, Brasil");
         urll.setUnidade_metrica("metric");
         urll.setChave_api("76TNBX56HWG6LMD274ED3RXF7");
         //urll.setData_inicio("2020-12-01");
         //urll.setData_fim("2020-12-031");
         urll.previsao_completa();
    }
}
