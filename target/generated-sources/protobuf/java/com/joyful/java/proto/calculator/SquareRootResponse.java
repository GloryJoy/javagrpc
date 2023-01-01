// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculator/calculator.proto

package com.joyful.java.proto.calculator;

/**
 * Protobuf type {@code calculator.SquareRootResponse}
 */
public final class SquareRootResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:calculator.SquareRootResponse)
    SquareRootResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SquareRootResponse.newBuilder() to construct.
  private SquareRootResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SquareRootResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new SquareRootResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.joyful.java.proto.calculator.Calculator.internal_static_calculator_SquareRootResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.joyful.java.proto.calculator.Calculator.internal_static_calculator_SquareRootResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.joyful.java.proto.calculator.SquareRootResponse.class, com.joyful.java.proto.calculator.SquareRootResponse.Builder.class);
  }

  public static final int ROOTEDNUMBER_FIELD_NUMBER = 1;
  private double rootedNumber_;
  /**
   * <code>double rootedNumber = 1;</code>
   * @return The rootedNumber.
   */
  @java.lang.Override
  public double getRootedNumber() {
    return rootedNumber_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (java.lang.Double.doubleToRawLongBits(rootedNumber_) != 0) {
      output.writeDouble(1, rootedNumber_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (java.lang.Double.doubleToRawLongBits(rootedNumber_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, rootedNumber_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.joyful.java.proto.calculator.SquareRootResponse)) {
      return super.equals(obj);
    }
    com.joyful.java.proto.calculator.SquareRootResponse other = (com.joyful.java.proto.calculator.SquareRootResponse) obj;

    if (java.lang.Double.doubleToLongBits(getRootedNumber())
        != java.lang.Double.doubleToLongBits(
            other.getRootedNumber())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ROOTEDNUMBER_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getRootedNumber()));
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.joyful.java.proto.calculator.SquareRootResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.joyful.java.proto.calculator.SquareRootResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.joyful.java.proto.calculator.SquareRootResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code calculator.SquareRootResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:calculator.SquareRootResponse)
      com.joyful.java.proto.calculator.SquareRootResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.joyful.java.proto.calculator.Calculator.internal_static_calculator_SquareRootResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.joyful.java.proto.calculator.Calculator.internal_static_calculator_SquareRootResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.joyful.java.proto.calculator.SquareRootResponse.class, com.joyful.java.proto.calculator.SquareRootResponse.Builder.class);
    }

    // Construct using com.joyful.java.proto.calculator.SquareRootResponse.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      rootedNumber_ = 0D;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.joyful.java.proto.calculator.Calculator.internal_static_calculator_SquareRootResponse_descriptor;
    }

    @java.lang.Override
    public com.joyful.java.proto.calculator.SquareRootResponse getDefaultInstanceForType() {
      return com.joyful.java.proto.calculator.SquareRootResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.joyful.java.proto.calculator.SquareRootResponse build() {
      com.joyful.java.proto.calculator.SquareRootResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.joyful.java.proto.calculator.SquareRootResponse buildPartial() {
      com.joyful.java.proto.calculator.SquareRootResponse result = new com.joyful.java.proto.calculator.SquareRootResponse(this);
      result.rootedNumber_ = rootedNumber_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.joyful.java.proto.calculator.SquareRootResponse) {
        return mergeFrom((com.joyful.java.proto.calculator.SquareRootResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.joyful.java.proto.calculator.SquareRootResponse other) {
      if (other == com.joyful.java.proto.calculator.SquareRootResponse.getDefaultInstance()) return this;
      if (other.getRootedNumber() != 0D) {
        setRootedNumber(other.getRootedNumber());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 9: {
              rootedNumber_ = input.readDouble();

              break;
            } // case 9
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }

    private double rootedNumber_ ;
    /**
     * <code>double rootedNumber = 1;</code>
     * @return The rootedNumber.
     */
    @java.lang.Override
    public double getRootedNumber() {
      return rootedNumber_;
    }
    /**
     * <code>double rootedNumber = 1;</code>
     * @param value The rootedNumber to set.
     * @return This builder for chaining.
     */
    public Builder setRootedNumber(double value) {
      
      rootedNumber_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double rootedNumber = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearRootedNumber() {
      
      rootedNumber_ = 0D;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:calculator.SquareRootResponse)
  }

  // @@protoc_insertion_point(class_scope:calculator.SquareRootResponse)
  private static final com.joyful.java.proto.calculator.SquareRootResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.joyful.java.proto.calculator.SquareRootResponse();
  }

  public static com.joyful.java.proto.calculator.SquareRootResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SquareRootResponse>
      PARSER = new com.google.protobuf.AbstractParser<SquareRootResponse>() {
    @java.lang.Override
    public SquareRootResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<SquareRootResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SquareRootResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.joyful.java.proto.calculator.SquareRootResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
