package it.besmartbeopen.gitlab.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of={"id", "iid", "title"})
@EqualsAndHashCode(of="id")
public class Issue {

  private int projectId;
  private Milestone milestone;
  private User author;
  private String description;
  private IssueState state;
  private int iid;
  private int id;
  private User assignee;
  private List<String> labels;
  private String title;
  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;
  private boolean subscribed;
  // readonly
  private int userNotesCount;
  private String webUrl;
  private boolean confidential;
}
