package utils

import config.Mail
import io.vertx.core.Vertx
import io.vertx.ext.mail.*

internal class Mailer(vertx: Vertx, mail: Mail?) {

	private var client: MailClient? = null
	private var sendFrom: String? = null
	private var mailTo: List<String>? = null

	init {

		if (mail != null) {

			sendFrom = mail.from
			mailTo = mail.to

			val config = MailConfig()
			config.hostname = mail.hostname
			config.port = mail.port
			config.username = mail.username
			config.password = mail.password
			config.login = LoginOption.REQUIRED
			config.authMethods = "PLAIN"
			config.isSsl = true

			client = MailClient.createNonShared(vertx, config)

		}

	}

	fun send(from: String? = sendFrom, to: List<String>? = mailTo, text: String? = null, html: String? = null) {

		if (client == null)
			return

		val message = MailMessage()
		message.from = from
		message.to = to

		if (text != null)
			message.text = text

		if (html != null)
			message.html = html

		println("Send Mail Attempt")
		client!!.sendMail(message) { result ->
			println(result)
			println("Send Mail Async Result")
			if (result.succeeded())
				println("Mail Sent")
			else
				println(result.cause().printStackTrace())
		}


	}


}