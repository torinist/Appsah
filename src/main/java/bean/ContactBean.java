package bean;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ContactBean {

	private String content; // 内容
	private String writer; // 投稿者
	private String datetime; // 投稿日時

	public ContactBean(String content, String writer) {
		this.content = content;
		this.writer = writer;
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), new Locale("jp", "JP", "JP"));
		datetime = calendar.getTime().toString();
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

}
