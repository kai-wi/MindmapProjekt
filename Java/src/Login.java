package prüfungsleistung;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
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

public class Login extends Application {
	
	GridPane anMeldung = new GridPane();
	  Scene szene_1 = new Scene(anMeldung, 600, 400);

	  Label benutzerName = new Label ("Benutzername");
	  Label passWort = new Label ("Passwort");
	  
	  TextField tf1 = new TextField ();
	  PasswordField tf2 = new PasswordField ();
	  
	  Button anmelden = new Button ("anmelden");
	  Button registrieren1 = new Button ("regestrieren");
	  Button abbrechen1 = new Button ("abbrechen");
	  
	  Regestrierung input;
	  
	  


	public void start (Stage pruefung) throws FileNotFoundException, IOException {
			// TODO Auto-generated method stub
		
	    anMeldung.add(benutzerName, 0, 0);
	    anMeldung.add(passWort, 0, 1);

	    anMeldung.add(tf1, 1, 0);
	    anMeldung.add(tf2, 1, 1);

	    anMeldung.add(anmelden, 0, 2);
	    anMeldung.add(registrieren1, 1, 2);
	    anMeldung.add(abbrechen1, 2, 2);
	    
	    Insets rand = new Insets(10);
	    anMeldung.setPadding(rand);
	    anMeldung.setHgap(30);
	    anMeldung.setVgap(20);
	    
	    
	    
      EventHandler<ActionEvent> FensterSchliessen = new EventHandler<ActionEvent>() {
			public void handle (ActionEvent event) {
			System.out.print("alles klar");
			System.exit(0); 
		
			
		}
 };
			
	abbrechen1.addEventHandler(ActionEvent.ACTION, FensterSchliessen); 
	

		
	
    pruefung.setScene(szene_1);
    pruefung.setTitle("Anmeldung");
    pruefung.show();
  }

	
	
	
  public static void main(String[] args) {
    launch(args);
  }

}