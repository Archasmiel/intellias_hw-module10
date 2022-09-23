package net.archasmiel.homework;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.archasmiel.homework.models.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class FileHelper {

	private FileHelper() {

	}

	public static boolean createFile(String path) {
		File file = new File(path);
		try {
			return file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<String> readLines(String path) {
		List<String> lines = new ArrayList<>();

		try(FileReader reader = new FileReader(path)) {
			StringBuilder builder = new StringBuilder();
			int symbol;
			while ((symbol = reader.read()) != -1) {
				if ((char) symbol == '\r') {
					lines.add(builder.toString());
					builder = new StringBuilder();
				} else
				if ((char) symbol != '\n') {
					builder.append((char) symbol);
				}
			}
			if (builder.length() > 0) {
				lines.add(builder.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

}
