package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import interfaces.IApi;
import interfaces.IRetornoSolicitacao;

public class ApiUber implements IApi {


	public static void main(String[] args) {
		
		
		URL obj = null;
		try {
			obj = new URL("https://api.uber.com/v1.2/products?latitude=37.7759792&longitude=-122.41823");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) obj.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    con.setRequestProperty("User-Agent", "Mozilla/5.0");
		
	    int responseCode = 0;
		try {
			responseCode = con.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Response Code : " + responseCode);
	    BufferedReader in = null;
		try {
			in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String inputLine;
	    StringBuffer response = new StringBuffer();
	    try {
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public IRetornoSolicitacao ChamarAplicativo(Destino destinoInicial, Destino destinoFinal) {

		DestinoUber destinoInicialUber = (DestinoUber) destinoInicial;
		DestinoUber destinoFinalUber = (DestinoUber) destinoFinal;
		
		
		return null;
	}

}
