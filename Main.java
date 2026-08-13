package condict;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

class Main {

	public static void createDictionary(Scanner sc, Dictionary dict) {
		System.out.println("Would you like to add words(1), go back to the main menu(2)");
		String choice = sc.nextLine();
		if (choice.equals("1")) {

			int Runs = 1;
			Boolean entriesDone = false;
			while (!entriesDone) {
				if (Runs > 1) {
					System.out.println("If you are finished type STOP");
				}

				System.out.println("What is your word called in its language:");
				String word = sc.nextLine();
				if (word.equals("STOP")) {
					dict.fileSaver();
					} else {
					Runs++;
					System.out.println("Enter pronunciation: ");
					String ipa = sc.nextLine();
					System.out.println("Enter translation into English: ");
					String translation = sc.nextLine();
					String key = translation;
					System.out.println("Enter part of speech: ");
					String pos = sc.nextLine();
					Entry entry = new Entry(word, ipa, translation, pos);

					dict.addEntry(key, entry);
				} 
		} 
		} else if (choice.equals("2")) {

		} else {
			System.out.println("Invalid response");
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
			
		System.out.println("Welcome to Condict");
		System.out.println("Would you like to create a new dictonary (1) or load up a existing one(2)?");
		String choice = sc.nextLine();

		Boolean validChoice = false;
		while (!validChoice) {
			if (choice.equals("1")) {

				Dictionary dict = new Dictionary();
				System.out.println("What is the name of your language?");
				dict.cLang = sc.nextLine();
				validChoice = true;
				createDictionary(sc, dict);
			} else if (choice.equals("2")) {

			} else {
				System.out.println("Invalid choice");
			}
		}
}
}
