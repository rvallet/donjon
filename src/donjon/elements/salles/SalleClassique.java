package donjon.elements.salles;

import java.util.List;

import donjon.elements.objets.Objet;
import donjon.elements.personnages.Adversaires;

public class SalleClassique extends Salles {

	public SalleClassique(int numSalle) {
		super(numSalle);
		setNom("Classique");
	}

	public SalleClassique(int numSalle, List<Adversaires> adv) {
		super(numSalle, adv);
		setNom("Classique");
	}

	public SalleClassique(int numSalle, List<Adversaires> adv, List<Objet> obj) {
		super(numSalle, adv, obj);
		setNom("Classique");
	}

	public SalleClassique(int numSalle, Salles sallePrecedente) {
		super(numSalle, sallePrecedente);
		setNom("Classique");
	}

	public SalleClassique(int numSalle, Salles sallePrecedente, List<Adversaires> adv) {
		super(numSalle, sallePrecedente, adv);
		setNom("Classique");
	}
}