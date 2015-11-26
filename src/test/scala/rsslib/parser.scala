
import org.scalatest._

import pe.ambivalenta.rsslib._

class ParserSpec extends FlatSpec{

	"ProcessItemChild" should "work" in {
		val item = <item><x>hello</x></item>
		assert(RSSParser.processItemChild((item \\ "x")(0)).get==("x","hello"))
	}
	"ProcessItemChild for a guid tag" should "produce a Guid case class" in {
		val item = <item><guid>hello</guid></item>
		assert(RSSParser.processItemChild((item \\ "guid")(0)).get==("guid",Guid("hello",true)))
		val item_notperm = <item><guid permalink="false">hello</guid></item>
		//assert(RSSParser.processItemChild((item_notperm \\ "guid")(0)).get==("guid",Guid("hello",false)))
	}
	"buildItemMap" should "return a map" in {
		val item = <item><x>hello</x></item>
		assert(RSSParser.buildItemMap(item)==Map("x"->"hello"))
	}

	"buildItemMap for an 'empty' node" should "return an empty map`" in {
		val item = <item></item>
		assert(RSSParser.buildItemMap(item)==Map())
	}

	"An item" should "be parseable" in {
			val item =
				<item>
					<title>Linguistics Club</title>
					<link>http://xkcd.com/1602/</link>
					<description>
						<img src="http://imgs.xkcd.com/comics/linguistics_club.png" title="If that's too easy, you could try joining Tautology Club, which meets on the date of the Tautology Club meeting." alt="If that's too easy, you could try joining Tautology Club, which meets on the date of the Tautology Club meeting." />
					</description>
					<pubDate>Wed, 11 Nov 2015 05:00:00 -0000</pubDate>
					<guid>http://xkcd.com/1602/</guid>
					</item>


			assert(RSSParser.parseItem(item).title.get=="Linguistics Club")
			assert(RSSParser.parseItem(item).link.get=="http://xkcd.com/1602/")
			//println(RSSParser.parseItem(item).description)
			assert(RSSParser.parseItem(item).description.get=="<img src=\"http://imgs.xkcd.com/comics/linguistics_club.png\" title=\"If that's too easy, you could try joining Tautology Club, which meets on the date of the Tautology Club meeting.\" alt=\"If that's too easy, you could try joining Tautology Club, which meets on the date of the Tautology Club meeting.\"/>")

		}
	}
