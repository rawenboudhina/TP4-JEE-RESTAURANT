package dao;
import java.util.List;
import metier.entities.Plat;

public interface IplatDao {
	
	public Plat save(Plat p);
	public List<Plat> platsParMC(String mc);
	public Plat getPlat(Long id);
	public Plat updatePlat(Plat p);
	public void deletePlat(Long id);
	}

