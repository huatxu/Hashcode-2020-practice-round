package morePizza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Combinator {
	private List<Integer> solution;
	private List<Integer> set;
	private int max;
	private boolean found;
	public Combinator(List<Integer> set, int max) {
		solution = new ArrayList<>();
		this.set = set;
		this.max = max;
		System.out.println(set);
		List<Integer> newSet = combinate();
		
		getBetter(newSet);
		System.out.println(solution.size());
		System.out.println(solution.stream().mapToInt(e -> e.intValue()).sum());
	}
	
	public List<Integer> getResults() {
		return solution;
	}
	
	private List<Integer> combinate() {
		int maxYet = 0;
		List<Integer> tempSolution2 = new ArrayList<>();
		for(int i = 0; i < set.size(); i++) {
			int sum = 0;
			List<Integer> tempSolution = new ArrayList<>();
			for(int j = i; j < set.size(); j++) {
				if(sum + set.get(j) <= max) {
					sum += set.get(j);
					tempSolution.add(set.get(j));
				}
				if(sum > maxYet) {
					maxYet = sum;
					tempSolution2 = new ArrayList<>(tempSolution);
				}
				if(sum == max) {
					found = true;
					break;
				}
 			}
		}
		List<Integer> newSet = new ArrayList<>(set);
		Iterator<Integer> newSetIt = newSet.iterator();
		while(newSetIt.hasNext()) {
			if(tempSolution2.contains(newSetIt.next())) {
				newSetIt.remove();
			}
		}
		Collections.sort(tempSolution2);
		Collections.sort(newSet);
		solution = tempSolution2;	
		return newSet;
	}

	
	private void getBetter(List<Integer> newSet) {
		int maxYet = solution.stream().mapToInt(e -> e.intValue()).sum();
		for(int i = 0; i < solution.size() && !found; i++) {
			List<Integer> newSolution = new ArrayList<>(solution.subList(i, solution.size()));
			int sum = newSolution.stream().mapToInt(e -> e.intValue()).sum();
			for(int j = 0; j < newSet.size(); j++) {
				if(sum + newSet.get(j) <= max) {
					newSolution.add(newSet.get(j));
					sum += newSet.get(j);
				}
				if(sum > maxYet) {
					solution = new ArrayList<>(newSolution);
				}
			}
			if(newSet.size() == 1) { 
				break; 
			}
			
		}
	}
}
