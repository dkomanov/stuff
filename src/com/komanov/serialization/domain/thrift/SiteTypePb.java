/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.komanov.serialization.domain.thrift;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-08-14")
public enum SiteTypePb implements org.apache.thrift.TEnum {
  UnknownSiteType(0),
  Flash(1),
  Silverlight(2),
  Html5(3);

  private final int value;

  private SiteTypePb(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static SiteTypePb findByValue(int value) { 
    switch (value) {
      case 0:
        return UnknownSiteType;
      case 1:
        return Flash;
      case 2:
        return Silverlight;
      case 3:
        return Html5;
      default:
        return null;
    }
  }
}
