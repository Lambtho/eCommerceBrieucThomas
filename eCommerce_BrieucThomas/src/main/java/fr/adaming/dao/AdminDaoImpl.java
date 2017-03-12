package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@Repository
@Transactional
public class AdminDaoImpl implements IAdminDao {

	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}



	@Override
	public int addProductDao(Produit produit) {
		Session s = sf.getCurrentSession();
		s.save(produit);

		return 1;
	}

	@Override
	public int delProductDao(long id_prod) {
		Session s = sf.getCurrentSession();
		
		Produit p = (Produit) s.get(Produit.class, id_prod);
		
		s.delete(p);

		return 1;
	}

	@Override
	public int updateProductDao(Produit produit) {

		Session s = sf.getCurrentSession();
		
		s.update(produit);
		
		return 1;
	}

	@Override
	public List<Produit> getAllProductDao() {
		Session s = sf.getCurrentSession();

		String req = "SELECT p FROM Produit p";

		Query query = s.createQuery(req);

		@SuppressWarnings("unchecked")
		List<Produit> listeProduits = query.list();

		return listeProduits;
	}

	@Override
	public Produit getByIdProductDao(long id_prod) {
		Session s = sf.getCurrentSession();
		Produit prod = (Produit) s.get(Produit.class, id_prod);
		
		return prod;
	}

	@Override
	public Categorie getByIdCategorieDao(long id_cat) {

		Session s = sf.getCurrentSession();

		return (Categorie) s.get(Categorie.class, id_cat);
	}

	@Override
	public List<Categorie> getAllCategorieDao() {

		Session s = sf.getCurrentSession();

		String req = "SELECT c FROM Categorie c";

		Query query = s.createQuery(req);

		@SuppressWarnings("unchecked")
		List<Categorie> listCat = query.list();

		return listCat;
	}

	@Override
	public int addCategorie(Categorie categorie) {
		
		Session s = sf.getCurrentSession();
		
		s.save(categorie);
		
		return 1;
	}

}
