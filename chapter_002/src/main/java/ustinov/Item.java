package ustinov;

public class Item {
    private String Id;
    private String name;
    private String description;
    private long create;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
