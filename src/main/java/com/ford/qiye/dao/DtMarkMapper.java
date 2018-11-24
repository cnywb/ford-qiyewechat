package com.ford.qiye.dao;

import com.ford.qiye.model.DtMark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanglijun on 16/8/10.
 */
public interface DtMarkMapper {



    /***
     * 设置用户标签
     * @param userId
     * @param markId
     */
    void insertUserMark(@Param ("userId")String userId,@Param ("markId")Long markId);
    /***
     *
     * @param userId
     * @return
     */
    List<DtMark> findByUserId(@Param ("userId")String userId);

    /***
     * 删除用户的标签;
     * @param userId
     */
     void deleteByUserId(@Param ("userId")String userId);


    /**查询所有信息*/
    List<DtMark> queryAll();


    /***
     * 分页查询
     * @param id
     * @param name
     * @return
     */
    List<DtMark> queryList(@Param ("id") Long id,@Param ("name") String name);

    /***
     * 新增
     * @param mark
     * @return
     */
    Long insert(@Param ("mark")DtMark mark);

    /***
     * 新增
     * @param mark
     * @return
     */
    void  update(@Param ("mark")DtMark mark);

    /***
     * 根据Id查找;
     * @param id
     * @return DtMark
     */
    DtMark findById(@Param ("id") Long id);

    /***
     * 根据ID删除
     * @param id
     */
    void  deleteById(@Param ("id") Long id);

    /***
     *
     * @param id
     * @param name
     * @param ifActive
     */
    void updateById(@Param ("id")Long id, @Param ("name")String name, @Param ("ifActive")Integer ifActive);
}
