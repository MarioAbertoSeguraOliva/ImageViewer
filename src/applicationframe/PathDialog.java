package applicationframe;

import javax.swing.JOptionPane;


public class PathDialog {
    private final String path;
    public PathDialog() {
       
        path = (String)JOptionPane.showInputDialog("Introduce una ruta: ");
        System.out.println(path);
    }
    
    public String getPath(){
        return path;
    }
    
    
}
