package com.playground.NewsAPI.model;

import jakarta.json.JsonObject;

public class Everything {
    private String author;
	private String title;
	private String content;
	private String publishedAt;

	public void setAuthor(String author) { this.author = author; }
	public String getAuthor() { return this.author; }

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setContent(String content) { this.content = content; }
	public String getContent() { return this.content; }

	public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }
	public String getPublishedAt() { return this.publishedAt; }

	public static Everything create(JsonObject jo) {
		System.out.printf("body: %s\n", jo.toString());
		Everything everything = new Everything();
		everything.setAuthor(jo.getString("author", "anon"));
		everything.setTitle(jo.getString("title"));
		everything.setContent(jo.getString("content"));
		everything.setPublishedAt(jo.getString("publishedAt"));
		return everything;
	}
}
