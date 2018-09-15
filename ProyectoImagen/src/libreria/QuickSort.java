package libreria;

import java.util.Arrays;

/**
 *
 * @author TEC LIVE IN 2018
 */
public class QuickSort 
{     
    
    private int[] lista;
    private int largo_lista;

    public void sort(int[] lista_numeros) 
    {
        if (lista_numeros == null || lista_numeros.length == 0) {
            return;
        }
        this.lista = lista_numeros;
        largo_lista = lista_numeros.length;
        quickSort(0, largo_lista - 1);
    }

    
    private void quickSort(int primero, int ultimo) 
    {
        int i = primero;
        int j = ultimo;

        // index medio de la lista
        int pivote = lista[primero + (ultimo - primero) / 2];

        // divide en dos arrays
        while (i <= j) {
            /**
             * en cada iteración se identificara los numeros de cada lado(izq y der)
             * dado el menor y el mayor respectivamente dependiendo del pivote para
             * la comparación. Una vez completi, se puede hacer el intercambio de 
             * ambos números
             */
            while (lista[i] < pivote) {
                i++;
            }
            while (lista[j] > pivote) {
                j--;
            }
            if (i <= j) {
                intercambioQuickSort(i, j);
                // mueve los indices de ambos lados
                i++;
                j--;
            }
        }

        // llamado recursivo
        if (primero < j) {
            quickSort(primero, j);
        }

        if (i < ultimo) {
            quickSort(i, ultimo);
        }
    }

    private void intercambioQuickSort(int primero, int ultimo) 
    {
        int variable_temporal = lista[primero];
        lista[primero] = lista[ultimo];
        lista[ultimo] = variable_temporal;
    }
}
