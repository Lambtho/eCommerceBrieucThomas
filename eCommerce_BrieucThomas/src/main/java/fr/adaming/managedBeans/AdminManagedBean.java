package fr.adaming.managedBeans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;
import fr.adaming.service.IAdminService;

@ManagedBean(name = "adminMb")
@SessionScoped
public class AdminManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Instanciation et injection de dépendance

	@ManagedProperty(value = "#{adminService}")
	IAdminService administrateurService;

	public void setAdministrateurService(IAdminService administrateurService) {
		this.administrateurService = administrateurService;
	}

	// Atributs
	AdminProd adminProd;
	Produit produit;
	Categorie categorie;

	String idCat;
	String idProd;
	int verif;
	String messageFailConnect;
	boolean afficheMessage;
	UploadedFile uploadedFile;

	List<Produit> listeProduits;
	List<Categorie> listeCategories;

	public AdminManagedBean() {

		this.adminProd = new AdminProd();
		this.produit = new Produit();
		this.categorie = new Categorie();
		this.listeProduits = new ArrayList<Produit>();
		this.listeCategories = new ArrayList<Categorie>();
	}

	// Getters & Setters
	public AdminProd getAdminProd() {
		return adminProd;
	}

	public void setAdminProd(AdminProd adminProd) {
		this.adminProd = adminProd;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
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

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	public int getVerif() {
		return verif;
	}

	public void setVerif(int verif) {
		this.verif = verif;
	}

	public String getIdCat() {
		return idCat;
	}

	public void setIdCat(String idCat) {
		this.idCat = idCat;
	}

	public String getIdProd() {
		return idProd;
	}

	public void setIdProd(String idProd) {
		this.idProd = idProd;
	}

	public String getMessageFailConnect() {
		return messageFailConnect;
	}

	public void setMessageFailConnect(String messageFailConnect) {
		this.messageFailConnect = messageFailConnect;
	}

	public boolean isAfficheMessage() {
		return afficheMessage;
	}

	public void setAfficheMessage(boolean afficheMessage) {
		this.afficheMessage = afficheMessage;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	// Méthodes
	@PostConstruct
	public void initFirstTime() {
		getAllProd();
		getAllCat();
		getFirstProd();

		this.afficheMessage = false;
	}

	public String retour() {

		return "Index";
	}

	public String connexion() {

		this.adminProd = administrateurService.isExistService(this.adminProd);

		if (this.adminProd != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrateur", adminProd);

			this.messageFailConnect = "";
			this.afficheMessage = false;
			return "ConnectSuccess";
		}

		this.adminProd = new AdminProd();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrateur", null);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Fail", "Les informations saisies sont invalides"));

		this.messageFailConnect = "Les informations saisies sont invalides";
		this.afficheMessage = true;

		return "ConnectFail";

	}

	public String deconnexion() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.adminProd = new AdminProd();

		return "AccueilAdmin";
	}

	// Méthode pour vérifier que l'utilisateur est bien connecté avant
	// d'afficher la page
	// Cette méthode est appellée par le header administrateur
	public void checkPermissions(ComponentSystemEvent event) throws IOException {

		// Récupère le contexte
		FacesContext fc = FacesContext.getCurrentInstance();

		// Récupère la session (n'en crée pas une nouvelle si elle n'existe pas)
		HttpSession httpSession = (HttpSession) (fc.getExternalContext().getSession(false));
		// Création d'une variable administrateur
		AdminProd adminSession = null;

		// Si la session existe
		if (httpSession != null) {
			// On met les infos administrateur dans la variable administrateur
			adminSession = (AdminProd) httpSession.getAttribute("administrateur");
		}

		// Si la session n'existe pas
		if (adminSession == null) {

			// Ajout d'un message dans le context
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous devez vous connecter pour accéder à l'espace administrateur"));

			// Redirection vers la page de login
			ExternalContext ec = fc.getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/admin/login.xhtml");
		}
	}

	// Verification de la connexion dans la page de login uniquement
	public void checkPermissionslogin(ComponentSystemEvent event) throws IOException {

		// Récupère le contexte
		FacesContext fc = FacesContext.getCurrentInstance();

		// Récupère la session
		HttpSession httpSession = (HttpSession) (fc.getExternalContext().getSession(false));

		// Création d'une variable administrateur
		AdminProd adminSession = null;

		// Si la session existe
		if (httpSession != null) {
			// On met les données de session de l'admin dans la variable
			// administrateur
			adminSession = (AdminProd) httpSession.getAttribute("agentSession");
		}

		// Si la session existe déjà, pas besoin de se logger donc redirection
		// sur l'accueil
		if (adminSession != null) {
			ExternalContext ec = fc.getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/admin/accueil.xhtml");
		}
	}

	public void getAllProd() {

		this.listeProduits = administrateurService.getAllProductService();

		for (Produit p : listeProduits) {

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
	}

	public void getAllCat() {

		this.listeCategories = administrateurService.getAllCategorieService();
	}

	public void refreshProd() {

		this.produit = administrateurService.getByIdProductService(Long.parseLong(this.idProd));
		trierListeCat();
	}

	public void getFirstProd() {

		this.produit = this.listeProduits.get(0);
		trierListeCat();

	}

	public void trierListeCat() {

		int index = 0;

		Categorie cat = this.produit.getCategorie();

		for (int i = 0; i < listeCategories.size(); i++) {
			Categorie c = listeCategories.get(i);
			if (c.getIdCategorie() == this.produit.getCategorie().getIdCategorie()) {
				index = i;
				break;
			}
		}

		this.listeCategories.remove(index);
		this.listeCategories.add(0, cat);

	}

	public void resetProd() {

		this.produit = new Produit();
	}

	public void reinitialiser() {

		getAllProd();
		getAllCat();
		getFirstProd();

	}

	public String addCategorie() {

		this.verif = administrateurService.addCategorie(this.categorie);

		reinitialiser();

		return "AccueilAdmin";
	}

	public String addProduit() {

		// On met la catégorie selectionnée dans le produit
		this.produit.setCategorie(administrateurService.getByIdCategorieService(Long.parseLong(this.idCat)));

		// On insert l'image si elle existe
		if (this.uploadedFile != null) {

			try {

				// On récupère le fichier uploadé
				InputStream input = uploadedFile.getInputstream();

				// Onle converti en fichier binaire
				byte[] image = IOUtils.toByteArray(input);

				// On l'ajoute au produit à insérer
				this.produit.setImage(image);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// Ajout du produit dans la BDD
		this.verif = administrateurService.addProductService(this.produit);

		reinitialiser();

		return "AccueilAdmin";
	}

	public String delProduit() {

		this.verif = administrateurService.delProductService(Long.parseLong(idProd));

		reinitialiser();

		return "AccueilAdmin";
	}

	public String upateProd() {

		// On insert l'image si elle existe
		if (this.uploadedFile != null) {

			try {

				// On récupère le fichier uploadé
				InputStream input = uploadedFile.getInputstream();

				// Onle converti en fichier binaire
				byte[] image = IOUtils.toByteArray(input);

				// On l'ajoute au produit à insérer
				this.produit.setImage(image);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		this.verif = administrateurService.updateProductService(this.produit);

		getAllProd();
		getAllCat();

		return "AccueilAdmin";
	}
}
