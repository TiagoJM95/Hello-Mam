package com.mindera.HelloMam.dtos.gets;

import java.io.Serializable;

public record MediaGetDto(
        Long id,
        String refId,
        String mediaType
) implements Serializable {
}