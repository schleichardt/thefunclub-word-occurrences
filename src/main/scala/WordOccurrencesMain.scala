package info.schleichardt.wordoccurrences

import io.Source
import scala.Predef._

object WordOccurrencesMain extends App {
  val wordsInText = new WordIterator(Source.stdin)
  val maxElements = 10
  val wordToWordCounts = WordCounter.mostFrequentWords(wordsInText.toIterable, maxElements)
  wordToWordCounts foreach { case (word, count) => println(s"$word: $count") }
}

//nice to know: after dropWhile/takeWhile the original iterator should not be used,else: undefined behaviour
class WordIterator(chars: Iterator[Char]) extends Iterator[String] {
  private def isWordCharacter(c: Char) = c >= 'a' && c <= 'z'
  private def isNotWordCharacter(c: Char) = !isWordCharacter(c)
  private var internalIterator = chars.map(_.toLower).dropWhile(isNotWordCharacter)
  def hasNext = internalIterator.hasNext
  def next = {
    val (iteratorWithWord, iteratorWithLeftOver) = internalIterator.span(isWordCharacter)
    internalIterator = iteratorWithLeftOver.dropWhile(isNotWordCharacter)
    iteratorWithWord.mkString
  }
}

object WordCounter {
  def countWords(words: Iterable[String]): Map[String, Int] = {
    val seed = Map[String, Int]()
    words.foldLeft(seed) { (wordToWordCountMap: Map[String, Int], s: String) =>
      wordToWordCountMap.updated(s, wordToWordCountMap.applyOrElse(s, (_: String) => 0) + 1)
    }
  }

  def mostFrequentWords(words: Iterable[String], maxElements: Int): Seq[(String, Int)] = {
    def sortWithMostOccurrenceThenLexically(left: Pair[String, Int], right: Pair[String, Int]) = {
      val hasEqualOccurrance = left._2 == right._2
      if (hasEqualOccurrance) {
        left._1 < right._1
      } else {
        left._2 > right._2
      }
    }

    countWords(words).toSeq.sortWith(sortWithMostOccurrenceThenLexically).take(maxElements)
  }
}