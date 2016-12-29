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

struct SiteCreatedPb {
    1: optional binary id,
    2: optional binary ownerId,
    3: optional SiteTypePb siteType,
}

struct SiteNameSetPb {
    1: optional string name,
}

struct SiteDescriptionSetPb {
    1: optional string description,
}

struct SiteRevisionSetPb {
    1: optional i64 revision,
}

struct SitePublishedPb {
}

struct SiteUnpublishedPb {
}

// SiteFlag

struct SiteFlagAddedPb {
    1: optional SiteFlagPb siteFlag,
}

struct SiteFlagRemovedPb {
    1: optional SiteFlagPb siteFlag,
}

// Domain

struct DomainAddedPb {
    1: optional string name,
}

struct DomainRemovedPb {
    1: optional string name,
}

struct PrimaryDomainSetPb {
    1: optional string name,
}

// DefaultMetaTag

struct DefaultMetaTagAddedPb {
    1: optional string name,
    2: optional string value,
}

struct DefaultMetaTagRemovedPb {
    1: optional string name,
}

// Page

struct PageAddedPb {
    1: optional string path,
}

struct PageRemovedPb {
    1: optional string path,
}

struct PageNameSetPb {
    1: optional string path,
    2: optional string name,
}

struct PageMetaTagAddedPb {
    1: optional string path,
    2: optional string name,
    3: optional string value,
}

struct PageMetaTagRemovedPb {
    1: optional string path,
    2: optional string name,
}

// Page::PageComponent

struct PageComponentAddedPb {
    1: optional string pagePath,
    2: optional binary id,
    3: optional PageComponentTypePb componentType,
}

struct PageComponentRemovedPb {
    1: optional string pagePath,
    2: optional binary id,
}

struct PageComponentPositionSetPb {
    1: optional binary id,
    2: optional i32 x,
    3: optional i32 y,
}

struct PageComponentPositionResetPb {
    1: optional binary id,
}

struct TextComponentDataSetPb {
    1: optional binary id,
    2: optional string text,
}

struct ButtonComponentDataSetPb {
    1: optional binary id,
    2: optional string name,
    3: optional string text,
    4: optional binary action,
}

struct BlogComponentDataSetPb {
    1: optional binary id,
    2: optional string name,
    3: optional bool rss,
    4: optional bool tags,
}

// EntryPoint

struct DomainEntryPointAddedPb {
    1: optional string domain,
}

struct FreeEntryPointAddedPb {
    1: optional string userName,
    2: optional string siteName,
}

struct EntryPointRemovedPb {
    1: optional string lookupKey,
}

struct PrimaryEntryPointSetPb {
    1: optional string lookupKey,
}

struct SiteEventPb {
    1: optional SiteCreatedPb SiteCreatedPb,
    2: optional SiteNameSetPb SiteNameSetPb,
    3: optional SiteDescriptionSetPb SiteDescriptionSetPb,
    4: optional SiteRevisionSetPb SiteRevisionSetPb,
    5: optional SitePublishedPb SitePublishedPb,
    6: optional SiteUnpublishedPb SiteUnpublishedPb,
    7: optional SiteFlagAddedPb SiteFlagAddedPb,
    8: optional SiteFlagRemovedPb SiteFlagRemovedPb,
    9: optional DomainAddedPb DomainAddedPb,
    10: optional DomainRemovedPb DomainRemovedPb,
    11: optional PrimaryDomainSetPb PrimaryDomainSetPb,
    12: optional DefaultMetaTagAddedPb DefaultMetaTagAddedPb,
    13: optional DefaultMetaTagRemovedPb DefaultMetaTagRemovedPb,
    14: optional PageAddedPb PageAddedPb,
    15: optional PageRemovedPb PageRemovedPb,
    16: optional PageNameSetPb PageNameSetPb,
    17: optional PageMetaTagAddedPb PageMetaTagAddedPb,
    18: optional PageMetaTagRemovedPb PageMetaTagRemovedPb,
    19: optional PageComponentAddedPb PageComponentAddedPb,
    20: optional PageComponentRemovedPb PageComponentRemovedPb,
    21: optional PageComponentPositionSetPb PageComponentPositionSetPb,
    22: optional PageComponentPositionResetPb PageComponentPositionResetPb,
    23: optional TextComponentDataSetPb TextComponentDataSetPb,
    24: optional ButtonComponentDataSetPb ButtonComponentDataSetPb,
    25: optional BlogComponentDataSetPb BlogComponentDataSetPb,
    26: optional DomainEntryPointAddedPb DomainEntryPointAddedPb,
    27: optional FreeEntryPointAddedPb FreeEntryPointAddedPb,
    28: optional EntryPointRemovedPb EntryPointRemovedPb,
    29: optional PrimaryEntryPointSetPb PrimaryEntryPointSetPb,
}

struct SiteEventDataPb {
    1: optional binary id,
    2: optional SiteEventPb ev,
    3: optional i64 timestamp,
}
