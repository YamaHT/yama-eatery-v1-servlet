/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Controller.User.HomeController;

import Data.Model.Account;
import Data.Repository.User.ChatRepository;
import Utils.Configurator.HttpSessionConfigurator;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author ADMIN
 */
@ServerEndpoint(value = "/home/chat", configurator = HttpSessionConfigurator.class)
public class ChatEndPoint {

    static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
        Account account = (Account) httpSession.getAttribute("account");
        if (account == null) {
            account = new Account(0, null, null, null, null, false, null);
        }
        session.getUserProperties().put("account", account);
        clients.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, JSONException {
        Account account = (Account) session.getUserProperties().get("account");
        JSONObject accountJSON = new JSONObject();
        accountJSON.put("name", account.getProfile().getName());
        accountJSON.put("imgBase64", account.getProfile().getImgBase64());

        JSONObject originJSON = new JSONObject(message);
        originJSON.put("account", accountJSON);

        new ChatRepository().addChat(originJSON.getString("message"), account);

        for (Session client : clients) {
            if (client.isOpen()) {
                client.getBasicRemote().sendText(originJSON.toString());
            }
        }
    }

}
