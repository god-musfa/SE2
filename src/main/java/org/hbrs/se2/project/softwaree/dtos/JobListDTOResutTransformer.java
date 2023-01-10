package org.hbrs.se2.project.softwaree.dtos;

import org.hibernate.transform.ResultTransformer;

import java.util.*;

public class JobListDTOResutTransformer implements ResultTransformer {

    private Map<Integer, JobListDTO> jobTestDTOMap = new LinkedHashMap<>();

    @Override
    public JobListDTO transformTuple(Object[] tuple, String[] aliases) {
        Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

        Integer jobID =  (Integer) tuple[aliasToIndexMap.get(JobListDTO.ID_ALIAS)];

        JobListDTO jobListDTO = jobTestDTOMap.computeIfAbsent(
                jobID,
                id -> new JobListDTO(tuple, aliasToIndexMap)
        );

        jobListDTO.getSkills().add(
                new JobListSkillDTO(tuple, aliasToIndexMap));
        System.out.print(aliasToIndexMap);

        jobListDTO.setCompany(new JobListCompanyDTO(tuple, aliasToIndexMap));

        System.out.print(aliasToIndexMap);
        return jobListDTO;
    }

    @Override
    public List<JobListDTO> transformList(List list) {
        return new ArrayList<>(jobTestDTOMap.values());
    }

    public  Map<String, Integer> aliasToIndexMap(
            String[] aliases) {
        Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();
        for (int i = 0; i < aliases.length; i++) {
            aliasToIndexMap.put(
                    aliases[i].toLowerCase(Locale.ROOT),
                    i);
        }
        return aliasToIndexMap;
    }
}
