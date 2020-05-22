package application;

import java.text.DecimalFormat;
import java.util.Scanner;

import org.apache.poi.ss.formula.functions.FinanceLib;

/* Ao digitar os valores, use a vírgula(,) como separador de decimais*/


public class Program {

	static Scanner sc = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("##,###.##");

	public static void main(String[] args) {
		
		System.out.println("Calculadora de Investimento para Aposentadoria");
		System.out.println();
		System.out.println("1 - Calculadora de aporte mensal");
		System.out.println("2 - Valor do aporte mensal");
		System.out.println("3 - Após a aposentadoria quantos anos o dinheiro durará");
		System.out.println("4 - Quantos anos o dinheiro durará");

		char ch = sc.next().charAt(0);

		switch (ch) {

		case '1':
			valorFuturo();
		case '2':
			aporteMensal();
		case '3':
			valorAcumulado();
		case '4':
			anosRestantes();
		}

		sc.close();
	}

	public static void valorFuturo() {
		try {
			System.out.print("Digite a taxa mensal: ");
			Double taxa = sc.nextDouble();
			System.out.print("Digite o prazo em meses: ");
			Integer prazo = sc.nextInt();
			System.out.print("Digite o valor do seu aporte mensal: ");
			Double aporte = sc.nextDouble();
			System.out.println("Digite o valor de investimento Inicial: ");
			Double valorInicial = sc.nextDouble();
			System.out.println("R$ " + df.format(-FinanceLib.fv(taxa / 100, prazo, aporte, valorInicial, false)));
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void aporteMensal() {
		try {
			System.out.print("Digite a taxa mensal: ");
			Double taxa = sc.nextDouble();
			System.out.print("Digite o prazo em meses: ");
			Integer prazo = sc.nextInt();
			System.out.println("Digite o valor que você deseja resgatar: ");
			Double valorFinal = sc.nextDouble();
			System.out.println("R$ " + df.format((-FinanceLib.pmt(taxa/100, prazo, 0.0, valorFinal, false))));
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void valorAcumulado() {
		try {
			System.out.print("Digite o valor acumulado: ");
			Double valorAcumulado = sc.nextDouble();
			System.out.print("Digite a taxa mensal: ");
			Double taxa = sc.nextDouble();
			System.out.print("Digite o prazo em anos que você pretende usar o dinheiro: ");
			Integer prazo = sc.nextInt();
			System.out.println("Digite o valor da sua retirada mensal: ");
			Double retirada = sc.nextDouble();
			
			Double fv = -FinanceLib.fv(taxa / 100, prazo*12, -retirada, valorAcumulado, false);
			if(fv>=0) {
				System.out.println("Com os esses valores, ainda sobrarão R$ " + df.format(fv));
			}
			else {
				System.out.println("O dinheiro acumulado, não será suficiente nessas condições");
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void anosRestantes() {
		try {
			System.out.print("Digite o valor acumulado: ");
			Double valorAcumulado = sc.nextDouble();
			System.out.print("Digite a taxa mensal: ");
			Double taxa = sc.nextDouble();
			System.out.println("Digite o valor da sua retirada mensal: ");
			Double retirada = sc.nextDouble();
			
			long ar = Math.round(FinanceLib.nper(taxa/100,-retirada,valorAcumulado,1,false)/12);
			if(ar>0) {
				System.out.printf("Nesse cenário, seu dinheiro vai durar %d anos%n", ar);
			}
			else {
				System.out.println("Nesse cenário seu dinheiro 'não vai acabar'");
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
}
