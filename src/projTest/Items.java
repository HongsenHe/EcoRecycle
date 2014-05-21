package projTest;

public class Items {
	// instance variable

	// for items from the user
	private int itemNum;
	private double weight;

	// for RMOS
	private double price;
	private String itemsType;
	private int itemsNum;

	// constructor
	public Items() {
	}

	public Items(int itemType, double weight) {
		this.itemNum = itemType;
		this.weight = weight;
	}

	public Items(double price, String itemsType, int itemNum) {
		this.price = price;
		this.itemsType = itemsType;
		this.itemsNum = itemNum;
	}

	// methods

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItemsType() {
		return itemsType;
	}

	public void setItemsType(String itemsType) {
		this.itemsType = itemsType;
	}

	public int getItemNum() {
		return itemsNum;
	}

	public void setItemNum(int itemNum) {
		this.itemsNum = itemNum;
	}

	public int getItemType() {
		return itemNum;
	}

	public void setItemType(int itemType) {
		this.itemNum = itemType;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
