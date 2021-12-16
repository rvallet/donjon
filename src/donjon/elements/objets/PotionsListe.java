package donjon.elements.objets;

public enum PotionsListe {
	SOIN("Potion de soin", 50, 0), FORCE("Potion de force", 0, 5);

	private String nom;
	private int soin;
	private int force;

	PotionsListe(String nom, int soin, int force) {
		setNom(nom);
		setSoin(soin);
		setForce(force);
	}

	public int getSoin() {
		return soin;
	}

	public void setSoin(int soin) {
		this.soin = soin;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}