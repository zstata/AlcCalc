import java.util.*;

class AlcCalc {
    public static void main(String args[]) {
        boolean flag = true;
        ArrayList<Drink> currRecipe = new ArrayList<Drink>();
        Scanner scanner = new Scanner(System.in);

        while (flag) {

            System.out.println("\nSelect an option:");
            System.out.println("1) Add a drink");
            System.out.println("2) Remove a drink");
            System.out.println("3) List current ingredients");
            System.out.println("4) List other recipes");
            System.out.println("5) Calculate current alcohol percentage");
            System.out.println("6) Exit");

            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    Drink new_drink = addDrink(scanner);
                    currRecipe.add(new_drink);
                    clearConsole();
                    break;
                case 2:
                    int decision = deleteDrink(scanner, currRecipe);
                    if (decision - 1 >= currRecipe.size()) {
                        System.out.println("Sorry, that was an invalid selection.");
                    } else {
                        currRecipe.remove(decision - 1);
                    }
                    clearConsole();
                    break;
                case 3:
                    listIngredients(currRecipe);
                    break;
                case 4:
                    listDrinks();
                    break;
                case 5:
                    calculateAlc(currRecipe);
                    break;
                case 6:
                    System.out.println("Goodbye.");
                    flag = false;
                    break;
                default:
                    break;
            }

        }

        scanner.close();
    }

    public final static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (final Exception e) {
            System.out.println("Uh oh!");
        }
    }

    public static Drink addDrink(Scanner scanner) {
        clearConsole();

        System.out.println("\nWhat is the drink's name?");
        String dname = scanner.nextLine();

        System.out.println("What is the drink's total volume?");
        double vol = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("What is the drink's alcohol percentage?");
        double perc = scanner.nextDouble() * 0.01;
        scanner.nextLine();

        Drink new_drink = new Drink(dname, vol, perc);
        return new_drink;
    }

    public static int deleteDrink(Scanner scanner, ArrayList<Drink> recipe) {
        clearConsole();

        System.out.println("Select which drink you would like to remove (by number not name):");
        listIngredients(recipe);

        int input = Integer.parseInt(scanner.nextLine());

        return input;
    }

    public static void listIngredients(ArrayList<Drink> recipe) {
        clearConsole();

        System.out.print("Current ingredients: \n");

        int count = 1;
        for (Drink d : recipe) {
            System.out.println("\t" + count + ") " + d.name);
            count++;
        }
    }

    public static void listDrinks() {

    }

    public static void calculateAlc(ArrayList<Drink> recipe) {
        clearConsole();

        double total_vol = 0;
        double alc_vol = 0;

        for (Drink d : recipe) {
            total_vol += d.oz;
            alc_vol += d.alc_oz;
        }

        if (total_vol != 0)
            System.out.println("The current alcohol percentage of this recipe is " + (alc_vol / total_vol) * 100 + "%");
        else {
            System.out.println("You haven't added any drinks yet you silly goose!");
        }

    }
}

class Drink {
    String name;
    double oz;
    double alc_oz;

    Drink(String name, double oz, double alc_percent) {
        this.name = name;
        this.oz = oz;
        this.alc_oz = alc_percent * this.oz;
    }
}
