package donjon.elements.donjon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import donjon.elements.objets.BourseOr;
import donjon.elements.objets.Objet;
import donjon.elements.objets.OneArmedBandit;
import donjon.elements.objets.Potions;
import donjon.elements.objets.PotionsListe;
import donjon.elements.personnages.Adversaires;
import donjon.elements.personnages.Bestiaire;
import donjon.elements.salles.Entree;
import donjon.elements.salles.SalleClassique;
import donjon.elements.salles.Salles;
import donjon.elements.salles.Sortie;
import donjon.interfaces.RanChoice;

public class GenerateurDonjon {
	private static int numMax;
	private static int nombreSalle;
	private boolean sortieExiste;
	private Map<Integer, Salles> carteDonjon = new HashMap<Integer, Salles>();
	private static Map<Integer, Salles> randomMap = new HashMap<Integer, Salles>();

	public void carteDemo() {
		/* Création des listes d'adversaires */
		List<Adversaires> advSalle1 = new ArrayList<>();
		List<Adversaires> advSalle2 = new ArrayList<>();
		List<Adversaires> advSalle3 = new ArrayList<>();
		List<Adversaires> advSalle4 = new ArrayList<>();

		/* Ajout des adversaires dans les listes d'adversaires */
		advSalle1.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle2.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle2.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle3.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle3.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle3.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle4.add(new Adversaires(Bestiaire.SQUELLETTES));

		/* Création des listes d'objets */
		List<Objet> objSalle0 = new ArrayList<>();
		List<Objet> objSalle1 = new ArrayList<>();
		List<Objet> objSalle2 = new ArrayList<>();
		List<Objet> objSalle3 = new ArrayList<>();
		List<Objet> objSalle4 = new ArrayList<>();
		List<Objet> objSalle5 = new ArrayList<>();

		/* Ajout d'objets dans les listes d'objets */
		objSalle0.add(new BourseOr(25));
		objSalle1.add(new BourseOr(25));
		objSalle1.add(new Potions(PotionsListe.SOIN));
		objSalle2.add(new BourseOr(50));
		objSalle2.add(new Potions(PotionsListe.SOIN));
		objSalle2.add(new Potions(PotionsListe.FORCE));
		objSalle2.add(new OneArmedBandit(25));
		objSalle3.add(new OneArmedBandit(50));
		objSalle3.add(new Potions(PotionsListe.SOIN));
		objSalle4.add(new OneArmedBandit(25));
		objSalle4.add(new Potions(PotionsListe.SOIN));
		objSalle5.add(new OneArmedBandit(75));
		objSalle5.add(new BourseOr(100));

		/* Création des salles, ajout des listes d'adversaires */
		Salles salle0 = new Entree(0);
		Salles salle1 = new SalleClassique(1, advSalle1);
		Salles salle2 = new SalleClassique(2, advSalle2);
		Salles salle3 = new SalleClassique(3, advSalle3);
		Salles salle4 = new SalleClassique(4, advSalle4);
		Salles salle5 = new Sortie(5);

		/* Ajout des listes d'objets dans les salles */
		salle0.setObjet(objSalle0);
		salle1.setObjet(objSalle1);
		salle2.setObjet(objSalle2);
		salle3.setObjet(objSalle3);
		salle4.setObjet(objSalle4);
		salle5.setObjet(objSalle5);

		/* Ajout des salles à la cartes du donjon */
		this.carteDonjon.put(0, salle0);
		this.carteDonjon.put(1, salle1);
		this.carteDonjon.put(2, salle2);
		this.carteDonjon.put(3, salle3);

		/* Ajout des couloirs (portes) sur les salles */
		salle0.getPortes().put(salle1.getNumSalle(), salle1);

		salle1.getPortes().put(salle0.getNumSalle(), salle0);
		salle1.getPortes().put(salle2.getNumSalle(), salle2);

		salle2.getPortes().put(salle1.getNumSalle(), salle1);
		salle2.getPortes().put(salle3.getNumSalle(), salle3);

		salle3.getPortes().put(salle2.getNumSalle(), salle2);
		salle3.getPortes().put(salle4.getNumSalle(), salle4);
		salle3.getPortes().put(salle5.getNumSalle(), salle5);

		salle4.getPortes().put(salle3.getNumSalle(), salle3);

		salle5.getPortes().put(salle3.getNumSalle(), salle3);
	}

	public static Salles salleRandom(int id, Salles ancienneSalle) {
		int nbAdvRandom = RanChoice.ranChoice(1, 3);
		int nbObjets = RanChoice.ranChoice(1, 2 * nbAdvRandom);
		List<Adversaires> listAdvRandom = new ArrayList<>();
		List<Objet> listObjRandom = new ArrayList<>();
		for (int i = 0; i < nbAdvRandom; i++) {
			int numRandomAdv = RanChoice.ranChoice(0, 100);
			if (numRandomAdv <= 95) {
				if (numRandomAdv <= 15) {
					listAdvRandom = null;
					break;
				} else if (numRandomAdv <= 60)
					listAdvRandom.add(new Adversaires(Bestiaire.ZOMBIS));
				else if (numRandomAdv <= 80)
					listAdvRandom.add(new Adversaires(Bestiaire.SQUELLETTES));
				else if (numRandomAdv > 80)
					listAdvRandom.add(new Adversaires(Bestiaire.VAMPIRES));
			} else
				listAdvRandom.add(new Adversaires(Bestiaire.LEECH));
		}
		for (int i = 0; i < nbObjets; i++) {
			int numRandomObj = RanChoice.ranChoice(0, 100);
			if (numRandomObj <= 95) {
				if (numRandomObj <= 30)
					listObjRandom.add(new OneArmedBandit(25));
				else if (numRandomObj <= 60)
					listObjRandom.add(new Potions(PotionsListe.SOIN));
				else if (numRandomObj <= 75)
					listObjRandom.add(new Potions(PotionsListe.FORCE));
				else if (numRandomObj <= 90)
					listObjRandom.add(new BourseOr(25));
				else if (numRandomObj <= 95)
					listObjRandom.add(new BourseOr(50));
			} else {
				listObjRandom.add(new Potions(PotionsListe.SOIN));
				listObjRandom.add(new OneArmedBandit(50));
			}
		}
		if (listAdvRandom != null && listObjRandom != null)
			return new SalleClassique(id, listAdvRandom, listObjRandom);
		else if (listObjRandom == null && listAdvRandom != null)
			return new SalleClassique(id, listAdvRandom);
//		else if (listObjRandom != null && listAdvRandom == null)
//			return new SalleClassique(id, listObjRandom);
		else if (listObjRandom == null && listAdvRandom == null)
			return new SalleClassique(id);
		return new SalleClassique(id);
	}

	/**
	 * Méthode permettant d'installer des couloirs sur des salles générées
	 * aléatoirement
	 **/
	public static Map<Integer, Salles> portesGen(Salles salle) {
		int numRandom;
		if (getNumMax() == 1 | getNumMax() == 2)
			numRandom = 1;
		else
			numRandom = RanChoice.ranChoice(1, 3);
		Map<Integer, Salles> map = new HashMap<>();
		if (!Salles.isSalleExist() && nombreSalle > numMax)
			for (int i = 0; i < numRandom; i++) {
				Salles nouvelleSalle = salleRandom(getNumMax(), salle);
				salle.getPortes().put(getNumMax(), nouvelleSalle);
				nouvelleSalle.getPortes().put(salle.getNumSalle(), salle);
				map.put(getNumMax(), nouvelleSalle);
				numMax++;
			}
		return map;
	}

	/** Méthode permettant de générer une salle sortie aléatoire **/
	public void sortieRandom() {
		for (Map.Entry<Integer, Salles> e : randomMap.entrySet()) {
			int i = e.getKey();
			if (i == getNumMax() - 1) {
				Salles ranS = e.getValue();
				Salles sortie = new Sortie(getNumMax(), ranS);
				randomMap.put(getNumMax(), sortie);
				ranS.getPortes().put(getNumMax(), sortie);
			}
		}
		setSortieExiste(true);
	}

	/**
	 * Méthode permettant de générer une carte aléatoire d'un nombre de salles
	 * définis.
	 **/
	public void carteRandom(int nbSalles) {
		nombreSalle = nbSalles;
		Salles entree = new Entree(0);
		List<Objet> listObjEntree = new ArrayList<>();
		listObjEntree.add(new BourseOr(50));
		listObjEntree.add(new OneArmedBandit(25));
		entree.setObjet(listObjEntree);
		getRandomMap().put(entree.getNumSalle(), entree);
		setNumMax(1);
		for (int i = 0; i < nbSalles - 2; i++) {
			Map<Integer, Salles> mapTemp = new HashMap<>();
			for (Map.Entry<Integer, Salles> entry : randomMap.entrySet()) {
				Salles salle = entry.getValue();
				Map<Integer, Salles> temp = portesGen(salle);
				if (temp != null) {
					mapTemp.putAll(temp);
				}
				// salle.setSalleExist(true);
				randomMap.put(entry.getKey(), salle);
			}
			randomMap.putAll(mapTemp);
		}
		if (!isSortieExiste())
			sortieRandom();
	}

	public void carteNiveau1() {
		/* Création des listes d'adversaires */
		List<Adversaires> advSalle1 = new ArrayList<>();
		List<Adversaires> advSalle2 = new ArrayList<>();
		List<Adversaires> advSalle3 = new ArrayList<>();
		List<Adversaires> advSalle4 = new ArrayList<>();
		List<Adversaires> advSalle5 = new ArrayList<>();
//		List<Adversaires> advSalle6 = new ArrayList<>();
		List<Adversaires> advSalle7 = new ArrayList<>();
		List<Adversaires> advSalle8 = new ArrayList<>();
		List<Adversaires> advSalle9 = new ArrayList<>();
		List<Adversaires> advSalle10 = new ArrayList<>();
		List<Adversaires> advSalle11 = new ArrayList<>();
//		List<Adversaires> advSalle12 = new ArrayList<>();
//		List<Adversaires> advSalle13 = new ArrayList<>();
		List<Adversaires> advSalle14 = new ArrayList<>();
		List<Adversaires> advSalle15 = new ArrayList<>();
//		List<Adversaires> advSalle16 = new ArrayList<>();
		List<Adversaires> advSalle17 = new ArrayList<>();
//		List<Adversaires> advSalle18 = new ArrayList<>();
		List<Adversaires> advSalle19 = new ArrayList<>();
		List<Adversaires> advSalle21 = new ArrayList<>();
		List<Adversaires> advSalle22 = new ArrayList<>();
//		List<Adversaires> advSalle23 = new ArrayList<>();

		/* Ajout des adversaires dans les listes d'adversaires */
		advSalle1.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle2.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle2.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle3.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle3.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle3.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle4.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle5.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle5.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle7.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle7.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle8.add(new Adversaires(Bestiaire.VAMPIRES));
		advSalle9.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle9.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle10.add(new Adversaires(Bestiaire.VAMPIRES));
		advSalle11.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle11.add(new Adversaires(Bestiaire.ZOMBIS));
		advSalle11.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle14.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle15.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle17.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle19.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle19.add(new Adversaires(Bestiaire.VAMPIRES));
		advSalle21.add(new Adversaires(Bestiaire.LEECH));
		advSalle22.add(new Adversaires(Bestiaire.SQUELLETTES));
		advSalle22.add(new Adversaires(Bestiaire.ZOMBIS));

		/* Création des listes d'objets */
		List<Objet> objSalle0 = new ArrayList<>();
		List<Objet> objSalle1 = new ArrayList<>();
		List<Objet> objSalle2 = new ArrayList<>();
		List<Objet> objSalle3 = new ArrayList<>();
		List<Objet> objSalle4 = new ArrayList<>();
		List<Objet> objSalle5 = new ArrayList<>();
		List<Objet> objSalle6 = new ArrayList<>();
		List<Objet> objSalle7 = new ArrayList<>();
		List<Objet> objSalle8 = new ArrayList<>();
		List<Objet> objSalle9 = new ArrayList<>();
		List<Objet> objSalle10 = new ArrayList<>();
		List<Objet> objSalle11 = new ArrayList<>();
		List<Objet> objSalle12 = new ArrayList<>();
		List<Objet> objSalle13 = new ArrayList<>();
		List<Objet> objSalle14 = new ArrayList<>();
		List<Objet> objSalle15 = new ArrayList<>();
		List<Objet> objSalle16 = new ArrayList<>();
		List<Objet> objSalle17 = new ArrayList<>();
		List<Objet> objSalle18 = new ArrayList<>();
		List<Objet> objSalle19 = new ArrayList<>();
		List<Objet> objSalle21 = new ArrayList<>();
		List<Objet> objSalle22 = new ArrayList<>();
		List<Objet> objSalle23 = new ArrayList<>();

		/* Ajout d'objets dans les listes d'objets */
		objSalle0.add(new BourseOr(25));
		objSalle1.add(new BourseOr(25));
		objSalle1.add(new Potions(PotionsListe.SOIN));
		objSalle2.add(new BourseOr(50));
		objSalle2.add(new Potions(PotionsListe.SOIN));
		objSalle2.add(new Potions(PotionsListe.FORCE));
		objSalle2.add(new OneArmedBandit(25));
		objSalle3.add(new OneArmedBandit(50));
		objSalle3.add(new Potions(PotionsListe.SOIN));
		objSalle4.add(new OneArmedBandit(25));
		objSalle4.add(new Potions(PotionsListe.SOIN));
		objSalle5.add(new OneArmedBandit(75));
		objSalle5.add(new BourseOr(100));
		objSalle6.add(new Potions(PotionsListe.SOIN));
		objSalle6.add(new OneArmedBandit(50));
		objSalle7.add(new Potions(PotionsListe.SOIN));
		objSalle7.add(new OneArmedBandit(50));
		objSalle7.add(new Potions(PotionsListe.FORCE));
		objSalle8.add(new BourseOr(50));
		objSalle9.add(new OneArmedBandit(50));
		objSalle10.add(new OneArmedBandit(50));
		objSalle11.add(new OneArmedBandit(50));
		objSalle12.add(new OneArmedBandit(50));
		objSalle13.add(new OneArmedBandit(50));
		objSalle14.add(new OneArmedBandit(50));
		objSalle15.add(new OneArmedBandit(50));
		objSalle16.add(new OneArmedBandit(50));
		objSalle17.add(new OneArmedBandit(50));
		objSalle18.add(new OneArmedBandit(50));
		objSalle19.add(new OneArmedBandit(50));
		objSalle21.add(new OneArmedBandit(50));
		objSalle22.add(new OneArmedBandit(50));
		objSalle23.add(new OneArmedBandit(50));

		/* Création des salles, ajout des listes d'adversaires */
		Salles salle0 = new Entree(0);
		Salles salle1 = new SalleClassique(1, advSalle1);
		Salles salle2 = new SalleClassique(2, advSalle2);
		Salles salle3 = new SalleClassique(3, advSalle3);
		Salles salle4 = new SalleClassique(4, advSalle4);
		Salles salle5 = new SalleClassique(5, advSalle5);
		Salles salle6 = new SalleClassique(6);
		Salles salle7 = new SalleClassique(7, advSalle7);
		Salles salle8 = new SalleClassique(8, advSalle8);
		Salles salle9 = new SalleClassique(9, advSalle9);
		Salles salle10 = new SalleClassique(10, advSalle10);
		Salles salle11 = new SalleClassique(11, advSalle11);
		Salles salle12 = new SalleClassique(12);
		Salles salle13 = new SalleClassique(13);
		Salles salle14 = new SalleClassique(14, advSalle14);
		Salles salle15 = new SalleClassique(15, advSalle15);
		Salles salle16 = new SalleClassique(16);
		Salles salle17 = new SalleClassique(17, advSalle17);
		Salles salle18 = new SalleClassique(18);
		Salles salle19 = new SalleClassique(19, advSalle19);
		Salles salle20 = new Sortie(20);
		Salles salle21 = new SalleClassique(21, advSalle21);
		Salles salle22 = new SalleClassique(22, advSalle22);
		Salles salle23 = new SalleClassique(23);

		/* Ajout des listes d'objets dans les salles */
		salle0.setObjet(objSalle0);
		salle1.setObjet(objSalle1);
		salle2.setObjet(objSalle2);
		salle3.setObjet(objSalle3);
		salle4.setObjet(objSalle4);
		salle5.setObjet(objSalle5);
		salle6.setObjet(objSalle6);
		salle7.setObjet(objSalle7);
		salle8.setObjet(objSalle8);
		salle9.setObjet(objSalle9);
		salle10.setObjet(objSalle10);
		salle11.setObjet(objSalle11);
		salle12.setObjet(objSalle12);
		salle13.setObjet(objSalle13);
		salle14.setObjet(objSalle14);
		salle15.setObjet(objSalle15);
		salle16.setObjet(objSalle16);
		salle17.setObjet(objSalle17);
		salle18.setObjet(objSalle18);
		salle19.setObjet(objSalle19);
		salle21.setObjet(objSalle21);
		salle22.setObjet(objSalle22);
		salle23.setObjet(objSalle23);

		/* Ajout des salles à la carte du donjon */
		this.carteDonjon.put(0, salle0);
		this.carteDonjon.put(1, salle1);
		this.carteDonjon.put(2, salle2);
		this.carteDonjon.put(3, salle3);
		this.carteDonjon.put(4, salle4);
		this.carteDonjon.put(5, salle5);
		this.carteDonjon.put(6, salle6);
		this.carteDonjon.put(7, salle7);
		this.carteDonjon.put(8, salle8);
		this.carteDonjon.put(9, salle9);
		this.carteDonjon.put(10, salle10);
		this.carteDonjon.put(11, salle11);
		this.carteDonjon.put(12, salle12);
		this.carteDonjon.put(13, salle13);
		this.carteDonjon.put(14, salle14);
		this.carteDonjon.put(15, salle15);
		this.carteDonjon.put(16, salle16);
		this.carteDonjon.put(17, salle17);
		this.carteDonjon.put(18, salle18);
		this.carteDonjon.put(19, salle19);
		this.carteDonjon.put(20, salle20);
		this.carteDonjon.put(21, salle21);
		this.carteDonjon.put(22, salle22);
		this.carteDonjon.put(23, salle23);

		/* Ajout des couloirs (portes) sur les salles */
		salle0.getPortes().put(salle1.getNumSalle(), salle1);

		salle1.getPortes().put(salle0.getNumSalle(), salle0);
		salle1.getPortes().put(salle2.getNumSalle(), salle2);
		salle1.getPortes().put(salle3.getNumSalle(), salle3);

		salle2.getPortes().put(salle1.getNumSalle(), salle1);
		salle2.getPortes().put(salle5.getNumSalle(), salle5);
		salle2.getPortes().put(salle6.getNumSalle(), salle6);

		salle3.getPortes().put(salle1.getNumSalle(), salle1);
		salle3.getPortes().put(salle4.getNumSalle(), salle4);

		salle4.getPortes().put(salle3.getNumSalle(), salle3);
		salle4.getPortes().put(salle7.getNumSalle(), salle7);
		salle4.getPortes().put(salle18.getNumSalle(), salle18);

		salle5.getPortes().put(salle2.getNumSalle(), salle2);
		salle5.getPortes().put(salle10.getNumSalle(), salle10);

		salle6.getPortes().put(salle2.getNumSalle(), salle2);
		salle6.getPortes().put(salle9.getNumSalle(), salle9);

		salle7.getPortes().put(salle4.getNumSalle(), salle4);
		salle7.getPortes().put(salle8.getNumSalle(), salle8);

		salle8.getPortes().put(salle7.getNumSalle(), salle7);
		salle8.getPortes().put(salle9.getNumSalle(), salle9);
		salle8.getPortes().put(salle14.getNumSalle(), salle14);

		salle9.getPortes().put(salle6.getNumSalle(), salle6);
		salle9.getPortes().put(salle12.getNumSalle(), salle12);
		salle9.getPortes().put(salle8.getNumSalle(), salle8);

		salle10.getPortes().put(salle5.getNumSalle(), salle5);
		salle10.getPortes().put(salle11.getNumSalle(), salle11);

		salle11.getPortes().put(salle10.getNumSalle(), salle10);
		salle11.getPortes().put(salle12.getNumSalle(), salle12);
		salle11.getPortes().put(salle13.getNumSalle(), salle13);

		salle12.getPortes().put(salle9.getNumSalle(), salle9);
		salle12.getPortes().put(salle11.getNumSalle(), salle11);

		salle13.getPortes().put(salle11.getNumSalle(), salle11);
		salle13.getPortes().put(salle19.getNumSalle(), salle19);

		salle14.getPortes().put(salle8.getNumSalle(), salle8);
		salle14.getPortes().put(salle15.getNumSalle(), salle15);
		salle14.getPortes().put(salle16.getNumSalle(), salle16);

		salle15.getPortes().put(salle14.getNumSalle(), salle14);
		salle15.getPortes().put(salle17.getNumSalle(), salle17);
		salle15.getPortes().put(salle18.getNumSalle(), salle18);

		salle16.getPortes().put(salle14.getNumSalle(), salle14);
		salle16.getPortes().put(salle17.getNumSalle(), salle17);
		salle16.getPortes().put(salle21.getNumSalle(), salle21);

		salle16.getPortes().put(salle14.getNumSalle(), salle14);
		salle16.getPortes().put(salle17.getNumSalle(), salle17);
		salle16.getPortes().put(salle21.getNumSalle(), salle21);

		salle17.getPortes().put(salle15.getNumSalle(), salle15);
		salle17.getPortes().put(salle16.getNumSalle(), salle16);
		salle17.getPortes().put(salle23.getNumSalle(), salle23);

		salle18.getPortes().put(salle4.getNumSalle(), salle4);
		salle18.getPortes().put(salle15.getNumSalle(), salle15);

		salle19.getPortes().put(salle13.getNumSalle(), salle13);
		salle19.getPortes().put(salle20.getNumSalle(), salle20);
		salle19.getPortes().put(salle21.getNumSalle(), salle21);

		salle20.getPortes().put(salle19.getNumSalle(), salle19);

		salle21.getPortes().put(salle19.getNumSalle(), salle19);
		salle21.getPortes().put(salle16.getNumSalle(), salle16);
		salle21.getPortes().put(salle22.getNumSalle(), salle22);

		salle22.getPortes().put(salle21.getNumSalle(), salle21);
		salle22.getPortes().put(salle23.getNumSalle(), salle23);

		salle23.getPortes().put(salle17.getNumSalle(), salle17);
		salle23.getPortes().put(salle22.getNumSalle(), salle22);

	}

	public static int getNumMax() {
		return numMax;
	}

	public void setNumMax(int numMax) {
		GenerateurDonjon.numMax = numMax;
	}

	public Map<Integer, Salles> getCarteDonjon() {
		return carteDonjon;
	}

	public void setCarteDonjon(Map<Integer, Salles> carteDonjon) {
		this.carteDonjon = carteDonjon;
	}

	public boolean isSortieExiste() {
		return sortieExiste;
	}

	public void setSortieExiste(boolean sortieExiste) {
		this.sortieExiste = sortieExiste;
	}

	public Map<Integer, Salles> getRandomMap() {
		return randomMap;
	}

	public void setRandomMap(Map<Integer, Salles> randomMap) {
		GenerateurDonjon.randomMap = randomMap;
	}
}