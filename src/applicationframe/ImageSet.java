package applicationframe;

import java.io.File;
import java.util.ArrayList;


public class ImageSet {
    private final String[] extensions = new String[]{"jpg"};
    private final Image[] imageFiles;
    private final File directory;
    private final ArrayList<File> files;
    
    public ImageSet(String path) {
        directory = new File(path);
        files = listAllFilesInFolder(directory.getPath());
        imageFiles = createCircularList(files);
    }
    
    private boolean accept(String name){
        for (String names : extensions) {
            if (name.endsWith("."+names)){
                return true;
            }
        }
        return false;
    }

    private ArrayList<File> listAllFilesInFolder(String path) {
        ArrayList<File> imageFilesInsideFolder = new ArrayList<>();
        File folder = new File(path);
        File[] files;
        try{
            files = folder.listFiles();
            for (File file : files) {
                if(accept(file.getName()))
                    imageFilesInsideFolder.add(file);
            }
        }catch(Exception e){
            files = new File[]{new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg"),
                               new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Lighthouse.jpg"),
                               new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulipans.jpg")};
            for (File file : files) {
                imageFilesInsideFolder.add(file);
            }
        }
        return imageFilesInsideFolder;
    }

    private Image[] createCircularList(ArrayList<File> files) {
        Image[] images = new Image[files.size()];
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image(files.get(i));
        }
        return fixLinks(images);
    }

    private Image[] fixLinks(Image[] images) {
        if(images.length<1){
            fixLinksNoImage(images);
        }else if(images.length==1){
            fixLinksOneImage(images);
        }else if(images.length==2){
            fixLinksTwoImages(images);
        }else{
            images[0].setPrevious(images[images.length-1]);
            images[0].setNext(images[1]);
            for (int i = 1; i < images.length-2; i++) {
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
        images[0].setNext(images[0]);
    }

    private void fixLinksTwoImages(Image[] images) {
        images[0].setNext(images[1]);
        images[0].setPrevious(images[1]);
        images[1].setNext(images[0]);
        images[1].setPrevious(images[0]);
    }
    
    public Image[] getImageFiles() {
        return imageFiles;
    }
}
