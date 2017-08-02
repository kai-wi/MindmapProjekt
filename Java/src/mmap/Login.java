package mmap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login extends Startseite {

	

	public  GridPane anMeldung = new GridPane();
	public Scene szene_1 = new Scene(anMeldung, 800, 600);

	Label benutzerName = new Label("Benutzername");
	Label passWort = new Label("Passwort");

	TextField tf1 = new TextField();
	PasswordField tf2 = new PasswordField();

	Button anmelden = new Button("anmelden");
	Button registrieren1 = new Button("registrieren");
	Button abbrechen1 = new Button("abbrechen");
	Alert NameFalsch = new Alert(AlertType.INFORMATION);
	Alert PasswortFalsch = new Alert(AlertType.WARNING);
	
	Image SYM_Logo = new Image("unserlogo.png");
	ImageView SYM_view = new ImageView(SYM_Logo);

	public Stage pruefung(Stage pruefung) throws FileNotFoundException, ClassNotFoundException, IOException {

		anMeldung.add(benutzerName, 0, 1);
		anMeldung.add(passWort, 0, 2);

		anMeldung.add(tf1, 1, 1);
		anMeldung.add(tf2, 1, 2);

		anMeldung.add(anmelden, 0, 3);
		anMeldung.add(registrieren1, 1, 3);
		anMeldung.add(abbrechen1, 2, 3);
		anMeldung.add(SYM_view, 1,0);

		Insets rand = new Insets(10);
		anMeldung.setPadding(rand);
		anMeldung.setHgap(30);
		anMeldung.setVgap(20);
		NameFalsch.setTitle("Falscher Benutzername");
		NameFalsch.setHeaderText("Bitte geben Sie Ihren Benutzernamen ein.");
		PasswortFalsch.setTitle("Falsches Passwort");
		PasswortFalsch.setHeaderText("Bitte geben Sie Ihr korrektes Passwort ein.");
		
		

		
		szene_1.getStylesheets().add("Stylesheet1.css");
		
		pruefung.setScene(szene_1);
		pruefung.setTitle("Anmeldung");
		pruefung.show();
		
		

		EventHandler<ActionEvent> Startseiteoeffnen = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event1) {
				try {
					einlesen();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String NameEingegeben = tf1.getText();
				String PasswortEingegeben = tf2.getText();
				Boolean PasswortRichtig = false;
				Boolean NameExistiert = false;
				System.out.println(
						"Passwort eingegeben: " + PasswortEingegeben + " Passwort: " + bnpw.get(NameEingegeben));

				if (bnpw.containsKey(NameEingegeben)) {
					NameExistiert = true;
					System.out.println("Name existiert");
				} else {
					System.out.println("Benutzer existiert nicht!");
					NameFalsch.showAndWait();
				}

				if (PasswortEingegeben.equals(bnpw.get(NameEingegeben))) {
					PasswortRichtig = true;
					System.out.println("Passwort richtig!");
				} 
				
				if (NameExistiert && PasswortRichtig == false) {
					PasswortFalsch.showAndWait();
				}

				if (NameExistiert && PasswortRichtig) {
					
					Functions_Startseite F2 = new Functions_Startseite();
					F2.benutzername = NameEingegeben;
					F2.username.setText("Hallo, " + NameEingegeben + "!");
					Stage s1 = new Stage();
					try {
						F2.stage1(s1);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					s1.show();
					pruefung.close();
				}

			}
		};
		EventHandler<ActionEvent> Registrierungoeffnen = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event2) {
				Registrierung2 R2 = new Registrierung2();
				Stage s1 = new Stage();
				try {
					R2.pruefung2(s1);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s1.show();

			}
		};

		EventHandler<ActionEvent> FensterSchliessen = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.print("alles klar");
				System.exit(0);

			}

		};

		abbrechen1.addEventHandler(ActionEvent.ACTION, FensterSchliessen);
		anmelden.addEventHandler(ActionEvent.ACTION, Startseiteoeffnen);
		registrieren1.addEventHandler(ActionEvent.ACTION, Registrierungoeffnen);
		return pruefung;

	}

	void einlesen() throws FileNotFoundException, ClassNotFoundException, IOException {
		if (Files.exists(Userdatei) && Files.size(Userdatei) != 0) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("User1.dat"));

			bnpw = (HashMap<String, String>) ois.readObject();
			ois.close();

		}

	}

}