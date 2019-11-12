package sockets;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint("/server")
public class SocketServer {

	private static ArrayList<Session> sessions = new ArrayList<Session>();

	@OnOpen
	public void onOpen(Session session) {
		sessions.add(session);
	}

	@OnMessage
	public void onMessage(String msg, Session session) {
		try {
			if (msg.trim().equals("USERS")) {
				session.getBasicRemote().sendText("Usuarios: " + sessions.size() );
				return;
			}

			for (int i = 0; i < sessions.size(); i++) {
				sessions.get(i).getBasicRemote().sendText(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
	}

	@OnClose
	public void onClose(Session session) {
		sessions.remove(session);
		for (int i = 0; i < sessions.size(); i++) {
			try {
				sessions.get(i).getBasicRemote().sendText("Alguien se desconectó");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
