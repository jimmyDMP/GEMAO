
package fr.gemao.view.location;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import fr.gemao.ctrl.PersonneCtrl;
import fr.gemao.ctrl.adherent.AdherentCtrl;
import fr.gemao.ctrl.location.LocationCtrl;
import fr.gemao.ctrl.materiel.CategorieCtrl;
import fr.gemao.ctrl.materiel.EtatCtrl;
import fr.gemao.ctrl.materiel.MaterielCtrl;
import fr.gemao.entity.Personne;
import fr.gemao.entity.adherent.Adherent;
import fr.gemao.entity.materiel.Categorie;
import fr.gemao.entity.materiel.Etat;
import fr.gemao.entity.materiel.Materiel;
import fr.gemao.entity.personnel.Personnel;
import fr.gemao.form.location.LocationForm;
import fr.gemao.form.util.Form;
import fr.gemao.view.JSPFile;
import fr.gemao.view.Pattern;

/**
 * Servlet implementation class locationInstrumentServlet
 */
/*@WebServlet(Pattern.LOCATION_LOCATION_INTERNE)
/*public class LocationInterneServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

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
	private static String CATEGORIE = "categorie";
	private final String ATTR_AUTO_FAMILLES = "auto_familles";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1er passage : choix de la catégorie
		List<Categorie> listeCategorie = CategorieCtrl.recupererToutesCategories();
		HttpSession session = request.getSession();
		if(session.getAttribute(PARAM_ID_CATEGORIE)!=null){
			List<Materiel> listeMateriel = MaterielCtrl.recupererMaterielByCategorie((int) session.getAttribute(PARAM_ID_CATEGORIE));
			request.setAttribute(PARAM_LISTE_MATERIEL, listeMateriel);
		}
		request.setAttribute(PARAM_LISTE_CATEGORIE, listeCategorie);

		this.getServletContext().getRequestDispatcher(JSPFile.LOCATION_INTERNE).forward(request, response);
	}

}
*/