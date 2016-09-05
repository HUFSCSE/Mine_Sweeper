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
 * 기존의 랭킹 순위에 새로운 user_score 를 정렬하여
 * 새롭게 랭킹 순위를 부여한다.
 * Level 1~4 에 따른 랭킹 순위가 txt 형식으로 저장되어진다. 
 */
public class Rank {
	/**
	 * @param user_name
	 * @param user_score
	 * @param level
	 * @throws FileNotFoundException
	 * @brief Sort Ranking in Level 1~4
	 * @details 
	 * Level 1~4 에 따른 Ranking 순위를 가져와 
	 * User Score 와 함께 Sort 를 통해서 
	 * 새롭게 순위를 매긴다.
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
	 
	            // 객체 닫기
	            fw.close(); 
	             
	             
	        }catch(Exception e){
	            e.printStackTrace();
	        }	
	}	
	/**
	 * @param map
	 * @return
	 * @brief Sort By Key
	 * @details map 의 key 에 따른 증가 형태로 sort
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
