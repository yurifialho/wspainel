package br.com.secoti.websocket.controllers;

import java.io.IOException;

import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

import br.com.secoti.model.Mensagem;
import br.com.secoti.websocket.util.ManagerPainel;

public class PainelController implements OnTextMessage {
	
	/* attributes */
	private Long loja;
	private Connection conn;
	
	/* constructor */
	public PainelController(Long loja) {
		this.loja = loja;
	}
	
	/* getters and setters */

	public Long getLoja() {
		return loja;
	}

	public void setLoja(Long loja) {
		this.loja = loja;
	}
	
	/* comunications methods */
	
	@Override
	public void onClose(int arg0, String arg1) {
		ManagerPainel.getInstance().remove(this);
	}
	
	@Override
	public void onOpen(Connection conn) {
		this.conn = conn;
		this.conn.setMaxIdleTime(86400000);
		ManagerPainel.getInstance().add(this);
	}

	@Override
	public void onMessage(String msg) {
		//don't send message
	}
	
	public void sendMenssage(Mensagem msg) throws IOException {
		this.conn.sendMessage(JSON.toString(msg.toMap()));
	}
}
