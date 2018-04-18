package ustinov;

import java.util.Scanner;

public class ConsoleInput implements Input {
    Scanner sc = new Scanner(System.in);

    @Override
    public String ask(String s) {
        print(s);
        return sc.nextLine();
    }

    @Override
    public void print(String data) {
        System.out.println(data);
    }
}
