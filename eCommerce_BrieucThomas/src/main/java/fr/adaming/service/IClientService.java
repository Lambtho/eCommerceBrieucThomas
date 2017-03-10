package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Client;
import fr.adaming.entities.Produit;

public interface IClientService {

	/**
	 * R�cup�rer les produits, � l'aide de l'identifiant du produit.
	 * 
	 * @param produit
	 *            l'objet produit
	 * @return listProdId la liste des produits poss�dant l'id correspondante
	 * 
	 * @author Thomas Ngo
	 * @see ClientServiceImpl#getProductByIdService(Produit)
	 */
	public Produit getProductByIdService(Produit produit);

	/**
	 * R�cup�rer les produits, � l'aide de mots cl�s.
	 * 
	 * @param keyWord
	 *            Le mot cl�, chaine de caract�re pr�sent dans la d�sigantion du
	 *            produit
	 * @return listProd la liste des produits poss�dant la chaine de caract�re
	 *         correspondante
	 * 
	 * @author Thomas Ngo
	 * @see ClientServiceImpl#getProductByKeyWordService(String)
	 */
	public List<Produit> getProductByKeyWordService(String keyWord, long id_categorie);

	/**
	 * Enregistrer un Client dans la base de donn�es,
	 * 
	 * @param client
	 *            l'objet Client
	 * @return verif l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas Ngo
	 * @see ClientServiceImpl#orderService(Client)
	 */
	public int orderService(Client client);

}
