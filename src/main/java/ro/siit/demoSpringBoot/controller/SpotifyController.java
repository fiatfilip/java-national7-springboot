package ro.siit.demoSpringBoot.controller;

import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/spotify")
public class SpotifyController {

    @Value("${spotify.authentication.url}")
    private String spotifyAuthenticationURL;

    @Value("${spotify.client.id}")
    private String spotifyClientID;

    @Value("${spotify.client.secret}")
    private String spotifyClientSecret;

    @Value("${spotify.api.url}")
    private String spotifyApiURL;

    @GetMapping("/tracks")
    public String getTrack(@RequestParam(name="id", required=false, defaultValue="2TpxZ7JUBn3uw46aR7qd6V") String id) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost tokenRequest = new HttpPost(spotifyAuthenticationURL);
        String encoding = Base64.getEncoder().encodeToString((spotifyClientID + ":" + spotifyClientSecret).getBytes());
        tokenRequest.addHeader(HttpHeaders.AUTHORIZATION, "Basic "  + encoding);

        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("grant_type", "client_credentials"));
            tokenRequest.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse response = httpClient.execute(tokenRequest);

            JSONObject responseAsJson = new JSONObject(EntityUtils.toString(response.getEntity()));
            String accessToken = responseAsJson.get("access_token").toString();

            HttpGet trackRequest = new HttpGet(spotifyApiURL + "/tracks/" + id);
            trackRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
            CloseableHttpResponse trackResponse = httpClient.execute(trackRequest);

            return EntityUtils.toString(trackResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "{\"id\": \""+ spotifyAuthenticationURL +"\"}";
    }

}
