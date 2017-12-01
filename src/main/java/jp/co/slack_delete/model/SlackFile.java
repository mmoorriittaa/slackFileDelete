package jp.co.slack_delete.model;

import java.util.Date;
import java.util.List;

public class SlackFile {

	String id;
	Long created;
	Long timestamp;
	String name;
	String title;
	String mimetype;
	String filetype;
	String pretty_type;
	String user;
	Boolean editable;
	Integer size;
	String mode;
	Boolean is_external;
	String external_type;
	Boolean is_public;
	Boolean public_url_shared;
	Boolean display_as_bot;
	String username;
	String url_private;
	String url_private_download;
	String permalink;
	String permalink_public;
	List<String> channels;
	List<String> groups;
	List<String> ims;
	Integer comments_count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getPretty_type() {
		return pretty_type;
	}

	public void setPretty_type(String pretty_type) {
		this.pretty_type = pretty_type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Boolean getIs_external() {
		return is_external;
	}

	public void setIs_external(Boolean is_external) {
		this.is_external = is_external;
	}

	public String getExternal_type() {
		return external_type;
	}

	public void setExternal_type(String external_type) {
		this.external_type = external_type;
	}

	public Boolean getIs_public() {
		return is_public;
	}

	public void setIs_public(Boolean is_public) {
		this.is_public = is_public;
	}

	public Boolean getPublic_url_shared() {
		return public_url_shared;
	}

	public void setPublic_url_shared(Boolean public_url_shared) {
		this.public_url_shared = public_url_shared;
	}

	public Boolean getDisplay_as_bot() {
		return display_as_bot;
	}

	public void setDisplay_as_bot(Boolean display_as_bot) {
		this.display_as_bot = display_as_bot;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl_private() {
		return url_private;
	}

	public void setUrl_private(String url_private) {
		this.url_private = url_private;
	}

	public String getUrl_private_download() {
		return url_private_download;
	}

	public void setUrl_private_download(String url_private_download) {
		this.url_private_download = url_private_download;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getPermalink_public() {
		return permalink_public;
	}

	public void setPermalink_public(String permalink_public) {
		this.permalink_public = permalink_public;
	}

	public List<String> getChannels() {
		return channels;
	}

	public void setChannels(List<String> channels) {
		this.channels = channels;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public List<String> getIms() {
		return ims;
	}

	public void setIms(List<String> ims) {
		this.ims = ims;
	}

	public Integer getComments_count() {
		return comments_count;
	}

	public void setComments_count(Integer comments_count) {
		this.comments_count = comments_count;
	}

	public Date getCreatedDate() {
		return new Date(created * 1000L);
	}

	public Date getTimestampDate() {
		return new Date(timestamp * 1000L);

	}
}
