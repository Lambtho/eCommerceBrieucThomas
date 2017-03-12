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

import fr.adaming.dao.IAdminDao;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
@Transactional
public class AdminDaoImplTest {

	@Autowired
	IAdminDao adminDao;

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Test
	@Rollback(true)
	public void testAddProductDao1(){
		int nombreProd = adminDao.getAllProductDao().size();
		
		Produit produit = new Produit("a", "b", 12, 42, false);
		
		adminDao.addProductDao(produit);
		
		assertEquals(nombreProd+1, adminDao.getAllProductDao().size());
	}


	@Test
	@Rollback(true)
	public void testAddProductDao2(){
		
		Produit produit = new Produit("a", "b", 12, 42, false);
		
		adminDao.addProductDao(produit);
		
		List<Produit> listeProd = adminDao.getAllProductDao();
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
	public void testDelProductDao(){
		
		List<Produit> listeProd = adminDao.getAllProductDao();
		int nbrProduits = listeProd.size();
		
		Produit produitRetour = null;
		for(Produit p:listeProd){
			produitRetour = p;
		}
		adminDao.delProductDao(produitRetour.getIdProduit());
		
		assertEquals(nbrProduits-1, adminDao.getAllProductDao().size());
		
	}
	
	
//	@Test
//	@Rollback(true)
//	public void testUpdateProductDao(){
//		
//		Produit produit1 = new Produit("a", "b", 12, 42, false);
//			
//		List<Produit> listeProd = adminDao.getAllProductDao();
//		
//		produit1.setIdProduit(listeProd.get(0).getIdProduit());
//		
//		adminDao.updateProductDao(produit1);
//		
//		Produit produitRetour = adminDao.getByIdProductDao(listeProd.get(0).getIdProduit());
//		
//		assertEquals(produit1.getDesignation(), produitRetour.getDesignation());
//		assertEquals(produit1.getDescription(), produitRetour.getDescription());
//		assertEquals(produit1.getListeQuantite(), produitRetour.getListeQuantite());
//		assertEquals(new Double(produit1.getPrix()), new Double(produitRetour.getPrix()));
//		assertEquals(produit1.isSelectionne(), produitRetour.isSelectionne());
//	}

	
	@Test
	@Rollback(true)
	public void testAddCatDao1(){
		int nombreCat= adminDao.getAllCategorieDao().size();
		Categorie categorie = new Categorie("aaaa", "bbbb");
		
		adminDao.addCategorie(categorie);
		
		assertEquals(nombreCat+1, adminDao.getAllCategorieDao().size());
	}


	@Test
	@Rollback(true)
	public void testAddCatDao2(){
		
		Categorie categorie = new Categorie("aaaa", "bbbb");
		adminDao.addCategorie(categorie);
		
		List<Categorie> listeCat= adminDao.getAllCategorieDao();
		Categorie cayegorieRetour = null;
		for(Categorie c:listeCat){
			cayegorieRetour = c;
		}
		
		assertEquals(categorie.getNomCategorie(), cayegorieRetour.getNomCategorie());
		assertEquals(categorie.getDescription(), cayegorieRetour.getDescription());
	}
}
