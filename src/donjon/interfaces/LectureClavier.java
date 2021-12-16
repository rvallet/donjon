package donjon.interfaces;

import java.util.Scanner;

public abstract class LectureClavier {
	private static Scanner scanner = new Scanner(System.in);

	public static int readInt() {
		return scanner.nextInt();
	}

	public static String readWord() {
		return scanner.next();
	}

	public static String readLine() {
		return scanner.nextLine();
	}
}