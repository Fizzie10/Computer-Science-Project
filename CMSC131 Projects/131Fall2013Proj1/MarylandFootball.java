import java.util.Scanner;

public class MarylandFootball {

	public static void main(String[] args) {

		Scanner TypeOfChoice = new Scanner(System.in);

		System.out.print("Type 1 to enter a number or 2 to enter a name:");

		int first;

		first = TypeOfChoice.nextInt();

		if (first == 1) {

			System.out.print("Enter player number:");

			int number;

			number = TypeOfChoice.nextInt();

			if (number == 15|| number == 1|| number == 16|| number == 45){
				System.out.print("Which player wears number "+number+" on his jersey?");

				String name;

				name = TypeOfChoice.next();

				if (number == 15 && name.equals("Craddock")|| number == 1 && name.equals("Diggs")|| number == 16 && name.equals("Brown")|| number == 45 && name.equals("Ross")){ 

					System.out.print("Correct!");
				} else {
					System.out.print("Incorrect!");
				}

			} else { 
				System.out.print("Invalid Choice.");
			}
		} else { 
			System.out.print("Choose a name:");
			
			String name;
			
			int number;
			
			name = TypeOfChoice.next();
			
			if (name.equals("Diggs")||name.equals("Craddock")||name.equals("Brown")||name.equals("Ross")){

				System.out.print("What number does "+name+" wear?");

				number = TypeOfChoice.nextInt();

				if (number == 15 && name.equals("Craddock")|| number == 1 && name.equals("Diggs")|| number == 16 && name.equals("Brown")|| number == 45 && name.equals("Ross")){
					
					System.out.print("Correct!");

				} else {
					System.out.print("Incorrect!");
				}
			} else {
				System.out.print("Invalid Choice.");
			}
		}
	}
}

