package server

import handlers.base
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import utils.asyncHandler

internal fun routing(vertx: Vertx): Router {

	val router = Router.router(vertx)

	router.get("/").asyncHandler { ctx -> base(ctx) }

	return router

}
