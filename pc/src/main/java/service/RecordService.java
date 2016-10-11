package service;

import entity.Record;

/**
 * Created by Bruce-Jiang on 2016/10/9.
 */
public abstract class RecordService {
    /**
     * 插入一条答题记录
     * @param record 答题记录Model
     * @return
     */
    public abstract int insertOneRecord(Record record);
}
