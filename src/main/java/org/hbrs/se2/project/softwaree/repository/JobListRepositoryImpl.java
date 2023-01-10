package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.JobListDTO;
import org.hbrs.se2.project.softwaree.dtos.JobListDTOResutTransformer;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.entities.Job_;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.entities.Skill_;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class JobListRepositoryImpl implements JobListRepository {
    @PersistenceContext
    private EntityManager em;

    TypedQuery<Tuple> createCriteriaQuery() {
        // Criteria Builder
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        // Create Query
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();

        // Define FROM Clause
        Root<Job> root = criteriaQuery.from(Job.class);

        //Join<Object, Object> skillJoin = (Join<Object, Object>) root.fetch(Job_.SKILLS);
        Join<Job, Skill> skills = root.join(Job_.skills, JoinType.LEFT);
        // Main Query
        criteriaQuery.multiselect(
                root.get(Job_.id).alias("j_id"),
                root.get(Job_.title).alias("j_title"),
                skills.get(Skill_.ID).alias("s_id"),
                skills.get(Skill_.DESCRIPTION).alias("s_description"));

        // WHERE Clause
        criteriaQuery.where(criteriaBuilder.isNotNull(skills.get(Skill_.id)));
        return  em.createQuery(criteriaQuery);
    }
    @Override
    @Transactional
    public List<JobListDTO> getJobList(String searchTerm, Integer studentID, Set<String> skillSet, Integer avgRatingLimit, LocalDate timeLimit) {
        /*      Average Rating SUBQUERY     */
        String selectAvgRating = "(SELECT avg(cr.rating) FROM coll.company_rating cr WHERE c.id = cr.company_id) ";

        /*      SELECT      */
        String selectString_fetch = "SELECT     j.id as j_id,   j.title as j_title,     SUBSTRING(j.description, 1, 400) as j_description,     j.location as j_location, j.views as j_views, j.creation_date as j_creation_date, " +
                        "            s.id as s_id,      s.description as s_description, \n" +
                        "            c.id as c_id,       c.name as c_name,       c.website as c_website, " + selectAvgRating + " as j_avgcompanyrating ";

        /*      JOIN      */
        String queryString_join = " FROM coll.job_listing j \n" +
                "            LEFT JOIN coll.company c " +
                "               ON j.company_id = c.id \n" +
                "            LEFT JOIN coll.job_skills js " +
                "               ON j.id = js.job_id \n" +
                "            LEFT JOIN coll.skill s " +
                "               ON s.id = js.skill_id ";

        /*      WHERE       */
        String blackListString = " WHERE NOT EXISTS " +
                                " (SELECT * FROM coll.blacklist b WHERE (b.student_id = :studentid) " +
                                "   AND (b.company_id = j.company_id)) ";
        String skills = skillSet.isEmpty() ? " " :
                        " AND j.id IN (SELECT j.id FROM coll.skill s \n" +
                        "LEFT JOIN coll.job_skills js ON s.id = js.skill_id \n" +
                        "LEFT JOIN coll.job_listing j ON j.id = js.job_id WHERE s.description IN (:skillList) " +
                        ")  ";
        String rating = avgRatingLimit > 0 ? " AND " + selectAvgRating + " >= :ratinglimit " : " ";
        String searchTermSelect = " AND (j.description ILIKE :searchterm" +
                        "             OR (j.location ILIKE :searchterm) " +
                        "             OR (j.title ILIKE :searchterm) " +
                        "             OR (c.website ILIKE :searchterm))";
        String dateFilter = " AND j.creation_date > :datelimit ";

        /*      ORDER BY        */
        String orderByClause = " ORDER BY j.creation_date DESC ";

        /*      Create Query        */
        Query resultQuery = em.createNativeQuery(
                            selectString_fetch
                            + queryString_join
                            + blackListString
                            + skills
                            + searchTermSelect
                            + rating
                            + dateFilter
                            + orderByClause);

        /*          PARAMETERS        */
        searchTerm = "%" + searchTerm + "%";

        resultQuery.setParameter("studentid", studentID);
        resultQuery.setParameter("searchterm", searchTerm);
        resultQuery.setParameter("datelimit", timeLimit);
        if(avgRatingLimit > 0) resultQuery.setParameter("ratinglimit",  avgRatingLimit);
        if(!skills.equals(" ")) resultQuery.setParameter("skillList",  skillSet);


        /*      Log     */
        System.out.print("\n ---------   Student ID   ----------: " + studentID.toString());
        System.out.print("\n ---------  results----------: " + resultQuery.getResultList().toString() );
        System.out.print("\n ---------   SearchTerm ----------: " + searchTerm);
        System.out.print("\n ---------   skillSet  ----------: " + skillSet);
        System.out.print("\n ---------   Date Limit  ----------: " + timeLimit.toString());

        return   resultQuery
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new JobListDTOResutTransformer())
                .getResultList();
    }
    @Override
    @Transactional
    public List<JobListDTO> getJobByCompanyID(Integer companyID) {
        /*      Average Rating SUBQUERY     */
        String selectAvgRating = "(SELECT avg(cr.rating) FROM coll.company_rating cr WHERE c.id = cr.company_id) ";

        /*      SELECT      */
        String selectString_fetch = "SELECT     j.id as j_id,   j.title as j_title,     SUBSTRING(j.description, 1, 400) as j_description,     j.location as j_location, j.views as j_views, j.creation_date as j_creation_date, " +
                "            s.id as s_id,      s.description as s_description, \n" +
                "            c.id as c_id,       c.name as c_name,       c.website as c_website, " + selectAvgRating + " as j_avgcompanyrating ";

        /*      JOIN      */
        String queryString_join = " FROM coll.job_listing j \n" +
                "            LEFT JOIN coll.company c " +
                "               ON j.company_id = c.id \n" +
                "            LEFT JOIN coll.job_skills js " +
                "               ON j.id = js.job_id \n" +
                "            LEFT JOIN coll.skill s " +
                "               ON s.id = js.skill_id ";

        /*      WHERE     */
        String whereCompanyID = " WHERE c.id = :companyID ";

        /*      ORDER BY        */
        String orderByClause = " ORDER BY j.creation_date DESC ";

        /*      Create Query        */
        Query resultQuery = em.createNativeQuery(
                selectString_fetch
                        + queryString_join
                        + whereCompanyID
                        + orderByClause);

        /*          PARAMETERS        */
        resultQuery.setParameter("companyID", companyID);

        /*      Log     */
        System.out.print("\n ---------   CompanyID   ----------: " + companyID.toString());

        return  resultQuery
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new JobListDTOResutTransformer())
                .getResultList();
    }
}

