package RESTful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ListPersonnes {

	public Map<Long,Personne> personnes  = new HashMap<>()
	{
		{
		put((long)1,new Personne(1,"AZER","AZER"));
		put((long)2,new Personne(2,"AZERT","AZERT"));
		}
	};
	public ListPersonnes() {

	}
	public List<Personne> consulterListePersonne(){
		return new ArrayList<>(personnes.values());
	}
	
	public Personne  consulterPersonne(long id) {
		return personnes.get(id);
	}
	
	public void ajouterPersonne(Personne personne) {
		personnes.put(personne.getId(), personne);
	}
	public void modifierPersonne(long id,Personne personne) {
		personnes.put(id, personne);
	}
	public void supprimerPersonne(Personne personne,long id) {
		personnes.remove(id);
	}
}
