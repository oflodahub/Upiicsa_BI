package com.abirid.jb;

public class Orden {
	private String id;
	private String anioMes;
	private String orden;
	private String banco;
	
	public Orden(String id, String anioMes, String orden, String banco){
		this.id = id;
		this.anioMes = anioMes;
		this.orden = orden;
		this.banco = banco;
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAnioMes() {
		return anioMes;
	}
	public void setAnioMes(String anioMes) {
		this.anioMes = anioMes;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	public String getCve(){
		return  getAnio() + orden + banco;

	}
	
	public int getAnio(){
		return Integer.valueOf(getAnioMes().substring(0,4));
	}
	
	public int getMes(){
		return Integer.valueOf(getAnioMes().substring(4,6));
	}

	@Override
	public String toString() {
		return "Orden [id=" + id + ", anioMes=" + anioMes + ", orden=" + orden + ", banco=" + banco + "]";
	}
	
}
