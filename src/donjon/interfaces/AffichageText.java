package donjon.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AffichageText {

	public static void affichageText(String relPath) {
		InputStream fr = null;
		BufferedReader br = null;
		try {
			fr = AffichageText.class.getResourceAsStream(relPath);
			br = new BufferedReader(new InputStreamReader(fr));
			while (br.readLine() != null) {
				System.out.println(br.readLine());
			}
		} catch (IOException e) {
			System.err.println("*** Le Fichier indiqué n'a pas été trouvé à cet emplacement !***");
			System.err.println(relPath);
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException | NullPointerException f) {
				f.printStackTrace();
			}
		}
	}
	public static void textDelai(String str) throws InterruptedException {
		for (int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));
			Thread.sleep(20);
		}
		System.out.println();
	}
	public static void textAsciiArt(String str) {
		int L = 6;
		int H = 5;
		String str1 = str.toLowerCase();
		String alphabet = "abcdefghijklmnopqrstuvwxyz. ";
		String[] asciiArt = new String[H];				
		asciiArt[0] = " ###  ####   #### ####  ##### #####  #### #   # #####  #### #   # #     #   # #   #  ###  ####   ###  ####   #### ##### #   # #   # #   # #   # #   # #####             ####  ";
		asciiArt[1] = "#   # #   # #     #   # #     #     #     #   #   #      #  #  #  #     ## ## ##  # #   # #   # #   # #   # #       #   #   # #   # #   #  # #   # #     #                  # ";
		asciiArt[2] = "##### ####  #     #   # ###   ###   #  ## #####   #      #  ###   #     # # # # # # #   # ####  #   # ####   ###    #   #   # #   # # # #   #     #    #                  ##  ";
		asciiArt[3] = "#   # #   # #     #   # #     #     #   # #   #   #   #  #  #  #  #     #   # #  ## #   # #      ###  #  #      #   #   #   #  # #   # #   # #    #   #      ###         #    ";
		asciiArt[4] = "#   # ####   #### ####  ##### #      #### #   # #####  ##   #   # ##### #   # #   #  ###  #         # #   # ####    #    ###    #    # #  #   #   #   #####  ###         #    ";

		String[] result = new String[H];

		for (int i = 0; i < H; i++) {
			result[i] = "******  ";
		}

		for (int k = 0; k < str.length(); k++) {
			if (alphabet.contains(Character.toString(str1.charAt(k)))) {
				int letIndex = alphabet.indexOf(str1.charAt(k));
				for (int j = 0; j < H; j++) {
					result[j] += asciiArt[j].substring(L * letIndex, L * (letIndex + 1));
				}

			} else {
				for (int j = 0; j < H; j++)
					result[j] += asciiArt[j].substring(L * 26, L * 27);
			}
		}

		for (int i = 0; i < H; i++)
			result[i] += "  ******";

		for (int i = 0; i < result[0].length(); i++)
			System.out.print("*");

		System.out.println();

		for (int j = 0; j < result.length; j++)
			System.out.println(result[j]);

		for (int i = 0; i < result[0].length(); i++)
			System.out.print("*");
		System.out.println();
	}
}