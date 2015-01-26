package applicationframe;

import java.io.File;

public class Image {
    private Image previous;
    private Image next;
    private final File image;
    private final String name;
    
    public Image(File image) {
        this.image = image;
        this.name = image.getAbsolutePath();
    }
    
    public void setNext(Image next){
        this.next = next;
    }
    
    public void setPrevious(Image previous){
        this.previous = previous;
    }

    public String getName() {
        return name;
    }

    public Image getPrevious() {
        return previous;
    }

    public Image getNext() {
        return next;
    }
    
}
