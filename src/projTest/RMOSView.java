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

package projTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.IconUIResource;

import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JButton;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Arc2D;
import java.awt.SystemColor;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Font;
import java.awt.Canvas;
import javax.swing.ImageIcon;

public class RMOSView extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private RCMModel rcm = new RCMModel();
	private RMOS rmos = new RMOS();

	private JPanel contentPane;
	private JTextField txtRcmNum;
	private JTextField txtRcmId;
	private JTextField txtLocation;
	private JTextField txtRcmNumRem;
	private JTextField txtRcmNumAct;
	private JTextField txtItemNum;
	private JTextField txtUniteprice;
	private JTextField txtItemtype;
	private JTextField txtItemNum_1;
	private JTextField txtItemnum;
	private JTextField txtNewPrice;
	private JTextField txtRcmNum_2;
	private JTextField txtRcmNum_3;
	private JTextField txtRcmNum_4;

	static JLabel lblRcm01, lblRcm02, lblRcm03, lblRcm04, lblRcm05, lblRcm06,
			lblRcm07, lblRcm08, lblRcm09, lblRcm10;

	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnActivate;
	private JButton btnItemAdd;
	private JButton btnItemRemove;
	private JButton btnChange;
	private JButton btnShowList;
	static JTextArea txtrOutput;
	private JButton btnClearScreen;
	private JButton btnShowRcmList;
	private JButton btnCheckMoney;
	private JButton btnCurrentCapacity;
	private JButton btnMonthlyWeight;
	private JButton btnGetMostBusy;
	private JButton btnRefillMoney;
	private JButton btnClearCapacity;
	private JPanel panel;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JTextField txtRcmnum;
	private JButton btnMonthlyMoney;
	private JButton btnShowGraph;
	private JButton btnLogin;
	private JButton btnMonthlyUsageTimes;
	private JButton btnMonthlyWeightStatistics;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// View frame = new View();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */

	// constructor starts!
	public RMOSView() {
		super("RMOS");
		setForeground(new Color(0, 51, 204));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 825);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel LeftPanel = new JPanel();
		LeftPanel.setBackground(new Color(255, 255, 0));
		LeftPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "RCM Controller",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 51,
						204)));
		contentPane.add(LeftPanel);
		LeftPanel.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		panel.setBounds(6, 24, 517, 38);
		LeftPanel.add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblUsername.setForeground(new Color(0, 51, 255));
		lblUsername.setBackground(new Color(0, 204, 153));
		lblUsername.setBounds(6, 9, 95, 22);
		panel.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setBounds(91, 6, 100, 28);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPassword.setForeground(new Color(0, 51, 255));
		lblPassword.setBounds(203, 9, 75, 22);
		panel.add(lblPassword);

		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(279, 6, 100, 28);
		panel.add(pwdPassword);

		btnLogin = new JButton("LogIn");
		btnLogin.setForeground(new Color(204, 0, 0));
		btnLogin.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnLogin.setBounds(391, 7, 117, 29);
		btnLogin.addActionListener((ActionListener) this);
		panel.add(btnLogin);

		JPanel RCMController = new JPanel();
		RCMController.setBounds(6, 74, 517, 155);
		RCMController.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "RCM Status",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 51,
						204)));
		RCMController.setBackground(new Color(255, 255, 102));
		LeftPanel.add(RCMController);
		RCMController.setLayout(new GridLayout(2, 5, 15, 10));

		// these are 10 RCM icons, red means disconnection, green means activate
		lblRcm01 = new JLabel("RCM01");
		lblRcm01.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcm01.setForeground(new Color(0, 51, 255));
		RCMController.add(lblRcm01);

		lblRcm02 = new JLabel("RCM02");
		lblRcm02.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcm02.setForeground(new Color(0, 51, 255));
		RCMController.add(lblRcm02);

		lblRcm03 = new JLabel("RCM03");
		lblRcm03.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcm03.setForeground(new Color(0, 51, 255));
		RCMController.add(lblRcm03);

		lblRcm04 = new JLabel("RCM04");
		lblRcm04.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcm04.setForeground(new Color(0, 51, 255));
		RCMController.add(lblRcm04);

		lblRcm05 = new JLabel("RCM05");
		lblRcm05.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcm05.setForeground(new Color(0, 51, 255));
		RCMController.add(lblRcm05);

		lblRcm06 = new JLabel("RCM06");
		lblRcm06.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcm06.setForeground(new Color(0, 51, 255));
		RCMController.add(lblRcm06);

		lblRcm07 = new JLabel("RCM07");
		lblRcm07.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcm07.setForeground(new Color(0, 51, 255));
		RCMController.add(lblRcm07);

		lblRcm08 = new JLabel("RCM08");
		lblRcm08.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcm08.setForeground(new Color(0, 51, 255));
		RCMController.add(lblRcm08);

		lblRcm09 = new JLabel("RCM09");
		lblRcm09.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcm09.setForeground(new Color(0, 51, 255));
		RCMController.add(lblRcm09);

		lblRcm10 = new JLabel("RCM10");
		lblRcm10.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcm10.setForeground(new Color(0, 51, 255));
		RCMController.add(lblRcm10);

		// ************add/remove/active a RCM panel***************
		JPanel Operations = new JPanel();
		Operations.setBounds(6, 241, 517, 234);
		Operations.setBackground(new Color(255, 255, 102));
		Operations.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Operations",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 51,
						204)));
		LeftPanel.add(Operations);
		Operations.setLayout(null);

		// add action*******************
		JLabel lblRcmNum = new JLabel("RCM Num");
		lblRcmNum.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcmNum.setForeground(new Color(0, 51, 255));
		lblRcmNum.setBounds(48, 33, 73, 29);
		Operations.add(lblRcmNum);

		txtRcmNum = new JTextField();
		txtRcmNum.setBounds(146, 33, 35, 29);
		Operations.add(txtRcmNum);
		txtRcmNum.setColumns(10);

		JLabel lblRcmId = new JLabel("RCM ID");
		lblRcmId.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcmId.setForeground(new Color(0, 51, 255));
		lblRcmId.setBounds(286, 33, 73, 29);
		Operations.add(lblRcmId);

		txtRcmId = new JTextField();
		txtRcmId.setBounds(354, 33, 112, 29);
		Operations.add(txtRcmId);
		txtRcmId.setColumns(10);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblLocation.setForeground(new Color(0, 51, 255));
		lblLocation.setBounds(48, 62, 86, 29);
		Operations.add(lblLocation);

		txtLocation = new JTextField();
		txtLocation.setBounds(146, 62, 134, 29);
		Operations.add(txtLocation);
		txtLocation.setColumns(10);

		// add button, should be listened
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnAdd.setForeground(new Color(204, 0, 0));
		btnAdd.setBounds(280, 62, 207, 29);
		btnAdd.addActionListener((ActionListener) this);
		Operations.add(btnAdd);

		// remove action*****************
		JLabel lblRcmNum_1 = new JLabel("RCM Num");
		lblRcmNum_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcmNum_1.setForeground(new Color(0, 51, 255));
		lblRcmNum_1.setBounds(48, 91, 73, 29);
		Operations.add(lblRcmNum_1);

		txtRcmNumRem = new JTextField();
		txtRcmNumRem.setBounds(146, 91, 35, 29);
		Operations.add(txtRcmNumRem);
		txtRcmNumRem.setColumns(10);

		// remove button, should be listened
		btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnRemove.setForeground(new Color(204, 0, 0));
		btnRemove.setBounds(280, 91, 207, 29);
		btnRemove.addActionListener((ActionListener) this);
		Operations.add(btnRemove);

		// activate action***************
		JLabel lblRcmNum_2 = new JLabel("RCM Num");
		lblRcmNum_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcmNum_2.setForeground(new Color(0, 51, 255));
		lblRcmNum_2.setBounds(48, 120, 73, 29);
		Operations.add(lblRcmNum_2);

		txtRcmNumAct = new JTextField();
		txtRcmNumAct.setBounds(146, 120, 35, 29);
		Operations.add(txtRcmNumAct);
		txtRcmNumAct.setColumns(10);

		// activate button, should be listened
		btnActivate = new JButton("Activate");
		btnActivate.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnActivate.setForeground(new Color(204, 0, 0));
		btnActivate.setBounds(280, 120, 207, 29);
		btnActivate.addActionListener((ActionListener) this);
		Operations.add(btnActivate);

		btnShowRcmList = new JButton("Show RCM List");
		btnShowRcmList.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnShowRcmList.setForeground(new Color(204, 0, 0));
		btnShowRcmList.setBounds(12, 154, 475, 29);
		btnShowRcmList.addActionListener((ActionListener) this);
		Operations.add(btnShowRcmList);

		btnRefillMoney = new JButton("Refill Cash");
		btnRefillMoney.setForeground(new Color(204, 0, 0));
		btnRefillMoney.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnRefillMoney.setBounds(179, 184, 150, 29);
		btnRefillMoney.addActionListener((ActionListener) this);
		Operations.add(btnRefillMoney);

		JLabel lblRcmNum_7 = new JLabel("RCM Num");
		lblRcmNum_7.setForeground(new Color(0, 51, 255));
		lblRcmNum_7.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcmNum_7.setBounds(48, 183, 73, 29);
		Operations.add(lblRcmNum_7);

		txtRcmnum = new JTextField();
		txtRcmnum.setBounds(132, 183, 35, 28);
		Operations.add(txtRcmnum);
		txtRcmnum.setColumns(10);

		btnClearCapacity = new JButton("Clear Capacity");
		btnClearCapacity.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnClearCapacity.setForeground(new Color(204, 0, 0));
		btnClearCapacity.setBounds(341, 184, 146, 29);
		btnClearCapacity.addActionListener((ActionListener) this);
		Operations.add(btnClearCapacity);

		// ********************items controller panel***************
		JPanel ItemsController = new JPanel();
		ItemsController.setBounds(6, 487, 517, 300);
		ItemsController.setForeground(new Color(51, 102, 204));
		ItemsController.setBackground(new Color(255, 255, 51));
		ItemsController.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Items Controller",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 51,
						204)));
		LeftPanel.add(ItemsController);
		ItemsController.setLayout(null);
		// /////////
		// add action *******************
		JLabel lblItemNum = new JLabel("Item Num");
		lblItemNum.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblItemNum.setForeground(new Color(0, 51, 255));
		lblItemNum.setBounds(48, 38, 75, 29);
		ItemsController.add(lblItemNum);

		txtItemNum = new JTextField();
		txtItemNum.setBounds(159, 38, 39, 29);
		ItemsController.add(txtItemNum);
		txtItemNum.setColumns(10);

		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblUnitPrice.setForeground(new Color(0, 51, 255));
		lblUnitPrice.setBounds(291, 38, 84, 29);
		ItemsController.add(lblUnitPrice);

		txtUniteprice = new JTextField();
		txtUniteprice.setBounds(378, 38, 92, 29);
		ItemsController.add(txtUniteprice);
		txtUniteprice.setColumns(10);

		// add button, should be listened
		btnItemAdd = new JButton("Add");
		btnItemAdd.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnItemAdd.setBackground(new Color(0, 204, 255));
		btnItemAdd.setForeground(new Color(204, 0, 0));
		btnItemAdd.setBounds(282, 65, 199, 29);
		btnItemAdd.addActionListener((ActionListener) this);

		JLabel lblItemType = new JLabel("Item Type");
		lblItemType.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblItemType.setForeground(new Color(0, 51, 255));
		lblItemType.setBounds(48, 64, 75, 29);
		ItemsController.add(lblItemType);

		txtItemtype = new JTextField();
		txtItemtype.setBounds(159, 64, 98, 29);
		ItemsController.add(txtItemtype);
		txtItemtype.setColumns(10);
		ItemsController.add(btnItemAdd);

		// remove action ****************
		JLabel lblItemNum_1 = new JLabel("Item Num");
		lblItemNum_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblItemNum_1.setForeground(new Color(0, 51, 255));
		lblItemNum_1.setBounds(48, 117, 75, 29);
		ItemsController.add(lblItemNum_1);

		txtItemNum_1 = new JTextField();
		txtItemNum_1.setBounds(159, 117, 39, 29);
		ItemsController.add(txtItemNum_1);
		txtItemNum_1.setColumns(10);

		// remove button, should be listened
		btnItemRemove = new JButton("Remove");
		btnItemRemove.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnItemRemove.setForeground(new Color(204, 0, 0));
		btnItemRemove.setBounds(282, 118, 199, 29);
		btnItemRemove.addActionListener((ActionListener) this);
		ItemsController.add(btnItemRemove);

		// change action ****************
		JLabel lblItemNum_2 = new JLabel("Item Num");
		lblItemNum_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblItemNum_2.setForeground(new Color(0, 51, 255));
		lblItemNum_2.setBounds(48, 171, 75, 29);
		ItemsController.add(lblItemNum_2);

		txtItemnum = new JTextField();
		txtItemnum.setBounds(159, 171, 39, 29);
		ItemsController.add(txtItemnum);
		txtItemnum.setColumns(10);

		JLabel lblNewPrice = new JLabel("New Price");
		lblNewPrice.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewPrice.setForeground(new Color(0, 51, 255));
		lblNewPrice.setBounds(291, 171, 75, 29);
		ItemsController.add(lblNewPrice);

		txtNewPrice = new JTextField();
		txtNewPrice.setBounds(378, 171, 92, 29);
		ItemsController.add(txtNewPrice);
		txtNewPrice.setColumns(10);

		// change button, should be listened
		btnChange = new JButton("Change");
		btnChange.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnChange.setForeground(new Color(204, 0, 0));
		btnChange.setBounds(12, 200, 469, 29);
		btnChange.addActionListener((ActionListener) this);
		ItemsController.add(btnChange);

		// show item list button ***********
		btnShowList = new JButton("Show Items List");
		btnShowList.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnShowList.setForeground(new Color(204, 0, 0));
		btnShowList.setBounds(12, 251, 219, 29);
		btnShowList.addActionListener((ActionListener) this);
		ItemsController.add(btnShowList);

		// clear screen button ********
		btnClearScreen = new JButton("Clear Screen");
		btnClearScreen.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnClearScreen.setForeground(new Color(204, 0, 0));
		btnClearScreen.setBounds(262, 251, 219, 29);
		btnClearScreen.addActionListener((ActionListener) this);
		ItemsController.add(btnClearScreen);

		// ******************* Left panel is Done ***************

		// *********************Right Panel starts******************
		JPanel RightPanel = new JPanel();
		RightPanel.setBackground(new Color(255, 255, 0));
		RightPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Display",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 51,
						204)));
		contentPane.add(RightPanel);
		RightPanel.setLayout(null);

		JPanel CheckStatus = new JPanel();
		CheckStatus.setBackground(new Color(255, 255, 51));
		CheckStatus.setBounds(6, 18, 533, 265);
		CheckStatus.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Check Status",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 51,
						204)));
		RightPanel.add(CheckStatus);
		CheckStatus.setLayout(null);

		JLabel lblRcmNum_3 = new JLabel("RCM Num");
		lblRcmNum_3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcmNum_3.setForeground(new Color(0, 51, 255));
		lblRcmNum_3.setBounds(21, 22, 87, 27);
		lblRcmNum_3.setHorizontalAlignment(SwingConstants.CENTER);
		CheckStatus.add(lblRcmNum_3);

		txtRcmNum_2 = new JTextField();
		txtRcmNum_2.setBounds(103, 21, 28, 27);
		CheckStatus.add(txtRcmNum_2);
		txtRcmNum_2.setColumns(10);

		btnCheckMoney = new JButton("Current Cash");
		btnCheckMoney.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnCheckMoney.setForeground(new Color(204, 0, 0));
		btnCheckMoney.setBounds(143, 19, 113, 34);
		btnCheckMoney.addActionListener((ActionListener) this);
		CheckStatus.add(btnCheckMoney);

		JLabel lblRcmNum_4 = new JLabel("RCM Num");
		lblRcmNum_4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcmNum_4.setForeground(new Color(0, 51, 255));
		lblRcmNum_4.setBounds(268, 25, 87, 21);
		lblRcmNum_4.setHorizontalAlignment(SwingConstants.CENTER);
		CheckStatus.add(lblRcmNum_4);

		txtRcmNum_3 = new JTextField();
		txtRcmNum_3.setBounds(354, 21, 28, 27);
		CheckStatus.add(txtRcmNum_3);
		txtRcmNum_3.setColumns(10);

		btnCurrentCapacity = new JButton("Current Capacity");
		btnCurrentCapacity.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnCurrentCapacity.setForeground(new Color(204, 0, 0));
		btnCurrentCapacity.setBounds(394, 19, 120, 34);
		btnCurrentCapacity.addActionListener((ActionListener) this);
		CheckStatus.add(btnCurrentCapacity);

		JLabel lblRcmNum_5 = new JLabel("RCM Num");
		lblRcmNum_5.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRcmNum_5.setForeground(new Color(0, 51, 255));
		lblRcmNum_5.setBounds(21, 58, 87, 27);
		lblRcmNum_5.setHorizontalAlignment(SwingConstants.CENTER);
		CheckStatus.add(lblRcmNum_5);

		txtRcmNum_4 = new JTextField();
		txtRcmNum_4.setBounds(103, 61, 28, 21);
		CheckStatus.add(txtRcmNum_4);
		txtRcmNum_4.setColumns(10);

		btnMonthlyWeight = new JButton("Monthly Weight");
		btnMonthlyWeight.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnMonthlyWeight.setForeground(new Color(204, 0, 0));
		btnMonthlyWeight.setBounds(143, 55, 117, 34);
		btnMonthlyWeight.addActionListener((ActionListener) this);
		CheckStatus.add(btnMonthlyWeight);

		btnGetMostBusy = new JButton("Get Most Busy RCM");
		btnGetMostBusy.setIcon(new ImageIcon("/Users/hongsenhe/Documents/AllEclipseFiles/EcoRecycle/icons/Apps-java-icon.png"));
		btnGetMostBusy.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnGetMostBusy.setForeground(new Color(204, 0, 0));
		btnGetMostBusy.setBounds(21, 97, 255, 149);
		btnGetMostBusy.addActionListener((ActionListener) this);
		CheckStatus.add(btnGetMostBusy);

		btnShowGraph = new JButton("Monthly Cash Issued Graph");
		btnShowGraph.setHorizontalAlignment(SwingConstants.LEFT);
		btnShowGraph.setIcon(new ImageIcon("/Users/hongsenhe/Documents/AllEclipseFiles/EcoRecycle/icons/Blip-icon.png"));
		btnShowGraph.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnShowGraph.setForeground(new Color(204, 0, 0));
		btnShowGraph.setBounds(288, 97, 226, 45);
		btnShowGraph.addActionListener((ActionListener) this);
		CheckStatus.add(btnShowGraph);

		btnMonthlyUsageTimes = new JButton("Monthly Usage Times Graph");
		btnMonthlyUsageTimes.setHorizontalAlignment(SwingConstants.LEFT);
		btnMonthlyUsageTimes.setIcon(new ImageIcon("/Users/hongsenhe/Documents/AllEclipseFiles/EcoRecycle/icons/Blip-icon.png"));
		btnMonthlyUsageTimes.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnMonthlyUsageTimes.setForeground(new Color(204, 0, 0));
		btnMonthlyUsageTimes.setBounds(288, 201, 226, 45);
		btnMonthlyUsageTimes.addActionListener((ActionListener) this);
		CheckStatus.add(btnMonthlyUsageTimes);

		btnMonthlyWeightStatistics = new JButton("Monthly Weight Statistics Graph");
		btnMonthlyWeightStatistics.setHorizontalAlignment(SwingConstants.LEFT);
		btnMonthlyWeightStatistics.setIcon(new ImageIcon("/Users/hongsenhe/Documents/AllEclipseFiles/EcoRecycle/icons/Blip-icon.png"));
		btnMonthlyWeightStatistics.setFont(new Font("Apple LiGothic",
				Font.BOLD, 15));
		btnMonthlyWeightStatistics.setForeground(new Color(204, 0, 0));
		btnMonthlyWeightStatistics.setBounds(288, 149, 226, 45);
		btnMonthlyWeightStatistics.addActionListener((ActionListener) this);
		CheckStatus.add(btnMonthlyWeightStatistics);

		JPanel Screen = new JPanel();
		Screen.setBackground(new Color(255, 255, 102));
		Screen.setForeground(new Color(255, 255, 102));
		Screen.setBounds(0, 295, 539, 492);
		Screen.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Screen",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 51,
						204)));
		RightPanel.add(Screen);
		Screen.setLayout(null);

		txtrOutput = new JTextArea();
		txtrOutput.setBounds(6, 26, 527, 488);
		txtrOutput.setEditable(false);
		txtrOutput.setBackground(new Color(255, 255, 102));
		txtrOutput.setForeground(new Color(0, 51, 255));
		txtrOutput.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		txtrOutput.setText("OutPut...\n");
		Screen.add(txtrOutput);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// store command from event e
		Object source = e.getSource();

		// so many if-else to judge which event is being used
		// ******* From Left Panel ********

		// *********** Operations Panel ******************
		// ******* Add a RCM into RMOS group ********
		if (source == btnAdd) {
			int RcmNum = Integer.parseInt(txtRcmNum.getText());
			int RcmID = Integer.parseInt(txtRcmId.getText());
			String RcmLoc = txtLocation.getText();

			// create a new RCM and store into RMOS list
			RCM aMachine = new RCM(RcmNum, RcmID, RcmLoc);
			rmos.addRCM(aMachine);

			// insert this RCM into database
			RmosDB.addRCM(aMachine);

			// set display on RMOS panel
			txtrOutput.setText(rmos.displayRCM());

			// after adding, color turn to red from CYAN (default color)
			// after activating, color turn to green, color turn to red when
			// remove
			switch (RcmNum) {
			case 1:
				lblRcm01.setForeground(Color.RED);
				break;
			case 2:
				lblRcm02.setForeground(Color.RED);
				break;
			case 3:
				lblRcm03.setForeground(Color.RED);
				break;
			case 4:
				lblRcm04.setForeground(Color.RED);
				break;
			case 5:
				lblRcm05.setForeground(Color.RED);
				break;
			case 6:
				lblRcm06.setForeground(Color.RED);
				break;
			case 7:
				lblRcm07.setForeground(Color.RED);
				break;
			case 8:
				lblRcm08.setForeground(Color.RED);
				break;
			case 9:
				lblRcm09.setForeground(Color.RED);
				break;
			case 10:
				lblRcm10.setForeground(Color.RED);
				break;
			default:
				break;
			}

		}

		// ********* Remove a RCM from RMOS group *********
		else if (source == btnRemove) {
			int RcmNum = Integer.parseInt(txtRcmNumRem.getText());
			rmos.removeRCM(RcmNum);
			txtrOutput.setText(rmos.displayRCM());

			// remove the RCM from database
			RmosDB.removeRCM(RcmNum);

			// turn Red, if a RCM is removed
			switch (RcmNum) {
			case 1:
				lblRcm01.setForeground(new Color(0, 51, 255));
				break;
			case 2:
				lblRcm02.setForeground(new Color(0, 51, 255));
				break;
			case 3:
				lblRcm03.setForeground(new Color(0, 51, 255));
				break;
			case 4:
				lblRcm04.setForeground(new Color(0, 51, 255));
				break;
			case 5:
				lblRcm05.setForeground(new Color(0, 51, 255));
				break;
			case 6:
				lblRcm06.setForeground(new Color(0, 51, 255));
				break;
			case 7:
				lblRcm07.setForeground(new Color(0, 51, 255));
				break;
			case 8:
				lblRcm08.setForeground(new Color(0, 51, 255));
				break;
			case 9:
				lblRcm09.setForeground(new Color(0, 51, 255));
				break;
			case 10:
				lblRcm10.setForeground(new Color(0, 51, 255));
				break;
			default:
				break;
			}
		}

		// ********** Activate a RCM ****************
		else if (source == btnActivate) {
			// get RCM number
			int RcmNum = Integer.parseInt(txtRcmNumAct.getText());
			// make sure which RCM number
			int tempNum = rmos.activeRCM(RcmNum);

			// activate the RCM
			try {
				RmosDB.activateRCM(tempNum);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// turn green color if it is activated
			switch (tempNum) {
			case 1:
				lblRcm01.setForeground(Color.GREEN);
				break;
			case 2:
				lblRcm02.setForeground(Color.GREEN);
				break;
			case 3:
				lblRcm03.setForeground(Color.GREEN);
				break;
			case 4:
				lblRcm04.setForeground(Color.GREEN);
				break;
			case 5:
				lblRcm05.setForeground(Color.GREEN);
				break;
			case 6:
				lblRcm06.setForeground(Color.GREEN);
				break;
			case 7:
				lblRcm07.setForeground(Color.GREEN);
				break;
			case 8:
				lblRcm08.setForeground(Color.GREEN);
				break;
			case 9:
				lblRcm09.setForeground(Color.GREEN);
				break;
			case 10:
				lblRcm10.setForeground(Color.GREEN);
				break;
			default:
				break;
			}
		}

		// ********* show RCM information **************
		else if (source == btnShowRcmList) {
			txtrOutput.setText(rmos.displayRCM());

		}

		// *********** Refill Money ******************
		else if (source == btnRefillMoney) {
			// get RCM number
			int rcmNum = Integer.parseInt(txtRcmnum.getText());
			double curMoney = 0;

			// get current money
			try {
				curMoney = RmosDB.getBalanceInDB(rcmNum);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			double monthlyMoney = 0;
			// store earning money such as 50 - 2(bal)
			// monthlyMoney = 48
			monthlyMoney = RmosDB.defBalance - curMoney;

			// update monthly money in DB
			try {
				RmosDB.setMonthlyIssued(rcmNum,
						(monthlyMoney + RmosDB.getMonthlyIssued(rcmNum)));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// clear curMoney
			try {
				RmosDB.setCurBalanceInDB(rcmNum, RmosDB.defBalance);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String msg = "";
			// display message
			try {
				txtrOutput.setText("Now RCM " + rcmNum + " has $ "
						+ RmosDB.getMonthlyIssued(rcmNum)
						+ " in Monthly Paid\n"
						+ "And its Current Balance is $ "
						+ RmosDB.getBalanceInDB(rcmNum));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		// ********** Clear RCM *******************
		else if (source == btnClearCapacity) {
			// load RCM number
			int rcmNum = Integer.parseInt(txtRcmnum.getText());

			// clear the RCM
			try {
				RmosDB.setMonthlyWeight(rcmNum, RmosDB.getCurCapacity(rcmNum));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				RmosDB.clearCurCap(rcmNum);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// change its color from orange to green 
			int tempNum = rcmNum;
			
			switch (tempNum) {
			case 1:
				lblRcm01.setForeground(Color.GREEN);
				break;
			case 2:
				lblRcm02.setForeground(Color.GREEN);
				break;
			case 3:
				lblRcm03.setForeground(Color.GREEN);
				break;
			case 4:
				lblRcm04.setForeground(Color.GREEN);
				break;
			case 5:
				lblRcm05.setForeground(Color.GREEN);
				break;
			case 6:
				lblRcm06.setForeground(Color.GREEN);
				break;
			case 7:
				lblRcm07.setForeground(Color.GREEN);
				break;
			case 8:
				lblRcm08.setForeground(Color.GREEN);
				break;
			case 9:
				lblRcm09.setForeground(Color.GREEN);
				break;
			case 10:
				lblRcm10.setForeground(Color.GREEN);
				break;
			default:
				break;
			}
			
			
			
			
			// display on screen
			Date empTime;
			empTime = new Date();

			String msg = "";
			msg = "RCM " + rcmNum + " has already been cleared\n"
					+ "Empty time is: " + empTime;

			txtrOutput.setText(msg);

		}

		// ****** Items Controller Panel ******************

		// *********** add an item to RCM **************
		else if (source == btnItemAdd) {
			Items item = new Items(Double.parseDouble(txtUniteprice.getText()),
					txtItemtype.getText(), Integer.parseInt(txtItemNum
							.getText()));

			try {
				// add each item into arrayList of RMOS
				rmos.addItem(item);

				// add each item into Database
				RmosDB.addItem(item);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			txtrOutput.setText(rmos.displayItemsList());
		}

		// ************* remove an item from RCM ************
		else if (source == btnItemRemove) {
			// get item number
			int itemNum = Integer.parseInt(txtItemNum_1.getText());
			rmos.removeItem(itemNum);
			txtrOutput.setText(rmos.displayItemsList());

			RmosDB.removeItem(itemNum);
		}

		// ************* change an item price ************
		else if (source == btnChange) {
			// get the item number and the new price
			int itemNum = Integer.parseInt(txtItemnum.getText());
			double itemPrice = Double.parseDouble(txtNewPrice.getText());

			rmos.change(itemNum, itemPrice);

			RmosDB.changeItem(itemNum, itemPrice);
		}

		// ************ show all items list *************
		else if (source == btnShowList) {
			txtrOutput.setText(rmos.displayItemsList());
		}

		// ************* Clear the Screen **************
		else if (source == btnClearScreen) {
			txtrOutput.setText("Output...");
		}

		// ************** Right Panel **************************

		// check money in a specific RCM
		if (source == btnCheckMoney) {
			// get RCM number
			int rcmNum = Integer.parseInt(txtRcmNum_2.getText());
			double money = 0;
			String msg = "";

			try {
				money = RmosDB.getBalanceInDB(rcmNum);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			msg = "RCM " + rcmNum + "\ncurrently has $" + money;
			txtrOutput.setText(msg);
		}

		// check current capacity
		else if (source == btnCurrentCapacity) {
			// get RCM number
			int rcmNum = Integer.parseInt(txtRcmNum_3.getText());
			double curCap = 0;
			String msg = "";

			try {
				curCap = RmosDB.getCurCapacity(rcmNum);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			msg = "RCM " + rcmNum + "\ncurrently has " + curCap + "lb.";

			// display
			txtrOutput.setText(msg);
		}

		// check monthly weight
		else if (source == btnMonthlyWeight) {
			// get RCM number
			int rcmNum = Integer.parseInt(txtRcmNum_4.getText());
			double monthlyW = 0;
			String msg = "";

			try {
				monthlyW = RmosDB.getMonthlyWeight(rcmNum);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			msg = "RCM " + rcmNum + "\nMonthly Weight is:\n" + monthlyW
					+ " lb.";

			// display
			txtrOutput.setText(msg);
		}

		// check most busy RCM
		else if (source == btnGetMostBusy) {
			// return the most busy RCM number
			String result = "";

			try {
				result = RmosDB.getBusyRCMInDB();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			txtrOutput.setText(String.valueOf(result));
		}

		// *********** Manager Log in **************
		else if (source == btnLogin) {
			String userName = txtUsername.getText();
			String passWord = "";

			char[] password = pwdPassword.getPassword();

			passWord = String.valueOf(password);

			boolean canLogIn = false;
			canLogIn = rmos.logIn(userName, passWord);

			if (canLogIn) {
				txtrOutput.setText("Log In Successful !");
			} else {
				txtrOutput
						.setText("Please input the valid username or password");
			}

		}

		// ******** Show Monthly money issued pie chart **
		else if (source == btnShowGraph) {

			// create pie object
			DefaultPieDataset cashPie = new DefaultPieDataset();

			Map<Integer, Double> cashMap = new LinkedHashMap<Integer, Double>();

			// call RmosDB method and store into local LinkedHashMap
			// all the data in cashMap
			try {
				cashMap = RmosDB.cashPieInDB();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// load cashPie, cashMap
			for (Iterator it = cashMap.keySet().iterator(); it.hasNext();) {
				// store in local variable
				Object key = it.next();
				String RcmNum = "RCM " + key;
				double moCash = 0;
				moCash = cashMap.get(key);

				// add local VAR into cashPie
				cashPie.setValue(RcmNum, moCash);
			}

			JFreeChart chart = ChartFactory
					.createPieChart("Monthly Cash Issued Pie Chart", cashPie,
							true, true, false);

			// display percentage
			PiePlot pp = (PiePlot) chart.getPlot();
			pp.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}",
					new DecimalFormat("0.0"), new DecimalFormat("0.0%")));

			ChartFrame chartFrame = new ChartFrame("Life of Pie", chart);
			chartFrame.setVisible(true);
			chartFrame.setSize(500, 500);

		}

		// show monthly usage times
		else if (source == btnMonthlyUsageTimes) {
			// create pie object
			DefaultPieDataset usagePie = new DefaultPieDataset();

			Map<Integer, Integer> usageMap = new LinkedHashMap<Integer, Integer>();

			// call RmosDB method and store into local LinkedHashMap
			// all the data in usageMap
			try {
				usageMap = RmosDB.usagePieInDB();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// load cashPie, cashMap
			for (Iterator it = usageMap.keySet().iterator(); it.hasNext();) {
				// store in local variable
				Object key = it.next();
				String RcmNum = "RCM " + key;
				double moUsage = 0;
				moUsage = usageMap.get(key);

				// add local VAR into cashPie
				usagePie.setValue(RcmNum, moUsage);
			}

			JFreeChart chart = ChartFactory.createPieChart(
					"Monthly Usage Times Pie Chart", usagePie, true, true,
					false);

			// display the actual number
			PiePlot pp2 = (PiePlot) chart.getPlot();
			pp2.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));

			ChartFrame chartFrame = new ChartFrame("Life of Pie", chart);
			chartFrame.setVisible(true);
			chartFrame.setSize(500, 500);

		}

		// show monthly weight statistics
		else if (source == btnMonthlyWeightStatistics) {
			// create pie object
			DefaultPieDataset usagePie = new DefaultPieDataset();

			Map<Integer, Double> weightMap = new LinkedHashMap<Integer, Double>();

			// call RmosDB method and store into local LinkedHashMap
			// all the data in weightMap
			try {
				weightMap = RmosDB.weightPieInDB();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// load cashPie, cashMap
			for (Iterator it = weightMap.keySet().iterator(); it.hasNext();) {
				// store in local variable
				Object key = it.next();
				String RcmNum = "RCM " + key;
				double moWeight = 0;
				moWeight = weightMap.get(key);

				// add local VAR into cashPie
				usagePie.setValue(RcmNum, moWeight);
			}

			JFreeChart chart = ChartFactory.createPieChart(
					"Monthly Weight Pie Chart", usagePie, true, true,
					false);

			// display the actual number
//			PiePlot pp2 = (PiePlot) chart.getPlot();
//			pp2.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));

			ChartFrame chartFrame = new ChartFrame("Life of Pie", chart);
			chartFrame.setVisible(true);
			chartFrame.setSize(500, 500);
		}
	}
}
