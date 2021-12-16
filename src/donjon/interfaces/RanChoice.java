package donjon.interfaces;

import java.util.Random;

public abstract class RanChoice {
	/**
	 * Méthode qui prend en paramètre des bornes inférieures et supérieures pour
	 * retourner un nombre aléatoire compris entre ces bornes
	 **/
	public static int ranChoice(int min, int max) {
		Random num = new Random();
		return min + num.nextInt(max - min + 1);
	}
}