package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.*;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class MarketAlertUmAPI{

    protected String apiKey;
    protected String baseUrl = "https://api.marketalertum.com/";

    // in some weird cases the connection times out.
    // resolve this by adding retrys. Also reduce timeout.
    RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(500)
            .build();

    HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(5, true);

    HttpClient client = HttpClientBuilder.create()
//            .setDefaultRequestConfig(requestConfig)
            .setRetryHandler(retryHandler)
            .build();


    // constructor takes api key
    public MarketAlertUmAPI(String apiKey){
        this.apiKey = apiKey;
    }

    public class InvalidKeyException extends Exception {
        public InvalidKeyException() {
            super("Invalid Key");
        }
    }

    public boolean login() throws InvalidKeyException {
        // create post request
        HttpPost request = new HttpPost("https://www.marketalertum.com/Alerts/LoginForm");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("UserId", this.apiKey));
        try {
            request.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }


        try {
            HttpResponse response = this.client.execute(request);

            request.releaseConnection();
            // ensure response status code is 302
            if (response.getStatusLine().getStatusCode() != 302) {
                System.out.println("Error: " + response.getStatusLine().getStatusCode());
                throw new InvalidKeyException();
            }

            // if header Location != /Alerts/List then login failed
            if (!response.getFirstHeader("Location").getValue().equals("/Alerts/List")){
                System.out.println("Login failed");
                throw new InvalidKeyException();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean logout(){
        // create get request
        HttpGet request = new HttpGet("https://www.marketalertum.com/Home/Logout");

        try {
            HttpResponse response = this.client.execute(request);
            request.releaseConnection();

            // ensure response status code is 200
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("Error: " + response.getStatusLine().getStatusCode());
                return false;
            }
            // Could check if cookies are cleared but logout doesn't clear cookies as of 29/12/2012
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // create alert
    public void createAlert(JsonObject alert) throws InvalidKeyException {
        // create post request
        HttpPost request = new HttpPost(this.baseUrl + "Alert");

        // add json to post request
        StringEntity params = null;
        try {
            alert = Json.createObjectBuilder(alert).add("postedBy", Json.createValue(this.apiKey)).build();
            params = new StringEntity(alert.toString());

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        try {
            HttpResponse response = this.client.execute(request);

            // ensure response status code is 201
            if (response.getStatusLine().getStatusCode() != 201) {
                System.out.println("Error: " + response.getStatusLine().getStatusCode());
                request.releaseConnection();
                throw new InvalidKeyException();
            }

            request.releaseConnection();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void purgeAlerts() throws InvalidKeyException {
        // create delete request
        HttpDelete request = new HttpDelete(this.baseUrl + "Alert/?userId=" + this.apiKey);

        // execute delete request
        try {
            HttpResponse response = this.client.execute(request);

            // ensure response status code is 200
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("Error: " + response.getStatusLine().getStatusCode());
                request.releaseConnection();
                throw new InvalidKeyException();
            }
            request.releaseConnection();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonArray getAlerts() throws InvalidKeyException{
        // create get request
        HttpGet request = new HttpGet(this.baseUrl + "Alert/?userId=" + this.apiKey);

        // execute get request
        try {
            HttpResponse response = this.client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("Error: " + response.getStatusLine().getStatusCode());
                request.releaseConnection();
                throw new InvalidKeyException();
            }

            String jsonString = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).lines().parallel().collect(Collectors.joining("\n"));
            JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
            JsonArray object = jsonReader.readArray();
            jsonReader.close();
            request.releaseConnection();
            // convert response content to json object and return it
            return object;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Json.createArrayBuilder().build();
    }

    public JsonArray getEventLog() throws InvalidKeyException{
        // create request request
        HttpGet request = new HttpGet(this.baseUrl + "EventsLog/" + this.apiKey);

        // execute request request
        try {
            HttpResponse response = this.client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("Error: " + response.getStatusLine().getStatusCode());
                request.releaseConnection();
                throw new InvalidKeyException();
            }

            // convert response content to json object and return it
            String jsonString = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).lines().parallel().collect(Collectors.joining("\n"));
            JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
            JsonArray object = jsonReader.readArray();
            jsonReader.close();
            request.releaseConnection();
            return object;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}