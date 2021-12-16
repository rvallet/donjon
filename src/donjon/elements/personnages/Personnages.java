package donjon.elements.personnages;

public abstract class Personnages {
	protected String nom;
	protected int vie;
	protected int force;
	protected int piecesOr;
	protected boolean dead;

	public Personnages() {
		this.dead = false;
	}

	public Personnages(int vie, int force) {
		this.vie = vie;
		this.force = force;
		this.piecesOr = 0;
		this.dead = false;
	}

	public Personnages(int vie, int force, int PO) {
		this.vie = vie;
		this.force = force;
		this.piecesOr = PO;
		this.dead = false;
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getPiecesOr() {
		return piecesOr;
	}

	public void setPiecesOr(int piecesOr) {
		this.piecesOr = piecesOr;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}