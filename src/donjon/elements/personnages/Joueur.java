package donjon.elements.personnages;

import java.util.Map;
import java.util.Random;

import donjon.elements.donjon.AffichageCarte;
import donjon.elements.donjon.Combat;
import donjon.elements.objets.BourseOr;
import donjon.elements.objets.Objet;
import donjon.elements.objets.OneArmedBandit;
import donjon.elements.objets.Potions;
import donjon.elements.salles.Salles;
import donjon.interfaces.AffichageText;
import donjon.interfaces.RanChoice;
import donjon.jeu.MainJeu;

public class Joueur extends Personnages {
	private String nom;
	public Salles salleActuelle;
	private boolean sortieTrouve;

	public Joueur(String nom, Salles salleActuelle) {
		super(100, 10);
		this.nom = nom;
		this.salleActuelle = salleActuelle;
	}

	/**
	 * Méthode permettant d'afficher les dégats causés par le joueur ainsi que ses
	 * points de vie restants.
	 **/
	public void attaquer(Adversaires c) {
		if (getVie() > 0) {
			System.out.println("**** Attaque de " + this.nom + " ****");
			System.out.println(this.nom + " attaque et cause " + this.getForce() + " points de dégats");
			if ("Liche".equalsIgnoreCase(c.type.getType())) {
				final int absorbedLife = RanChoice.ranChoice(1, 5);
				System.out.println("La liche absorbe " + absorbedLife + " points de vie !");
				this.setVie(this.getVie() + absorbedLife);
			}
			c.setVie(c.getVie() - this.getForce());
			System.out.println(this.nom + " a encore " + getVie() + " points de vie.");
			System.out.println();
		} else if (getVie() <= 0) {
			setDead(true);
		}
	}

	/**
	 * Méthode permettant de lancer un combat contre un adversaire
	 **/
	public void combattre() throws InterruptedException {
		if (this.salleActuelle.getAdv() != null) {
			if (this.salleActuelle.getAdv().get(this.salleActuelle.getAdv().size() - 1).isDead()) {
				System.out.println("Cet adversaire est déjà mort");
			} else {
				for (int i = 0; i < this.salleActuelle.getAdv().size(); i++) {
					if (!this.salleActuelle.getAdv().get(i).isDead()) {
						new Combat(this.salleActuelle.getAdv().get(i), this).lancerCombat();
						break;
					}
				}
			}
		}
	}

	public void combattre(int numAdv) throws InterruptedException {
		if (this.salleActuelle.getAdv() != null) {
			if (!this.salleActuelle.getAdv().get(numAdv).isDead()) {
				new Combat(this.salleActuelle.getAdv().get(numAdv), this).lancerCombat();
			} else
				System.out.println("Cet adversaire est déjà mort");
		}
	}

	/**
	 * Méthode permettant d'utiliser un objet
	 **/
	public void utiliser(String runObj) {
		if (this.salleActuelle.getObjet() != null) {
			for (int i = 0; i < this.salleActuelle.getObjet().size(); i++) {
				if (this.salleActuelle.getObjet().get(i).getNom().equalsIgnoreCase(runObj)) {
					if (runObj.equalsIgnoreCase("potion de soin")) {
						System.out.println("Vous buvez la potion de soin...");
						System.out.println("Vie : +" + ((Potions) this.salleActuelle.getObjet().get(i)).getSoin());
						this.vie += ((Potions) this.salleActuelle.getObjet().get(i)).getSoin();
						this.salleActuelle.getObjet().remove(i);
						break;
					} else if (runObj.equalsIgnoreCase("potion de force")) {
						System.out.println("Vous buvez la potion de force...");
						System.out.println("Force : +" + ((Potions) this.salleActuelle.getObjet().get(i)).getForce());
						this.force += ((Potions) this.salleActuelle.getObjet().get(i)).getForce();
						this.salleActuelle.getObjet().remove(i);
						break;
					} else if (runObj.equalsIgnoreCase("bourse d'or")) {
						System.out.println("Vous ramassez la bourse d'or...");
						System.out.println(
								"Pièces d'or : +" + ((BourseOr) this.salleActuelle.getObjet().get(i)).getPiecesOr());
						this.piecesOr += ((BourseOr) this.salleActuelle.getObjet().get(i)).getPiecesOr();
						this.salleActuelle.getObjet().remove(i);
						break;
					} else if (runObj.equalsIgnoreCase("bandit manchot")) {
						if (this.getPiecesOr() >= ((OneArmedBandit) this.salleActuelle.getObjet().get(i)).getRancon()) {
							System.out.println("Vous payer une rançon au Bandit Manchot...("
									+ ((OneArmedBandit) this.salleActuelle.getObjet().get(i)).getRancon()
									+ " pièces d'or)");
							this.piecesOr -= ((OneArmedBandit) this.salleActuelle.getObjet().get(i)).getRancon();
							Objet objAleatoire = ((OneArmedBandit) this.salleActuelle.getObjet().get(i)).getObjet();
							System.out.println("Vous obtenez : " + objAleatoire.getNom() + " .");
							this.salleActuelle.getObjet().remove(i);
							this.salleActuelle.getObjet().add(this.salleActuelle.getObjet().size(), objAleatoire);
							utiliser(objAleatoire.getNom());
							break;
						} else if (this.getPiecesOr() < ((OneArmedBandit) this.salleActuelle.getObjet().get(i))
								.getRancon()) {
							System.out.println("Vous n'avez pas les "
									+ ((OneArmedBandit) this.salleActuelle.getObjet().get(i)).getRancon()
									+ " pièces d'or pour payer la rançon de ce Bandit Manchot...");
							System.out.println("Il part sans vous laisser le moindre objet");
							this.salleActuelle.getObjet().remove(i);
							break;
						}
					}
				}
			}
		} else if ((this.salleActuelle.getObjet() == null))
			System.out.println("Il n'y a pas d'objet dans cette salle");
	}

	/**
	 * Méthode permettant de changer de salle
	 **/
	public void changerSalle(String direction) {
		Boolean verifPortes = false;
		for (Map.Entry<Integer, Salles> e : this.salleActuelle.getPortes().entrySet()) {
			if (Integer.parseInt(direction) == e.getKey())
				verifPortes = true;
		}
		if (this.salleActuelle.getAdv() != null
				&& !this.salleActuelle.getAdv().get(this.salleActuelle.getAdv().size() - 1).isDead()) {
			System.out.println("Attention ! Il semblerais qu'on vous empêche de quitter cette salle");
		} else if (this.salleActuelle.getAdv() == null || this.salleActuelle.getAdv() != null
				&& this.salleActuelle.getAdv().get(this.salleActuelle.getAdv().size() - 1).isDead()) {
			if (verifPortes) {
				for (Map.Entry<Integer, Salles> e : this.salleActuelle.getPortes().entrySet()) {
					if (Integer.parseInt(direction) == e.getKey().intValue())
						this.salleActuelle = e.getValue();
				}
				try {
					MainJeu.joueur.arriveSalle();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("La direction indiquée doit correspondre à un numéro de couloir accéssible");
			}
		}

	}

	/**
	 * Méthode permettant d'afficher une phrase d'acceuil à l'netrée dans une salle
	 **/
	public void arriveSalle() throws InterruptedException {
		if (this.salleActuelle.getNom().equalsIgnoreCase("entrée")) {
			AffichageText.textDelai("Vous êtes à l'entrée du donjon");
			System.out.println("");
			AffichageText.textDelai("Vous devez en trouver la sortie et survivre au nombreux adversaires qui le peuplent");
			System.out.println("");
		} else {
			AffichageText.textDelai("Vous arrivez dans une Salle " + this.salleActuelle.getNom());
			System.out.println("");
		}
		AffichageCarte.affichageSalle();
		if (this.salleActuelle.getNom().equalsIgnoreCase("Sortie")) {
			System.out.println("");
			System.out.println("*****************************************************");
			System.out.println("* Félicitation ! Vous sortez indemne de ce donjon ! *");
			System.out.println("*****************************************************");
			System.out.println("");
			Thread.sleep(2000);
			AffichageText.affichageText("/graph/donjon-victoire.txt");
			MainJeu.joueur.setSortieTrouve();
		} else if (this.salleActuelle.getAdv() != null
				&& !this.salleActuelle.getAdv().get(this.salleActuelle.getAdv().size() - 1).isDead()) {
			System.out.println("Attention ! Un adversaire vous empêche de quitter cette salle");
		} else if (this.salleActuelle.getAdv() != null
				&& this.salleActuelle.getAdv().get(this.salleActuelle.getAdv().size() - 1).isDead()) {
			System.out.println("Un combat à eu lieu ici ! Des restes monstres gisent au sol");
		} else if (this.salleActuelle.getAdv() == null) {
			System.out.println("Cette salle semble tranquille et silencieuse");
		}
	}

	/**
	 * Méthode permettant d'obtenir des informations sur la salle actuelle
	 * (Adversaires présents, objets présents, couloirs accessibles à partir de
	 * cette salle).
	 **/
	public void regarderSalle() {
		System.out.println("**************************************************");
		System.out.println("Vous trouvez dans une salle " + this.salleActuelle.getNom() + " (Salle n°"
				+ this.salleActuelle.getNumSalle() + ")");
		if (this.salleActuelle.getAdv() != null) {
			if (this.salleActuelle.getAdv().size() < 2) {
				String advEtat = "";
				if (this.salleActuelle.getAdv().get(0).isDead()) {
					advEtat = "(Mort)";
				} else
					advEtat = "(En vie)";
				System.out.println("Il y a " + this.salleActuelle.getAdv().size() + " adversaire : "
						+ this.salleActuelle.getAdv().get(0).getNom() + " " + advEtat);
			} else if (this.salleActuelle.getAdv().size() > 1) {
				String advEtat = "";
				System.out.println("Il y a " + this.salleActuelle.getAdv().size() + " adversaires : ");
				for (int i = 0; i < this.salleActuelle.getAdv().size(); i++) {
					if (this.salleActuelle.getAdv().get(i).isDead())
						advEtat = "(Mort)";
					else
						advEtat = "(En vie)";
					System.out.println(i + 1 + " - " + this.salleActuelle.getAdv().get(i).getNom() + " " + advEtat);
				}
			}
		} else if (this.salleActuelle.getAdv() == null)
			System.out.println("Il y a aucun adversaire dans cette salle");
		if (this.salleActuelle.getObjet() != null) {

			if (this.salleActuelle.getObjet().size() < 2 && this.salleActuelle.getObjet().size() > 0) {
				System.out.println("          ----------          ");
				System.out.println("Un objet dans la salle : ");
				System.out.print(this.salleActuelle.getObjet().get(0).getNom());
				System.out.println(" ");
			} else if (this.salleActuelle.getObjet().size() > 1) {
				System.out.println("          ----------          ");
				System.out.println("Il y a des objets dans la salle : ");
				for (int i = 0; i < this.salleActuelle.getObjet().size(); i++) {
					if (this.salleActuelle.getObjet().get(i) != null)
						System.out.println(i + 1 + " - " + this.salleActuelle.getObjet().get(i).getNom());
				}
			} else if (this.salleActuelle.getObjet().size() == 0) {
				System.out.println("          ----------          ");
				System.out.println("Il ne reste plus aucun objet dans cette salle");
			}
		} else
			System.out.println("Il y a aucun objet dans cette salle");

		System.out.println("          ----------          ");
		MainJeu.joueur.salleActuelle.regarderPortes();
		System.out.println("**************************************************");
	}

	public boolean getSortieTrouve() {
		return sortieTrouve;
	}

	public void setSortieTrouve() {
		this.sortieTrouve = true;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Salles getSalleActuelle() {
		return salleActuelle;
	}

	public boolean finDeJeu() {
		return MainJeu.joueur.getSortieTrouve() || MainJeu.joueur.isDead();
	}
}