package mmap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Registrierung2 extends Startseite {

	GridPane reGestrierung = new GridPane();
	Scene szene_2 = new Scene(reGestrierung, 600, 600);

	Label benutzerName = new Label("Benutzername");

	Label passWort = new Label("Passwort");

	TextField tfBenutzername = new TextField();

	PasswordField tfPasswort = new PasswordField();

	Label VorName = new Label("Vorname");
	TextField tfVorname = new TextField();

	Label NachName = new Label("Nachname");
	TextField tfNachName = new TextField();

	Label eMail = new Label("eMail- Adresse");
	TextField tfeMail = new TextField();

	Button abbrechen = new Button("abbrechen");
	Button registrieren = new Button("registrieren");
	
	public int BenutzerNummer = 0;
	Image SYM_Logo = new Image("unserlogo.png");
	ImageView SYM_view = new ImageView(SYM_Logo);
	
	
	
	
	
	
	
	
	
	
	public  Stage pruefung2(Stage pruefung2) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(bnpw.toString());

		reGestrierung.add(benutzerName, 0, 1);
		reGestrierung.add(passWort, 0, 2);

		reGestrierung.add(VorName, 0, 3);
		reGestrierung.add(NachName, 0, 4);
		reGestrierung.add(eMail, 0, 5);

		reGestrierung.add(tfBenutzername, 2, 1);

		reGestrierung.add(tfPasswort, 2, 2);
		reGestrierung.add(tfVorname, 2, 3);
		reGestrierung.add(tfNachName, 2, 4);
		reGestrierung.add(tfeMail, 2, 5);

		reGestrierung.add(abbrechen, 3, 8);
		reGestrierung.add(registrieren, 2, 8);
		reGestrierung.add(SYM_view, 2, 0);

		Insets rand = new Insets(10);
		reGestrierung.setPadding(rand);
		reGestrierung.setHgap(30);
		reGestrierung.setVgap(20);

		pruefung2.setScene(szene_2);
		pruefung2.setTitle("Registrierung");
//		pruefung2.show();
	
		
		if (Files.exists(Userdatei) && Files.size(Userdatei) != 0) {
	         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("User1.dat"));

			bnpw = (HashMap<String,String>) ois.readObject();
			ois.close();
		
		}
		
		
		
		System.out.println(bnpw.toString());
		
		EventHandler<MouseEvent> FensterSchliessen = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				pruefung2.close();

			}
		};

		abbrechen.addEventHandler(MouseEvent.MOUSE_CLICKED, FensterSchliessen);

		EventHandler<MouseEvent> Speichern = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){

				File file = new File("/Beispiel.txt"); // TODO File einfügen
				String Benutzername = tfBenutzername.getText();
				String Passwort = tfPasswort.getText();
				String Vorname = tfVorname.getText();
				String Nachname = tfNachName.getText();
				String eMail = tfeMail.getText();
				
				
				User U1 = new User(Benutzername, Vorname, Nachname, eMail, 1);

				
				bnpw.putIfAbsent(Benutzername, Passwort);
				String k = bnpw.get(Benutzername);
				System.out.println(k);
				
				
				System.out.println(bnpw.toString());
		
				
				try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("User1.dat"));
					oos.writeObject(bnpw);
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				
				pruefung2.close();
			}
		};

		
				
		
		
		registrieren.addEventHandler(MouseEvent.MOUSE_CLICKED, Speichern);
		
		
		
		return pruefung2;
		
				
	}
	String Benutzername = tfBenutzername.getText();
	String Passwort = tfPasswort.getText();
	String Vorname = tfVorname.getText();
	String Nachname = tfNachName.getText();
	String eMail_text = tfeMail.getText();
	
	public String getBenutzername() {
		return Benutzername;
	}
	public void setBenutzername(String benutzername) {
		Benutzername = benutzername;
	}
	public String getPasswort() {
		return Passwort;
	}
	public void setPasswort(String passwort) {
		Passwort = passwort;
	}
	public String getVorname() {
		return Vorname;
	}
	public void setVorname(String vorname) {
		Vorname = vorname;
	}
	public String getNachname() {
		return Nachname;
	}
	public void setNachname(String nachname) {
		Nachname = nachname;
	}
	public String geteMail_text() {
		return eMail_text;
	}
	public void seteMail_text(String eMail_text) {
		this.eMail_text = eMail_text;
	}
	
	
	
	
	
}