package projTest;

import java.sql.SQLException;
import java.util.*;

public class RCM extends Observable {

	// instance variable
	private int RCMID;
	private String location;
	private int RCMNum;

	private static final double CAPACITY = 50; // total weight for a RCM;

	private double onceWeight = 0; // only one session action
	private double totalWeight = 0; // only one person action
	private double curWeight = 0; // only for how much space that RCM has
	private double onceMoney = 0; // only one session action
	private double totalMoney = 0; // only for one person action
	private double balance = 0; // balance from the RCM
	private double monthlyWeight = 0; // monthly Weight += each curWeight
	private double monthlyMoney = 0; // monthly Money += each curWeight
	private double unitPrice = 0; // unit price

	private Date time;
	private RMOS rmos = new RMOS();

	// *******calculate input item weight and price, key step ******
	public String calItem(int itemNum, double itemWeight) throws SQLException {
		String result = "";

		setUnitPrice(RmosDB.getPriceDB(itemNum));
		// using setter and getter methods
		setOnceWeight(itemWeight);

		// totalWeight means for one person but several session
		setTotalWeight(getOnceWeight());

		// setup once Money
		setOnceMoney(getUnitPrice() * getOnceWeight());
		setTotalMoney(getOnceMoney());

		result = "You have already input Item Number " + itemNum + "\nWeight: "
				+ getOnceWeight() + " lb. ( " + getMetricUnit(itemWeight)
				+ " KG)    Price: $ " + getOnceMoney()
				+ "\nYour total money is: $ " + getTotalMoney() + "\n";

		return result;
	}

	public void endSession(int RcmNum) throws SQLException {
		// just call a RmosDB method, update 
		RmosDB.setCurCapacity(RcmNum, getTotalWeight());
	}

	public double getMetricUnit(double itemWeight) {
		return itemWeight * 0.453;
	}

	// this is just judging if the RCM is full !!
	public boolean isFull(int rcmNum, double itemWeight) throws SQLException {
		// default
		boolean result = true;

		// get curW from DB + this person + this session
		double tempW = RmosDB.getCurCapacity(rcmNum) + getTotalWeight()
				+ itemWeight;

		// ****** judge if it is full ******
		if (tempW < CAPACITY) {
			result = false;
		}
		return result;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getMonthlyMoney() {
		return monthlyMoney;
	}

	public void setMonthlyMoney(double monthlyMoney) {
		this.monthlyMoney = monthlyMoney;
	}

	public void setMonthlyWeight(double monthlyWeight) {
		this.monthlyWeight = monthlyWeight;
	}

	// useful ********** DELETE
	public void setCurWeight(double curWeight) {
		this.curWeight = curWeight;
	}

	public double getCurWeight() {
		return curWeight;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setOnceWeight(double onceWeight) {
		this.onceWeight = onceWeight;
	}

	public double getOnceWeight() {
		return onceWeight;
	}

	public void setOnceMoney(double onceMoney) {
		this.onceMoney = onceMoney;
	}

	public double getOnceMoney() {
		return onceMoney;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney += totalMoney;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight += totalWeight;
	}

	public static double getCapacity() {
		return CAPACITY;
	}

	// ****** useful method ***********

	// constructor
	public RCM() {
	} // default

	public RCM(int RCMNum) {
		this.RCMNum = RCMNum;
	}

	public RCM(int RCMNum, int RCMID, String location) {
		this.RCMNum = RCMNum;
		this.RCMID = RCMID;
		this.location = location;
	}

	// methods

	public void clear() {
		monthlyWeight += curWeight;
		curWeight = 0;
		time = new Date(); // store the time of last removal
	}

	public int getRCMID() {
		return RCMID;
	}

	public void setRCMID(int rCMID) {
		RCMID = rCMID;
	}

	public double getMonthlyWeight() {
		return monthlyWeight;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRCMNum() {
		return RCMNum;
	}

	public void setRCMNum(int rCMNum) {
		RCMNum = rCMNum;
	}

}
