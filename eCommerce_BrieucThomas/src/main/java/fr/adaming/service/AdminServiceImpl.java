package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	IAdminDao administrateurDao;

	public void setAdministrateurDao(IAdminDao administrateurDao) {
		this.administrateurDao = administrateurDao;
	}
	

	@Override
	public AdminProd isExistService(AdminProd administrateur) {
		
		return administrateurDao.isExistDao(administrateur);
	}

	@Override
	public int addProductService(Produit produit) {
		
		return administrateurDao.addProductDao(produit);
	}

	@Override
	public int delProductService(long id_prod) {
		
		return administrateurDao.delProductDao(id_prod);
	}

	@Override
	public int updateProductService(Produit produit) {
		
		return administrateurDao.updateProductDao(produit);
	}

	@Override
	public List<Produit> getAllProductService() {
		
		return administrateurDao.getAllProductDao();
	}

	@Override
	public Produit getByIdProductService(long id_prod) {
		
		return administrateurDao.getByIdProductDao(id_prod);
	}


	@Override
	public Categorie getByIdCategorieService(long id_cat) {
		
		return administrateurDao.getByIdCategorieDao(id_cat);
	}


	@Override
	public List<Categorie> getAllCategorieService() {
		
		return administrateurDao.getAllCategorieDao();
	}


	@Override
	public int addCategorie(Categorie categorie) {

		return administrateurDao.addCategorie(categorie);
	}

}
