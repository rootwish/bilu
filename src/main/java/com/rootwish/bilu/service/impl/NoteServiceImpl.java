package com.rootwish.bilu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rootwish.bilu.entity.NoteEntity;
import com.rootwish.bilu.mapper.NoteMapper;
import com.rootwish.bilu.model.NoteModel;
import com.rootwish.bilu.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/27
 */
@Service
@Slf4j
public class NoteServiceImpl extends ServiceImpl<NoteMapper,NoteEntity> implements NoteService{

    @Override
    public boolean saveNote(NoteModel noteModel) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setInformationId(noteModel.getInformationId());
        noteEntity.setNoteText(noteModel.getNoteText());
        return this.save(noteEntity);
    }

    @Override
    public boolean updateNote(NoteModel noteModel) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(noteModel.getId());
        noteEntity.setInformationId(noteModel.getInformationId());
        noteEntity.setNoteText(noteModel.getNoteText());
        return this.updateById(noteEntity);
    }

    @Override
    public NoteModel getNoteFoInformationId(Integer informationId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("information_id",informationId);
        NoteEntity one = this.getOne(queryWrapper);
        NoteModel noteModel = new NoteModel();
        if(null != one) {
            noteModel.setId(one.getId());
            noteModel.setInformationId(one.getInformationId());
            noteModel.setNoteText(one.getNoteText());
        }
        return noteModel;
    }
}
