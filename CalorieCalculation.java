import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * @author Habib
 * 
 */
public class CalorieCalculation extends JFrame {
	public static int cal = 0;
	private static final long serialVersionUID = 1L;
	public static int bettary_life = 0;

	public static String bmiString = " ";
	public static double weight_pound = 0.0;
	public static double needed_calorie = 0.0;
	public static double unit_value = 0.0;
	private JCheckBox[] inputCheckBox = { new JCheckBox("Milk (Whole)"),
			new JCheckBox("White Boiled Rice"),
			new JCheckBox("Egg Fried Rice"),
			new JCheckBox("Chicken Fried Rice"),
			new JCheckBox("Beef (Average Lean)"), new JCheckBox("Egg"),
			new JCheckBox("Sugar"), new JCheckBox("Chicken (Average)"),
			new JCheckBox("Pigeon (Average)"), new JCheckBox("Honey"),
			new JCheckBox("Other per Gram: "), };

	private JCheckBox[] burnCheckBox = { new JCheckBox("Watiching TV"),
			new JCheckBox("Reading"), new JCheckBox("Sleeping"),
			new JCheckBox("Jumping Rope"), new JCheckBox("Running"),
			new JCheckBox("Swimming"), new JCheckBox("Aerobics"),
			new JCheckBox("Elliptical Trianing"), new JCheckBox("Bi Cycling"),
			new JCheckBox("Water Aerobics"),
			new JCheckBox("Other per Minute: "), };

	private JLabel inputLabel = new JLabel("Quantity you take in Gram:");

	private JLabel burnTime = new JLabel("Time you spend on this in Minute:");

	private JTextField inputText = new JTextField(10);
	private JTextField burnText = new JTextField(10);

	public CalorieCalculation(String fn, String ln, String us, String ps,
			int ht, int wt, double bmi, int d) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height, width;
		height = (int) screenSize.getHeight();
		width = (int) screenSize.getWidth();
		setTitle("Daily Calorie Calculator");

		setSize(width, height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

		weight_pound = (double) wt / 0.453592;
		needed_calorie = weight_pound * 16.00;

		unit_value = 600.00 / needed_calorie;
		cal = d;
		bettary_life = (int) (unit_value * cal);
		// Battery Calculation Panel
		Batary bettaryObject = new Batary(fn, ln, us, ps, ht, wt, bmi);
		JPanel caloriePanel = new JPanel();
		caloriePanel.setLayout(new GridLayout(0, 1, 5, 5));
		// caloriePanel.setBackground(Color.decode("#a7f276"));
		caloriePanel.setBorder(new TitledBorder("Calorie Bettary"));
		caloriePanel.add(bettaryObject);

		// battery calculation ends here...

		// Input of Calorie Panel
		JPanel inputPanel = new JPanel();
		JPanel otherInput = new JPanel();
		final JTextField othertxtInput = new JTextField();
		otherInput.setLayout(new GridLayout(0, 2, 5, 5));
		otherInput.add(inputCheckBox[inputCheckBox.length - 1]);
		otherInput.add(othertxtInput);

		inputPanel.setBorder(new TitledBorder("Input of Calorie"));
		// inputPanel.setBackground(Color.decode("#176caa"));
		inputPanel.setLayout(new GridLayout(0, 1, 5, 5));
		inputPanel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		for (int i = 0; i < inputCheckBox.length; i++) {

			if (i == inputCheckBox.length - 1)
				inputPanel.add(otherInput);
			else
				inputPanel.add(inputCheckBox[i]);

		}
		JPanel inputPanelInput = new JPanel();
		inputPanelInput.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		inputPanelInput.setLayout(new GridLayout(0, 2, 5, 5));
		// inputLabel.setBackground(Color.BLUE);
		inputPanelInput.add(inputLabel);
		inputPanelInput.add(inputText);
		inputPanel.add(inputPanelInput);

		// input panel ends here...

		// Burning panel starts here...
		JPanel burnPanel = new JPanel();
		burnPanel.setBorder(new TitledBorder("Burning of Calorie"));
		// burnPanel.setBackground(Color.decode("#a7f276"));
		burnPanel.setLayout(new GridLayout(0, 1, 5, 5));
		JPanel otherBurn = new JPanel();
		final JTextField othertxtBurn = new JTextField();
		otherBurn.setLayout(new GridLayout(0, 2, 5, 5));
		otherBurn.add(burnCheckBox[burnCheckBox.length - 1]);
		otherBurn.add(othertxtBurn);
		for (int i = 0; i < burnCheckBox.length; i++) {

			if (i == burnCheckBox.length - 1)
				burnPanel.add(otherBurn);
			else
				burnPanel.add(burnCheckBox[i]);

		}

		JPanel burnPanel2 = new JPanel();
		burnPanel2.setLayout(new GridLayout(1, 0, 5, 5));
		burnPanel2.add(burnTime);
		burnPanel2.add(burnText);
		burnPanel.add(burnPanel2);
		// Burning Panel ends here...

		JPanel cal = new JPanel();
		cal.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		cal.setBorder(new TitledBorder("Input and Burning Box"));
		cal.setLayout(new GridLayout(0, 2, 5, 5));
		cal.add(inputPanel);
		cal.add(burnPanel);
		// /Final Addition .......

		setLayout(new GridLayout(0, 1, 5, 5));
		add(caloriePanel);
		add(cal);
		for (int i = 0; i < inputCheckBox.length; i++) {
			final int p = i;
			inputCheckBox[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					switch (p) {
					case 0:
						if (inputCheckBox[p].isSelected())
							setInputBettary(60);
						break;
					case 1:
						if (inputCheckBox[p].isSelected())
							setInputBettary(151);
						break;
					case 2:
						if (inputCheckBox[p].isSelected())
							setInputBettary(210);
						break;
					case 3:
						if (inputCheckBox[p].isSelected())
							setInputBettary(220);
						break;
					case 4:
						if (inputCheckBox[p].isSelected())
							setInputBettary(304);
						break;
					case 5:
						if (inputCheckBox[p].isSelected())
							setInputBettary(147);
						break;
					case 6:
						if (inputCheckBox[p].isSelected())
							setInputBettary(387);
						break;
					case 7:
						if (inputCheckBox[p].isSelected())
							setInputBettary(140);
						break;
					case 8:
						if (inputCheckBox[p].isSelected())
							setInputBettary(250);
						break;
					case 9:
						if (inputCheckBox[p].isSelected())
							setInputBettary(304);
						break;
					case 10:
						if (inputCheckBox[p].isSelected()) {
							String string = othertxtInput.getText();
							int StringValue = Integer.parseInt(string) * 100;
							setInputBettary(StringValue);
						}
						break;
					}

				}
			});
		}

		for (int i = 0; i < burnCheckBox.length; i++) {
			final int m = i;
			burnCheckBox[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					switch (m) {
					case 0:
						if (burnCheckBox[m].isSelected())
							setBurnBettary(28);
						break;
					case 1:
						if (burnCheckBox[m].isSelected())
							setBurnBettary(42);
						break;
					case 2:
						if (burnCheckBox[m].isSelected())
							setBurnBettary(23);
						break;
					case 3:
						if (burnCheckBox[m].isSelected())
							setBurnBettary(372);
						break;
					case 4:
						if (burnCheckBox[m].isSelected())
							setBurnBettary(335);
						break;
					case 5:
						if (burnCheckBox[m].isSelected())
							setBurnBettary(223);
						break;
					case 6:
						if (burnCheckBox[m].isSelected())
							setBurnBettary(372);
						break;
					case 7:
						if (burnCheckBox[m].isSelected())
							setBurnBettary(335);
						break;
					case 8:
						if (burnCheckBox[m].isSelected())
							setBurnBettary(260);
						break;
					case 9:
						if (burnCheckBox[m].isSelected())
							setBurnBettary(149);
						break;
					case 10:
						if (burnCheckBox[m].isSelected()) {
							String string = othertxtBurn.getText();
							int StringValue = Integer.parseInt(string) * 30; //
							setBurnBettary(StringValue);
						}
						break;

					}

				}
			});
		}

	}

	class Batary extends JPanel {
		private static final long serialVersionUID = 1L;
		private String username, pass;
		private String fn, ln;
		private int ht, wt;
		private double bmi;

		public Batary(String fn, String ln, String us, String ps, int ht,
				int wt, double bmi) {
			username = us;
			pass = ps;
			this.fn = fn;
			this.ln = ln;
			this.ht = ht;
			this.wt = wt;
			this.bmi = bmi;

		}

		protected void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 60));

			g.drawRect(320, 20, 620, 200);

			int[] x = { 940, 955, 955, 940 };

			int[] y = { 90, 100, 140, 150 };
			g.fillPolygon(x, y, 4);
			if (bettary_life <= 150)
				g.setColor(Color.RED);

			else if (bettary_life > 150 && bettary_life <= 450)
				g.setColor(Color.YELLOW);
			else if (bettary_life > 450) {
				g.setColor(Color.GREEN);
			}

			if (bettary_life <= 600)
				g.fillRect(330, 30, bettary_life, 180);
			else
				g.fillRect(330, 30, 600, 180);

			g.setColor(Color.BLACK);

			g.setFont(new Font("Kristen ITC", Font.BOLD, 20));
			g.drawString("You have: " + cal + " Calories", 520, 265);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Airal", Font.BOLD, 15));
			g.drawString(fn + " " + ln + ", Hight: " + ht + " CM, Weight: "
					+ wt + " KG, " + setResultBMI(bmi), 400, 320);

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost/calorie", "root", "");
				Statement st = con.createStatement();

				st.executeUpdate("UPDATE `calorietable` SET `Calorie`= \""
						+ cal + "\" WHERE `User Name`= \"" + username
						+ "\" AND`Password`= \"" + pass + "\"");

			} catch (Exception ex) {

				JOptionPane
						.showMessageDialog(
								null,
								"Internet Connection or, Database Error. Please Contact to System Administrator.Email: habib_cse_ruet@yahoo.com");
			}

		}
	}

	public static void main(String args[]) {
		new Welcome();
	}

	void setInputBettary(int x) {
		String IQString = inputText.getText();
		int iQ = Integer.parseInt(IQString);
		bettary_life += (int) (unit_value * (double) x / 100.00 * (double) iQ);
		cal = (int) (Math.floor(bettary_life / unit_value));
		repaint();

	}

	void setBurnBettary(int x) {
		String BTString = burnText.getText();
		int BT = Integer.parseInt(BTString);
		needed_calorie = weight_pound * 16.00;
		unit_value = 600.00 / needed_calorie;

		bettary_life -= (int) (unit_value * (double) x / 30.00 * (double) BT);
		cal = (int) (Math.floor(bettary_life / unit_value));
		repaint();

	}

	static String setResultBMI(double bmi) {
		String p = "";
		if (bmi < 18.5)
			p = "BMI: " + bmi + " and Position: \"UNDERWEIGHT\".";
		else if (bmi >= 18.5 && bmi < 25)
			p = "BMI: " + bmi + " and Position:  \"NORMAL\".";
		else if (bmi >= 25 && bmi < 30)
			p = "BMI: " + bmi + " and Position:  \"OVERWEIGHT\".";
		else if (bmi >= 30 && bmi < 35)
			p = "BMI: " + bmi + " and Position: \"OBESE\".";
		else if (bmi >= 35)
			p = "BMI: " + bmi + " and Position: \"MORBIDLY OBESE\".";
		return p;

	}
}

class Welcome extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel title = new JLabel("Daily Calorie Calculator");
	private JLabel un = new JLabel("User Name:");
	private JTextField unF = new JTextField(40);
	private JLabel ps = new JLabel("Password:");
	private JTextField psF = new JTextField(40);
	private JButton lg = new JButton("Login");
	private JButton reg = new JButton("Registration");
	private JLabel newU = new JLabel("New User? Click Registration Button...");
	private JLabel regU = new JLabel(
			"Registrated User? Enter your User Name and Password...");
	private JLabel about = new JLabel("About");

	public Welcome() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height, width;
		height = (int) screenSize.getHeight();
		width = (int) screenSize.getWidth();
		setSize(width, height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Welcome to Daily Calorie Calculator");
		setVisible(true);
		setBackground(Color.decode("#276cff"));
		// setLocationRelativeTo(null);
		// setFont(new Font("Kristen ITC", Font.BOLD, 30));
		setLayout(null);
		title.setBounds(350, 80, 800, 50);
		title.setFont(new Font("Kristen ITC", Font.BOLD, 45));
		title.setForeground(Color.decode("#176caa"));
		regU.setBounds(300, 250, 600, 30);
		regU.setFont(new Font("Kristen ITC", Font.BOLD, 15));
		un.setBounds(300, 300, 100, 30);
		unF.setBounds(390, 300, 150, 30);
		ps.setBounds(560, 300, 100, 30);
		psF.setBounds(630, 300, 100, 30);
		lg.setBounds(790, 300, 200, 30);
		lg.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		lg.setBackground(Color.blue);
		lg.setForeground(Color.white);
		newU.setBounds(300, 450, 500, 30);
		newU.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		reg.setBounds(790, 450, 200, 30);
		reg.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		reg.setBackground(Color.blue);
		reg.setForeground(Color.white);
		//
		about.setFont(new Font("Consolas", Font.BOLD, 25));
		about.setBounds(650, 550, 200, 25);
		about.setForeground(Color.black);
		add(about);
		JLabel pro = new JLabel("Project: Daily Calorie Calculator");
		JLabel std = new JLabel("Student Name: Habibur Rahman");
		JLabel id = new JLabel("Student ID: 123044");
		JLabel crs = new JLabel("Course: CSE 300");
		JLabel mail = new JLabel("Email: habib_cse_ruet@yahoo.com");
		JLabel spr = new JLabel("Supervisor: Dr. Rabiul Islam");
		pro.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pro.setBounds(550, 575, 300, 30);
		spr.setFont(new Font("Times New Roman", Font.BOLD, 15));
		spr.setBounds(600, 600, 200, 20);
		std.setBounds(600, 620, 200, 20);
		id.setBounds(600, 640, 200, 20);
		crs.setBounds(600, 660, 200, 20);
		mail.setBounds(600, 680, 200, 20);
		add(pro);
		add(std);
		add(id);
		add(crs);
		add(mail);
		add(spr);

		//
		add(title);
		add(regU);
		add(un);
		add(unF);
		add(ps);
		add(psF);
		add(lg);
		add(newU);
		add(reg);
		lg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String user_name = unF.getText();
				String password = psF.getText();

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost/calorie", "root", "");
					Statement st = con.createStatement();
					ResultSet rs = st
							.executeQuery("SELECT *  FROM `calorietable` WHERE `User Name` LIKE '"
									+ user_name + "'");
					String check = "";
					String fn = "", ln = "";
					int data = 0;
					int height = 0;
					int weight = 0;
					double bmi = 0.0;
					if (rs.next()) {
						check = rs.getString("Password");
						fn = rs.getString("First Name");
						ln = rs.getString("Last Name");
						height = rs.getInt("Height");
						data = rs.getInt("Calorie");
						weight = rs.getInt("Weight");
						bmi = rs.getDouble("BMI");
					}
					if (check.equals(password) && !(user_name.equals(""))
							&& !(password.equals(""))) {
						new CalorieCalculation(fn, ln, user_name, password,
								height, weight, bmi, data);
						setVisible(false);
					} else {

						JOptionPane.showMessageDialog(null,
								"User Name or Password Incorrect!!");
						new Welcome();
						setVisible(false);
					}

				} catch (Exception e) {

					JOptionPane
							.showMessageDialog(
									null,
									"Internet Connection or, Database Error. Please Contact to System Administrator. Email: habib_cse_ruet@yahoo.com");
				new Welcome();
				}

			}
		});
		reg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				new Registraton();
				setVisible(false);
			}
		});
	}
}

class Registraton extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton submit = new JButton("Submit");
	private JLabel first_name = new JLabel("First Name:");
	private JLabel last_name = new JLabel("Last Name:");
	private JLabel user_name = new JLabel("User Name:");
	private JLabel newPassword = new JLabel("Password:");
	private JLabel conPassword = new JLabel("Confirm Password:");
	private JLabel initCal = new JLabel("Calorie Desirevd Now:");
	private JLabel h = new JLabel("Your Height(CM):");
	private JLabel w = new JLabel("Your Weight(KG):");
	private JTextField firstText = new JTextField(20);
	private JTextField lastText = new JTextField(20);
	private JTextField userText = new JTextField(20);
	private JTextField newPText = new JTextField(20);
	private JTextField conPText = new JTextField(20);
	private JTextField hText = new JTextField(20);
	private JTextField wText = new JTextField(20);
	private JTextField initCalText = new JTextField(20);
	private JLabel title = new JLabel("Registration Form");

	public Registraton() {

		setTitle("User Registration Form");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height, width;
		height = (int) screenSize.getHeight();
		width = (int) screenSize.getWidth();
		setSize(width, height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		// setDefaultCloseOperation(operation)
		setLocationRelativeTo(null);
		setLayout(null);
		title.setBounds(400, 150, 400, 40);
		title.setFont(new Font("Kristen ITC", Font.BOLD, 40));
		first_name.setBounds(300, 250, 200, 30);
		firstText.setBounds(600, 250, 200, 30);
		last_name.setBounds(300, 300, 200, 30);
		lastText.setBounds(600, 300, 200, 30);
		user_name.setBounds(300, 350, 200, 30);
		userText.setBounds(600, 350, 200, 30);
		newPassword.setBounds(300, 400, 200, 30);
		newPText.setBounds(600, 400, 200, 30);
		conPassword.setBounds(300, 450, 200, 30);
		conPText.setBounds(600, 450, 200, 30);
		h.setBounds(300, 500, 200, 30);
		hText.setBounds(600, 500, 200, 30);
		w.setBounds(300, 550, 200, 30);
		wText.setBounds(600, 550, 200, 30);
		initCal.setBounds(300, 600, 200, 30);
		initCalText.setBounds(600, 600, 200, 30);
		submit.setBounds(500, 650, 250, 30);
		add(title);
		add(first_name);
		add(firstText);
		add(last_name);
		add(lastText);
		add(user_name);
		add(userText);
		add(newPassword);
		add(newPText);
		add(conPassword);
		add(conPText);
		add(initCal);
		add(initCalText);
		add(h);
		add(w);
		add(hText);
		add(wText);
		add(submit);
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost/calorie", "root", "");
					String us = userText.getText();
					String ps = conPText.getText();
					if ((conPText.getText()).equals(newPText.getText())
							&& !(newPText.equals(""))) {
						int d = Integer.parseInt(initCalText.getText());
						int height = Integer.parseInt(hText.getText());
						int weight = Integer.parseInt(wText.getText());
						double bmi = (double) weight
								/ Math.pow(height / 100.00, 2);
						Statement st = con.createStatement();
						st.execute("INSERT INTO `calorietable`(`First Name`, `Last Name`, `User Name`, `Password`, `Height`, `Weight`, `BMI`, `Calorie`) VALUES (\""
								+ firstText.getText()
								+ "\",\""
								+ lastText.getText()
								+ "\",\""
								+ userText.getText()
								+ "\",\""
								+ ps
								+ "\",\""
								+ height
								+ "\",\""
								+ weight
								+ "\",\""
								+ bmi
								+ "\",\"" + d + "\")");

						new CalorieCalculation(firstText.getText(), lastText
								.getText(), us, ps, Integer.parseInt(hText
								.getText()), Integer.parseInt(wText.getText()),
								bmi, Integer.parseInt(initCalText.getText()));
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null,
								"Password Not Matched!!!");
						new Registraton();
						setVisible(false);
					}
				} catch (Exception ex) {
					JOptionPane
							.showMessageDialog(
									null,
									"Internet Connection or, Database Error. Please Contact to System Administrator. Email: habib_cse_ruet@yahoo.com");
					new Registraton();
				}

			}
		});
	}
}
