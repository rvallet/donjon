package donjon.elements.objets;

public class OneArmedBandit extends Objet {
	private int rancon;

	public OneArmedBandit(int rancon) {
		super("Bandit Manchot", "");
		this.rancon = rancon;
	}

	public int getRancon() {
		return rancon;
	}

	public void setRancon(int rancon) {
		this.rancon = rancon;
	}

	public Objet getObjet() {
		return ObjetAleatoire.objetAleatoire();
	}
}