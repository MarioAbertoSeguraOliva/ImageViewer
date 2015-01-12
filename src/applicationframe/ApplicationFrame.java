package applicationframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public ApplicationFrame() {
        image = readImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        addWidgets();
        setVisible(true);
        setSize(500,500);
        setMinimumSize(new Dimension(250, 250));
        setTitle("Image viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addWidgets() {
        add(createImagePanel());
        add(createLeftButton(), BorderLayout.WEST);
        add(createRightButton(), BorderLayout.EAST);
    }

    private JPanel createImagePanel() {
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
        left.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                image = readImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\Lighthouse.jpg");
            }
        });
        return left;
    }

    private JButton createRightButton() {
        JButton right = new JButton("Right image");
        right.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                image = readImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg");
            }
        });
        return right;
    }  
}