package projTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.factories.FormFactory;
//import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class RCMView extends JFrame implements ActionListener {

	/**
	 * 
	 */
	RCM rcm;
	RMOS rmos = new RMOS();

	private static final long serialVersionUID = 1L;
	private JPanel rcmPanel;
	private JTextField txtRcmnum;
	private JTextField txtItemnumber;
	private JTextField txtItemweight;
	private JButton btnStart;
	private JButton btnContinue;
	private JButton btnEnd;
	private JButton btnInsert;
	private JButton btnReturnMoney;
	private JButton btnReturnCoupon;
	private JButton btnSwitch;
	private JTextArea txtrOutput;
	private JPanel rightPanel;
	private JTextArea txtrOutput02;
	String curMessage = "";
	int count = 0;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RCMView frame = new RCMView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public RCMView() {
		setFont(new Font("Apple LiSung", Font.BOLD, 12));
		setForeground(new Color(0, 51, 255));
		setBackground(new Color(0, 51, 255));
		setTitle("RCM\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		rcmPanel = new JPanel();
		rcmPanel.setBackground(new Color(0, 51, 255));
		rcmPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(rcmPanel);
		rcmPanel.setLayout(new GridLayout(0, 2, 0, 0));

		// ******* Right Panel **************
		JPanel UserInput = new JPanel();
		UserInput.setBackground(new Color(255, 255, 0));
		rcmPanel.add(UserInput);
		UserInput.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "User Input", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 51, 204)));
		UserInput.setLayout(null);

		JLabel lblSelectRcm = new JLabel("Select a RCM Number");
		lblSelectRcm.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblSelectRcm.setForeground(new Color(0, 51, 255));
		lblSelectRcm.setBounds(14, 47, 148, 16);
		UserInput.add(lblSelectRcm);

		txtRcmnum = new JTextField();
		txtRcmnum.setBounds(159, 44, 35, 22);
		UserInput.add(txtRcmnum);
		txtRcmnum.setColumns(10);

		// three buttons: star, continue, and end
		btnStart = new JButton("Start");
		btnStart.setHorizontalAlignment(SwingConstants.LEFT);
		btnStart.setIcon(new ImageIcon("/Users/hongsenhe/Documents/AllEclipseFiles/EcoRecycle/icons/Blip-icon.png"));
		btnStart.setForeground(new Color(204, 0, 0));
		btnStart.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnStart.setEnabled(false);
		btnStart.addActionListener((ActionListener) this);
		btnStart.setBounds(14, 105, 80, 43);
		UserInput.add(btnStart);

		btnContinue = new JButton("Continue");
		btnContinue.setHorizontalAlignment(SwingConstants.LEFT);
		btnContinue.setIcon(new ImageIcon("/Users/hongsenhe/Documents/AllEclipseFiles/EcoRecycle/icons/Blip-icon.png"));
		btnContinue.setForeground(new Color(204, 0, 0));
		btnContinue.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnContinue.setEnabled(false);
		btnContinue.addActionListener((ActionListener) this);
		btnContinue.setBounds(120, 105, 101, 43);
		UserInput.add(btnContinue);

		btnEnd = new JButton("End");
		btnEnd.setHorizontalAlignment(SwingConstants.LEFT);
		btnEnd.setIcon(new ImageIcon("/Users/hongsenhe/Documents/AllEclipseFiles/EcoRecycle/icons/Blip-icon.png"));
		btnEnd.setForeground(new Color(204, 0, 0));
		btnEnd.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnEnd.setEnabled(false);
		btnEnd.addActionListener((ActionListener) this);
		btnEnd.setBounds(245, 105, 89, 43);
		UserInput.add(btnEnd);

		JLabel lblItemNumber = new JLabel("Item Num");
		lblItemNumber.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblItemNumber.setForeground(new Color(0, 51, 255));
		lblItemNumber.setBackground(new Color(0, 51, 255));
		lblItemNumber.setBounds(21, 169, 73, 16);
		UserInput.add(lblItemNumber);

		txtItemnumber = new JTextField();
		txtItemnumber.setEnabled(false);
		txtItemnumber.setBounds(97, 163, 50, 28);
		UserInput.add(txtItemnumber);
		txtItemnumber.setColumns(10);

		JLabel lblItemWeight = new JLabel("Item Weight");
		lblItemWeight.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblItemWeight.setForeground(new Color(0, 51, 255));
		lblItemWeight.setBounds(171, 169, 89, 16);
		UserInput.add(lblItemWeight);

		txtItemweight = new JTextField();
		txtItemweight.setEnabled(false);
		txtItemweight.setBounds(272, 163, 62, 28);
		UserInput.add(txtItemweight);
		txtItemweight.setColumns(10);

		// Function buttons
		btnInsert = new JButton("Insert");
		btnInsert.setForeground(new Color(204, 0, 0));
		btnInsert.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnInsert.setEnabled(false);
		btnInsert.addActionListener((ActionListener) this);
		btnInsert.setBounds(14, 203, 320, 29);
		UserInput.add(btnInsert);

		btnReturnMoney = new JButton("Return Cash");
		btnReturnMoney.setForeground(new Color(204, 0, 0));
		btnReturnMoney.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnReturnMoney.setEnabled(false);
		btnReturnMoney.addActionListener((ActionListener) this);
		btnReturnMoney.setBounds(14, 233, 148, 29);
		UserInput.add(btnReturnMoney);

		btnSwitch = new JButton("Switch");
		btnSwitch.setForeground(new Color(204, 0, 0));
		btnSwitch.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnSwitch.addActionListener((ActionListener) this);
		btnSwitch.setBounds(231, 42, 103, 29);
		UserInput.add(btnSwitch);

		btnReturnCoupon = new JButton("Return Coupon");
		btnReturnCoupon.setForeground(new Color(204, 0, 0));
		btnReturnCoupon.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnReturnCoupon.setEnabled(false);
		btnReturnCoupon.addActionListener((ActionListener) this);
		btnReturnCoupon.setBounds(186, 233, 148, 29);
		UserInput.add(btnReturnCoupon);

		txtrOutput = new JTextArea();
		txtrOutput.setBounds(6, 289, 383, 273);
		txtrOutput.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		UserInput.add(txtrOutput);
		txtrOutput.setBackground(new Color(255, 255, 102));
		txtrOutput.setForeground(new Color(0, 51, 255));
		txtrOutput.setEditable(false);
		txtrOutput.setText("--------------------Welcome !--------------------");

		rightPanel = new JPanel();
		rightPanel.setBackground(new Color(255, 255, 0));
		rightPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "SCREEN", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 51, 204)));
		rcmPanel.add(rightPanel);
		rightPanel.setLayout(null);

		txtrOutput02 = new JTextArea();
		txtrOutput02.setBounds(6, 21, 378, 541);
		txtrOutput02.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		rightPanel.add(txtrOutput02);
		txtrOutput02.setBackground(new Color(255, 255, 51));
		txtrOutput02.setForeground(new Color(0, 51, 255));
		txtrOutput02.setEditable(false);
		txtrOutput02
				.setText("---------------Welcome !---------------");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// take event
		Object source = e.getSource();
		int RcmNum = 0;

		// if - else to select a current action

		// select a RCM number action
		if (source == btnSwitch) {

			// get RCM number
			RcmNum = Integer.parseInt(txtRcmnum.getText());

			// judge if the user input is a valid RcmNum in DB
			boolean hasRcmInDB = false;
			try {
				hasRcmInDB = RmosDB.existRcmNum(RcmNum);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// if the user switch a valid RCM, then all buttons are enabled
			if (hasRcmInDB) {
				btnStart.setEnabled(true);

				// for another switch, so have to be set false
				btnContinue.setEnabled(false);
				btnEnd.setEnabled(false);
				txtItemnumber.setEnabled(false);
				txtItemweight.setEnabled(false);
				btnInsert.setEnabled(false);
				btnReturnMoney.setEnabled(false);

				// then set all the txtField and screen are null
				txtItemnumber.setText(null);
				txtItemweight.setText(null);
				txtrOutput.setText("Welcome to RCM " + RcmNum);
				txtrOutput02
						.setText("---------------Welcome !---------------");

				// generate a new RCM
				rcm = new RCM(RcmNum);
				count = 0;
				curMessage = "";

			}

			// if the user input is not in DB, then display. : (
			else {
				txtrOutput.setText("Please input a valid RCM number! ");

				// if use switch a RCM, but it's a invalid RcmNum
				btnStart.setEnabled(false);
				btnContinue.setEnabled(false);
				btnEnd.setEnabled(false);
				txtItemnumber.setEnabled(false);
				txtItemweight.setEnabled(false);
				btnInsert.setEnabled(false);
				btnReturnMoney.setEnabled(false);
			}
		}

		// ******** implement each action ***********
		if (source == btnStart) {
			// change the status
			txtItemnumber.setEnabled(true);
			txtItemweight.setEnabled(true);
			btnInsert.setEnabled(true);

			// tell the user which item is acceptable
			try {
				// screen display
				String temp = "";
				temp = " ****** These are acceptable items list ******" + "\n"
						+ RmosDB.itemsListDB()
						+ "\nPlease Input an Acceptable Item Number: ";

				// display something on screen
				txtrOutput02
						.setText("---------------Welcome !---------------");
				txtrOutput.setText(temp);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		// *********** Insert Action ***************

		/*
		 * first, judge if it is a valid item number second, judge if RCM is
		 * full third, insert
		 */

		if (source == btnInsert) {
			// get itemNum and itemWeight from the user
			int itemNum = Integer.parseInt(txtItemnumber.getText());
			double itemWeight = Double.parseDouble(txtItemweight.getText());

			// judge if it is an acceptable item first
			boolean hasItemNum = false;
			boolean isFull = true;

			try {
				hasItemNum = RmosDB.existItemNum(itemNum);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// if user input is valid, item is acceptable
			if (hasItemNum) {
				// set them enabled
				btnContinue.setEnabled(true);
				btnEnd.setEnabled(true);

				// create a RCM

				// judge if the machine is full
				try {
					isFull = rcm.isFull(rcm.getRCMNum(), itemWeight);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// if this RCM is not full, then insert item and calculate
				if (!isFull) {
					try {
						// display some messages
						curMessage += rcm.calItem(itemNum, itemWeight)
								+ "-----Do you want to continue or end?-----\n\n";

						// should add value into RCM and store into DB

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// after clicking insert button, setup 0
					txtrOutput02.setText(curMessage);
					txtItemnumber.setText(null);
					txtItemweight.setText(null);
					txtItemnumber.setEnabled(false);
					txtItemweight.setEnabled(false);
					btnInsert.setEnabled(false);
					btnStart.setEnabled(false);
				}

				else {
					String curM = "This RCM is Full ! \n"
							+ "Sorry, RCM will return your previous items\n";

					txtrOutput02.setText(curM);

					btnStart.setEnabled(false);
					btnContinue.setEnabled(false);
					btnEnd.setEnabled(false);
					txtItemnumber.setEnabled(false);
					txtItemweight.setEnabled(false);
					txtItemnumber.setText(null);
					txtItemweight.setText(null);
					btnInsert.setEnabled(false);
					btnReturnMoney.setEnabled(false);
					btnReturnCoupon.setEnabled(false);

				}
			}

			// if user input is not valid, then display something
			else {
				String temp = "";
				try {
					temp = " ****** These are acceptable items list ******"
							+ "\n" + RmosDB.itemsListDB()
							+ "\nPlease Input an Acceptable Item Number: ";

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// display something on screen
				txtrOutput.setText(temp);
				txtrOutput02.setText("Please Input an Acceptable Item Number");
				txtItemnumber.setText(null);
				txtItemweight.setText(null);
			}

		}

		// ******* continue action *********
		if (source == btnContinue) {
			// setup some functions
			txtItemnumber.setEnabled(true);
			txtItemweight.setEnabled(true);
			btnInsert.setEnabled(true);
		}

		// *********** end action ***********
		if (source == btnEnd) {

			try {
				// add total weight into curCapacity in DB
				rcm.endSession(rcm.getRCMNum());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// local variable
			double totalMoney = 0;
			totalMoney = rcm.getTotalMoney();

			String mesg = "";
			mesg = "Your current money is: " + String.valueOf(totalMoney)
					+ "\nDo you want cash or coupon?\n";
			txtrOutput02.setText(mesg);

			// some preparing actions
			btnStart.setEnabled(false);
			btnContinue.setEnabled(false);
			btnEnd.setEnabled(false);
			txtItemnumber.setEnabled(false);
			txtItemweight.setEnabled(false);
			btnInsert.setEnabled(false);
			txtItemnumber.setText(null);
			txtItemweight.setText(null);
			btnReturnMoney.setEnabled(true);
			btnReturnCoupon.setEnabled(true);

			/*
			 * when finish a successful action, count ++ the most count will be
			 * treated as the busiest one Also, every month, the count will be
			 * set to zero.
			 */
			try {
				RmosDB.setMonthlyUse(rcm.getRCMNum());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			/*
			 * when current capacity = 90% * CAPACITY, RMOS will display a
			 * warning message when current capacity = 95% * CAPACITY, RMOS will
			 * know which one and clear by manager
			 * 
			 * Check current capacity, when clicked 'End' button
			 */

			// check curCapacity
			try {
				double curCap = RmosDB.getCurCapacity(rcm.getRCMNum());
				int tempNum = rcm.getRCMNum();

				if (curCap > RCM.getCapacity() * 0.9) {

					// turn ORANGE on RMOS panel, RCM is almost full
					switch (tempNum) {

					case 1:
						RMOSView.lblRcm01.setForeground(Color.PINK);
						RMOSView.txtrOutput.setText("RCM 1 is almost full !!!");
						break;
					case 2:
						RMOSView.lblRcm02.setForeground(Color.PINK);
						RMOSView.txtrOutput.setText("RCM 2 is almost full !!!");
						break;
					case 3:
						RMOSView.lblRcm03.setForeground(Color.PINK);
						RMOSView.txtrOutput.setText("RCM 3 is almost full !!!");
						break;
					case 4:
						RMOSView.lblRcm04.setForeground(Color.PINK);
						RMOSView.txtrOutput.setText("RCM 4 is almost full !!!");
						break;
					case 5:
						RMOSView.lblRcm05.setForeground(Color.PINK);
						RMOSView.txtrOutput.setText("RCM 5 is almost full !!!");
						break;
					case 6:
						RMOSView.lblRcm06.setForeground(Color.PINK);
						RMOSView.txtrOutput.setText("RCM 6 is almost full !!!");
						break;
					case 7:
						RMOSView.lblRcm07.setForeground(Color.PINK);
						RMOSView.txtrOutput.setText("RCM 7 is almost full !!!");
						break;
					case 8:
						RMOSView.lblRcm08.setForeground(Color.PINK);
						RMOSView.txtrOutput.setText("RCM 8 is almost full !!!");
						break;
					case 9:
						RMOSView.lblRcm09.setForeground(Color.PINK);
						RMOSView.txtrOutput.setText("RCM 9 is almost full !!!");
						break;
					case 10:
						RMOSView.lblRcm10.setForeground(Color.PINK);
						RMOSView.txtrOutput.setText("RCM 10 is almost full !!");
						break;
					default:
						break;
					}

					/*
					 * RMOS VIEW will show something or do something Maybe the
					 * icon will change and flash its color and Administrator
					 * will clear the CurW for the RCM But here, due to the
					 * final I won't do that. // :~
					 */
				}
				if (curCap > RCM.getCapacity() * 0.95) {
					// RMOS will know on screen, and clear RCM manually
					// RMOS will clear curCap, and add them to monthlyWeight
					String msg = "";

					// set up message
					msg = "RCM " + rcm.getRCMNum() + " Should be Emptied !";
					// display
					RMOSView.txtrOutput.setText(msg);

					// RMOS will do it.
					// RmosDB.setMonthlyWeight(rcm.getRCMNum(),
					// RmosDB.getCurCapacity(rcm.getRCMNum()));
					// RmosDB.clearCurCap(rcm.getRCMNum());
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// ******** return money action *******
		if (source == btnReturnMoney) {
			// judge if the RCM has enough cash first
			// HERE ********************
			double totalMoney = rcm.getTotalMoney();
			double balanceInDB = 0;
			try {
				balanceInDB = RmosDB.getBalanceInDB(rcm.getRCMNum());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// if there is enough money
			if (totalMoney < balanceInDB) {

				double cash = balanceInDB - totalMoney;
				try {
					RmosDB.setCurBalanceInDB(rcm.getRCMNum(), cash);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String msg = "Here is your cash: $ " + totalMoney;
				txtrOutput02.setText(msg);
			}

			// if there is not enough money
			else {
				String msg = "Sorry, we do not have enough cash\n"
						+ "Please take the coupon, Thank you! \n";

				txtrOutput02.setText(msg);

				/*
				 * Also, RCM can report RMOS that this RCM does not have enough
				 * cash And RMOS will set up :~
				 */
			}

			// set up a group of buttons
			btnStart.setEnabled(false);
			btnContinue.setEnabled(false);
			btnEnd.setEnabled(false);
			txtItemnumber.setEnabled(false);
			txtItemweight.setEnabled(false);
			btnInsert.setEnabled(false);
			txtItemnumber.setText(null);
			txtItemweight.setText(null);
			btnReturnMoney.setEnabled(false);
			btnReturnCoupon.setEnabled(false);
		}

		// ******* return money action ********
		if (source == btnReturnCoupon) {
			txtrOutput
					.setText("--------------Welcome !--------------");
			txtrOutput02.setText("Here is your coupon, Thank you!");

			btnStart.setEnabled(false);
			btnContinue.setEnabled(false);
			btnEnd.setEnabled(false);
			txtItemnumber.setEnabled(false);
			txtItemweight.setEnabled(false);
			btnInsert.setEnabled(false);
			txtItemnumber.setText(null);
			txtItemweight.setText(null);
			btnReturnMoney.setEnabled(false);
			btnReturnCoupon.setEnabled(false);
		}
	}
}
