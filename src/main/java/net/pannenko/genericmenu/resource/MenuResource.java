package net.pannenko.genericmenu.resource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.jersey.caching.CacheControl;
import net.pannenko.genericmenu.core.Menu;
import net.pannenko.genericmenu.dao.MenuDAO;
import net.pannenko.genericmenu.template.GenerateTemplate;

@Path("/menus")
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource {
  private static final Logger LOGGER = LoggerFactory.getLogger(MenuResource.class);

  private MenuDAO dao;

  public MenuResource(MenuDAO dao) {
    this.dao = dao;
  }

  @GET
  @Timed(name = "get-requests")
  public List<Menu> viewMenus() {
    LOGGER.trace("viewPages");
    List<Menu> menus = dao.getAll();
    return menus;
  }

  @DELETE
  public Response deleteMenu(@FormParam("id") Optional<Integer> id) {
    LOGGER.trace("deletePage");
    dao.deleteById(id.orElse(-1));
    return Response.ok().entity("Deleted").build();
  }

  @GET
  @Path("/{menu}")
  @CacheControl(immutable = true)
  @Timed(name = "get-requests")
  public Menu viewMenu(@PathParam("menu") Optional<String> name) throws URISyntaxException {
    return dao.findByName(name.get());
  }

  @POST
  @Timed(name = "post-requests")
  public Menu createMenu(Menu menu) throws IOException {
    LOGGER.trace("createPage: " + menu.toString());

    GenerateTemplate gt = new GenerateTemplate();

    menu = dao.insert(menu);

    String document = gt.generateResourceUsingTemplate(menu.getName(), menu.getId(), 1);
    menu.setDocument(document);
    dao.update(menu);

    return menu;
  }

}