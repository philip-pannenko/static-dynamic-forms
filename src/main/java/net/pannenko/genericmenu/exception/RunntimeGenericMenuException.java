package net.pannenko.genericmenu.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RunntimeGenericMenuException implements ExceptionMapper<RuntimeException> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RunntimeGenericMenuException.class);

  public Response toResponse(RuntimeException runtime) {
    if (!(runtime instanceof NotFoundException)) {
      LOGGER.error("Application Crashed", runtime);
      return Response.serverError().build();
    } else {
      return Response.status(404).build();
    }
  }

}
