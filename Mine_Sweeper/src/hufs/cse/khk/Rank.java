package hufs.cse.khk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * @author gudrbscse
 * @brief Rank : User Ranking Class
 * @details
 * ������ ��ŷ ������ ���ο� user_score �� �����Ͽ�
 * ���Ӱ� ��ŷ ������ �ο��Ѵ�.
 * Level 1~4 �� ���� ��ŷ ������ txt �������� ����Ǿ�����. 
 */
public class Rank {
	/**
	 * @param user_name
	 * @param user_score
	 * @param level
	 * @throws FileNotFoundException
	 * @brief Sort Ranking in Level 1~4
	 * @details 
	 * Level 1~4 �� ���� Ranking ������ ������ 
	 * User Score �� �Բ� Sort �� ���ؼ� 
	 * ���Ӱ� ������ �ű��.
	 */
	static void sort(String user_name, long user_score,int level) throws FileNotFoundException{
			File file = new File("Rank"+level+".txt");
	        long [] score = new long [100];
	        String [] name = new String[100];
	        Map<String, Long> hashMap = new HashMap<String, Long>();
	        int i=0;
	        try {
	            Scanner input = new Scanner(file);
	            while(input.hasNext())
	            {
	            	name[i] = input.next();
	            	score[i] = input.nextInt();
	            	hashMap.put(name[i], score[i]);
	                i++;
	            }
	            input.close();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        hashMap.put(user_name, user_score);
	        
	        Iterator it = sortByKey(hashMap).iterator();
	        
	        file.delete();
	        
	        try{ 
	        	FileWriter fw = new FileWriter(file, true) ;
	        	
	    	    while(it.hasNext()){    	    
	    	    	String value = (String)it.next();
	    	    	long key = hashMap.get(value);
	    	    	fw.write(value+" "+key + "\n");
	    	    	fw.flush();
	    	       
	    	    }//while
	 
	            // ��ü �ݱ�
	            fw.close(); 
	             
	             
	        }catch(Exception e){
	            e.printStackTrace();
	        }	
	}	
	/**
	 * @param map
	 * @return
	 * @brief Sort By Key
	 * @details map �� key �� ���� ���� ���·� sort
	 */
	public static List sortByKey(final Map map){
	        List<String> list = new ArrayList();
	        list.addAll(map.keySet());
	         
	        Collections.sort(list,new Comparator(){
	             
	            public int compare(Object o1,Object o2){
	                Object v1 = map.get(o1);
	                Object v2 = map.get(o2);
	                 
	                return ((Comparable) v1).compareTo(v2);
	            }
	             
	        });
	        return list;
	}	 	
}
