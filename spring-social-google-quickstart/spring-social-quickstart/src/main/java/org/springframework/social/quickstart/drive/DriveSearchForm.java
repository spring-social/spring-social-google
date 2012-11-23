package org.springframework.social.quickstart.drive;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DriveSearchForm {

	private String pageToken;
	
	private boolean negate;
	
	private String titleIs;
	
	private String titleContains;
	
	private String fullTextContains;
	
	private String mimeTypeIs;
	
	private DateOperators modifiedOperator;
	
	@DateTimeFormat(iso=DATE)
	private Date modifiedValue;
	
	private DateOperators lastViewedOperator;
	
	@DateTimeFormat(iso=DATE)
	private Date lastViewedValue;
	
	private OptionalBoolean trashed;
	
	private OptionalBoolean starred;
	
	private OptionalBoolean hidden;
	
	private String parentId;
	
	private String owner;
	
	private String writer;
	
	private String reader;
	
	private boolean sharedWithMe;

	public String getPageToken() {
		return pageToken;
	}

	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}
	
	public boolean isNegate() {
		return negate;
	}

	public void setNegate(boolean negate) {
		this.negate = negate;
	}

	public String getTitleIs() {
		return titleIs;
	}

	public void setTitleIs(String titleIs) {
		this.titleIs = titleIs;
	}

	public String getTitleContains() {
		return titleContains;
	}

	public void setTitleContains(String titleContains) {
		this.titleContains = titleContains;
	}

	public String getFullTextContains() {
		return fullTextContains;
	}

	public void setFullTextContains(String fullTextContains) {
		this.fullTextContains = fullTextContains;
	}

	public String getMimeTypeIs() {
		return mimeTypeIs;
	}

	public void setMimeTypeIs(String mimeTypeIs) {
		this.mimeTypeIs = mimeTypeIs;
	}

	public DateOperators getModifiedOperator() {
		return modifiedOperator;
	}

	public void setModifiedOperator(DateOperators modifiedOperator) {
		this.modifiedOperator = modifiedOperator;
	}

	public Date getModifiedValue() {
		return modifiedValue;
	}

	public void setModifiedValue(Date modifiedValue) {
		this.modifiedValue = modifiedValue;
	}

	public DateOperators getLastViewedOperator() {
		return lastViewedOperator;
	}

	public void setLastViewedOperator(DateOperators lastViewedOperator) {
		this.lastViewedOperator = lastViewedOperator;
	}

	public Date getLastViewedValue() {
		return lastViewedValue;
	}

	public void setLastViewedValue(Date lastViewedValue) {
		this.lastViewedValue = lastViewedValue;
	}

	public OptionalBoolean getTrashed() {
		return trashed;
	}

	public void setTrashed(OptionalBoolean trashed) {
		this.trashed = trashed;
	}

	public OptionalBoolean getStarred() {
		return starred;
	}

	public void setStarred(OptionalBoolean starred) {
		this.starred = starred;
	}

	public OptionalBoolean getHidden() {
		return hidden;
	}

	public void setHidden(OptionalBoolean hidden) {
		this.hidden = hidden;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public boolean isSharedWithMe() {
		return sharedWithMe;
	}

	public void setSharedWithMe(boolean sharedWithMe) {
		this.sharedWithMe = sharedWithMe;
	}
	
}
