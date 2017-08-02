package mmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User extends Startseite implements Serializable{
		
	/**
	 * @param benutzername
	 * @param vorname
	 * @param nachname
	 * @param email
	 */
	int Nummer;
	
		
	User(String benutzername, String vorname, String nachname,  String email, int Nummer){
		Registrierung2 R1 = new Registrierung2();
		benutzername = R1.getBenutzername();
		vorname = R1.getVorname();
		nachname = R1.getNachname();
		email = R1.geteMail_text();
	}


}
