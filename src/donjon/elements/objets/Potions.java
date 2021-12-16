package donjon.elements.objets;

public class Potions extends Objet {
	private int soin;
	private int force;
	private PotionsListe type;

	public Potions(PotionsListe type) {
		this.setType(type);
		this.setNom("Potion de " + type);
		this.setSoin(PotionsListe.SOIN.getSoin());
		this.setForce(PotionsListe.FORCE.getForce());
	}

	public Potions(String n, String desc) {
		super(n, desc);
	}

	public PotionsListe getType() {
		return type;
	}

	public void setType(PotionsListe type) {
		this.type = type;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getSoin() {
		return soin;
	}

	public void setSoin(int soin) {
		this.soin = soin;
	}
}