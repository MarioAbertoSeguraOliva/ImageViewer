package applicationframe;

import java.awt.Image;
import java.io.File;
import java.util.LinkedList;


public class ImageSet {
    private LinkedList<Image> imageSet;
    private String[] extensions = new String[]{"jpg"};
    
    private boolean accept(File file, String name){
        for (String names : extensions) {
            if (name.endsWith("."+names)){
                return true;
            }
        }
        return false;
    }
}
