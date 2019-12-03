package com.pinwheel.gofast.service;

import com.pinwheel.gofast.entity.Company;
import com.pinwheel.gofast.entity.Point;
import com.pinwheel.gofast.entity.User;
import com.pinwheel.gofast.entity.VerificationToken;
import com.pinwheel.gofast.entity.dto.PointDto;
import com.pinwheel.gofast.repository.VerificationTokenRepository;
import com.pinwheel.gofast.repository.api.PointRepository;
import com.pinwheel.gofast.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SimplePointService implements PointService {
    private final PointRepository pointRepository;

    @Override
    public Point create(PointDto pointDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Point point = new Point(pointDto.getAddress(), (Company) authentication.getPrincipal());
        return pointRepository.save(point);
    }

    @Override
    public List<Point> getByUserId(Long id) {
        return pointRepository.findByCompanyId(id);
    }

    @Override
    public List<Point> search(String search, Long from) {
        return pointRepository.findByAddressContainsIgnoreCase(search);
    }
}
