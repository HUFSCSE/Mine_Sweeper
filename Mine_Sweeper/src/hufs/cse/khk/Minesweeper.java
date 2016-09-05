package hufs.cse.khk;

import hufs.cse.khk.MinesweeperUI.MouseHandler;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * @author gudrbscse
 * @brief Minesweeper : Minesweeper data class
 * @details
 *  Minesweeper Ŭ���� ���α׷��� ������ Ŭ������,
 *  UI Ŭ���� �� Minesweeper Game �� �ʿ��� �����Ͱ� ����.
 */
public class Minesweeper {
	int fw, fh, blockr, blockc, var1, var2, num_of_mine, detectedmine = 0, minelevel = 1,
	            savedblockr, savedblockc, savednum_of_mine = 10;
	int[] r = {-1, -1, -1, 0, 1, 1, 1, 0};
	int[] c = {-1, 0, 1, 1, 1, 0, -1, -1};
	int[][] countmine;
	int[][] colour;
	Random ranr = new Random();
	Random ranc = new Random();
	boolean check = true, starttime = false;
	Point framelocation;
	Timer timer;
	MouseHandler mh;
	Point p;
	
	MinesweeperUI minesweeperui;
	
	/**
	 * @brief Create UI
	 * @details
	 * MinsweeperUI Ŭ������ �����Ͽ� 
	 * ����ã�� GUI ������ �����Ѵ�.
	 */
	public Minesweeper(){
		minesweeperui = new MinesweeperUI(this);
	}	 	
}
