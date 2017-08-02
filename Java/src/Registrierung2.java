package prüfungsleistung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Regestrierung extends Login {

	GridPane reGestrierung = new GridPane();
	Scene szene_2 = new Scene(reGestrierung, 600, 400);

	Label benutzerName = new Label("Benutzername");

	Label passWort = new Label("Passwort");

	TextField tf7 = new TextField();

	PasswordField tf9 = new PasswordField();

	Label VorName = new Label("Vorname");
	TextField tf10 = new TextField();

	Label NachName = new Label("Nachname");
	TextField tf11 = new TextField();

	Label eMail = new Label("eMail- Adresse");
	TextField tf12 = new TextField();

	Button abbrechen = new Button("abbrechen");
	Button registrieren = new Button("regestrieren");

	@Override
	public void start(Stage pruefung2)  {
		// TODO Auto-generated method stub

		reGestrierung.add(benutzerName, 0, 0);
		reGestrierung.add(passWort, 0, 1);

		reGestrierung.add(VorName, 0, 2);
		reGestrierung.add(NachName, 0, 3);
		reGestrierung.add(eMail, 0, 4);

		reGestrierung.add(tf7, 2, 0);

		reGestrierung.add(tf9, 2, 1);
		reGestrierung.add(tf10, 2, 2);
		reGestrierung.add(tf11, 2, 3);
		reGestrierung.add(tf12, 2, 4);

		reGestrierung.add(abbrechen, 3, 7);
		reGestrierung.add(registrieren, 2, 7);

		Insets rand = new Insets(10);
		reGestrierung.setPadding(rand);
		reGestrierung.setHgap(30);
		reGestrierung.setVgap(20);

		pruefung2.setScene(szene_2);
		pruefung2.setTitle("Registrierung");
		pruefung2.show();
	

		EventHandler<MouseEvent> FensterSchliessen = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				System.exit(0);

			}
		};

		abbrechen.addEventHandler(MouseEvent.MOUSE_CLICKED, FensterSchliessen);

		EventHandler<MouseEvent> Speichern = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				File file = new File("C:/Users\\Elisabeth\\Java\\Beispiel.txt");
				String Benutzername = tf7.getText();
				String Passwort = tf9.getText();
				String Vorname = tf10.getText();
				String Nachname = tf11.getText();
				String eMail = tf12.getText();
				try {
					PrintWriter output = new PrintWriter(file);

					output.println(Benutzername);
					output.println(Passwort);
					output.println(Vorname);
					output.println(Nachname);
					output.println(eMail);

					ArrayList<Kunden> Kunde = new ArrayList<Kunden>();
					Kunde.add(new Kunden(Benutzername, Passwort, Vorname, Nachname, eMail));

					FileOutputStream out = new FileOutputStream(file);
					ObjectOutputStream output1 = new ObjectOutputStream(out);
					for (Kunden k : Kunde) {
						output1.writeObject(k);

					}

					output1.close();
					out.close();

					FileInputStream in = new FileInputStream(file);
					ObjectInputStream input = new ObjectInputStream(in);
					ArrayList<Kunden> Kunde1 = new ArrayList<Kunden>();

					while (true) {
						try {
						Kunden k = (Kunden)input.readObject();
						Kunde1.add(k);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}

				} catch (IOException ex) {
					System.out.println("Error");
				}

			}
		};

		registrieren.addEventHandler(MouseEvent.MOUSE_CLICKED, Speichern);
		
		
		
		
	}

	
	

}