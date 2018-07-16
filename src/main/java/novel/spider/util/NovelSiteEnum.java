package novel.spider.util;

/**
 * 
 * 小说支持的小说网站枚举
 * @author aAa
 *
 */
public enum NovelSiteEnum {
	DingDianXiaoShuo(1,"x23us.com"),	
	BiQuGe(2,"biquge.com.tw"),
	
	BiXiaWenXue(3,"www.bixia.org"),
	KanShuZhong(4,"www.kanshuzhong.com");
	private  int id;
	private String url;
	private NovelSiteEnum(int id,String url) {
		this.id=id;
		this.url=url;
	
	}
	
	public static NovelSiteEnum getEnumById(int id) {
		switch (id) {
		case 1:
			return DingDianXiaoShuo;
			
		case 2:
			return BiQuGe;
		case 3:
			return BiXiaWenXue;
		case 4:
			return KanShuZhong;
			
		default:
			throw new RuntimeException(id+"是不被支持的小说网站");
		}
		
	}
	//通过url
	public static NovelSiteEnum getEnumByUrl(String  url) {
		//匹配
		for (NovelSiteEnum novelSiteEnum : values()) {
			if(url.contains(novelSiteEnum.url)) 
			{
				
				return novelSiteEnum;
			}
		}
		throw new RuntimeException(url+"是不被支持的小说网站");
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
