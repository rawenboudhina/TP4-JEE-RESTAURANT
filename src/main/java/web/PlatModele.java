package web;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Plat;

public class PlatModele {
	private String motCle;
	List<Plat> plats = new ArrayList<>();
	public String getMotCle() {
	return motCle;
	}
	public void setMotCle(String motCle) {
	this.motCle = motCle;
	}
	public List<Plat> getPlats() {
	return plats;
	}
	public void setPlats(List<Plat> plats) {
	this.plats = plats;
	}

}
