package info.schleichardt.wordoccurrences

object WordOccurrencesMain extends App {
  
}

//nice to know: after dropWhile/takeWhile the original iterator should not be used,else: undefined behaviour
class WordIterator(chars: Iterator[Char]) extends Iterator[String] {
  private var internalIterator = chars.map(_.toLower).map(c => if (c >= 'a' && c <= 'z') c else ' ').dropWhile(_ == ' ')
  def hasNext = internalIterator.hasNext
  def next = {
    val (iteratorWithWord, iteratorWithLeftOver) = internalIterator.span(_ != ' ')
    internalIterator = iteratorWithLeftOver.dropWhile(_ == ' ')
    iteratorWithWord.mkString
  }
}