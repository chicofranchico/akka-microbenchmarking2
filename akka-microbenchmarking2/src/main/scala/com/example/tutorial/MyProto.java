// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.example.tutorial;

public final class MyProto {
  private MyProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PingOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required int32 hop = 1;
    boolean hasHop();
    int getHop();
  }
  public static final class Ping extends
      com.google.protobuf.GeneratedMessage
      implements PingOrBuilder {
    // Use Ping.newBuilder() to construct.
    private Ping(Builder builder) {
      super(builder);
    }
    private Ping(boolean noInit) {}
    
    private static final Ping defaultInstance;
    public static Ping getDefaultInstance() {
      return defaultInstance;
    }
    
    public Ping getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.tutorial.MyProto.internal_static_tutorial_Ping_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.tutorial.MyProto.internal_static_tutorial_Ping_fieldAccessorTable;
    }
    
    private int bitField0_;
    // required int32 hop = 1;
    public static final int HOP_FIELD_NUMBER = 1;
    private int hop_;
    public boolean hasHop() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getHop() {
      return hop_;
    }
    
    private void initFields() {
      hop_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasHop()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, hop_);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, hop_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static com.example.tutorial.MyProto.Ping parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.example.tutorial.MyProto.Ping parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.example.tutorial.MyProto.Ping parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.example.tutorial.MyProto.Ping parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.example.tutorial.MyProto.Ping parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.example.tutorial.MyProto.Ping parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static com.example.tutorial.MyProto.Ping parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.example.tutorial.MyProto.Ping parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.example.tutorial.MyProto.Ping parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.example.tutorial.MyProto.Ping parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.example.tutorial.MyProto.Ping prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.example.tutorial.MyProto.PingOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.example.tutorial.MyProto.internal_static_tutorial_Ping_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.example.tutorial.MyProto.internal_static_tutorial_Ping_fieldAccessorTable;
      }
      
      // Construct using com.example.tutorial.MyProto.Ping.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        hop_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.example.tutorial.MyProto.Ping.getDescriptor();
      }
      
      public com.example.tutorial.MyProto.Ping getDefaultInstanceForType() {
        return com.example.tutorial.MyProto.Ping.getDefaultInstance();
      }
      
      public com.example.tutorial.MyProto.Ping build() {
        com.example.tutorial.MyProto.Ping result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private com.example.tutorial.MyProto.Ping buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        com.example.tutorial.MyProto.Ping result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public com.example.tutorial.MyProto.Ping buildPartial() {
        com.example.tutorial.MyProto.Ping result = new com.example.tutorial.MyProto.Ping(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.hop_ = hop_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.example.tutorial.MyProto.Ping) {
          return mergeFrom((com.example.tutorial.MyProto.Ping)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(com.example.tutorial.MyProto.Ping other) {
        if (other == com.example.tutorial.MyProto.Ping.getDefaultInstance()) return this;
        if (other.hasHop()) {
          setHop(other.getHop());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasHop()) {
          
          return false;
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              hop_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required int32 hop = 1;
      private int hop_ ;
      public boolean hasHop() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public int getHop() {
        return hop_;
      }
      public Builder setHop(int value) {
        bitField0_ |= 0x00000001;
        hop_ = value;
        onChanged();
        return this;
      }
      public Builder clearHop() {
        bitField0_ = (bitField0_ & ~0x00000001);
        hop_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:tutorial.Ping)
    }
    
    static {
      defaultInstance = new Ping(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:tutorial.Ping)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_Ping_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_tutorial_Ping_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rmessage.proto\022\010tutorial\"\023\n\004Ping\022\013\n\003hop" +
      "\030\001 \002(\005B\037\n\024com.example.tutorialB\007MyProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_tutorial_Ping_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_tutorial_Ping_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_tutorial_Ping_descriptor,
              new java.lang.String[] { "Hop", },
              com.example.tutorial.MyProto.Ping.class,
              com.example.tutorial.MyProto.Ping.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}