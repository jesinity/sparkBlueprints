package it.jesinity.sparkate

import java.io.InputStream
import java.nio.file.{Path, Paths}

import org.scalatest.FunSuite

import scala.io.Source

class ReadTheFileTest extends FunSuite{

  test("read a file in the test resources"){

    val asStream: InputStream = classOf[ReadTheFileTest].getResourceAsStream("prova.txt")
    val toList: List[String] = Source.fromInputStream(asStream).getLines().toList

    assertResult("ciao" ::Nil)(toList)

  }

  test("Wikidata home shoud be defined in the file system"){

    val env: String = System.getenv("WIKIDATA_HOME")
    assertResult(true)(env!=null)

    val path: Path = Paths.get(env,"ciao.txt")
    val toList: List[String] = Source.fromFile(path.toFile()).getLines().toList
    assertResult("ciao" ::Nil)(toList)
  }

}
