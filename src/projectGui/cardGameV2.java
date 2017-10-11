/*
Ziaul Choudhury
Java Draw a Card game
*/

package projectGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class cardGameV2 {

	public int[] uniqueRandomArray(){
			 
 	    int[] a = new int[52];
 	    
 	    for(int i = 0 ; i < a.length ; i++) // go trough each index of array
 	    {
 	        a[i] = (int)(Math.random()*52+1); // populate each index with random number within range of 52
 	        for(int j = 0 ; j < i ; j++) // access previous items/index
 	        {
 	            if(a[i] == a[j]) // check if both indexes contain same 
 	            {
 	                i--; //if yes decrement i to replace the duplicate index value

 	            }
 	        }   
 	    }
 	   	    
		return a;
 	}// End of uniqueRandomArray()
 	
	public void printRandArry(int[] a){ // get the array and print
		System.out.println("Here's the randomize array: ");
		for(int i = 0 ; i< a.length ; i++)
 	    {
 	        System.out.print("Index: " + i + ", Value: " + a[i] + "\n");
 	    }
		
	}// End of printRandArr()
		
	public void playButonSound(String name) { // import sound clips and play
		  try {
			  File file = new File("sound/"+ name + ".wav");
			  Clip clip = AudioSystem.getClip();
			  clip.open(AudioSystem.getAudioInputStream(file));
			  clip.start();
		   
		  }catch(Exception e) {
	          System.err.println(e.getMessage());
		  }
		  
	}// End of playButonSound()
		
	
	public static void main(String[] args) {
		
		
		cardGameV2 p = new cardGameV2();
		int rand_array[] = p.uniqueRandomArray();
		
		JFrame gui = new JFrame(); // Creating a gui
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the program
		gui.setTitle("Card Game"); //title
		gui.setSize(400, 400); // size, pick one that has width and height
		gui.setLocationRelativeTo(null); // opens the gui window on the middle rather then on the corner
						
		//card display
		JPanel cardDisplay = new JPanel(new BorderLayout());
		ImageIcon img = new ImageIcon("img/0.png"); // adding the img form img floder
		JLabel card = new JLabel(img); // adding image to the panel
		card.setHorizontalAlignment(JLabel.CENTER); // added for centering
		card.setVerticalAlignment(JLabel.CENTER); // added for centering
		
		//Buttons Panel
		JPanel buttonsPanel = new JPanel();
		String c = "Shuffle";
		JButton shuffle = new JButton(c);
		JButton draw = new JButton("Draw Card");
		
		shuffle.addActionListener(new ActionListener() {
			boolean hasBeenSet = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==shuffle){// if it's the shuffle button
					if(!hasBeenSet){ // if the button has clicked (true) do this to change button name.
						p.playButonSound("clicked"); // playButtonSound to play each time user click the button 					
						shuffle.setText("Stop");  // setting button name to stop
						shuffle.setForeground(Color.red); // setting text color to red for "stop" 
						System.out.println("Shuffle Clicked");
						ImageIcon img = new ImageIcon("img/shuffling.gif"); // Changing the picture to gif 
						card.setIcon(img); // adding gif image to the panel
						hasBeenSet = true;  // changing the boolean value to enable shuffle
					}else{ // if the button has not clicked (false) do this to change button name.
						p.printRandArry(rand_array); // print random array once stop clicked
						p.playButonSound("clicked"); // play button sound
						shuffle.setText("Shuffle"); // changing button text back "Shuffle"
						shuffle.setForeground(Color.black); // button text to original color 
						System.out.println("Stop Clicked");
						ImageIcon img = new ImageIcon("img/0.png"); // orginal image for button
						card.setIcon(img); // adding image to the panel
						hasBeenSet = false; // setting boolean value to false for furthen access
					}
				}
				draw.setEnabled(true);
			}
		});
		
		draw.addActionListener(new ActionListener() {
			short index = 0; // setting index value to fetch item(s) from random array by index
			@Override
			public void actionPerformed(ActionEvent e) {				
				p.playButonSound("draw");
				System.out.println("Draw Clicked");	
				if(index <= rand_array.length){
					ImageIcon img = new ImageIcon("img/"+rand_array[index]+".png"); //passing index for card 
					card.setIcon(img); // changing the image to current image associated with array index
					index++; // increment if the user wants to draw next card
					int cl = 52 - index; // count how many cards are left
					System.out.println("Card left: " + cl ); // print how many card left
					
					if(index  == 52){ // if no card left set index back to 0
						System.out.println("No more card left.");
						index = 0;
						draw.setEnabled(false); // disabling the draw button
					}
				}else return;		
			}
		});
				
		draw.setEnabled(false);
		buttonsPanel.add(shuffle);
		buttonsPanel.add(draw);
		
		//Container
		Container container = gui.getContentPane();
		
		// adding stuff to container
		cardDisplay.add(card); // display
		container.add(cardDisplay, BorderLayout.CENTER);
		container.add(buttonsPanel, BorderLayout.PAGE_END);
		
		gui.setVisible(true);		

	} // End of main
	
}// End of cardGameV2 
