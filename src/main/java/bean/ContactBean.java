package bean;

public class ContactBean {

	private int id; // コンテンツのID
	private String content; // 内容
	private String writerId; // 投稿者ID
	private String writer; // 投稿者
	private String categoryId; // カテゴリID
	private String categoryName; // カテゴリ名
	private String datetime; // 投稿日時

	public ContactBean(int id, String content, String writerId, String writer,
			String categoryId, String categoryName, String datetime) {
		this.id = id;
		this.content = content;
		this.writerId = writerId;
		this.writer = writer;
		// Calendar calendar = Calendar.getInstance(
		// TimeZone.getTimeZone("Asia/Tokyo"),
		// new Locale("jp", "JP", "JP"));
		// this.datetime = calendar.getTime().toString();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.datetime = datetime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

}
