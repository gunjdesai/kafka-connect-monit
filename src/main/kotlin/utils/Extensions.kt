package utils

import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.experimental.launch

/**
 * An extension method for simplifying coroutines usage with Vert.x Web routers
 */
fun Route.asyncHandler(fn : suspend (RoutingContext) -> Unit) {
	handler { ctx ->
		launch(ctx.vertx().dispatcher()) {
			try {
				fn(ctx)
			} catch(e: Exception) {
				ctx.fail(e)
			}
		}
	}
}
