package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public enum Colors {
	
	RED(0, "Red", new Color(255, 0, 0)),
	ORANGE(1, "Orange", new Color(255, 135, 0)),
	YELLOW(2, "Yellow", new Color(255, 211, 0)),
	LIME(3, "Lime", new Color(222, 255, 10)),
	GREEN(4, "Green", new Color(161, 255, 10)),
	LIGHT_BLUE(5, "Light Blue", new Color(10, 239, 255)),
	BLUE(6, "Blue", new Color(20, 125, 245)),
	INDIGO(7, "Indigo", new Color(88, 10, 255)),
	PURPLE(8, "Purple", new Color(190, 10, 255)),
	MAGENTA(9, "Magenta", new Color(255, 0, 84));

	private static final int MAX_COLOR_NUMBER = 10;
	private final int colorValue;
	private final String name;
	private final Color actualColor;

	private Colors(int number, String name, Color color) {
		this.colorValue = number;
		this.name = name;
		this.actualColor = color;
	}
	
	public static List<Colors> getRandomColors(int n) {
		List<Colors> result = new LinkedList<>(Arrays.asList(Colors.values()));
		int colorNum = n;
		
		// Checks that the number of color requested doesn't exceed the maximum number of colors.
		if (n > MAX_COLOR_NUMBER) {
			System.err.println("Input number (" + n + ") was too large: input number adapted to the upper bound (" + MAX_COLOR_NUMBER + ")");
			colorNum = MAX_COLOR_NUMBER;
		}
		
		Random randColor = new Random();
		for (int i = 0; i < (MAX_COLOR_NUMBER - colorNum); i++) {
			result.remove(randColor.nextInt(MAX_COLOR_NUMBER - i));
		}
		
		return result;
	}
	
	public static Colors translateColor(Color colorToTranslate) {
		List<Colors> colorsList = new LinkedList<>(Arrays.asList(Colors.values()));
		List<Colors> requestedColor = colorsList.stream().filter(c -> c.getActualColor().equals(colorToTranslate)).collect(Collectors.toList());
		if(requestedColor.isEmpty()) {
			return null;
		}
		return requestedColor.get(0);
	}
	
	public int getColorValue() {
		return this.colorValue;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Color getActualColor() {
		return this.actualColor;
	}
	
	public String toString() {
		return this.name + ": " + this.colorValue + ", " + this.actualColor;
	}

}
