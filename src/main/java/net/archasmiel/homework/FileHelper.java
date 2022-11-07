package net.archasmiel.homework;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

	public FileHelper() {

	}

	public List<String> readLines(String path) {
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
