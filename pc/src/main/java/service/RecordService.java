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

    /**
     * 查询用户是第几次做题
     * @param u_id 用户编号
     * @return
     */
    public abstract int queryForFrequency(int u_id);
}
