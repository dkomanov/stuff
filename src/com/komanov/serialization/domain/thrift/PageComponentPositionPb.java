/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.komanov.serialization.domain.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-08-14")
public class PageComponentPositionPb implements org.apache.thrift.TBase<PageComponentPositionPb, PageComponentPositionPb._Fields>, java.io.Serializable, Cloneable, Comparable<PageComponentPositionPb> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PageComponentPositionPb");

  private static final org.apache.thrift.protocol.TField X_FIELD_DESC = new org.apache.thrift.protocol.TField("x", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField Y_FIELD_DESC = new org.apache.thrift.protocol.TField("y", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new PageComponentPositionPbStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new PageComponentPositionPbTupleSchemeFactory();

  public int x; // optional
  public int y; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    X((short)1, "x"),
    Y((short)2, "y");

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
        case 1: // X
          return X;
        case 2: // Y
          return Y;
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
  private static final int __X_ISSET_ID = 0;
  private static final int __Y_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.X,_Fields.Y};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.X, new org.apache.thrift.meta_data.FieldMetaData("x", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.Y, new org.apache.thrift.meta_data.FieldMetaData("y", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PageComponentPositionPb.class, metaDataMap);
  }

  public PageComponentPositionPb() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PageComponentPositionPb(PageComponentPositionPb other) {
    __isset_bitfield = other.__isset_bitfield;
    this.x = other.x;
    this.y = other.y;
  }

  public PageComponentPositionPb deepCopy() {
    return new PageComponentPositionPb(this);
  }

  @Override
  public void clear() {
    setXIsSet(false);
    this.x = 0;
    setYIsSet(false);
    this.y = 0;
  }

  public int getX() {
    return this.x;
  }

  public PageComponentPositionPb setX(int x) {
    this.x = x;
    setXIsSet(true);
    return this;
  }

  public void unsetX() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __X_ISSET_ID);
  }

  /** Returns true if field x is set (has been assigned a value) and false otherwise */
  public boolean isSetX() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __X_ISSET_ID);
  }

  public void setXIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __X_ISSET_ID, value);
  }

  public int getY() {
    return this.y;
  }

  public PageComponentPositionPb setY(int y) {
    this.y = y;
    setYIsSet(true);
    return this;
  }

  public void unsetY() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __Y_ISSET_ID);
  }

  /** Returns true if field y is set (has been assigned a value) and false otherwise */
  public boolean isSetY() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __Y_ISSET_ID);
  }

  public void setYIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __Y_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case X:
      if (value == null) {
        unsetX();
      } else {
        setX((java.lang.Integer)value);
      }
      break;

    case Y:
      if (value == null) {
        unsetY();
      } else {
        setY((java.lang.Integer)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case X:
      return getX();

    case Y:
      return getY();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case X:
      return isSetX();
    case Y:
      return isSetY();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof PageComponentPositionPb)
      return this.equals((PageComponentPositionPb)that);
    return false;
  }

  public boolean equals(PageComponentPositionPb that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_x = true && this.isSetX();
    boolean that_present_x = true && that.isSetX();
    if (this_present_x || that_present_x) {
      if (!(this_present_x && that_present_x))
        return false;
      if (this.x != that.x)
        return false;
    }

    boolean this_present_y = true && this.isSetY();
    boolean that_present_y = true && that.isSetY();
    if (this_present_y || that_present_y) {
      if (!(this_present_y && that_present_y))
        return false;
      if (this.y != that.y)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetX()) ? 131071 : 524287);
    if (isSetX())
      hashCode = hashCode * 8191 + x;

    hashCode = hashCode * 8191 + ((isSetY()) ? 131071 : 524287);
    if (isSetY())
      hashCode = hashCode * 8191 + y;

    return hashCode;
  }

  @Override
  public int compareTo(PageComponentPositionPb other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetX(), other.isSetX());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetX()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.x, other.x);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetY(), other.isSetY());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetY()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.y, other.y);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("PageComponentPositionPb(");
    boolean first = true;

    if (isSetX()) {
      sb.append("x:");
      sb.append(this.x);
      first = false;
    }
    if (isSetY()) {
      if (!first) sb.append(", ");
      sb.append("y:");
      sb.append(this.y);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PageComponentPositionPbStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public PageComponentPositionPbStandardScheme getScheme() {
      return new PageComponentPositionPbStandardScheme();
    }
  }

  private static class PageComponentPositionPbStandardScheme extends org.apache.thrift.scheme.StandardScheme<PageComponentPositionPb> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PageComponentPositionPb struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // X
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.x = iprot.readI32();
              struct.setXIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // Y
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.y = iprot.readI32();
              struct.setYIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, PageComponentPositionPb struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetX()) {
        oprot.writeFieldBegin(X_FIELD_DESC);
        oprot.writeI32(struct.x);
        oprot.writeFieldEnd();
      }
      if (struct.isSetY()) {
        oprot.writeFieldBegin(Y_FIELD_DESC);
        oprot.writeI32(struct.y);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PageComponentPositionPbTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public PageComponentPositionPbTupleScheme getScheme() {
      return new PageComponentPositionPbTupleScheme();
    }
  }

  private static class PageComponentPositionPbTupleScheme extends org.apache.thrift.scheme.TupleScheme<PageComponentPositionPb> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PageComponentPositionPb struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetX()) {
        optionals.set(0);
      }
      if (struct.isSetY()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetX()) {
        oprot.writeI32(struct.x);
      }
      if (struct.isSetY()) {
        oprot.writeI32(struct.y);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PageComponentPositionPb struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.x = iprot.readI32();
        struct.setXIsSet(true);
      }
      if (incoming.get(1)) {
        struct.y = iprot.readI32();
        struct.setYIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

