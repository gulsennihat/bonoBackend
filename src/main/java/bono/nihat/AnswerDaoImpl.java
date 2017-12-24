package bono.nihat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class AnswerDaoImpl implements AnswerDao {

	public String findAnswer() throws Exception {
		Map<Integer, String> map = new HashMap();
		String answer;
			
		InputStream inputStream = AnswerDaoImpl.class.getResourceAsStream("/answers.properties");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String line;
		
		int key = 0;
		while ((line = reader.readLine()) != null) {
			map.put(key, line);
			key++;
		}
		reader.close();
		
		Random rand = new Random(); 
    	Integer rnd = rand.nextInt(map.size()); 
    	answer = map.get(rnd);
    	
    	return answer;
	}
	
	public void writeQuestion(Question question) {
	}
	
}
