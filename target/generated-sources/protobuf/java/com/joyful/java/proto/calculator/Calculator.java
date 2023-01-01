// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculator/calculator.proto

package com.joyful.java.proto.calculator;

public final class Calculator {
  private Calculator() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_SumRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_SumRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_SumResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_SumResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_PrimeNumberDecomositionRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_PrimeNumberDecomositionRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_PrimeNumberDecompositionResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_PrimeNumberDecompositionResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_ComputeAverageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_ComputeAverageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_ComputeAverageResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_ComputeAverageResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_FindMaximumRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_FindMaximumRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_FindMaximumResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_FindMaximumResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_SquareRootRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_SquareRootRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_SquareRootResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_SquareRootResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033calculator/calculator.proto\022\ncalculato" +
      "r\"9\n\nSumRequest\022\024\n\014first_number\030\001 \001(\005\022\025\n" +
      "\rsecond_number\030\002 \001(\005\"!\n\013SumResponse\022\022\n\ns" +
      "um_result\030\001 \001(\005\"0\n\036PrimeNumberDecomositi" +
      "onRequest\022\016\n\006number\030\001 \001(\005\"8\n PrimeNumber" +
      "DecompositionResponse\022\024\n\014prime_factor\030\001 " +
      "\001(\005\"\'\n\025ComputeAverageRequest\022\016\n\006number\030\001" +
      " \001(\005\")\n\026ComputeAverageResponse\022\017\n\007averag" +
      "e\030\001 \001(\001\"$\n\022FindMaximumRequest\022\016\n\006number\030" +
      "\001 \001(\005\"&\n\023FindMaximumResponse\022\017\n\007maximum\030" +
      "\001 \001(\005\"#\n\021SquareRootRequest\022\016\n\006number\030\001 \001" +
      "(\005\"*\n\022SquareRootResponse\022\024\n\014rootedNumber" +
      "\030\001 \001(\0012\310\003\n\021CalculatorService\0228\n\003Sum\022\026.ca" +
      "lculator.SumRequest\032\027.calculator.SumResp" +
      "onse\"\000\022x\n\030PrimeNumberDecomposition\022*.cal" +
      "culator.PrimeNumberDecomositionRequest\032," +
      ".calculator.PrimeNumberDecompositionResp" +
      "onse\"\0000\001\022[\n\016ComputeAverage\022!.calculator." +
      "ComputeAverageRequest\032\".calculator.Compu" +
      "teAverageResponse\"\000(\001\022T\n\013FindMaximum\022\036.c" +
      "alculator.FindMaximumRequest\032\037.calculato" +
      "r.FindMaximumResponse\"\000(\0010\001\022L\n\tSqareRoot" +
      "\022\035.calculator.SquareRootRequest\032\036.calcul" +
      "ator.SquareRootResponse\"\000B\'\n com.joyful." +
      "java.proto.calculatorP\001\210\001\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_calculator_SumRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_calculator_SumRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_SumRequest_descriptor,
        new java.lang.String[] { "FirstNumber", "SecondNumber", });
    internal_static_calculator_SumResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_calculator_SumResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_SumResponse_descriptor,
        new java.lang.String[] { "SumResult", });
    internal_static_calculator_PrimeNumberDecomositionRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_calculator_PrimeNumberDecomositionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_PrimeNumberDecomositionRequest_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_calculator_PrimeNumberDecompositionResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_calculator_PrimeNumberDecompositionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_PrimeNumberDecompositionResponse_descriptor,
        new java.lang.String[] { "PrimeFactor", });
    internal_static_calculator_ComputeAverageRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_calculator_ComputeAverageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_ComputeAverageRequest_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_calculator_ComputeAverageResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_calculator_ComputeAverageResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_ComputeAverageResponse_descriptor,
        new java.lang.String[] { "Average", });
    internal_static_calculator_FindMaximumRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_calculator_FindMaximumRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_FindMaximumRequest_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_calculator_FindMaximumResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_calculator_FindMaximumResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_FindMaximumResponse_descriptor,
        new java.lang.String[] { "Maximum", });
    internal_static_calculator_SquareRootRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_calculator_SquareRootRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_SquareRootRequest_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_calculator_SquareRootResponse_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_calculator_SquareRootResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_SquareRootResponse_descriptor,
        new java.lang.String[] { "RootedNumber", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}