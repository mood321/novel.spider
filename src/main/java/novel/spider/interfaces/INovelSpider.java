package novel.spider.interfaces;

import java.util.Iterator;
import java.util.List;

import novel.spider.entity.Novel;

/**
 * 爬取某站点的小说列表
 * @author aAa
 *
 */
public interface INovelSpider {
	/**
	 * 抓取尝试最大次数
	 */
	public static final int MAX_TRY_TIMES=3;
	/**
	 * 通过url 得到小说列表集合
	 * @param url
	 * @return
	 */
	public List<Novel> getNovel(String url,Integer maxTryTimes);
	
	/**
	 * 小说列表是否有下一页
	 * @return
	 */
	public boolean hasNext();
	
	public String next();
	
	public Iterator<List<Novel>> iterator(String dristPage,Integer maxTryTimes);
}
