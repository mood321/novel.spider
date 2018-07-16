package novel.spider.imp.chapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.entity.Chapter;
import novel.spider.imp.AbstractSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;


/**
 * 实现章节列表
 * @author aAa
 *
 */

public class AbstractChapterSpider extends AbstractSpider  implements IChapterSpider{
	
	/* (non-Javadoc)
	 * @see novel.spider.interfaces.IChapterSpider#getsChapter(java.lang.String)
	 */
	@Override
	public List<Chapter> getsChapter(String url) {
		try {
			 
			String result=crawl(url);
			//System.out.println(result);
			Document doc =Jsoup.parse(result);
			//设置成绝对路径
			doc.setBaseUri(url);
			Elements els=doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
			List<Chapter> chapters=new ArrayList<>();
			for (Element a : els) {
				//System.out.println(a);
				Chapter chapter =new Chapter();
				chapter.setTitle(a.text());
				//chapter.setUrl("http://www.biquge.com.tw"+a.attr("href"));
				chapter.setUrl(a.absUrl("href"));
				chapters.add(chapter);
				
			}
			return chapters;
		} catch (Exception e) {
		
			throw new RuntimeException();
		}
		
	}

}
