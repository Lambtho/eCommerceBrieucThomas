package fr.adaming.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;



import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;
import fr.adaming.service.IAdminService;

@Controller
@SessionAttributes({"listeProduit","listeCategorie"})
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService administrateurService;
	
	
	
	public void setAdministrateurService(IAdminService administrateurService) {
		this.administrateurService = administrateurService;
	}

	


	@RequestMapping(method = RequestMethod.GET)
	public String accueil(ModelMap model) {
		System.out.println("coucou");
		List<Produit> listeProduits = administrateurService.getAllProductService();
		
		model.put("listeProduit", listeProduits);
		System.out.println("je passe par accueil admin");

		return "admin/accueil";
	}
	
	@RequestMapping(value="getAllCat", method=RequestMethod.GET)
	public void getAllCat(ModelMap model){
		
		List<Categorie> listCat=administrateurService.getAllCategorieService();
		model.put("listeCategorie",listCat);
		
		
	}

	
	
	//Methode Ajout de Produit--------------------------------------
	//Mise en place du Formulaire 
	@RequestMapping(value="formAddProd",method=RequestMethod.GET)
	public ModelAndView formulaireAddProduit(Model model){
		System.out.println("je passe par ce controller");
		
		List<Categorie> listCat=administrateurService.getAllCategorieService();
		
		model.addAttribute("listeCategorie",listCat);
		
		return new ModelAndView("admin/addProd","addProduit",new Produit());
	}

	//Soumission du Formulaire
	@RequestMapping(value="soumettreAddProduit",method=RequestMethod.POST)
	public String soumettreFormAddProduit(Model model, Produit produit, String idCategorie){
		System.out.println(idCategorie);
			
		
		// On met la catégorie selectionnée dans le produit
		produit.setCategorie(administrateurService.getByIdCategorieService(Long.parseLong(idCategorie)));
		
		produit.setImage(null);
		
				
			
		
		administrateurService.addProductService(produit);
				
		return "admin/accueil";
		
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
		
		int verif = administrateurService.delProductService(id);
		
		
		return "admin/accueil";
		
	}
	
	
	//--------------------Modification Produit---------------------
	
	//Mise en place du Formulaire
	@RequestMapping(value="formModifProduit",method=RequestMethod.GET)
	public ModelAndView formModifProduit(){
		return new ModelAndView("updateProd","ModifProduit",new Produit());
	}
	
	//Soumission du formulaire
	
	public String soumettreModifProduit(Model model,Produit produit,UploadedFile uploadedFile){
		
		// On insert l'image si elle existe
				if (uploadedFile != null) {

					try {

						// On récupère le fichier uploadé
						InputStream input = uploadedFile.getInputstream();

						// Onle converti en fichier binaire
						byte[] image = IOUtils.toByteArray(input);

						// On l'ajoute au produit à insérer
						produit.setImage(image);

					} catch (IOException e) {
						e.printStackTrace();
					}

				}
		
		administrateurService.updateProductService(produit);
		
		return "admin/accueil";
		
	}
	
	
	
}
