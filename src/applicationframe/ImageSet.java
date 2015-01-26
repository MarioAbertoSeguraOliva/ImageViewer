package applicationframe;

import java.io.File;

public class ImageSet {
    private static final String[] extensions = { "jpg", "png" };    
    private final Image[] images;
    
    public ImageSet(String path) {
        this.images = link(createImages(filesIn(new File(path))));
    }
    
    private File[] filesIn(File folder) {
        File[] files = folder.listFiles((File dir, String name) -> {
            for (String extension : extensions)
                if (name.endsWith("." + extension)) return true;
            return false;
        });
        return files;
    }

    private Image[] createImages(File[] files) {
        Image[] images = new Image[files.length];
        for (int i = 0; i < images.length; i++) 
            images[i] = new Image(files[i]);
        return images;
    }

    private Image[] link(Image[] images) {
        if(images.length<1){
            fixLinksNoImage(images);
        }else if(images.length==1){
            fixLinksOneImage(images);
        }else if(images.length==2){
            fixLinksTwoImages(images);
        }else{
            images[0].setPrevious(images[images.length-1]);
            images[0].setNext(images[1]);
            for (int i = 1; i < images.length-1; i++) {
                images[i].setPrevious(images[i-1]);
                images[i].setNext(images[i+1]);
            }
            images[images.length-1].setPrevious(images[images.length-2]);
            images[images.length-1].setNext(images[0]);
        }
        return images;
    }

    private void fixLinksNoImage(Image[] images) {
        
    }

    private void fixLinksOneImage(Image[] images) {
        images[0].setNext(images[0]);
        images[0].setPrevious(images[0]);
    }

    private void fixLinksTwoImages(Image[] images) {
        images[0].setNext(images[1]);
        images[0].setPrevious(images[1]);
        images[1].setNext(images[0]);
        images[1].setPrevious(images[0]);
    }
    
    public Image[] getImageFiles() {
        return images;
    }
}
