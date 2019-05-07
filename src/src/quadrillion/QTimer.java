package quadrillion;

import utils.Message;
import utils.Observable;
import utils.Observer;
import utils.QSoundLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Defines a timer for the game that runs on a separate thread. Provides functionality for starting and stopping the
 * timer.
 *
 * @author Unsal Ozturk
 * @version 20190316
 */
public class QTimer implements Observable {
    private Timer timer;
    private long timeRemaining;
    private long initialTime;
    private boolean running; // No other way to stop java timer thread
    private List<Observer> observers;

    // TODO: Custom Timer type that is not based on java.util.Timer
    // Introduces a thread that runs in the background continuously
    // No way to suspend the thread of the timer...

    /**
     * Constructs a QTimer instance. The timer is not running by default.
     *
     * @param timeRemaining The time remaining until the timer ends.
     */
    public QTimer(long timeRemaining) {
        observers = new ArrayList<>();
        running = false;
        this.timeRemaining = timeRemaining;
        this.initialTime = timeRemaining;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (running)
                    tick();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    /**
     * Ticks the timer by 100 milliseconds. Scheduled every 100 milliseconds.
     */
    public void tick() {
        timeRemaining -= 100;
        if (timeRemaining <= 0) {
            terminate();
        }
        notifyObservers();
    }

    /**
     * Terminates the timer. The thread of the timer object is probably returned to the thread pool.
     */
    public void terminate() {
        stop();
        timer.cancel();
    }

    /**
     * Restarts the timer. Sets time remaining to the initial value of the remaining time.
     */
    public void restart() {
        running = false;
        this.timeRemaining = initialTime;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (running)
                    tick();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    /**
     * Returns the remaining time in milliseconds.
     *
     * @return Remaining time in milliseconds.
     */
    public long getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * Sets the remaining time to a desired value.
     *
     * @param timeRemaining The desired value to which the time will be set.
     */
    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    /**
     * Starts the timer.
     */
    public void start() {
        running = true;
    }

    /**
     * Stops the timer.
     */
    public void stop() {
        running = false;
    }

    /**
     * Returns the running state of the timer.
     *
     * @return True if the timer is running, false otherwise.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Returns if time has run out.
     *
     * @return True if time has run out, false otherwise.
     */
    public boolean isOutOfTime() {
        return timeRemaining <= 0;
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers() {
        //System.out.println(timeRemaining);
        for(Observer o: observers) {
            Message msg;
            if(timeRemaining <= 0) {
                msg = new Message("1100");
            } else if (timeRemaining == 100000 ){
                msg = new Message("1001");
            } else {
                msg = new Message("0000");
            }
            o.update(msg);
        }
    }

}
