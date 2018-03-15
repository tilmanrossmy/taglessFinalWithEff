import java.time.ZoneId

trait ConfDsl[F[_]] {

  def getConf: F[Conf]

}

final case class Conf(timeZone: ZoneId)
