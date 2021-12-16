package donjon.interfaces.cmd;

import donjon.interfaces.InterfaceCMD;
import donjon.jeu.MainJeu;

public class CmdUtiliser implements InterfaceCMD {
	public static final String CMD = "utiliser";
	public String objet;
	public String typeObjet;
	public String typePotion = " ";

	public CmdUtiliser(String objet) {
		this.objet = objet;
	}

	public CmdUtiliser(String objet, String typeObjet) {
		this.objet = objet;
		this.typeObjet = typeObjet;
	}

	public CmdUtiliser(String objet, String typeObjet, String typePotion) {
		this.objet = objet;
		this.typeObjet = typeObjet;
		this.typePotion = typePotion;
	}

	@Override
	public void run() {
		if (MainJeu.joueur.salleActuelle.getObjet() != null) {
			if (typeObjet != null) {
				if (typeObjet.equalsIgnoreCase("potion")) {
					if (typePotion.contains("soin")) {
						typeObjet = "potion de soin";
						MainJeu.joueur.utiliser(typeObjet);
					} else if (typePotion.contains("force")) {
						typeObjet = "potion de force";
						MainJeu.joueur.utiliser(typeObjet);
					} else if (!typePotion.contains("force") && !typePotion.contains("soin"))
						System.out.println(
								"Précisez le type de potion : 'utiliser potion soin'/'utiliser potion force' ?");
				} else if (typeObjet.equalsIgnoreCase("bourse")) {
					typeObjet = "bourse d'or";
					MainJeu.joueur.utiliser(typeObjet);
				} else if (typeObjet.equalsIgnoreCase("bandit")) {
					typeObjet = "bandit manchot";
					MainJeu.joueur.utiliser(typeObjet);
				} else
					System.out.println("'" + objet + " " + typeObjet + "' n'est pas envisageable.");
			} else {
				System.out.println("Que souhaitez vous utiliser ?");
				if (MainJeu.joueur.salleActuelle.getObjet() != null) {
					if (MainJeu.joueur.salleActuelle.getObjet().size() < 2
							&& MainJeu.joueur.salleActuelle.getObjet().size() > 0) {
						System.out.print(MainJeu.joueur.salleActuelle.getObjet().get(0).getNom() + " - taper 'utiliser "
								+ MainJeu.joueur.salleActuelle.getObjet().get(0).getNom().toLowerCase() + "'.");
						System.out.println(" ");
					} else if (MainJeu.joueur.salleActuelle.getObjet().size() > 1) {
						for (int i = 0; i < MainJeu.joueur.salleActuelle.getObjet().size(); i++) {
							if (MainJeu.joueur.salleActuelle.getObjet().get(i) != null)
								System.out.println(i + 1 + " - "
										+ MainJeu.joueur.salleActuelle.getObjet().get(i).getNom()
										+ " - taper 'utiliser "
										+ MainJeu.joueur.salleActuelle.getObjet().get(i).getNom().toLowerCase() + "'.");
						}
					} else if (MainJeu.joueur.salleActuelle.getObjet().size() == 0) {
						System.out.println("Il ne reste plus aucun objet dans cette salle");
					}
				} else
					System.out.println("Il y a aucun objet dans cette salle");
			}
		} else if (MainJeu.joueur.salleActuelle.getObjet() == null
				|| MainJeu.joueur.salleActuelle.getObjet().get(0) == null)
			System.out.println("Il n'y a plus aucun objet dans cette salle");
	}
}