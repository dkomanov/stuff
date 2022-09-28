@0x828a33383840f66f;

using Java = import "/capnp/java.capnp";
$Java.package("com.komanov.serialization.domain.capnproto");
$Java.outerClassname("CommonCapnproto");

struct Uuid {
  mostSignificantBits @0 :Int64;
  leastSignificantBits @1 :Int64;
}

struct Instant {
  millis @0 :UInt64;
}

enum SiteType {
    unknownSiteType @0;
    flash @1;
    silverlight @2;
    html5 @3;
}

enum SiteFlag {
    unknownSiteFlag @0;
    free @1;
    premium @2;
}

enum PageComponentType {
    unknownPageComponentType @0;
    text @1;
    button @2;
    blog @3;
}
