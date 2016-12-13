/*
 * Copyright 2016 HM Revenue & Customs
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

package uk.gov.hmrc.nationalinsurancerecord.connectors

import uk.gov.hmrc.play.audit.http.config.LoadAuditingConfig
import uk.gov.hmrc.play.audit.http.connector.AuditConnector
import uk.gov.hmrc.play.audit.model.AuditEvent
import uk.gov.hmrc.play.config.AppName
import uk.gov.hmrc.play.http.HeaderCarrier
import uk.gov.hmrc.play.http.logging.MdcLoggingExecutionContext._


object CustomAuditConnector extends CustomAuditConnector {
  override lazy val auditConnector = NationalInsuranceRecordAuditConnector
}

trait CustomAuditConnector {

  val auditConnector: AuditConnector

  def sendEvent(event: AuditEvent)(implicit hc: HeaderCarrier): Unit =
    auditConnector.sendEvent(event)
}

object NationalInsuranceRecordAuditConnector extends AuditConnector with AppName {
  override lazy val auditingConfig = LoadAuditingConfig(s"auditing")
}