// Generated by Cap'n Proto compiler, DO NOT EDIT
// source: common.capnp

package com.komanov.serialization.domain.capnproto;

public final class CommonCapnproto {
  public static class Uuid {
    public static final org.capnproto.StructSize STRUCT_SIZE = new org.capnproto.StructSize((short)2,(short)0);
    public static final class Factory extends org.capnproto.StructFactory<Builder, Reader> {
      public Factory() {
      }
      public final Reader constructReader(org.capnproto.SegmentReader segment, int data,int pointers, int dataSize, short pointerCount, int nestingLimit) {
        return new Reader(segment,data,pointers,dataSize,pointerCount,nestingLimit);
      }
      public final Builder constructBuilder(org.capnproto.SegmentBuilder segment, int data,int pointers, int dataSize, short pointerCount) {
        return new Builder(segment, data, pointers, dataSize, pointerCount);
      }
      public final org.capnproto.StructSize structSize() {
        return Uuid.STRUCT_SIZE;
      }
      public final Reader asReader(Builder builder) {
        return builder.asReader();
      }
    }
    public static final Factory factory = new Factory();
    public static final org.capnproto.StructList.Factory<Builder,Reader> listFactory =
      new org.capnproto.StructList.Factory<Builder, Reader>(factory);
    public static final class Builder extends org.capnproto.StructBuilder {
      Builder(org.capnproto.SegmentBuilder segment, int data, int pointers,int dataSize, short pointerCount){
        super(segment, data, pointers, dataSize, pointerCount);
      }
      public final Reader asReader() {
        return new Reader(segment, data, pointers, dataSize, pointerCount, 0x7fffffff);
      }
      public final long getMostSignificantBits() {
        return _getLongField(0);
      }
      public final void setMostSignificantBits(long value) {
        _setLongField(0, value);
      }

      public final long getLeastSignificantBits() {
        return _getLongField(1);
      }
      public final void setLeastSignificantBits(long value) {
        _setLongField(1, value);
      }

    }

    public static final class Reader extends org.capnproto.StructReader {
      Reader(org.capnproto.SegmentReader segment, int data, int pointers,int dataSize, short pointerCount, int nestingLimit){
        super(segment, data, pointers, dataSize, pointerCount, nestingLimit);
      }

      public final long getMostSignificantBits() {
        return _getLongField(0);
      }

      public final long getLeastSignificantBits() {
        return _getLongField(1);
      }

    }

  }


  public static class Instant {
    public static final org.capnproto.StructSize STRUCT_SIZE = new org.capnproto.StructSize((short)1,(short)0);
    public static final class Factory extends org.capnproto.StructFactory<Builder, Reader> {
      public Factory() {
      }
      public final Reader constructReader(org.capnproto.SegmentReader segment, int data,int pointers, int dataSize, short pointerCount, int nestingLimit) {
        return new Reader(segment,data,pointers,dataSize,pointerCount,nestingLimit);
      }
      public final Builder constructBuilder(org.capnproto.SegmentBuilder segment, int data,int pointers, int dataSize, short pointerCount) {
        return new Builder(segment, data, pointers, dataSize, pointerCount);
      }
      public final org.capnproto.StructSize structSize() {
        return Instant.STRUCT_SIZE;
      }
      public final Reader asReader(Builder builder) {
        return builder.asReader();
      }
    }
    public static final Factory factory = new Factory();
    public static final org.capnproto.StructList.Factory<Builder,Reader> listFactory =
      new org.capnproto.StructList.Factory<Builder, Reader>(factory);
    public static final class Builder extends org.capnproto.StructBuilder {
      Builder(org.capnproto.SegmentBuilder segment, int data, int pointers,int dataSize, short pointerCount){
        super(segment, data, pointers, dataSize, pointerCount);
      }
      public final Reader asReader() {
        return new Reader(segment, data, pointers, dataSize, pointerCount, 0x7fffffff);
      }
      public final long getMillis() {
        return _getLongField(0);
      }
      public final void setMillis(long value) {
        _setLongField(0, value);
      }

    }

    public static final class Reader extends org.capnproto.StructReader {
      Reader(org.capnproto.SegmentReader segment, int data, int pointers,int dataSize, short pointerCount, int nestingLimit){
        super(segment, data, pointers, dataSize, pointerCount, nestingLimit);
      }

      public final long getMillis() {
        return _getLongField(0);
      }

    }

  }


  public enum SiteType {
    UNKNOWN_SITE_TYPE,
    FLASH,
    SILVERLIGHT,
    HTML5,
    _NOT_IN_SCHEMA,
  }

  public enum SiteFlag {
    UNKNOWN_SITE_FLAG,
    FREE,
    PREMIUM,
    _NOT_IN_SCHEMA,
  }

  public enum PageComponentType {
    UNKNOWN_PAGE_COMPONENT_TYPE,
    TEXT,
    BUTTON,
    BLOG,
    _NOT_IN_SCHEMA,
  }


public static final class Schemas {
public static final org.capnproto.SegmentReader b_ee577fc98b21a1f2 =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u00f2\u00a1\u0021\u008b\u00c9\u007f\u0057\u00ee" +
   "\r\u0000\u0000\u0000\u0001\u0000\u0002\u0000" +
   "\u006f\u00f6\u0040\u0038\u0038\u0033\u008a\u0082" +
   "\u0000\u0000\u0007\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\u0092\u0000\u0000\u0000" +
   "\u001d\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0019\u0000\u0000\u0000\u0077\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0063\u006f\u006d\u006d\u006f\u006e\u002e\u0063" +
   "\u0061\u0070\u006e\u0070\u003a\u0055\u0075\u0069" +
   "\u0064\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u0008\u0000\u0000\u0000\u0003\u0000\u0004\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0029\u0000\u0000\u0000\u00a2\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u002c\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0038\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0001\u0000\u0000\u0000\u0001\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0001\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0035\u0000\u0000\u0000\u00aa\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0038\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0044\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u006d\u006f\u0073\u0074\u0053\u0069\u0067\u006e" +
   "\u0069\u0066\u0069\u0063\u0061\u006e\u0074\u0042" +
   "\u0069\u0074\u0073\u0000\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u006c\u0065\u0061\u0073\u0074\u0053\u0069\u0067" +
   "\u006e\u0069\u0066\u0069\u0063\u0061\u006e\u0074" +
   "\u0042\u0069\u0074\u0073\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" + "");
public static final org.capnproto.SegmentReader b_d853f3d80e4b07bc =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u00bc\u0007\u004b\u000e\u00d8\u00f3\u0053\u00d8" +
   "\r\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u006f\u00f6\u0040\u0038\u0038\u0033\u008a\u0082" +
   "\u0000\u0000\u0007\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\u00aa\u0000\u0000\u0000" +
   "\u001d\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0019\u0000\u0000\u0000\u003f\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0063\u006f\u006d\u006d\u006f\u006e\u002e\u0063" +
   "\u0061\u0070\u006e\u0070\u003a\u0049\u006e\u0073" +
   "\u0074\u0061\u006e\u0074\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u0004\u0000\u0000\u0000\u0003\u0000\u0004\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\r\u0000\u0000\u0000\u003a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0008\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0014\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u006d\u0069\u006c\u006c\u0069\u0073\u0000\u0000" +
   "\u0009\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0009\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" + "");
public static final org.capnproto.SegmentReader b_c31fef0f9dd469e5 =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u00e5\u0069\u00d4\u009d\u000f\u00ef\u001f\u00c3" +
   "\r\u0000\u0000\u0000\u0002\u0000\u0000\u0000" +
   "\u006f\u00f6\u0040\u0038\u0038\u0033\u008a\u0082" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\u00b2\u0000\u0000\u0000" +
   "\u001d\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0019\u0000\u0000\u0000\u0067\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0063\u006f\u006d\u006d\u006f\u006e\u002e\u0063" +
   "\u0061\u0070\u006e\u0070\u003a\u0053\u0069\u0074" +
   "\u0065\u0054\u0079\u0070\u0065\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u0010\u0000\u0000\u0000\u0001\u0000\u0002\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0029\u0000\u0000\u0000\u0082\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0025\u0000\u0000\u0000\u0032\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0002\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u001d\u0000\u0000\u0000\u0062\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0003\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0019\u0000\u0000\u0000\u0032\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0075\u006e\u006b\u006e\u006f\u0077\u006e\u0053" +
   "\u0069\u0074\u0065\u0054\u0079\u0070\u0065\u0000" +
   "\u0066\u006c\u0061\u0073\u0068\u0000\u0000\u0000" +
   "\u0073\u0069\u006c\u0076\u0065\u0072\u006c\u0069" +
   "\u0067\u0068\u0074\u0000\u0000\u0000\u0000\u0000" +
   "\u0068\u0074\u006d\u006c\u0035\u0000\u0000\u0000" + "");
public static final org.capnproto.SegmentReader b_ad53a42629df3177 =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u0077\u0031\u00df\u0029\u0026\u00a4\u0053\u00ad" +
   "\r\u0000\u0000\u0000\u0002\u0000\u0000\u0000" +
   "\u006f\u00f6\u0040\u0038\u0038\u0033\u008a\u0082" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\u00b2\u0000\u0000\u0000" +
   "\u001d\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0019\u0000\u0000\u0000\u004f\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0063\u006f\u006d\u006d\u006f\u006e\u002e\u0063" +
   "\u0061\u0070\u006e\u0070\u003a\u0053\u0069\u0074" +
   "\u0065\u0046\u006c\u0061\u0067\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u000c\u0000\u0000\u0000\u0001\u0000\u0002\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u001d\u0000\u0000\u0000\u0082\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0019\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0002\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0011\u0000\u0000\u0000\u0042\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0075\u006e\u006b\u006e\u006f\u0077\u006e\u0053" +
   "\u0069\u0074\u0065\u0046\u006c\u0061\u0067\u0000" +
   "\u0066\u0072\u0065\u0065\u0000\u0000\u0000\u0000" +
   "\u0070\u0072\u0065\u006d\u0069\u0075\u006d\u0000" + "");
public static final org.capnproto.SegmentReader b_dadb6dbcd1af0860 =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u0060\u0008\u00af\u00d1\u00bc\u006d\u00db\u00da" +
   "\r\u0000\u0000\u0000\u0002\u0000\u0000\u0000" +
   "\u006f\u00f6\u0040\u0038\u0038\u0033\u008a\u0082" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\u00fa\u0000\u0000\u0000" +
   "\u0021\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u001d\u0000\u0000\u0000\u0067\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0063\u006f\u006d\u006d\u006f\u006e\u002e\u0063" +
   "\u0061\u0070\u006e\u0070\u003a\u0050\u0061\u0067" +
   "\u0065\u0043\u006f\u006d\u0070\u006f\u006e\u0065" +
   "\u006e\u0074\u0054\u0079\u0070\u0065\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u0010\u0000\u0000\u0000\u0001\u0000\u0002\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0029\u0000\u0000\u0000\u00ca\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u002d\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0002\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0025\u0000\u0000\u0000\u003a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0003\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u001d\u0000\u0000\u0000\u002a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0075\u006e\u006b\u006e\u006f\u0077\u006e\u0050" +
   "\u0061\u0067\u0065\u0043\u006f\u006d\u0070\u006f" +
   "\u006e\u0065\u006e\u0074\u0054\u0079\u0070\u0065" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0074\u0065\u0078\u0074\u0000\u0000\u0000\u0000" +
   "\u0062\u0075\u0074\u0074\u006f\u006e\u0000\u0000" +
   "\u0062\u006c\u006f\u0067\u0000\u0000\u0000\u0000" + "");
}
}

