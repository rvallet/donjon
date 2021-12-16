package donjon.interfaces.cmd;

import java.util.Map;

import donjon.elements.salles.Salles;
import donjon.interfaces.InterfaceCMD;
import donjon.jeu.MainJeu;

public class CmdDeplacer implements InterfaceCMD {
	public static final String CMD = "direction";
	public String direction;

	public CmdDeplacer(String dir) {
		this.direction = dir;
	}

	@Override
	public void run() {
		try {
			if (direction.equalsIgnoreCase("direction")) {
				for (Map.Entry<Integer, Salles> e : MainJeu.joueur.getSalleActuelle().getPortes().entrySet()) {
					System.out.println("Couloir n°" + e.getKey() + " ('direction " + e.getKey() + "')");
				}
			} else {
				MainJeu.joueur.changerSalle(direction); // voir nb > 9
			}
		} catch (NumberFormatException e) {
			System.out.println("Vous devez entrer un chiffre après la commande direction (exemple : 'direction 1').");
		}
	}
}