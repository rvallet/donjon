package donjon.interfaces.cmd;

import donjon.interfaces.InterfaceCMD;
import donjon.jeu.MainJeu;

public class CmdRegarder implements InterfaceCMD {
	public static final String CMD = "regarder";

	@Override
	public void run() {
		MainJeu.joueur.regarderSalle();
	}
}