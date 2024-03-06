package metier.entities;

import java.io.Serializable;

public class Plat implements Serializable {
	private Long idPlat;
	private String nomPlat;
	private double prix;

	public Plat() {
		super();
	}
	



	public Plat(String nomPlat, double prix) {
		super();
		this.nomPlat = nomPlat;
		this.prix = prix;
	}




	public Long getIdPlat() {
		return idPlat;
	}




	public void setIdPlat(Long idPlat) {
		this.idPlat = idPlat;
	}




	public String getNomPlat() {
		return nomPlat;
	}




	public void setNomPlat(String nomPlat) {
		this.nomPlat = nomPlat;
	}




	public double getPrix() {
		return prix;
	}




	public void setPrix(double prix) {
		this.prix = prix;
	}




	@Override
	public String toString() {
		return "Plat [" + "nomPlat=" + nomPlat + ", prix=" + prix + "]";
	}
}
