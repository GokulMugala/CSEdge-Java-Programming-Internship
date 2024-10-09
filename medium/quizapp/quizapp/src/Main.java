import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizFrame());
    }
}

class QuizFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private int score = 0;
    private final int totalQuestions = 4; // Update this if you add more questions
    private final List<String> incorrectQuestions = new ArrayList<>();
    private final JLabel progressLabel = new JLabel("Progress: 0/" + totalQuestions);
    private final JProgressBar progressBar = new JProgressBar(0, totalQuestions);
    private final Timer timer;
    private int timeLeft = 30; // 30 seconds for each question
    private final JLabel timerLabel = new JLabel("Time left: " + timeLeft + "s");

    public QuizFrame() {
        setTitle("Online Quiz Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add quiz panels
        cardPanel.add(new QuestionPanel("Question 1: What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1, "Hint: It's an even number"), "1");
        cardPanel.add(new QuestionPanel("Question 2: What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2, "Hint: It's known for the Eiffel Tower"), "2");
        cardPanel.add(new QuestionPanel("Question 3: What is the largest planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2, "Hint: It's a gas giant"), "3");
        cardPanel.add(new QuestionPanel("Question 4: What is the chemical symbol for water?", new String[]{"H2O", "O2", "CO2", "NaCl"}, 0, "Hint: It's composed of hydrogen and oxygen"), "4");
        cardPanel.add(new ResultPanel(), "Result");

        add(progressLabel, BorderLayout.NORTH);
        add(progressBar, BorderLayout.SOUTH);
        add(timerLabel, BorderLayout.EAST);
        add(cardPanel, BorderLayout.CENTER);

        timer = new Timer(1000, new TimerListener());
        timer.start();

        setVisible(true);
    }

    class QuestionPanel extends JPanel {
        public QuestionPanel(String question, String[] options, int correctAnswer, String hint) {
            setLayout(new BorderLayout());
            setBackground(new Color(173, 216, 230)); // Light blue background
            JLabel questionLabel = new JLabel(question);
            questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
            add(questionLabel, BorderLayout.NORTH);

            ButtonGroup group = new ButtonGroup();
            JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
            for (int i = 0; i < options.length; i++) {
                JRadioButton optionButton = new JRadioButton(options[i]);
                optionButton.setFont(new Font("Arial", Font.PLAIN, 14));
                group.add(optionButton);
                optionsPanel.add(optionButton);
                int finalI = i;
                optionButton.addActionListener(e -> {
                    if (finalI == correctAnswer) {
                        score++;
                        playSound("correct.wav");
                        JOptionPane.showMessageDialog(this, "Correct!", "Feedback", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        incorrectQuestions.add(question);
                        playSound("incorrect.wav");
                        JOptionPane.showMessageDialog(this, "Incorrect!", "Feedback", JOptionPane.ERROR_MESSAGE);
                    }
                    updateProgress();
                    if (cardPanel.getComponentCount() - 1 == cardPanel.getComponentZOrder(this) + 1) {
                        ((ResultPanel) cardPanel.getComponent(cardPanel.getComponentCount() - 1)).updateScore();
                        timer.stop(); // Stop the timer when the result panel is shown
                    }
                    cardLayout.next(cardPanel);
                    resetTimer();
                });
            }
            add(optionsPanel, BorderLayout.CENTER);

            JButton hintButton = new JButton("Hint");
            hintButton.addActionListener(e -> JOptionPane.showMessageDialog(this, hint, "Hint", JOptionPane.INFORMATION_MESSAGE));
            add(hintButton, BorderLayout.SOUTH);
        }

        private void updateProgress() {
            int currentQuestion = cardPanel.getComponentZOrder(this) + 1;
            progressLabel.setText("Progress: " + currentQuestion + "/" + totalQuestions);
            progressBar.setValue(currentQuestion);
        }

        private void resetTimer() {
            timeLeft = 30;
            timerLabel.setText("Time left: " + timeLeft + "s");
        }

        private void playSound(String soundFile) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            }
        }
    }

    class ResultPanel extends JPanel {
        private final JLabel resultLabel;

        public ResultPanel() {
            setLayout(new BorderLayout());
            setBackground(new Color(144, 238, 144)); // Light green background
            resultLabel = new JLabel();
            resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
            add(resultLabel, BorderLayout.CENTER);
        }

        public void updateScore() {
            double percentage = ((double) score / totalQuestions) * 100;
            String grade;
            if (percentage >= 90) {
                grade = "A";
            } else if (percentage >= 80) {
                grade = "B";
            } else if (percentage >= 60) {
                grade = "C";
            } else if (percentage >= 40) {
                grade = "D";
            } else {
                grade = "F";
            }

            StringBuilder resultText = new StringBuilder("<html>Your score: " + score + "/" + totalQuestions + "<br>");
            resultText.append("Percentage: ").append(String.format("%.2f", percentage)).append("%<br>");
            resultText.append("Grade: ").append(grade).append("<br>");
            if (!incorrectQuestions.isEmpty()) {
                resultText.append("Incorrectly answered questions:<br>");
                for (String question : incorrectQuestions) {
                    resultText.append(question).append("<br>");
                }
            }
            resultText.append("</html>");
            resultLabel.setText(resultText.toString());
        }
    }

    class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                cardLayout.next(cardPanel);
                resetTimer();
            }
        }

        private void resetTimer() {
            timeLeft = 30;
            timerLabel.setText("Time left: " + timeLeft + "s");
        }
    }
}
