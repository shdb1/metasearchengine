package com.metasearchengine.ir60.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	
	private static final String USER_AGENT = "Mozilla/5.0";


	private static final String POST_URL = "";

	private static final String POST_PARAMS = "userName=shadab";
	
	public static String doHTTPGetID(String url) throws IOException {
		String id = null;
		//URL obj = new URL("https://news.google.com/search?q=sachin&hl=en-IN&gl=IN&ceid=IN%3Aen");
		URL obj = new URL(url);

		System.out.println(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		StringBuffer response = new StringBuffer();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result	

			/*
			 * 	// print result	
			PrintWriter out = new PrintWriter("filename.txt");
			out.println(response);
			 * String data=response.toString().split("\\(")[1]; String responsess=
			 * data.substring(0, data.length()-1); ObjectMapper mapper = new ObjectMapper();
			 * IMDBObject imdb = mapper.readValue(responsess, IMDBObject.class); id =
			 * imdb.getD().get(0).getId();
			 */
			
		} else {
			System.out.println("GET request not worked");
		}
		System.out.println("returning id:"+id);
		return response.toString();

	}

	private static void sendPOST() throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}

}
