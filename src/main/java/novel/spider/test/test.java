package novel.spider.test;

import java.util.HashMap;
import java.util.Map;

public class test {
public static void main(String[] args) {
	Map<String ,Integer> map  =new HashMap<>();
	System.out.println(map.put("id", 1));
	System.out.println(map.put("id", 33));
	System.out.println(map.put("id", 33));
	System.out.println(map.keySet().size());
}
}
