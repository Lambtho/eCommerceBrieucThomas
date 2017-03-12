package fr.adaming.controller;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;
import fr.adaming.service.IAdminService;
import fr.adaming.service.IClientService;

@Controller
@SessionAttributes("commande")
@RequestMapping("/client")
public class ClientController {

	@Autowired
	IClientService clientService;

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	@Autowired
	IAdminService administrateurService;

	public void setAdministrateurService(IAdminService administrateurService) {
		this.administrateurService = administrateurService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String accueil(ModelMap model, String keyword, String idCategorie) {

		if (idCategorie == null || idCategorie.equals(""))
			idCategorie = "0";
		if (keyword == null)
			keyword = "";

		List<Produit> listeProduits = clientService.getProductByKeyWordService(keyword, Long.parseLong(idCategorie));

		Commande commande = (Commande) model.get("commande");
		if (commande == null)
			commande = new Commande(Calendar.getInstance());

		List<LigneCommande> listelcmd = commande.getListeLignesCommandes();

		if (listelcmd == null)
			listelcmd = new ArrayList<LigneCommande>();

		// Pour chacun des poduits affichés
		for (int i = 0; i < listeProduits.size(); i++) {
			// Création de la liste déroulante de sélection de la quantité
			Produit p = listeProduits.get(i);

			// On récupère la quantité max de produits disponible
			int quantiteMax = p.getQuantite();

			// On soustrait a cette quantité le nombre de produits identiques
			// déjà ajoutés au panier
			for (int j = 0; j < listelcmd.size(); j++) {

				Produit produitPanier = listelcmd.get(j).getProduit();

				if (p.getIdProduit() == produitPanier.getIdProduit()) {
					quantiteMax -= (listelcmd.get(j).getQuantite());
				}
			}

			// On crée la liste d'entiers pour la sélection du produit
			List<Integer> listInt = new ArrayList<Integer>();
			if (quantiteMax > 0) {
				for (int j = 0; j <= quantiteMax; j++) {
					listInt.add(j);
				}
			} else {
				listInt.add(0);
			}

			// On associe cette liste au produit
			p.setListeQuantite(listInt);

			// On insert ce produit dans la liste de produits à afficher
			listeProduits.set(i, p);

			// Dans le cas où il y a une image
			if (p.getImage() != null) {
				// Création des liens pour les images
				try {

					// On crée le fichier (vide) qui recevra l'image dans le
					// fichier prévu à cet effet
					FileOutputStream fos = new FileOutputStream(
							FacesContext.getCurrentInstance().getExternalContext().getRealPath("imagesAffich")
									+ "/image" + p.getIdProduit() + ".jpg");

					// On écrit l'image dans le fichier créé
					fos.write(p.getImage());

					// On ferme le stream
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// Création de la liste de catégories
		// On recupère toutes les catégories dans la BDD
		List<Categorie> listeCategories = administrateurService.getAllCategorieService();

		// On ajpute la "catégorie" qui contient toutes les catégories
		Categorie categorieTous = new Categorie(0, "Tout", "Toutes nos catégories");
		listeCategories.add(0, categorieTous);

		// Sélection de la catégorie courrante
		for (Categorie cat : listeCategories) {

			if (cat.getIdCategorie() == Long.parseLong(idCategorie)) {
				model.put("categorie", cat);
				break;
			}
		}

		model.addAttribute("listeProduits", listeProduits);
		model.put("commande", commande);
		// Nombre de produits dans le panier
		model.put("nbrProduitPanier", listelcmd.size());
		model.put("listeCategories", listeCategories);
		model.put("keyword", keyword);
		model.put("idCategorie", idCategorie);

		return "client/accueil";
	}

	// Méthode pour ajouter un produit au panier
	@RequestMapping(value = "/ajoutPanier", method = RequestMethod.GET)
	public String selectProduct(ModelMap model, Long idProd, String keyword, String idCategorie) {

		int flagAjout = 0;

		Commande commande = (Commande) model.get("commande");
		if (commande == null) {
			commande = new Commande(Calendar.getInstance());
			model.addAttribute("commande", commande);
		}

		List<Produit> listeProduits = administrateurService.getAllProductService();

		List<LigneCommande> listelcmd = commande.getListeLignesCommandes();
		if (listelcmd == null)
			listelcmd = new ArrayList<LigneCommande>();

		// Si le produit existe et qu'il y en a suffisament en stock on accepte
		// de l'ajouter au panier

		for (Produit p : listeProduits) {
			if (p.getIdProduit() == idProd) {
				flagAjout = 1;
				for (LigneCommande lcmd : listelcmd) {
					if (lcmd.getProduit().getIdProduit() == idProd) {
						if (p.getQuantite() - lcmd.getQuantite() - 1 < 0) {
							flagAjout = 0;
							break;
						}
					}
				}
			}
		}

		// Si un produit est sélectionné
		if (flagAjout == 1) {

			int flagUpdate = 0;

			for (LigneCommande lcmd : listelcmd) {
				if (lcmd.getProduit().getIdProduit() == idProd) {
					lcmd.setQuantite(lcmd.getQuantite() + 1);
					lcmd.setPrix(lcmd.getPrix() + lcmd.getProduit().getPrix());
					flagUpdate = 1;
					break;
				}
			}

			if (flagUpdate == 0) {
				Produit p = new Produit();
				p.setIdProduit(idProd);

				p = clientService.getProductByIdService(p);

				LigneCommande lcmd = new LigneCommande(1, p.getPrix());
				lcmd.setProduit(p);

				// Association de la commande à la ligne de commande
				lcmd.setCommande(commande);

				listelcmd.add(lcmd);
			}

			commande.setListeLignesCommandes(listelcmd);

		}

		model.put("commande", commande);
		model.put("keyword", keyword);
		model.put("idCategorie", idCategorie);
		accueil(model, keyword, idCategorie);
		model.put("nbrProduitPanier", listelcmd.size());
		return "client/accueil";
	}

	// Récupération du panier
	@RequestMapping(value = "/afficherPanier", method = RequestMethod.GET)
	public ModelAndView getPanier(ModelMap model) {

		// Récupère le contenu du panier
		List<Produit> listeProduits = new ArrayList<Produit>();

		Commande commande = (Commande) model.get("commande");

		List<LigneCommande> listelcmd = commande.getListeLignesCommandes();

		// Pour chaque produit du panier on lui associe la quantitée
		// sélectionnée
		for (int i = 0; i < listelcmd.size(); i++) {
			Produit p = listelcmd.get(i).getProduit();
			p.setQuantite(listelcmd.get(i).getQuantite());
			listeProduits.add(p);
		}

		// Calcul du total
		double totalPanier = 0;
		for (int i = 0; i < listelcmd.size(); i++) {

			// Pour chaque produit du panier, on ajoute son orix total
			totalPanier += listelcmd.get(i).getPrix();
		}

		String desactiveCmd;
		if (totalPanier == 0) {
			desactiveCmd = "disabled=\"disabled\"";
		} else {
			desactiveCmd = "";
		}

		model.put("commande", commande);
		model.put("nbrProduitPanier", listelcmd.size());
		model.put("totalPanier", totalPanier);
		model.put("listeProduits", listeProduits);
		model.put("desactiveCmd", desactiveCmd);

		return new ModelAndView("client/panier", "command", new Client());

	}

	@RequestMapping(value = "/supprimerPanier", method = RequestMethod.GET)
	public ModelAndView deleteProduct(ModelMap model, Long idProd) {

		Commande commande = (Commande) model.get("commande");
		List<LigneCommande> listelcmd = commande.getListeLignesCommandes();
		Produit produit = new Produit();
		produit.setIdProduit(idProd);
		produit = clientService.getProductByIdService(produit);

		if (listelcmd != null) {
			if (listelcmd.size() > 0) {
				for (int i = 0; i < listelcmd.size(); i++) {

					Produit prodPanier = listelcmd.get(i).getProduit();
					if (prodPanier.getIdProduit() == produit.getIdProduit()) {
						listelcmd.remove(i);
						break;
					}
				}
			}
		}

		getPanier(model);
		commande.setListeLignesCommandes(listelcmd);
		model.put("commande", commande);
		model.put("nbrProduitPanier", listelcmd.size());

		return new ModelAndView("client/panier", "command", new Client());
	}

	@RequestMapping(value = "/passerCommande", method = RequestMethod.POST)
	public String commander(ModelMap model, @ModelAttribute("command") Client pClient) {

		Commande commande = (Commande) model.get("commande");

		commande.setClient(pClient);

		List<Commande> listeCmds = new ArrayList<Commande>();
		listeCmds.add(commande);
		pClient.setListeCommande(listeCmds);

		clientService.orderService(pClient);

		accueil(model, "", "");
		model.put("nbrProduitPanier", 0);

		return "client/accueil";
	}

	// Méthode pour récupérer la photo depuis la BDD
	@RequestMapping(value = "/photo", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long idProduit) throws IOException {

		// Récupère l'employé par id
		Produit produit = new Produit();
		produit.setIdProduit(idProduit);
		produit = clientService.getProductByIdService(produit);
		System.out.println(produit);

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
