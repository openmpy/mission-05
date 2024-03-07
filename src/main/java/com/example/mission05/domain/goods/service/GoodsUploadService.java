package com.example.mission05.domain.goods.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.GetGoodsResponseDto;
import com.example.mission05.domain.goods.entity.Goods;
import com.example.mission05.domain.goods.repository.GoodsRepository;
import com.example.mission05.domain.member.repository.MemberRepository;
import com.example.mission05.global.exception.CustomApiException;
import com.example.mission05.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class GoodsUploadService {

    private static final String AWS_S3_URL = "https://hanghae.s3.ap-northeast-2.amazonaws.com/goods/";

    private final GoodsRepository goodsRepository;
    private final MemberRepository memberRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public GetGoodsResponseDto uploadGoodsImage(String email, Long goodsId, MultipartFile file) {
        if (!memberRepository.existsByEmail(email)) {
            throw new CustomApiException(ErrorCode.NOT_FOUND_EMAIL.getMessage());
        }
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_GOODS.getMessage())
        );

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        String fileName = file.getOriginalFilename();
        String extension = getExtension(fileName);

        String fileUrl = goods.getId() + "." + extension;
        String key = "goods/" + fileUrl;

        try {
            if (amazonS3Client.doesObjectExist(bucket, key)) {
                amazonS3Client.deleteObject(bucket, key);
            }
            amazonS3Client.putObject(bucket, key, file.getInputStream(), metadata);
            goods.uploadImageUrl(AWS_S3_URL + fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GetGoodsResponseDto(goods);
    }

    private String getExtension(String fileName) {
        int extensionIndex = fileName.lastIndexOf(".");
        return fileName.substring(extensionIndex + 1);
    }
}
