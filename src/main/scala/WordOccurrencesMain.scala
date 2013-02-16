package info.schleichardt.wordoccurrences

object WordOccurrencesMain extends App {
  
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
}