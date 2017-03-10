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
@Table(name = "administrateurs")
public class AdminProd implements Serializable {

	private static final long serialVersionUID = 1L;

	//Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_admin")
	private long id_admin;
	@Column(name = "nom_admin")
	private String nom;
	@Column(name = "email_admin")
	private String email;
	@Column(name = "password_admin")
	private String password;
	@Column(name = "actived", columnDefinition = "BIT(1)")
	private boolean actived;
	
	@ManyToOne
	@JoinColumn(name="role_id_fk", referencedColumnName="id_role")
	private Role role;

	//Constructeurs
	public AdminProd() {
		super();
	}

	public AdminProd(String nom, String email, String password) {
		super();
		this.nom = nom;
		this.email = email;
		this.password = password;
	}

	public AdminProd(long id_admin, String nom, String email, String password) {
		super();
		this.id_admin = id_admin;
		this.nom = nom;
		this.email = email;
		this.password = password;
	}

	//Getters & Setters
	public long getId_admin() {
		return id_admin;
	}

	public void setId_admin(long id_admin) {
		this.id_admin = id_admin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AdminProd [id_admin=" + id_admin + ", nom=" + nom + ", email=" + email + ", password=" + password + "]";
	}

}
