/*
 * Copyright 2013 http4s.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.http4s
package headers

import cats.parse.Parser
import cats.parse.Parser.char
import org.typelevel.ci._

sealed abstract class `Upgrade-Insecure-Requests`(val value: String)
    extends Product
    with Serializable

object `Upgrade-Insecure-Requests` {
  case object Upgrade extends `Upgrade-Insecure-Requests`("1")

  private[http4s] val parser: Parser[`Upgrade-Insecure-Requests`] =
    char('1').as(Upgrade)

  def parse(s: String): ParseResult[`Upgrade-Insecure-Requests`] =
    ParseResult.fromParser(parser, "Invalid Upgrade-Insecure-Requests header")(s)

  implicit val headerInstance: Header[`Upgrade-Insecure-Requests`, Header.Single] =
    Header.create(
      ci"Upgrade-Insecure-Requests",
      _.value,
      parse,
    )

}
