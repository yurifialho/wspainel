package br.com.secoti.websocket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import br.com.secoti.websocket.controllers.PainelController;

@WebServlet("/wspainel")
public class PainelWebSocketServlet extends WebSocketServlet {
	
	private static final long serialVersionUID = 1L;

	public PainelWebSocketServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getNamedDispatcher("default").forward(request, response);
	}
	
	public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
		String[] parameters = request.getParameterValues("chkShop");
		if (parameters == null || parameters.length < 1 || !parameters[0].matches("[0-9]+")) {
			return null;
		} else {
			return new PainelController(new Long(parameters[0]));
		}
	}
}