package donjon.elements.salles;

import java.util.List;

import donjon.elements.personnages.Adversaires;

public class Sortie extends Salles {
	public Sortie(int numSalle) {
		super(numSalle);
		setNom("Sortie");
	}

	public Sortie(int numSalle, List<Adversaires> adv) {
		super(numSalle, adv);
		setNom("Sortie");
	}

	public Sortie(int numSalle, Salles sallePrecedente) {
		super(numSalle, sallePrecedente);
		setNom("Sortie");
	}

	public Sortie(int numSalle, Salles sallePrecedente, List<Adversaires> adv) {
		super(numSalle, sallePrecedente, adv);
		setNom("Sortie");
	}
}