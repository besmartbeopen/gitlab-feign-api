package it.besmartbeopen.gitlab.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of={"id", "username"})
@EqualsAndHashCode(of="id")
public class User {
  private int id;
  private String username;
  private String name;
  private UserState state;
  private String avatarUrl;
  private String webUrl;
}
