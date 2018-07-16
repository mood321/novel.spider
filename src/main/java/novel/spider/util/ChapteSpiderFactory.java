package novel.spider.util;

import novel.spider.imp.chapter.DefaultChapterSpider;
import novel.spider.imp.chapter.SortChapterSpider;
import novel.spider.interfaces.IChapterSpider;

public final class ChapteSpiderFactory {
	private ChapteSpiderFactory() {
	}
	
	//工厂方法
	/**
	 * 通过url 得到一个ICahpteSpider的实现类
	 * @param url
	 * @return
	 */
	public static IChapterSpider getChapterSpider(String url) {
		NovelSiteEnum novelSiteEnum=NovelSiteEnum.getEnumByUrl(url);
		IChapterSpider cs=null;
		switch (novelSiteEnum) {
		case BiXiaWenXue:
			cs=new SortChapterSpider();
			break;

		case DingDianXiaoShuo:	
		case BiQuGe:	
		case KanShuZhong:
			cs=new DefaultChapterSpider();
			break;
		
		}
		return cs;
		
	}
}
