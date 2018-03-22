package fr.wildcodeschool.quetes.chrono;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Chrono {

  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE d MMMM uuuu", Locale.FRENCH);
  public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
  private JFrame frame;
  private final TimeProvider timeProvider;
  private boolean rolling;

  private JPanel mainPanel;
  private JButton startStopButton;
  private JButton resetButton;
  private JLabel chronoCount;
  private JLabel countedLabel;
  private JLabel hoursLabel;
  private JLabel minutesLabel;
  private JLabel secondsLabel;
  private JLabel hoursCount;
  private JLabel minutesCount;
  private JLabel secondsCount;
  private JLabel dateLabel;
  private JLabel dateText;
  private JLabel timeLabel;
  private JLabel timeText;
  private JPanel buttonsPanel;
  private JPanel centerPanel;
  private JPanel currentTimePanel;


  public Chrono(TimeProvider timeProvider) {
    this.timeProvider = timeProvider;

    startStopButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        startStop();
      }
    });

    resetButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        reset();
      }
    });

    mainPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) { // Left click
          startStop();
        } else if (e.getButton() == 3) { // Right clic
          reset();
        } else {
          super.mouseClicked(e);
        }
      }
    });
  }


  private void startStop() {
    timeProvider.startStop();
    frame.repaint();
  }

  private void reset() {
    timeProvider.reset();
    frame.repaint();
  }

  private void hookKeyboard() {
    //TODO
  }

  private void display() throws InterruptedException {
    frame = new JFrame("Chrono") {
      @Override
      public void dispose() {
        rolling = false;
        super.dispose();
      }
    };

    frame.setContentPane(mainPanel);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  public synchronized void roll() throws InterruptedException {
    if(!rolling) {
      rolling = true;

      hookKeyboard();
      display();

      refreshCounters(); // in case we're not initialized at 0 seconds

      while (rolling) {
        if(timeProvider.isStarted()) {
          refreshCounters();
        }

        refreshCurrentDateTime();

        frame.repaint();
        Thread.sleep(1000L);
      }
    }
  }



  private void refreshCurrentDateTime() {
    LocalDateTime now = LocalDateTime.now();
    dateText.setText(DATE_FORMATTER.format(now));
    timeText.setText(TIME_FORMATTER.format(now));
  }

  private void refreshCounters() {
    chronoCount.setText(String.format("%d secondes", timeProvider.getSecondsTotalRuntime()));
    hoursCount.setText(String.format("%02d", timeProvider.getHoursRuntime()));
    minutesCount.setText(String.format("%02d", timeProvider.getMinutesRuntime()));
    secondsCount.setText(String.format("%02d", timeProvider.getSecondsRuntime()));
  }


}
