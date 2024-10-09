public class InventoryItem {
    private String itemId;
    private String itemName;
    private int quantity;

    public InventoryItem(String itemId, String itemName, int quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
