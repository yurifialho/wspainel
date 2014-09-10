package br.com.secoti.websocket.util;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import br.com.secoti.model.Mensagem;
import br.com.secoti.websocket.controllers.PainelController;

public class ManagerPainel {
	/* attributes */
	private static ManagerPainel INSTANCE;
	
	private Set<PainelController> paineis = new CopyOnWriteArraySet<PainelController>();
	
	/* private constructor */
	private ManagerPainel() {
		
	}
	
	/* instance of singleton */	
	public static ManagerPainel getInstance() {
		if (INSTANCE == null) {
		 	INSTANCE = new ManagerPainel(); 
		}
		
		return INSTANCE;
	}
	
	public void add(PainelController painel) {
		this.paineis.add(painel);
	}
	
	public void remove(PainelController painel) {
		this.paineis.remove(painel);
	}
	
	public PainelController getByLoja(Long loja) {
		for(PainelController painel : this.paineis) {
			if (painel.getLoja().equals(loja)) {
				return painel;
			}
		}
		return null;
	}
	
	public Set<PainelController> getAll() {
		return this.paineis;
	}
	
	public void sendMessageToLoja(Mensagem msg) throws IOException {
		for(PainelController painel : this.paineis) {
			if (painel.getLoja().equals(msg.getLoja())) {
				painel.sendMenssage(msg);
			}
		}
	}
}
