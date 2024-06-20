package donjon.interfaces.cmd;

import donjon.interfaces.InterfaceCMD;

public class CmdVide implements InterfaceCMD {

	@Override
	public void run() {
		System.out.println("**vide**");
	}

}