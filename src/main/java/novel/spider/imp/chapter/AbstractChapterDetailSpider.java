package novel.spider.imp.chapter;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import novel.spider.entity.ChapterDetail;
import novel.spider.imp.AbstractSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider{

	@Override
	public ChapterDetail getChapterDetail(String url) {
		try {
			String result=super.crawl(url);
			result.replaceAll("&nbsp", " ");
			result.replaceAll("<br/>", "{line}").replaceAll("<br />", "{line}");
			//String 转doc
			Document doc=Jsoup.parse(result);
			//转为绝对地址
			doc.setBaseUri(url);
			Map<String, String > content=
					NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
			
			//标题
			String titleSelector=content.get("chapter-detail-title-selector");
			String[] splits=titleSelector.split("\\,");
			splits=parseSelector(splits);
			
			ChapterDetail cd=new ChapterDetail();
			//标题名称
			cd.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
			
			//内容
			String contentSelector=content.get("chapter-detail-content-selector");
			//对象内容赋值
			cd.setContent(doc.select(contentSelector).first().text().replace("{line}", "\n"));
			
			//上一张地址
			String prevSelector=content.get("chapter-detail-prev-selector") ;
			
			String[] prevSplits=prevSelector.split("\\,");
			prevSplits=parseSelector(prevSplits);
			
			cd.setPrev(doc.select(prevSplits[0]).get(Integer.parseInt(prevSplits[1])).absUrl("href"));
			
			//下一张地址
			String nextSelector=content.get("chapter-detail-next-selector") ;
			
			String[] nextSplits=nextSelector.split("\\,");
			nextSplits=parseSelector(nextSplits);
			//System.out.println(doc.select(nextSplits[0]).get(Integer.parseInt(nextSplits[1])).text());
			cd.setNext(doc.select(nextSplits[0]).get(Integer.parseInt(nextSplits[1])).absUrl("href"));
			
			return cd;
		} catch (Exception e) {
			
			throw new RuntimeException("抓取失败");
		}
		
		
	}
	/**
	 * 
	*/
	private String[] parseSelector(String[] splits) {
		String[] newSplits=new String[2];
		if(splits.length==1) {
			newSplits[0]=splits[0];
			newSplits[1]="0";
			return newSplits;
		}else {
			return splits;
		}
	}
}
