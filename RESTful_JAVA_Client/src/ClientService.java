import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class ClientService {
	
	public static final String BASE_URL = "http://localhost:9999/RESTful_API_SERVER/monapi/listes/";
	
	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Menu:");
			System.out.println("1. Ajouter une nouvelle liste de personnes");
			System.out.println("2. Consulter la liste des personnes dans une liste donnée");
			System.out.println("3. Consulter les informations d'une personne donnée dans une liste donnée");
			System.out.println("4. Ajouter une nouvelle personne dans une liste donnée");
			System.out.println("5. Modifier les informations d'une personne donnée dans une liste donnée");
			System.out.println("6. Supprimer une personne donnée à partir d'une liste donnée");
			System.out.println("0. Quitter");

			System.out.print("Choisissez une option (0-6): ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				
				System.out.println("Entrez l'id de la liste:");
				long idListe = scanner.nextLong();
				
				ajouterListe(idListe);

				break;
			case 2:
				System.out.println("Entrez l'id de la liste:");
				idListe = scanner.nextLong();
				
				consulterListePersonnes(idListe);

				break;
			case 3:
				System.out.println("Entrez l'id de la liste:");
				idListe = scanner.nextLong();
				
				System.out.println("Entrez l'id de la personne:");
				long idPersonne = scanner.nextLong();
				
				consulterPersonne(idListe, idPersonne);
				break;

			case 4:					
				System.out.println("Entrez l'id de la liste:");
				idListe = scanner.nextLong();
				
				System.out.println("Entrez l'id de la personne:");
				idPersonne = scanner.nextLong();
				
				System.out.println("Entrez le nom de la personne:");
				String nom = scanner.next();

				System.out.println("Entrez le prénom de la personne:");
				String prenom = scanner.next();

				Personne personne = new Personne(idPersonne, nom, prenom);
				
				ajouterPersonne(idListe, personne);
				
				break;
			case 5:
				System.out.println("Entrez l'id de la liste:");
				idListe = scanner.nextLong();
				
				System.out.println("Entrez l'id de la personne à modifier:");
				idPersonne = scanner.nextLong();
				
				System.out.println("Entrez le nouveau nom de la personne:");
				nom = scanner.next();

				System.out.println("Entrez nouveau prénom de la personne:");
				prenom = scanner.next();

				personne = new Personne(idPersonne, nom, prenom);
				
				modifierPersonne(idListe, idPersonne, personne);

				break;
				
			case 6:
				System.out.println("Entrez l'id de la liste:");
				idListe = scanner.nextLong();
				
				System.out.println("Entrez l'id de la personne:");
				idPersonne = scanner.nextLong();
				
				supprimerPersonne(idListe, idPersonne);

				break;
			case 0:
				System.out.println("Programme terminé.");
				System.exit(0);
				break;
			default:
				System.out.println("Option invalide. Veuillez choisir une option valide.");
			}
		}	
	}
	
	//L'ajout d'une nouvelle liste de personnes
	public static void ajouterListe(long idListe) {
		
		Client client = ClientBuilder.newClient();
		
		Response response = client.target(BASE_URL)
								  .queryParam("idListe", idListe)
								  .request()
								  .post(null);
		
		System.out.println(response.readEntity(String.class));
		
	}	
	
	//La consultation de la liste des personnes dans une liste donnée
	public static void consulterListePersonnes(long idListe) {
		
		Client client = ClientBuilder.newClient();
		
		Response response = client.target(BASE_URL)
								  .path(String.valueOf(idListe)) 
								  .request(MediaType.APPLICATION_XML)
								  .get();
		
		List<Personne> personnes = response.readEntity(new GenericType<List<Personne>>() {});
		
		for(Personne personne : personnes) {
			System.out.println("Id: "+personne.getId());
			System.out.println("Nom: "+personne.getNom());
			System.out.println("Prénom: "+personne.getPrenom());
		}		
	}
	
	
	//La consultation des informations d'une personne donnée dans une liste donnée
	public static void consulterPersonne(long idListe, long idPersonne) {
		
		Client client = ClientBuilder.newClient();
			
		Response response = client.target(BASE_URL+idListe+"-"+idPersonne)
								  .request(MediaType.APPLICATION_XML)
								  .get();
		
		Personne personne = response.readEntity(Personne.class);
		
		System.out.println("Id: "+personne.getId());
		System.out.println("Nom: "+personne.getNom());
		System.out.println("Prénom: "+personne.getPrenom());
				
	}
	
	//L'ajout d'une nouvelle personne dans une liste donnée
	public static void ajouterPersonne(long idListe, Personne personne) {
		
		Client client = ClientBuilder.newClient();
		
		Response response = client.target(BASE_URL)
								  .path(String.valueOf(idListe))
								  .request(MediaType.APPLICATION_XML)
								  .post(Entity.entity(personne, MediaType.APPLICATION_XML));
		
		System.out.println(response.readEntity(String.class));
		
		
	}
	
	
	//La modification des informations d'une personne donnée dans une liste donnée
	public static void modifierPersonne(long idListe, long idPersonne, Personne personne) {
		
		Client client = ClientBuilder.newClient();
		
		Response response = client.target(BASE_URL+idListe+"-"+idPersonne)
								  .request(MediaType.APPLICATION_XML)
								  .put(Entity.entity(personne, MediaType.APPLICATION_XML));
		
		System.out.println(response.readEntity(String.class));
	}
	
	
	//La suppression d'une personne donnée à partir d'une liste donnée
	public static void supprimerPersonne(long idListe, long idPersonne) {
		
		Client client = ClientBuilder.newClient();
		
		Response response = client.target(BASE_URL+idListe+"-"+idPersonne)
								  .request()
								  .delete();
		
		System.out.println(response.readEntity(String.class));		
	}
}
