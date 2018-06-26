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
			config.isSsl = true


			if (mail.username != null && mail.password != null){
				config.username = mail.username
				config.password = mail.password
				config.login = LoginOption.REQUIRED
				config.authMethods = "PLAIN"
			}


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


		client!!.sendMail(message) { result ->
			if (result.succeeded())
				println("Mail Sent")
			else
				println(result.cause().printStackTrace())
		}


	}


}