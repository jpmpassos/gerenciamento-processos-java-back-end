package br.com.gerencproces.util;

public class FuncoesUtils {

	public static double caucularDistanciaDeCoodenadas(Integer x1, Integer y1, Integer x2, Integer y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
}
