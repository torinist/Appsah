package backingbean;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.servlet.ServletContext;

@Named
@RequestScoped
public class TextResourcesShowBackingBean {

	String text;

	@PostConstruct
	public void init() throws Exception {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		String url = (String) flash.get("resources");

		// ファイルの読み込み
		String path = getRealPath(url);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			String str;
			while((str = br.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
		} catch (Exception e) {
			throw e;
		}
		text = sb.toString();

		System.out.println("TextResourcesShowの" + url);
	}

	// 参考書参照
	public static String getRealPath(String path) {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return ctx.getRealPath(path);
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
