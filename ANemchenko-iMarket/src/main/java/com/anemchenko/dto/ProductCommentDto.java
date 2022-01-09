package com.anemchenko.dto;

import com.anemchenko.model.Product;
import com.anemchenko.model.ProductComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductCommentDto {
    private Long commentId;
    private String userName;
    private String productTitle;
    private String comment;

    public ProductCommentDto(ProductComment productComment){
        this.commentId = productComment.getProductCommentId();
        this.userName = productComment.getUser().getUsername();
        this.productTitle = productComment.getProduct().getTitle();
        this.comment = productComment.getComment();

    }
}
