package fr.adaming.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_role")
	private int id_role;
	@Column(name = "namerole")
	private String role;
	
	@OneToMany(mappedBy="role")
	private List<AdminProd> listeAdmin;

	public Role() {
		super();
	}

	public Role(String role) {
		super();
		this.role = role;
	}

	public Role(int id_role, String role) {
		super();
		this.id_role = id_role;
		this.role = role;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<AdminProd> getListeAdmin() {
		return listeAdmin;
	}

	public void setListeAdmin(List<AdminProd> listeAdmin) {
		this.listeAdmin = listeAdmin;
	}

	@Override
	public String toString() {
		return "Role [id_role=" + id_role + ", role=" + role + ", listeAdmin=" + listeAdmin + "]";
	}

}
