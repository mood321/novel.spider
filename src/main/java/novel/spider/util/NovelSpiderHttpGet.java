package novel.spider.util;

import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;



public class NovelSpiderHttpGet extends HttpGet {

	public NovelSpiderHttpGet() {
		// TODO Auto-generated constructor stub
	}

	public NovelSpiderHttpGet(URI uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}

	public NovelSpiderHttpGet(String uri) {
		super(uri);
		
	}


	/**
	 * 默认时间
	 * @author aAa
	 *
	 */
	
	private void serDefaultConfig() {
		this.setConfig(RequestConfig.custom()
				.setSocketTimeout(100)
				.setConnectTimeout(100)//链接服务器时间
				.setConnectionRequestTimeout(100)//服务器返回时间超时
				.build());
		this.setHeader("User-Agent","NovelSpider");//设置请求头
		System.out.println(this.getConfig().getConnectTimeout()
				);
	}
}
