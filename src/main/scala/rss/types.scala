package pe.ambivalenta.rsslib

import java.time.LocalDateTime

case class Guid(contents:String,isPermaLink:Boolean=true)
case class Enclosure(url:String,length:Long,enclosureType:String)
case class Source(url:String,description:String)
case class Category(category:String,domain:Option[String])
case class Cloud(domain:String,port:Int,path:String,registerProcedure:String,protocol:String)

case class TextInput(title:String,description:String,name:Option[String],link:Option[String])

case class Image(url:String,title:String,link:String,width:Option[Int]=Some(88),height:Option[Int] = Some(31), description:Option[String])

case class Channel(title:String,link:String,description:String,language:Option[String] = None,copyright:Option[String],
	managingEditor:Option[String]=None,webMaster:Option[String]=None,pubDate:Option[LocalDateTime]=None,lastBuildDate:Option[LocalDateTime]=None,
	category : Option[Category]=None, generator: Option[String]=None, docs:Option[String]=None,cloud:Option[Cloud]=None,ttl:Option[Int]=None,
	image : Option[Image] = None, rating : Option[String] = None, textInput : Option[TextInput] = None, skipHours : Option[Int], skipDays : Option[List[String]],
   items:Seq[Item])

case class Item(title:Option[String]=None, link:Option[String]=None,description : Option[String]=None, author : Option[String]=None,
		category:Option[List[String]]=None,comments:Option[String]=None,enclosure:Option[Enclosure]=None,
		guid:Option[Guid]=None,pubDate:Option[LocalDateTime]=None,source:Option[Source]=None)

