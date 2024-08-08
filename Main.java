import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 0;
        ArrayList<Ponto> pontos = new ArrayList<>();
        Random gerador = new Random();
        for (int i = 0; i < n; i++) {
            pontos.add(new Ponto(gerador.nextInt(), gerador.nextInt()));
        }
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
