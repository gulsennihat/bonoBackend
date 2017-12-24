package bono.nihat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController {

	@Autowired
	AnswerService answerService;
	
	 @RequestMapping(value="/answer",  method = RequestMethod.POST)
	    public @ResponseBody Answer getAnswer(@RequestBody Question question) throws Exception {
	    	Answer answer = new Answer();
	    	question.getQuestion();
		 	answer.setAnswer(answerService.getAnswer(question));
	        return answer;
	    }
	    
}
