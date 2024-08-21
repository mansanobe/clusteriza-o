import java.util.ArrayList;

public class HeapBinariaMinima
{
	public class Cluster{
		Ponto ponto1;
		Ponto ponto2;
		double distancia;

		public Cluster(Ponto ponto1, Ponto ponto2, double distancia) {
			this.ponto1 = ponto1;
			this.ponto2 = ponto2;
			this.distancia = distancia;
		}

		public Ponto getPonto1() {
			return ponto1;
		}

		public void setPonto1(Ponto ponto1) {
			this.ponto1 = ponto1;
		}

		public Ponto getPonto2() {
			return ponto2;
		}

		public void setPonto2(Ponto ponto2) {
			this.ponto2 = ponto2;
		}

		public double getDistancia() {
			return distancia;
		}

		public void setDistancia(double distancia) {
			this.distancia = distancia;
		}
	}
	private int n;               /* Numero de elementos no heap. */
	private int tam;             /* Tamanho máximo do heap. */
	private Cluster[] vetor;          /* Vetor com elementos. */

	/* Constrói heap vazio. */
	public HeapBinariaMinima(int tamanho)
	{
		n = 0;
		tam = tamanho;
		vetor = new Cluster[tamanho+1];
	}

	/* Constrói heap a partir de vetor v. */
	public HeapBinariaMinima(int tamanho, Cluster[] v)
	{
		tam = tamanho;
		vetor = new Cluster[tamanho+1];
		n = tamanho;

		for( int i = 0; i < tamanho; i++ )
			vetor[ i + 1 ] = v[ i ];

		constroiHeap();
	}

	/* Testa se a fila de prioridade está logicamente vazia.
	   Retorna true se vazia, false, caso contrário. */
	public boolean vazia()
	{
		return n == 0;
	}

	/* Faz a lista de prioridades logicamente vazia. */
	public void fazVazia()
	{
		n = 0;
	}

	/* Imprime os elementos da heap. */
	public void imprime()
	{
		System.out.print("Conteúdo da heap: ");
		
		for(int i = 1; i <= n; i++)
			System.out.print(vetor[i] + " ");

		System.out.println();
	}

	/* Busca o menor item na fila de prioridades.
	   Retorna o menor item em itemMin e true, ou falso se a heap estiver vazia. */
	public double min()
	{
		if (this.vazia())
		{
			System.out.println("Fila de prioridades vazia!");
			return Double.MAX_VALUE;
		}

		return vetor[1].getDistancia();
	}

	/* Remove o menor item da lista de prioridades e coloca ele em itemMin. */
	public double removeMin()
	{
		double itemMin;
		
		if(this.vazia())
		{
			System.out.println("Fila de prioridades vazia!");
			return Integer.MAX_VALUE;
		}

		itemMin = vetor[1].getDistancia();
		vetor[1] = vetor[n];
		n--;
		refaz(1);
		
		return itemMin;
	}

	/* Método auxiliar que estabelece a propriedade de ordem do heap a 
	 * partir de um vetor arbitrário dos itens. */
	private void constroiHeap()
	{
		for( int i = n / 2; i > 0; i-- )
			refaz(i);
	}

	/* Método auxiliar para restaurar a propriedade de heap que
	 * não está sendo respeitada pelo nó i. */
	private void refaz(int i)
	{
		double x = vetor[ i ].getDistancia();

		while(2*i <= n)
		{
			int filhoEsq, filhoDir, menorFilho;
			
			filhoEsq = 2*i;
			filhoDir = 2*i + 1;
			
			/* Por enquanto, o menor filho é o da esquerda. */
			menorFilho = filhoEsq;
			
			/* Verifica se o filho direito existe. */
			if(filhoDir <= n)
			{
				 /* Em caso positivo, verifica se é menor que o filho esquerdo. */
				if(vetor[ filhoDir ].getDistancia() < vetor[ filhoEsq ].getDistancia())
					menorFilho = filhoDir;
			}

			/* Compara o valor do menor dos filhos com x. */
			if(vetor[ menorFilho ].getDistancia() < x)
				vetor [ i ] = vetor[ menorFilho ];
			else
				break;
			
			/* Como o elemento x que estava na posição "i" desceu para o nível de baixo, a próxima
			 * iteração vai verificar a possibilidade de trocar esse elemento x (agora na 
			 * posição "menorFilho") com um de seus novos filhos. */
			i = menorFilho;
		}
		
		vetor[ i ].setDistancia(x);
	}

	/* Insere item x na fila de prioridade, mantendo a propriedade do heap.
	 * São permitidas duplicatas. */
	public void insere(int x)
	{
		if (tam == n)
		{
			System.out.println("Fila de prioridades cheia!");
			return;
		}

		/* O elemento é inicialmente inserido na primeira posição disponível
		 * na árvore, considerando de cima para baixo, da esquerda para a direita. */
		n++;
		int pos = n;
		
		/* Sentinela utilizada para tratar o caso do pai do nó raiz (de índice 1). */
		vetor[0].setDistancia(x);

		/* Refaz heap (sobe elemento). */
		while(x < vetor[pos/2].getDistancia())
		{
			vetor[pos] = vetor[ pos/2 ];
			pos /= 2;
		}
		
		vetor[pos].setDistancia(x);
	}
	public HeapBinariaMinima calcularDistancias(Ponto[] vetor, int tamanho){
		ArrayList<Cluster> clusters = new ArrayList<>();
		for (int i = 0; i < vetor.length; i++){
			for (int j = 0; j < vetor.length; j++){
				//pular duplicatas
				if (j > i){
					Cluster cluster = new Cluster(vetor[i], vetor[j], calcularDistancias(vetor[i], vetor[j]));
					clusters.add(cluster);
				}

			}
		}
		Cluster[] clustersVetor = new Cluster[clusters.size()];
		for (int i = 0; i < clusters.size(); i++){
			clustersVetor[i] = clusters.get(i);
		}
		HeapBinariaMinima heap = new HeapBinariaMinima(tamanho, clustersVetor);
		return heap;
	}
	private double calcularDistancias(Ponto x, Ponto y){

		double distancia = Math.sqrt((Math.pow(x.getX(), 2) - Math.pow(y.getX(), 2)) + (Math.pow(x.getY(), 2)) - (Math.pow(y.getY(), 2)));
		return distancia;
	}
}