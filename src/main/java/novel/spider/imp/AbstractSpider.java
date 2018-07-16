package novel.spider.imp;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderHttpGet;
import novel.spider.util.NovelSpiderUtil;


public abstract class AbstractSpider {
	
	/**
	 * 通过url抓取网页内容
	 * @author aAa
	 *
	 */
	protected String crawl(String url) throws Exception{
		CloseableHttpResponse httpresponse=null;
		try(CloseableHttpClient httpClient=
				HttpClientBuilder.create().build();){
			//HttpGet httpGet=new HttpGet(url);
			HttpGet httpGet=new NovelSpiderHttpGet(url);//重写httpget 写入超时时间
				httpresponse=httpClient.execute(httpGet);
				String result=
				EntityUtils.toString(httpresponse.getEntity(),
						NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter"));
			return result;
			
			
		}catch(Exception e){
			throw new RuntimeException("crawl 抓取失败");
		}finally {
			httpresponse.close();
		}
		
	}
}
