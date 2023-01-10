package org.hbrs.se2.project.softwaree.dtos;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class JobListCompanyDTO implements Serializable {
    static final String NAME_ALIAS = "c_name";
    static final String ID_ALIAS = "c_id";
    static final String WEBSITE_ALIAS = "c_website";

    private Integer id;
    private String name;
    private String website;
    public JobListCompanyDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
        this.id = (Integer)(tuple[aliasToIndexMap.get(ID_ALIAS)]);
        this.name = (String) (tuple[aliasToIndexMap.get(NAME_ALIAS)]);
        this.website = (String) (tuple[aliasToIndexMap.get(WEBSITE_ALIAS)]);
    }

    public String getWebsite() {
        return website;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobListCompanyDTO entity = (JobListCompanyDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.website, entity.website);

    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
