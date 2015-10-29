package backingbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import logic.ContactService;
import logic.WriterService;
import bean.ContactBean;
import bean.WriterBean;

@Named
@RequestScoped
public class ContactEditBean {

	/**
	 *
	 */

	@NotNull
	private String content;

	// contentを登録した人のID
	private String writerId;

	// TODO: Mapにしたらどうだろうか。そうするとIDとnameが紐づいて楽なんだけどなあ
	private List<WriterBean> writerIds;

	@Inject
	private WriterService writerService;

	@Inject
	ContactService contactService;

	@PostConstruct
	public void init() {
		System.out.println("!!!! init開始");
		displayInit();
	}

	/*
	 *  submitボタン押下時のメソッド
	 */
	public void edit() {
		System.out.println("writerId: " + writerId + ", content: " + content);
		contactService.contactAdd(new ContactBean(0, content, writerId, null, null, null, null));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Complete."));
		displayInit();
	}

	/*
	 * 画面部品を初期化するメソッド
	 */
	private void displayInit() {
		content = null;
		writerId = null;
		writerIds = writerService.writerCreate();
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

	public List<WriterBean> getWriterIds() {
		return writerIds;
	}

	public void setWriterIds(List<WriterBean> writerIds) {
		this.writerIds = writerIds;
	}

}
