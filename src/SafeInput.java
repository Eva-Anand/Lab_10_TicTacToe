import java.util.Scanner;

public class SafeInput
{

    public static int getRangedInt(
            Scanner console, String prompt, int low, int high)
    {

        while (true)
        {
            System.out.print(prompt + " [" + low + "-" + high + "]: ");

            if (console.hasNextInt())
            {
                int value = console.nextInt();
                console.nextLine();

                if (value >= low && value <= high)
                {
                    return value;
                }

                System.out.println(
                        "Enter a number between " + low + " and " + high + ".");
            } else
            {
                console.nextLine();
                System.out.println("Invalid input. Enter a whole number.");
            }
        }
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        while (true) {
            System.out.print(prompt + " [Y/N]: ");
            String answer = console.nextLine().trim();

            if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Please enter Y or N.");
        }
    }
}