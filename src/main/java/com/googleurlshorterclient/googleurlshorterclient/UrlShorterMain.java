/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googleurlshorterclient.googleurlshorterclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author asenturk
 */
public class UrlShorterMain {

    public static String googUrl = " https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyBag4rVljWWqh7_ZR1APV-auYJ1tl5OeBM";

    public static String shorten(String longUrl) {
        String shortUrl = "";

        try {
            URLConnection conn = new URL(googUrl).openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write("{\"longUrl\":\"" + longUrl + "\"}");
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null) {
                if (line.indexOf("id") > -1 && line.length() > 10) {
                    shortUrl = line.substring(8, line.length() - 2);
                    break;
                }
                shortUrl += line;
            }

            wr.close();
            rd.close();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return shortUrl;
    }
    
    public static void main(String args[]){
        String longUrl = "www.atlasglb.com";
        System.out.println("LongUrl....:" + longUrl);
        System.out.println("ShortUrl...:" + shorten(longUrl));
    }
}
