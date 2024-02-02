package laboratorio;

import java.util.Scanner;

public class Sprint03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner e = new Scanner(System.in);

		int numEquipes = 0, combates = 0;

		System.out.println("Digite a quantidade de equipes que irão participar da competição");
		numEquipes = e.nextInt();

// VALIDAÇÃO Nº DE EQUIPES

		while (numEquipes < 11 || numEquipes > 99) {
			System.out.println("O número de equipes inserido não está de acordo com as regras da competição. Para iniciar\nas disputas, "
					+ "é necessário que haja pelo menos 11 equipes participantes, com um limite\nmáximo de 99 equipes. Por favor, insira um número válido.");
			numEquipes = e.nextInt(); 
		}

		System.out.println("Quantos combates serão realizados por equipe?");
		combates = e.nextInt();

// VALIDAÇÃO Nº DE COMBATES

		while (combates < 1) {
			System.out.println("As quipes precisam realizar pelo menos 1 combate, digite novamente:");
			combates = e.nextInt();
		}

// DECLARAÇÃO DOS VETORES 

		String resuCombat[] = new String[numEquipes];
		double pontuacao[] = new double[numEquipes];
		double desing[] = new double[numEquipes];
		double pontosEmpate[] = new double[numEquipes];
		int rankingInd[] = new int[numEquipes];

// INFORMANDO NOTA DE DESING E RESULTADO DOS COMBATES 		

		for (int i = 0; i < numEquipes; i++) {
			System.out.println("Digite a nota de desing do robô da " + (i + 1) + "ª equipe");
			desing[i] = e.nextDouble();

			for (int j = 0; j < combates; j++) {
				System.out.println("Digite o resultado do " + (j + 1) + "º combate da " + (i + 1) + "ª equipe");
				System.out.println("V = Vitoria\nE = Empate\nD = Derrota");
				resuCombat[i] = e.next().toUpperCase();

//VALIDAÇÃO RESULTADO DOS COMBATES 

				while (!resuCombat[i].equals("V") && !resuCombat[i].equals("E") && !resuCombat[i].equals("D")) {
					System.out.println("Opção inválida, digite novamente");
					System.out.println("V = Vitoria\nE = Empate\nD = Derrota");
					resuCombat[i] = e.next().toUpperCase();
				}
//ATRIBUINDO VALORES A CADA RESULTADO 

				if (resuCombat[i].equals("V")) {
					pontuacao[i] = pontuacao[i] + 5;

				} else if (resuCombat[i].equals("E")) {
					pontuacao[i] = pontuacao[i] + 3;

				} else if (resuCombat[i].equals("D")) {
					pontuacao[i] = 0;
				}

			}
		}

// ARRUMANDO INDICE E POSIÇÕES DO RANKING 

		for (int i = 0; i < numEquipes; i++) {
			rankingInd[i] = i;
		}

		for (int i = 0; i < numEquipes - 1; i++) {
			for (int j = i + 1; j < numEquipes; j++) {
				if (pontuacao[rankingInd[i]] < pontuacao[rankingInd[j]]) {
					int temp = rankingInd[i];
					rankingInd[i] = rankingInd[j];
					rankingInd[j] = temp;
				} else if (pontuacao[rankingInd[i]] == pontuacao[rankingInd[j]]) {
					if (desing[rankingInd[i]] < desing[rankingInd[j]]) {
						int temp = rankingInd[i];
						rankingInd[i] = rankingInd[j];
						rankingInd[j] = temp;
					}
				}
			}
		}

// RANKING

		System.out.println("\nRanking:\n");

		for (int r = 0; r < numEquipes; r++) {
			int equipeInd = rankingInd[r];
			double pontuacaoEquipe = pontuacao[equipeInd];
			System.out.println((r + 1) + "° lugar - Equipe " + (equipeInd + 1) + ": " + pontuacaoEquipe + " pontos");
		}

// CASO HAJA EMPATE 		

		boolean empate = false;
		for (int i = 0; i < numEquipes - 1; i++) {
			if (pontuacao[rankingInd[i]] == pontuacao[rankingInd[i + 1]]) {
				empate = true;
				break;
			}
		}

		if (empate) {
			System.out.println("\nHá empate no ranking. Será necessário utilizar a nota de desing para desempatar.");

			for (int i = 0; i < numEquipes - 1; i++) {
				for (int j = i + 1; j < numEquipes; j++) {
					if (pontuacao[rankingInd[i]] == pontuacao[rankingInd[j]]) {
						if (desing[rankingInd[i]] < desing[rankingInd[j]]) {
							int temp = rankingInd[i];
							rankingInd[i] = rankingInd[j];
							rankingInd[j] = temp;
						}
					}
				}
			}

			for (int i = 0; i < numEquipes; i++) {
				pontosEmpate[i] = pontuacao[rankingInd[i]] + desing[rankingInd[i]];
			}

// RANKING DESEMPATADO 

			System.out.println("\nNovo ranking:\n");

			for (int r = 0; r < numEquipes; r++) {
				int equipeInd = rankingInd[r];
				double pontuacaoEquipe = pontuacao[equipeInd];
				double pontosFinais = pontosEmpate[r];
				System.out.println((r + 1) + "° lugar - Equipe " + (equipeInd + 1) + ": " + pontuacaoEquipe
						+ " pontos + Design: " + desing[equipeInd] + "\nPontuação final: " + pontosFinais);
				System.out.println("");
			}
		}
	}
}
