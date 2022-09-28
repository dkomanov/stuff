@0xa89cd67d322d666b;

using Java = import "/capnp/java.capnp";
using Common = import "common.capnp";
$Java.package("com.komanov.serialization.domain.capnproto");
$Java.outerClassname("SiteCapnproto");

struct Domain {
  name @0 :Text;
  primary @1 :Bool;
}

struct EntryPoint {
  primary @0 :Bool;

  union {
    domain :group {
      domain @1 :Text;
    }
    free :group {
      userName @2 :Text;
      siteName @3 :Text;
    }
  }
}

struct MetaTag {
  name @0 :Text;
  value @1 :Text;
}

struct PageComponentData {
  union {
    text :group {
      text @0 :Text;
    }
    button :group {
      name @1 :Text;
      text @2 :Text;
      action @3 :Common.Uuid;
    }
    blog :group {
      name @4 :Text;
      rss @5 :Bool;
      tags @6 :Bool;
    }
  }
}

struct PageComponentPosition {
  x @0 :UInt32;
  y @1 :UInt32;
}

struct PageComponent {
  id @0 :Common.Uuid;
  componentType @1 :Common.PageComponentType;
  data @2 :PageComponentData;
  position @3 :PageComponentPosition;
  dateCreated @4 :Common.Instant;
  dateUpdated @5 :Common.Instant;
}

struct Page {
  name @0 :Text;
  path @1 :Text;
  metaTags @2 :List(MetaTag);
  components @3 :List(PageComponent);
}

# By some reason enum list doesn't work.
struct SiteFlagWrapper {
  value @0 :Common.SiteFlag;
}

struct Site {
  id @0 :Common.Uuid;
  ownerId @1 :Common.Uuid;
  revision @2 :UInt64;
  siteType @3 :Common.SiteType;
  flags @4 :List(SiteFlagWrapper);
  name @5 :Text;
  description @6 :Text;
  domains @7 :List(Domain);
  defaultMetaTags @8 :List(MetaTag);
  pages @9 :List(Page);
  entryPoints @10 :List(EntryPoint);
  published @11 :Bool;
  dateCreated @12 :Common.Instant;
  dateUpdated @13 :Common.Instant;
}
