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
        case g if element.label == "guid" => parseGuid(element)
        case enclosure if element.label == "enclosure" => parseEnclosure(element)
        case _ => Some((element.label,element.flatMap(_.descendant).mkString.trim))
      }
    }

    def parseItem(element : scala.xml.Elem) : Item = {
        Item(yank(element,"title"),yank(element,"link"), yank(element,"description"))
    }
    
    def parseGuid(element : scala.xml.Node) : Option[(String,Guid)] = {
      val plink = (element \\ "@permalink").map(_.text)
      Some((element.label,Guid(element.flatMap(_.descendant).mkString.trim,
         plink.length match {
             case 1 => plink.head.toBoolean
             case _ => true
         }))) 
    }
    
    def parseEnclosure(element : scala.xml.Node) : Option[(String,Enclosure)] = {
      val url = (element \\ "@url").map(_.text).head
      val length = (element \\ "@length").map(_.text).head.toInt
      val enclosureType = (element \\ "@type").map(_.text).head
      Some((element.label,Enclosure(url,length,enclosureType))) 
    }
  }
