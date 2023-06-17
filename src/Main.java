import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"Молоко", "Хлеб", "Сахар"};
    static int[] prices = {100, 150, 200};

    static File saveFile = new File("basket.txt");

    public static void main(String[] args) throws FileNotFoundException {
        Basket basket = null;
        if (saveFile.exists()) {
            basket = Basket.loadFromTxtFile(saveFile);
        } else {
            basket = new Basket(products, prices);
        }

        while (true) {
            showPrice();
            System.out.println("Выберите товар и количество или введите end");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }

            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);
            basket.saveTxt(saveFile);
        }
        basket.printCart();
    }

    public static void showPrice() {
        System.out.println("Что можно купить:");
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + " " + prices[i] + " руб/шт");

        }
    }
}