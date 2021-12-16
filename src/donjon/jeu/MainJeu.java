package donjon.jeu;

import donjon.elements.donjon.GenerateurDonjon;
import donjon.elements.personnages.Joueur;
import donjon.elements.salles.Salles;
import donjon.interfaces.AffichageText;
import donjon.interfaces.InterfaceCMD;
import donjon.interfaces.InterpreteurCmd;
import donjon.interfaces.LectureClavier;
import donjon.interfaces.cmd.CmdQuit;

public class MainJeu {
	public static Joueur joueur;
	public static GenerateurDonjon donjon;

	public static void main(String[] args) {
		launch();
	}

	public static void launch() {
		AffichageText.affichageText("/graph/donjon-accueil.txt");
		System.out.println("");
		System.out.println("********************************");
		System.out.println("*** Bienvenue Aventurier ***");
		System.out.println("********************************");
		System.out.println("");
		String cmdStr;
		InterfaceCMD command;
		donjon = new GenerateurDonjon();
		boolean choixJeu = false;
		Salles entree = null;
		do {
			System.out.println("Choix du Donjon : ");
			System.out.println("- Mode Démonstration (6 salles)           -> Taper 'demo'");
			System.out.println("- Mode donjon Classique                   -> Taper 'classic'");
			System.out.println("- Mode donjon Aléatoire (choix nb salles) -> Taper 'alea'");
			System.out.println("Votre choix ?> ");
			String modeJeu = LectureClavier.readLine();

			if (modeJeu.equalsIgnoreCase("demo")) {
				choixJeu = true;
				donjon.carteDemo();
				entree = donjon.getCarteDonjon().get(0);
			} else if (modeJeu.equalsIgnoreCase("classic")) {
				choixJeu = true;
				donjon.carteNiveau1();
				entree = donjon.getCarteDonjon().get(0);
			} else if (modeJeu.equalsIgnoreCase("alea")) {
				System.out.println("Combien de salles ?");
				System.out.println("Votre choix ?> ");
				String nbSalles = LectureClavier.readLine();
				try {
					donjon.carteRandom(Integer.parseInt(nbSalles));
					choixJeu = true;
				} catch (NumberFormatException e) {
					System.out.println("Il faut rentrer un nombre de salles");
				}
				entree = donjon.getRandomMap().get(0);
			} else {
				System.out.println("Votre choix " + modeJeu + " ne figure pas parmis les propositions");
			}
		} while (!choixJeu);
		System.out.println("Quel est votre nom ? ");
		System.out.print("> ");
		joueur = new Joueur(LectureClavier.readLine(), entree);
		try {
			MainJeu.joueur.arriveSalle();
		} catch (InterruptedException e) {
			System.err.println("Interruption -> Main : Méthode arriveSalle()");
		}
		System.out.println("");
		System.out.println("Tapez 'aide' à tout moment pour obtenir la liste des actions possible");
		System.out.println("");
		do {
			System.out.println("");
			System.out.print("Votre action " + joueur.getNom() + " ?> ");
			cmdStr = LectureClavier.readLine();
			command = InterpreteurCmd.create(cmdStr);
			command.run();
		} while (!(command instanceof CmdQuit) && !MainJeu.joueur.finDeJeu());
	}
}