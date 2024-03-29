package com.hladkevych.menu.dto;

/**
 * Created by ggladko97 on 04.02.18......
 */
public class DietDTO {
  private String title;
  private String description;
  private String url;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override public String toString() {
    return "DietDTO{" +
        "title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", url='" + url + '\'' +
        '}';
  }
}
