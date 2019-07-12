package ninja.cooperstuff.pokemon.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceLoader {
	public static BufferedImage load(String path) {
		try {
			return ImageIO.read(Thread.currentThread().getContextClassLoader().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
