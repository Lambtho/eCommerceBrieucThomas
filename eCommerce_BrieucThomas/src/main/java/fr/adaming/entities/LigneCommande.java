package fr.adaming.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lignes_commande")
public class LigneCommande implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lcmd")
	private long id_ligneCommande;
	@Column(name = "quantite_lcmd")
	private int quantite;
	@Column(name = "prix_lcmd")
	private double prix;

	@ManyToOne
	@JoinColumn(name = "id_cmd_fk", referencedColumnName = "id_cmd")
	private Commande commande;
	
	@ManyToOne
	@JoinColumn(name = "id_prod_fk", referencedColumnName = "id_prd")
	private Produit produit;

	// Constructeurs
	public LigneCommande() {
		super();
	}

	public LigneCommande(int quantite, double prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(long id_ligneCommande, int quantite, double prix) {
		super();
		this.id_ligneCommande = id_ligneCommande;
		this.quantite = quantite;
		this.prix = prix;
	}

	// Getters & Setters
	public long getId_ligneCommande() {
		return id_ligneCommande;
	}

	public void setId_ligneCommande(long id_ligneCommande) {
		this.id_ligneCommande = id_ligneCommande;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Override
	public String toString() {
		return "LigneCommande [id_ligneCommande=" + id_ligneCommande + ", quantite=" + quantite + ", prix=" + prix
				+ "]";
	}

}
