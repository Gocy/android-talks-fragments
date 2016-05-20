package com.gluketic.example.fragments.masterdetail.data;

/**
 * Created by Goran Luketic
 */
public class DummyItem {

    private String id;
    private String content;
    private String details;

    public DummyItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return content;
    }
}
