package TFCBestFormula;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class JarFileInput {
    public static Image loadJarImg(String fileSrc){
        Image image = null;
        InputStream is = JarFileInput.class.getResourceAsStream(fileSrc);
        try {
            assert is != null;
            image = ImageIO.read(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public static BufferedImage loadJarBufferedImg(String fileSrc){
        BufferedImage bufferedImage = null;
        InputStream is = JarFileInput.class.getResourceAsStream(fileSrc);
        try {
            assert is != null;
            bufferedImage = ImageIO.read(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
