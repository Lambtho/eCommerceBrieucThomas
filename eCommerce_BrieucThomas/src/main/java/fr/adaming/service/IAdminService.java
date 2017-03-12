package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

public interface IAdminService {
	
	
	public int addCategorie(Categorie categorie);
	
	
	/**
	 * Ajouter un Produit à la base de données
	 * @param prod
	 * 			Le produit ajouter à la base de données
	 * @return verif
	 * 			l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#addProductService(Produit)
	 *
	 */
	public int addProductService(Produit produit);
	

	/**
	 * Supprimer un Produit de la base de données, à l'aide de son identifiant
	 * @param id_prod
	 * 			l'id du produit à supprimer
	 * @return verif
	 * 			l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#delProductService(long)
	 *
	 */
	public int delProductService(long id_prod);
	

	/**
	 * Modifier un Produit de la base de données
	 * @param prod
	 * 			le produit modifié
	 * @return verif
	 * 			l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#updateProductService(Produit)
	 *
	 */
	public int updateProductService(Produit produit);
	

	/**
	 * Récupérer la liste des produits 
	 * @return listProd
	 * 			La liste des produits
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#getAllProductService()
	 *
	 */
	public List<Produit> getAllProductService();
	
	

	/**
	 * Récupérer les produits, 
	 * à l'aide de l'identifiant du produit.
	 * @param id_prod
	 * 			l'identifiant de l'objet Produit
	 * @return pr1
	 * 			l'objet Produit
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#getByIdProductService(long)
	 */
	public Produit getByIdProductService(long id_prod);
	
	
	public Categorie getByIdCategorieService(long id_cat);
	
	public List<Categorie> getAllCategorieService();


}
