package donjon.interfaces.cmd;

import donjon.interfaces.InterfaceCMD;
import donjon.jeu.MainJeu;

public class CmdCaracteristiques implements InterfaceCMD {
	public static final String CMD = "stats";

	@Override
	public void run() {
		System.out.println("********* " + CMD + " ***********");
		System.out.println("Nom         : " + MainJeu.joueur.getNom());
		System.out.println("Santé       : " + MainJeu.joueur.getVie());
		System.out.println("Force       : " + MainJeu.joueur.getForce());
		System.out.println("Pièces d'or : " + MainJeu.joueur.getPiecesOr());
		System.out.println("***************************");
	}
}