package bono.nihat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.openshift.internal.client.utils.StringUtils;

@Component
public class AnswerService implements IAnswerService {

	@Autowired
	AnswerDao answerDao;

	static Queue<String> queue = new LinkedList<String>();
	
	@Deprecated
	public Map<Integer, String> ReadAnswerMap() throws Exception {

		Map<Integer, String> map = new HashMap();

		InputStream inputStream = AnswerService.class.getResourceAsStream("/answers.properties");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String line;
		int key = 0;
		while ((line = reader.readLine()) != null) {
			map.put(key, line);
			key++;
		}
		reader.close();
		return map;
	}

	public String getAnswer(Question question) throws Exception {

		String answer = answerDao.findAnswer();
		while (!checkAnswer(answer)) {
			answer = answerDao.findAnswer();
		}
		answer = checkWords(question.getQuestion(), answer);
		return answer;

	}

	public static boolean checkAnswer(String answer) {
		
		if(queue.size()>10){
			queue.poll();
		}
		
		for (Object o : queue) {
			if (o.toString().equals(answer)) {
				return false;
			}
		}
		queue.add(answer);
		return true;
	}
	
	public static String checkWords(String question, String answer){
		question.trim();
		String[] questionWords = question.trim().toLowerCase().split(" ");
		
		String[] tarih = {"tarih", "yil","yıl", "sene", "zaman", "gun" , "gün", "ay", "saat", "dakika", "nezaman", "kadar" };
		String[] others = {"isim", "adı", "ad", "yaş", "boy", "kilo", "ismi", "soyad", "soyadı", "renk", "rengi","kilom","kilosu", "kiloyum", "kaç", "hangi", "ne"};
		
		for (String questionWord : questionWords) {
			for (String tarihWord : tarih) {
				if(questionWord.contains(tarihWord)){ //.equals(tarihWord)
					answer="Bana tarih ve zaman sormamalısın.. Bunu cevaplayarak dünyanın dengesiyle oynayamam!";
					return answer;
				}
			}
			
			for (String otherWord : others) {
				if(otherWord.contains(questionWord)){ //equals(questionWord)
					answer="Bu sorunun tarzı hoşuma gitmedi. Böyle net cevaplar gerektiren sorulara cevap vererek bütün dünyanın dikkatini çekmek istemiyorum!";
					return answer;
				}
			}
		}
		
		
		return answer;
		
		
	}

}
