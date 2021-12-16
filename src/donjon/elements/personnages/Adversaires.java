package donjon.elements.personnages;

public class Adversaires extends Personnages {
//	private Bestiaire type;

	public Adversaires(Bestiaire type) {
//		this.type = type;
		setNom(type.getName());
		setVie(type.getVie());
		setForce(type.getForce());
		setPiecesOr(type.getPO());
		setDead(false);
	}

	/**
	 * Méthode permettant d'afficher les dégats causés par un adversaire ainsi que
	 * ses points de vie restants.
	 **/
	public void attaquer(Joueur c) {
		if (getVie() > 0) {
			System.out.println("**** Attaque de " + this.nom + " ****");
			System.out.println(this.nom + " attaque et cause " + this.getForce() + " points de dégats");
			c.setVie(c.getVie() - this.getForce());
			System.out.println(this.nom + " a encore " + getVie() + " points de vie.");
			System.out.println();
		} else if (getVie() <= 0) {
			setDead(true);
		}
	}
}
