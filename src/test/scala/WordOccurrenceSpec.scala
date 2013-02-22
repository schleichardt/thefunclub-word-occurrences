import info.schleichardt.wordoccurrences.{WordOccurrencesMain, WordCounter}
import io.Source
import java.lang.String
import org.specs2.mutable._
import java.io._
import scala.Console

class WordOccurrenceSpec extends Specification {

  //Console.setIn does not Work properly with Source.stdin, since Console.in is not related to System.in
  def withSystemInReplacement[T](in: InputStream)(thunk: => T) = {
    val systemIn = System.in
    try {
      System.setIn(in)
      thunk
    } finally {
      System.setIn(systemIn)
    }
  }

  "The application" should {
    "find the 10 most frequent words with their number of occurrences and print them into the console" in {
      val testData = getClass.getResource("/test1.txt").openStream()
      val out = new ByteArrayOutputStream
      withSystemInReplacement(testData) {
        Console.withOut(out) {
          WordOccurrencesMain.main(Array())
        }
      }
      new String(out.toByteArray) === """abc: 3
                                        |def: 2
                                        |d: 1
                                        |e: 1
                                        |f: 1
                                        |g: 1
                                        |h: 1
                                        |i: 1
                                        |j: 1
                                        |k: 1
                                        |""".stripMargin //line end is important
    }
  }

  def characters = "abc xyz abc abc cde xyz ".toIterable

  "WordCounter" should {
    "count words" in {
      WordCounter.countWords(characters) === Map("abc" -> 3, "cde" -> 1, "xyz" -> 2)
    }

    "state most frequent words" in {
      WordCounter.mostFrequentWords(characters, 2) === Seq("abc" -> 3, "xyz" -> 2)
    }
  }
}