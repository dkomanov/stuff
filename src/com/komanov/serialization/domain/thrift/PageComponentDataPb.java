/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.komanov.serialization.domain.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-08-14")
public class PageComponentDataPb implements org.apache.thrift.TBase<PageComponentDataPb, PageComponentDataPb._Fields>, java.io.Serializable, Cloneable, Comparable<PageComponentDataPb> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PageComponentDataPb");

  private static final org.apache.thrift.protocol.TField TEXT_FIELD_DESC = new org.apache.thrift.protocol.TField("text", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField BUTTON_FIELD_DESC = new org.apache.thrift.protocol.TField("button", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField BLOG_FIELD_DESC = new org.apache.thrift.protocol.TField("blog", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new PageComponentDataPbStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new PageComponentDataPbTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable TextComponentDataPb text; // optional
  public @org.apache.thrift.annotation.Nullable ButtonComponentDataPb button; // optional
  public @org.apache.thrift.annotation.Nullable BlogComponentDataPb blog; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TEXT((short)1, "text"),
    BUTTON((short)2, "button"),
    BLOG((short)3, "blog");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TEXT
          return TEXT;
        case 2: // BUTTON
          return BUTTON;
        case 3: // BLOG
          return BLOG;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.TEXT,_Fields.BUTTON,_Fields.BLOG};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TEXT, new org.apache.thrift.meta_data.FieldMetaData("text", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TextComponentDataPb.class)));
    tmpMap.put(_Fields.BUTTON, new org.apache.thrift.meta_data.FieldMetaData("button", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ButtonComponentDataPb.class)));
    tmpMap.put(_Fields.BLOG, new org.apache.thrift.meta_data.FieldMetaData("blog", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, BlogComponentDataPb.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PageComponentDataPb.class, metaDataMap);
  }

  public PageComponentDataPb() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PageComponentDataPb(PageComponentDataPb other) {
    if (other.isSetText()) {
      this.text = new TextComponentDataPb(other.text);
    }
    if (other.isSetButton()) {
      this.button = new ButtonComponentDataPb(other.button);
    }
    if (other.isSetBlog()) {
      this.blog = new BlogComponentDataPb(other.blog);
    }
  }

  public PageComponentDataPb deepCopy() {
    return new PageComponentDataPb(this);
  }

  @Override
  public void clear() {
    this.text = null;
    this.button = null;
    this.blog = null;
  }

  @org.apache.thrift.annotation.Nullable
  public TextComponentDataPb getText() {
    return this.text;
  }

  public PageComponentDataPb setText(@org.apache.thrift.annotation.Nullable TextComponentDataPb text) {
    this.text = text;
    return this;
  }

  public void unsetText() {
    this.text = null;
  }

  /** Returns true if field text is set (has been assigned a value) and false otherwise */
  public boolean isSetText() {
    return this.text != null;
  }

  public void setTextIsSet(boolean value) {
    if (!value) {
      this.text = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public ButtonComponentDataPb getButton() {
    return this.button;
  }

  public PageComponentDataPb setButton(@org.apache.thrift.annotation.Nullable ButtonComponentDataPb button) {
    this.button = button;
    return this;
  }

  public void unsetButton() {
    this.button = null;
  }

  /** Returns true if field button is set (has been assigned a value) and false otherwise */
  public boolean isSetButton() {
    return this.button != null;
  }

  public void setButtonIsSet(boolean value) {
    if (!value) {
      this.button = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public BlogComponentDataPb getBlog() {
    return this.blog;
  }

  public PageComponentDataPb setBlog(@org.apache.thrift.annotation.Nullable BlogComponentDataPb blog) {
    this.blog = blog;
    return this;
  }

  public void unsetBlog() {
    this.blog = null;
  }

  /** Returns true if field blog is set (has been assigned a value) and false otherwise */
  public boolean isSetBlog() {
    return this.blog != null;
  }

  public void setBlogIsSet(boolean value) {
    if (!value) {
      this.blog = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case TEXT:
      if (value == null) {
        unsetText();
      } else {
        setText((TextComponentDataPb)value);
      }
      break;

    case BUTTON:
      if (value == null) {
        unsetButton();
      } else {
        setButton((ButtonComponentDataPb)value);
      }
      break;

    case BLOG:
      if (value == null) {
        unsetBlog();
      } else {
        setBlog((BlogComponentDataPb)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case TEXT:
      return getText();

    case BUTTON:
      return getButton();

    case BLOG:
      return getBlog();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case TEXT:
      return isSetText();
    case BUTTON:
      return isSetButton();
    case BLOG:
      return isSetBlog();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof PageComponentDataPb)
      return this.equals((PageComponentDataPb)that);
    return false;
  }

  public boolean equals(PageComponentDataPb that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_text = true && this.isSetText();
    boolean that_present_text = true && that.isSetText();
    if (this_present_text || that_present_text) {
      if (!(this_present_text && that_present_text))
        return false;
      if (!this.text.equals(that.text))
        return false;
    }

    boolean this_present_button = true && this.isSetButton();
    boolean that_present_button = true && that.isSetButton();
    if (this_present_button || that_present_button) {
      if (!(this_present_button && that_present_button))
        return false;
      if (!this.button.equals(that.button))
        return false;
    }

    boolean this_present_blog = true && this.isSetBlog();
    boolean that_present_blog = true && that.isSetBlog();
    if (this_present_blog || that_present_blog) {
      if (!(this_present_blog && that_present_blog))
        return false;
      if (!this.blog.equals(that.blog))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetText()) ? 131071 : 524287);
    if (isSetText())
      hashCode = hashCode * 8191 + text.hashCode();

    hashCode = hashCode * 8191 + ((isSetButton()) ? 131071 : 524287);
    if (isSetButton())
      hashCode = hashCode * 8191 + button.hashCode();

    hashCode = hashCode * 8191 + ((isSetBlog()) ? 131071 : 524287);
    if (isSetBlog())
      hashCode = hashCode * 8191 + blog.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(PageComponentDataPb other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetText(), other.isSetText());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetText()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.text, other.text);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetButton(), other.isSetButton());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetButton()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.button, other.button);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetBlog(), other.isSetBlog());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBlog()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.blog, other.blog);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("PageComponentDataPb(");
    boolean first = true;

    if (isSetText()) {
      sb.append("text:");
      if (this.text == null) {
        sb.append("null");
      } else {
        sb.append(this.text);
      }
      first = false;
    }
    if (isSetButton()) {
      if (!first) sb.append(", ");
      sb.append("button:");
      if (this.button == null) {
        sb.append("null");
      } else {
        sb.append(this.button);
      }
      first = false;
    }
    if (isSetBlog()) {
      if (!first) sb.append(", ");
      sb.append("blog:");
      if (this.blog == null) {
        sb.append("null");
      } else {
        sb.append(this.blog);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (text != null) {
      text.validate();
    }
    if (button != null) {
      button.validate();
    }
    if (blog != null) {
      blog.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PageComponentDataPbStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public PageComponentDataPbStandardScheme getScheme() {
      return new PageComponentDataPbStandardScheme();
    }
  }

  private static class PageComponentDataPbStandardScheme extends org.apache.thrift.scheme.StandardScheme<PageComponentDataPb> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PageComponentDataPb struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TEXT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.text = new TextComponentDataPb();
              struct.text.read(iprot);
              struct.setTextIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // BUTTON
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.button = new ButtonComponentDataPb();
              struct.button.read(iprot);
              struct.setButtonIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // BLOG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.blog = new BlogComponentDataPb();
              struct.blog.read(iprot);
              struct.setBlogIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, PageComponentDataPb struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.text != null) {
        if (struct.isSetText()) {
          oprot.writeFieldBegin(TEXT_FIELD_DESC);
          struct.text.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.button != null) {
        if (struct.isSetButton()) {
          oprot.writeFieldBegin(BUTTON_FIELD_DESC);
          struct.button.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.blog != null) {
        if (struct.isSetBlog()) {
          oprot.writeFieldBegin(BLOG_FIELD_DESC);
          struct.blog.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PageComponentDataPbTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public PageComponentDataPbTupleScheme getScheme() {
      return new PageComponentDataPbTupleScheme();
    }
  }

  private static class PageComponentDataPbTupleScheme extends org.apache.thrift.scheme.TupleScheme<PageComponentDataPb> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PageComponentDataPb struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetText()) {
        optionals.set(0);
      }
      if (struct.isSetButton()) {
        optionals.set(1);
      }
      if (struct.isSetBlog()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetText()) {
        struct.text.write(oprot);
      }
      if (struct.isSetButton()) {
        struct.button.write(oprot);
      }
      if (struct.isSetBlog()) {
        struct.blog.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PageComponentDataPb struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.text = new TextComponentDataPb();
        struct.text.read(iprot);
        struct.setTextIsSet(true);
      }
      if (incoming.get(1)) {
        struct.button = new ButtonComponentDataPb();
        struct.button.read(iprot);
        struct.setButtonIsSet(true);
      }
      if (incoming.get(2)) {
        struct.blog = new BlogComponentDataPb();
        struct.blog.read(iprot);
        struct.setBlogIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

