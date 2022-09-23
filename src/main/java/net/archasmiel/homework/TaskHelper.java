package net.archasmiel.homework;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.archasmiel.homework.models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static net.archasmiel.homework.FileHelper.readLines;

public class TaskHelper {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Pattern PHONE_EXP1 = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
	private static final Pattern PHONE_EXP2 = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}");

	private TaskHelper() {

	}

	private static boolean isPhone(CharSequence cs) {
		return PHONE_EXP1.matcher(cs).results().count() == 1 || PHONE_EXP2.matcher(cs).results().count() == 1;
	}

	private static User getUser(String line) {
		String[] data = line.split(" ");
		return new User(data[0], Integer.parseInt(data[1]));
	}

	public static void parsePhones(String path) {
		List<String> lines = readLines(path);
		lines.stream().filter(TaskHelper::isPhone).forEach(System.out::println);
	}

	public static void parseUsers(String path) {
		try (FileWriter writer = new FileWriter("users1.json")) {
			List<User> users = readLines(path).stream()
				.skip(1).map(TaskHelper::getUser).toList();

			writer.write(GSON.toJson(users));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void parseWords(String path) {
		Map<String, Integer> wordCount = new HashMap<>();

		readLines(path).forEach(e -> {
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
