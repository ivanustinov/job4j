package ustinov;

import ustinov.Input;
import ustinov.Tracker;

public interface Action {
    void execute(Tracker tracker, Input input);
    String info();
    int key();
}
