package main;

import java.util.Scanner;

public class MainMethod {

	static String result;
	static String number_system;

	public static void main(String[] args) { // starte das Programm
		processInput();

	}

	// region Hauptmethode
	private static void processInput() {
		while (true) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

			System.out.print("Bitte gib die umzurechnende Zahl ein: "); // macht drei Abfragen für die nötigen Informationen
			String number = scanner.nextLine();

			System.out.print("Bitte gib das Start-Zahlensystem ein (dec, hex, dual): ");
			String start = scanner.nextLine().toUpperCase();

			System.out.print("Bitte gib das Ziel-Zahlensystem ein (dec, hex, dual): ");
			String end = scanner.nextLine().toUpperCase();

			// Kern des Programmes. Überprüft die abgefragten Werte auf korrektheit und führt jenachdem die passende Methode aus
			try {
				switch (start) {
				case "DEC":
				case "DEZ":
					if (end.equals("DUAL")) {
						result = dec2Dual(number);
						number_system = "(2)";
					} else if (end.equals("HEX")) {
						result = dec2Hex(number);
						number_system = "(16)";
					} else if (end.equals("DEC") || end.equals("DEZ")) {
						System.out.println("Dezimal nach Dezimal ist unzulässig!");
					} else {
						System.out.println("Das eingegebene Zahlensystem wurde nicht erkannt...");
					}
					break;
				case "DUAL":
					if (checkBinary(number)) {
						if (end.equals("DEC") || end.equals("DEZ")) {
							result = dual2Dec(number);
							number_system = "(10)";
						} else if (end.equals("HEX")) {
							result = dual2Hex(number);
							number_system = "(16)";
						} else if (end.equals("DUAL")) {
							System.out.println("Dual nach Dual ist unzulässig!");
						} else {
							System.out.println("Das eingegebene Zahlensystem wurde nicht erkannt...");
						}
					} else {
						throw new NumberFormatException();
					}
					break;
				case "HEX":
					if (end.equals("DUAL")) {
						result = hex2Dual(number);
						number_system = "(2)";
					} else if (end.equals("DEC") || end.equals("DEZ")) {
						result = hex2Dec(number);
						number_system = "(10)";
					} else if (end.equals("HEX")) {
						System.out.println("Hexadezimal nach Hexaezimal ist unzulässig!");
					} else {
						System.out.println("Das eingegebene Zahlensystem wurde nicht erkannt...");
					}
					break;
				default:
					System.out.println("Das eingegebene Zahlensystem wurde nicht erkannt...");
					break;
				}

				System.out.println("Das Ergebnis ist: " + result + number_system); // gibt die Antwort aus
			} catch (NumberFormatException e) {
				System.out.println("Die eingegebene Zahl entspricht dem falschen Zahlensystem...");
			} catch (Exception e) {
				System.out.println("Das eingegebene Zahlensystem wurde nicht erkannt...");
			}
		}
	}
	// endregion

	// region Eingabe prüfen
	private static boolean checkBinary(String bin) {
		boolean valid = true;

		char[] a = bin.toCharArray();

		for (char c : a) {
			valid = ((c >= '0') && (c <= '1')); // Wenn das Binary etwas anderes als 0 oder 1 enthält, gib false zurück
			if (!valid) {
				break;
			}
		}
		return valid;
	}
	// endregion

	// region Umrechnungsmethoden
	private static String dec2Dual(String dec) {
		String dual = "";
		int decInt = Integer.parseInt(dec);

		while (decInt > 0) {
			dual = decInt % 2 + dual;
			decInt = decInt / 2;
		}
		return dual;
	}

	private static String dec2Hex(String dec) { // über dual
		String dual = dec2Dual(dec);
		String hex = dual2Hex(dual);
		return hex;
	}

	private static String dual2Dec(String dual) {
		int dec = 0;

		for (int i = 0; i < dual.length(); i++) {
			if (dual.charAt(i) == '1') {
				dec = dec * 2 + 1;
			} else if (dual.charAt(i) == '0') {
				dec = dec * 2;
			}
		}
		return dec + "";
	}

	private static String dual2Hex(String dual) {
		String hex = "";
		while (dual.length() % 4 != 0) { // fülle das Binary auf, dass es durch 4 teilbar ist
			dual = "0" + dual;
		}
		for (int i = 0; i < dual.length(); i += 4) {
			String c = dual.substring(i, i + 4);
			switch (c) {
			case "0000":
				hex += "0";
				break;
			case "0001":
				hex += "1";
				break;
			case "0010":
				hex += "2";
				break;
			case "0011":
				hex += "3";
				break;
			case "0100":
				hex += "4";
				break;
			case "0101":
				hex += "5";
				break;
			case "0110":
				hex += "6";
				break;
			case "0111":
				hex += "7";
				break;
			case "1000":
				hex += "8";
				break;
			case "1001":
				hex += "9";
				break;
			case "1010":
				hex += "A";
				break;
			case "1011":
				hex += "B";
				break;
			case "1100":
				hex += "C";
				break;
			case "1101":
				hex += "D";
				break;
			case "1110":
				hex += "E";
				break;
			case "1111":
				hex += "F";
				break;
			}
		}
		return hex;
	}

	private static String hex2Dual(String hex) {
		hex = hex.toUpperCase();
		String dual = "";
		for (int i = 0; i < hex.length(); i++) {
			String c = hex.charAt(i) + "";
			switch (c) {
			case "0":
				dual += "0000";
				break;
			case "1":
				dual += "0001";
				break;
			case "2":
				dual += "0010";
				break;
			case "3":
				dual += "0011";
				break;
			case "4":
				dual += "0100";
				break;
			case "5":
				dual += "0101";
				break;
			case "6":
				dual += "0110";
				break;
			case "7":
				dual += "0111";
				break;
			case "8":
				dual += "1000";
				break;
			case "9":
				dual += "1001";
				break;
			case "A":
				dual += "1010";
				break;
			case "B":
				dual += "1011";
				break;
			case "C":
				dual += "1100";
				break;
			case "D":
				dual += "1101";
				break;
			case "E":
				dual += "1110";
				break;
			case "F":
				dual += "1111";
				break;
			}
		}
		return dual;
	}

	private static String hex2Dec(String hex) { // über dual
		String dual = hex2Dual(hex);
		String dec = dual2Dec(dual);
		return dec;
	}
	// endregion
}
