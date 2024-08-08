package RESTful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
@Path("/listes")
public class GestionnaireListes {
	public static Map<Long,ListPersonnes> Listes = new HashMap<Long,ListPersonnes>()
	{
		{
		put((long)1,new ListPersonnes());
		}
	};
	

	 ListPersonnes ListPersonnes1 = new ListPersonnes();

    @POST
   public String ajouterListes(@QueryParam("idListe") long idListe) {
    	
    	Listes.put(idListe,  new ListPersonnes());
    	
    	return "Ajout réussi";
    	
    	
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{idListe}")
    public List<Personne> consulterListePersonne(@PathParam("idListe") long idListe) {
      
    	return Listes.get(idListe).consulterListePersonne();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{idListe}/{idPersonne}")
    public Personne consulterPersonneDansListe(@PathParam("idListe") long idListe,@PathParam("idPersonne") long idPersonne) {
    	
    	if(Listes.containsKey(idListe)) {
    		if(Listes.get(idListe).personnes.containsKey(idPersonne)) {
    			return Listes.get(idListe).personnes.get(idPersonne);
    		}
    	}
		return null;
    	
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/{idListe}")
    public String ajouterPersonneDansListe(@PathParam("idListe") long idListe,Personne personne) {
    	
    	if(Listes.containsKey(idListe)) {
    		Listes.get(idListe).personnes.put(personne.id, personne);
    		return "Ajout réussi";
    	}
    	return "Liste n'éxiste Pas";
    	
    } 
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/{idListe}/{idPersonne}")
    public String modifierPersonneDansListe(@PathParam("idListe") long idListe,@PathParam("idPersonne") long idPersonne,Personne personne) {
    	
    	if(Listes.containsKey(idListe)) {
    		if(Listes.get(idListe).personnes.containsKey(idPersonne)) {
    			
        		Listes.get(idListe).personnes.get(idPersonne).setId(personne.id);
        		Listes.get(idListe).personnes.get(idPersonne).setNom(personne.nom);
        		Listes.get(idListe).personnes.get(idPersonne).setPrenom(personne.prenom);
        		
        		return "Modification réussi";
    		}
    		return "Personne n'éxiste Pas";

    	}
    	return "Liste n'éxiste Pas";
    	
    } 
    
    @DELETE
    @Path("/{idListe}/{idPersonne}")
    public String supprimerPersonneDansListe(@PathParam("idListe") long idListe,@PathParam("idPersonne") long idPersonne) {
    	
    	if(Listes.containsKey(idListe)) {
    		if(Listes.get(idListe).personnes.containsKey(idPersonne)) {
    			
        		Listes.get(idListe).personnes.remove(idPersonne);
        		
        		return "Suppression réussi";
    		}
    		return "Personne n'éxiste Pas";

    	}
    	return "Liste n'éxiste Pas";
    	
    }  
    
    
    
    
    
}
