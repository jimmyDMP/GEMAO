package fr.gemao.view.location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gemao.ctrl.adherent.AdherentCtrl;
import fr.gemao.ctrl.location.LocationCtrl;
import fr.gemao.ctrl.materiel.CategorieCtrl;
import fr.gemao.ctrl.materiel.MaterielCtrl;
import fr.gemao.entity.Personne;
import fr.gemao.entity.adherent.Adherent;
import fr.gemao.entity.materiel.Categorie;
import fr.gemao.entity.materiel.Location;
import fr.gemao.entity.materiel.Materiel;
import fr.gemao.form.util.Form;
import fr.gemao.view.JSPFile;
import fr.gemao.view.Pattern;

/**
 * Servlet implementation classe ConsulterLocationServlet
 */
@WebServlet(Pattern.LOCATION_CONSULTER)
public class ConsulterLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PARAM_SET_LOCATION = "location";

	private final String PARAM_ID_CATEGORIE = "idCategorie";
	private final String PARAM_NOM_CATEGORIE = "nomCategorie";
	private final String PARAM_LISTE_CATEGORIE = "listeCategorie";
	private final String PARAM_LISTE_MATERIEL = "listeMateriel";
	private final String PARAM_LISTE_ADHERENT = "listeAdherent";
	private final String PARAM_ID_DESIGNATION = "nomDesignation";
	private final String PARAM_ID_ADHERENT = "adherent";
	private final String PARAM_DATE_DEBUT = "debutLocation";
	private final String PARAM_DATE_FIN = "finLocation";
	private final String PARAM_CAUTION = "caution";
	private final String PARAM_MONTANT = "montant";
	private final String CATEGORIE = "categorie";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1er passage : choix de la catégorie
		List<Categorie> listeCategorie = CategorieCtrl.recupererToutesCategories();
		HttpSession session = request.getSession();

		request.setAttribute(PARAM_LISTE_CATEGORIE, listeCategorie);

		if (session.getAttribute(PARAM_ID_CATEGORIE) != null) {

			// 2eme passage: On recupere les materiels
			List<Materiel> listeMateriel = MaterielCtrl.recupererTousMateriels(); // (int)
																					// session.getAttribute(PARAM_ID_CATEGORIE));
			request.setAttribute(PARAM_LISTE_MATERIEL, listeMateriel);

			// on recupere les adherents
			List<Adherent> listeAdherent = AdherentCtrl.recupererTousAdherents();
		}
		
		

		// 2eme passage: liste location
		List<Location> locations = new ArrayList<>();
		LocationCtrl.getAll();
		if (locations.isEmpty()) {
			String vide = "La liste des locations est vide";
			request.setAttribute("vide", vide);
		}

		this.getServletContext().getRequestDispatcher(JSPFile.LOCATION_CONSULTER).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1er passage : choix de la catégorie
		List<Categorie> listeCategorie = CategorieCtrl.recupererToutesCategories();
		HttpSession session = request.getSession();
		request.setAttribute(PARAM_LISTE_CATEGORIE, listeCategorie);
		
		
		List<Location>locations = new ArrayList<>();
		
		locations = LocationCtrl.getAll();
		System.out.println(locations);
		request.setAttribute("listeLocation", locations);
		
		this.getServletContext().getRequestDispatcher(JSPFile.LOCATION_CONSULTER).forward(request, response);
	}
}



		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// 1er passage : choix de la catégorie
		/*List<Categorie> listeCategorie = CategorieCtrl.recupererToutesCategories();
		HttpSession session = request.getSession();
		request.setAttribute(PARAM_LISTE_CATEGORIE, listeCategorie);
		
		if (Form.getValeurChamp(request, PARAM_NOM_CATEGORIE) != null) {
			String setLocation = Form.getValeurChamp(request, PARAM_NOM_CATEGORIE);
		}
		List<Location> locations = new ArrayList<>();
		locations = LocationCtrl.getAll();


		if (session.getAttribute(PARAM_ID_CATEGORIE) != null) {

			// 2eme passage: On recupere les materiels
			List<Materiel> listeMateriel = new ArrayList<>(); 
			listeMateriel = MaterielCtrl.recupererTousMateriels();
			request.setAttribute(PARAM_LISTE_MATERIEL, listeMateriel);

			// on recupere les adherents
			List<Adherent> listeAdherent = AdherentCtrl.recupererTousAdherents();
		}

		// Nouvelle liste Location pour le tableau des locations
		List<Location> listeLocations = new ArrayList<>();

		this.getServletContext().getRequestDispatcher(JSPFile.LOCATION_CONSULTER).forward(request, response);
	}*/