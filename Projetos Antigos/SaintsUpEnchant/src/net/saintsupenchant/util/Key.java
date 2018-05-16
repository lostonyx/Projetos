package net.saintsupenchant.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;
import net.saintsupenchant.Main;
import org.bukkit.Bukkit;

public class Key {
    Main pl;
    public Key(Main plugin) {
        this.pl = plugin;
    } 
    public static String Key(){
        //String key = pegarKey(PegarIp());
        return pegarKey(PegarIp());
    }
    public static String PegarIp() {
	try {
		URL url = new URL("http://checkip.amazonaws.com/");
		URLConnection openConnection = url.openConnection();
		openConnection.addRequestProperty("User-Agent",
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
