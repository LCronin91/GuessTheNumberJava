package guessinggame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuessingGame extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Label to tell user number range
	private JLabel lblGuess;
	
	// Text field for the user's guess
	private JTextField txtGuess;
	
	// Label for too high/too low output to user
	private JLabel lblOutput;
	
	// Label for number of tries remaining
	private JLabel lblTries;
	
	// Number user is trying to guess
	private int theNumber;
	
	// Give user limited tries to guess
	private int triesLeft;
	
	// Allow user to choose number range
	private int gameLevel;
	
	// Method to check whether user's guess is too high or too low
	public void checkGuess()
	{
		// Gets the user's guess
		String guessText = txtGuess.getText();
		String message = "";
		
		try
		{
			// Checks the guess
			int guess = Integer.parseInt(guessText);
			
			// Count this as one try
			triesLeft--;

			// Too high
			if(guess > theNumber)
			{
				message = guess + " was too high. Guess again.";
				lblOutput.setText(message);
				lblTries.setText("You have " + triesLeft + " remaining");
			}
			// Too low
			else if(guess < theNumber)
			{
				message = guess + " was too low. Guess again.";
				lblOutput.setText(message);
				lblTries.setText("You have " + triesLeft + " remaining");
			}
			
			// Correct
			else
			{
				message = guess + " was correct. You win! \n Let's play again!";
				lblOutput.setText(message);
				if(lblGuess.getText() == "Guess a number between 1 and 10:")
				{
					lblTries.setText("You guessed in " + (4 - triesLeft) + " tries.");
				}
				else if(lblGuess.getText() == "Guess a number between 1 and 100:")
				{
					lblTries.setText("You guessed in " + (7 - triesLeft) + " tries.");
				}
				else if(lblGuess.getText() == "Guess a number between 1 and 1000:")
				{
					lblTries.setText("You guessed in " + (9 - triesLeft) + " tries.");
				}
				newGame();
			}
			
			if(triesLeft <=0)
			{
				int option = JOptionPane.showConfirmDialog(null, "You ran out of tries. The number was " + theNumber
						+ "\nPlay again?");
				if(option == JOptionPane.CLOSED_OPTION || option == JOptionPane.NO_OPTION)
				{
					System.exit(0);
				}
			}
		} 
		catch (Exception e)
		{
			lblOutput.setText("Enter a whole number in the range listed above.");
		}
		finally
		{
			txtGuess.requestFocus();
			txtGuess.selectAll();
		}
	}
	
	// Sets a new random number between 1 and 10, 1 and 100, or 1 and 1000
	public void newGame()
	{
		Object[] options = {"Between 1 and 10", "Between 1 and 100", "Between 1 and 1000", "Exit"};
		int gameLevel = JOptionPane.showOptionDialog(getParent(), "Choose a level to play", "Game Level", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		txtGuess.setText("");
		
		if (gameLevel == 0)
		{
			theNumber = (int)(Math.random() * 10 + 1);
			lblTries.setText("You have 4 tries remaining");
			lblGuess.setText("Guess a number between 1 and 10:");
			lblOutput.setText("Enter a number above, and click 'Guess'.");
			triesLeft = 4;
		}
		else if(gameLevel == 1)
		{
			theNumber = (int)(Math.random() * 100 + 1);
			lblTries.setText("You have 7 tries remaining");
			lblGuess.setText("Guess a number between 1 and 100:");
			lblOutput.setText("Enter a number above, and click 'Guess'.");
			triesLeft = 7;
		}
		else if(gameLevel == 2)
		{
			theNumber = (int)(Math.random() * 1000 + 1);
			lblTries.setText("You have 9 tries remaining");
			lblGuess.setText("Guess a number between 1 and 1000:");
			lblOutput.setText("Enter a number above, and click 'Guess'.");
			triesLeft = 9;
		}
		else if(gameLevel == 3 || gameLevel == JOptionPane.CLOSED_OPTION)
		{
			System.exit(0);
		}
	}
	
	public GuessingGame() 
	{
		setTitle("Guess the Number");
		getContentPane().setBackground(new Color(230, 230, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblGuessTheNumber = new JLabel("Guess the Number!");
		lblGuessTheNumber.setForeground(new Color(138, 43, 226));
		lblGuessTheNumber.setBounds(0, 11, 434, 60);
		lblGuessTheNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuessTheNumber.setFont(new Font("Ink Free", Font.BOLD, 40));
		getContentPane().add(lblGuessTheNumber);
		
		JPanel pnlGuess = new JPanel();
		pnlGuess.setBackground(new Color(230, 230, 250));
		pnlGuess.setBounds(37, 82, 360, 35);
		getContentPane().add(pnlGuess);
		pnlGuess.setLayout(null);
		
		lblGuess = new JLabel("Guess a number between 1 and 100:");
		lblGuess.setBackground(new Color(230, 230, 250));
		lblGuess.setBounds(0, 0, 278, 35);
		pnlGuess.add(lblGuess);
		lblGuess.setFont(new Font("Candara", Font.PLAIN, 16));
		lblGuess.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtGuess = new JTextField();
		txtGuess.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				checkGuess();
			}
		});
		txtGuess.setHorizontalAlignment(SwingConstants.CENTER);
		txtGuess.setBounds(288, 0, 72, 35);
		pnlGuess.add(txtGuess);
		txtGuess.setFont(new Font("Candara", Font.PLAIN, 16));
		txtGuess.setColumns(10);
		
		JButton btnGuess = new JButton("Guess");
		btnGuess.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				checkGuess();
			}
		});
		btnGuess.setForeground(new Color(138, 43, 226));
		btnGuess.setVerticalAlignment(SwingConstants.TOP);
		btnGuess.setBackground(Color.WHITE);
		btnGuess.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 30));
		btnGuess.setBounds(150, 144, 146, 35);
		getContentPane().add(btnGuess);
		
		lblOutput = new JLabel("Enter a number above, and click 'Guess'.");
		lblOutput.setForeground(new Color(0, 0, 0));
		lblOutput.setFont(new Font("Ink Free", Font.BOLD, 17));
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setBounds(10, 201, 414, 35);
		getContentPane().add(lblOutput);
		
		lblTries = new JLabel("You have 7 tries remaining");
		lblTries.setHorizontalAlignment(SwingConstants.CENTER);
		lblTries.setForeground(new Color(204, 0, 51));
		lblTries.setFont(new Font("Candara", Font.PLAIN, 14));
		lblTries.setBounds(10, 263, 414, 14);
		getContentPane().add(lblTries);
	}

	public static void main(String[] args) 
	{
		GuessingGame theGame = new GuessingGame();
		theGame.newGame();
		theGame.setSize(new Dimension(450, 350));
		theGame.setLocationRelativeTo(null);
		theGame.setVisible(true);
	}
}
