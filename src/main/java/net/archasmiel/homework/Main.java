package net.archasmiel.homework;

public class Main {

	public static void main(String[] args) {
		task1();
	}

	private static void task1() {
		TaskHelper helper = new TaskHelper();
		helper.parsePhones("file1.txt");
	}

	private static void task2() {
		TaskHelper helper = new TaskHelper();
		helper.parseUsers("file2.txt");
	}

	private static void task3() {
		TaskHelper helper = new TaskHelper();
		helper.parseWords("file3.txt");
	}

}
