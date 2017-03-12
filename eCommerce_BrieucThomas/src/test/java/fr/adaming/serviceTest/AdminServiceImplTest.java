package fr.adaming.serviceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;
import fr.adaming.service.IAdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
@Transactional
public class AdminServiceImplTest {

	@Autowired
	IAdminService adminService;

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	@Test
	@Rollback(true)
	public void testAddProductService1(){
		int nombreProd = adminService.getAllProductService().size();
		
		Produit produit = new Produit("a", "b", 12, 42, false);
		
		adminService.addProductService(produit);
		
		assertEquals(nombreProd+1, adminService.getAllProductService().size());
	}


	@Test
	@Rollback(true)
	public void testAddProductService2(){
		
		Produit produit = new Produit("a", "b", 12, 42, false);
		
		adminService.addProductService(produit);
		
		List<Produit> listeProd = adminService.getAllProductService();
		Produit produitRetour = null;
		for(Produit p:listeProd){
			produitRetour = p;
		}
		
		assertEquals(produit.getDesignation(), produitRetour.getDesignation());
		assertEquals(produit.getDescription(), produitRetour.getDescription());
		assertEquals(produit.getListeQuantite(), produitRetour.getListeQuantite());
		assertEquals(new Double(produit.getPrix()), new Double(produitRetour.getPrix()));
		assertEquals(produit.isSelectionne(), produitRetour.isSelectionne());
	}
	
	
	@Test
	@Rollback(true)
	public void testDelProductService(){
		
		List<Produit> listeProd = adminService.getAllProductService();
		int nbrProduits = listeProd.size();
		
		Produit produitRetour = null;
		for(Produit p:listeProd){
			produitRetour = p;
		}
		adminService.delProductService(produitRetour.getIdProduit());
		
		assertEquals(nbrProduits-1, adminService.getAllProductService().size());
		
	}
	
	
//	@Test
//	@Rollback(true)
//	public void testUpdateProductService(){
//		
//		Produit produit1 = new Produit("a", "b", 12, 42, false);
//			
//		List<Produit> listeProd = adminService.getAllProductService();
//		
//		produit1.setIdProduit(listeProd.get(0).getIdProduit());
//		
//		adminService.updateProductService(produit1);
//		
//		Produit produitRetour = adminService.getByIdProductService(listeProd.get(0).getIdProduit());
//		
//		assertEquals(produit1.getDesignation(), produitRetour.getDesignation());
//		assertEquals(produit1.getDescription(), produitRetour.getDescription());
//		assertEquals(produit1.getListeQuantite(), produitRetour.getListeQuantite());
//		assertEquals(new Double(produit1.getPrix()), new Double(produitRetour.getPrix()));
//		assertEquals(produit1.isSelectionne(), produitRetour.isSelectionne());
//	}

	
	@Test
	@Rollback(true)
	public void testAddCatService1(){
		int nombreCat= adminService.getAllCategorieService().size();
		Categorie categorie = new Categorie("aaaa", "bbbb");
		
		adminService.addCategorie(categorie);
		
		assertEquals(nombreCat+1, adminService.getAllCategorieService().size());
	}


	@Test
	@Rollback(true)
	public void testAddCatService2(){
		
		Categorie categorie = new Categorie("aaaa", "bbbb");
		adminService.addCategorie(categorie);
		
		List<Categorie> listeCat= adminService.getAllCategorieService();
		Categorie cayegorieRetour = null;
		for(Categorie c:listeCat){
			cayegorieRetour = c;
		}
		
		assertEquals(categorie.getNomCategorie(), cayegorieRetour.getNomCategorie());
		assertEquals(categorie.getDescription(), cayegorieRetour.getDescription());
	}
}
