package it.besmartbeopen.gitlab.pagination;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Page<T> {
  private final List<T> data;
  private final int total;
  private final int totalPages;
  private final int perPage;
}