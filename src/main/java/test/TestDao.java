package test;

import java.util.List;

import dao.PlatDaoImpl;
import metier.entities.Plat;

public class TestDao {
	public static void main(String[] args) {
	PlatDaoImpl pdao= new PlatDaoImpl();
	Plat plat= pdao.save(new Plat("ojja",38000));
	System.out.println(plat);
	List<Plat> plats =pdao.platsParMC("plat");
	for (Plat p : plats)
	System.out.println(p);
	}
	

}

