package info.schleichardt.wordoccurrences

import io.Source.stdin
import scala.collection.mutable.Map
import WordCounter.mostFrequentWords

object WordOccurrencesMain extends App {
  mostFrequentWords(stdin, maxElements = 10) foreach { case (word, count) => println(s"$word: $count") }
}

object WordCounter {
  private def isWordCharacter(c: Char) = c >= 'a' && c <= 'z'
  private def increase[T](map: Map[T, Long], key: T) {
    val oldValue = map.getOrElse(key, 0L)
    val newValue = oldValue + 1L
    map.update(key, newValue)
  }
  private def seed = Pair(Map[String, Long](), new StringBuilder)

  def countWords(characters: Iterator[Char]): Map[String, Long] = {
    val preparedIterator = characters.map(_.toLower) ++ " ".iterator //last char whitespace needed for fold to count last word
    preparedIterator.foldLeft(seed) { case ((wordCountMap, wordBuilder), character) =>
      if (isWordCharacter(character)) {
        wordBuilder += character
      } else {
        val word = wordBuilder.toString
        if (!word.isEmpty) {
          increase(wordCountMap, word)
          wordBuilder.clear()
        }
      }
      Pair(wordCountMap, wordBuilder)
    }._1
  }

  def mostFrequentWords(characters: Iterator[Char], maxElements: Int): Seq[(String, Long)] = {
    def compareWithMostOccurrenceThenLexically(left: Pair[String, Long], right: Pair[String, Long]) = {
      val hasEqualOccurrence = left._2 == right._2
      if (hasEqualOccurrence) {
        left._1 < right._1
      } else {
        left._2 > right._2
      }
    }
    countWords(characters).toSeq.sortWith(compareWithMostOccurrenceThenLexically).take(maxElements)
  }
}
