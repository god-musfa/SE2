package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Student;

/** This interface just ensures that the Controller used for feedback from RatingComponent is capable of handling
 *  rating changes.
  */

public interface RatingFeedbackControl {

    public void setRating(int rating, int student_id, int company_id);

    public int getRating(int student_id, int company_id);

}
