package info.schleichardt.wordoccurrences

import io.Source.stdin
import WordCounter.mostFrequentWords

object WordOccurrencesMain extends App {
  mostFrequentWords(stdin, maxElements = 10) foreach { case (word, count) => println(s"$word: $count") }
}

object WordCounter {
  private type Word = String
  private type WordCount = Long

  private def isWordCharacter(c: Char) = c >= 'a' && c <= 'z'

  def countWords(characters: Iterator[Char]): Map[Word, WordCount] = {
    val preparedIterator = characters.map(_.toLower) ++ " ".iterator //last char whitespace needed for fold to count last word
    val wordCountMap = collection.mutable.Map[Word, WordCount]()
    val wordBuilder = new StringBuilder
    preparedIterator foreach { character =>
      if (isWordCharacter(character)) {
        wordBuilder += character
      } else {
        val word = wordBuilder.toString
        val previousValue = wordCountMap.getOrElse(word, 0L)
        wordCountMap.update(word, previousValue + 1L)
        wordBuilder.clear()
      }
    }
    wordCountMap -= ("") toMap//remove empty word and make immutable
  }

  def mostFrequentWords(characters: Iterator[Char], maxElements: Int): Seq[(Word, WordCount)] = {
    def compareWithMostOccurrenceThenLexically(item: (Word, WordCount)) = (-1 * item._2, item._1)
    //here are improvements possible, we need only 10 elements, why should the rest be sorted?
    countWords(characters).toSeq.sortBy(compareWithMostOccurrenceThenLexically).take(maxElements)
  }
}
