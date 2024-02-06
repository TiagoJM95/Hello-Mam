package com.mindera.HelloMam.dtos.gets;

import java.io.Serializable;

public record MediaGetDto(
        Long id,
        Long refId,
        String mediaType
) implements Serializable {
}