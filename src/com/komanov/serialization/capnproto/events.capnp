@0xdf8c7f8133478f01;

using Java = import "/capnp/java.capnp";
using Common = import "common.capnp";
$Java.package("com.komanov.serialization.domain.capnproto");
$Java.outerClassname("EventsCapnproto");

struct SiteCreated {
  id @0 :Common.Uuid;
  ownerId @1 :Common.Uuid;
  siteType @2 :Common.SiteType;
}

struct SiteNameSet {
  name @0 :Text;
}

struct SiteDescriptionSet {
  description @0 :Text;
}

struct SiteRevisionSet {
  revision @0 :UInt64;
}

struct SitePublished {
}

struct SiteUnpublished {
}

# SiteFlag

struct SiteFlagAdded {
  siteFlag @0 :Common.SiteFlag;
}

struct SiteFlagRemoved {
  siteFlag @0 :Common.SiteFlag;
}

# Domain

struct DomainAdded {
  name @0 :Text;
}

struct DomainRemoved {
  name @0 :Text;
}

struct PrimaryDomainSet {
  name @0 :Text;
}

# DefaultMetaTag

struct DefaultMetaTagAdded {
  name @0 :Text;
  value @1 :Text;
}

struct DefaultMetaTagRemoved {
  name @0 :Text;
}

# Page

struct PageAdded {
  path @0 :Text;
}

struct PageRemoved {
  path @0 :Text;
}

struct PageNameSet {
  path @0 :Text;
  name @1 :Text;
}

struct PageMetaTagAdded {
  path @0 :Text;
  name @1 :Text;
  value @2 :Text;
}

struct PageMetaTagRemoved {
  path @0 :Text;
  name @1 :Text;
}

# Page::PageComponent

struct PageComponentAdded {
  pagePath @0 :Text;
  id @1 :Common.Uuid;
  componentType @2 :Common.PageComponentType;
}

struct PageComponentRemoved {
  pagePath @0 :Text;
  id @1 :Common.Uuid;
}

struct PageComponentPositionSet {
  id @0 :Common.Uuid;
  x @1 :UInt32;
  y @2 :UInt32;
}

struct PageComponentPositionReset {
  id @0 :Common.Uuid;
}

struct TextComponentDataSet {
  id @0 :Common.Uuid;
  text @1 :Text;
}

struct ButtonComponentDataSet {
  id @0 :Common.Uuid;
  name @1 :Text;
  text @2 :Text;
  action @3 :Common.Uuid;
}

struct BlogComponentDataSet {
  id @0 :Common.Uuid;
  name @1 :Text;
  rss @2 :Bool;
  tags @3 :Bool;
}

# EntryPoint

struct DomainEntryPointAdded {
  domain @0 :Text;
}

struct FreeEntryPointAdded {
  userName @0 :Text;
  siteName @1 :Text;
}

struct EntryPointRemoved {
  lookupKey @0 :Text;
}

struct PrimaryEntryPointSet {
  lookupKey @0 :Text;
}

struct SiteEvent {
  ev :union {
    siteCreated @0 :SiteCreated;
    siteNameSet @1 :SiteNameSet;
    siteDescriptionSet @2 :SiteDescriptionSet;
    siteRevisionSet @3 :SiteRevisionSet;
    sitePublished @4 :SitePublished;
    siteUnpublished @5 :SiteUnpublished;
    siteFlagAdded @6 :SiteFlagAdded;
    siteFlagRemoved @7 :SiteFlagRemoved;
    domainAdded @8 :DomainAdded;
    domainRemoved @9 :DomainRemoved;
    primaryDomainSet @10 :PrimaryDomainSet;
    defaultMetaTagAdded @11 :DefaultMetaTagAdded;
    defaultMetaTagRemoved @12 :DefaultMetaTagRemoved;
    pageAdded @13 :PageAdded;
    pageRemoved @14 :PageRemoved;
    pageNameSet @15 :PageNameSet;
    pageMetaTagAdded @16 :PageMetaTagAdded;
    pageMetaTagRemoved @17 :PageMetaTagRemoved;
    pageComponentAdded @18 :PageComponentAdded;
    pageComponentRemoved @19 :PageComponentRemoved;
    pageComponentPositionSet @20 :PageComponentPositionSet;
    pageComponentPositionReset @21 :PageComponentPositionReset;
    textComponentDataSet @22 :TextComponentDataSet;
    buttonComponentDataSet @23 :ButtonComponentDataSet;
    blogComponentDataSet @24 :BlogComponentDataSet;
    domainEntryPointAdded @25 :DomainEntryPointAdded;
    freeEntryPointAdded @26 :FreeEntryPointAdded;
    entryPointRemoved @27 :EntryPointRemoved;
    primaryEntryPointSet @28 :PrimaryEntryPointSet;
  }
}

struct SiteEventData {
  id @0 :Common.Uuid;
  ev @1 :SiteEvent;
  timestamp @2 :Common.Instant;
}
