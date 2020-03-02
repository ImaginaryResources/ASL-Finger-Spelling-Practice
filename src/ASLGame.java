import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ASLGame extends JPanel {
	
	/**
	 * ImaginaryResources 7-30-17
	 * ASL practice game, generates random words in sign language, user needs to type what is signed
	 */
	
	private static final long serialVersionUID = 1L;
	private static JTextField input;
	private static JButton newWordButt, replayButt, giveUpButt;
	private static JFrame frame = new JFrame("ASLGame!");
	private int time, randInt, counter;
	private List<String> lines;
	private String[] words;
	private ArrayList<Image> alphabet;
	private Timer timer;
	private Random rand = new Random();
	private boolean replayable = true, showWord = false;
	
	public ASLGame () 
	{	
		alphabet = new ArrayList<Image>();
		lines = new ArrayList<String>();
		
		try 
		{
			for (int i = 0; i < 26; i++) {
	            char letter = (char) (97 + i);
	            String fileName = letter + ".png";
	            alphabet.add(ImageIO.read(new File("res/" + fileName)));
	        }
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(new File("res/words.txt"));
			while (scan.hasNextLine()) {
			  lines.add(scan.nextLine());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
		words = lines.toArray(new String[0]);
		
		randInt = rand.nextInt(words.length);
		
		input.addActionListener(new textListener());
		
		newWordButt.addActionListener(new newWordListener());
		
		replayButt.addActionListener(new replayListener());
		
		giveUpButt.addActionListener(new giveUpListener());
		
		this.setBackground(Color.black);
		
		timer = new Timer(1000, new TimerListener());
		timer.start();
	}
	
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			time++;
			repaint();
		}
	}
	
	private class replayListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	if(replayable){
	        	time = 0;
	        	timer.start();
	        	repaint();
        	}
        }
    }
	
	private class newWordListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	time = 0;
        	timer.start();
        	input.setText("");
        	randInt = rand.nextInt(words.length);
        	replayable = true;
        	showWord = false;
        	giveUpButt.setEnabled(true);
        	repaint();
        }
    }
	
	private class textListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			if (input.getText().equalsIgnoreCase(words[randInt]) && replayable)
			{
				input.setText("");
				replayable = false;
				if(!showWord)
					counter++;
			}
		}
	}
	
	private class giveUpListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			showWord = true;
			giveUpButt.setEnabled(false);
			counter--;
		}
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setFont(new Font("Times New Roman", Font.PLAIN, 150));
		g.setColor(Color.white);
	
		for (int i = 0; i < words[randInt].length(); i++){
            int letter = words[randInt].charAt(i) - 97;
            
    		char current = 0, prev = 0;
    		current = words[randInt].charAt(i);
    		if(i > 0 && i < words[randInt].length())
    			prev = words[randInt].charAt(i - 1);
    		
    		if (time == i)
    			g.drawImage(alphabet.get(letter), 300, 100, 500, 500, null);
    		
    		
    		if(current == prev && time == i){
    			//System.out.println("consecutive occurrences");
    			repaint();
    			g.drawImage(alphabet.get(letter), 200, 100, 500, 500, null);
    		}
		}
		
		if(showWord)
			g.drawString(words[randInt], 400, 115);
		
		g.drawString(""+counter+"", 10, 115);
		
	}
	
	public static void main (String[] args)
    {
        frame.setSize( new Dimension( 1270, 950) );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setLocationRelativeTo(null);
        input = new JTextField (60);
        newWordButt = new JButton("New Word");
        replayButt = new JButton("Replay");
        giveUpButt = new JButton("Give up");
        Font bigFont = input.getFont().deriveFont(Font.PLAIN, 150f);
        input.setFont(bigFont);
		frame.add(input, BorderLayout.SOUTH);
		frame.add(newWordButt, BorderLayout.NORTH);
		frame.add(replayButt, BorderLayout.EAST);
		frame.add(giveUpButt, BorderLayout.WEST);
        ASLGame game = new ASLGame();
        frame.add(game);      
        frame.setVisible(true); 
    }
}
