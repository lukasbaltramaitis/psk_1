package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Action;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface ActionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actions
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actions
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    int insert(Action record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actions
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    Action selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actions
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    List<Action> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actions
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    int updateByPrimaryKey(Action record);

    List<Action> selectByRoundNr(@Param("roundNr") int roundNr);
}