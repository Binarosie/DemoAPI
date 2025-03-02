package com.example.demo.entity;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "universities")

public class University implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", length = 7)
    @Size(max = 7)
    private String universityId;

    @Column(name = "UniversityName", columnDefinition = "NVARCHAR(255)", nullable = false)
    @NotEmpty(message = "University name is required")
    private String universityName;

    @Column(name = "UniversityAddress", columnDefinition = "NVARCHAR(255)", nullable = false)
    @NotEmpty(message = "University address is required")
    private String universityAddress;

    @Column(name = "CreatedOn", columnDefinition = "NVARCHAR(255)", nullable = false)
    @NotEmpty(message = "Created day is required")
    private String createOn;

    @JsonIgnore
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Course> course;


    public University( String universityId,String universityName,String universityAddress,  String createOn) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.universityAddress = universityAddress;
        this.createOn = createOn;
    }

    public @NotEmpty(message = "Created day is required") String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(@NotEmpty(message = "Created day is required") String createOn) {
        this.createOn = createOn;
    }

    public @NotEmpty(message = "University address is required") String getUniversityAddress() {
        return universityAddress;
    }

    public void setUniversityAddress(@NotEmpty(message = "University address is required") String universityAddress) {
        this.universityAddress = universityAddress;
    }

    public @Size(max = 7) String getUniversityId() {
            return universityId;
    }

    public void setUniversityId(@Size(max = 7) String universityId) {
        this.universityId = universityId;
    }

    public @NotEmpty(message = "University name is required") String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(@NotEmpty(message = "University name is required") String universityName) {
        this.universityName = universityName;
    }
}
