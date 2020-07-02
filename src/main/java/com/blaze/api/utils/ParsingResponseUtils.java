package com.blaze.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;

public class ParsingResponseUtils {
	public static Map<String, String> handleResponse(HttpResponse response) {
		String contentType = null;
		if (response.getEntity().getContentType() != null) {
			contentType = response.getEntity().getContentType().getValue();
		}
		if (contentType.contains("application/json")) {
			return handleJsonResponse(response);
		} else if (contentType.contains("application/x-www-form-urlencoded")) {
			return null;
		} else if (contentType.contains("application/xml")) {
			return null;
		} else {
			throw new RuntimeException("Cannot handle " + contentType
					+ " content type. Supported content types include JSON, XML and URLEncoded");
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> handleJsonResponse(HttpResponse response) {
		Map<String, String> oauthLoginResponse = null;
		try {
			oauthLoginResponse = (Map<String, String>) new JSONParser()
					.parse(EntityUtils.toString(response.getEntity()));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (RuntimeException e) {
			System.out.println("Could not parse JSON response");
			throw e;
		}
		return oauthLoginResponse;
	}


	@SuppressWarnings("resource")
	public static String getRespondeMessagefromServerResponse(HttpResponse response) {
		String responseString = null ;
		HttpEntity responseHttpEntity = response.getEntity();
		InputStream content;
		try {
			content = responseHttpEntity.getContent();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = buffer.readLine()) != null) {
				responseString += line;
			}
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
		return responseString;
	}

}
