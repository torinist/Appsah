package bean;

import java.io.File;
import java.io.Serializable;

public class MenuBean implements Serializable {

	// menuのID
	private String id;

	// menuのname
	private String name;

	// menuのfile
	private File menufile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getMenufile() {
		return menufile;
	}

	public void setMenufile(File menufile) {
		this.menufile = menufile;
	}

}
