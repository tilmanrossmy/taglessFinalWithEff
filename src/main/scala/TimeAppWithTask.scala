import java.time.format.{DateTimeFormatter, TextStyle}
import java.time.{ZoneId, ZonedDateTime}
import java.util.Locale


object TimeAppWithTask extends App{

  private val c = Conf(ZoneId.of(ZoneId.SHORT_IDS.get("PST")))

  private val taskInterpreter = new TaskInterpreter {
    override def getConfig: Conf = c
  }
  private val time = taskInterpreter.timeInterpreter
  private val conf = taskInterpreter.confInterpreter

  private val t: ZonedDateTime = TimeProgram.program(conf,time).unsafePerformSync

  private val timeString = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format(t)

  println(s"Current time in zone ${c.timeZone.getDisplayName(TextStyle.FULL, Locale.getDefault)} is $timeString")

}
