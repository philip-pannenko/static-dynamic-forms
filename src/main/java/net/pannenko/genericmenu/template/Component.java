package net.pannenko.genericmenu.template;

public class Component {
  private String filename;
  private String id;

  public Component(String filename, String id) {
    super();
    this.filename = filename;
    this.id = id;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
