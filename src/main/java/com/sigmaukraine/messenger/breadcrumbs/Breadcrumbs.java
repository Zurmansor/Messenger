package com.sigmaukraine.messenger.breadcrumbs;

public class Breadcrumbs {

    public Breadcrumbs(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public Breadcrumbs(String title, String link, boolean translatable) {
        this.title = title;
        this.link = link;
        this.translatable = translatable;
    }

    private String title;

    private String link;

    private boolean translatable = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isTranslatable() {
        return translatable;
    }

    public void setTranslatable(boolean translatable) {
        this.translatable = translatable;
    }
}
