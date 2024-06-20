package donjon.interfaces;

import java.util.ArrayList;
import java.util.List;

import donjon.interfaces.cmd.CmdCaracteristiques;
import donjon.interfaces.cmd.CmdCombattre;
import donjon.interfaces.cmd.CmdDeplacer;
import donjon.interfaces.cmd.CmdHelp;
import donjon.interfaces.cmd.CmdIntrouvable;
import donjon.interfaces.cmd.CmdQuit;
import donjon.interfaces.cmd.CmdRegarder;
import donjon.interfaces.cmd.CmdUtiliser;
import donjon.interfaces.cmd.CmdVide;

public abstract class InterpreteurCmd implements InterfaceCMD {

	private final static String BOURSE = "bourse";
	private final static String BANDIT = "bandit";
	private final static String SOIN = "soin";
	private final static String VIE = "VIE";
	private final static String FORCE = "force";
	private final static String PRECISEZ = "precisez";

	public static InterfaceCMD create(String cmd) {
		InterfaceCMD cmdLauched = null;
		String[] splitTemp = cmd.split(" ");
		List<String> cmdSplit = new ArrayList<>();
		for (int i = 0; i < splitTemp.length; i++) {
			cmdSplit.add(splitTemp[i]);
		}
		if (cmd.length() == 0) {
			cmdLauched = new CmdVide();

		} else if (CmdRegarder.CMD.equalsIgnoreCase(cmdSplit.get(0))) {
			cmdLauched = new CmdRegarder();

		} else if (CmdDeplacer.CMD.equalsIgnoreCase(cmdSplit.get(0))) {
			if (cmdSplit.size() > 1) {
				cmdLauched = new CmdDeplacer(cmdSplit.get(1));
			} else if (cmdSplit.size() < 2) {
				System.out.println("Quelle direction ?");
				cmdLauched = new CmdDeplacer(cmdSplit.get(0));
			}
		} else if (CmdCombattre.CMD.equalsIgnoreCase(cmdSplit.get(0))) {
			if (cmdSplit.size() < 2) {
				cmdLauched = new CmdCombattre();
			} else if (cmdSplit.size() > 1) {
				try {
					cmdLauched = new CmdCombattre(Integer.parseInt(cmdSplit.get(1)));
				} catch (NumberFormatException e) {
					System.out.println(
							"Vous devez entrer un chiffre après la commande combattre si la salle contiens plusieurs adversaires");
					cmdLauched = new CmdCombattre();
				}
			}
		} else if (CmdUtiliser.CMD.equalsIgnoreCase(cmdSplit.get(0))) {
			if (cmdSplit.size() > 1) {
				if (cmdSplit.toString().toLowerCase().contains(BOURSE))
					cmdLauched = new CmdUtiliser(cmdSplit.get(0), cmdSplit.get(1));
				else if (cmdSplit.toString().toLowerCase().contains(BANDIT))
					cmdLauched = new CmdUtiliser(cmdSplit.get(0), cmdSplit.get(1));
				else if (cmdSplit.toString().toLowerCase().contains(SOIN) || cmdSplit.toString().contains(VIE))
					cmdLauched = new CmdUtiliser(cmdSplit.get(0), cmdSplit.get(1), SOIN);
				else if (cmdSplit.toString().toLowerCase().contains(FORCE))
					cmdLauched = new CmdUtiliser(cmdSplit.get(0), cmdSplit.get(1), FORCE);
				else if (!cmdSplit.toString().toLowerCase().contains(FORCE)
						&& !cmdSplit.toString().contains(SOIN)) {
					cmdLauched = new CmdUtiliser(cmdSplit.get(0), cmdSplit.get(1), PRECISEZ);
				}
			} else if (cmdSplit.size() < 2)
				cmdLauched = new CmdUtiliser(cmdSplit.get(0));

		} else if (CmdQuit.CMD.equalsIgnoreCase(cmdSplit.get(0))) {
			cmdLauched = new CmdQuit();

		} else if (CmdHelp.CMD.equalsIgnoreCase(cmdSplit.get(0))) {
			cmdLauched = new CmdHelp();

		} else if (CmdCaracteristiques.CMD.equalsIgnoreCase(cmdSplit.get(0))) {
			cmdLauched = new CmdCaracteristiques();

		} else {
			cmdLauched = new CmdIntrouvable();
		}
		return cmdLauched;
	}
}