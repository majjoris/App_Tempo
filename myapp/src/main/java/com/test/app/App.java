package com.test.app;

import java.util.Scanner;

public final class App {
    
	
    public static void main(String[] args) throws Exception {
        
        Scanner ler = new Scanner(System.in);
        System.out.println("Localizaçao");

        SolicitarUrl urll = new SolicitarUrl();
        urll.previsao_prox_dias();
            

    }

}
