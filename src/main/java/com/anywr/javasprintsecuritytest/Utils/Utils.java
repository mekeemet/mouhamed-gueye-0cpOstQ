package com.anywr.javasprintsecuritytest.Utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Utils {
    /**
     * Generate a {@link Pageable} base on the configuration values provided
     *
     * @param page      The Page number
     * @param perPage   The max amount of elements per page
     * @param orderBy   The name of the fiel to order
     * @param direction The order direction (ASC or DESC)
     * @return a {@link Pageable} configured for the search
     */
    public static Pageable getPageable(final int page, final int perPage, final String orderBy,
                                       final String direction) {
        Pageable pageable;
        if (StringUtils.isNotEmpty(orderBy) && StringUtils.isNotEmpty(direction)) {
            Sort.Direction dir;
            if (direction.equalsIgnoreCase("asc")) {
                dir = Sort.Direction.ASC;
            } else {
                dir = Sort.Direction.DESC;
            }
            Sort sort = Sort.by(dir, orderBy);
            pageable = PageRequest.of(page, perPage, sort);
        } else {
            pageable = PageRequest.of(page, perPage);
        }
        return pageable;
    }
}
