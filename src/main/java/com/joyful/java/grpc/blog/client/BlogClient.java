package com.joyful.java.grpc.blog.client;

import com.joyful.java.grpc.blog.*;
import com.joyful.java.grpc.calculator.client.CalculatorClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public class BlogClient {
    public void run() {

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .usePlaintext()
                .build();

//        doCreateBlogCall(managedChannel);
//        doReadBlogCall(managedChannel);
//        doUpdateBlogCall(managedChannel);
//        doDeleteBlogCall(managedChannel);
        doListBlogCall(managedChannel);
        managedChannel.shutdown();

    }

    private void doListBlogCall(ManagedChannel managedChannel) {
        BlogServiceGrpc.BlogServiceBlockingStub blogServiceBlockingStub = BlogServiceGrpc.newBlockingStub(managedChannel);


        ListBlogRequest listBlogRequest = ListBlogRequest.newBuilder().build();
        blogServiceBlockingStub.listBlog(listBlogRequest)
                .forEachRemaining(listBlogResponse -> {
                            System.out.println(listBlogResponse.getBlog());
                        }
                );

    }

    private void doDeleteBlogCall(ManagedChannel managedChannel) {
        BlogServiceGrpc.BlogServiceBlockingStub blogServiceBlockingStub = BlogServiceGrpc.newBlockingStub(managedChannel);

        String blogId = "63ad21354ba4590c457a9f90";

        DeleteBlogRequest deleteBlogRequest = DeleteBlogRequest.newBuilder()
                .setId(blogId)
                .build();

        try {

            DeleteBlogResponse deleteBlogResponse = blogServiceBlockingStub.deleteBlog(deleteBlogRequest);
            System.out.println("The result from trying to delete a blog " + deleteBlogResponse);

        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode() == Status.Code.NOT_FOUND) {
                System.out.println("The give document id (" + blogId + ") is not found on the server.");
                System.out.println("The given error message is " + sre.getMessage());
            } else {
                System.out.println("There is unknown error occured as the following description : " + sre.getMessage());
                sre.printStackTrace();
            }

        }


    }

    private void doUpdateBlogCall(ManagedChannel managedChannel) {

        BlogServiceGrpc.BlogServiceBlockingStub blogServiceBlockingStub = BlogServiceGrpc.newBlockingStub(managedChannel);

        Blog blog = Blog.newBuilder()
                .setTitle("Updated title")
                .setContent("We have got an update")
                .setAuthorId("Glory Joy")
                .setId("63ad2202c0a1a05bf3041d2a")
                .build();

        UpdateBlogRequest updateBlogRequest = UpdateBlogRequest.newBuilder()
                .setBlog(blog)
                .build();

        UpdateBlogResponse updateBlogResponse = blogServiceBlockingStub.updateBlog(updateBlogRequest);

        System.out.println("The update request has been sent to server. The result is = " + updateBlogResponse.getBlog());

    }

    private void doReadBlogCall(ManagedChannel managedChannel) {
        BlogServiceGrpc.BlogServiceBlockingStub blogServiceBlockingStub = BlogServiceGrpc.newBlockingStub(managedChannel);

        ReadBlogRequest request = ReadBlogRequest
                .newBuilder()
                .setId("63ac1116d69e2b025a743164")
                .build();

        ReadBlogResponse response = blogServiceBlockingStub.readBlog(request);

        System.out.println("Receiving blog by Id = " + request.getId() + " with the content = " + response.toString());
    }

    private void doCreateBlogCall(ManagedChannel managedChannel) {
        BlogServiceGrpc.BlogServiceBlockingStub blogServiceBlockingStub = BlogServiceGrpc.newBlockingStub(managedChannel);

        CreateBlogRequest createBlogRequest = CreateBlogRequest
                .newBuilder()
                .setBlog(Blog.newBuilder()
                        .setAuthorId("Joy")
                        .setContent("new blog")
                        .setTitle("Hello new blog"))
                .build();


        CreateBlogResponse createBlogResponse = blogServiceBlockingStub.createBlog(createBlogRequest);

        System.out.println("Receive response " + createBlogResponse.getBlog());

//        ReadBlogRequest request = ReadBlogRequest
//                .newBuilder()
//                .setId(createBlogResponse.getBlog().getId())
//                .build();
//
//        ReadBlogResponse response = blogServiceBlockingStub.readBlog(request);
//
//        System.out.println("Receiving blog by Id = " + request.getId() + " with the content = " + response.toString());


    }

    public static void main(String[] args) {

        BlogClient blogClient = new BlogClient();
        blogClient.run();

    }
}
