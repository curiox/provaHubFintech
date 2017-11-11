package org.provaHubFintech.model;

public class Transferencia {
	int idContaOrigem;
	int idContaDestino;
	float quantia;
	public int getIdContaOrigem() {
		return idContaOrigem;
	}
	public void setIdContaOrigem(int idContaOrigem) {
		this.idContaOrigem = idContaOrigem;
	}
	public int getIdContaDestino() {
		return idContaDestino;
	}
	public void setIdContaDestino(int idContaDestino) {
		this.idContaDestino = idContaDestino;
	}
	public float getQuantia() {
		return quantia;
	}
	public void setQuantia(float quantia) {
		this.quantia = quantia;
	}
}
