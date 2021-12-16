package donjon.interfaces.cmd;

import donjon.interfaces.InterfaceCMD;

public class CmdQuit implements InterfaceCMD {
	public static final String CMD = "quit";
	private boolean quit = false;

	@Override
	public void run() {
		System.out.println("Vous quittez la partie en cours...");
		setQuit(true);
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}
}