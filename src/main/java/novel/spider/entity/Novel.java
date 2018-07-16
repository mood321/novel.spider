package novel.spider.entity;

import java.io.Serializable;
import java.util.Date;

public class Novel implements Serializable{
	
	private int id;
	

	/**
	 * 书名
	 */
	private String name;
	/**
	 * 作者名
	 */
	private String author;
	/**
	 * 链接
	 */
	private String url;
	
	/**
	 * 类型 武侠。。。
	 */
	private String type;
	/**
	 * 最后的章节名
	 */
	private String lastUpdateChapter;
	
	/**
	 * 最后的章节链接
	 */
	private String lastUpdateChapterUrl;
	
	/**
	 * 最后更新时间
	 */
	private Date lastUpdateTime;
	
	/**
	 * 小说状态   1 连载  2 完结
	 */
	private int status;
	
	/**
	 * 
	 */
	private char firstLetter;
	
	/**
	 * 平台ID
	 */
	private int platformID;
	
	/**
	 * 存储到库时间
	 */
	private Date addTime; 
	
	public Novel() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastUpdateChapter() {
		return lastUpdateChapter;
	}

	public void setLastUpdateChapter(String lastUpdateChapter) {
		this.lastUpdateChapter = lastUpdateChapter;
	}

	public String getLastUpdateChapterUrl() {
		return lastUpdateChapterUrl;
	}

	public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
		this.lastUpdateChapterUrl = lastUpdateChapterUrl;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public char getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(char firstLetter) {
		this.firstLetter = firstLetter;
	}

	public int getPlatformID() {
		return platformID;
	}

	public void setPlatformID(int platformID) {
		this.platformID = platformID;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addTime == null) ? 0 : addTime.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + firstLetter;
		result = prime * result + id;
		result = prime * result + ((lastUpdateChapter == null) ? 0 : lastUpdateChapter.hashCode());
		result = prime * result + ((lastUpdateChapterUrl == null) ? 0 : lastUpdateChapterUrl.hashCode());
		result = prime * result + ((lastUpdateTime == null) ? 0 : lastUpdateTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + platformID;
		result = prime * result + status;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Novel other = (Novel) obj;
		if (addTime == null) {
			if (other.addTime != null)
				return false;
		} else if (!addTime.equals(other.addTime))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (firstLetter != other.firstLetter)
			return false;
		if (id != other.id)
			return false;
		if (lastUpdateChapter == null) {
			if (other.lastUpdateChapter != null)
				return false;
		} else if (!lastUpdateChapter.equals(other.lastUpdateChapter))
			return false;
		if (lastUpdateChapterUrl == null) {
			if (other.lastUpdateChapterUrl != null)
				return false;
		} else if (!lastUpdateChapterUrl.equals(other.lastUpdateChapterUrl))
			return false;
		if (lastUpdateTime == null) {
			if (other.lastUpdateTime != null)
				return false;
		} else if (!lastUpdateTime.equals(other.lastUpdateTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (platformID != other.platformID)
			return false;
		if (status != other.status)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Novel [id=" + id + ", name=" + name + ", author=" + author + ", url=" + url + ", type=" + type
				+ ", lastUpdateChapter=" + lastUpdateChapter + ", lastUpdateChapterUrl=" + lastUpdateChapterUrl
				+ ", lastUpdateTime=" + lastUpdateTime + ", status=" + status + ", firstLetter=" + firstLetter
				+ ", platformID=" + platformID + ", addTime=" + addTime + "]";
	}

	
}
