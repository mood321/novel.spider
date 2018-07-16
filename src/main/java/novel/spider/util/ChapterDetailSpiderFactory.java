package novel.spider.util;

import novel.spider.imp.chapter.DefaultChapterSpider;
import novel.spider.imp.chapter.DefaultChpterDetailSpider;
import novel.spider.imp.chapter.SortChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;

public class ChapterDetailSpiderFactory {
	private ChapterDetailSpiderFactory() {
	}
	
	//工厂方法
	/**
	 * 通过url 得到一个ICahpteDetailSpider的实现类
	 * @param url
	 * @return
	 */
	public static IChapterDetailSpider getChapterSpider(String url) {
		NovelSiteEnum novelSiteEnum=NovelSiteEnum.getEnumByUrl(url);
		IChapterDetailSpider cds=null;
		switch (novelSiteEnum) {
		case BiXiaWenXue:
		case DingDianXiaoShuo:	
		case BiQuGe:
		case KanShuZhong:
			cds=new DefaultChpterDetailSpider();
			break;
		
		}
		return cds;
	
	}
	
	
}
