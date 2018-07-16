package novel.spider.imp.novel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.entity.Novel;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

/**
 * 看书中的小说列表爬取
 * 
 * @author aAa
 *
 */
public class KanSZNovelSpider extends AbstractNovelSpider {
	

	public KanSZNovelSpider() {

	}

	@Override
	public List<Novel> getNovel(String url,Integer maxTryTimes) {
		Elements trs = super.getsTr(url, maxTryTimes);
		List<Novel> list = new ArrayList<>();
		for (int i = 1, size = trs.size(); i < size - 1; i++) {
			Element tr = trs.get(i);
			Elements tds = tr.getElementsByTag("td");
			Novel novel = new Novel();
			// 小说名字
			novel.setName(tds.get(1).text());
			// 对小说列表的小说链接处理
			String novelUrl = tds.get(1).getElementsByTag("a").first().absUrl("href");

			// 测试获取值
			// System.out.println(trs.get(81));

			novel.setUrl(novelUrl);
			novel.setLastUpdateChapter(tds.get(2).text());

			novel.setLastUpdateChapterUrl(tds.get(2).getElementsByTag("a").first().absUrl("href"));

			novel.setAuthor(tds.get(3).text());
			// 更新时间
			novel.setLastUpdateTime(NovelSpiderUtil.getDate(tds.get(4).text(), "MM-dd"));// 时间格式07-14
			novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));

			novel.setType(tds.get(0).text());

			novel.setPlatformID(NovelSiteEnum.getEnumByUrl(url).getId());

			list.add(novel);

		}
		
		return list;
	}

	

	

	



}
