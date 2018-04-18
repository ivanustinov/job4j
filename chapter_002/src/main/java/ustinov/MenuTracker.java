package ustinov;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    Action[] actions = new Action[5];


    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }


    public void fillActions() {
        actions[0] = new AddItem();
        actions[1] = new ShowItems();
    }

    public void select(int key) {
        actions[key].execute(tracker, input);
    }
    public void show() {
        for (Action action : actions) {
            if(action == null)
                break;
            System.out.println(action.info());
        }
    }
    private class AddItem implements Action {
        public int key() {
            return 0;
        }

        @Override
        public void execute(Tracker tracker, Input input) {
            String name = input.ask("Please enter the name.");
            String desc = input.ask("Please enter the description.");
            tracker.add(new Item(name, desc));
        }

        @Override
        public String info() {
            return String.format("%s - %s", key(), "add the new Item.");
        }
    }

    private class ShowItems implements Action {
        @Override
        public void execute(Tracker tracker, Input input) {
            for (Item item : tracker.getAll()) {
                System.out.println(String.format("%s %s", item.getId(), item.getName()));
            }
        }

        @Override
        public String info() {
            return String.format("%s - %s", key(), "Show all items");
        }

        @Override
        public int key() {
            return 1;
        }
    }
    private class EditItem implements Action {
        @Override
        public void execute(Tracker tracker, Input input) {
            String id = input.ask("Please enter Id Item to edit");
            String name = input.ask("Please enter the name");
            String desc = input.ask("Please enter the description");
            Item item = new Item(name, desc);
            item.setId(id);
            tracker.editItem(item);
        }

        @Override
        public String info() {
            return String.format("%s - %s", key(), "Edit item");
        }

        @Override
        public int key() {
            return 2;
        }
    }
}
