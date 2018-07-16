package novel.spider.test;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import configuration.Configuration;
import novel.spider.entity.Chapter;
import novel.spider.entity.Novel;
import novel.spider.imp.chapter.AbstractChapterSpider;
import novel.spider.imp.chapter.DefaultChapterSpider;
import novel.spider.imp.chapter.DefaultChpterDetailSpider;
import novel.spider.imp.chapter.SortChapterSpider;
import novel.spider.imp.download.NovelDownload;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.INovelDownLoad;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NoelSpiderrFactory;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;


public class Testcase {
	@Test
	/**
	 * 测试章节列表
	*/
	public void testChapter() {
		AbstractChapterSpider acs=new DefaultChapterSpider();
		String url="https://www.bixia.org/148_148399/";	
		List<Chapter> chapters=acs.getsChapter(url);
		for (Chapter chapter : chapters) {
			System.out.println(chapter);
		}
		
	}
	/**
	 * 
	 */
	@Test
	
	public void testGetSiteContext() {
		System.out.println(
				NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl
						("https://www.x23us.com/html/54/54811/"))
				);
		
	}
	
	
	/**
	 * 内容
	 * @author aAa
	 *
	 */
	@Test
	public void getChapter() {
		IChapterDetailSpider icds=new DefaultChpterDetailSpider();
		System.out.println(icds.getChapterDetail("https://www.bixia.org/148_148399/7586659.html").getContent());;
	}
	/**
	 * 测试比较器
	 * @author aAa
	 *
	 */
	@Test
	public void getSortChapterDetail() {
		AbstractChapterSpider acs=new SortChapterSpider();
		String url="https://www.bixia.org/148_148399/";	
		List<Chapter> chapters=acs.getsChapter(url);
		for (Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}
	/**
	 * 小说下载测试
	 * @author aAa
	 *
	 */
	@Test
	public void getDownload() {
		INovelDownLoad down=new NovelDownload();
		Configuration config=new Configuration();
		config.setPath("E:/1");
		System.out.println("下载完毕 ，保存地址："+
				down.download("http://www.biquge.com.tw/0_213/", config)
				);
	}
	/**
	 * 测试文件复制方法
	 */
	@Test
	public void getTxt() {
		NovelSpiderUtil.multiFileMeange("E:/1", null,false);
	}
	
	/**
	 * 测试看书中获取小说列表
	 */
	@Test
	public void getKanshuzhong() {
		String url="http://www.kanshuzhong.com/booktype/1/1/";
		INovelSpider novelSpider=NoelSpiderrFactory.getNovelSpider(url);
		List<Novel> novels=novelSpider.getNovel(url,3);
		for (Novel novel : novels) {
			System.out.println(novel);
		}
	}
	/**
	 * 看书中
	 * 对小说网站下一页 确认和抓取
	 */
	@Test
	public void testKanshuzhongIterator() {
		String url="http://www.kanshuzhong.com/map/E/1/";
		INovelSpider novelSpider=NoelSpiderrFactory.getNovelSpider(url);
		/*novelSpider.getNovel(url);
		System.out.println(novelSpider.hasNext());
		System.out.println(novelSpider.next());*/
		Iterator<List<Novel>> iterator=novelSpider.iterator(url,3);
		while(iterator.hasNext()) {
			List<Novel> novels=iterator.next();
			System.out.println(novelSpider.next());
			/*for (Novel novel : novels) {
				System.out.println(novel);
			}*/
		}
	}
}
