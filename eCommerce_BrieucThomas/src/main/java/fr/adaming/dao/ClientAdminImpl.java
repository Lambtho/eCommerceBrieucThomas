package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Client;
import fr.adaming.entities.Produit;

@Repository
public class ClientAdminImpl implements IClientDao {

	@Autowired
	SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Produit getProductById(Produit produit) {

		Session s = sf.getCurrentSession();

		Produit p = (Produit) s.get(Produit.class, produit.getIdProduit());

		return p;
	}

	@Override
	public List<Produit> getProductByKeyWord(String keyWord, long id_categorie) {
		Session s = sf.getCurrentSession();
		Query query = null;

		if (id_categorie != 0) {
			String req = "SELECT p FROM Produit p WHERE (p.description LIKE :keyWord OR p.designation LIKE :keyWord) AND p.categorie.idCategorie=:id_cat";
			query = s.createQuery(req);
			query.setParameter("keyWord", "%" + keyWord + "%");
			query.setParameter("id_cat", id_categorie);
		} else {
			String req = "SELECT p FROM Produit p WHERE p.description LIKE :keyWord OR p.designation LIKE :keyWord";
			query = s.createQuery(req);
			query.setParameter("keyWord", "%" + keyWord + "%");
		}

		@SuppressWarnings("unchecked")
		List<Produit> listProd = query.list();

		return listProd;
	}

	@Override
	public int order(Client client) {

		Session s = sf.getCurrentSession();

		s.saveOrUpdate(client);

		return 1;
	}

}
