/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.komanov.serialization.domain.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-08-14")
public class DomainEntryPointPb implements org.apache.thrift.TBase<DomainEntryPointPb, DomainEntryPointPb._Fields>, java.io.Serializable, Cloneable, Comparable<DomainEntryPointPb> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DomainEntryPointPb");

  private static final org.apache.thrift.protocol.TField DOMAIN_FIELD_DESC = new org.apache.thrift.protocol.TField("domain", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField PRIMARY_FIELD_DESC = new org.apache.thrift.protocol.TField("primary", org.apache.thrift.protocol.TType.BOOL, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new DomainEntryPointPbStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new DomainEntryPointPbTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.lang.String domain; // optional
  public boolean primary; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DOMAIN((short)1, "domain"),
    PRIMARY((short)2, "primary");

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
        case 1: // DOMAIN
          return DOMAIN;
        case 2: // PRIMARY
          return PRIMARY;
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
  private static final int __PRIMARY_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.DOMAIN,_Fields.PRIMARY};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DOMAIN, new org.apache.thrift.meta_data.FieldMetaData("domain", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PRIMARY, new org.apache.thrift.meta_data.FieldMetaData("primary", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DomainEntryPointPb.class, metaDataMap);
  }

  public DomainEntryPointPb() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DomainEntryPointPb(DomainEntryPointPb other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetDomain()) {
      this.domain = other.domain;
    }
    this.primary = other.primary;
  }

  public DomainEntryPointPb deepCopy() {
    return new DomainEntryPointPb(this);
  }

  @Override
  public void clear() {
    this.domain = null;
    setPrimaryIsSet(false);
    this.primary = false;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getDomain() {
    return this.domain;
  }

  public DomainEntryPointPb setDomain(@org.apache.thrift.annotation.Nullable java.lang.String domain) {
    this.domain = domain;
    return this;
  }

  public void unsetDomain() {
    this.domain = null;
  }

  /** Returns true if field domain is set (has been assigned a value) and false otherwise */
  public boolean isSetDomain() {
    return this.domain != null;
  }

  public void setDomainIsSet(boolean value) {
    if (!value) {
      this.domain = null;
    }
  }

  public boolean isPrimary() {
    return this.primary;
  }

  public DomainEntryPointPb setPrimary(boolean primary) {
    this.primary = primary;
    setPrimaryIsSet(true);
    return this;
  }

  public void unsetPrimary() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PRIMARY_ISSET_ID);
  }

  /** Returns true if field primary is set (has been assigned a value) and false otherwise */
  public boolean isSetPrimary() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PRIMARY_ISSET_ID);
  }

  public void setPrimaryIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PRIMARY_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case DOMAIN:
      if (value == null) {
        unsetDomain();
      } else {
        setDomain((java.lang.String)value);
      }
      break;

    case PRIMARY:
      if (value == null) {
        unsetPrimary();
      } else {
        setPrimary((java.lang.Boolean)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case DOMAIN:
      return getDomain();

    case PRIMARY:
      return isPrimary();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case DOMAIN:
      return isSetDomain();
    case PRIMARY:
      return isSetPrimary();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof DomainEntryPointPb)
      return this.equals((DomainEntryPointPb)that);
    return false;
  }

  public boolean equals(DomainEntryPointPb that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_domain = true && this.isSetDomain();
    boolean that_present_domain = true && that.isSetDomain();
    if (this_present_domain || that_present_domain) {
      if (!(this_present_domain && that_present_domain))
        return false;
      if (!this.domain.equals(that.domain))
        return false;
    }

    boolean this_present_primary = true && this.isSetPrimary();
    boolean that_present_primary = true && that.isSetPrimary();
    if (this_present_primary || that_present_primary) {
      if (!(this_present_primary && that_present_primary))
        return false;
      if (this.primary != that.primary)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetDomain()) ? 131071 : 524287);
    if (isSetDomain())
      hashCode = hashCode * 8191 + domain.hashCode();

    hashCode = hashCode * 8191 + ((isSetPrimary()) ? 131071 : 524287);
    if (isSetPrimary())
      hashCode = hashCode * 8191 + ((primary) ? 131071 : 524287);

    return hashCode;
  }

  @Override
  public int compareTo(DomainEntryPointPb other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetDomain(), other.isSetDomain());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDomain()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.domain, other.domain);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetPrimary(), other.isSetPrimary());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPrimary()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.primary, other.primary);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("DomainEntryPointPb(");
    boolean first = true;

    if (isSetDomain()) {
      sb.append("domain:");
      if (this.domain == null) {
        sb.append("null");
      } else {
        sb.append(this.domain);
      }
      first = false;
    }
    if (isSetPrimary()) {
      if (!first) sb.append(", ");
      sb.append("primary:");
      sb.append(this.primary);
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

  private static class DomainEntryPointPbStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public DomainEntryPointPbStandardScheme getScheme() {
      return new DomainEntryPointPbStandardScheme();
    }
  }

  private static class DomainEntryPointPbStandardScheme extends org.apache.thrift.scheme.StandardScheme<DomainEntryPointPb> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DomainEntryPointPb struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DOMAIN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.domain = iprot.readString();
              struct.setDomainIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PRIMARY
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.primary = iprot.readBool();
              struct.setPrimaryIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, DomainEntryPointPb struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.domain != null) {
        if (struct.isSetDomain()) {
          oprot.writeFieldBegin(DOMAIN_FIELD_DESC);
          oprot.writeString(struct.domain);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetPrimary()) {
        oprot.writeFieldBegin(PRIMARY_FIELD_DESC);
        oprot.writeBool(struct.primary);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DomainEntryPointPbTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public DomainEntryPointPbTupleScheme getScheme() {
      return new DomainEntryPointPbTupleScheme();
    }
  }

  private static class DomainEntryPointPbTupleScheme extends org.apache.thrift.scheme.TupleScheme<DomainEntryPointPb> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DomainEntryPointPb struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetDomain()) {
        optionals.set(0);
      }
      if (struct.isSetPrimary()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetDomain()) {
        oprot.writeString(struct.domain);
      }
      if (struct.isSetPrimary()) {
        oprot.writeBool(struct.primary);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DomainEntryPointPb struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.domain = iprot.readString();
        struct.setDomainIsSet(true);
      }
      if (incoming.get(1)) {
        struct.primary = iprot.readBool();
        struct.setPrimaryIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

