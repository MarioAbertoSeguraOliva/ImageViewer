package applicationframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ApplicationFrame extends JFrame  {
    private Image image;
    private int indexImage = 0;
    private ImageSet imageSet;
    public ApplicationFrame() {
        addWidgets();
        setSize(500,500);
        setMinimumSize(new Dimension(250, 250));
        setTitle("Image viewer");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addWidgets() {
        add(createImagePanel());
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createImagePanel() {
        imageSet = new ImageSet(new PathDialog().getPath());
        image = readImage(imageSet.getImageFiles()[indexImage].getName());
        return new JPanel() {
            {
                getContentPane().addComponentListener(createComponentListener());
            }
            
            @Override
            public void paint(Graphics g) {
                super.paint(g); 
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }

            private ComponentListener createComponentListener() {
                return new ComponentListener() {

                    @Override
                    public void componentResized(ComponentEvent e) {
                        revalidate();
                    }

                    @Override
                    public void componentMoved(ComponentEvent e) {
                    }

                    @Override
                    public void componentShown(ComponentEvent e) {
                    }

                    @Override
                    public void componentHidden(ComponentEvent e) {
                    }
                };
            }     
        };
    }

    private Image readImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException ex) {
            return null;
        }
    }

    private JButton createLeftButton() {
        JButton left = new JButton("Left image");
        left.addActionListener((ActionEvent e) -> {
            applicationframe.Image imageToDisplay = imageSet.getImageFiles()[indexImage].getPrevious();
            indexImage = locateImage(imageSet, imageToDisplay);
            image = readImage(imageSet.getImageFiles()[0].getPrevious().getName());
        });
        return left;
    }

    private JButton createRightButton() {
        JButton right = new JButton("Right image");
        right.addActionListener((ActionEvent e) -> {
            applicationframe.Image imageToDisplay = imageSet.getImageFiles()[indexImage].getNext();
            indexImage = locateImage(imageSet, imageToDisplay);
            image = readImage(imageSet.getImageFiles()[0].getNext().getName());
        });
        return right;
    }  

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(createLeftButton(), BorderLayout.WEST);
        panel.add(createRightButton(), BorderLayout.EAST);
        return panel;
    }

    private int locateImage(ImageSet imageSet, applicationframe.Image imageToDisplay) {
        for (int i = 0; i < imageSet.getImageFiles().length; i++) {
            if (imageSet.getImageFiles()[i] == imageToDisplay)
                return i;
        }
        return 0;
    }
}