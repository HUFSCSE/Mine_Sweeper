package hufs.cse.khk;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;
import java.util.List;
 
/**
 * @author gudrbscse
 * @brief Minesweeper UI
 * @details
 * ����ã���� GUI Class �̴�.
 */
public class MinesweeperUI extends JFrame implements ActionListener, ContainerListener {
 
	Minesweeper minesweeper;
	
    JButton[][] blocks;
    ImageIcon[] imageicon = new ImageIcon[11];
    JPanel minepanel = new JPanel();
    JPanel panelmt = new JPanel();
    JTextField mine_count, time_count;
    JButton reset = new JButton("NEW");
    
    Random ranr = new Random();
    Random ranc = new Random();

    Rank ranking = new Rank();
    /**
     * @brief ������
     * @param minesweeper
     * @details 
     * minesweeper Ŭ������ ���ؼ� data �������� �޴´�.
     */
    public MinesweeperUI(Minesweeper minesweeper) {
        super("����ã��");
        this.minesweeper = minesweeper;
        
        setLocation(900, 300);
        setImageIcon();
        setpanel(1, 0, 0, 0);
        setmenu();
        minesweeper.timer = new Timer(this);
 
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                	minesweeper.timer.stop();
                    setpanel(minesweeper.minelevel, minesweeper.savedblockr, minesweeper.savedblockc, minesweeper.savednum_of_mine);
                } catch (Exception ex) {
                    setpanel(minesweeper.minelevel, minesweeper.savedblockr, minesweeper.savedblockc, minesweeper.savednum_of_mine);
                }
                reset();
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * @brief set menu
     * @details
     * menu �� Set ���ش�.
     * Level 1 : Novice
     * Level 2 : Intermediate
     * Level 3 : Expert
     * Level 4 : Customize
     */
    public void setmenu() {
        JMenuBar bar = new JMenuBar();
 
        JMenu game = new JMenu("GAME");
 
        JMenuItem menuitem = new JMenuItem("new game");
        final JCheckBoxMenuItem beginner = new JCheckBoxMenuItem("Novice(Level 1)");
        final JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermediate(Level 2)");
        final JCheckBoxMenuItem expart = new JCheckBoxMenuItem("Expert(Level 3)");
        final JCheckBoxMenuItem custom = new JCheckBoxMenuItem("Customize(Level 4)");
        final JMenuItem exit = new JMenuItem("Exit");
        final JMenu help = new JMenu("Help");
        final JMenuItem helpitem = new JMenuItem("Help");
        
        final JMenuItem rank = new JMenuItem("Rank");
 
        ButtonGroup status = new ButtonGroup();
 
        menuitem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setpanel(1, 0, 0, 0);
                    }
                }
        );
 
        beginner.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
	                	try {
	                		minesweeper.timer.stop();
	                	} catch (Exception ex) {	                    
	                    }
                        minepanel.removeAll();
                        reset();
                        setpanel(1, 0, 0, 0);
                        minepanel.revalidate();
                        minepanel.repaint();
                        beginner.setSelected(true);
                        minesweeper.minelevel = 1;
                    }
                }
        );
        intermediate.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	try {
                    		minesweeper.timer.stop();
	                	} catch (Exception ex) {	                    
	                    }
                        minepanel.removeAll();
                        reset();
                        setpanel(2, 0, 0, 0);
                        minepanel.revalidate();
                        minepanel.repaint();
                        intermediate.setSelected(true);
                        minesweeper.minelevel = 2;
                    }
                }
        );
        expart.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	try {
                    		minesweeper.timer.stop();
	                	} catch (Exception ex) {	                    
	                    }
                        minepanel.removeAll();
                        reset();
                        setpanel(3, 0, 0, 0);
                        minepanel.revalidate();
                        minepanel.repaint();
                        expart.setSelected(true);
                        minesweeper.minelevel = 3;
                    }
                }
        );
 
        custom.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	try {
                    		minesweeper.timer.stop();
	                	} catch (Exception ex) {	                    
	                    }
                        Customization cus = new Customization();
                        reset();
                        minepanel.revalidate();
                        minepanel.repaint();
 
                        custom.setSelected(true);
                        minesweeper.minelevel = 4;
                    }
                }
        );
 
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        
        rank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	final String[] nums = { "1", "2", "3","4" };
            	JFrame frame = new JFrame("Select Level");
                String selectedlevel = (String) JOptionPane.showInputDialog(frame, 
                		null,
                    "Select Level",
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    nums, 
                    nums[0]);
                
            	File file = new File("Rank"+selectedlevel+".txt");
                long [] score = new long [100];
                String [] name = new String[100];
                int i=0;
                try {
                    Scanner input = new Scanner(file);
                    while(input.hasNext())
                    {
                    	name[i] = input.next();
                    	score[i] = input.nextInt();
                        i++;
                    }
                    input.close();
                }
                catch(Exception e1)
                {
                    e1.printStackTrace();
                }
                JLabel labels[] = new JLabel[5];
                for ( i =  0; i < 5; i++) {
                   labels[i] = new JLabel();
                   labels[i].setText(i+1+"��  : "+name[i]+"  ���� : "+score[i]);
                }
                JOptionPane pane = new JOptionPane( labels, 
                		JOptionPane.PLAIN_MESSAGE);
                pane.createDialog(null, "Level "+selectedlevel+" Rank Board").setVisible(true);
                
            }
        });
 
        helpitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "khk : 010-1234-5678");
 
            }
        });
 
        setJMenuBar(bar);
 
        status.add(beginner);
        status.add(intermediate);
        status.add(expart);
        status.add(custom);
 
        game.add(menuitem);
        game.addSeparator();
        game.add(beginner);
        game.add(intermediate);
        game.add(expart);
        game.add(custom);
        game.addSeparator();

        game.add(rank);
        
        game.add(exit);
        
        
        help.add(helpitem);
 
        bar.add(game);
        bar.add(help);
 
        show();
    }


    /**
     * @brief Set Panel
     * @details
     * Level �� ���� Panel �� Set ���ְ� �ϴ� �Լ�.
     */
    public void setpanel(int level, int setr, int setc, int setm) {
        if (level == 1) {
        	minesweeper.fw = 200;
        	minesweeper.fh = 300;
        	minesweeper.blockr = 10;
        	minesweeper. blockc = 10;
        	minesweeper.num_of_mine = 10;
        } else if (level == 2) {
        	minesweeper.fw = 320;
        	minesweeper.fh = 416;
        	minesweeper.blockr = 16;
        	minesweeper.blockc = 16;
        	minesweeper. num_of_mine = 70;
        } else if (level == 3) {
        	minesweeper.fw = 400;
        	minesweeper.fh = 520;
        	minesweeper.blockr = 20;
        	minesweeper.blockc = 20;
        	minesweeper.num_of_mine = 150;
        } else if (level == 4) {
        	minesweeper.fw = (20 * setc);
        	minesweeper.fh = (24 * setr);
        	minesweeper.blockr = setr;
        	minesweeper.blockc = setc;
        	minesweeper.num_of_mine = setm;
        }
 
        minesweeper.savedblockr = minesweeper.blockr;
        minesweeper.savedblockc = minesweeper.blockc;
        minesweeper.savednum_of_mine = minesweeper.num_of_mine;
 
        setSize(minesweeper.fw, minesweeper.fh);
        setResizable(false);
        minesweeper.detectedmine = minesweeper.num_of_mine;
        minesweeper.p = this.getLocation();
 
        blocks = new JButton[minesweeper.blockr][minesweeper.blockc];
        minesweeper.countmine = new int[minesweeper.blockr][minesweeper.blockc];
        minesweeper.colour = new int[minesweeper.blockr][minesweeper.blockc];
        minesweeper.mh = new MouseHandler();
 
        getContentPane().removeAll();
        minepanel.removeAll();
 
        mine_count = new JTextField("" + minesweeper.num_of_mine, 3);
        mine_count.setEditable(false);
        mine_count.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        mine_count.setBackground(Color.BLACK);
        mine_count.setForeground(Color.RED);
        mine_count.setBorder(BorderFactory.createLoweredBevelBorder());
        time_count = new JTextField("000", 3);
        time_count.setEditable(false);
        time_count.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        time_count.setBackground(Color.BLACK);
        time_count.setForeground(Color.RED);
        time_count.setBorder(BorderFactory.createLoweredBevelBorder());
        reset.setBorder(BorderFactory.createLoweredBevelBorder());
 
        panelmt.removeAll();
        panelmt.setLayout(new BorderLayout());
        panelmt.add(mine_count, BorderLayout.WEST);
        panelmt.add(reset, BorderLayout.CENTER);
        panelmt.add(time_count, BorderLayout.EAST);
        panelmt.setBorder(BorderFactory.createLoweredBevelBorder());
 
        minepanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));
        minepanel.setPreferredSize(new Dimension(minesweeper.fw, minesweeper.fh));
        minepanel.setLayout(new GridLayout(0, minesweeper.blockc));
        minepanel.addContainerListener(this);
 
        for (int i = 0; i < minesweeper.blockr; i++) {
            for (int j = 0; j < minesweeper.blockc; j++) {
                blocks[i][j] = new JButton("");
                blocks[i][j].addMouseListener(minesweeper.mh);
                minepanel.add(blocks[i][j]);
            }
        }
        reset();
 
        minepanel.revalidate();
        minepanel.repaint();
 
        getContentPane().setLayout(new BorderLayout());
        getContentPane().addContainerListener(this);
        getContentPane().repaint();
        getContentPane().add(minepanel, BorderLayout.CENTER);
        getContentPane().add(panelmt, BorderLayout.NORTH);
        
        setVisible(true);
    }


    /**
     * @brief Set ImageIcon
     * @details 
     * imageicon[0~8] �� null, 1~8 �� ���� �̹����� ����
     * imageicon 9 �� ���� �̹����� ����.
     * imageicon 10 �� flag �̹����� ����.
     */
    public void setImageIcon() {
        String name;
 
        for (int i = 0; i <= 8; i++) {
            name = "/resources/" + i + ".gif";
            imageicon[i] = new ImageIcon(getClass().getResource("/resources/" + i + ".gif"));
        }
        imageicon[9] = new ImageIcon(getClass().getResource("/resources/mine.gif"));
        imageicon[10] = new ImageIcon(getClass().getResource("/resources/flag.gif"));
    }
   
    /**
     * @brief Reset
     * @details
     * Game �� Reset �Ͽ� ���ο� ������ �ϵ��� �ϰ� ����� �Լ��̴�.
     */
    public void reset() {
    	minesweeper.check = true;
    	minesweeper.starttime = false;
        for (int i = 0; i < minesweeper.blockr; i++) {
            for (int j = 0; j < minesweeper.blockc; j++) {
            	minesweeper.colour[i][j] = 'w';
            }
        }
    }
    
    public void componentAdded(ContainerEvent ce) {
    }
 
    public void componentRemoved(ContainerEvent ce) {
    }
 
    public void actionPerformed(ActionEvent ae) {
    }

    /**
     * @brief Mouse Handler
     * @details
     * ����ã���� Mouse Event Handler Class�̴�.
     * ������ Ŭ���ϸ� SearchMine(c) �Լ��� ���ؼ� dfs �� ���� ������ ������,
     * win_check() �Լ��� ���ؼ� ���ڸ� �� ã�Ҵ��� Ȯ���Ѵ�.
     */
    class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent c) {
            if (minesweeper.check == true) {
                for (int i = 0; i < minesweeper.blockr; i++) {
                    for (int j = 0; j < minesweeper.blockc; j++) {
                        if (c.getSource() == blocks[i][j]) {
                        	minesweeper.var1 = i;
                        	minesweeper.var2 = j;
                            i = minesweeper.blockr;
                            break;
                        }
                    }
                }
                setmine();
                calculation();
                minesweeper.check = false;
            }
            SearchMine(c);
            win_check();
 
            if (minesweeper.starttime == false) {
            	minesweeper.timer.Start();
            	minesweeper.starttime = true;
            }
 
        }
    }

    /**
     * @brief win check
     * @details
     * Game ���� ���̻� ���� ���ڰ� ������ User �� �̰��.
     * User �̸��� �޾Ƽ� Ranking �� ������ �����Ѵ�.
     */
    public void win_check() {
        int q = 0;
        for (int k = 0; k < minesweeper.blockr; k++) {
            for (int l = 0; l < minesweeper.blockc; l++) {
                if (minesweeper.colour[k][l] == 'w') {
                    q = 1;
                }
            }
        }
 
        if (q == 0) {
        	long temp_time = 0;
        	try {
        		temp_time = minesweeper.timer.stop();
        	} catch (Exception ex) {	                    
            }
            for (int k = 0; k < minesweeper.blockr; k++) {
                for (int l = 0; l < minesweeper.blockc; l++) {
                    blocks[k][l].removeMouseListener(minesweeper.mh);
                }
            }
            JTextField name = new JTextField();
            Object[] message = {"name", name};

            JOptionPane pane = new JOptionPane( message, 
            		JOptionPane.PLAIN_MESSAGE, 
            		JOptionPane.OK_CANCEL_OPTION);
            pane.createDialog(null, "Input Name").setVisible(true);
            
            try {
				ranking.sort(name.getText().toString(),temp_time,minesweeper.minelevel);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

        }
    }   

    /**
     * @brief Search Mine
     * @details
     * ���ڸ� ã�� �Լ��̴�. dfs �Լ��� ȣ���Ͽ�
     * Ŭ���� ������ �� �� �ִ� ��� ������ Ž���Ѵ�.
     * ���� Ŭ���� ���� �����̸�, ������ �����Ѵ�.
     */
    public void SearchMine(MouseEvent e) {
        for (int i = 0; i < minesweeper.blockr; i++) {
            for (int j = 0; j < minesweeper.blockc; j++) {
 
                if (e.getSource() == blocks[i][j]) {
                    if (e.isMetaDown() == false) {
                        if (blocks[i][j].getIcon() == imageicon[10]) {
                            if (minesweeper.detectedmine < minesweeper.num_of_mine) {
                            	minesweeper.detectedmine++;
                            }
                            mine_count.setText("" + minesweeper.detectedmine);
                        }
 
                        if (minesweeper.countmine[i][j] == -1) {
                            for (int k = 0; k < minesweeper.blockr; k++) {
                                for (int l = 0; l < minesweeper.blockc; l++) {
                                    if (minesweeper.countmine[k][l] == -1) {
 
                                        blocks[k][l].setIcon(imageicon[9]);
                                        blocks[k][l].removeMouseListener(minesweeper.mh);
                                    }
                                    blocks[k][l].removeMouseListener(minesweeper.mh);
                                }
                            }
                            minesweeper.timer.stop();
                            JOptionPane.showMessageDialog(null, "Game Over");
                        } else if (minesweeper.countmine[i][j] == 0) {
                            dfs(i, j);
                        } else {
                            blocks[i][j].setIcon(imageicon[minesweeper.countmine[i][j]]);
                            minesweeper.colour[i][j] = 'b';
                            break;
                        }
                    } else {
                        if (minesweeper.detectedmine != 0) {
                            if (blocks[i][j].getIcon() == null) {
                            	minesweeper.detectedmine--;
                                blocks[i][j].setIcon(imageicon[10]);
                            }
                            mine_count.setText("" + minesweeper.detectedmine);
                        }
 
                    }
                }
 
            }
        }
 
    }
    /**
     * @brief dfs func
     * @details
     * dfs �˰����� ������ �Լ��̴�.
     * Ŭ���� �������� �̵��� �� �ִ� ������ dfs �˰������� Ž���Ѵ�.
     */
    public void dfs(int row, int col) {
    	 
        int R, C;
        minesweeper.colour[row][col] = 'b';
        blocks[row][col].setBackground(Color.GRAY);
        blocks[row][col].setIcon(imageicon[minesweeper.countmine[row][col]]);
        for (int i = 0; i < 8; i++) {
            R = row + minesweeper.r[i];
            C = col + minesweeper.c[i];
            if (R >= 0 && R < minesweeper.blockr && C >= 0 && C < minesweeper.blockc && minesweeper.colour[R][C] == 'w') {
                if (minesweeper.countmine[R][C] == 0) {
                    dfs(R, C);
                } else {
                    blocks[R][C].setIcon(imageicon[minesweeper.countmine[R][C]]);
                    minesweeper.colour[R][C] = 'b';
                }
            }
        }
    }

    /**
     * @brief calculate value
     * @details
     * ���� �ֺ��� ���ڰ� � �ִ��� ����ϴ� �Լ��̴�.
     * 0~8�� ���� ������.
     */
    public void calculation() {
        int row, column;
        for (int i = 0; i < minesweeper.blockr; i++) {
            for (int j = 0; j < minesweeper.blockc; j++) {
                int value = 0;
                int R, C;
                row = i;
                column = j;
                if (minesweeper.countmine[row][column] != -1) {
                    for (int k = 0; k < 8; k++) {
                        R = row + minesweeper.r[k];
                        C = column + minesweeper.c[k];
 
                        if (R >= 0 && C >= 0 && R < minesweeper.blockr && C < minesweeper.blockc) {
                            if (minesweeper.countmine[R][C] == -1) {
                                value++;
                            }
                        }
                    }
                    minesweeper.countmine[row][column] = value;
                }
            }
        }
    }
 

    /**
     * @brief Set Mine
     * @details
     * Mine �� Random ���� Set ���ִ� �Լ��̴�.
     */
    public void setmine() {
        int row = 0, col = 0;
        Boolean[][] flag = new Boolean[minesweeper.blockr][minesweeper.blockc];
 
        for (int i = 0; i < minesweeper.blockr; i++) {
            for (int j = 0; j < minesweeper.blockc; j++) {
                flag[i][j] = true;
                minesweeper.countmine[i][j] = 0;
            }
        }
 
        flag[minesweeper.var1][minesweeper.var2] = false;
        minesweeper.colour[minesweeper.var1][minesweeper.var2] = 'b';
 
        for (int i = 0; i < minesweeper.num_of_mine; i++) {
            row = ranr.nextInt(minesweeper.blockr);
            col = ranc.nextInt(minesweeper.blockc);
 
            if (flag[row][col] == true) {
 
            	minesweeper.countmine[row][col] = -1;
            	minesweeper.colour[row][col] = 'b';
                flag[row][col] = false;
            } else {
                i--;
            }
        }
    }
 
   

    /**
     * @brief Customize Mode
     * @details
     * ���� ã�� ������ Customize Mode�� ����� �� �ְ� ���ִ� Ŭ�����̴�.
     * �ִ� 30, 30 ũ���� ����ã�� ������ �����ϸ�,
     * ������ �ּ� ������ 10�� �̴�.
     */
    class Customization extends JFrame implements ActionListener {
 
        JTextField t1, t2, t3;
        JLabel lb1, lb2, lb3;
        JButton b1, b2;
        int cr, cc, cm, actionc = 0;
 
        Customization() {
            super("CUSTOMIZETION");
            setSize(180, 200);
            setResizable(false);
            setLocation(minesweeper.p);
 
            t1 = new JTextField();
            t2 = new JTextField();
            t3 = new JTextField();
 
            b1 = new JButton("OK");
            b2 = new JButton("Cencel");
 
            b1.addActionListener(this);
            b2.addActionListener(this);
 
            lb1 = new JLabel("Row");
            lb2 = new JLabel("Column");
            lb3 = new JLabel("mine");
 
            getContentPane().setLayout(new GridLayout(0, 2));
 
            getContentPane().add(lb1);
            getContentPane().add(t1);
            getContentPane().add(lb2);
            getContentPane().add(t2);
            getContentPane().add(lb3);
            getContentPane().add(t3);
 
            getContentPane().add(b1);
            getContentPane().add(b2);
            
            show();
        }
 
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                try {
                    cr = Integer.parseInt(t1.getText());
                    cc = Integer.parseInt(t2.getText());
                    cm = Integer.parseInt(t3.getText());
                    setpanel(4, row(), column(), mine());
                    dispose();
                } catch (Exception any) {
                    JOptionPane.showMessageDialog(this, "Wrong");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                }
            }
 
            if (e.getSource() == b2) {
                dispose();
            }
        }
 
        public int row() {
            if (cr > 30) {
                return 30;
            } else if (cr < 10) {
                return 10;
            } else {
                return cr;
            }
        }
 
        public int column() {
            if (cc > 30) {
                return 30;
            } else if (cc < 10) {
                return 10;
            } else {
                return cc;
            }
        }
 
        public int mine() {
            if (cm > ((row() - 1) * (column() - 1))) {
                return ((row() - 1) * (column() - 1));
            } else if (cm < 10) {
                return 10;
            } else {
                return cm;
            }
        }
    }
}