package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.SingletonConnection;
import metier.entities.Plat;

public class PlatDaoImpl implements IplatDao {
	@Override
	public Plat save(Plat p) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Plats(NOM_PLAT,PRIX) VALUES(?,?)");
			ps.setString(1, p.getNomPlat());
			ps.setDouble(2, p.getPrix());
			ps.executeUpdate();
			PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID_PLAT) as MAX_ID FROM PLATS");
			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				p.setIdPlat(rs.getLong("MAX_ID"));
			}
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Plat> platsParMC(String mc) {
		List<Plat> plats = new ArrayList<Plat>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from PLATS where NOM_PLAT LIKE ?");
			ps.setString(1, "%" + mc + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Plat p = new Plat();
				p.setIdPlat(rs.getLong("ID_PLAT"));
				p.setNomPlat(rs.getString("NOM_PLAT"));
				p.setPrix(rs.getDouble("PRIX"));
				plats.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return plats;
	}

	@Override
	public Plat getPlat(Long id) {
		   Connection conn=SingletonConnection.getConnection();
		    Plat p = new Plat();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from PLATS where ID_PLAT = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				
				p.setIdPlat(rs.getLong("ID_PLAT"));
				p.setNomPlat(rs.getString("NOM_PLAT"));
				p.setPrix(rs.getDouble("PRIX"));
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return p;
	
	}

	@Override
	public Plat updatePlat(Plat p) {
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("UPDATE PLATS SET NOM_PLAT=?,PRIX=? WHERE ID_PLAT=?");
			ps.setString(1, p.getNomPlat());
			ps.setDouble(2, p.getPrix());
			ps.setLong(3, p.getIdPlat());
			ps.executeUpdate();
			ps.close();
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public void deletePlat(Long id) {
		 Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM PLATS WHERE ID_PLAT = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
