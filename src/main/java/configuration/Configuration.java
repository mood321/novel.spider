package configuration;

import java.io.Serializable;


public class Configuration implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7103449195795360195L;


	public static final int DEFAULT_SIZE=100;
	
	
	/**
	 * 默认尝试次数
	 */
	public static final int TRYTIMES=3;
	/**
	 * 下载文件的基地址
	 *
	 */
	private String path;
	/*
	 * 每个线程下载张数
	*/
	private int size;
	
	private int tryTimes;
	
	
	public Configuration() {
		this.size=DEFAULT_SIZE;
		this.tryTimes=TRYTIMES;
	}
	public int getTryTimes() {
		return tryTimes;
	}

	public void setTryTimes(int tryTimes) {
		this.tryTimes = tryTimes;
	}

	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	} 
	
	
}
