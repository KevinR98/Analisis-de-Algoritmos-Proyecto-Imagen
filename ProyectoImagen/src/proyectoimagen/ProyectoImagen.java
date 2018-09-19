package proyectoimagen;

import Interfaz.ImagenGrafica;

public class ProyectoImagen 
{

    public static void main(String[] args) 
    {
        AlgoritmoGenetico  proyecto = new AlgoritmoGenetico("verde.png", 10, 10, 300, 300,0);

        
        ImagenGrafica img = new ImagenGrafica(proyecto, 10);
        
    }
    
}
