package novel.spider.util;

import novel.spider.imp.novel.KanSZNovelSpider;
import novel.spider.interfaces.INovelSpider;

public final class NoelSpiderrFactory {
	
	
	/**
	 * 不能被实例化
	 */
	private NoelSpiderrFactory() {
	}
	
	/**
	 * 网站的小说列表的工厂实现类
	 * @param url
	 * @return
	 */
	public static INovelSpider getNovelSpider(String url) {
		NovelSiteEnum  novelSiteEnum=NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case KanShuZhong:return new KanSZNovelSpider();
			
			

		default:
			throw new RuntimeException(url+"暂时不支持");
		}
				
	}
}
