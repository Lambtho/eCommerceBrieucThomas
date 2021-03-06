package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

public interface IAdminDao {
	

	/**
	 * Ajouter un Produit � la base de donn�es
	 * 
	 * @param prod
	 *            Le produit ajouter � la base de donn�es
	 * @return verif l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas Ng�
	 * @see AdministrateurServiceImpl#addProductService(Produit)
	 *
	 */
	public int addProductDao(Produit produit);

	/**
	 * Supprimer un Produit de la base de donn�es, � l'aide de son identifiant
	 * 
	 * @param id_prod
	 *            l'id du produit � supprimer
	 * @return verif l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas Ng�
	 * @see AdministrateurServiceImpl#delProductService(long)
	 *
	 */
	public int delProductDao(long id_prod);

	/**
	 * Modifier un Produit de la base de donn�es
	 * 
	 * @param prod
	 *            le produit modifi�
	 * @return verif l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas Ng�
	 * @see AdministrateurServiceImpl#updateProductService(Produit)
	 *
	 */
	public int updateProductDao(Produit produit);

	/**
	 * R�cup�rer la liste des produits
	 * 
	 * @return listProd La liste des produits
	 * 
	 * @author Thomas Ng�
	 * @see AdministrateurServiceImpl#getAllProductService()
	 *
	 */
	public List<Produit> getAllProductDao();

	/**
	 * R�cup�rer les produits, � l'aide de l'identifiant du produit.
	 * 
	 * @param id_prod
	 *            l'identifiant de l'objet Produit
	 * @return pr1 l'objet Produit
	 * 
	 * @author Thomas Ng�
	 * @see AdministrateurServiceImpl#getByIdProductService(long)
	 */
	public Produit getByIdProductDao(long id_prod);

	public Categorie getByIdCategorieDao(long id_cat);

	public List<Categorie> getAllCategorieDao();
	
	public int addCategorie(Categorie categorie);
}
