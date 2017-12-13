package bono.nihat;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController {

	@Autowired
	AnswerService answerService;
	
	 @RequestMapping("/answer")
	    public Answer greeting() throws Exception {
	    	Answer answer = new Answer();
	    	Map<Integer, String> answerMap = new HashMap();
	    	
	    	answerMap = answerService.ReadAnswerMap();
	    	Random rand = new Random(); 
	    	Integer rnd = rand.nextInt(answerMap.size()); 

	    	answer.setAnswer(answerMap.get(rnd));
	        return answer;
	    }
	    
	    
}
