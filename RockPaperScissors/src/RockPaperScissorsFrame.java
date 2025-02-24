import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

/**
 * RockPaperScissorsFrame is a Java Swing GUI that allows a user to play
 * Rock Paper Scissors against the computer.
 *
 * @author Sawyer Davidson
 */

public class RockPaperScissorsFrame extends JFrame {
    private final JTextField playerWinsField;
    private final JTextField computerWinsField;
    private final JTextField tiesField;
    private final JTextArea resultArea;
    private int playerWins = 0, computerWins = 0, ties = 0;
    private final String[] choices = {"Rock", "Paper", "Scissors"};
    private final Random random = new Random();

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));

        // Load images and set buttons
        JButton rockButton = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/rock.png"))));
        JButton paperButton = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/paper.png"))));
        JButton scissorsButton = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/scissors.png"))));
        JButton quitButton = new JButton("Quit");

        // Button sizes (optional)
        Dimension buttonSize = new Dimension(100, 100);
        rockButton.setPreferredSize(buttonSize);
        paperButton.setPreferredSize(buttonSize);
        scissorsButton.setPreferredSize(buttonSize);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.NORTH);

        // Stats Panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 6));
        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0", 3);
        playerWinsField.setEditable(false);
        statsPanel.add(playerWinsField);

        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0", 3);
        computerWinsField.setEditable(false);
        statsPanel.add(computerWinsField);

        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0", 3);
        tiesField.setEditable(false);
        statsPanel.add(tiesField);

        add(statsPanel, BorderLayout.CENTER);

        // Results Display
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.SOUTH);

        // Event Listeners
        rockButton.addActionListener(e -> playGame("Rock"));
        paperButton.addActionListener(e -> playGame("Paper"));
        scissorsButton.addActionListener(e -> playGame("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void playGame(String playerChoice) {
        String computerChoice = choices[random.nextInt(3)];
        String result;

        if (playerChoice.equals(computerChoice)) {
            result = "It's a tie! Both chose " + playerChoice;
            ties++;
            tiesField.setText(String.valueOf(ties));
        } else if (
                (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                        (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                        (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))
        ) {
            result = playerChoice + " beats " + computerChoice + " (Player Wins)";
            playerWins++;
            playerWinsField.setText(String.valueOf(playerWins));
        } else {
            result = computerChoice + " beats " + playerChoice + " (Computer Wins)";
            computerWins++;
            computerWinsField.setText(String.valueOf(computerWins));
        }

        resultArea.append(result + "\n");
    }
}
