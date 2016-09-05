package hufs.cse.khk;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 * @author gudrbscse
 * @brief Timer Class
 * @details 
 * User 의 Game 진행 시간을 저장하는 클래스이다.
 */
public class Timer extends JFrame implements Runnable {
	 
    long startTime;
    Thread updater;
    boolean isRunning = false;
    long timer = 0;
    MinesweeperUI minesweeperui;
    
    /**
     * @brief 생성자
     * @param minesweeperui
     * @details 생성자를 통해서 ui class 를 받는다.
     */
    public Timer(MinesweeperUI minesweeperui){
    	this.minesweeperui = minesweeperui;
    }
    /**
     * @brief run timer
     * @details 1초 단위로 시간이 동작하게 하는 함수이다.
     */
    public void run() {
        try {
            while (isRunning) {
                SwingUtilities.invokeAndWait(displayUpdater);
                Thread.sleep(1000);
            }
        } catch (java.lang.reflect.InvocationTargetException ite) {
            ite.printStackTrace(System.err);
        } catch (InterruptedException ie) {
        }
    }

    /**
     * @brief Start timer
     * @details 마우스가 지뢰를 찾기 시작하면 timer 를 시작해주는 함수이다.
     */
    public void Start() {
        startTime = System.currentTimeMillis();
        isRunning = true;
        updater = new Thread(this);
        updater.start();
    }
    /**
     * @brief stop timer
     * @details game 이 끝나면 user 의 time 을 반환해 준다.
     * @return user_time
     */
    public long stop() {
        long user_time = timer;
        isRunning = false;
        try {
            updater.join();
        } catch (InterruptedException ie) {
        }
        displayElapsedTime(user_time);
        timer = 0;
        return user_time;
    }
    /**
     * @brief display update
     * @details UI 의 time display 를 update 하게 해준다.
     */
    private void displayElapsedTime(long user_time) {
        if (user_time >= 0 && user_time < 9) {
        	minesweeperui.time_count.setText("00" + user_time);
        } else if (user_time > 9 && user_time < 99) {
        	minesweeperui.time_count.setText("0" + user_time);
        } else if (user_time > 99 && user_time < 999) {
        	minesweeperui.time_count.setText("" + user_time);
        }
    }
    Runnable displayUpdater = new Runnable() {
        public void run() {
            displayElapsedTime(timer);
            timer++;
        }
    };
}