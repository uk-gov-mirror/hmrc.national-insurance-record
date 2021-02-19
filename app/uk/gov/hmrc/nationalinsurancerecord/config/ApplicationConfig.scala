/*
 * Copyright 2021 HM Revenue & Customs
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

package uk.gov.hmrc.nationalinsurancerecord.config

import com.google.inject.{Inject, Singleton}
import play.api.Configuration
import uk.gov.hmrc.play.bootstrap.config.ServicesConfig

@Singleton
class ApplicationConfig @Inject()(configuration: Configuration, servicesConfig: ServicesConfig) {

  val serviceUrl = servicesConfig.baseUrl("citizen-details")
  val desUrl = servicesConfig.baseUrl("des-hod")

  val responseCacheTTL = configuration.getInt("mongodb.responseTTL").getOrElse(throw new RuntimeException("MongoDB TTL is not configured"))

  lazy val authorization: String = s"Bearer ${configuration.getString("microservice.services.des-hod.authorizationToken").getOrElse("Local")}"

  lazy val desEnvironment: String = configuration.getString("microservice.services.des-hod.environment").getOrElse("Local")
}
