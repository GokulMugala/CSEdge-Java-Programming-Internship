import java.util.HashMap;
import java.util.Map;

public class InventoryManagement {
    private final Map<String, InventoryItem> inventory;

    public InventoryManagement() {
        inventory = new HashMap<>();
    }

    public void addItem(InventoryItem item) {
        inventory.put(item.getItemId(), item);
    }

    public void removeItem(String itemId) {
        inventory.remove(itemId);
    }

    public InventoryItem getItem(String itemId) {
        return inventory.get(itemId);
    }

    public void displayAllItems() {
        for (InventoryItem item : inventory.values()) {
            System.out.println(item);
        }
    }
}
