package backingbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import bean.WriterBean;
import logic.WriterService;

@Named
@ViewScoped
public class ContactEditBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private String content;

	// contentを登録した人のID
	private String writerId;

	// TODO: Mapにしたらどうだろうか。そうするとIDとnameが紐づいて楽なんだけどなあ
	private List<WriterBean> writerIds;

	@Inject
	private WriterService writerService = new WriterService();

	@PostConstruct
	public void init() {
		System.out.println("!!!! init開始");
		content = null;
		writerId = null;
		writerIds = writerService.writerCreate();
	}

	// submitボタン押下時のメソッド
	public void edit() {
		System.out.println("writerId: " + writerId + ", content: " + content);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Complete."));
		init();
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
