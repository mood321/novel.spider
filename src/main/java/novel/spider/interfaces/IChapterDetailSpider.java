package novel.spider.interfaces;

import novel.spider.entity.ChapterDetail;


public interface IChapterDetailSpider {
	/**
	 * 给一个url 返回一个章节实体
	 * @author aAa
	 *
	 */
	public ChapterDetail  getChapterDetail(String url);
	 
}
