package libreria;
/**
 *
 * @author TEC LIVE 2018
 */
public class FormulaSimilitud 
{       
    public double distancia(int x1, int y1, int x2, int y2)
    {
        //
        double diferenciaX = x1 - x2;
        double diferenciaY = y1 - y2;

        return Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));
    }
}
 