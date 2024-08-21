import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random aleatorio = new Random();
        int tamanho = 10;
        Ponto[] vetor = new Ponto[tamanho];
        for (int i = 0; i < tamanho; i++){
            int x = aleatorio.nextInt(tamanho);
            int y = aleatorio.nextInt(tamanho);
            vetor[i] = new Ponto(x,y);
        }
        HeapBinariaMinima heap = new HeapBinariaMinima(tamanho);
        heap = heap.calcularDistancias(vetor, tamanho);
        heap.imprime();
    }
}
