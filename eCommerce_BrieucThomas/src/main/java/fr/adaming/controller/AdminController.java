package fr.adaming.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

		return "/admin/accueil";
	}
	
	@RequestMapping(value="getAllCat", method=RequestMethod.GET)
	public void getAllCat(ModelMap model){
		
		List<Categorie> listCat=administrateurService.getAllCategorieService();
		model.put("listeCategorie",listCat);
		
		
	}

	
	
	//Methode Ajout de Produit--------------------------------------
	//Mise en place du Formulaire 
	@RequestMapping(value="/formAddProd",method=RequestMethod.GET)
	public ModelAndView formulaireAddProduit(Model model){
		System.out.println("je passe par ce controller");
		
		List<Categorie> listCat=administrateurService.getAllCategorieService();
		
		model.addAttribute("listeCategorie",listCat);
		
		return new ModelAndView("admin/addProd","produitForm",new Produit());
	}

	//Soumission du Formulaire
	@RequestMapping(value="/soumettreAddProduit",method=RequestMethod.POST)
	@ModelAttribute("produitForm")
	public String soumettreFormAddProduit(ModelMap model, Produit produit, String idCategorie){
		System.out.println(idCategorie);
		if(produit.getIdProduit()==0){
			// On met la catégorie selectionnée dans le produit
			produit.setCategorie(administrateurService.getByIdCategorieService(Long.parseLong(idCategorie)));
			
			produit.setImage(null);
			
			administrateurService.addProductService(produit);
			}else{
				// On met la catégorie selectionnée dans le produit
				produit.setCategorie(administrateurService.getByIdCategorieService(Long.parseLong(idCategorie)));
				
				produit.setImage(null);
				administrateurService.updateProductService(produit);
			}	
		
				
		List<Categorie> listCat=administrateurService.getAllCategorieService();
		model.put("listeCategorie",listCat);
			
		System.out.println("je met a jour la liste produit");
		return "/admin/accueil";
		
	}
	
	//---------------------Delete Produit

	//Soumission du Formulaire
	@RequestMapping(value="/soumettreSupProduit",method=RequestMethod.GET)
	public String soumettreSupProduit(ModelMap model,@RequestParam("id_param") long id ){
		System.out.println(id);
		administrateurService.delProductService(id);
		
		
		System.out.println("je met a jour la liste produit");
		return "/admin/accueil";
		
	}
	
	
	//--------------------Modification Produit---------------------
	
	//Mise en place du Formulaire
	@RequestMapping(value="/formModifProduit",method=RequestMethod.GET)
	public String formModifProduit(ModelMap model,@RequestParam("id_param") long idProduit){
		System.out.println(idProduit);
		Produit produit=administrateurService.getByIdProductService(idProduit);
		model.addAttribute("produitForm", produit);
		
		return "admin/addProd";
	}
	
	//Methode Ajout de Categorie--------------------------------------
			//Mise en place du Formulaire 
			@RequestMapping(value="formAddCat",method=RequestMethod.GET)
			public ModelAndView formulaireAddProduit(){
				System.out.println("je passe par ce controller");
				return new ModelAndView("admin/addCat","addCategorie",new Categorie());
			}

			//Soumission du Formulaire
			@RequestMapping(value="soumettreAddCat",method=RequestMethod.POST)
			public String soumettreFormAddCategorie(Model model, Categorie categorie ){
			
						administrateurService.addCategorie(categorie);		
			
						
				return "/admin/accueil";
	}
			
	
	
	
}
