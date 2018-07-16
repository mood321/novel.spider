package novel.spider.interfaces;

import configuration.Configuration;

public interface INovelDownLoad {
	
	/**
	 * 
	 * 通过章节url 下载小说
	 * @author aAa
	 *
	 */
	public String download(String url,Configuration config);
	
}
