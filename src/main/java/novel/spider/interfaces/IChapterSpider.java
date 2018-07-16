package novel.spider.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import novel.spider.entity.Chapter;
import novel.spider.util.NovelSiteEnum;



public interface IChapterSpider {
	/**
	 * 给一个url 返回章节列表
	*/
	public List<Chapter> getsChapter(String url);
	
	
}
