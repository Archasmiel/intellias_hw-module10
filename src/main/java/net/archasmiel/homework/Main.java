package net.archasmiel.homework;

public class Main {

	public static void main(String[] args) {
		task3();
	}

	private static void task1() {
		TaskHelper.parsePhones("file1.txt");
	}

	private static void task2() {
		TaskHelper.parseUsers("file2.txt");
	}

	private static void task3() {
		TaskHelper.parseWords("file3.txt");
	}

}
