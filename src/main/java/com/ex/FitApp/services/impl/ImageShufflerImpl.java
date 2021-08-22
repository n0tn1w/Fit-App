package com.ex.FitApp.services.impl;

import com.ex.FitApp.services.ImageShuffler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ImageShufflerImpl implements ImageShuffler {
  @Override
  public void shuffle(List<String> images) {
    Collections.shuffle(images);
  }
}
