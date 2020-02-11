package morePizza;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Driver {
	public static void main(String[] args) throws IOException {
		Files.list(Paths.get(".")).filter(e -> e.toString().endsWith(".in")).forEach(e -> drive(e));
	}

	private static void drive(Path path) {
		List<Integer> solution = null;
		List<Integer> set = null;
		try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
			int max = Integer.parseInt(reader.readLine().split(" ")[0]);
			String[] temp = reader.readLine().split(" ");
			set = new ArrayList<>();
			for(String elemento : temp) {
				set.add(Integer.parseInt(elemento));
			}
			Collections.sort(set, Collections.reverseOrder());
			Combinator combinador = new Combinator(set, max);
			solution = combinador.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try(PrintWriter writer = new PrintWriter(path.toString().replace(".in", ".out"))) {
			List<Integer> indexes = new ArrayList<>();
			Collections.sort(set);
			writer.println(solution.stream().count());
			for( int i = 0; i < solution.size(); i++) {
				int index = set.indexOf(solution.get(i));
				if(indexes.contains(index)) {
					set.set(index, 0);
					i--;
					continue;
				}
				if(index != -1) {
					indexes.add(index);
					writer.print(index + " ");
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
