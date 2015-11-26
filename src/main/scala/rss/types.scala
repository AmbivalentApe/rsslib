package pe.ambivalenta.rsslib

case class RSSChannel(title:String,link:String,description:String,language:Option[String] = None,copyright:Option[String],
	managingEditor:Option[String]=None,SwebMaster:Option[String]=None,pubDate:Option[String]=None,lastBuildDate:Option[String]=None,
	category : Option[Category]=None, generator: Option[String]=None, docs:Option[String]=None,cloud:Option[Cloud]=None,ttl:Option[Int]=None,
   items:Seq[RSSItem])

case class RSSItem(title:Option[String]=None, link:Option[String]=None,description : Option[String]=None, author : Option[String]=None,
		category:Option[List[String]]=None,comments:Option[String]=None,enclosure:Option[Enclosure]=None,
		guid:Option[Guid]=None,pubDate:Option[String]=None,source:Option[Source]=None)

case class Guid(contents:String,isPermaLink:Boolean=true)
case class Enclosure(url:String,length:Long,enclosureType:String)
case class Source(url:String,description:String)
case class Category(category:String,domain:Option[String])
case class Cloud(domain:String,port:Int,path:String,registerProcedure:String,protocol:String)
