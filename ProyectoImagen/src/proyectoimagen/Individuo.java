
package proyectoimagen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Individuo {
    /*Nota convertir a privado*/
    public int informacionRGB [];
    
    private double adaptabilidad;
    private int tamanno;
    public int tammanoX;
    public int tammanoY;
    
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
    public void formarIndividuoInicial(int imagenMeta [][]){
        

        
        for(int indice = 0; indice < tamanno ; ++indice){
            this.informacionRGB[indice] = calcularBitRandom();
        }
        
        calcularAdaptabilidad(imagenMeta);
    }
    
    /*
    Algoritmo de mutacion y decendencia.
    */
    public void crearDescendencia(Individuo primerOrigen, Individuo segundoOrigen, int imagenMeta [][], int porcentajeMutacion){
        int primerMitad [] = primerOrigen.obtenerMitad();
        int segundaMitad [] = segundoOrigen.obtenerMitad();
        
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

        double ciclosPorcentaje = (porcentajeMutacion)/(double)100 * this.tamanno;
        for(int informacionActual = 0; informacionActual < (int)ciclosPorcentaje ; ++informacionActual){
            
            this.informacionRGB[numeroRandom.nextInt(this.tamanno)] = calcularBitRandom();
        }
        
        calcularAdaptabilidad(imagenMeta);
        
    }
    

    public void algoritmoGenetico()
    {
        //funcion de comparar en la adap... tomar el 100 meta y hacer que se acerquen lo más posible y al final 
        //tener una funcion que cuente los numeros que se acerquen más y el menor será el mejor adapta. osea llega a la meta.
        
        
        
        
    }
    
    
    //      Calculo de variables.
    
    public double distanciaEuclideana(int imagenMeta [][])
    {
        
        int color = 0;
        int colorMeta = 0;
        
        int indice = 0;
        
        int diferenciaAzul = 0;
        int diferenciaVerde = 0;
        int diferenciaRojo = 0;
        int diferenciaContraste = 0;
        
        double sumatoriaAzul = 0;
        double sumatoriaVerde = 0;
        double sumatoriaRojo = 0;
        double sumatoriaContraste = 0;
        
        
        double adaptabilidadPromedio = 0;
        
        for(int x = 0; x < this.tammanoX ; ++x){
            for(int y = 0; y < this.tammanoY ; ++y){
                indice = x * this.tammanoY + y;
                
                color = this.informacionRGB[x * this.tammanoY + y];
                colorMeta = imagenMeta[x][y];
                
                diferenciaAzul = (color & 0x000000FF) - (colorMeta & 0x000000FF);
                diferenciaVerde = (color >> 8 & 0x000000FF) - (colorMeta >> 8 & 0x000000FF);
                diferenciaRojo = (color >> 16 & 0x000000FF) - (colorMeta >> 16 & 0x000000FF);
                diferenciaContraste = (color >> 24 & 0x000000FF) - (colorMeta >> 24 & 0x000000FF);
                
                
                sumatoriaAzul = sumatoriaAzul + Math.pow(diferenciaAzul,2);
                sumatoriaVerde = sumatoriaVerde + Math.pow(diferenciaVerde,2);
                sumatoriaRojo = sumatoriaRojo + Math.pow(diferenciaRojo,2);
                sumatoriaContraste = sumatoriaContraste + Math.pow(diferenciaContraste,2);
            }
        }
        
        adaptabilidadPromedio = Math.sqrt(sumatoriaAzul) + Math.sqrt(sumatoriaVerde) + Math.sqrt(sumatoriaRojo) + Math.sqrt(sumatoriaContraste);
        adaptabilidadPromedio = adaptabilidadPromedio / 4;
        
        return adaptabilidadPromedio;
    }
    
    /* 
    A considerar: se puede hacer le parametro de imagen meta un atributo del objeto por el constructor.
    Esto podria mejorar tiempos.
    */
    private void calcularAdaptabilidad(int imagenMeta [][]){
        this.adaptabilidad = distanciaEuclideana(imagenMeta);
    }
    
    
    // Metodo Get de adaptabilidad.
    public double obtenerAdaptabilidad(){
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
    private int [] obtenerMitad(){
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
