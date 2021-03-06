import java.time.format.{DateTimeFormatter, TextStyle}
import java.time.{ZoneId, ZonedDateTime}
import java.util.Locale

import ConfDsl.confInterpreter
import TimeDsl.timeInterpreter
import cats.data.Reader
import org.atnos.eff.Fx2
import org.atnos.eff.addon.scalaz.concurrent.TimedTask
import org.atnos.eff.syntax.addon.scalaz.all.toTaskOps
import org.atnos.eff.syntax.reader.ReaderEffectOps

import scala.concurrent.ExecutionContext.Implicits.global




object TimeApp extends App {

  type S = Fx2[TimedTask, Reader[Conf, ?]]

  implicit val dts = scalaz.concurrent.Strategy.DefaultTimeoutScheduler

  private val c = Conf(ZoneId.of(ZoneId.SHORT_IDS.get("PST")))

  private val t: ZonedDateTime = TimeProgram.program(confInterpreter[S], timeInterpreter[S])
    .runReader(c)
    .runSequential
    .unsafePerformSync

  private val timeString = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format(t)

  println(s"Current time in zone ${c.timeZone.getDisplayName(TextStyle.FULL, Locale.getDefault)} is $timeString")

}
