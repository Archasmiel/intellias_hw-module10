package net.archasmiel.homework;

import net.archasmiel.homework.models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collector;

public class TaskHelper {

	private static final FileHelper FILE_HELPER = new FileHelper();

	public TaskHelper() {

	}

	public void parsePhones(String path) {
		List<String> lines = FILE_HELPER.readLines(path);
		lines.stream()
			.filter(e -> e.matches("\\d{3}-\\d{3}-\\d{4}") || e.matches("\\(\\d{3}\\) \\d{3}-\\d{4}"))
			.forEach(System.out::println);
	}

	public void parseUsers(String path) {
		try (FileWriter writer = new FileWriter("user.json")) {
			String result = FILE_HELPER.readLines(path).stream()
				.skip(1)
				.map(e -> {
					try {
						String[] data = e.split(" ");
						int age = Integer.parseInt(data[1]);
						return new User(data[0], age);
					} catch (Exception exception) {
						return null;
					}
				})
				.collect(Collector.of(
					() -> new StringBuilder("[\n"),
					(builder, o) -> {
						if (o == null) return;
						builder.append("\t{\n")
								.append("\t\t").append("\"name\": \"").append(o.name()).append("\",\n")
								.append("\t\t").append("\"age\": ").append(o.age()).append("\n")
								.append("\t},\n");
					},
					StringBuilder::append,
					builder -> {
						if (builder.length() > 2) {
							builder.deleteCharAt(builder.length()-2);
						}
						return builder.append("]").toString();
					}
				));

			writer.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parseWords(String path) {
		Map<String, Integer> wordCount = new HashMap<>();

		FILE_HELPER.readLines(path).forEach(e -> {
			String[] words = e.split(" +");
			for (String word: words) {
				wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
			}
		});

		wordCount.entrySet().stream()
			.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
			.forEach(e -> System.out.printf("%s %d%n", e.getKey(), e.getValue()));
	}

}
