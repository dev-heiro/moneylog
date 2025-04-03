package org.codenova.moneylog.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.codenova.moneylog.entity.Verification;

@Mapper
public interface VerificationRepository {

    public int save(Verification verification);

    public Verification findByToken(@Param("token") String token);
}
