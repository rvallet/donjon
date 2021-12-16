package donjon.elements.personnages;

public enum Bestiaire {
	ZOMBIS("Zombi", 50, 6, 5), SQUELLETTES("Squelette", 60, 12, 20), VAMPIRES("Vampire", 80, 25, 50),
	LEECH("Liche", 120, 35, 100);

	private String type;
	private int vie;
	private int force;
	private int piecesOr;

	Bestiaire(String type, int vie, int force, int PO) {
		setType(type);
		setVie(vie);
		setForce(force);
		setPiecesOr(PO);
	}

	public String getName() {
		return type;
	}

	public int getVie() {
		return vie;
	}

	public int getForce() {
		return force;
	}

	public int getPO() {
		return piecesOr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPiecesOr() {
		return piecesOr;
	}

	public void setPiecesOr(int piecesOr) {
		this.piecesOr = piecesOr;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public void setForce(int force) {
		this.force = force;
	}
}