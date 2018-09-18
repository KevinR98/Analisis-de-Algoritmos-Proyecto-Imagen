package proyectoimagen;

import java.util.Arrays;
import libreria.FormulaSimilitud;
import libreria.QuickSort;

public class ProyectoImagen 
{

    public static void main(String[] args) 
    {

        AlgoritmoGenetico  a = new AlgoritmoGenetico("verde.png", 10000, 1000, 30, 30);
        
        //Corrida formula de similitud euclediana
        //euclediana();
        
        
        //Corrida quicksort
        /*
        int[] unsorted = {6, 5, 3, 1, 8, 7, 2, 4};
        System.out.println("Unsorted array :" + Arrays.toString(unsorted));
        QuickSort algorithm = new QuickSort();
        algorithm.sort(unsorted);
        System.out.println("Sorted array :" + Arrays.toString(unsorted));
        */
    }
    
}
