package model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

	private final int colorValue;
	private final String name;
	private final Color actualColor;

	private Colors(int number, String name, Color color) {
		this.colorValue = number;
		this.name = name;
		this.actualColor = color;
	}
	
	public static List<Colors> getRandomColors(int num){
		/*Random randColors = new Random();
		List<Colors> chosenColors = new LinkedList<>();
		while(1=1) {
			chosenColors.add(randColors.nextInt(num));
		}*/
		return null;
	}
	
	public int getColorValue(int i) {
		return this.colorValue;
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
