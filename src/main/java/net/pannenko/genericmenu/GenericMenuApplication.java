package net.pannenko.genericmenu;

import java.io.IOException;
import java.util.List;

import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.skife.jdbi.v2.DBI;

import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.pannenko.genericmenu.core.Menu;
import net.pannenko.genericmenu.dao.MenuDAO;
import net.pannenko.genericmenu.dao.OrderDAO;
import net.pannenko.genericmenu.exception.RunntimeGenericMenuException;
import net.pannenko.genericmenu.resource.MenuResource;
import net.pannenko.genericmenu.resource.OrderResource;
import net.pannenko.genericmenu.template.GenerateTemplate;

public class GenericMenuApplication extends Application<GenericMenuConfiguration> {

  public static final boolean IS_GENERATE_TEMPLATES = false;

  public static void main(String[] args) throws Exception {
    new GenericMenuApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<GenericMenuConfiguration> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    bootstrap.getObjectMapper().registerModule(new JsonOrgModule());
  }

  @Override
  public void run(GenericMenuConfiguration configuration, Environment environment) throws IOException {

    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
    final MenuDAO menuDAO = jdbi.onDemand(MenuDAO.class);
    final OrderDAO orderDAO = jdbi.onDemand(OrderDAO.class);

    if (IS_GENERATE_TEMPLATES) {
      GenerateTemplate gt = new GenerateTemplate();
      gt.generateResourceUsingTemplate();
      List<Menu> menus = menuDAO.getAll();
      for (Menu menu : menus) {
        gt.writeTemplatesToFile(menu.getName(), menu.getDocument());
      }
    }
    environment.jersey().setUrlPattern("/resources/*");
    environment.jersey().register(new MenuResource(menuDAO));
    environment.jersey().register(new OrderResource(orderDAO));
    environment.jersey().register(new RunntimeGenericMenuException());

    // environment.servlets().addServlet("testServletFTL", FreeMarkerAssetServlet.class).addMapping("/*");

    final ErrorPageErrorHandler epeh = new ErrorPageErrorHandler();
    epeh.addErrorPage(400, 599, "/error.html"); /// general-error");
    environment.getApplicationContext().setErrorHandler(epeh);

  }
}