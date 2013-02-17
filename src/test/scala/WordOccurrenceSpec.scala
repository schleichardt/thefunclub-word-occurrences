import info.schleichardt.wordoccurrences.{WordOccurrencesMain, WordCounter, WordIterator}
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

  "WordIterator" should {
    "deliver words from a character iterator" in {
      val iterator = new WordIterator(Source.fromString("#+ ABC cde 235 ,._ xYz 9"))
      iterator.toArray === Array("abc", "cde", "xyz")
    }
  }

  "WordCounter" should {
    "count words" in {
      WordCounter.countWords(Array("abc", "abc", "abc", "cde", "xyz", "xyz")) === Map("abc" -> 3, "cde" -> 1, "xyz" -> 2)
    }

    "state most frequent words" in {
      WordCounter.mostFrequentWords(Array("abc", "abc", "abc", "cde", "xyz", "xyz"), 2) === Seq("abc" -> 3, "xyz" -> 2)
    }
  }
}