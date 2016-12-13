package it.besmartbeopen.gitlab.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of="name")
public class UploadFile {

  private final String name;
  private final byte[] data;

  public UploadFile(File file) throws IOException {
    name = file.getName();
    data = Files.readAllBytes(file.toPath());
  }

  public UploadFile(String name, byte[] data) {
    this.name = name;
    this.data = data;
  }
}
