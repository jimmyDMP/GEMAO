package fr.gemao.sql.location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.gemao.entity.materiel.Location;
import fr.gemao.sql.DAOFactory;
import fr.gemao.sql.IDAO;
import fr.gemao.sql.exception.DAOException;
import fr.gemao.sql.util.DAOUtilitaires;

public class LocationDAO extends IDAO<Location> {
	public LocationDAO(DAOFactory factory) {
		super(factory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Location> getAll() {
		List<Location> liste = new ArrayList<>();

		Location location = null;
		Connection connexion = null;
		PreparedStatement requete = null;
		ResultSet result = null;
		String sql = "SELECT * FROM location;";
		try {

			connexion = factory.getConnection();
			requete = DAOUtilitaires.initialisationRequetePreparee(connexion,
					sql, false);
			result = requete.executeQuery();

			while (result.next()) {
				location = this.map(result);
				liste.add(location);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaires.fermeturesSilencieuses(result, requete, connexion);
		}

		return liste;
	}

	@Override
	public Location create(Location obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Location obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Location update(Location obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location get(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Location> getAllAll() {
		List<Location> liste = new ArrayList<>();

		Location location = null;
		Connection connexion = null;
		PreparedStatement requete = null;
		ResultSet result = null;
		String sql = "SELECT nom, prenom, dateNaissance, d.libelle, m.typeMateriel, dateEmprunt, dateEcheance, dateRetour, idEtatDebut, idEtatFin, montant FROM location l INNER JOIN personne p ON l.idPersonne=p.idPersonne"
				+ " INNER JOIN materiel m ON l.idMateriel = m.idMateriel"
				+ " INNER JOIN designation d ON m.idDesignation = d.idDesignation"
				+ " INNER JOIN categorie c ON m.idCategorie = c.idCategorie ORDER BY p.nom ;";
		try {
			connexion = factory.getConnection();
			requete = DAOUtilitaires.initialisationRequetePreparee(connexion,
					sql, false);
			result = requete.executeQuery();

			while (result.next()) {
				location = this.map2(result);
				liste.add(location);
			}
		} catch (SQLException e1) {
			throw new DAOException(e1);
		} finally {
			DAOUtilitaires.fermeturesSilencieuses(result, requete, connexion);
		}
		return liste;
	}

	public Date getDebutLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Location map(ResultSet result) throws SQLException {
		Integer idEtatFin = result.getInt("idEtatFin"),
				idReparation = result.getInt("idReparation");
		return new Location(
				factory.getPersonneDAO().get(result.getInt("idPersonne")),
				factory.getMaterielDAO().get(result.getInt("idMateriel")),
				factory.getEtatDAO().get(result.getInt("idEtatDebut")),
				factory.getEtatDAO().get(idEtatFin),
				result.getString("dateEmprunt"),
				result.getString("dateRetour"),
				result.getString("dateEcheance"),
				result.getFloat("montant"),
				factory.getReparationDAO().get(idReparation));
	}
	
	protected Location map2(ResultSet result) throws SQLException {
		Integer idEtatFin = result.getInt("idEtatFin");
		return new Location(
				factory.getPersonneDAO().get(result.getInt("idPersonne")),
				factory.getMaterielDAO().get(result.getInt("idMateriel")),
				factory.getEtatDAO().get(result.getInt("idEtatDebut")),
				factory.getEtatDAO().get(idEtatFin),
				result.getString("dateEmprunt"),
				result.getString("dateRetour"),
				result.getString("dateEcheance"),
				result.getFloat("montant"), null);
	}
}
