package pe.ambivalenta.rsslib

import scala.xml._
import java.net.URL
import scala.collection.immutable.Map
object RSSParser{

    def parseFeed(url:String): Unit = {
      parseFeedElement(XML.loadString(scala.io.Source.fromURL(new URL(url)).mkString))
    }

    def parseFeedElement(element :scala.xml.Elem) : Unit = {

    }

    def yank(element : scala.xml.Elem, child : String) : Option[String] ={
        Some((element \\ child).flatMap(_.descendant).mkString.trim)

    }

    def buildItemMap(element : scala.xml.Elem) : Map[String,Any] ={
        Map(element.nonEmptyChildren.flatMap(processItemChild).toList:_*)
    }

    def processItemChild(element : scala.xml.Node) : Option[(String,Any)] ={
      element match {
        case _:PCData => None
        case g if element.label == "guid" => Some((element.label,
                                                Guid(element.flatMap(_.descendant).mkString.trim
                                              )))
        case _ => Some((element.label,element.flatMap(_.descendant).mkString.trim))
      }
    }

    def parseItem(element : scala.xml.Elem) : RSSItem = {
        RSSItem(yank(element,"title"),yank(element,"link"), yank(element,"description"))
    }
  }
