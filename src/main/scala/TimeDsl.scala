import java.time.{ZoneId, ZonedDateTime}


trait TimeDsl[F[_] ] {
  def getTime(zoneId: ZoneId): F[ZonedDateTime]
}


