package bono.nihat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController {


    @RequestMapping("/answer")
    public Answer greeting() {
    	Answer answer = new Answer();
    	answer.setAnswer("Her zaman imkansizi hayal ediyorsun.");
        return answer;
    }
}
