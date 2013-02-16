import info.schleichardt.wordoccurrences.{WordCounter, WordIterator}
import io.Source
import org.specs2.execute.Pending
import org.specs2.mutable._

class WordOccurrenceSpec extends Specification {

  "The application" should {
    "find the 10 most frequent words with their number of occurrences" in {
      Pending("TODO")
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