package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import model.Pair;

public class FloodColors {
	
	private final List<Pair<Integer, Color>> colors;
	private Random randColor;
	
	public FloodColors(){
		colors = new ArrayList<>(Arrays.asList(
				new Pair<>(0, new Color(255, 0, 0)),
				new Pair<>(1, new Color(255, 135, 0)),
				new Pair<>(2, new Color(255, 211, 0)),
				new Pair<>(3, new Color(222, 255, 10)),
				new Pair<>(4, new Color(161, 255, 10)),
				new Pair<>(5, new Color(10, 239, 255)),
				new Pair<>(6, new Color(20, 125, 245)),
				new Pair<>(7, new Color(88, 10, 255)),
				new Pair<>(8, new Color(190, 10, 255)),
				new Pair<>(9, new Color(255, 0, 84))
			));
		randColor = new Random();
	}
	
	public Color getChosenColor(int chosenColor) {
		return colors.get(chosenColor).getY();
	}
	
	public List<Pair<Integer, Color>> getNColors(int n, int size) {
		List<Pair<Integer, Color>> resList = new ArrayList();
		boolean done = false;
		while(done == false) {
			if(resList.size() == size) {
				
			}
		}
		return resList; //colors.stream().filter(p -> p.getX() <= n).collect(Collectors.toList());
	}
	
	public List<Pair<Integer, Color>> getAllColors() {
		return colors;
	}
	
}
