package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

public interface IAdminDao {
	/**
	 * Pour les attribut email et password, verifier si ils sont associés à un
	 * AdminProd
	 * 
	 * @param admin
	 *            l'AdminProd sur lequel ont effectue les verifications
	 * @return adminRetour L'AdminProd dont les attributs email et password sont
	 *         verifiés
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#isExistService(AdminProd)
	 *
	 */
	public AdminProd isExistDao(AdminProd administrateur);

	/**
	 * Ajouter un Produit à la base de données
	 * 
	 * @param prod
	 *            Le produit ajouter à la base de données
	 * @return verif l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#addProductService(Produit)
	 *
	 */
	public int addProductDao(Produit produit);

	/**
	 * Supprimer un Produit de la base de données, à l'aide de son identifiant
	 * 
	 * @param id_prod
	 *            l'id du produit à supprimer
	 * @return verif l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#delProductService(long)
	 *
	 */
	public int delProductDao(long id_prod);

	/**
	 * Modifier un Produit de la base de données
	 * 
	 * @param prod
	 *            le produit modifié
	 * @return verif l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#updateProductService(Produit)
	 *
	 */
	public int updateProductDao(Produit produit);

	/**
	 * Récupérer la liste des produits
	 * 
	 * @return listProd La liste des produits
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#getAllProductService()
	 *
	 */
	public List<Produit> getAllProductDao();

	/**
	 * Récupérer les produits, à l'aide de l'identifiant du produit.
	 * 
	 * @param id_prod
	 *            l'identifiant de l'objet Produit
	 * @return pr1 l'objet Produit
	 * 
	 * @author Thomas Ngô
	 * @see AdministrateurServiceImpl#getByIdProductService(long)
	 */
	public Produit getByIdProductDao(long id_prod);

	public Categorie getByIdCategorieDao(long id_cat);

	public List<Categorie> getAllCategorieDao();
	
	public int addCategorie(Categorie categorie);
}
