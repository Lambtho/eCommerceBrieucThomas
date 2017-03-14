package fr.adaming.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;
import fr.adaming.service.IAdminService;
import fr.adaming.service.IClientService;

@Controller
@SessionAttributes({ "listeProduit", "listeCategorie" })
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService administrateurService;

	public void setAdministrateurService(IAdminService administrateurService) {
		this.administrateurService = administrateurService;
	}

	@Autowired
	IClientService clientService;

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView accueil(ModelMap model) {

		List<Produit> listeProduits = administrateurService.getAllProductService();

		model.put("listeProduit", listeProduits);

		List<Categorie> listCat = administrateurService.getAllCategorieService();
		model.put("listeCategorie", listCat);

		model.addAttribute("listeCategorie", listCat);

		List<Produit> listeProduitsAjout = new ArrayList<>();
		Produit p = new Produit("", "", 0, 0, false);
		listeProduitsAjout.add(0, p);
		for (Produit prd : listeProduits) {
			listeProduitsAjout.add(prd);
		}

		model.addAttribute("listeProduitAjout", listeProduitsAjout);

		return new ModelAndView("admin/accueil", "produitForm", new Produit());

	}

	@RequestMapping(value = "getAllCat", method = RequestMethod.GET)
	public void getAllCat(ModelMap model) {

		List<Categorie> listCat = administrateurService.getAllCategorieService();
		model.put("listeCategorie", listCat);

	}

	// Methode Ajout de Produit--------------------------------------
	// Mise en place du Formulaire
	@RequestMapping(value = "/formAddProd", method = RequestMethod.GET)
	public ModelAndView formulaireAddProduit(Model model) {

		List<Categorie> listCat = administrateurService.getAllCategorieService();

		model.addAttribute("listeCategorie", listCat);

		return new ModelAndView("admin/addProd", "produitForm", new Produit());
	}

	// Soumission du Formulaire
	@RequestMapping(value = "/soumettreAddProduit", method = RequestMethod.POST)
	public String soumettreFormAddProduit(ModelMap model, @ModelAttribute("produitForm") Produit produit,
			String idCategorie, String idProduit, String description, String designation, String prix, String quantite,
			MultipartFile file) {

		// On met la catégorie selectionnée dans le produit
		produit.setCategorie(administrateurService.getByIdCategorieService(Long.parseLong(idCategorie)));

		produit.setIdProduit(Long.parseLong(idProduit));
		produit.setDescription(description);
		produit.setDesignation(designation);
		produit.setPrix(Double.parseDouble(prix));
		produit.setQuantite(Integer.parseInt(quantite));

		// Récupération de l'image
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				produit.setImage(bytes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (produit.getIdProduit() != 0) {
				produit.setImage(administrateurService.getByIdProductService(produit.getIdProduit()).getImage());
			}
		}

		if (produit.getIdProduit() == 0) {
			administrateurService.addProductService(produit);
		} else {
			administrateurService.updateProductService(produit);
		}

		accueil(model);

		return "admin/accueil";

	}

	// ---------------------Delete Produit

	// Soumission du Formulaire
	@RequestMapping(value = "/soumettreSupProduit", method = RequestMethod.GET)
	public String soumettreSupProduit(ModelMap model, @RequestParam("id_param") long id) {
		administrateurService.delProductService(id);

		accueil(model);

		return "admin/accueil";

	}

	// --------------------Modification Produit---------------------

	// Mise en place du Formulaire
	@RequestMapping(value = "/formModifProduit", method = RequestMethod.GET)
	public String formModifProduit(ModelMap model, @RequestParam("id_param") long idProduit) {

		Produit produit = administrateurService.getByIdProductService(idProduit);
		model.addAttribute("produitForm", produit);

		return "admin/addProd";
	}

	// Methode Ajout de Categorie--------------------------------------
	// Mise en place du Formulaire
	@RequestMapping(value = "/cat/formAddCat", method = RequestMethod.GET)
	public ModelAndView formulaireAddProduit() {

		return new ModelAndView("admin/addCat", "addCategorie", new Categorie());
	}

	// Soumission du Formulaire
	@RequestMapping(value = "/cat/soumettreAddCat", method = RequestMethod.POST)
	public String soumettreFormAddCategorie(ModelMap model, Categorie categorie, String nomCategorie,
			String description) {

		administrateurService.addCategorie(categorie);

		accueil(model);

		return "admin/accueil";
	}

	// Méthode pour récupérer la photo depuis la BDD
	@RequestMapping(value = "/photo", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long idProduit) throws IOException {

		// Récupère l'employé par id
		Produit produit = new Produit();
		produit.setIdProduit(idProduit);
		produit = clientService.getProductByIdService(produit);

		// S'il n'y a pas de photo associée à l'employé
		if (produit.getImage() == null) {

			// On ne retourne rien
			return new byte[0];
		} else {

			// Sinon, on retoune la photo
			return IOUtils.toByteArray(new ByteArrayInputStream(produit.getImage()));
		}

	}

}
