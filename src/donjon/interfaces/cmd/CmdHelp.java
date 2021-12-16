package donjon.interfaces.cmd;

import donjon.interfaces.InterfaceCMD;

public class CmdHelp implements InterfaceCMD {
	public static final String CMD = "aide";

	@Override
	public void run() {
		System.out.println("****************************************************************");
		System.out.println("** stats     - Permet d'afficher ses caractéristiques         **");
		System.out.println("** direction - permet de se déplacer vers une autre salle     **");
		System.out.println("** combattre - permet d'engager un combat à mort              **");
		System.out.println("** regarder  - permet d'obtenir des informations sur la salle **");
		System.out.println("** utiliser  - permet d'utiliser un objet                     **");
		System.out.println("** quit      - permet de sortir du jeu                        **");
		System.out.println("****************************************************************");
	}
}