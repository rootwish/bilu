package com.rootwish.bilu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rootwish.bilu.entity.ClassificationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/5
 */
@Repository
@Mapper
public interface ClassificationMapper extends BaseMapper<ClassificationEntity> {
}
