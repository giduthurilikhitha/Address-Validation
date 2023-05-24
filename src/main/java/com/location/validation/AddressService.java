package com.location.validation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;


import org.springframework.stereotype.Service;


public class AddressService {
	
	
	public String validateAndGetAddress(String address) throws IOException {
		 String validatedAddress="";
		 try {
	            String encodedAddress = java.net.URLEncoder.encode(address, "UTF-8");
	            String urlStr = "https://nominatim.openstreetmap.org/search?q=" + encodedAddress + "&format=json&addressdetails=1&limit=1";

	            URL url = new URL(urlStr);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");

	            if (conn.getResponseCode() == 200) {
	                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                String line;
	                StringBuilder response = new StringBuilder();

	                while ((line = reader.readLine()) != null) {
	                    response.append(line);
	                }

	                reader.close();
	                validatedAddress = response.toString();
	            } else {
	                System.out.println("Failed to validate address. HTTP error code: " + conn.getResponseCode());
	            }

	            conn.disconnect();
	        } catch (Exception e) {
	            System.out.println("Error validating address: " + e.getMessage());
	        }

	        return validatedAddress;
	}

}

