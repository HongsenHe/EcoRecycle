/**
 * Author: Hongsen He, Chicheng Zhou
 * March 16, 2014
 * 
 * In this project, we simulate a EcoSystem
 * The EcoSystem has two parts: RMOS and RCM
 * 
 * RMOS introduction:
 * 1. RMOS can add/delete/active 10 RCMs,
 * 2. RMOS can add/delete/change items status,
 * 3. RMOS will know RCMs status with different color
 * 4. RMOS can check RCMs current status: weight, cash
 * 5. RMOS statistics RCMs monthly information
 * and shows by three pie chart
 * 
 * RCM introduction:
 * 1. Each RCM will show an acceptable item list
 * 2. RCM can judge if the user input a valid item
 * 3. RCM will calculate input items value
 * 4. RCM will judge if the RCM is full or has enough cash
 * 5. RCM can return cash or coupon to the user
 * 6. RCM can communicate with RMOS and update its status
 * 
 * java version "1.7.0_07"
 * Java(TM) SE Runtime Environment (build 1.7.0_07-b10)
 * Java HotSpot(TM) 64-Bit Server VM (build 23.3-b01, mixed mode)
 * JAVA IDE: Eclipse
 * Database: MySQL
 */

package testFile;

import projTest.*;

public class Tester {

	public static void main(String[] args) {
		
		// open RCM UI
		RCMView rcmView = new RCMView();
		rcmView.setVisible(true);
		
		// open RMOS UI
		RMOSView rmosView = new RMOSView();
		rmosView.setVisible(true);
		
	}

}
