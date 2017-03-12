package fr.adaming.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;
import fr.adaming.service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService administrateurService;
	
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

	public void setAdministrateurService(IAdminService administrateurService) {
		this.administrateurService = administrateurService;
	}

	
	
	
	public AdminProd getAdminProd() {
		return adminProd;
	}




	public void setAdminProd(AdminProd adminProd) {
		this.adminProd = adminProd;
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




	public int getVerif() {
		return verif;
	}




	public void setVerif(int verif) {
		this.verif = verif;
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




	public IAdminService getAdministrateurService() {
		return administrateurService;
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

		getAllCat();
		getAllProd();
		getFirstProd();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String accueil(ModelMap model) {
		System.out.println("coucou");
		List<Produit> listeProduits = administrateurService.getAllProductService();
		
		model.addAttribute("listeProduits", listeProduits);


		return "admin/accueil";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public void getAllCat(ModelMap model){
		
		List<Categorie> listCat=administrateurService.getAllCategorieService();
		model.addAttribute("listeCategorie",listCat);
		
		
	}
	//Je fais les Déja les méthode Principale (celle que je comprend ^^)
	
	
	//Methode Ajout de Produit--------------------------------------
	//Mise en place du Formulaire 
	@RequestMapping(value="formAddProd",method=RequestMethod.GET)
	public ModelAndView formulaireAddProduit(){
		return new ModelAndView("addProd","addProduit",new Produit());
	}

	//Soumission du Formulaire
	@RequestMapping(value="soumettreAddProduit",method=RequestMethod.POST)
	public String soumettreFormAddProduit(Model model, Produit pProduit){
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
				
				pProduit=produit;
		
		administrateurService.addProductService(pProduit);
				reinitialiser();
		return "AccueilAdmin";
		
	}
	
	//---------------------Delete Produit
	//mise en place du Formulaire RMQ on utilise une methode utilisant le paramètre id mis en place dans l'url
	
	@RequestMapping(value="formSupProduit",method=RequestMethod.GET)
	public String formSupProduit(){
		
		return "delProd";	
	}
	//Soumission du Formulaire
	@RequestMapping(value="soumettreSupProduit",method=RequestMethod.GET)
	public String soumettreSupProduit(Model model,@RequestParam("id_param") int id ){
		
		this.verif = administrateurService.delProductService(id);
		
		reinitialiser();
		return "AccueilAdmin";
		
	}
	
	
	//--------------------Modification Produit---------------------
	
	//Mise en place du Formulaire
	@RequestMapping(value="formModifProduit",method=RequestMethod.GET)
	public ModelAndView formModifProduit(){
		return new ModelAndView("updateProd","ModifProduit",new Produit());
	}
	
	//Soumission du formulaire
	
	public String soumettreModifProduit(Model model,Produit pProduit){
		
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
		pProduit=produit;
		administrateurService.updateProductService(pProduit);
		reinitialiser();
		return "AccueilAdmin";
		
	}
	
	
	
}
