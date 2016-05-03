package com.stara.crawler.common;

import java.util.Date;

import com.stara.crawler.util.DateFormatter;

/**
 * @see 下载的资源文件命名策略
 * @author Administrator
 *
 */
public enum ResourceStoragePolicy {
	TIMESTAMP{public String geneTag(String suffix){return String.valueOf(System.currentTimeMillis()).concat(suffix);}},
	UUID{public String geneTag(String suffix){return java.util.UUID.randomUUID().toString().concat(suffix);}},
	PATTERDATE{public String geneTag(String suffix){return DateFormatter.format(new Date(), "yyyyMMddHHmmss").concat(suffix);}};//时间戳,UUID,原生
	public abstract String geneTag(String suffix);
}
