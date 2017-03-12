package fr.adaming.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import fr.adaming.entities.Categorie;


public class AdminProd extends AdminController {

	
	//Methode Ajout de Produit--------------------------------------
		//Mise en place du Formulaire 
		@RequestMapping(value="formAddCat",method=RequestMethod.GET)
		public ModelAndView formulaireAddProduit(){
			return new ModelAndView("addCat","addCategorie",new Categorie());
		}

		//Soumission du Formulaire
		@RequestMapping(value="soumettreAddProduit",method=RequestMethod.POST)
		public String soumettreFormAddCategorie(Model model, Categorie categorie ){
		
					administrateurService.addCategorie(categorie);		
		
					reinitialiser();
			return "AccueilAdmin";
}
		
		
		
}		