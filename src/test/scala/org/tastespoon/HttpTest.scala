package org.tastespoon

import utest._
import utest.ExecutionContext.RunNow
import scala.concurrent.Future

object HttpTest extends TestSuite {

  TasteSpoon.define("http", "127.0.0.1")

  val tests = TestSuite {
    "returns OK" - {
      val http = Http("http://example.com:8000")(TasteSpoon.server("http"))
      http.result().flatMap { response =>
        assert(response.statusCode == 200)
        assert(response.headers.get("content-type") == Some("text/html"))
        Future.successful(Unit)
      }
    }
  }
}
