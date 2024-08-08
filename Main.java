import java.lang.Math;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Ponto p = new Ponto(1,2);

    }

    public Ponto calculaCentroide(ArrayList<Ponto> lista) {
        Ponto centroide = new Ponto(0,0);
        double menorCentroide = Double.MAX_VALUE;
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if (!lista.get(i).equals(lista.get(j))){
                    double centroideAtual = Math.sqrt(Math.pow((lista.get(i).getX() - lista.get(j).getX()), 2) + Math.pow((lista.get(i).getY() - lista.get(j).getY()), 2));
                    if (centroideAtual < menorCentroide) {
                        menorCentroide = centroideAtual;
                        centroide.setX((lista.get(i).getX()+lista.get(j).getX())/2);
                        centroide.setY((lista.get(i).getY()+lista.get(j).getY())/2);
                    }
                }
            }

        }
        return centroide;
    }
}
