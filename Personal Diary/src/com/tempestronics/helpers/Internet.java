package com.tempestronics.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class Internet {
	public static String getHTML(String urlToRead) throws IOException, Exception {
	      URL url;
	      HttpURLConnection conn;
	      BufferedReader rd;
	      String line;
	      String result = "";
	         url = new URL(urlToRead);
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line;
	         }
	         rd.close();
	      
	      return result;
	   }
	
	public static void getFile(String urlToRead, String destinationFilepath) throws IOException, Exception {
	 CloseableHttpClient httpclient = HttpClientBuilder.create().build();
	 HttpGet httpget = new HttpGet(urlToRead);
	 HttpResponse response = httpclient.execute(httpget);
	 System.out.println(response.getStatusLine());
	 HttpEntity entity = response.getEntity();
	 if (entity != null) {
	     InputStream instream = entity.getContent();
	     try {
	         BufferedInputStream bis = new BufferedInputStream(instream);
	         String filePath = destinationFilepath;
	         BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	         int inByte;
	         while ((inByte = bis.read()) != -1 ) {
	             bos.write(inByte);
	         }
	         bis.close();
	         bos.close();
	     } catch (IOException ex) {
	         throw ex;
	     } catch (RuntimeException ex) {
	         httpget.abort();
	         throw ex;
	     } finally {
	         instream.close();
	     }
	     httpclient.close();
	 }
	}
}
