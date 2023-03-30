import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ToyStore {
    private ArrayList<Toy> toys = new ArrayList<Toy>();

    public void addToy(int id, String name, int quantity, int weight) {
        Toy toy = new Toy(id, name, quantity, weight);
        toys.add(toy);
    }

    public void updateToyWeight(int id, int weight) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                break;
            }
        }
    }

    public void playGame() {
        ArrayList<Toy> prizeToys = new ArrayList<Toy>();
        Random random = new Random();

        for (Toy toy : toys) {
            int weight = toy.getWeight();
            int randomInt = random.nextInt(100) + 1;
            if (randomInt <= weight) {
                prizeToys.add(toy);
            }
        }

        if (prizeToys.isEmpty()) {
            System.out.println("Извините, игрушек не осталось.");
            return;
        }

        Toy prizeToy = prizeToys.get(random.nextInt(prizeToys.size()));
        System.out.println("Ваш приз это " + prizeToy.getName());
        prizeToy.decreaseQuantity();

        try {
            FileWriter writer = new FileWriter("prizeToys.txt", true);
            writer.write(prizeToy.getName() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("Произошла ошибка");
            e.printStackTrace();
        }
    }

    public void showToys() {
        System.out.println("Список игрушек: ");
        for (Toy toy : toys) {
            System.out.println(toy.getId() + " - " + toy.getName() + " - " + toy.getQuantity() + " - " + toy.getWeight());
        }
    }

    public void addToyMenu() {
        Scanner input = new Scanner(System.in);

        System.out.print("Введите номер игрушки: ");
        int id = input.nextInt();

        System.out.print("Введите название игрушки ");
        input.nextLine();
        String name = input.nextLine();

        System.out.print("Введите количество игрушек: ");
        int quantity = input.nextInt();

        System.out.print("Введите вес игрушки: ");
        int weight = input.nextInt();

        addToy(id, name, quantity, weight);
        System.out.println("Игрушка добавлена.");
    }

    public void updateToyWeightMenu() {
        Scanner input = new Scanner(System.in);

        System.out.print("Введите номер игрушки: ");
        int id = input.nextInt();

        System.out.print("Введите новый вес игрушки: ");
        int weight = input.nextInt();

        updateToyWeight(id, weight);
        System.out.println("Вес игрушки изменен.");
    }

    public void playGameMenu() {
        playGame();
        System.out.println("Игра окончена.");
    }

    public void showToysMenu() {
        showToys();
    }

    public void startMenu() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n Меню:");
            System.out.println("1. Добавить новую игрушку");
            System.out.println("2. Изменить вес игрушки");
            System.out.println("3. Провести розыгрыш");
            System.out.println("4. Показать список игрушек");
            System.out.println("5. Выход");
            System.out.print("Введите ваш выбор: ");
            int choice = input.nextInt();
    
            switch (choice) {
                case 1:
                    addToyMenu();
                    break;
                case 2:
                    updateToyWeightMenu();
                    break;
                case 3:
                    playGameMenu();
                    break;
                case 4:
                    showToysMenu();
                    break;
                case 5:
                    System.out.println("Пока!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }
    
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        toyStore.addToy(1, "Машинка", 10, 10);
        toyStore.addToy(2, "Кукла", 20, 20);
        toyStore.addToy(3, "Мячик", 30, 30);
        toyStore.startMenu();
    }
}    