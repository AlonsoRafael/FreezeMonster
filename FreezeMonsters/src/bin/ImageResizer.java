package bin;

import java.awt.Image;
import java.util.Objects;
import javax.swing.ImageIcon;

public class ImageResizer {

    public static ImageIcon resizeImage(String imagePath, int newWidth, int newHeight) {
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(ImageResizer.class.getResource(imagePath)));
        Image originalImage = originalIcon.getImage();

        // Redimensionar a imagem
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }
}
