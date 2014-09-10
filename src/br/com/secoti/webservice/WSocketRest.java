package br.com.secoti.webservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import br.com.secoti.model.Mensagem;
import br.com.secoti.websocket.util.ManagerPainel;

@Path("/wsocketrest")
public class WSocketRest {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessage() {
		
		Map<String,String> result = new HashMap<String, String>();
		
		try {
			Mensagem msg = new Mensagem(new Long(getParameter("loja")), 
										getParameter("guiche"), 
										new Integer(getParameter("senha")), 
										getParameter("rechamada").equals("S") ? true : false);
			ManagerPainel.getInstance().sendMessageToLoja(msg);
			result.put("STATUS", "OK");
			result.put("TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:s").format(new Date()));
		} catch (Exception e) {
			result.put("STATUS", "FAIL");
			result.put("TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:s").format(new Date()));
		}
		
		return result.toString();
	}
	
	private String getParameter(String key) {
		return this.uriInfo.getQueryParameters().getFirst(key);
	}
}