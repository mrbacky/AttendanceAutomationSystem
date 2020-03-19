/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.be;

/**
 *
 * @author Martin
 */
public class Course {
    
    private String name;
    private String startDuration;
    private String endDuration;
    
    public Course(String name, String startDuration, String endDuration) {
        this.name = name;
        this.startDuration = startDuration;
        this.endDuration = endDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDuration() {
        return startDuration;
    }

    public void setStartDuration(String startDuration) {
        this.startDuration = startDuration;
    }

    public String getEndDuration() {
        return endDuration;
    }

    public void setEndDuration(String endDuration) {
        this.endDuration = endDuration;
    }
    
    
    
}
