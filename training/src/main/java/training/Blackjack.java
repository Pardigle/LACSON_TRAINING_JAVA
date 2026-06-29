package training;
import java.util.Scanner;

public class Blackjack {
	public static void main (String[] args) {
		print_day_patternmatch();
	}
	
	public static int blackjack(int A, int B) {
		if (A > 21 && B > 21) {
			return 0;
		} else if (A <= 21 && (B > 21 || A > B || A == B || (A == 21 && B == 21))) {
			return A;
		} else {
			return B;
		}
	}
	
	public static void print_day_switch() {
		enum Day {
			Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Number (1-7): ");
		String input = scanner.nextLine();
		
		switch (input) {
		case "1":
			System.out.println(Day.Monday);
			break;
		case "2":
			System.out.println(Day.Tuesday);
			break;
		case "3":
			System.out.println(Day.Wednesday);
			break;
		case "4":
			System.out.println(Day.Thursday);
			break;
		case "5":
			System.out.println(Day.Friday);
			break;
		case "6":
			System.out.println(Day.Saturday);
			break;
		case "7":
			System.out.println(Day.Sunday);
			break;
		default:
			System.out.println("Invalid day number.");
		}
		
		scanner.close();
	}
	
	public static void print_day_patternmatch() {
		enum Day {
			Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Number (1-7): ");
		String input = scanner.nextLine();
		
		switch (input) {
		case "1" -> System.out.println(Day.Monday);
		case "2" -> System.out.println(Day.Tuesday);
		case "3" -> System.out.println(Day.Wednesday);
		case "4" -> System.out.println(Day.Thursday);
		case "5" -> System.out.println(Day.Friday);
		case "6" -> System.out.println(Day.Saturday);
		case "7" -> System.out.println(Day.Sunday);
		default -> System.out.println("Invalid day number.");
		}
		
		scanner.close();		
	}
	
	public static void pyramid_for() {
		Scanner scanner = new Scanner(System.in);
		int input_int = 0;
		
		while (!(0 < input_int && input_int <= 20)) {
			System.out.print("Number (1-20): ");
			String input = scanner.nextLine();
			input_int = Integer.parseInt(input);
			
			if (0 < input_int && input_int <= 20) {
				for (int i = 1; i <= input_int; i++) {
					for (int j = 0; j < i; j++) {
						System.out.print(j + 1);
						System.out.print(" ");
					}
					System.out.println();
				}
			}
		}
		scanner.close();
	}
	
	public static void pyramid_while() {
		Scanner scanner = new Scanner(System.in);
		int input_int = 0;
		
		while (!(0 < input_int && input_int <= 20)) {
			System.out.print("Number (1-20): ");
			String input = scanner.nextLine();
			input_int = Integer.parseInt(input);
			
			if (0 < input_int && input_int <= 20) {
				int i = 1;
				while (i <= input_int) {
					int j = 0;
					while (j < i) {
						System.out.print(j + 1);
						System.out.print(" ");
						j++;
					}
					System.out.println();
					i++;
				}
			}
		}
		scanner.close();
	}
	
	public static void pyramid_dowhile() {
		Scanner scanner = new Scanner(System.in);
		int input_int = 0;
		
		while (!(0 < input_int && input_int <= 20)) {
			System.out.print("Number (1-20): ");
			String input = scanner.nextLine();
			input_int = Integer.parseInt(input);
			
			if (0 < input_int && input_int <= 20) {
				int i = 1;
				do {
					int j = 0;
					do {
						System.out.print(j + 1);
						System.out.print(" ");
						j++;
					} while (j < i);
					System.out.println();
					i++;
				} while (i <= input_int);
			}
		}
		scanner.close();
	}
}