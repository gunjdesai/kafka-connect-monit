package server

import handlers.base
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import utils.HTTP_ROUTE_FORMAT

internal fun routing(vertx: Vertx): Router {

	val router = Router.router(vertx)

	router.get("/").handler({ ctx -> base(ctx) })

	return router

}
