import java.time.{ZoneId, ZonedDateTime}

import scalaz.concurrent.Task

trait TaskInterpreter {

  def timeInterpreter = new TimeDsl[Task] {
    override def getTime(zoneId: ZoneId): Task[ZonedDateTime] = Task.delay(ZonedDateTime.now(zoneId))
  }

  def confInterpreter = new ConfDsl[Task] {
    override def getConf: Task[Conf] = Task.delay(getConfig)
  }

  def getConfig: Conf
}
