import java.time.{ZoneId, ZonedDateTime}

import cats.data.Reader
import org.atnos.eff.{Eff, reader, |=}
import org.atnos.eff.addon.scalaz.concurrent.TaskEffect.{_task, taskFork}
import scalaz.concurrent.Task

final class EffInterpreter[R](implicit taskEv: _task[R], _reader: Reader[Conf, ?] |= R) {

  lazy val timeInterpreter: TimeDsl[Eff[R, ?]] = new TimeDsl[Eff[R, ?]] {

    override def getTime(zoneId: ZoneId): Eff[R, ZonedDateTime] =
      taskFork[R, ZonedDateTime](Task.delay(ZonedDateTime.now(zoneId)))
  }

  lazy val confInterpreter: ConfDsl[Eff[R, ?]] = new ConfDsl[Eff[R, ?]] {
    override def getConf: Eff[R, Conf] = reader.ask[R, Conf]
  }
}
