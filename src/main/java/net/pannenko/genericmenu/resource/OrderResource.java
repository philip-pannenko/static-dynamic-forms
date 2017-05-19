package net.pannenko.genericmenu.resource;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import net.pannenko.genericmenu.core.Order;
import net.pannenko.genericmenu.dao.OrderDAO;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderResource.class);

  private OrderDAO dao;

  public OrderResource(OrderDAO dao) {
    this.dao = dao;
  }

  @GET
  @Timed(name = "get-requests")
  public List<Order> viewAllOrders() {
    LOGGER.trace("viewOrders");
    List<Order> orders = dao.getAll();
    return orders;
  }
  
  @GET
  @Path("{orderId}")
  @Timed(name = "get-requests")
  public Order getOrder(@PathParam("orderId") Optional<Integer> orderId) {
    LOGGER.trace("getOrder");
    return dao.findById(orderId.orElse(-1));
  }

  @GET
  @Path("/for-menu/{menuId}")
  @Timed(name = "get-requests")
  public List<Order> viewMenuOrders(@PathParam("menuId") Optional<Integer> menuId) {
    LOGGER.trace("viewOrders");
    List<Order> orders = dao.getMenuOrders(menuId.orElse(-1));
    return orders;
  }

  @POST
  @Timed(name = "post-requests")
  public Order createOrder(Order order) {
    LOGGER.trace("createOrder");
    return dao.insert(order.getMenu().getId(), order.getData());
  }
  
  @PUT
  @Timed(name = "put-requests")
  public Order updateOrder(Order order) {
    LOGGER.trace("updateOrder");
    return dao.update(order.getId(), order.getData());
  }

}