package com.rootwish.bilu.service;

import com.rootwish.bilu.model.NoteModel;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/27
 */
public interface NoteService {

    boolean saveNote(NoteModel noteModel);
    boolean updateNote(NoteModel noteModel);
    NoteModel getNoteFoInformationId(Integer informationId);
}
