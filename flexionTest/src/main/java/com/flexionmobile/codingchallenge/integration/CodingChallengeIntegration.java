package com.flexionmobile.codingchallenge.integration;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import org.apache.http.impl.client.HttpClientBuilder;
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

import org.apache.http.client.methods.HttpPost;

public class CodingChallengeIntegration implements Integration {

    private List<Purchase> purchases = new ArrayList<Purchase>();
    private final String developerId = "AdamZakar2";
    private final String urlString = "http://sandbox.flexionmobile.com/javachallenge/rest/developer";
    private final String DELIMITER = "/";
    private final String CONSUMED = "consume";
    private final String BUY = "buy";
    private final String ALL = "all";
    private final String GET = "GET";
    private final String POST = "POST";

    public Purchase buy(String itemId) {
        HttpURLConnection connection = buildUpConnection(urlString + DELIMITER + developerId +
                DELIMITER + BUY + DELIMITER + itemId, POST);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())))) {
            String output;
            while ((output = br.readLine()) != null) {
                return parseJSONToPurchase(new JSONObject(output));
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        connection.disconnect();

        throw new NullPointerException("There were no server response");
    }


    public List<Purchase> getPurchases() {

        HttpURLConnection connection = buildUpConnection(urlString +
                DELIMITER + developerId + DELIMITER + ALL, GET);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())));) {
            String output;
            while ((output = br.readLine()) != null) {
                JSONObject jsonResult = new JSONObject(output);
                JSONArray innerData = jsonResult.getJSONArray("purchases");
                for (int i = 0; i < innerData.length(); i++) {
                    purchases.add(parseJSONToPurchase(innerData.getJSONObject(i)));
                }
                return purchases;
            }
            connection.disconnect();
            throw new NullPointerException("There were no server response");
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
        }

    }


    public void consume(Purchase purchase) {
        HttpPost httpPost = new HttpPost(urlString + DELIMITER + developerId +
                DELIMITER + CONSUMED + DELIMITER + purchase.getId());
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response = client.execute(httpPost);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }


    private HttpURLConnection buildUpConnection(String urlString, String requestType) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestType);
            return connection;
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
        }
    }

    private Purchase parseJSONToPurchase(JSONObject element) {
        Purchase purchase;
        try {
            purchase = new CodingChallengePurchase(
                    element.getString("id"),
                    element.getBoolean("consumed"),
                    element.getString("itemId"));
            return purchase;
        } catch (JSONException jsonex) {
            jsonex.printStackTrace();
            return null;
        }
    }

}
