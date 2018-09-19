package Interfaz;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import proyectoimagen.AlgoritmoGenetico;

/**
 *
 * @author TEC 2018 LIVE
 */
public class ImagenGrafica extends JFrame{
      
      InputStream imgStream;
      Image imgArray[]=new Image[10];
      ImageIcon imgArray2[]=new ImageIcon[10];
      int tamanno;
      
      public ImagenGrafica(AlgoritmoGenetico a, int tam){
          
            super("Imagenes");
            
            this.setSize(300, 300);
            this.setVisible(true);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
            imgArray2=a.cargaImagen();
            tamanno=tam;
            System.out.println("poblacion.."+tam);
            repaint();

        
      }
      
      public void paint(Graphics g)
      {
            Graphics g2 = (Graphics2D)g;
            //g2.setColor(Color.black);
            g2.fillRect(0, 0, 250, 400);//creo un triangulo negro del tamaño de la ventana para fondo
            
            for(int cont=0;cont<tamanno;cont++){
                try
                {
                    Thread.sleep(100);
                    g2.drawImage(imgArray2[cont].getImage(), 100, 50, 70, 70, null);
                    JOptionPane.showMessageDialog(null, "Genetica Evolutiva", "Muestra la imagen", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                
            }
            
      }
     

}