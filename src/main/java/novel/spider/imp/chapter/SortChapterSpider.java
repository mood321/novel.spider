package novel.spider.imp.chapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import novel.spider.entity.Chapter;

public class SortChapterSpider  extends AbstractChapterSpider{
	@Override
	public List<Chapter> getsChapter(String url) {
		List<Chapter> chapters=super.getsChapter(url);
		
		Collections.sort(chapters, new Comparator<Chapter>() {

			@Override
			public int compare(Chapter o1, Chapter o2) {
				String o1Url=o1.getUrl();
				String o2Url=o2.getUrl();
				String o1Index=o1Url.substring(o1Url.indexOf("/")+1, o1Url.indexOf("."));
				String o2Index=o2Url.substring(o2Url.indexOf("/")+1, o2Url.indexOf("."));
				
				return o1Index.compareTo(o2Index);
			}
			
		});
		
		return chapters;
	}
}
