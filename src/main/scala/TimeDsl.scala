import java.time.{ZoneId, ZonedDateTime}

import org.atnos.eff.{Eff, |=}
import org.atnos.eff.addon.scalaz.concurrent.TaskEffect.{_task, taskFork}
import scalaz.concurrent.Task

import scala.language.higherKinds


trait TimeDsl[F[_] ] {
  def getTime(zoneId: ZoneId): F[ZonedDateTime]
}

object TimeDsl{
  implicit def timeInterpreter[R](implicit taskEv: _task[R]): TimeDsl[Eff[R, ?]] =
    (zoneId: ZoneId) => taskFork[R, ZonedDateTime](Task.delay(ZonedDateTime.now(zoneId)))
}


