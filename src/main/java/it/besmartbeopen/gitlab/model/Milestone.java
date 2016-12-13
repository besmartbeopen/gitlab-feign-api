package it.besmartbeopen.gitlab.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(of={"id", "iid", "title"})
@EqualsAndHashCode(of="id")
public class Milestone {

  private LocalDate dueDate;
  private int projectId;
  private int id;
  private int iid;
  private MilestoneState state;
  private String title;
  private String description;
  private LocalDate startDate;
  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;
}
