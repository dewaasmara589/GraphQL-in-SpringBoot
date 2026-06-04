package dev.dewa.graphqljava.review;

public record ReviewFilter(
        Integer rating,
        Boolean verified,
        String reviewerName) {
}
