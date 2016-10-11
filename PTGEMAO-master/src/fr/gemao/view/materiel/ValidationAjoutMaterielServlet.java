package fr.gemao.view.materiel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gemao.ctrl.materiel.MaterielCtrl;
import fr.gemao.entity.materiel.Materiel;
import fr.gemao.view.JSPFile;
import fr.gemao.view.Pattern;
import fr.gemao.view.ResultatServlet;

/**
 * Servlet implementation class ValidationAjoutMaterielServlet
 */
@WebServlet("/ValidationAjoutMaterielServlet")
public class ValidationAjoutMaterielServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("materiel") == null){
			this.getServletContext().getRequestDispatcher(JSPFile.ERREUR_404).forward(request, response);
		} else {
			Materiel materiel = (Materiel) session.getAttribute("materiel");
	
			request.setAttribute("materiel", materiel);
			
	
			this.getServletContext().getRequestDispatcher(JSPFile.MATERIEL_VALIDATION_AJOUT).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Materiel materiel = (Materiel) session.getAttribute("materiel");
		
		MaterielCtrl.ajoutMateriel(materiel.getEtat(),
				materiel.getCategorie(), materiel.getMarque(),
				materiel.getDesignation(), materiel.getFournisseur(),
				materiel.getTypeMat(), materiel.getNumSerie(),
				materiel.getDateAchat(), materiel.getValeurAchat(),
				materiel.getValeurReap(), materiel.isDeplacable(),
				materiel.getObservation(), 1,
				materiel.isLouable());
		
		request.setAttribute(ResultatServlet.ATTR_TITRE_H1, "Confirmation");
		request.setAttribute(ResultatServlet.ATTR_NOM_BOUTON, "Retour");
		request.setAttribute(ResultatServlet.ATTR_LIEN_BOUTON, Pattern.MATERIEL_LISTER);
		request.setAttribute(ResultatServlet.ATTR_RESULTAT, "Le materiel "+ materiel.getDesignation().getLibelleDesignation() + " a été créé.");
		session.removeAttribute("materiel");
		this.getServletContext().getRequestDispatcher(JSPFile.RESULTAT).forward(request, response);
	}

}