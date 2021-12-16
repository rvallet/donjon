package donjon.interfaces.cmd;

import donjon.interfaces.InterfaceCMD;
import donjon.jeu.MainJeu;

public class CmdCombattre implements InterfaceCMD {
	public static final String CMD = "combattre";
	public int choixAdv = 0;

	public CmdCombattre(int choixAdv) {
		this.choixAdv = choixAdv;
	}

	public CmdCombattre() {
	}

	@Override
	public void run() {
		if (MainJeu.joueur.salleActuelle.getAdv() == null) {
			System.out.println("Il n'y a aucun adversaire dans cette salle");
		} else if (MainJeu.joueur.salleActuelle.getAdv().size() < 2) {
			System.out.println("Vous tentez un assault contre l'unique "
					+ MainJeu.joueur.salleActuelle.getAdv().get(0).getNom() + " présent dans cette salle :");
			try {
				MainJeu.joueur.combattre();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (MainJeu.joueur.salleActuelle.getAdv().size() > 1) {
			if (choixAdv == 0) {
				System.out.println("Quel adversaire souhaitez combattre ?");
				String advEtat = "";
				for (int i = 0; i < MainJeu.joueur.salleActuelle.getAdv().size(); i++) {
					if (MainJeu.joueur.salleActuelle.getAdv().get(i).isDead())
						advEtat = "(Mort)";
					else
						advEtat = "(En vie) - 'combattre " + (i + 1) + "'";
					System.out.println(
							i + 1 + " - " + MainJeu.joueur.salleActuelle.getAdv().get(i).getNom() + " " + advEtat);
				}
			} else if (choixAdv <= MainJeu.joueur.salleActuelle.getAdv().size() && choixAdv > 0) {
				if (!MainJeu.joueur.salleActuelle.getAdv().get(choixAdv - 1).isDead())
					try {
						MainJeu.joueur.combattre(choixAdv - 1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				else
					System.out.println("Cet adversaire gît déjà au sol.");
			} else if (choixAdv < 1 || choixAdv > MainJeu.joueur.salleActuelle.getAdv().size())
				System.out.println(
						"Vous devez choisir un adversaire à attaquer parmis ceux qui sont toujours en vie. (Exemple : 'combattre 1')");
		}
	}
}