

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Personne {

	long id;
	String nom, prenom;
	
	public Personne() {}	
	
	public Personne(long id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}	
}
