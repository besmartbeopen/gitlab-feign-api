package it.besmartbeopen.gitlab.model;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of="id")
@EqualsAndHashCode(of="id")
public class IssueNote {

  private Integer id;
  private String body;
  // private ...   attachment;
  private User author;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean system;
  private boolean upvote;
  private boolean downvote;
  private Integer noteableId;
  private String noteableType;
}
