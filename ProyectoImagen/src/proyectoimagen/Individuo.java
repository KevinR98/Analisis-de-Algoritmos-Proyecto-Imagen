
package proyectoimagen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Individuo {
    
    int informacionRGB [];
    
    int adaptabilidad;
    int tamanno;
    int tammanoX;
    int tammanoY;
    
    /*
    A considerar: 
    *   Separar los algoritmos de crear inicialmente y crearDescendencia puede no importar en teoria.
    *   Buscar forma de defenir tamanno solo una vez.
    */
    Individuo(int tamannoX, int tamannoY){
        this.tammanoX = tamannoX;
        this.tammanoY = tamannoY;
        this.tamanno = tamannoX * tamannoY;
        informacionRGB = new int[this.tamanno];
    }
    
    
    //      Creacion de objeto
    
    /*
    Nota:   La imagen meta se ocupa cada vez que se quiera crear.
            Buscar alternativa.
    */
    /*
    Algoritmo que crea un individuo con informacion random.
    */
    public void formarIndividuoInicial(int imagenMeta []){
        

        
        for(int indice = 0; indice < tamanno ; ++indice){
            this.informacionRGB[indice] = calcularBitRandom();
        }
        
        calcularAdaptabilidad(imagenMeta);
    }
    
    /*
    Algoritmo de mutacion y decendencia.
    */
    public void crearDescendencia(Individuo primerOrigen, Individuo segundoOrigen, int imagenMeta [], int porcentajeMutacion){
        calcularAdaptabilidad(imagenMeta);
        
        int primerMitad [] = primerOrigen.obtenerMitad(0);
        int segundaMitad [] = segundoOrigen.obtenerMitad(0);
        
        //Merge
        int mitad = this.tamanno/2;
        
        for(int indice = 0 ; indice<mitad ; ++indice){
            this.informacionRGB[indice] = primerMitad[indice];
            this.informacionRGB[indice+mitad] = segundaMitad[indice];
        }
        
        
        //Mutacion
        /*
        *   Se toma el procentaje que se desea cambiar aleatoriamente.
        *   Se modifican los valores aleatoriamente de ese porcentaje.
        */
        Random numeroRandom = new Random();

        double ciclosPorcentaje = (porcentajeMutacion)/100 * this.tamanno;
        for(int informacionActual = 0; informacionActual < (int)ciclosPorcentaje ; ++informacionActual){
            
            this.informacionRGB[numeroRandom.nextInt(this.tamanno)] = calcularBitRandom();
        }
        
    }
    
    
    
    
    //      Calculo de variables.
    
    /* 
    A considerar: se puede hacer le parametro de imagen meta un atributo del objeto por el constructor.
    Esto podria mejorar tiempos.
    */
    private void calcularAdaptabilidad(int imagenMeta []){
        Random numero = new Random();
        this.adaptabilidad = numero.nextInt(100);
    }
    
    
    // Metodo Get de adaptabilidad.
    public int obtenerAdaptabilidad(){
        return this.adaptabilidad;
    }
    
    /*
    Calcula un bit rgb random, con la lumonicidad al maximo.
    */
    private int calcularBitRandom(){
        Random numeroRandom = new Random();

        
        int z = 255<<24;
        int red = numeroRandom.nextInt(255)<<16;
        int green = numeroRandom.nextInt(255)<<8;
        int blue = numeroRandom.nextInt(255);
        int color = z + red + green + blue;
        
        return color;
    }
    
    
    /*
    Nota: Encontrar forma de almacenar los tres en uno, quitando el if.
    
    Obtiene elemento de la matriz guardada como arreglo.
    Numeros colores:
    0 = azul
    1 = rojo
    2 = verde
    */
    private int [] obtenerMitad(int color){
        int mitad []=  new int[tamanno/2];
        
        for(int elemento = 0; elemento < tamanno/2 ; ++elemento){
            mitad[elemento] = this.informacionRGB[elemento];
        }
        
        return mitad;
    }
    
    
    
    //Salida
    
    public void guardarIndividuo(){
        //BufferedImage siguienteImagen = new BufferedImage(this.tammanoY, this.tammanoX, BufferedImage.TYPE_INT_ARGB);
        
        int color = 0;
        
        for(int x = 0; x < this.tammanoY; ++x){
            for(int y = 0; y < this.tammanoX; ++y){
                color = this.informacionRGB[this.tammanoY*y + x];
                //siguienteImagen.setRGB(x, y, color);
                //System.out.print(Integer.toBinaryString(siguienteImagen.getRGB(x, y)) + "\t");
            }
            //System.out.println("\n");
        }
        System.out.println("Individuo guardado");
    }
    
    
}
