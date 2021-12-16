package donjon.elements.objets;

import donjon.interfaces.RanChoice;

public class ObjetAleatoire extends Objet {

	public static Objet objetAleatoire() {
		int objetAlea = RanChoice.ranChoice(1, 3);
		Objet objetBandit;

		switch (objetAlea) {
		case 1:
			objetBandit = new BourseOr(75);
			break;
		case 2:
			objetBandit = new Potions(PotionsListe.SOIN);
			break;
		case 3:
			objetBandit = new Potions(PotionsListe.FORCE);
			break;
		default:
			objetBandit = new BourseOr(75);
		}
		return objetBandit;
	}
}
