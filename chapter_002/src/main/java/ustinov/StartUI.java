package ustinov;

public class StartUI {

    private final Tracker tracker;
    private final Input input;

    public StartUI(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
    }

    public static void main(String[] args) {
        new StartUI(new Tracker(), new ConsoleInput()).init();
    }


    public void init() {
        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
        do {
            menu.show();
            int key = Integer.parseInt(input.ask("Select:"));
            menu.select(key);
        }
        while (!"y".equals(input.ask("Exit? y")));
    }
}
