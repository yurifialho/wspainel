package br.com.secoti.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Mensagem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* attributes */
	private Long loja;
	private String guiche;
	private Integer senha;
	private Boolean rechamada;
	
	/* constructors */
	public Mensagem() {}
	
	public Mensagem(Long loja, String guiche, Integer senha, Boolean rechamada) {
		this.loja = loja;
		this.guiche = guiche;
		this.senha = senha;
		this.rechamada = rechamada;
	}
	
	/* getter and setters */
	
	public Long getLoja() {
		return loja;
	}
	public void setLoja(Long loja) {
		this.loja = loja;
	}
	public String getGuiche() {
		return guiche;
	}
	public void setGuiche(String guiche) {
		this.guiche = guiche;
	}
	public Integer getSenha() {
		return senha;
	}
	public void setSenha(Integer senha) {
		this.senha = senha;
	}
	public Boolean getRechamada() {
		return rechamada;
	}
	public void setRechamada(Boolean rechamada) {
		this.rechamada = rechamada;
	}
	
	/* useful methods */
	
	public Map<String,Object> toMap() {
		Map<String,Object> map = new HashMap<String, Object>();
			map.put("loja", this.loja);
			map.put("guiche", this.guiche);
			map.put("senha", this.senha);
			map.put("rechamada", this.rechamada);
		return map;
	}
}