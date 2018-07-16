package novel.spider.imp.novel;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.entity.Novel;
import novel.spider.imp.AbstractSpider;
import novel.spider.imp.novel.AbstractNovelSpider.Noveliterator;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

/**
 * 
 * 一个抽象小说列表抓取
 * 实现解析trs的方法
 * @author aAa
 *
 */
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider {
	/**
	 * 下一页url
	 * 并允许子类继承
	 */
	protected  String nextPage;
	/**
	 * 代表下一頁的值
	 */
	protected Element nextPageElement;
	
	/**
	 * 默认抓取小说列表的方法
	 * 最多尝试{@link INovelSpider#MAX_TRY_TIMES} 次
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected Elements getsTr(String url) throws Exception {
		
		return getsTr(url,INovelSpider.MAX_TRY_TIMES);
		
	}
	/**
	 * 以指定次数 形式抓取小说列表
	 * @param url
	 * @param maxTryTimes 若果为null 默认值为{@link INovelSpider#MAX_TRY_TIMES}
	 * @return
	 */
	protected Elements getsTr(String url,Integer maxTryTimes) {
		maxTryTimes=maxTryTimes==null?INovelSpider.MAX_TRY_TIMES:maxTryTimes;
		Elements trs=null;
		for (int i = 0; i < maxTryTimes; i++) {
			try {
				String result=super.crawl(url);
				Map<String ,String> content=NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
				String novelSelector=content.get("novel-selector");
				if(novelSelector==null)
					throw new RuntimeException("目前不支持该小说网站的列表");
				Document doc=Jsoup.parse(result);
				doc.setBaseUri(url);
				trs=doc.select(novelSelector);
				
				
				//是否有下一頁
				String nextPageSelector=content.get("novel-next-page-selector");
				if(nextPageSelector!=null) {
					Elements nextPageElements=doc.select(nextPageSelector);
					nextPageElement=nextPageElements==null?null:nextPageElements.first();
					//
					if (nextPageElement != null) {
						nextPage = nextPageElement.absUrl("href");

					} else {
						nextPage = "";
					}
				}
				return trs;
			}catch(Exception e){
				
			}
		}
		throw new RuntimeException(url+"尝试了"+maxTryTimes+"失败了");
		
		
	}
	@Override
	public boolean hasNext() {
		return !nextPage.isEmpty();
	}

	@Override
	public String next() {
		return nextPage;
	}
	
	@Override
	public Iterator<List<Novel>> iterator(String fristPage,Integer maxTryTimes) {
		nextPage=fristPage;
		return new Noveliterator(maxTryTimes);
	}
	/**
	 * 一个迭代器  对小说列表下一页的抓取
	 * @author aAa
	 *
	 */
	protected class Noveliterator implements Iterator<List<Novel>> {
		private Integer maxTryTimes;
		public Noveliterator(Integer maxTryTimes) {
			this.maxTryTimes=maxTryTimes;
		}
		@Override
		public boolean hasNext() {
			// 內部類調用外部同名方法
			return AbstractNovelSpider.this.hasNext();
		}

		@Override
		public List<Novel> next() {
			List<Novel> novels=getNovel(nextPage,maxTryTimes);

			return novels;
		}
	}
}
