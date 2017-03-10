package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.entities.Client;
import fr.adaming.entities.Produit;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements IClientService {

	@Autowired
	IClientDao clientDao;

	public void setClientDao(IClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public Produit getProductByIdService(Produit produit) {

		return clientDao.getProductById(produit);
	}

	@Override
	public List<Produit> getProductByKeyWordService(String keyWord, long id_categorie) {

		return clientDao.getProductByKeyWord(keyWord, id_categorie);
	}

	@Override
	public int orderService(Client client) {

		return clientDao.order(client);
	}

}
