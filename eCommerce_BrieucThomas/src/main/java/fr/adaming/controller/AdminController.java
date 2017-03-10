package fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.adaming.entities.Produit;
import fr.adaming.service.IAdminService;

@Controller
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
		
		model.addAttribute("listeProduits", listeProduits);


		return "admin/accueil";
	}

}
