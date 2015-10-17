package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import bean.WriterBean;

@ApplicationScoped
public class WriterService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<WriterBean> writerCreate() {
		List<WriterBean> writers = new ArrayList<WriterBean>();
		writers.add(new WriterBean("00001", "aoki"));
		writers.add(new WriterBean("00002", "legend"));
		writers.add(new WriterBean("00003", "enomoto"));
		writers.add(new WriterBean("00004", "kaneki"));
		writers.add(new WriterBean("00005", "haise"));
		writers.add(new WriterBean("00006", "reo"));
		
		return writers;
	}
}
