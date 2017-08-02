package mmap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.stage.Stage;

	
	
public class Startseite extends Application implements Serializable {
	Path Userdatei = Paths.get("User1.dat");
	Path UserListDatei = Paths.get("UserList.dat");
	HashMap<String, String> bnpw = new HashMap<>();
	ArrayList<User> UserList = new ArrayList<>();
	
	
	
	public void start(Stage Startseite) throws IOException, ClassNotFoundException {
		
		if (Files.exists(Userdatei) && Files.size(Userdatei) != 0) {
	         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("User1.dat"));

			bnpw = (HashMap<String,String>) ois.readObject();
			ois.close();
		
		}
		
		
		System.out.println(bnpw.toString());
		
		if (Files.exists(UserListDatei) && Files.size(UserListDatei) != 0) {
	         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("UserList.dat"));

			UserList = (ArrayList<User>) ois.readObject();
			ois.close();
		
		}

		
		Login L1 = new Login();
		Stage s3 = new Stage();
		L1.pruefung(s3);
		
		

		
		

	}
	

	public static void main(String[] args) {
		launch(args);

	}

}
