import java.time.ZoneId

import cats.data.Reader
import org.atnos.eff.addon.scalaz.concurrent.TaskEffect._task
import org.atnos.eff.{Eff, reader, |=}

trait ConfDsl[F[_]] {

  def getConf: F[Conf]

}

object ConfDsl{
  implicit def confInterpreter[R](implicit _reader: Reader[Conf, ?] |= R) = new ConfDsl[Eff[R, ?]] {
    override def getConf: Eff[R, Conf] = reader.ask[R, Conf]
  }
}

final case class Conf(timeZone: ZoneId)
