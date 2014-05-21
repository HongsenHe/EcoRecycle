package projTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RMOS {

	private String userName = "demo";
	private String passWord = "demo";
	
	public RMOS() {
	}
	

	// **** store all the RCM into this arrayList for speedup***********
	public ArrayList<RCM> rcmList = new ArrayList<RCM>();

	public ArrayList<RCM> getRCMList() {
		return rcmList;
	}

	// add a RCM method
	public void addRCM(RCM rcm) {
		rcmList.add(rcm);
	}

	// log in method
	public boolean logIn(String userName, String passWord){
		boolean result = false;
		
		// judge user name and password
		if((this.userName.equals(userName)) && (this.passWord.equals(passWord))){
			result = true;
		}
		return result;
	}
	// display all RCM
	public String displayRCM() {
		String result = "";

		for (RCM aRcm : rcmList) {
			result += " RCM number:   " + aRcm.getRCMNum() + "   RCM ID:    "
					+ aRcm.getRCMID() + "   RCM Location:    "
					+ aRcm.getLocation() + "  \n";
		}
		return result;
	}

	// remove a RCM
	public void removeRCM(int RcmNum) {

		if (!rcmList.isEmpty()) {
			RCM temp = null;
			for (RCM aRCM : rcmList) {
				if (aRCM.getRCMNum() == RcmNum) {
					temp = aRCM;
				}
			}
			if (temp != null) {
				rcmList.remove(temp);
			}
		}
	}

	// active RCM
	public int activeRCM(int RcmNum) {
		int result = 0;
		for (RCM aRCM : rcmList) {
			if (aRCM.getRCMNum() == RcmNum) {
				result = RcmNum;
			}
		}

		return result;
	}

	// ***** Store all item into this arrayList *********
	// All RCMs will share this itemsList info.
	// when you delete a RCM, it will not remove this itemsList
	ArrayList<Items> itemsList = new ArrayList<Items>();

	public ArrayList<Items> getItemsList() {
		return itemsList;
	}

	// add an item
	public void addItem(Items item) throws IOException {
		itemsList.add(item);
	}

	// display items list, should grab data from DB.
	public String displayItemsList() {
		String result = "";
		for (Items aItem : itemsList) {
			result += "  Item Number:  " + aItem.getItemNum()
					+ "      Item Type:  " + aItem.getItemsType()
					+ "      Item Unit Price:  $" + aItem.getPrice()
					+ "	 \nAdd Successful...\n";
		}

		// it should access from database not arrayList
		return result;
	}

	// remove an item
	public void removeItem(int itemNum) {
		if (!itemsList.isEmpty()) {
			Items foundItem = null;
			for (Items aItem : itemsList) {
				if (aItem.getItemNum() == itemNum) {
					foundItem = aItem;
					// itemsList.remove(aItem);
				}
			}
			if (foundItem != null) {
				itemsList.remove(foundItem);
			}
		}
	}

	// change an item price
	public void change(int itemNum, double price) {
		if (!itemsList.isEmpty()) {
			for (Items aItem : itemsList) {
				if (aItem.getItemNum() == itemNum) {
					aItem.setPrice(price);
				}
			}
		}
	}

}
