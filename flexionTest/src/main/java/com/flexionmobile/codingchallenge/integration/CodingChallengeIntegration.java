package com.flexionmobile.codingchallenge.integration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CodingChallengeIntegration implements Integration {

    private List<Purchase> purchases = new ArrayList<Purchase>();
    private final String developerId = "AdamZakar";
    private final String urlString = "http://sandbox.flexionmobile.com/javachallenge/rest/developer";
    private final String DELIMITER="/";
    private final String CONSUMED= "consume";
    private final String BUY= "buy";
    private final String ALL= "all";

    public Purchase buy(String itemId) {
        try {
            URL url = new URL(urlString + DELIMITER + developerId +
                    DELIMITER+BUY+DELIMITER+ itemId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                JSONObject jsonResult = new JSONObject(output);
               return readJson(jsonResult);
            }

            connection.disconnect();

            throw new NullPointerException("There were no server response");
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
        }
    }



    public List<Purchase> getPurchases() {
        try {
            URL url = new URL(urlString + DELIMITER + developerId + DELIMITER +ALL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                    JSONObject jsonResult = new JSONObject(output);
                    JSONArray innerData = jsonResult.getJSONArray("purchases");
                    for (int i = 0; i < innerData.length(); i++) {
                        JSONObject element= innerData.getJSONObject(i);
                            purchases.add(readJson(element));
                    };
                    return purchases;
            }

            connection.disconnect();

            return null;
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
        }
    }

    public Purchase readJson(JSONObject element){
        Purchase purchase;
        try{
          purchase=new CodingChallengePurchase(
                    element.getString( "id"),
                    element.getBoolean("consumed"),
                    element.getString("itemId"));
                    return purchase;
        }catch (JSONException jsonex){
           jsonex.printStackTrace();
           return null;
        }
    }

    public void consume(Purchase purchase) {

        try {
            URL url = new URL(urlString + DELIMITER + developerId +
                    DELIMITER+CONSUMED+DELIMITER+ purchase.getId());
            System.out.println(urlString + DELIMITER + developerId +
                    DELIMITER+CONSUMED+DELIMITER+ purchase.getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.disconnect();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

}
