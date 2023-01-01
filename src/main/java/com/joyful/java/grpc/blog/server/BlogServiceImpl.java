package com.joyful.java.grpc.blog.server;

import com.joyful.java.grpc.blog.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class BlogServiceImpl extends BlogServiceGrpc.BlogServiceImplBase {

    private MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    private MongoDatabase mongoDatabase = mongoClient.getDatabase("blogdb");
    private MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("blog");

    @Override
    public void createBlog(CreateBlogRequest request, StreamObserver<CreateBlogResponse> responseObserver) {

        System.out.println("Receive create blog request");
        Blog blog = request.getBlog();
        Document document = new Document("author_id", blog.getAuthorId())
                .append("title", blog.getTitle())
                .append("content", blog.getContent());


        System.out.println("insert blog...");
        mongoCollection.insertOne(document);

        String id = document.getObjectId("_id").toString();

//        CreateBlogResponse createBlogResponse = CreateBlogResponse
//                .newBuilder()
//                .setBlog(Blog.newBuilder()
//                        .setAuthorId(blog.getAuthorId())
//                        .setContent(blog.getContent())
//                        .setTitle(blog.getTitle())
//                        .setId(id)
//                        .build())
//                .build();

        //Or another way
        CreateBlogResponse createBlogResponse = CreateBlogResponse
                .newBuilder()
                .setBlog(blog.toBuilder().setId(id))
                .build();

        responseObserver.onNext(createBlogResponse);
        responseObserver.onCompleted();


    }

    @Override
    public void readBlog(ReadBlogRequest request, StreamObserver<ReadBlogResponse> responseObserver) {

        String blogId = request.getId();
        Bson bsonFilter = eq("_id", new ObjectId(blogId));
        FindIterable<Document> findIterable = mongoCollection.find(bsonFilter);

        MongoCursor<Document> cursor = findIterable.iterator();
        Document document;

        if (cursor.hasNext()) {
            document = cursor.next();
            Blog blog = Blog.newBuilder()
                    .setId(document.getObjectId("_id").toHexString())
                    .setAuthorId(document.getString("author_id"))
                    .setContent(document.getString("content"))
                    .setTitle(document.getString("title"))
                    .build();

            ReadBlogResponse readBlogResponse = ReadBlogResponse
                    .newBuilder()
                    .setBlog(blog)
                    .build();

            responseObserver.onNext(readBlogResponse);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("The blog with the given id " + blogId + " is not found")
                            .asRuntimeException()
            );

        }


    }

    @Override
    public void updateBlog(UpdateBlogRequest request, StreamObserver<UpdateBlogResponse> responseObserver) {

        String blogId = request.getBlog().getId();

        Bson bsonFilter = eq("_id", new ObjectId(blogId));

        try {
            Document document = mongoCollection.find(bsonFilter).first();

            if (document != null) {
                //update blog
                Document newDocument = new Document("author_id", request.getBlog().getAuthorId())
                        .append("title", request.getBlog().getTitle())
                        .append("content", request.getBlog().getContent());

                mongoCollection.replaceOne(bsonFilter, newDocument);

                Blog blog = Blog.newBuilder()
                        .setAuthorId(newDocument.getString("title"))
                        .setContent(newDocument.getString("content"))
                        .setTitle(newDocument.getString("title"))
                        .setId(document.getObjectId("_id").toHexString())
                        .build();

                //build response
                UpdateBlogResponse updateBlogResponse = UpdateBlogResponse.newBuilder()
                        .setBlog(blog)
                        .build();

                //return the result
                responseObserver.onNext(updateBlogResponse);
                responseObserver.onCompleted();


            } else {
                responseObserver.onError(Status.NOT_FOUND
                        .withDescription("The blog id " + blogId + " is not found. The update operations failed.")
                        .asRuntimeException());
            }

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    @Override
    public void deleteBlog(DeleteBlogRequest request, StreamObserver<DeleteBlogResponse> responseObserver) {
        String blogId = request.getId();

        try {

            ObjectId objectId = new ObjectId(blogId);
            Bson bsonFilter = Filters.eq("_id", objectId);

            Optional<Document> optionalDocument = Optional.ofNullable(mongoCollection.find(bsonFilter).first());
            optionalDocument
                    .ifPresentOrElse(document -> {
                        DeleteResult deleteResult = mongoCollection.deleteOne(bsonFilter);
                        System.out.println(deleteResult);

                        Long deleteCount = deleteResult.getDeletedCount();

                        DeleteBlogResponse deleteBlogResponse = DeleteBlogResponse.newBuilder()
                                .setDeleteCount(deleteCount.intValue())
                                .setAcknowledgedDeletion(deleteResult.wasAcknowledged())
                                .build();

                        responseObserver.onNext(deleteBlogResponse);
                        responseObserver.onCompleted();

                    }, () -> responseObserver.onError(
                            Status.NOT_FOUND
                                    .withDescription("The provided document Id (" + blogId + ") is not found.")
                                    .asRuntimeException()
                    ));
        } catch (IllegalArgumentException e) {
            System.out.println("The provided id is not valid.");
            System.out.println(e.getMessage());
            responseObserver.onError(
                    Status
                            .INVALID_ARGUMENT
                            .withDescription("The given id (" + blogId + ") is not a valid id format.")
                            .asRuntimeException()
            );
        } catch (Exception e) {
            System.out.println("There is another error.");
            System.out.println(e.getMessage());
            responseObserver.onError(
                    Status.UNKNOWN
                            .withDescription("There is an error occured with unknown cause.")
                            .asRuntimeException()
            );

        }


//        try {
//            Document findingDocument = mongoCollection.find(bsonFilter).first();
//
//            if (findingDocument != null) {
//                DeleteResult deleteResult = mongoCollection.deleteOne(bsonFilter);
//                System.out.println(deleteResult);
//
//                Long deleteCount = deleteResult.getDeletedCount();
//
//                DeleteBlogResponse deleteBlogResponse = DeleteBlogResponse.newBuilder()
//                        .setDeleteCount(deleteCount.intValue())
//                        .setAcknowledgedDeletion(deleteResult.wasAcknowledged())
//                        .build();
//
//                responseObserver.onNext(deleteBlogResponse);
//                responseObserver.onCompleted();
//
//
//            } else {
//                responseObserver.onError(
//                        Status.NOT_FOUND
//                                .withDescription("The provided document Id (" + blogId + ") is not found.")
//                                .asRuntimeException()
//                );
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }


    }

    @Override
    public void listBlog(ListBlogRequest request, StreamObserver<ListBlogResponse> responseObserver) {

        try {

            mongoCollection.find().forEach(document -> {
                responseObserver.onNext(ListBlogResponse.newBuilder()
                        .setBlog(
                                Blog.newBuilder()
                                        .setId(document.getObjectId("_id").toHexString())
                                        .setTitle(document.getString("title"))
                                        .setContent(document.getString("content"))
                                        .setAuthorId(document.getString("author_id"))
                                        .build()
                        )
                        .build());
            });

            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }

    }
}
