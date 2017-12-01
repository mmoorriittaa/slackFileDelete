package jp.co.slack_delete.model;

import java.util.List;

public class SlackFileList {

	Boolean ok;
	List<SlackFile> files;
	Paging paging;

	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public List<SlackFile> getFiles() {
		return files;
	}

	public void setFiles(List<SlackFile> files) {
		this.files = files;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

}
