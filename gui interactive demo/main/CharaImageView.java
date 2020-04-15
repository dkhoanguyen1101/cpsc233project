package main;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/** small class to associate an imageView with a character via it's ID
 * 
 * @author Derrick C.
 *
 */
public class CharaImageView {

	private int id; //ID of character being used
	private ImageView image; //the imageView to put on the screen
	
	/**constructor
	 * 
	 * @param id  ID of the character
	 * @param ImageURL  The image to use for that character
	 */
	public CharaImageView(int id, String ImageURL) {
		this.id = id;
		image = new ImageView();
		setImage(ImageURL);
	}
	
	/** return the reference to the imageView of that character
	 * 
	 * @return  the char's imageView
	 */
	public ImageView getImage() {
		return image;
	}
	
	/** sets the image of the imageView
	 * 
	 * @param URL  URL of the image to set
	 */
	public void setImage(String URL) {
		image.setImage(new Image(URL));
	}
	
	/** ID getter
	 * 
	 * @return ID
	 */
	public int getID() {
		return id;
	}
}
