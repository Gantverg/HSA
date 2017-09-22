package tel_ran.view;

import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput {
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public String getString(String prompt) {
		put(prompt);
		return scanner.nextLine();
	}

	@Override
	public void put(Object object) {
		System.out.println(object);
	}

}
