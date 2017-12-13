package bono.nihat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class AnswerService implements IAnswerService {
	
	@Value("classpath:answers.properties")
    private Resource res;
	 java.util.List<String> lines = new ArrayList<String>();
	 
	  public  Map<Integer, String> ReadAnswerMap()
	    		throws Exception
	    		{
	    		  Map<Integer, String> map = new HashMap();
	    		
	    		  final Resource fileResource = res;
	    		  
	    		  lines  = Files.readAllLines(Paths.get(res.getURI()), StandardCharsets.UTF_8);
	    		
	    		  BufferedReader reader = new BufferedReader(new InputStreamReader(fileResource.getInputStream()));
	    		  String line;
	    		  int key = 0;
	    		  while ((line = reader.readLine()) != null)
	    		  {
	    		    map.put(key, line);
	    		    key++;
	    		  }
	    		  reader.close();
	    		  return map;
	    		}
}
