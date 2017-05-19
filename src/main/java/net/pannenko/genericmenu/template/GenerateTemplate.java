package net.pannenko.genericmenu.template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class GenerateTemplate {

  private static final String TEMPLATE_DIR = "templates/";
  private static final String TEMPLATE_ASSETS = TEMPLATE_DIR + "assets/";
  private static final String OUTPUT_DIR = "src/main/resources/assets/";
  private static final String OUTPUT_DIR_FORMS = OUTPUT_DIR + "forms/";
  private static final String HTML_ENDING = ".html";
  private static final String UTF_8 = "UTF-8";
  private static final String BASE_MENU = "common/base-menu.ftl";

  private Configuration cfg;

  public static void main(String[] args) throws IOException {
    new GenerateTemplate().generateResourceUsingTemplate();
  }

  public GenerateTemplate() throws IOException {
    cfg = new Configuration(new Version(2, 3, 23));
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    File templateDirectory = new File(classloader.getResource(TEMPLATE_DIR).getFile());
    TemplateLoader fileloader = new FileTemplateLoader(templateDirectory);
    cfg.setTemplateLoader(fileloader);
    cfg.setDefaultEncoding(UTF_8);
    cfg.setLocale(Locale.US);
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
  }

  public void generateResourceUsingTemplate() {

    Map<String, Object> input = new HashMap<String, Object>();

    try {

      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      File templateDirectory = new File(classloader.getResource(TEMPLATE_ASSETS).getFile());

      for (File templatefile : templateDirectory.listFiles()) {

        Template template = cfg.getTemplate("assets/" + templatefile.getName());

        String newFilename = templatefile.getName().substring(0, templatefile.getName().indexOf('.')) + HTML_ENDING;

        File file = new File(OUTPUT_DIR + newFilename);

        System.out.println(file.getAbsolutePath());
        if (!file.exists()) {
          file.createNewFile();
        }

        Writer out = new FileWriter(file);
        template.process(input, out);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TemplateException e) {
      e.printStackTrace();
    }
  }

  public void writeTemplatesToFile(String name, String document) {
    if (name == null || document == null) {
      return;
    }

    try (Writer out = new FileWriter(OUTPUT_DIR_FORMS + name)) {
      File file = new File(OUTPUT_DIR_FORMS + name);
      System.out.println(file.getAbsolutePath());
      if (!file.exists()) {
        file.createNewFile();
      }

      out.write(document);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public String generateResourceUsingTemplate(String name, int id, Integer count) {
    String result = null;

    Map<String, Object> input = new HashMap<String, Object>();

    // This part is temporary. Let's expand it more!
    input.put("title", name);
    input.put("menuId", id);
    List<Component> components = new ArrayList<Component>();
    for (int i = 0; i < count; i++) {
      components.add(new Component("/components/testcomponent.ftl", "" + i));
    }
    input.put("components", components);
    // End of temporary...
    
    
    try (Writer writer = new StringWriter(); Writer out = new FileWriter(OUTPUT_DIR_FORMS + name)) {

      // Prepare text for DB
      Template template = cfg.getTemplate(BASE_MENU);
      template.process(input, writer);
      result = writer.toString();

      // Prepare text for File
      File file = new File(OUTPUT_DIR_FORMS + name);
      if (!file.exists()) {
        file.createNewFile();
      }
      template.process(input, out);

    } catch (IOException | TemplateException e) {
      e.printStackTrace();
    }
    return result;
  }

}
