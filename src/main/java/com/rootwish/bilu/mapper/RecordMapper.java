package com.rootwish.bilu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rootwish.bilu.entity.RecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/13
 */
@Repository
@Mapper
public interface RecordMapper extends BaseMapper<RecordEntity> {
}
