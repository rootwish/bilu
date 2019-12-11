package com.rootwish.bilu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rootwish.bilu.entity.InformationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/10
 */
@Repository
@Mapper
public interface InformationMapper extends BaseMapper<InformationEntity> {
}
