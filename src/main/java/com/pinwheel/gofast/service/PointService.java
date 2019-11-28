package com.pinwheel.gofast.service;

import com.pinwheel.gofast.entity.Point;
import com.pinwheel.gofast.entity.User;
import com.pinwheel.gofast.entity.VerificationToken;
import com.pinwheel.gofast.entity.dto.PointDto;

import java.util.List;

public interface PointService {
    Point create(PointDto pointDto);

    List<Point> getByUserId(Long id);
}
