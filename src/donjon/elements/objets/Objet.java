package donjon.elements.objets;

public abstract class Objet {
	private String nom;
	private String description;

	public Objet() {
	}

	public Objet(String n, String desc) {
		this.nom = n;
		this.description = desc;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String n) {
		this.nom = n;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}
}