package mmap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
//import javafx.stage.Modality;
import javafx.stage.Stage;


public class Projectcreate extends Startseite implements Serializable {
	Stage newone = new Stage();
	GridPane newgrid = new GridPane();
	Scene szene = new Scene(newgrid, 800, 600);
	static TextField Projektname = new TextField("Projektname");
	TextField Zugangscode = new TextField("Zugangscode");
	public Button create = new Button("Projekt erstellen");
	public Button cancel = new Button("Abbrechen");
	Label PN = new Label("Projektname");
	Label ZC = new Label("Zugangscode");
	String NPN = Projektname.getText();
	String NZC = Zugangscode.getText();
	HashMap<String,String> Projekte = new HashMap<>();
	Path ProjekteDatei = Paths.get("Projekte.dat");
	String Benutzername;
	



	public Stage newsite(Stage newsite) throws FileNotFoundException, IOException, ClassNotFoundException {
		// newone.initModality(Modality.APPLICATION_MODAL);
		newone.setResizable(true);
		Insets rand = new Insets(15);
		newgrid.setPadding(rand);
		newgrid.setHgap(15);
		newgrid.setVgap(15);
		newgrid.add(create, 4, 2);
		newgrid.add(cancel, 4, 3);
		newgrid.add(Projektname, 3, 2);
		newgrid.add(Zugangscode, 3, 3);
		newgrid.add(ZC, 2, 3);
		newgrid.add(PN, 2, 2);
		// VBox layout=new VBox(10);
		
		newone.setScene(szene);
		newone.setTitle("Neues Projekt erstellen");
		newone.setScene(szene);
		newone.show();
		System.out.println("show");
		szene.getStylesheets().add("Stylesheet1.css");
		
System.out.println(Benutzername);

		EventHandler<MouseEvent> closenew = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event2) {

				closesite();
			}
		};

		EventHandler<MouseEvent> saveproject = new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event3) {
				try {
					projectsave();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
						
			}

		};
		
		EventHandler<ActionEvent> openMindMap = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event3) {
				Mindmap M2 = new Mindmap();
				Stage s1 = new Stage();
				
				try {
					M2.mmStage(s1);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Mindmap.knotenNameTF.setText(Projektname.getText());
				s1.show();
			}
		};
		
		
		
		cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, closenew);
		create.addEventHandler(MouseEvent.MOUSE_CLICKED, closenew);
		create.addEventHandler(MouseEvent.MOUSE_CLICKED, saveproject);
		create.addEventHandler(ActionEvent.ACTION, openMindMap);

		return newsite;
	}

	public void closesite() {
		newone.close();
		System.out.println("closed");
	}

	public void projectsave() throws FileNotFoundException, IOException, ClassNotFoundException  {
		String NPN = Projektname.getText();
		String NZC = Zugangscode.getText();
	
		
		if (Files.exists(ProjekteDatei) && Files.size(ProjekteDatei) != 0) {
	         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Projekte.dat"));

			Projekte = (HashMap<String,String>) ois.readObject();
			ois.close();
		
		} 
				
		Projekte.put(Benutzername, NPN);
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Projekte.dat"));
			oos.writeObject(Projekte);
			oos.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		for (int i = 0; i < Projekte.size(); i++) {
		 	System.out.println(Projekte.toString());
			
		}
		
		
		
	}
}

