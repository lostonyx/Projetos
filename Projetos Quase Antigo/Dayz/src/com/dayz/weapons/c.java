
package com.dayz.weapons;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class c{
	public static String Cripto(String senha)
	{
		//Criptografa a String passada por parâmetro
		int contador, tamanho,codigoASCII;
		String senhaCriptografada = "";
		tamanho = senha.length();
		senha = senha.toUpperCase();
		contador = 0;
		while(contador <tamanho)
		{
			codigoASCII = senha.charAt(contador)+130;
			senhaCriptografada = senhaCriptografada +(char) codigoASCII;
			contador++;
		}
		return senhaCriptografada;
	}
	public static String Decripto(String senha)
	{
		//Descriptografa a String passada por parâmetro
		int contador, tamanho,codigoASCII;
		String senhaCriptografada = "";
		tamanho = senha.length();
		senha = senha.toUpperCase();
		contador = 0;
		while(contador <tamanho)
		{
			codigoASCII = senha.charAt(contador)-130;
			senhaCriptografada = senhaCriptografada +(char) codigoASCII;
			contador++;
		}
		return senhaCriptografada;
	}
	
	
    public static String Key(){
        //String key = pegarKey(PegarIp());
        return pegarKey(PegarIp());
    }
    public static String PegarIp() {
	try {
		URL url = new URL("http://checkip.amazonaws.com/");
		URLConnection openConnection = url.openConnection();
		openConnection
				.addRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		Scanner r = new Scanner(openConnection.getInputStream());
		StringBuilder sb = new StringBuilder();
		while (r.hasNext()) {
			sb.append(r.next() + " ");
		}
		r.close();
		return sb.toString();
	} 
        catch (IOException e) {
            
	}
	return null;
    }
	public static String pegarKey(String Ip) {
            String key = null;
            if(Ip.isEmpty()){
                return "Sem conexao com o sistema!";
            }
            else{
                try {
                    URL url = new URL("https://saomc.com.br/api/check/");
                    URLConnection openConnection = url.openConnection();
                    openConnection.addRequestProperty("User-Agent", "SaintsEncantar/1.0 (Windows; U; Win98; en-US; rv:1.7.2) Gecko/20040803");
                    Scanner r = new Scanner(openConnection.getInputStream());
                    StringBuilder sb = new StringBuilder();
                    while (r.hasNext()) {
			sb.append(r.next() + " ");
                    }
                    r.close();
                    key = sb.toString();
                    return sb.toString();
                } 
                catch (IOException e) {  
                }
                return key;
            }
	}

}