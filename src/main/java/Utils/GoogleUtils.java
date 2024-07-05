/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author ADMIN
 */
public class GoogleUtils {

    private final String GOOGLE_CLIENT_ID = "13280710106-5ak2ec14u3k3bmns32v7g69kl0aq1759.apps.googleusercontent.com";
    private final String GOOGLE_CLIENT_SECRET = "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";
    private final String GOOGLE_REDIRECT_URI = "http://localhost:8080/auth/loginGoogleCallback";
    private final String GOOGLE_GRANT_TYPE = "authorization_code";

    public String getGoogleOAuthLoginURL() {
        return "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=" + GOOGLE_CLIENT_ID
                + "&redirect_uri=" + GOOGLE_REDIRECT_URI
                + "&response_type=code"
                + "&scope=email"
                + "&approval_prompt=force";
    }

    public String getEmailFromCode(String code) {
        String email = null;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://oauth2.googleapis.com/token");

            Map<String, String> parameters = new HashMap<>();
            parameters.put("code", code);
            parameters.put("client_id", GOOGLE_CLIENT_ID);
            parameters.put("client_secret", GOOGLE_CLIENT_SECRET);
            parameters.put("redirect_uri", GOOGLE_REDIRECT_URI);
            parameters.put("grant_type", GOOGLE_GRANT_TYPE);
            String jsonParameters = mapToJson(parameters);

            StringEntity entity = new StringEntity(jsonParameters);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");

            HttpResponse httpResponse = httpClient.execute(httpPost);

            String responseBody = EntityUtils.toString(httpResponse.getEntity());
            email = extractEmailFromToken(responseBody);
        } catch (Exception e) {
        }
        return email;
    }

    private String mapToJson(Map<String, String> map) {
        StringBuilder jsonBuilder = new StringBuilder("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jsonBuilder.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
        }
        jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    private String extractEmailFromToken(String responseBody) {
        String idToken = new Gson().fromJson(responseBody, JsonObject.class).get("id_token").getAsString();
        String[] idTokenParts = idToken.split("\\.");
        String encodedPayload = idTokenParts[1];
        String payload = new String(Base64.getDecoder().decode(encodedPayload));
        JsonObject payloadObject = new JsonParser().parse(payload).getAsJsonObject();
        return payloadObject.get("email").getAsString();
    }
}
