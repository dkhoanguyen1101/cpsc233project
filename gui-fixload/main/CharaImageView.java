package main;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class CharaImageView {

	private int id;
	private ImageView image;
	
	public CharaImageView(int id, String ImageURL) {
		this.id = id;
		image = new ImageView();
		setImage(ImageURL);
	}
	
	public ImageView getImage() {
		return image;
	}
	
	public void setImage(String URL) {
		image.setImage(new Image(URL));
	}
	
	public int getID() {
		return id;
	}
}
