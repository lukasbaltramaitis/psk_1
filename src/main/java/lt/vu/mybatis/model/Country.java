package lt.vu.mybatis.model;

public class Country {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column countries.id
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column countries.name
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column countries.id
     *
     * @return the value of countries.id
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column countries.id
     *
     * @param id the value for countries.id
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column countries.name
     *
     * @return the value of countries.name
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column countries.name
     *
     * @param name the value for countries.name
     *
     * @mbg.generated Thu Apr 16 22:32:08 EEST 2020
     */
    public void setName(String name) {
        this.name = name;
    }
}