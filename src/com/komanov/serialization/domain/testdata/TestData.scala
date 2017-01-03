package com.komanov.serialization.domain.testdata

import java.time.Instant
import java.util.UUID
import java.util.UUID.randomUUID

import com.komanov.serialization.domain._

import scala.util.Random

object TestData {

  val baseDate = Instant.parse("2016-05-24T10:00:00Z")
  val zeroGuid = new UUID(0, 0)

  val site1k = Site(
    randomUUID,
    randomUUID,
    1L,
    SiteType.Flash,
    Seq(SiteFlag.Free),
    "some-site-name",
    "Conveying or northward offending admitting perfectly my. Colonel gravity get thought fat.\n" +
      "Interested especially do impression he unpleasant travelling excellence. All few our knew.\n" +
      "Эхо высотах лун уме рек утоляют горящие Дум зрю Что. Сон змей смел дум Тем кущ Чуть неба лес.",
    Nil,
    Seq(MetaTag("og:image", "http://example.org/title.png")),
    Seq(
      Page(
        "index",
        "/",
        Nil,
        Seq(
          PageComponent(
            randomUUID,
            PageComponentType.Text,
            TextComponentData("Title"),
            None,
            baseDate.plusSeconds(86400),
            baseDate.plusSeconds(86400)
          )
        )
      )
    ),
    Seq(
      FreeEntryPoint("user", "some-site-name", primary = true)
    ),
    published = true,
    baseDate,
    baseDate.plusSeconds(86400)
  )

  val site2k = Site(
    randomUUID,
    randomUUID,
    2L,
    SiteType.Silverlight,
    Seq(SiteFlag.Premium),
    "another-site-name",
    "Conveying or northward offending admitting perfectly my. Colonel gravity get thought fat smiling add but.\n" +
      "Wonder twenty hunted and put income set desire expect. Am cottage calling my is mistake cousins talking up.\n" +
      "Interested especially do impression he unpleasant travelling excellence. All few our knew time done draw ask.\n" +
      "Эхо высотах лун уме рек утоляют горящие Дум зрю Что. Сон змей смел дум Тем кущ Чуть неба лес. Увы сын нам это Бог Рук кем Оне. Вы Он мя Ко.\n" +
      "Кто Се то гл во. Тиран скрою мглой. Себе ведя соты пищи роды нему.",
    Seq(
      Domain("example.com", primary = true),
      Domain("example.org", primary = false)
    ),
    Seq(
      MetaTag("og:image", "https://yastatic.net/morda-logo/i/share-logo-ru.png"),
      MetaTag("og:locale", "ru-RU"),
      MetaTag("og:url", "http://example.org"),
      MetaTag("og:description", "Описание-е")
    ),
    Seq(
      Page(
        "index",
        "/",
        Nil,
        Seq(
          PageComponent(
            randomUUID,
            PageComponentType.Text,
            TextComponentData("Yet Another Title"),
            None,
            baseDate.plusSeconds(86401),
            baseDate.plusSeconds(86402)
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Button,
            ButtonComponentData("click_btn", "Click Me!", randomUUID),
            Some(PageComponentPosition(1, 1)),
            baseDate.plusSeconds(86401),
            baseDate.plusSeconds(86402)
          )
        )
      )
    ),
    Seq(
      DomainEntryPoint("example.com", primary = true),
      DomainEntryPoint("example.org", primary = false),
      FreeEntryPoint("user", "another-site-name", primary = false)
    ),
    published = true,
    baseDate,
    baseDate.plusSeconds(86412)
  )

  val site4k = Site(
    randomUUID,
    randomUUID,
    3L,
    SiteType.Silverlight,
    Seq(SiteFlag.Premium),
    "another-site-name-2",
    "Conveying or northward offending admitting perfectly my. Colonel gravity get thought fat smiling add but.\n" +
      "Wonder twenty hunted and put income set desire expect. Am cottage calling my is mistake cousins talking up.\n" +
      "Interested especially do impression he unpleasant travelling excellence. All few our knew time done draw ask.\n" +
      "Эхо высотах лун уме рек утоляют горящие Дум зрю Что. Сон змей смел дум Тем кущ Чуть неба лес. Увы сын нам это Бог Рук кем Оне. Вы Он мя Ко.\n" +
      "Его нас для Сый вер. Ее да От Се их. Орион ничто Благо. Еще Оне сих тем. Угнетало ко гл но Аз Не величьем возлюблю.\n" +
      "Кто Се то гл во. Тиран скрою мглой. Себе ведя соты пищи роды нему.",
    Seq(
      Domain("example.com", primary = true),
      Domain("example.org", primary = false)
    ),
    Seq(
      MetaTag("og:image", "https://yastatic.net/morda-logo/i/share-logo-ru.png"),
      MetaTag("og:locale", "ru-RU"),
      MetaTag("og:url", "http://example.org"),
      MetaTag("og:description", "Описание-е")
    ),
    Seq(
      Page(
        "index",
        "/",
        Nil,
        Seq(
          PageComponent(
            randomUUID,
            PageComponentType.Text,
            TextComponentData("Another Title"),
            None,
            baseDate.plusSeconds(86401),
            baseDate.plusSeconds(86402)
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Button,
            ButtonComponentData("click_btn", "Click Me!", randomUUID),
            Some(PageComponentPosition(1, 1)),
            baseDate.plusSeconds(86401),
            baseDate.plusSeconds(86402)
          )
        )
      ),
      Page(
        "blog",
        "/blog",
        Seq(
          MetaTag("og:type", "blog")
        ),
        Seq(
          PageComponent(
            randomUUID,
            PageComponentType.Text,
            TextComponentData("My blog!"),
            None,
            baseDate.plusSeconds(86401),
            baseDate.plusSeconds(86402)
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Blog,
            BlogComponentData("BLOG", rss = true, tags = true),
            None,
            baseDate.plusSeconds(86401),
            baseDate.plusSeconds(86402)
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Text,
            TextComponentData("Some blog entry, a little bit text, a little bit not text."),
            Some(PageComponentPosition(0, 100)),
            baseDate.plusSeconds(1),
            baseDate.plusSeconds(1)
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Button,
            ButtonComponentData("post_comment", "Comment!", randomUUID),
            Some(PageComponentPosition(0, 0)),
            baseDate.plusSeconds(86401),
            baseDate.plusSeconds(86402)
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Text,
            TextComponentData("My blog!"),
            Some(PageComponentPosition(0, 100)),
            baseDate.plusSeconds(86401),
            baseDate.plusSeconds(86402)
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Button,
            ButtonComponentData("post_comment", "Comment!", randomUUID),
            Some(PageComponentPosition(0, 0)),
            baseDate.plusSeconds(86401),
            baseDate.plusSeconds(86402)
          )
        )
      )
    ),
    Seq(
      DomainEntryPoint("example4k.com", primary = true),
      DomainEntryPoint("example4k.org", primary = false),
      DomainEntryPoint("subdomain1.example4k.org", primary = false),
      DomainEntryPoint("subdomain2.example4k.org", primary = false),
      DomainEntryPoint("subdomain3.example4k.org", primary = false),
      FreeEntryPoint("user", "another-site-name-2", primary = false)
    ),
    published = true,
    baseDate,
    baseDate.plusSeconds(86413)
  )

  val site8k = site4k.copy(
    siteType = SiteType.Html5,
    pages = site4k.pages ++ (0 to 7).map { index =>
      Page(
        s"ct$index",
        s"/ct/$index",
        Nil,
        Seq(
          PageComponent(
            randomUUID,
            PageComponentType.Text,
            TextComponentData(s"Cat $index"),
            None,
            randomInstant,
            randomInstant
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Button,
            ButtonComponentData(s"bb${index}_clk", "Buy $index", randomUUID),
            None,
            randomInstant,
            randomInstant
          )
        )
      )
    }
  )

  val site64k = site4k.copy(
    siteType = SiteType.Html5,
    pages = site4k.pages ++ (10 to 49).map { index =>
      Page(
        s"catalog$index",
        s"/product$index/catalog/$index",
        (0 to 9).map(tagIndex => MetaTag(s"name$tagIndex", s"value-of-a-tag-$tagIndex")),
        Seq(
          PageComponent(
            randomUUID,
            PageComponentType.Text,
            TextComponentData(s"Product catalog title $index"),
            None,
            randomInstant,
            randomInstant
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Button,
            ButtonComponentData("click", "Another Order $index", randomUUID),
            Some(PageComponentPosition(100, 500)),
            randomInstant,
            randomInstant
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Text,
            TextComponentData(s"Yet${index}another${index}title$index"),
            None,
            randomInstant,
            randomInstant
          ),
          PageComponent(
            randomUUID,
            PageComponentType.Button,
            ButtonComponentData("click", "Order$index", randomUUID),
            Some(PageComponentPosition(100, 1000)),
            randomInstant,
            randomInstant
          )
        )
      )
    },
    entryPoints = site4k.entryPoints ++ (0 to 19).map { index =>
      DomainEntryPoint(s"mirror-$index.example.org", primary = false)
    },
    defaultMetaTags = (1 to 5).map { index =>
      MetaTag(s"og:special-tag-$index", s"our special value $index")
    }
  )

  val sites: Seq[(String, Site)] = Seq(
    "1k" -> site1k,
    "2k" -> site2k,
    "4k" -> site4k,
    "8k" -> site8k,
    "64k" -> site64k
  )

  val events: Seq[(String, Seq[SiteEventData])] = sites.map {
    case (name, site) => name -> EventProcessor.unapply(site)
  }

  def all: Seq[(String, Site, Seq[SiteEventData])] = sites.zip(events).map {
    case ((name, site), (_, eventList)) =>
      (name, site, eventList)
  }

  private def randomInstant = baseDate.plusSeconds(100000000 + Random.nextInt(900000000))

}
