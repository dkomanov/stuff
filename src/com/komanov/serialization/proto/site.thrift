namespace java com.komanov.serialization.domain.thrift
namespace scala com.komanov.serialization.domain.thriftscala

enum SiteTypePb {
    UnknownSiteType = 0,
    Flash = 1,
    Silverlight = 2,
    Html5 = 3
}

enum SiteFlagPb {
    UnknownSiteFlag = 0,
    Free = 1,
    Premium = 2
}

enum PageComponentTypePb {
    UnknownPageComponentType = 0,
    Text = 1,
    Button = 2,
    Blog = 3
}

struct DomainPb {
    1: optional string name,
    2: optional bool primary
}

struct DomainEntryPointPb {
    1: optional string domain,
    2: optional bool primary
}

struct FreeEntryPointPb {
    1: optional string userName,
    2: optional string siteName,
    3: optional bool primary
}

struct EntryPointPb {
    1: optional DomainEntryPointPb domain,
    2: optional FreeEntryPointPb free
}

struct MetaTagPb {
    1: optional string name,
    2: optional string value
}

struct TextComponentDataPb {
    1: optional string text
}

struct ButtonComponentDataPb {
    1: optional string name,
    2: optional string text,
    3: optional binary action
}

struct BlogComponentDataPb {
    1: optional string name,
    2: optional bool rss,
    3: optional bool tags
}

struct PageComponentDataPb {
    1: optional TextComponentDataPb text,
    2: optional ButtonComponentDataPb button,
    3: optional BlogComponentDataPb blog
}

struct PageComponentPositionPb {
    1: optional i32 x,
    2: optional i32 y
    }

struct PageComponentPb {
    1: optional binary id,
    2: optional PageComponentTypePb componentType,
    3: optional PageComponentDataPb data,
    4: optional PageComponentPositionPb position,
    5: optional i64 dateCreated,
    6: optional i64 dateUpdated
}

struct PagePb {
    1: optional string name,
    2: optional string path,
    3: optional list<MetaTagPb> metaTags,
    4: optional list<PageComponentPb> components
}

struct SitePb {
    1: optional binary id,
    2: optional binary ownerId,
    3: optional i64 revision,
    4: optional SiteTypePb siteType,
    5: optional list<SiteFlagPb> flags,
    6: optional string name,
    7: optional string description,
    8: optional list<DomainPb> domains,
    9: optional list<MetaTagPb> defaultMetaTags,
    10: optional list<PagePb> pages,
    11: optional list<EntryPointPb> entryPoints,
    12: optional bool published,
    13: optional i64 dateCreated,
    14: optional i64 dateUpdated
}
