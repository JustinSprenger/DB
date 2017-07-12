package main;

import java.util.Scanner;

import InOut.Read;
import database.Database;

public class Main {

	public static void main(String[] args) {
		Database db = new Database("db.f4.htw-berlin.de",5432 , "_s0556255__beleg", "_s0556255__beleg_generic", "Starten123");
		
		Read sc = new Read();
		int auswahl;
		String[] values = new String[4];
		
		do{
		
		System.out.println("1 Adressen ausgeben");
		System.out.println("2 durch Adressen navigieren");
		System.out.println("3 neuer Eintrag einer Adresse");
		System.out.println("4 Adresse löschen");
		System.out.println("5 Beenden");
		
		auswahl = sc.readInt();
		
		switch (auswahl) {
			case 1:
				db.getDatabase();
				System.out.println("");
			break;
			
			case 2:
				db.row();
				System.out.println("");
			break;
			
			case 3:
				System.out.print("Straße: ");
				values[0] = sc.readString();
				System.out.print("Hausnummer: ");
				values[1] = sc.readString();
				System.out.print("Ort: ");
				values[2] = sc.readString();
				System.out.print("Postleitzahl: ");
				values[3] = sc.readString();
				
				db.insertDatabase(values);
				
				System.out.println("");
			break;
			
			case 4:
				int a = 0;
				int zeile = 0;
				String[] val = new String[4];
				System.out.println("Bitte wählen sie einen Datensatz aus");
				zeile = db.getDatabasewn();
				a = sc.readInt();
				if(a>zeile-1||a==0){
					System.out.println("Die angabe ist ausserhalb des Wertebereichs");
				}else{
					val = db.getDatabasefromnum(a);
					db.updateDatabase(val);
				}

				System.out.println("");
			break;
			
		default:
			break;
		}
		
		
		
		
		}while(auswahl!=5);
		
	}

}
