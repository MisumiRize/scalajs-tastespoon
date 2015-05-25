package org.tastespoon

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}

trait Server extends js.Object {
  var name: String = js.native
  var address: String = js.native
}

object TasteSpoon {

  private val module = g.require("tastespoon")

  def define(name: String, address: String): Unit = {
    module.define(name, address)
  }

  def server(name: String): Server = {
    module.server(name).asInstanceOf[Server]
  }
}

trait HttpResponse extends js.Object {
  var statusCode: Int = js.native
}

class Http private(httpContext: js.Dynamic) {

  def result(): Future[HttpResponse] = {
    val promise = Promise[HttpResponse]
    Future {
      httpContext.result().then((res: js.Dynamic) => {
        promise.success(res.asInstanceOf[HttpResponse])
      })
    }
    promise.future
  }
}

object Http {

  private val module = g.require("tastespoon")

  def apply(url: String): (Server) => Http = {
    (server: Server) => {
      new Http(module.http(url)(server))
    }
  }
}
