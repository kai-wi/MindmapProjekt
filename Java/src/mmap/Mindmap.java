//kommentieren: alle klassen, methoden, 

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
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Mindmap extends Startseite {
	
	public  BorderPane fenster = new BorderPane();
	public Scene szene = new Scene(fenster, 800, 600);
	public static Pane mindmap_zeichnen = new Pane();
	public String knotenName;
	public static TextField knotenNameTF = new TextField(Projectcreate.Projektname.getText());
	Button knotenErstellen = new Button("Erstellen");
	Label ueberschrift = new Label("Willkommen zu Ihrer Mindmap!");
	Label sliderx_Label = new Label("links - rechts");
	Label slidery_Label = new Label("oben - unten");
	Slider sliderX = new Slider();
	Slider sliderY = new Slider();
	Button ArrayTest = new Button("Array Test");
	Button Zuruck = new Button("Startseite");
	
	
	Image SYM_Logo = new Image("unserlogo.png");
	ImageView SYM_view = new ImageView(SYM_Logo);
	
	VBox controls = new VBox(15);
	public int knotenNummer;
    Path MindMapDatei = Paths.get(knotenNameTF.getText() + "knoten.dat");
	
	/*
	 * ArrayList für das erstellen der Mindmap-Ellipsen
	 */
	
	public static ArrayList<New_Knoten> knotenListe = new ArrayList<>();


    // Die Liste aller Kunden und deren Eigenschaften werden in die Datei geschrieben
    
	
	public  Stage mmStage(Stage mmStage) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		sliderX.setMin(0);
		sliderX.setMax(100);
		sliderX.setShowTickLabels(true);
		sliderX.setMajorTickUnit(20);

		sliderY.setMin(0);
		sliderY.setMax(100);
		sliderY.setShowTickLabels(true);
		sliderY.setMajorTickUnit(20);

		fenster.setLeft(controls);
		fenster.setCenter(mindmap_zeichnen);
		fenster.setTop(ueberschrift);
		mindmap_zeichnen.setPrefHeight(szene.getHeight());
		mindmap_zeichnen.setPrefWidth(szene.getWidth() * 3 / 4);
		double maxY = mindmap_zeichnen.getPrefHeight();
		double maxX = mindmap_zeichnen.getPrefWidth();
		mindmap_zeichnen.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		controls.getChildren().addAll(SYM_view, knotenNameTF, new Separator(), knotenErstellen, sliderx_Label, sliderX, slidery_Label,
			sliderY, ArrayTest,Zuruck);
	    String DateiPfad = MindMapDatei.toString();
	    
	    
//		Canvas canvas = new Canvas(szene.getWidth() * 3 / 4, szene.getHeight());
//	    drawOnCanvas(canvas);
	    
		if (Files.exists(MindMapDatei)) {
	         ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DateiPfad));

			knotenListe = (ArrayList<New_Knoten>) ois.readObject();
			ois.close();
			for (New_Knoten nk: knotenListe) {
				New_Knoten kn1 = new New_Knoten (nk.getX(),nk.getY(),nk.getName(),nk.getKnotenNummer()); 
				}
	        ObservableList<Node> workingCollection = FXCollections.observableArrayList(mindmap_zeichnen.getChildren());
	        Collections.swap(workingCollection, workingCollection.size()-2, workingCollection.size()-1);
	        mindmap_zeichnen.getChildren().setAll(workingCollection);
		} else {
			/*
			 * Erster Knoten wird in der Mitte mit Namen des Projekts erstellt
			 */
			
			New_Knoten erster = new New_Knoten(mindmap_zeichnen.getPrefHeight()/2, mindmap_zeichnen.getPrefWidth()/2, knotenNameTF.getText(), 1);
			
			knotenListe.add(erster);
			
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DateiPfad));
				oos.writeObject(knotenListe);
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			sliderX.setValue(50);
			sliderY.setValue(50);
			System.out.println(knotenNameTF.getText());
		}

		EventHandler<ActionEvent> mindmaperstellen = new EventHandler<ActionEvent>() {
			/*
			 * Eventhandler für das Erstellen des Knotens
			 */

			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				double posX = sliderX.getValue()/100*maxX;
				double posY = sliderY.getValue()/100*maxY;
				
				knotenNummer++;
				New_Knoten knoten = new New_Knoten(posX, posY, knotenNameTF.getText(),
						knotenNummer);
//				System.out.println("Knotennummer "+knotenNummer);
//				System.out.println("posX: " + posX);
				
//				System.out.println("X von erster: " + erster.getX());
				
				knotenListe.add(knoten);
				
		        ObservableList<Node> workingCollection = FXCollections.observableArrayList(mindmap_zeichnen.getChildren());
		        Collections.swap(workingCollection, workingCollection.size()-2, workingCollection.size()-1);
		        mindmap_zeichnen.getChildren().setAll(workingCollection);
	
				
				try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DateiPfad));
					oos.writeObject(knotenListe);
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

				
				
				
				System.out.println(mindmap_zeichnen.getChildren().toString());
		}
		};
			
		EventHandler<ActionEvent> ArrayTest = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				for (New_Knoten nk: knotenListe) {
					System.out.println(nk.getName());
					System.out.println(knotenListe.size());
					
					}
				
				
			}
			
			
			
		};
		EventHandler<ActionEvent> Startzuruck = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event5) {
//				Functions_Startseite F2 = new Functions_Startseite();
//				Stage s1 = new Stage();
//				F2.stage1(s1);
//				s1.show();
				mindmap_zeichnen.getChildren().clear();
				mmStage.close();
				
			}
			
			
			
		};
		Zuruck.addEventHandler(ActionEvent.ACTION, Startzuruck);
		knotenErstellen.addEventHandler(ActionEvent.ACTION, mindmaperstellen);
		this.ArrayTest.addEventHandler(ActionEvent.ACTION, ArrayTest);
		
		mmStage.setScene(szene);
		mmStage.setTitle("SYM");
		//mmStage.show();
		
		return mmStage;

	}

}
