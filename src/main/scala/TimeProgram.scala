import scalaz.Monad
// you need this import to get flatMap
import scalaz.syntax.monad._

import scala.language.higherKinds


object TimeProgram  {

  def program[F[_] : Monad](conf: ConfDsl[F], time: TimeDsl[F]) = {
    import conf._
    for {
      c <- getConf
      t <- time.getTime(c.timeZone)
    } yield t
  }
}
