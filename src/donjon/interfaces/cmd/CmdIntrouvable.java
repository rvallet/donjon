package donjon.interfaces.cmd;

import donjon.interfaces.InterfaceCMD;

public class CmdIntrouvable implements InterfaceCMD {

	@Override
	public void run() {
		System.out.println("*** La commande est introuvable. Tapez 'aide' pour afficher les commandes disponibles ***");
	}
}