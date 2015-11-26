package pe.ambivalenta.rsslib


trait iTunes{
	val subtitle : String
	val summary  : String
	val author   : String
	val imageURL : String
	val duration : Int

}


case class Pod(title:String, url:String)
