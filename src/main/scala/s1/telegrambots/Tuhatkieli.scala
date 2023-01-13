package s1.telegrambots.examples


import s1.telegrambots.BasicBot

import scala.collection.mutable._
import scala.util.Random.nextInt

object Tuhatkieli extends App {

  val bot = new BasicBot() {

    val words = Map[String, String]()
    private var testVar = ""

    def add(word: Message): String = {
      if (getString(word).contains(":")) {
        val separate = getString(word).split(":")
        this.words += (separate(0) -> separate(1))
        "Word and translation added."
      }
      else "Invalid format. Try \"word\":\"translation\""
    }

    def showWords(show: Message): String = {
      if (words.isEmpty)
        "No words added yet."
      else
        "Words you're learning: " + this.words.mkString("\n")
    }

    def test(know: Message): String = {
      val randomInteger = nextInt(words.size)
      var a = words.keys.toBuffer

      for (i <- 0 to randomInteger) {
        testVar = a(i)
      }
      "To answer remember to use command /ans. Your word: " + testVar
    }

    def ans(answer: Message): String = {
      if (words(testVar) == getString(answer)) "Correct"
      else "Wrong"
    }

    def greetings(greet: Message): String = {
      "Hello!\nHei!\nHallo!\nKonnichiwa!\nHola!\nNi Hao!\nGood luck with your language learning journey.\nTo see all commands use command /help."
    }

    def help(list: Message): String = {
      "This bot saves the words you want to learn and tests your knowledge on them." +
        "\n" +
      "\nCommands:" +
      "\n/add Saves you word and its translation. \nUse format \"word\":\"translation\"." +
        "\n" +
      "\n/test  Tests how well you know the words. To answer use command /ans." +
        "\n" +
      "\n/ans Used to answer to command /test." +
        "\n" +
      "\n/show  Shows the list of words you're currently learning." +
        "\n" +
      "\n/deleteWord  Deletes the wanted word from the current list of words. Please ONLY type the original word and not the translation also." +
        "\n" +
      "\n/deleteList  Deletes the whole list of words." +
        "\n" +
      "\n/kill  Kills the bot." +
        "\n" +
      "\n/start  Almost gets you started." +
        "\n" +
      "\n/greet  Gets you started."
    }

    def start(start: Message): String = "To get started use command /greet."

    def deleteList(delete: Message): String = {
      this.words.clear()
      "List cleared."
    }

    def deleteWord(delete: Message): String = {
      val check = this.words.get(getString(delete))
      check match {
        case None => "Couldn't delete the word. Check spelling and that you only wrote the original word and not the translation."
        case Some(deletion) => this.words -= getString(delete)
          "Word deleted."
      }
    }


    this.command("show", showWords)
    this.command("add", add)
    this.command("test", test)
    this.command("ans", ans)
    this.command("greet", greetings)
    this.command("start", start)
    this.command("help", help)
    this.command("deleteList", deleteList)
    this.command("deleteWord", deleteWord)

    // Lopuksi Botti pitää vielä saada käyntiin
    this.run()

  }

}
