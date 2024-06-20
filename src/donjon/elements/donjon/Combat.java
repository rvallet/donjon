package donjon.elements.donjon;

import donjon.elements.personnages.Adversaires;
import donjon.elements.personnages.Joueur;
import donjon.interfaces.AffichageText;
import donjon.interfaces.RanChoice;

public class Combat {
	protected Adversaires adv1;
	protected Joueur joueur;

	public Combat(Adversaires adv1, Joueur joueur) {
		this.adv1 = adv1;
		this.joueur = joueur;
	}

	/**
	 * M�thode r�cursive utilisant la m�thode attaquer(adv) de la classe Joueur.
	 **/
	public void lancerCombat() throws InterruptedException {
		if (!this.adv1.isDead()) {
			while (!this.adv1.isDead() && !this.joueur.isDead()) {
				Thread.sleep(600);
				this.joueur.attaquer(this.adv1);
				if (!this.adv1.isDead()) {
					Thread.sleep(600);
					this.adv1.attaquer(this.joueur);
				}
			}
			if (!this.joueur.isDead() && this.adv1.isDead()) {
				int gainPO = RanChoice.ranChoice(this.adv1.getPiecesOr(), 2 * this.adv1.getPiecesOr());
				System.out.println("");
				System.out.println("Le combat était rude ! Votre adversaire laisse tomber au sol une bourse d'or...");
				System.out.println(
						"Vous ramassez la bourse et dépouillez votre adversaire de " + gainPO + " pièces d'or.");
				System.out.println("");
				this.joueur.setPiecesOr(this.joueur.getPiecesOr() + gainPO);
				Thread.sleep(600);
				System.out.println("Vous avez maintenant " + this.joueur.getPiecesOr() + " pièces d'or.");
				Thread.sleep(600);
				switch (RanChoice.ranChoice(1, 8)){
					case 1:
						System.out.println("Vous avez trouvé une potion de soin sur le cadavre de votre adversaire.");
						this.joueur.setVie(this.joueur.getVie() + 20);
						System.out.println("Vous avez maintenant " + this.joueur.getVie() + " points de vie.");
						break;
					case 2:
						System.out.println("Vous avez trouvé une potion de force sur le cadavre de votre adversaire.");
						this.joueur.setForce(this.joueur.getForce() + 5);
						System.out.println("Vous avez maintenant " + this.joueur.getForce() + " points de force.");
						break;
					default:
						System.out.println("Vous n'avez rien trouvé d'autre sur le cadavre de votre adversaire.");
				}
			}
			if (this.joueur.isDead() && !this.adv1.isDead()) {
				System.out.println("Votre adversaire vous a fait mondre la poussière...");
				Thread.sleep(1500);
				System.out.println("");
				AffichageText.affichageText("/graph/donjon-defaite.txt");
				Thread.sleep(3000);
				System.out.println("");
				System.out.println("");
				AffichageText.textAsciiArt("Fin de partie !!!");
			}
		} else if (this.adv1.isDead()) {
			System.out.println("Il n'y a plus d'adversaire vivant à combattre dans cette salle");
		} else
			System.out.println("Il n'y a aucun adversaire dans cette salle");
	}
}