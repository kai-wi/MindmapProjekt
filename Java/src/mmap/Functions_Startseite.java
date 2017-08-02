package mmap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Functions_Startseite extends Startseite {
	public BorderPane bord = new BorderPane();
	
	Scene szene = new Scene(bord);
	TextArea notes = new TextArea("Notizen");
	Button logout = new Button("Logout");
	public Button newproject = new Button("Neues Projekt erstellen");
	String benutzername = "";
	Label username = new Label("Hallo, " + benutzername + "!");
	String s;
	Button PN1 = new Button(s);
	VBox box = new VBox(10);
	Image SYM_Logo = new Image("unserlogo.png");
	ImageView SYM_view = new ImageView(SYM_Logo);
	HBox LogoName = new HBox(10);
	ComboBox<String> ProjektListe = new ComboBox<>();
	HashMap<String,String> Projekte = new HashMap<>();
	Path ProjekteDatei = Paths.get("Projekte.dat");
	Button loadProject = new Button("Projekt laden:");
	
	ArrayList<String> UserProjekte = new ArrayList<>();
	Path UPDatei = Paths.get(benutzername + ".dat");
	String UPDString = UPDatei.toString();
	
	
	// from register a function to save the
	// inserted name
	// set on drag and drop
	//MenuBar menuBar = new MenuBar();
	//Functions_Startseite(){};
	public Stage stage1(Stage stage1) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		einlesen();
		box.getChildren().addAll(newproject, loadProject, ProjektListe);
		
//		Projekte.forEach((k,v)->ProjektListe.getItems().add(k));
		
//		for (Entry<String, String> entry : Projekte.entrySet()) {
//		    String key = entry.getKey();
//		    String value = entry.getValue();
//		    
//		    if (value.equals(benutzername)) {
//		    	ProjektListe.getItems().add(key);
//		    // ...
//		}			
//			
//		}
		
		
		
		
		bord.setCenter(notes);
		bord.setPadding(new Insets(10, 20, 10, 20));
		username.setMinSize(300, 0);
		LogoName.getChildren().add(SYM_view);
		LogoName.getChildren().add(username);
		
		//BorderPane.setRowSpan(bord, 1);
		//GridPane.setColumnSpan(bord, 1);
		bord.setRight(logout);
		bord.setLeft(box);
		bord.setTop(LogoName);
		username.setPrefSize(100, 100);
		username.accessibleTextProperty();
		username.setFont(new Font("Arial", 30));

		
		szene.getStylesheets().add("Stylesheet1.css");
		
		
		
		stage1.show();
		stage1.setTitle("Startseite");
		stage1.setScene(szene);
		//stage1.show();

		EventHandler<MouseEvent> opennew = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event1) {
				Projectcreate P2 = new Projectcreate();
				P2.Benutzername = benutzername;
				Stage s1 = new Stage();
				
				try {
					P2.newsite(s1);
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
			}
		};
		Projectcreate P=new Projectcreate();
		
		EventHandler<MouseEvent> load = new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event1) {
				Mindmap M2 = new Mindmap();
				M2.MindMapDatei = Paths.get(ProjektListe.getSelectionModel().getSelectedItem().toString() + "knoten.dat");
				Stage s1 = new Stage();
				
				try {
					M2.mmStage(s1);
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
		
		
		EventHandler<MouseEvent> closeeverything = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event2) {
				stage1.close();
			Login L2 = new Login();
			Stage s1 = new Stage();
			try {
				L2.pruefung(s1);
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
		
		
		
		
		newproject.addEventHandler(MouseEvent.MOUSE_CLICKED, opennew);
		logout.addEventHandler(MouseEvent.MOUSE_CLICKED, closeeverything);
		loadProject.addEventFilter(MouseEvent.MOUSE_CLICKED, load);
		return stage1;

	}
	
	void TextAreaSpeichern() {
		
		
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Textarea.dat"));
			oos.writeObject(notes);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	void einlesen() throws FileNotFoundException, ClassNotFoundException, IOException {
		
		
		if (Files.exists(UPDatei) && Files.size(UPDatei) != 0) {
	         ObjectInputStream ois = new ObjectInputStream(new FileInputStream(UPDString));

			UserProjekte = (ArrayList<String>) ois.readObject();
			ois.close();
		
		} 
	}
}

