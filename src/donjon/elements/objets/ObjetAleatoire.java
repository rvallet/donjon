package donjon.elements.objets;

import donjon.interfaces.RanChoice;

public class ObjetAleatoire extends Objet {

	public static Objet objetAleatoire() {
		int objetAlea = RanChoice.ranChoice(1, 8);
		Objet objetBandit;

		switch (objetAlea) {
			case 1:
			case 2:
				objetBandit = new BourseOr(75);
				break;
			case 3:
			case 4:
			case 5:
				objetBandit = new Potions(PotionsListe.FORCE);
				break;
			case 6:
			case 7:
			case 8:
			default:
				objetBandit = new Potions(PotionsListe.SOIN);
		}
		return objetBandit;
	}
}
