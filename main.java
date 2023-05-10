import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton startButton, stopButton, resetButton;
    private JLabel timeLabel;
    private Timer timer;
    private int hours, minutes, seconds, milliseconds;

    public Stopwatch() {
        setTitle("Секундомер");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(2, 2));
        timeLabel = new JLabel("00:00:00.000", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        startButton = new JButton("Старт");
        stopButton = new JButton("Стоп");
        resetButton = new JButton("Сброс");
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        resetButton.addActionListener(this);
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(resetButton);
        add(timeLabel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        
        timer = new Timer(10, this);
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            timer.start();
        } else if (e.getSource() == stopButton) {
            timer.stop();
        } else if (e.getSource() == resetButton) {
            timer.stop();
            hours = 0;
            minutes = 0;
            seconds = 0;
            milliseconds = 0;
            updateTimeLabel();
        } else if (e.getSource() == timer) {
            milliseconds += 10;
            if (milliseconds >= 1000) {
                milliseconds -= 1000;
                seconds++;
            }
            if (seconds >= 60) {
                seconds -= 60;
                minutes++;
            }
            if (minutes >= 60) {
                minutes -= 60;
                hours++;
            }
            updateTimeLabel();
        }
    }

    private void updateTimeLabel() {
        String time = String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
        timeLabel.setText(time);
    }
}
