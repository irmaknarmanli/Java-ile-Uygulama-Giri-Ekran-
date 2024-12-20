package yeniOlsun;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Uygulama implements ActionListener {
	
	static Font myFont;
	static JButton createProfile, submit, display;
	static JFrame startFrame;
	static JPanel upper, lower;
	static GridBagConstraints gbcLower;
	static JTextField name, age;
	static ArrayList<String> userNameList;
	static ArrayList<Integer> userAgeList;
	static String enteredName;
	static String enteredAge;
	
	public static void main(String[] args) {
		
		userNameList = new ArrayList<>();
	    userAgeList = new ArrayList<>();
		unclickedFrame();
	}
			private static Font loadCustomFont(String fontFileName, int style, float size) {
	    InputStream is = null;
	    try {
	        is = Uygulama.class.getResourceAsStream("/Font/" + fontFileName);
	        Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(customFont);
	        return customFont.deriveFont(style, size);
	    } catch (FontFormatException e) {
	        e.printStackTrace();
	        return new Font("SansSerif", style, (int) size); // Default to SansSerif if custom font loading fails
	    }catch(IOException e) {
	    	 e.printStackTrace();
		        return new Font("SansSerif", style, (int) size); // Default to SansSerif if custom font loading fails
	    }
	    finally {
	        if (is != null) {
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
			public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createProfile) {
			createProfile.setVisible(false);
			creatingProfile();
		}
		if(e.getSource() == submit) {
			enteredName = name.getText();
            enteredAge = age.getText();
            if (enteredName.isEmpty() || enteredAge.isEmpty()) {
                JOptionPane.showMessageDialog(startFrame, "Please enter both your name and age.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
            try {
                int age = Integer.parseInt(enteredAge);
                userAgeList.add(age);
                userNameList.add(enteredName);
                newUser();
            } catch (NumberFormatException m) {
                JOptionPane.showMessageDialog(startFrame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
		}
		}
		
		if(e.getSource() == display) {
			createProfile.setVisible(false);
		display();
			
		}
}
			public static void mainFrame() {
				startFrame = new JFrame();
				startFrame.setTitle("Pizza Tarlası");
				startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				startFrame.setSize(700,700);
				startFrame.setResizable(false);
			}
			public static void upperFrame() {
				
				upper = new JPanel();
				upper.setBackground(new Color(247, 180, 100));
				upper.setPreferredSize(new Dimension(100,150));
				
				JLabel pizzaTarlasi = new JLabel();

				pizzaTarlasi.setHorizontalAlignment(JLabel.CENTER);
				pizzaTarlasi.setVerticalAlignment(JLabel.TOP);
				pizzaTarlasi.setText("Pizza Tarlasi");
		     	pizzaTarlasi.setFont(loadCustomFont("Photograph Signature.ttf", Font.PLAIN, 60));
				
				JLabel intro = new JLabel();
				intro.setFont(loadCustomFont("Caramel.ttf", Font.PLAIN, 22));
				intro.setText("welcome to Pizza Tarlasi. you can create a new account now and join us for the best organic pizzas!");
				
				GridBagConstraints gbcUpper = new GridBagConstraints();
		        gbcUpper.gridx = 0;
		        gbcUpper.gridy = 0;
		        gbcUpper.anchor = GridBagConstraints.CENTER;
		        upper.add(pizzaTarlasi, gbcUpper);

		        GridBagConstraints gbcIntro = new GridBagConstraints();
		        gbcIntro.gridx = 0;
		        gbcIntro.gridy = 1;
		        gbcIntro.anchor = GridBagConstraints.CENTER;
		        upper.add(intro, gbcIntro);
		        upper.add(pizzaTarlasi, BorderLayout.NORTH);
				upper.add(intro, BorderLayout.NORTH);
				startFrame.add(upper, BorderLayout.NORTH);
				startFrame.setVisible(true);
				
			}
			public static void unclickedFrame() {
				mainFrame();
				upperFrame();
				lowerStructure();
			        
			        createProfile = new JButton();
					createProfile.setFont(loadCustomFont("Caramel.ttf", Font.PLAIN, 32));
					createProfile.setForeground(Color.WHITE);
					createProfile.setBackground(new Color(160, 7, 7));
					createProfile.setPreferredSize(new Dimension(220, 110));
					createProfile.setText("Create new profile");	
					createProfile.addActionListener(new Uygulama());
					
			        lower.add(createProfile, gbcLower);
					lower.add(createProfile);
					startFrame.add(lower, BorderLayout.SOUTH);
					startFrame.setVisible(true);
			}
			public static void creatingProfile() {
			
				JLabel userName = new JLabel("Your name:");
				userName.setFont(loadCustomFont("Caramel.ttf", Font.PLAIN, 20));
				
				GridBagConstraints gbcLabelName = new GridBagConstraints();
				gbcLabelName.gridx = 0;
				gbcLabelName.gridy = 0;
				gbcLabelName.anchor = GridBagConstraints.WEST;
				lower.add(userName, gbcLabelName);
				
				name = new JTextField();
				name.setPreferredSize(new Dimension(250, 40));
				name.setFont(loadCustomFont("Caramel.ttf", Font.PLAIN, 30));
				
				GridBagConstraints gbcName = new GridBagConstraints();
				gbcName.gridx = 0;
				gbcName.gridy = 1;
				gbcName.anchor = GridBagConstraints.WEST;
				lower.add(name, gbcName);
			    
			    JLabel userAge = new JLabel("Your age:");
				userAge.setFont(loadCustomFont("Caramel.ttf", Font.PLAIN, 20));
				
			    GridBagConstraints gbcLabelAge = new GridBagConstraints();
				gbcLabelAge.gridx = 0;
				gbcLabelAge.gridy = 2;
				gbcLabelAge.anchor = GridBagConstraints.WEST;
				lower.add(userAge, gbcLabelAge);
				
				age = new JTextField();
				age.setPreferredSize(new Dimension(250, 40));
				age.setFont(loadCustomFont("Caramel.ttf", Font.PLAIN, 30));
				
				GridBagConstraints gbcAge = new GridBagConstraints();
				gbcAge.gridx = 0;
				gbcAge.gridy = 3;
				gbcAge.anchor = GridBagConstraints.WEST;
				lower.add(age, gbcAge);
				
				submit = new JButton("Submit");
				submit.addActionListener(new Uygulama());
				submit.setFont(loadCustomFont("Caramel.ttf", Font.PLAIN, 30));
				submit.setBackground(new Color(250, 248, 132));
				
				GridBagConstraints gbcSubmit = new GridBagConstraints();
			    gbcSubmit.gridx = 0;
			    gbcSubmit.gridy = 4;
			    gbcSubmit.anchor = GridBagConstraints.WEST;
			    lower.add(submit, gbcSubmit);
			    
			    display = new JButton("Display Profiles");
			    display.addActionListener(new Uygulama());
				display.setFont(loadCustomFont("Caramel.ttf", Font.PLAIN, 30));
				display.setBackground(new Color(250, 248, 132));
				
				GridBagConstraints gbcDisplay = new GridBagConstraints();
			    gbcDisplay.gridy = 6;
			    gbcDisplay.anchor = GridBagConstraints.EAST;
			   lower.add(display, gbcDisplay);
			}
			public static void lowerStructure() {
				
				lower = new JPanel(new GridBagLayout());
				lower.setBackground(new Color(247, 206, 136));
				lower.setPreferredSize(new Dimension(700,517));
				
				gbcLower = new GridBagConstraints();
				gbcLower.gridy = 1;
				gbcLower.insets.top = 10;
			}
			
			public static void display() {
			    startFrame.getContentPane().removeAll();
			    startFrame.repaint();
			    upperFrame();
			    lowerStructure();
			    Collections.sort(userNameList);

			    JPanel background = new JPanel();
			    background.setBackground(new Color(247, 236, 176));
			    
			    StringBuilder namesText = new StringBuilder();
				namesText.append("Profiles:").append("<br>");
			    for (String userName : userNameList) {
			        namesText.append(userName).append("<br>").append("----------").append("<br>");
			    }
			    
			    JLabel sortedNamesLabel = new JLabel("<html>" + namesText.toString() + "</html>");
			    sortedNamesLabel.setFont(loadCustomFont("Caramel.ttf", Font.PLAIN, 30));

			    GridBagConstraints gbcSortedNames = new GridBagConstraints();
			    gbcSortedNames.gridx = 0;
			    gbcSortedNames.gridy = 0;
			    gbcSortedNames.anchor = GridBagConstraints.LINE_START;
			    
			    GridBagConstraints gbcBackground = new GridBagConstraints();
			    gbcBackground.gridx = 0;
			    gbcBackground.gridy = 0;
			    gbcBackground.anchor = GridBagConstraints.WEST;
			    gbcBackground.insets = new Insets(0, 10, 0, 0);

			    lower.setLayout(new GridBagLayout());
			    lower.add(background, gbcBackground);
			    
			    background.add(sortedNamesLabel);
			    startFrame.add(upper, BorderLayout.NORTH);
			    startFrame.add(lower, BorderLayout.SOUTH);
			    startFrame.setVisible(true);
}
			public static void newUser() {
				
				 JOptionPane.showMessageDialog(startFrame, "Profile of " + enteredName + " has been added!", "Profile Added", JOptionPane.PLAIN_MESSAGE);
			}
}