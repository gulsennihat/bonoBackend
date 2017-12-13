package bono.nihat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController {

	
    @RequestMapping("/answer")
    public Answer greeting() throws Exception {
    	Answer answer = new Answer();
    	Map<Integer, String> answerMap = new HashMap();
    	answerMap = readPropertiesFileAsMap("answers.properties");
    	Random rand = new Random(); 
    	Integer rnd = rand.nextInt(answerMap.size()); 

    	answer.setAnswer(answerMap.get(rnd));
        return answer;
    }
    
    public static Map<Integer, String> readPropertiesFileAsMap(String filename)
    		throws Exception
    		{
    		  Map<Integer, String> map = new HashMap();
    		  BufferedReader reader = new BufferedReader(new FileReader(filename));
    		  String line;
    		  int key = 0;
    		  while ((line = reader.readLine()) != null)
    		  {
//    		    if (line.trim().length()==0) continue;
//    		    if (line.charAt(0)=='#') continue;
//    		    // assumption here is that proper lines are like "String : http://xxx.yyy.zzz/foo/bar",
//    		    // and the ":" is the delimiter
//    		    int delimPosition = line.indexOf(delimiter);
//    		    String key = line.substring(0, delimPosition-1).trim();
//    		    String value = line.substring(delimPosition+1).trim();
    		    map.put(key, line);
    		    key++;
    		  }
    		  reader.close();
    		  return map;
    		}
}
