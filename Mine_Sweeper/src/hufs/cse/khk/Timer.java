package hufs.cse.khk;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 * @author gudrbscse
 * @brief Timer Class
 * @details 
 * User �� Game ���� �ð��� �����ϴ� Ŭ�����̴�.
 */
public class Timer extends JFrame implements Runnable {
	 
    long startTime;
    Thread updater;
    boolean isRunning = false;
    long timer = 0;
    MinesweeperUI minesweeperui;
    
    /**
     * @brief ������
     * @param minesweeperui
     * @details �����ڸ� ���ؼ� ui class �� �޴´�.
     */
    public Timer(MinesweeperUI minesweeperui){
    	this.minesweeperui = minesweeperui;
    }
    /**
     * @brief run timer
     * @details 1�� ������ �ð��� �����ϰ� �ϴ� �Լ��̴�.
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
     * @details ���콺�� ���ڸ� ã�� �����ϸ� timer �� �������ִ� �Լ��̴�.
     */
    public void Start() {
        startTime = System.currentTimeMillis();
        isRunning = true;
        updater = new Thread(this);
        updater.start();
    }
    /**
     * @brief stop timer
     * @details game �� ������ user �� time �� ��ȯ�� �ش�.
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
     * @details UI �� time display �� update �ϰ� ���ش�.
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