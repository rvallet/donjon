package donjon.elements.salles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import donjon.elements.objets.Objet;
import donjon.elements.personnages.Adversaires;

public abstract class Salles {
	private String nom;
	private int numSalle;
	private String description;
	private Map<Integer, Salles> portes = new HashMap<Integer, Salles>();
	private static boolean salleExist;
//	private boolean salleExamine;
	private List<Objet> objet;
	private List<Adversaires> adv;

	public Salles(int numSalle) {
		setNumSalle(numSalle);
		setDescription("Salle n°" + numSalle);
	}

	public Salles(int numSalle, Salles sallePrecedente) {
		setNumSalle(numSalle);
		setDescription("Salle n°" + numSalle);
		portes.put(sallePrecedente.getNumSalle(), sallePrecedente);
	}

	public Salles(int numSalle, List<Adversaires> adv) {
		setNumSalle(numSalle);
		setDescription("Salle n°" + numSalle);
		setAdv(adv);
	}

//	public Salles(int numSalle, List<Objet> obj) {
//		setNumSalle(numSalle);
//		setDescription("Salle n°" + numSalle);
//		setObjet(obj);
//	if (adv instanceof Adversaires)
//		setAdv(adv);
//	else if (adv instanceof Objet)
//		setObjet(adv);
//	}

	public Salles(int numSalle, List<Adversaires> adv, List<Objet> obj) {
		setNumSalle(numSalle);
		setDescription("Salle n°" + numSalle);
		setAdv(adv);
		setObjet(obj);
	}

	public Salles(int numSalle, Salles sallePrecedente, List<Adversaires> adv) {
		setNumSalle(numSalle);
		portes.put(sallePrecedente.getNumSalle(), sallePrecedente);
		setDescription("Salle n°" + numSalle);
		setAdv(adv);
	}

	/**
	 * Méthode permettant de lister les couloirs accéssibles à partir de la salle
	 * actuelle du joueur
	 **/
	public void regarderPortes() {
		System.out.println("Couloirs : ");
		for (Map.Entry<Integer, Salles> e : portes.entrySet()) {
			System.out.println("Couloir n°" + e.getKey() + " (tapez 'direction " + e.getKey() + "')");
		}
		System.out.println();
	}

	public int getNumSalle() {
		return numSalle;
	}

	public void setNumSalle(int numSalle) {
		this.numSalle = numSalle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Adversaires> getAdv() {
		return adv;
	}

	public void setAdv(List<Adversaires> adv) {
		this.adv = adv;
	}

	public static boolean isSalleExist() {
		return salleExist;
	}

	public void setSalleExist(boolean salleExist) {
		Salles.salleExist = salleExist;
	}

//	public boolean isSalleExamine() {
//		return salleExamine;
//	}
//
//	public void setSalleExamine(boolean salleExamine) {
//		this.salleExamine = salleExamine;
//	}

	public List<Objet> getObjet() {
		return objet;
	}

	public void setObjet(List<Objet> objet) {
		this.objet = objet;
	}

	public Map<Integer, Salles> getPortes() {
		return portes;
	}
}