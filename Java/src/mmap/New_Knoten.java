package mmap;



import java.awt.MouseInfo;
import java.awt.Point;
//KOmmentar zum testen2345
import java.io.Serializable;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class New_Knoten implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Funktion, die einen neuen Knoten an der Stelle x,y, mit dem Radius radius
	 * und dem Inhalt name erstellt. Skaliert mit der länge des Textes
	 * 
	 * @param x
	 *            Die x Koordinate des Knotens
	 * @param y
	 *            Die y Kordinate des Knotens
	 * @param radius
	 *            Der Radius der Ellipse
	 * @param name
	 *            Der Name des Knotens
	 */
	double x;
	double y;
	String name;
	int knotenNummer;

	public New_Knoten(double x, double y, String name, int knotenNummer) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		this.knotenNummer = knotenNummer;

		String IDNr = String.valueOf(knotenNummer);

		String ID = "knoten" + IDNr;

		/*
		 * Erstellt den Text an der Stelle x,y den Text und zentriert ihn mit
		 * Hilfe der Translate Funktion
		 * 
		 */

		Text Knotenname = new Text (name);
		{
			Knotenname.setX(x);
			Knotenname.setY(y);

//			Knotenname.setText(name);
//			Knotenname.setLayoutX(x);
//			Knotenname.setLayoutY(y);
//			Knotenname.setBorder(null);
//			Knotenname.autosize();
			Knotenname.setTranslateX(Knotenname.getLayoutBounds().getWidth() / 2 * -1);
			Knotenname.setTranslateY(Knotenname.getLayoutBounds().getHeight() * 0.25);
		}
		/*
		 * Erstellt die Ellipse an x,y mit dem Breitenradius des Textes mit der
		 * Länge des Textes und dem Höhenradius mit der Hälfte
		 */

		Ellipse k1 = new Ellipse();
		{
			k1.setCenterX(x);
			k1.setCenterY(y);
			k1.setRadiusX(Knotenname.getLayoutBounds().getWidth() * 0.75);
			k1.setRadiusY(Knotenname.getLayoutBounds().getHeight());
			k1.setFill(Color.WHITE);
			k1.setStroke(Color.BLACK);
			k1.setStrokeWidth(1.5);
		}

		Line line = new Line();
		{
			line.setStartX(mmap.Mindmap.mindmap_zeichnen.getPrefWidth() / 2);
			line.setStartY(mmap.Mindmap.mindmap_zeichnen.getPrefHeight() / 2);
			line.setEndX(x);
			line.setEndY(y);
			line.getStrokeMiterLimit();
			line.setStrokeMiterLimit(k1.getStrokeWidth());
		}
		

		
		
	
		Group knotenGruppe = new Group();

		knotenGruppe.getChildren().addAll(line, k1, Knotenname);

		/*
		 * Ellipse und Text wird zur Pane mindmap_zeichnen hinzugefügt
		 */

		mmap.Mindmap.mindmap_zeichnen.getChildren().addAll(knotenGruppe);


		

	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKnotenNummer() {
		return knotenNummer;
	}

	public void setKnotenNummer(int knotenNummer) {
		this.knotenNummer = knotenNummer;
	}

}
