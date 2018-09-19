package proyectoimagen;

import Interfaz.ImagenGrafica;

public class ProyectoImagen 
{

    public static void main(String[] args) 
    {
        AlgoritmoGenetico  proyecto = new AlgoritmoGenetico("verde.png", 10000, 1000, 30, 30,0);

        
        ImagenGrafica img = new ImagenGrafica(proyecto, 1000);
        
    }
    
}
