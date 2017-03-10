package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Client;
import fr.adaming.entities.Produit;

public interface IClientService {

	/**
	 * Récupérer les produits, à l'aide de l'identifiant du produit.
	 * 
	 * @param produit
	 *            l'objet produit
	 * @return listProdId la liste des produits possédant l'id correspondante
	 * 
	 * @author Thomas Ngo
	 * @see ClientServiceImpl#getProductByIdService(Produit)
	 */
	public Produit getProductByIdService(Produit produit);

	/**
	 * Récupérer les produits, à l'aide de mots clés.
	 * 
	 * @param keyWord
	 *            Le mot clé, chaine de caractère présent dans la désigantion du
	 *            produit
	 * @return listProd la liste des produits possédant la chaine de caractère
	 *         correspondante
	 * 
	 * @author Thomas Ngo
	 * @see ClientServiceImpl#getProductByKeyWordService(String)
	 */
	public List<Produit> getProductByKeyWordService(String keyWord, long id_categorie);

	/**
	 * Enregistrer un Client dans la base de données,
	 * 
	 * @param client
	 *            l'objet Client
	 * @return verif l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas Ngo
	 * @see ClientServiceImpl#orderService(Client)
	 */
	public int orderService(Client client);

}
