package Day1;
import java.util.Scanner;

public class ZigzagPattern {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Number: ");
		String input = scanner.nextLine();
		
		boolean left_to_right = true;
		int current_write_num = 1;
		
		int input_int = Integer.parseInt(input);
		
		for (int i = 0; i < input_int; i++) {
			if (left_to_right) {
				for (int j = 0; j < input_int; j++) {
					System.out.print(current_write_num);
					System.out.print("	");
					current_write_num++;
				}
			} else {
				current_write_num += input_int - 1;
				for (int j = 0; j < input_int; j++) {
					System.out.print(current_write_num);
					System.out.print("	");
					current_write_num--;
				}
				current_write_num += input_int + 1;
			}
			left_to_right = !left_to_right;
			System.out.println();
		}
		
		scanner.close();
	}
}
