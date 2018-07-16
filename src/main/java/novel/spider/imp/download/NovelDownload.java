package novel.spider.imp.download;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;


import configuration.Configuration;
import novel.spider.entity.Chapter;
import novel.spider.entity.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownLoad;
import novel.spider.util.ChapteSpiderFactory;
import novel.spider.util.ChapterDetailSpiderFactory;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;




public class NovelDownload implements INovelDownLoad{

	/**
	 * 通过线程下载小说
	 * @author aAa
	 *
	 */
	

	@Override
	public String download(String url, Configuration config) {
		IChapterSpider cs=
				ChapteSpiderFactory.getChapterSpider(url);
		List<Chapter> chapters =cs.getsChapter(url);
				
		//线程下载完毕 返回值
		//所有线程完成 合并
		int size=config.getSize();
		//默认100章
		int maxThreadSize=(int)Math.ceil(chapters.size()*1.0/size);
		Map<String,List<Chapter>> downloadTaskAlloc=new HashMap<>();
		for (int i = 0; i < maxThreadSize; i++) {
			//i=0 0-99 0-99.txt
			//i=1 100-199 100-199.txt
			//i=2 200-299 200-299.txt
			//...
			//i=20  2000-2099 2000-2099.txt
			//一共2053
			int formIndex=i*(config.getSize());
			int endIndex= 
					i==maxThreadSize-1?chapters.size()
							:i*(config.getSize())+config.getSize();
			
			
			downloadTaskAlloc.put(formIndex+"-"+endIndex,
									chapters.subList(formIndex, endIndex));
			
		}
		//创建线程池
		ExecutorService service=
				Executors.newFixedThreadPool(maxThreadSize);
		//map 所有key
		Set<String> keyset=downloadTaskAlloc.keySet();
		//线程池返回集合
		List<Future<String>> tasks=new ArrayList<>();
		//创建对应文件夹
		String savePath=config.getPath()+"/"
						+NovelSiteEnum.getEnumByUrl(url)
						.getUrl();
		new File(savePath).mkdirs();
		//循环添加线程
		for (String string : keyset) {
			
			tasks.add( service.submit(new DownloadCallable(
					savePath+"/"+string +".txt", 
					downloadTaskAlloc.get(string),3)));
		}
		service.shutdown();
		//线程返回结果集
		for (Future<String> future : tasks) {
			try {
				System.out.println(future.get()+"下载完成");
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		NovelSpiderUtil.multiFileMeange(savePath, null,true);
		
		return savePath+"mrogo.txt";
	}

}
class DownloadCallable implements Callable<String> {
	private List<Chapter> chapters;
	private String path;
	private int times;
	public DownloadCallable(String path,List<Chapter> chapters,int times) {
		this.path=path;
		this.chapters=chapters;
		this.times=times;
	}
	
	@Override
	public String call() throws Exception {
		try(
				PrintWriter out=new PrintWriter(new File(path),"UTF-8")
			){
			for (Chapter chapter : chapters) {
				IChapterDetailSpider cds=
						ChapterDetailSpiderFactory
							.getChapterSpider(chapter.getUrl());
				ChapterDetail cd=null;
				for (int i = 0; i < times; i++) {
					try {
						cd=cds.getChapterDetail(chapter.getUrl());
						out.println(cd.getTitle());
						out.println(cd.getContent());
						break;
					} catch (RuntimeException e) {
						System.out.println("尝试"+i+"次time失败");
					}
				}
			}
			
		}catch(IOException e){
			throw new RuntimeException();
		}
		
		return path;
	}
	
	
}

