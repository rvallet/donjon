package donjon.elements.donjon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import donjon.elements.salles.Salles;
import donjon.jeu.MainJeu;

public class AffichageCarte {

	/**
	 * Méthode qui retourne un affichage du contenu de la salle
	 * 
	 * @author Rémy VALLET @
	 **/
	public static void affichageSalle() {
		List<Integer> numPorte = new ArrayList<>();
		for (Map.Entry<Integer, Salles> e : MainJeu.joueur.getSalleActuelle().getPortes().entrySet()) {
			numPorte.add(e.getKey());
		}
		if (numPorte.size() >= 4)
			if (numPorte.get(3) < 10)
				System.out.println("####C" + numPorte.get(3) + "####");
			else
				System.out.println("####C" + numPorte.get(3) + "###");
		else
			System.out.println("##########");
		System.out.println("#   J    #");
		if (numPorte.size() >= 1)
			if (numPorte.get(0) < 10) {
				if (numPorte.size() >= 2)
					System.out.println("C" + numPorte.get(0) + "      " + "C" + numPorte.get(1));
				else
					System.out.println("C" + numPorte.get(0) + "       #");
			} else if (numPorte.get(0) > 9) {
				if (numPorte.size() >= 2)
					System.out.println("C" + numPorte.get(0) + "      " + "C" + numPorte.get(1));
				else
					System.out.println("C" + numPorte.get(0) + "      #");
			} else {
				System.out.println("#        #");
			}
		if (MainJeu.joueur.getSalleActuelle().getAdv() != null)
			System.out.println("#  " + MainJeu.joueur.getSalleActuelle().getAdv().size() + " Adv" + " #");
		else {
			System.out.println("#        #");
		}
		if (MainJeu.joueur.getSalleActuelle().getObjet() != null)
			System.out.println("#  " + MainJeu.joueur.getSalleActuelle().getObjet().size() + " Obj" + " #");
		else {
			System.out.println("#        #");
		}
		if (numPorte.size() >= 3)
			if (numPorte.get(2) < 10)
				System.out.println("####C" + numPorte.get(2) + "####");
			else
				System.out.println("####C" + numPorte.get(2) + "###");
		else
			System.out.println("##########");
	}

	public static void affichageCarte() {
	}
}