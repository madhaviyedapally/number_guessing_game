import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame {
    private int numberToGuess;
    private int numberOfTries;
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel triesLabel;

    public GuessingGameGUI() {
        // Set up the game
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        numberOfTries = 0;

        // Set up the frame
        setTitle("Number Guessing Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60, 63, 65));
        
        // Set up the panel for the input and button
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.setBackground(new Color(43, 43, 43));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add components to the panel
        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(instructionLabel);

        guessField = new JTextField();
        guessField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(guessField);

        JButton guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 16));
        guessButton.setBackground(new Color(75, 110, 175));
        guessButton.setForeground(Color.WHITE);
        panel.add(guessButton);

        feedbackLabel = new JLabel("", SwingConstants.CENTER);
        feedbackLabel.setForeground(Color.WHITE);
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(feedbackLabel);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Set up the label for the number of tries
        triesLabel = new JLabel("Number of attempts: 0", SwingConstants.CENTER);
        triesLabel.setForeground(Color.WHITE);
        triesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(triesLabel, BorderLayout.SOUTH);

        // Add action listener to the button
        guessButton.addActionListener(new GuessButtonListener());
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                numberOfTries++;
                if (guess < numberToGuess) {
                    feedbackLabel.setText("Too low! Try again.");
                } else if (guess > numberToGuess) {
                    feedbackLabel.setText("Too high! Try again.");
                } else {
                    feedbackLabel.setText("Congratulations! You've guessed the correct number!");
                }
                triesLabel.setText("Number of attempts: " + numberOfTries);
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Invalid input! Please enter a number.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessingGameGUI().setVisible(true);
            }
        });
    }
}
