/*
Ziaul Choudhury
Java Draw a Card game
*/

package projectGui;

import java.awt.BorderLayout;
//import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardGame {

	public static void main(String[] args) {
		JFrame gui = new JFrame(); // Creating a gui
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the program
		gui.setTitle("Card Game"); //title
		gui.setSize(300, 300); // size, pick one that has width and height
		gui.setLocationRelativeTo(null); // opens the gui window on the middle rather then on the corner
		
		//Container
		Container container = gui.getContentPane();
		

		//card display
		JPanel cardDisplay = new JPanel(new BorderLayout());
		ImageIcon img = new ImageIcon("img/0.png"); // adding the img form img floder
		JLabel card = new JLabel(img); // adding image to the panel
		card.setHorizontalAlignment(JLabel.CENTER); // added for centering
		card.setVerticalAlignment(JLabel.CENTER); // added for centering
		cardDisplay.add(card); // display
		
		
		
		//Buttons Panel
		String b1= "Shuffle";
		String b2= "Draw Card";
		
		JPanel buttonsPanel = new JPanel();
		JButton shuffle = new JButton(b1);
		JButton draw = new JButton(b2);
		
		
		shuffle.addActionListener(new ActionListener() {
			boolean hasBeenSet = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==shuffle){
					if(!hasBeenSet){
						shuffle.setText("Stop");
						System.out.println("Shuffle Clicked");
						ImageIcon img = new ImageIcon("img/shuffling.gif"); // adding the img form img floder
						card.setIcon(img); // adding image to the panel
						hasBeenSet = true;
					}else{
						shuffle.setText("Shuffle");
						System.out.println("Stop Clicked");
						ImageIcon img = new ImageIcon("img/0.png"); // adding the img form img floder
						card.setIcon(img); // adding image to the panel
						hasBeenSet = false;
					}
				}
				
				draw.setEnabled(true);
				
			}
		});
		
		draw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Draw Clicked");	
				int num = 21;
				ImageIcon img = new ImageIcon("img/"+num+".png"); //("img/"+num+".png") adding the img form img floder
				card.setIcon(img); // changing the img dynamically 24 will be changed
				
			}
		});
		
		
		
		draw.setEnabled(false);
		buttonsPanel.add(shuffle);
		buttonsPanel.add(draw);
		
		
		// adding stuff to container
		container.add(cardDisplay, BorderLayout.CENTER);
		container.add(buttonsPanel, BorderLayout.PAGE_END);
		
		gui.setVisible(true);
		
		

	}

}
