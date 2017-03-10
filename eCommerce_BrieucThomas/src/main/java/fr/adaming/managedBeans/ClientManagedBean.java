package fr.adaming.managedBeans;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;
import fr.adaming.service.IAdminService;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clientMb")
@SessionScoped
public class ClientManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Instanciation et injection de dépendance
	@ManagedProperty(value = "#{adminService}")
	IAdminService administrateurService;

	public void setAdministrateurService(IAdminService administrateurService) {
		this.administrateurService = administrateurService;
	}

	@ManagedProperty(value = "#{clientService}")
	IClientService clientService;

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	// Attributs
	Produit produit;
	Produit produitPrix;
	Categorie categorie;
	Commande commande;
	LigneCommande ligneCommande;
	Client client;

	String quantiteSelect;
	int quantiteSelectInt;
	int nbrProduitPanier;
	double totalPanier;
	String lienFacture;
	String keyword;
	String idCategorie;
	boolean desactiveCmd;

	List<Produit> listeProduits;
	List<Categorie> listeCategories;
	List<LigneCommande> listelcmd;

	public ClientManagedBean() {
		initialisation();

		this.keyword = "";
		this.idCategorie = "0";
		this.desactiveCmd = true;

	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public String getQuantiteSelect() {
		return quantiteSelect;
	}

	public void setQuantiteSelect(String quantiteSelect) {
		this.quantiteSelect = quantiteSelect;
	}

	public Produit getProduitPrix() {
		return produitPrix;
	}

	public void setProduitPrix(Produit produitPrix) {
		this.produitPrix = produitPrix;
	}

	public List<LigneCommande> getListelcmd() {
		return listelcmd;
	}

	public void setListelcmd(List<LigneCommande> listelcmd) {
		this.listelcmd = listelcmd;
	}

	public int getQuantiteSelectInt() {
		return quantiteSelectInt;
	}

	public void setQuantiteSelectInt(int quantiteSelectInt) {
		this.quantiteSelectInt = quantiteSelectInt;
	}

	public int getNbrProduitPanier() {
		return nbrProduitPanier;
	}

	public void setNbrProduitPanier(int nbrProduitPanier) {
		this.nbrProduitPanier = nbrProduitPanier;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getTotalPanier() {
		return totalPanier;
	}

	public void setTotalPanier(double totalPanier) {
		this.totalPanier = totalPanier;
	}

	public String getLienFacture() {
		return lienFacture;
	}

	public void setLienFacture(String lienFacture) {
		this.lienFacture = lienFacture;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(String idCategorie) {
		this.idCategorie = idCategorie;
	}

	public boolean isDesactiveCmd() {
		return desactiveCmd;
	}

	public void setDesactiveCmd(boolean desactiveCmd) {
		this.desactiveCmd = desactiveCmd;
	}

	// Methodes
	public void initialisation() {

		//Initialisation des variables
		this.produit = new Produit();
		this.produitPrix = new Produit();
		this.categorie = new Categorie();
		this.client = new Client();

		this.listeCategories = new ArrayList<Categorie>();
		this.listeProduits = new ArrayList<Produit>();
		this.listelcmd = new ArrayList<LigneCommande>();

		//On crée une nouvelle commande
		this.ligneCommande = new LigneCommande();
		this.commande = new Commande(Calendar.getInstance());
		this.client.setListeCommande(new ArrayList<Commande>());
	}

	
	//Mé&thode pour récupérer tous les produits
	public void getAllProd() {

		//Récupère la liste de tous les produits contenant le mot clef et etant de la catégorie selectionnée
		this.listeProduits = clientService.getProductByKeyWordService(this.keyword, Long.parseLong(this.idCategorie));

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

			//On crée la liste d'entiers pour la sélection du produit
			List<Integer> listInt = new ArrayList<Integer>();
			if (quantiteMax > 0) {
				for (int j = 0; j <= quantiteMax; j++) {
					listInt.add(j);
				}
			} else {
				listInt.add(0);
			}
			
			//On associe cette liste au produit
			p.setListeQuantite(listInt);

			//On insert ce produit dans la liste de produits à afficher
			this.listeProduits.set(i, p);
			
			//Dans le cas où il y a une image
			if (p.getImage() != null) {
				// Création des liens pour les images
				try {
					
					//On crée le fichier (vide) qui recevra l'image dans le fichier prévu à cet effet
					FileOutputStream fos = new FileOutputStream(
							FacesContext.getCurrentInstance().getExternalContext().getRealPath("imagesAffich")
									+ "/image" + p.getIdProduit() + ".jpg");
					
					//On écrit l'image dans le fichier créé
					fos.write(p.getImage());
					
					//On ferme le stream
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// Nombre de produits dans le panier
		this.nbrProduitPanier = listelcmd.size();

		// Création de la liste de catégories
		//On recupère toutes les catégories dans la BDD
		this.listeCategories = administrateurService.getAllCategorieService();
		
		//On ajpute la "catégorie" qui contient toutes les catégories
		Categorie categorieTous = new Categorie(0, "Tout", "Toutes nos catégories");
		this.listeCategories.add(0, categorieTous);
		
		//Sélection de la catégorie courrante
		for (Categorie cat : listeCategories) {

			if (cat.getIdCategorie() == Long.parseLong(idCategorie)) {
				this.categorie = cat;
				break;
			}
		}

	}

	
	// Récupération du panier
	public void getPanier() {

		// Récupère le contenu du panier
		List<Produit> lprod = new ArrayList<Produit>();
		
		//Pour chaque produit du panier on lui associe la quantitée sélectionnée
		for (int i = 0; i < listelcmd.size(); i++) {
			Produit p = listelcmd.get(i).getProduit();
			p.setQuantite(this.listelcmd.get(i).getQuantite());
			lprod.add(p);
		}
		//Et on remet le tout dans la liste
		this.listeProduits = lprod;

		// Calcul du total
		this.totalPanier = 0;
		for (int i = 0; i < listelcmd.size(); i++) {

			//Pour chaque produit du panier, on ajoute son orix total
			this.totalPanier += listelcmd.get(i).getPrix();
		}

		
		//Si le total du panier est de zéro, on désactive le bouton de commande
		if (this.totalPanier == 0) {
			this.desactiveCmd = true;
		} else {
			this.desactiveCmd = false;
		}
	}

	//Récupération de la quantité de produit sélectionnée (et conversion en integer)
	public void updateQuantite() {

		this.quantiteSelectInt = Integer.parseInt(this.quantiteSelect);
	}

	
	//Méthode pour ajouter un produit au panier
	public String selectProduct() {

		//Si un produit est sélectionné
		if (quantiteSelectInt > 0) {
			int flagUpdate = 0;

			//Calcul du total par produit
			double prixTot = quantiteSelectInt * this.produit.getPrix();

			//Création du nouvelle ligne de commande
			this.ligneCommande = new LigneCommande(quantiteSelectInt, prixTot);
			this.ligneCommande.setProduit(this.produit);
			this.ligneCommande.setCommande(this.commande);

			//Ajout d'un produit au panier s'il est déjà existant (mise à jour de la quantité et du prix)
			for (int i = 0; i < listelcmd.size(); i++) {

				LigneCommande lcmd = listelcmd.get(i);

				if (lcmd.getProduit().getIdProduit() == this.produit.getIdProduit()) {
					lcmd.setPrix(lcmd.getPrix() + prixTot);
					lcmd.setQuantite(lcmd.getQuantite() + quantiteSelectInt);
					this.ligneCommande = lcmd;
					flagUpdate = 1;
					break;
				}
			}

			//Ajout d'un nouveau produit au panier
			if (flagUpdate == 0) {
				// Association de la commande à la ligne de commande
				this.ligneCommande.setCommande(this.commande);

				this.listelcmd.add(ligneCommande);
			}
		}
		return null;
	}

	public String deleteProduct() {

		for (int i = 0; i < listelcmd.size(); i++) {

			Produit prodPanier = this.listelcmd.get(i).getProduit();
			if (prodPanier.getIdProduit() == this.produit.getIdProduit()) {
				this.listelcmd.remove(i);
				break;
			}
		}

		// Nombre de produits dans le panier
		this.nbrProduitPanier = listelcmd.size();

		return null;
	}

	public String commander() {

		// On associe le client à la commande
		this.commande.setClient(this.client);

		// On associe les lignes de commande a la commande
		this.commande.setListeLignesCommandes(this.listelcmd);

		// Création de la liste des commandes passées par le client
		List<Commande> listCmd = this.client.getListeCommande();

		// Ajout de la commande passée dans la liste de toutes les commandes
		listCmd.add(this.commande);

		// Association du client et des commandes passées
		this.client.setListeCommande(listCmd);

		// Enregistrement de la commande dans la BDD
		clientService.orderService(client);

		// débiter les stocks pour chacun des produits commandés
		for (LigneCommande lcmd : this.listelcmd) {

			// On récupère un produit de la liste des lignes de commande
			Produit p = new Produit();
			p = lcmd.getProduit();

			// On récupère la quantité du produit acheté par le client
			int quantt = p.getQuantite();

			// On récupère la quantité de produits disponible dans la BDD
			int quanttStock = clientService.getProductByIdService(p).getQuantite();

			// Nombre de produits restants après l'achat
			p.setQuantite(quanttStock - quantt);

			// Mise à jour du produit dans la BDD après achat
			administrateurService.updateProductService(p);

		}

		generatePdf();

		// Réinitialisation des variables après les variables (même chose que
		// dans le constructeur vide)
		initialisation();

		// On met la liste de tous les produits dans la liste à afficher
		this.listeProduits = administrateurService.getAllProductService();
		this.nbrProduitPanier = 0;
		this.totalPanier = 0;

		return "Facture";
	}

	@SuppressWarnings("deprecation")
	public void generatePdf() {

		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("factures");

		this.lienFacture = "factures/facture" + this.commande.getIdCommande() + ".pdf";
		String pathPdf = path + "/facture" + this.commande.getIdCommande() + ".pdf";

		Document document = new Document(PageSize.A4);
		try {

			PdfWriter.getInstance(document, new FileOutputStream(pathPdf));

			document.addTitle("Facture de la commande n°" + this.commande.getIdCommande());

			document.open();

			Paragraph titre = new Paragraph("Facture de la commande n°" + this.commande.getIdCommande());
			titre.setAlignment(Element.ALIGN_CENTER);

			document.add(titre);

			Phrase nomCl = new Phrase(client.getNomClient() + "\n");
			Phrase adresseCl = new Phrase(client.getAdresse() + "\n");
			Phrase emailCl = new Phrase(client.getEmail() + "\n");
			Phrase telephoneCl = new Phrase(client.getTel() + "\n");

			Paragraph coordonneesCl = new Paragraph("Coordonées\n");
			coordonneesCl.add(nomCl);
			coordonneesCl.add(adresseCl);
			coordonneesCl.add(emailCl);
			coordonneesCl.add(telephoneCl);

			document.add(coordonneesCl);

			Table tableauPanier = new Table(6);
			tableauPanier.setPadding(2);
			tableauPanier.setDefaultCellBorderColor(Color.GRAY);
			tableauPanier.setCellpadding(2);

			// En-têtes

			new Font(Font.HELVETICA, 11, Font.BOLD);
			Cell cell1 = new Cell("");
			cell1.setHeader(true);
			tableauPanier.addCell(cell1);
			tableauPanier.addCell("Désignation");
			tableauPanier.addCell("Catégorie");
			tableauPanier.addCell("Quantité");
			tableauPanier.addCell("Prix Unitaire (€)");
			tableauPanier.addCell("Prix Total (€)");

			for (int i = 0; i < listelcmd.size(); i++) {

				tableauPanier.addCell(String.valueOf(i + 1));
				tableauPanier.addCell(this.listelcmd.get(i).getProduit().getDesignation());
				tableauPanier.addCell(this.listelcmd.get(i).getProduit().getCategorie().getNomCategorie());
				tableauPanier.addCell(String.valueOf(this.listelcmd.get(i).getQuantite()));
				tableauPanier.addCell(String.valueOf(this.produit.getPrix()));
				tableauPanier.addCell(String.valueOf(this.listelcmd.get(i).getPrix()));
			}

			Paragraph recapCmd = new Paragraph("\n\n\nRécapitulatif de la commande",
					new Font(Font.TIMES_ROMAN, 15, Font.BOLD));

			recapCmd.add(tableauPanier);

			document.add(recapCmd);

			Paragraph total = new Paragraph("Total dû :" + totalPanier + " €");

			document.add(total);

		} catch (DocumentException de) {
			de.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		document.close();

	}

}
